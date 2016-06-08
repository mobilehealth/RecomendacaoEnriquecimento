package mobilehealth.analyzer.test;

import mobilehealth.prc.analyzer.MetadataMap;
import mobilehealth.prc.analyzer.IAnalyzer;
import mobilehealth.prc.analyzer.Analyzer;

public class SemAugTest {

	public static void main(String[] args) {
		
		//Cria o objeto de enriquecimento
		IAnalyzer semantic = new Analyzer();
		
		//Inicializa com o diretório onde o arquivo de configuração e as ontologias estão
		//O arquivo de configuração é criado caso não exista
  		semantic.init("//Users/DaLua/SVN/ubi_server_analyzer/Ontologias");
		
  		//Cria um usuário de maneira explícita
		MetadataMap userData = semantic.newMetadataMap();
		userData.put("UserID", "123");
		userData.put("birthDate", java.util.Calendar.getInstance().getTime().toString());
		userData.put("firstName", "Argemiro");
		userData.put("lastName", "Neto");
		userData.put("genre", "M");
		userData.put("phone", "8863-4710");
		userData.put("rating", "1");
		userData.put("signInData", java.util.Calendar.getInstance().getTime().toString());
		semantic.addUser(userData);
		
		//Solicita um enriquecimento para este usuário
		String url1 = "file:///Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/Material de Testes/estrutura de dados/lect5.html";
		MetadataMap metadata1 = semantic.newMetadataMap();
		metadata1.put("UserID", "123");
		metadata1.put("URL", url1);
		metadata1.put("sourceURL", url1);
		metadata1.put("typeResource", "file");
		
		semantic.processURLWithMetadata(url1, metadata1);
		
		//Solicita o enriquecimento para outro tipo de recurso
		String url2 = "file:///Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/Material de Testes/material diabetes/LearnAboutDiabetes.aspx.htm";
		MetadataMap metadata2 = semantic.newMetadataMap();
		metadata2.put("UserID", "123");
		metadata2.put("URL", url2);
		metadata2.put("sourceURL", url2);
		metadata2.put("typeResource", "file");
		
		semantic.processURLWithMetadata(url2, metadata2);
		
		//Solicita o mesmo enriquecimento para usuário que não existe e será criado de maneira implícita
		String url3 = "file:///Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/Material de Testes/material diabetes/Diabetes_mellitus.htm";
		MetadataMap metadata3 = semantic.newMetadataMap();
		metadata3.put("UserID", "456");
		metadata3.put("URL", url3);
		metadata3.put("sourceURL", url3);
		metadata3.put("typeResource", "file");
		
		semantic.processURLWithMetadata(url3, metadata3);
		
		
		//Faz um segundo acesso do usuário 123 ao primeiro tipo de conteúdo
		String url4 = "file:///Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/Material de Testes/estrutura de dados/lect3.html";
		MetadataMap metadata4 = semantic.newMetadataMap();
		metadata4.put("UserID", "123");
		metadata4.put("URL", url4);
		metadata4.put("sourceURL", url4);
		metadata4.put("typeResource", "file");
		
		semantic.processURLWithMetadata(url4, metadata4);
		
		
		//Solicita a relação entre o usuário 123 e o conteúdo "voting-example.xml"
		float user123Relatiion1 = semantic.getUserInterest("123", "http://gate.ac.uk/example#Leader", false);
		float user123Relatiion2 = semantic.getUserInterest("123", "http://localhost/health20/Onto20#HumanBody", false);	
		
		/*
		 * Para saber qual a URI da ontologia que será pesquisada você pode chamar as seguintes funções:
		 * 1. semantic.getDomainsOntologies() : o metadata de retorno contém informações sobre as ontologias carregadas.
		 *    Uma dessas informações é a URI da mesma. Utilizando a função...
		 * 2. semantic.getDomainOntoForURL(aOntologyURI) : obterá uma string contendo o corpo da ontologia em formato RDF/XML.
		 *    Você poderá utilizá-lo para criar um objeto de ontologia utilizando qualquer biblioteca, como o JENA, GATE, etc.
		 *    Pode utilizar também o objeto "Ontology" interno do ubi.analytic.SemanticIndexer. Exemplo:
		 *    
		 *    2.1 Ontology onto = new OntologyImplGate();
		 *        onto.readOntologyData(corpo_da_ontologia);
		 *        onto.getOClasses(false) - para obter a lista de classes;
		 *        onto.getOInstances() - para obter a lista de instâncias;
		 *    
		 *    O formato das strings da lista de classes e listas de intâncias será em URI, logo, estará pronto para ser
		 *    chamado nas funções abaixo.
		 */
		
		float resourceRelation1 = semantic.getResourceDomainRelation(url3, "http://localhost/health20/Onto20#Disease", true);
		float resourceRelation2 = semantic.getResourceDomainRelation(url1, "http://www.semanticweb.org/jonathan/ontologies/2013/11/dataStructure#DataStructure", true);
		
		System.out.println(user123Relatiion1 + " e " + user123Relatiion2);
		System.out.println(resourceRelation1 + " e " + resourceRelation2);
		
		semantic.finalize();
	}
}
