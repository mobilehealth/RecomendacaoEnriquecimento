package mobilehealth.prc.analyzer.semantic.indexer;

import java.io.File;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gate.Factory;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OConstants.OntologyFormat;
import gate.creole.ontology.*;
import gate.util.ClosableIterator;
import gate.util.GateException;

public class RelationCalcImplGate implements RelationCalc {

	private gate.creole.ontology.Ontology domainOnto;
	private gate.creole.ontology.Ontology profileOnto;
	
	public RelationCalcImplGate() {
		
		if (!Gate.isInitialised()) {
			try {
				//Initialize the GATE
				Gate.init();

				//Load the required plugins
				Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Tools").toURI().toURL());
				Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Gazetteer_Ontology_Based").toURI().toURL());
				Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Ontology").toURI().toURL());
			} catch (MalformedURLException | GateException e) {
				e.printStackTrace();
			}
		}

		try {
			domainOnto = (gate.creole.ontology.Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", Factory.newFeatureMap());
			profileOnto = (gate.creole.ontology.Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", Factory.newFeatureMap());

		} catch (ResourceInstantiationException e) {
			System.out.println("erro: inicialização da ontologia");
			e.printStackTrace();
		}
	}

	@Override
	public float getResourceDomainRelation(String resourceURI,
			String domainURI, boolean recursive,
			String profileOntoText, String domainOntoText) {
		
		//Limpa as ontologias para garantir que não hajam dados de chamadas anteriores
		profileOnto.cleanOntology();
		domainOnto.cleanOntology();
		
		//Carrega os novos dados nas ontologias
		StringReader profReader = new StringReader(profileOntoText);
		profileOnto.readOntologyData(profReader, null, OntologyFormat.RDFXML, false);
		
		Set<OClass> profileTopClasses = profileOnto.getOClasses(true);
		String profileNamespace = profileTopClasses.iterator().next().getONodeID().getNameSpace();
		profileOnto.setDefaultNameSpace(profileNamespace);

		
		StringReader domReader = new StringReader(domainOntoText);
		domainOnto.readOntologyData(domReader, null, OntologyFormat.RDFXML, false);
		
		Set<OClass> domainTopClasses = domainOnto.getOClasses(true);
		String domainamespace = domainTopClasses.iterator().next().getONodeID().getNameSpace();
		domainOnto.setDefaultNameSpace(domainamespace);
		
		// Calcula quantos links há no recurso em teste e verifica quantos são do domínio em questão
		//retorna um índice percentual 0..1
		
		//Lê todos os links para este recurso
		OInstance resInst = profileOnto.getOInstance(profileOnto.createOURI(resourceURI));
		ObjectProperty objProp = profileOnto.getObjectProperty(profileOnto.createOURI(
				profileOnto.getDefaultNameSpace() + "hasLinks"));
		
		List<OInstance> resourceLinks = null;
		
		if (objProp.isValidDomain(resInst))
			resourceLinks = resInst.getObjectPropertyValues(objProp);
		else
			return -1.f;
		
		//Lê os links para o domínio específico
		List<OInstance> linksToDomain = new ArrayList<OInstance>();
		
		for (OInstance link : resourceLinks) {
			
			DatatypeProperty dtp = profileOnto.getDatatypeProperty(
					profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "URI"));
			
			//Se ligado diretamente ao domínio ou a um de seus filhos...
			String linkResourceStr = link.getDatatypePropertyValues(dtp).get(0).toString();
			
			//Verifica se o linkResourceStr se refere à uma classe ou instância
			//Primeiro verificamos se o resource casa exatamente
			//Se for uma classe, o resource buscado é uma classe filha ou instância filha?
			//Se for uma instância, estamos buscando uma instância irmã de primeira ordem
			
			//Se o link é para uma classe da ontologia
			if (domainOnto.containsOClass(domainOnto.createOURI(linkResourceStr))) {
				OClass classe = domainOnto.getOClass(domainOnto.createOURI(linkResourceStr));
				
				//Se o domínio é uma classe
				if (domainOnto.containsOClass(domainOnto.createOURI(domainURI))) {
					OClass domClass = domainOnto.getOClass(domainOnto.createOURI(domainURI));
					if (classe.isSubClassOf(domClass, Closure.DIRECT_CLOSURE) ||
							classe.isSuperClassOf(domClass, Closure.TRANSITIVE_CLOSURE) ||
							linkResourceStr == domainURI)
						linksToDomain.add(link);
				}
				else if (domainOnto.containsOInstance(domainOnto.createOURI(domainURI))) {
					OInstance domInst = domainOnto.getOInstance(domainOnto.createOURI(domainURI));
					if (domInst.isInstanceOf(classe, Closure.TRANSITIVE_CLOSURE))
						linksToDomain.add(link);
				}
			}
			//Senão, trata como uma instância
			else if (domainOnto.containsOInstance(domainOnto.createOURI(linkResourceStr))){
				OInstance instancia = domainOnto.getOInstance(domainOnto.createOURI(linkResourceStr));
				
				if (domainOnto.containsOClass(domainOnto.createOURI(domainURI))) {
					OClass domClass = domainOnto.getOClass(domainOnto.createOURI(domainURI));
					if (instancia.isInstanceOf(domClass, Closure.TRANSITIVE_CLOSURE))
						linksToDomain.add(link);
				}
				else if (domainOnto.containsOInstance(domainOnto.createOURI(domainURI))) {
					OInstance domInst = domainOnto.getOInstance(domainOnto.createOURI(domainURI));
					Set<OClass> classesPai = instancia.getOClasses(Closure.DIRECT_CLOSURE);
					
					OClass classePai = (OClass)classesPai.toArray()[0];
					if (domInst.isInstanceOf(classePai, Closure.DIRECT_CLOSURE))
						linksToDomain.add(link);
				}
			}
		}
		
		//Calcula quantos URIs diferentes do domínio são marcados com os links e verifica em relação à todos
		// os recursos, retornando um valor percentual entre 0 e 1
		float relacaoRecurso = (float)linksToDomain.size() / (float)resourceLinks.size();
		
		/*
		 * Se um domínio em questão é uma classe, então todas as subclasses e instâncias inferiores serão
		 * consideradas para o cálculo. Se o domínio se refere à uma instância, então todas as instâncias irmãs
		 * serão consideradas.
		 */
		
		float domainCount = 0;
		
		if (domainOnto.containsOClass(domainOnto.createOURI(domainURI))) {
			OClass domClass = domainOnto.getOClass(domainOnto.createOURI(domainURI));
			
			Set<OClass> classes =  domainOnto.getOClasses(false);
			Set<OClass> classToRemove = new HashSet<>();
			
//			Parte da verificação dos labels
			for (OClass oClass : classes) {
				if (oClass.getLabels().size() == 0)
					classToRemove.add(oClass);
				
				else if (!oClass.isSubClassOf(domClass, Closure.TRANSITIVE_CLOSURE) && !oClass.equals(domClass))
					classToRemove.add(oClass);
			}
			classes.removeAll(classToRemove);
			
			Set<OInstance> instances = domainOnto.getOInstances(domClass, Closure.TRANSITIVE_CLOSURE);
			
			
//			Parte da verificação dos labels
			Set<OInstance> instToRemove = new HashSet<>();
			
			for (OInstance oInstance : instances) {
				if (oInstance.getLabels().size() == 0)
					instToRemove.add(oInstance);
			}
			instances.removeAll(instToRemove);
			
			domainCount = (float)classes.size() + (float)instances.size();
		}
		else if (domainOnto.containsOInstance(domainOnto.createOURI(domainURI))) {
			OInstance domInst = domainOnto.getOInstance(domainOnto.createOURI(domainURI));
			
			Set<OClass> classesPai = domInst.getOClasses(Closure.DIRECT_CLOSURE);

			OClass classePai = (OClass)classesPai.toArray()[0];
			
			ClosableIterator<OInstance> instIter = domainOnto.getOInstancesIterator(classePai, Closure.DIRECT_CLOSURE);
			
			Set<OInstance> instances = new HashSet<OInstance>();
			while (instIter.hasNext()) {
				
				OInstance theInstance = instIter.next();
				
				if (theInstance.getLabels().size() != 0)
					instances.add(theInstance);
			}
			
			domainCount = (float)instances.size();
		}
		
		//TODO é preciso verificar links que apontem para os recursos de maneira não repetida
		//Os links a serem considerados também deve ser no ramo do domínio
		Set<String> domainsForLinks = new HashSet<>();
		
		for (OInstance link : linksToDomain) {
			
			DatatypeProperty dtp = profileOnto.getDatatypeProperty(
					profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "URI"));
			
			//Se ligado diretamente ao domínio ou a um de seus filhos...
//			String linkResourceStr = link.getDatatypePropertyValues(dtp).get(0).toString();
			domainsForLinks.add(link.getDatatypePropertyValues(dtp).get(0).toString());
		}
		
		float relacaoDominio = (float)domainsForLinks.size() / domainCount;
		
		//Faz a média aritmétrica entre as duas partes e retorna este número
		
		return (relacaoDominio + relacaoRecurso) / 2.f;
	}

