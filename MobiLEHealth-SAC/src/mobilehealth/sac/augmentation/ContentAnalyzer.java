package mobilehealth.sac.augmentation;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import mobilehealth.sac.domain.DomainData;
import mobilehealth.sac.ontology.Link;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.semanticrepository.SemanticRepository;
import mobilehealth.sac.util.Util;
import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.LanguageAnalyser;
import gate.ProcessingResource;
import gate.creole.ANNIEConstants;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.Ontology;
import gate.util.InvalidOffsetException;
import gate.util.persistence.PersistenceManager;

/**
 * 
 * @author Jonathan
 * @date 15/09/2014
 */
public class ContentAnalyzer {

	private SerialAnalyserController controller;
	private ProcessingResource docReset;
	private ProcessingResource tokeniser;
	private ProcessingResource spliter;
	private ProcessingResource posTagger;
	private ProcessingResource morpher;
	private LanguageAnalyser ontoRootGaz;
	private ProcessingResource flexGaz;
	private Ontology domainOntology;
	private Corpus corpus;

	/**
	 * Default Constructor
	 */
	public ContentAnalyzer() {

	}

	/**
	 * Initialize GATE and load resources to execute content analyzer.
	 */
	private void loadGate() {

		try {

			/* Checks if gate is initialized */
			GateUtil.initialize();

			/* Load ANNIE */
			controller = (SerialAnalyserController) PersistenceManager.loadObjectFromFile(new File(new File(Gate.getPluginsHome(), ANNIEConstants.PLUGIN_DIR), ANNIEConstants.DEFAULT_FILE));

			/* Create the processing resources */
			docReset = (ProcessingResource) Factory.createResource("gate.creole.annotdelete.AnnotationDeletePR");
			tokeniser = (ProcessingResource) Factory.createResource("gate.creole.tokeniser.DefaultTokeniser");
			spliter = (ProcessingResource) Factory.createResource("gate.creole.splitter.SentenceSplitter");
			posTagger = (ProcessingResource) Factory.createResource("gate.creole.POSTagger");
			morpher = (ProcessingResource) Factory.createResource("gate.creole.morph.Morph");

			/*
			 * Register the plugin to load domain ontologies. The domainOntology will be reloaded
			 * for each domain ontology.
			 */
			domainOntology = (Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", Factory.newFeatureMap());

			/* create the OntoRootGazetteer */
			FeatureMap orgParams = Factory.newFeatureMap();
			orgParams.put("morpher", morpher);
			orgParams.put("posTagger", posTagger);
			orgParams.put("tokeniser", tokeniser);
			orgParams.put("ontology", domainOntology);
			ontoRootGaz = (LanguageAnalyser) Factory.createResource("gate.clone.ql.OntoRootGaz", orgParams);

			/* Create the FlexibleGazetteer */
			List<String> names = new ArrayList<String>();
			names.add("Token.root");
			FeatureMap flexGParams = Factory.newFeatureMap();
			flexGParams.put("gazetteerInst", ontoRootGaz);
			flexGParams.put("inputFeatureNames", names);
			flexGaz = (ProcessingResource) Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", flexGParams);

			/* Add the Processing Resources to the controller */
			controller.add(docReset);
			controller.add(tokeniser);
			controller.add(spliter);
			controller.add(posTagger);
			controller.add(morpher);
			controller.add(flexGaz);

			/* Add the corpus documents for augmentation */
			corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
			controller.setCorpus(corpus);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method do natural language processing for a specific content doing the crossing with
	 * domain ontologies. The result of this process is a set of annotations that do references of
	 * the text with domain concepts.
	 * 
	 * @param content
	 * @return annotations with relations between content and domain ontologies.
	 * @throws IOException
	 * @throws OWLOntologyCreationException
	 */
	public List<Link> analyze(String content, String language) {

		List<Link> annotations = new ArrayList<Link>();

		if (language.equals(Resource.LANGUAGE_EN)) {
			annotations = englishAnalyze(content);
		} else {
			annotations = defaultAnalyze(content, language);
		}

		return annotations;
	}

	private List<Link> defaultAnalyze(String content, String language) {

		SemanticRepository semanticRepository = new SemanticRepository();

		/* Gets Labels of all individuals (concepts) of the all domain ontologies */
		List<DomainData> labels = semanticRepository.getLabels(language);

		List<DomainData> relations = new ArrayList<DomainData>();

		for (DomainData domainData : labels) {
			List<DomainData> relationLabel = processContent(content, domainData);
			if (!relationLabel.isEmpty()) {
				relations.addAll(relationLabel);
			}
		}

		List<Link> links = new ArrayList<Link>();

		// Algritmo para criar os links agrupado pela uri...
		// Criar c�pia da lista?

		while (!relations.isEmpty()) {
			DomainData domainData = relations.get(0); // Pegando primeiro elemento da lista
			relations.remove(0); // Removendo elemento da lista
			int i = 0;
			int count = 1; // Contador de uris
			for (; i < relations.size(); i++) {
				DomainData temp = relations.get(i);
				if (temp.iri.equalsIgnoreCase(domainData.iri)) {
					count++; // Contando uris encontradas
					relations.remove(i); // Removendo elemento da lista para n�o ser contado
											// novamente
					i = -1; // Voltando ao in�cio da lista (i++ = 0)
				}
			}

			Link link = new Link();
			link.setDomainURI(domainData.iri); // Iri em quest�o
			link.setQuantity(count); // Quantas vezes foi contada
			link.setRoot(domainData.label); // Palavra relacionada

			link.setDomain(semanticRepository.getDomainByCompleteURI(domainData.iri));

			links.add(link);
		}

		return links;
	}

	// TODO: Salatiel
	private List<DomainData> processContent(String content, DomainData domainData) {
		ArrayList<DomainData> relation = new ArrayList<DomainData>();

		// Quebrar o texto do tamanho da seten�a da label
		ArrayList<String> brkTxt = Util.brokenText(content, domainData.qtdPalavras);
		// Para cada elemento do texto quebrado, fazer compara��o
		for (int j = 0; j < brkTxt.size(); j++) {
			// Se trecho do texto for igual � label em quest�o
			if (brkTxt.get(j).equalsIgnoreCase(domainData.label)) {
				// Adiciona Label e URI
				DomainData d = new DomainData(brkTxt.get(j), domainData.iri);
				relation.add(d);
			}
		}

		return relation;
	}

	/**
	 * This method do natural language processing for a specific content doing the crossing with
	 * domain ontologies. The result of this process is a set of annotations that do references of
	 * the text with domain concepts.
	 * 
	 * @param content
	 * @return annotations with relations between content and domain ontologies.
	 */
	public List<Link> englishAnalyze(String content) {

		loadGate();

		List<MetadataMap> annotations = new ArrayList<MetadataMap>();

		/* create a GATE document */
		String contentType = Util.isUrl(content) ? "sourceUrl" : "stringContent";

		FeatureMap params = Factory.newFeatureMap();
		params.put(contentType, content);
		params.put("preserveOriginalContent", new Boolean(true));
		params.put("collectRepositioningInfo", new Boolean(true));
		Document doc = null;

		try {
			/* Create a document and add to corpus */
			doc = (Document) Factory.createResource("gate.corpora.DocumentImpl", params);
			corpus.add(doc);

			/* execute the corpus for all domain ontologies registered in the application */
			List<String> domainOntologies;

			domainOntologies = SemanticRepository.getDomainOntologies();

			for (String ontologyText : domainOntologies) {

				/* Clean the default ontology of corpus to load another ontology */
				domainOntology.cleanOntology();

				/* Load a new ontology in the corpus */
				domainOntology.readOntologyData(new StringReader(ontologyText), null, OConstants.OntologyFormat.RDFXML, false);

				/* reloaded OntoRootGazzeter */
				ontoRootGaz.reInit();

				/* Execute corpus */
				annotations.addAll(this.executeCorpus());

			}

		} catch (ResourceInstantiationException | IOException e) {
			e.printStackTrace();
		} finally {
			corpus.clear();
			Factory.deleteResource(doc);
		}

		List<Link> links = new ArrayList<Link>();

		for (MetadataMap annotation : annotations) {
			if (annotation.get("type").equals("instance")) {

				String uri = (String) annotation.get("URI");

				Link link = null;

				// localizar URI nos links
				for (Link linkAux : links) {
					if (linkAux.getDomainURI().equals(uri)) {
						link = linkAux;
					}
				}

				if (link == null) {
					link = new Link();
					link.setDomainURI(uri);
					link.setQuantity(1);
					link.setRoot((String) annotation.get("root"));

					SemanticRepository semanticRepository = new SemanticRepository();
					link.setDomain(semanticRepository.getDomainByCompleteURI(uri));

					links.add(link);

				} else {
					link.setQuantity(link.getQuantity() + 1);
				}

			}
		}

		return links;
	}

	/**
	 * Execute corpus. The corpus is responsible for executing the NLP together with the domain
	 * ontology. Returning annotations regarding that relates content and concepts ontology.
	 * 
	 * @return annotations corpus
	 */
	private List<MetadataMap> executeCorpus() {

		List<MetadataMap> annotations = new ArrayList<MetadataMap>();

		try {
			controller.execute();

			AnnotationSet corpusAnnotations = controller.getCorpus().get(0).getAnnotations();

			Set<Annotation> lookupAnnotations = new HashSet<Annotation>(corpusAnnotations.get("Lookup"));

			Set<Annotation> tokenAnnotations = new HashSet<Annotation>(corpusAnnotations.get("Token"));

			Set<Annotation> sentenceAnnotations = new HashSet<Annotation>(corpusAnnotations.get("Sentence"));

			// Search the correspondent token and sentence for each lookup annotation
			for (Annotation annotation : lookupAnnotations) {

				Annotation token = null, sentence = null;

				// get the correspondent token
				for (Annotation tokenTemp : tokenAnnotations) {
					if (tokenTemp.getStartNode().getOffset() == annotation.getStartNode().getOffset()) {
						token = tokenTemp;
						break;
					}
				}

				// get the correspondent sentence
				for (Annotation sentenceTemp : sentenceAnnotations) {
					if (sentenceTemp.getStartNode().getOffset() <= annotation.getStartNode().getOffset() && sentenceTemp.getEndNode().getOffset() >= annotation.getEndNode().getOffset()) {
						sentence = sentenceTemp;

						FeatureMap sentenceString = Factory.newFeatureMap();
						sentenceString.put("sentence", corpus.get(0).getContent().getContent(sentence.getStartNode().getOffset(), sentence.getEndNode().getOffset()));
						sentence.setFeatures(sentenceString);

						break;
					}
				}

				if (sentence != null) {
					// Create the annotation conform Semantic Indexer Structure
					MetadataMap tempMMap = new MetadataMap();
					tempMMap.put("URI", (annotation.getFeatures().get("URI") == null) ? "" : annotation.getFeatures().get("URI"));
					tempMMap.put("propertyURI", (annotation.getFeatures().get("propertyURI") == null) ? "" : annotation.getFeatures().get("propertyURI"));
					tempMMap.put("classURI", (annotation.getFeatures().get("classURI") == null) ? "" : annotation.getFeatures().get("classURI"));
					tempMMap.put("type", (annotation.getFeatures().get("type") == null) ? "" : annotation.getFeatures().get("type"));
					tempMMap.put("offset", (annotation.getStartNode().getOffset() == null) ? "" : annotation.getStartNode().getOffset());
					tempMMap.put("root", (token.getFeatures().get("root") == null) ? "" : token.getFeatures().get("root"));
					tempMMap.put("category", (token.getFeatures().get("category") == null) ? "" : token.getFeatures().get("category"));
					tempMMap.put("kind", (token.getFeatures().get("kind") == null) ? "" : token.getFeatures().get("kind"));
					tempMMap.put("length", (token.getFeatures().get("length") == null) ? "" : token.getFeatures().get("length"));
					tempMMap.put("string", (token.getFeatures().get("string") == null) ? "" : token.getFeatures().get("string"));
					tempMMap.put("sentence", (sentence.getFeatures().get("sentence") == null) ? "" : sentence.getFeatures().get("sentence"));

					annotations.add(tempMMap);
				}
			} // for
		} catch (ExecutionException | InvalidOffsetException e) {
			e.printStackTrace();
		}

		return annotations;
	}

	/**
	 * Finalize Gate
	 */
	public void finalize() {

		if (docReset != null)
			Factory.deleteResource(docReset);

		if (tokeniser != null)
			Factory.deleteResource(tokeniser);

		if (spliter != null)
			Factory.deleteResource(spliter);

		if (posTagger != null)
			Factory.deleteResource(posTagger);

		if (morpher != null)
			Factory.deleteResource(morpher);

		if (ontoRootGaz != null)
			Factory.deleteResource(ontoRootGaz);

		if (flexGaz != null)
			Factory.deleteResource(flexGaz);

		if (domainOntology != null)
			Factory.deleteResource(domainOntology);

		if (controller != null)
			controller.cleanup();

	}

	public List<Link> analyze(String classLabel, Map<String, String> measurements) {

		// Links que ser�o retornados
		ArrayList<Link> links = new ArrayList<Link>();
		// Lista de ontologias
		// List<File> files = getDomainOntologiesFile();
		List<File> files = new ArrayList<File>();
		File fil = new File("app/ontology/Blood Glucose.owl");
		files.add(fil);
		for (File file : files) {
			try {
				OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
				OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
				OWLDataFactory factory = manager.getOWLDataFactory();
				ArrayList<DomainData> inference = new ArrayList<DomainData>();
				// Buscando cada classe
				for (OWLClass cls : ontology.getClassesInSignature()) {
					// Buscando cada indiv�duo
					for (OWLIndividual ind : cls.getIndividuals(ontology)) {
						// Buscando cada anota��o do indiv�duo (labels)
						for (OWLAnnotation annot : ((OWLEntity) ind).getAnnotations(ontology, factory.getRDFSLabel())) {

							// Transformando a anota��o
							OWLLiteral val = (OWLLiteral) annot.getValue();

							// Se label for igual ao recebido no m�todo
							if (val.getLiteral().equalsIgnoreCase(classLabel)) {
								// findDataProperty(ontology, factory, ind, bld);
								// Para cada objectProperty,
								for (OWLObjectPropertyAssertionAxiom objProp : ontology.getObjectPropertyAssertionAxioms(ind)) {
									// A quem se relaciona
									OWLNamedIndividual relInd = (OWLNamedIndividual) objProp.getObject();
									// Para cada Data property do individuo
									for (OWLDataPropertyAssertionAxiom data : ontology.getDataPropertyAssertionAxioms(relInd)) {

										// Obter o valor referente a propriedade
										// Cast para DataProperty
										OWLDataProperty temp = data.getProperty().asOWLDataProperty();
										// System.out.println(temp.getIRI().getFragment());

										// Se no hash map, existe campo #string
										if (measurements.containsKey(temp.getIRI().getFragment())) {
											// Obtendo valor do Map
											String mapValue = measurements.get(temp.getIRI().getFragment());

											float mapX = Float.parseFloat(mapValue);
											OWLLiteral s = data.getObject();
											String value = s.getLiteral();
											// Separando valores
											String[] values = value.split(" ");
											float x1 = Float.parseFloat(values[0]);
											float x2 = Float.parseFloat(values[1]);

											// Caso valor esteja no intervalo
											if (mapX >= x1 && mapX <= x2) {
												// Salvando essa informa��o sobre a IRI do indiv�duo
												// e a Label
												OWLNamedIndividual id = ind.asOWLNamedIndividual();
												DomainData d = new DomainData(classLabel, id.getIRI().toString());
												// Adicionando primeira inferencia encontrada
												inference.add(d);
											}
										}
									}
								}
							}
						}
					}
				}
				// Contagem de URIs que apareceram
				while (!inference.isEmpty()) {
					DomainData domainData = inference.get(0); // Pegando primeiro elemento da lista
					inference.remove(0); // Removendo elemento da lista
					int i = 0;
					int count = 1; // Contador de uris
					for (; i < inference.size(); i++) {
						DomainData temp = inference.get(i);
						if (temp.iri.equalsIgnoreCase(domainData.iri)) {
							count++; // Contando uris encontradas
							inference.remove(i); // Removendo elemento da lista para n�o ser contado
													// novamente
							i = -1; // Voltando ao in�cio da lista (i++ = 0)
						}
					}

					Link link = new Link();
					// link.setDomainURI(domainData.iri); // Iri em quest�o
					link.setUri(domainData.iri);
					link.setQuantity(count); // Quantas vezes foi contada
					link.setRoot(domainData.label); // Palavra relacionada

					// link.setDomain(semanticRepository.getDomainByCompleteURI(domainData.iri));

					links.add(link);
				}
			} catch (Exception e) {

			}

		}
		return links;
	}

}
