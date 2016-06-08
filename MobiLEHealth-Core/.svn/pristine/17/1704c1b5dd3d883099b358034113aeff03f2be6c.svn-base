package mobilehealth.analyzer.test;

import gate.util.GateException;

import java.io.IOException;

public class Testes {

	public static void main(String[] args) throws GateException, IOException, ClassNotFoundException {
//		// TODO Auto-generated method stub
//
//		//TODO ISemanticAugmentation Inicialização do módulo semantico
//		//TODO Criar uma operação de getIndexerInterface para ser usado pelo carregador
//		
//		//Initialize the GATE
//		Gate.init();
//		
//		//Load ANNIE
//		SerialAnalyserController controller = (SerialAnalyserController) 
//				PersistenceManager.loadObjectFromFile(new File(new File(
//						Gate.getPluginsHome(), ANNIEConstants.PLUGIN_DIR),
//						ANNIEConstants.DEFAULT_FILE));
//		
//		//Load the required plugins
//		Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Tools").toURI().toURL());
//		Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Gazetteer_Ontology_Based").toURI().toURL());
//		Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Ontology").toURI().toURL());
//		
//		
//		//Create the resources
//		ProcessingResource docReset = (ProcessingResource) Factory.createResource("gate.creole.annotdelete.AnnotationDeletePR");
//		ProcessingResource tokeniser = (ProcessingResource) Factory.createResource("gate.creole.tokeniser.DefaultTokeniser");
//		ProcessingResource spliter = (ProcessingResource) Factory.createResource("gate.creole.splitter.SentenceSplitter");
//		ProcessingResource posTagger = (ProcessingResource) Factory.createResource("gate.creole.POSTagger");
//		ProcessingResource morpher = (ProcessingResource) Factory.createResource("gate.creole.morph.Morph");
//		
//		
//		//Create the ontology from a file
//		URL ontoURL = new URL("file:///Users/Neto/Documents/workspace/GATE_Test/test-ontology-instances.owl");
//		FeatureMap ontoParams = Factory.newFeatureMap();
//		ontoParams.put("rdfXmlURL", ontoURL);
//		ontoParams.put("persistent", false);
//		Ontology onto = (Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", ontoParams);
//		
//
//		try {
//			OInstance inst = onto.getOInstance(onto.createOURI("http://gate.ac.uk/example#David_Cameron"));
//			inst.setLabel("Argemiro Neto - Test", OConstants.PORTUGUESE);
//			AnnotationProperty ann = onto.getAnnotationProperty(onto.createOURI("http://www.w3.org/2000/01/rdf-schema#label"));
//			inst.removeAnnotationPropertyValues(ann);
//			java.io.PrintWriter printOnto = new java.io.PrintWriter(new File("//Users/Neto/Documents/workspace/GATE_Test/test-ontology-instances-Arg.owl"));
//			onto.writeOntologyData(printOnto, OntologyFormat.RDFXML, false);
//			printOnto.flush();
//			printOnto.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//		//create the OntoRootGazetteer
//		FeatureMap orgParams = Factory.newFeatureMap();
//		orgParams.put("morpher", morpher);
//		orgParams.put("posTagger", posTagger);
//		orgParams.put("tokeniser", tokeniser);
//		orgParams.put("ontology", onto);
//		LanguageAnalyser orgAnalyser = (LanguageAnalyser)Factory.createResource("gate.clone.ql.OntoRootGaz", orgParams);
//		
//		//Create the FlexibleGazetteer
//		FeatureMap flexGParams = Factory.newFeatureMap();
//		flexGParams.put("gazetteerInst", orgAnalyser);
//		
//		java.util.ArrayList<String> names = new java.util.ArrayList<String>();
//		names.add("Token.root");		
//		flexGParams.put("inputFeatureNames", names);
//		ProcessingResource flexGaz = (ProcessingResource) Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", flexGParams); 
//		
//		//Add the Processing Resources to the controller
//		controller.add(docReset);
//		controller.add(tokeniser);
//		controller.add(spliter);
//		controller.add(posTagger);
//		controller.add(morpher);
//		controller.add(flexGaz);
//		
//		//Create the document and corpus for test
//		URL u = new URL("file:///Users/Neto/Documents/workspace/GATE_Test/voting-example.xml");
//	    
//	    FeatureMap params = Factory.newFeatureMap();
//	    params.put("sourceUrl", u);
//	    params.put("preserveOriginalContent", new Boolean(true));
//	    params.put("collectRepositioningInfo", new Boolean(true));
//	    Document doc = (Document)Factory.createResource("gate.corpora.DocumentImpl", params);
//	    Corpus corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
//	    corpus.add(doc);
//	   
//	    // tell the pipeline about the corpus and run it
//	    controller.setCorpus(corpus);
//	    controller.execute();
//	    
//		//Verifying the results, if there any
//	    AnnotationSet defaultAnnotSet = doc.getAnnotations();    
//
//	    
//		Set<Annotation> lookupAnnot = 
//				new HashSet<Annotation>(defaultAnnotSet.get("Lookup"));
//		
//		Set<Annotation> tokenAnnot = 
//				new HashSet<Annotation>(defaultAnnotSet.get("Token"));
//		
//		Set<Annotation> sentenceAnnot = 
//				new HashSet<Annotation>(defaultAnnotSet.get("Sentence"));
//		
//		List<MetadataMap> annotations = new ArrayList<MetadataMap>();
//		
//		//Search the correspondent token and sentence for each lookup annotation 
//		for (Annotation annotation : lookupAnnot) {
//			
//			Annotation token = null, sentence = null;
//			
//			//get the correspondent token
//			for (Annotation tokenTemp : tokenAnnot) {
//				if (tokenTemp.getStartNode().getOffset() == annotation.getStartNode().getOffset()) {
//					token = tokenTemp;
//					break;
//				}	
//			}
//			
//			//get the correspondent sentence
//			for (Annotation sentenceTemp : sentenceAnnot) {
//				if (sentenceTemp.getStartNode().getOffset() <= annotation.getStartNode().getOffset() &&
//						sentenceTemp.getEndNode().getOffset() >= annotation.getEndNode().getOffset()) {
//					sentence = sentenceTemp;
//					
//					FeatureMap sentenceString = Factory.newFeatureMap();
//					sentenceString.put("sentence", doc.getContent().getContent(sentence.getStartNode().getOffset(), sentence.getEndNode().getOffset()));
//					sentence.setFeatures(sentenceString);
//					break;
//				}	
//			}
//			
//			//Create the annotation conform Semantic Indexer Structure
//			MetadataMap tempMMap = new MetadataMap();
//			tempMMap.put("URI", annotation.getFeatures().get("URI"));
//			tempMMap.put("propertyURI", annotation.getFeatures().get("propertyURI"));
//			tempMMap.put("classURI", annotation.getFeatures().get("classURI"));
//			tempMMap.put("type", annotation.getFeatures().get("type"));
//			tempMMap.put("Offset", annotation.getStartNode().getOffset());
//			tempMMap.put("root", token.getFeatures().get("root"));
//			tempMMap.put("category", token.getFeatures().get("category"));
//			tempMMap.put("kind", token.getFeatures().get("kind"));
//			tempMMap.put("length", token.getFeatures().get("length"));
//			tempMMap.put("string", token.getFeatures().get("string"));
//			tempMMap.put("sentence", sentence.getFeatures().get("sentence"));
//			
//			annotations.add(tempMMap);			
//		}
//	    
//		onto.toString();
//		
//		for (MetadataMap metadataMap : annotations) {
//			System.out.println(metadataMap);
//		}
//		
//		//Ending resources - free the pointers to the GC
//		controller.cleanup();
//		Factory.deleteResource(docReset);
//		Factory.deleteResource(tokeniser);
//		Factory.deleteResource(spliter);
//		Factory.deleteResource(posTagger);
//		Factory.deleteResource(morpher);
//		Factory.deleteResource(onto);
//		Factory.deleteResource(orgAnalyser);
//		Factory.deleteResource(flexGaz);
//		
//		//Arquivo de configuração do indexer
//		MetadataMap map = new MetadataMap();
//		
//		map.put("profile", "//Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/SESProfile.owl");
//		map.put("http://gate.ac.uk/example#", "//Users/Neto/Documents/workspace/GATE_Test/test-ontology-instances.owl");
//		
//		java.io.PrintWriter print = new java.io.PrintWriter(new File("//Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/test.cfg"));
//		print.print(map);
//		print.flush();
//		print.close();
//		
//		FileOutputStream fos = new FileOutputStream("//Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/indexer.cfg");
//		ObjectOutputStream oos = new ObjectOutputStream(fos);;
//		oos.writeObject(map);
//
//		oos.close();
//		fos.close();
//		
//		FileInputStream fis = new FileInputStream("//Users/Neto/Documents/workspace/MobiLE/layer_rules/mod_analytic/src/ubi/analytic/indexer.cfg");
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		MetadataMap test = (MetadataMap) ois.readObject();
//		
//		System.out.println(test);
	}
}