	@Override
	public float getUserInterest(String userID, String domainURI,
			boolean recursive,
			String profileOntoText, String domainOntoText) {
		
		//Limpa as ontologias para garantir que não hajam dados de chamadas anteriores
		profileOnto.cleanOntology();
		domainOnto.cleanOntology();

		//Carrega os novos dados nas ontologias
		StringReader profReader = new StringReader(profileOntoText);
		profileOnto.readOntologyData(profReader, null, OntologyFormat.RDFXML, false);
		
		Set<OClass> profileTopClasses = profileOnto.getOClasses(true);
		String profileNamespace = profileTopClasses.iterator().next().getONodeID().getNameSpace();
		profileOnto.setDefaultNameSpace(profileNamespace);

		
		StringReader domReader = new StringReader(domainOntoText);
		domainOnto.readOntologyData(domReader, null, OntologyFormat.RDFXML, false);
		
		Set<OClass> domainTopClasses = domainOnto.getOClasses(true);
		String domainamespace = domainTopClasses.iterator().next().getONodeID().getNameSpace();
		domainOnto.setDefaultNameSpace(domainamespace);
				
		/*
		 * A = Verifica quantos acessos exsitem para o usuário e relaciona com a quantidade de acessos
		 * Para o recurso específico.
		 * B = Relaciona o tempo total de uso do sistema com o tempo de uso do recurso específico
		 * C = Verifica a relação do recurso com o domínio
		 * R = (A + B)/2 * C
		 */
		
		//Identifica o usuário
		OInstance user = null;
		
		OClass theUserClass = profileOnto.getOClass(profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "User"));
		Set<OInstance> users = profileOnto.getOInstances(theUserClass, Closure.DIRECT_CLOSURE);

		for (OInstance userInst : users) {
			Set<Literal> labels = userInst.getLabels();
			for (Literal literal : labels) {
				if (literal.toString().equals(userID)) {
					user = profileOnto.getOInstance(userInst.getOURI());
					break;
				}
			}
		}
		
		if (user == null)
			return -1;
		
		ObjectProperty opAccess = profileOnto.getObjectProperty(profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "hasAccess"));
		
		//A lista completa de acessos do usuário
		List<OInstance> userAccessObjs = user.getObjectPropertyValues(opAccess);
		
		//A lista de acessos para o domínio em questão
		ObjectProperty opAccessToResource = profileOnto.getObjectProperty(profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "hasLinkedWith"));
		ObjectProperty opHasLinks = profileOnto.getObjectProperty(profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "hasLinks"));
		DatatypeProperty dpLinkToURI = profileOnto.getDatatypeProperty(profileOnto.createOURI(profileOnto.getDefaultNameSpace() + "URI"));
		
		List<OInstance> accessToDomain = new ArrayList<>();
		
		for (OInstance access : userAccessObjs) {
			
			List<OInstance> resourceFromAccess = access.getObjectPropertyValues(opAccessToResource);
			
			for (OInstance resource : resourceFromAccess) {
				
				//Para cada um dos resources levantamos os links do mesmo
				//Para cada link, verificamos se a URI do mesmo é a procurada
				//Talvez seja interessante colocar também a recursividade nesse ponto
				List<OInstance> links = resource.getObjectPropertyValues(opHasLinks);
				
				for (OInstance link : links) {

					///////////////////////////////////////////
					//Se o link é para uma classe da ontologia
					String linkResourceStr = link.getDatatypePropertyValues(dpLinkToURI).get(0).toString();
					
					if (domainOnto.containsOClass(domainOnto.createOURI(linkResourceStr))) {
						OClass classe = domainOnto.getOClass(domainOnto.createOURI(linkResourceStr));

						//Se o domínio é uma classe
						if (domainOnto.containsOClass(domainOnto.createOURI(domainURI))) {
							OClass domClass = domainOnto.getOClass(domainOnto.createOURI(domainURI));
							if (classe.isSubClassOf(domClass, Closure.DIRECT_CLOSURE) ||
									classe.isSuperClassOf(domClass, Closure.TRANSITIVE_CLOSURE) ||
									linkResourceStr == domainURI) {
								accessToDomain.add(access);
								break;
							}
//								linksToDomain.add(link);
						}
						else if (domainOnto.containsOInstance(domainOnto.createOURI(domainURI))) {
							OInstance domInst = domainOnto.getOInstance(domainOnto.createOURI(domainURI));
							if (domInst.isInstanceOf(classe, Closure.TRANSITIVE_CLOSURE)) {
								accessToDomain.add(access);
								break;
							}
//								linksToDomain.add(link);
						}
					}
					//Senão, trata como uma instância
					else if (domainOnto.containsOInstance(domainOnto.createOURI(linkResourceStr))){
						OInstance instancia = domainOnto.getOInstance(domainOnto.createOURI(linkResourceStr));

						if (domainOnto.containsOClass(domainOnto.createOURI(domainURI))) {
							OClass domClass = domainOnto.getOClass(domainOnto.createOURI(domainURI));
							if (instancia.isInstanceOf(domClass, Closure.TRANSITIVE_CLOSURE)) {
								accessToDomain.add(access);
								break;
							}
//								linksToDomain.add(link);
						}
						else if (domainOnto.containsOInstance(domainOnto.createOURI(domainURI))) {
							OInstance domInst = domainOnto.getOInstance(domainOnto.createOURI(domainURI));
							Set<OClass> classesPai = instancia.getOClasses(Closure.DIRECT_CLOSURE);

							OClass classePai = (OClass)classesPai.toArray()[0];
							if (domInst.isInstanceOf(classePai, Closure.DIRECT_CLOSURE)) {
								accessToDomain.add(access);
								break;
							}
//								linksToDomain.add(link);
						}
					}
					//////////////////////////////////
				}			
			}
		}
		
		//Calcula a relação entre o recurso e o domínio
		float userRelation;
		
		if (accessToDomain.size() == 0)
			userRelation = 0;
		else {
			float domRelation = 0;
			
			Set<OInstance> resourcesToDomain = new HashSet<>();
			
			for (OInstance access : accessToDomain) {
				resourcesToDomain.add(
						access.getObjectPropertyValues(
								profileOnto.getObjectProperty(
										profileOnto.createOURI(
												profileOnto.getDefaultNameSpace() + "hasLinkedWith"))).get(0));
			}
			
			for (OInstance resource : resourcesToDomain) {
				domRelation += getResourceDomainRelation(resource.getOURI().toString(), domainURI, recursive, profileOntoText, domainOntoText);
			}
			
			domRelation /= accessToDomain.size();
			userRelation = ((float)accessToDomain.size() / (float)userAccessObjs.size()) * domRelation;
		}
		return userRelation;
	}
	
	@Override
	public void finalize() {
		Factory.deleteResource(domainOnto);
		Factory.deleteResource(profileOnto);
	}
}
