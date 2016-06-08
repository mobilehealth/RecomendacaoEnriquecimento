package mobilehealth.prc.analyzer.semantic.augmentation;

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
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.Ontology;
import gate.util.InvalidOffsetException;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mobilehealth.prc.analyzer.MetadataMap;
import mobilehealth.prc.analyzer.semantic.indexer.SemanticIndexer;

public class SemanticAugmentationImplGate implements SemanticAugmentation {


	private SerialAnalyserController controller;
	private ProcessingResource docReset;
	private ProcessingResource tokeniser;
	private ProcessingResource spliter;
	private ProcessingResource posTagger;
	private ProcessingResource morpher;
	private LanguageAnalyser ontoRootGaz;
	private ProcessingResource flexGaz;
	private Ontology domainOnto;
	private Corpus corpus;

	private SemanticIndexer indexer;


	public SemanticAugmentationImplGate(SemanticIndexer indexer) {

		try {

			if (!Gate.isInitialised()) {
				//Initialize the GATE
				Gate.init();

				//Load the required plugins
				Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Tools").toURI().toURL());
				Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Gazetteer_Ontology_Based").toURI().toURL());
				Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Ontology").toURI().toURL());
			}			

			//Register the pointer for indexer
			this.indexer = indexer;

			//Load ANNIE
			controller = (SerialAnalyserController) 
					PersistenceManager.loadObjectFromFile(new File(new File(
							Gate.getPluginsHome(), ANNIEConstants.PLUGIN_DIR),
							ANNIEConstants.DEFAULT_FILE));

			//Create the resources
			docReset = (ProcessingResource) Factory.createResource("gate.creole.annotdelete.AnnotationDeletePR");
			tokeniser = (ProcessingResource) Factory.createResource("gate.creole.tokeniser.DefaultTokeniser");
			spliter = (ProcessingResource) Factory.createResource("gate.creole.splitter.SentenceSplitter");
			posTagger = (ProcessingResource) Factory.createResource("gate.creole.POSTagger");
			morpher = (ProcessingResource) Factory.createResource("gate.creole.morph.Morph");

			//Register the default and profile ontologies
			//domainOnto será carregada para cada ontologia carregada pelo indexador
			domainOnto = (Ontology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", Factory.newFeatureMap());

			//create the OntoRootGazetteer
			FeatureMap orgParams = Factory.newFeatureMap();
			orgParams.put("morpher", morpher);
			orgParams.put("posTagger", posTagger);
			orgParams.put("tokeniser", tokeniser);
			orgParams.put("ontology", domainOnto);
			ontoRootGaz = (LanguageAnalyser)Factory.createResource("gate.clone.ql.OntoRootGaz", orgParams);

			//Create the FlexibleGazetteer
			FeatureMap flexGParams = Factory.newFeatureMap();
			flexGParams.put("gazetteerInst", ontoRootGaz);

			java.util.ArrayList<String> names = new java.util.ArrayList<String>();
			names.add("Token.root");		
			flexGParams.put("inputFeatureNames", names);
			flexGaz = (ProcessingResource) Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", flexGParams); 

			//Add the Processing Resources to the controller
			controller.add(docReset);
			controller.add(tokeniser);
			controller.add(spliter);
			controller.add(posTagger);
			controller.add(morpher);
			controller.add(flexGaz);

			//Add the corpus documents for augmentation
			corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
			controller.setCorpus(corpus);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	@Override
	/* (non-Javadoc)
	 * @see ubi.analytic.SemanticAugmentation.SemanticAugmentation#finalize()
	 */
	@Override
	public void finalize() {

		Factory.deleteResource(docReset);
		Factory.deleteResource(tokeniser);
		Factory.deleteResource(spliter);
		Factory.deleteResource(posTagger);
		Factory.deleteResource(morpher);
		Factory.deleteResource(ontoRootGaz);
		Factory.deleteResource(flexGaz);
		Factory.deleteResource(domainOnto);
		controller.cleanup();
	}

	//	@Override
	/* (non-Javadoc)
	 * @see ubi.analytic.SemanticAugmentation.SemanticAugmentation#processURLWithMetadata(java.net.URL, ubi.analytic.MetadataMap)
	 */
	@Override
	public void processURLWithMetadata(URL url, MetadataMap metadata) {

		FeatureMap params = Factory.newFeatureMap();
		params.put("sourceUrl", url);
		params.put("preserveOriginalContent", new Boolean(true));
		params.put("collectRepositioningInfo", new Boolean(true));
		Document doc = null;

		try {
			doc = (Document)Factory.createResource("gate.corpora.DocumentImpl", params);
			corpus.add(doc);
			List<MetadataMap> annotations = new ArrayList<MetadataMap>();
			
			Map<Object, Object> dOntos = new HashMap<Object, Object>();
			dOntos.putAll(indexer.getDomainsOntologies());
			
			//Inserido para testar o acesso
			annotations.clear();
			
			for (Object ontoURI : dOntos.keySet()) {
				domainOnto.cleanOntology();
				
				String ontoText = (String) dOntos.get(ontoURI);
				StringReader ontoTextStream = new StringReader(ontoText.toString());
				domainOnto.readOntologyData(ontoTextStream, null, OConstants.OntologyFormat.RDFXML, false);
				
				Set<OClass> profileTopClasses = domainOnto.getOClasses(true);
				String profileNamespace = profileTopClasses.iterator().next().getONodeID().getNameSpace();
				domainOnto.setDefaultNameSpace(profileNamespace);
				
				ontoRootGaz.reInit();
				
//				annotations.clear();
				annotations.addAll(this.executeCorpus());
	
//				indexer.doProfileUpdate(annotations, metadata, ontoURI.toString());
			}				
			
			indexer.doProfileUpdate(annotations, metadata, "");
			
			// TODO Ver uma forma boa de testar ou até se é necessário
//			boolean result = true;
//
//			if (result) {
//				reloadOntologies();
//			}

		} catch (ResourceInstantiationException e) {
			e.printStackTrace();
		} finally {
			corpus.clear();
			Factory.deleteResource(doc);
		}
	}

	/* (non-Javadoc)
	 * @see ubi.analytic.SemanticAugmentation.SemanticAugmentation#processDataWithMetadata(java.lang.String, ubi.analytic.MetadataMap)
	 */
	@Override
	public void processDataWithMetadata(String content, MetadataMap metadata) {

		 FeatureMap params = Factory.newFeatureMap();
		 params.put("stringContent", content);
		 params.put("preserveOriginalContent", new Boolean(true));
		 params.put("collectRepositioningInfo", new Boolean(true));
		 Document doc = null;

		 try {
			 doc = (Document)Factory.createResource("gate.corpora.DocumentImpl", params);
			 corpus.clear();
			 corpus.add(doc);
			 List<MetadataMap> annotations = new ArrayList<MetadataMap>();

			 Map<Object, Object> dOntos = new HashMap<Object, Object>();
			 dOntos.putAll(indexer.getDomainsOntologies());

			 for (Object ontoURI : dOntos.keySet()) {
				 domainOnto.cleanOntology();

				 String ontoText = (String) dOntos.get(ontoURI);
				 StringReader ontoTextStream = new StringReader(ontoText.toString());
				 domainOnto.readOntologyData(ontoTextStream, null, OConstants.OntologyFormat.RDFXML, false);
				 
				 Set<OClass> profileTopClasses = domainOnto.getOClasses(true);
				 String profileNamespace = profileTopClasses.iterator().next().getONodeID().getNameSpace();
				 domainOnto.setDefaultNameSpace(profileNamespace);
					
				 ontoRootGaz.reInit();

				 annotations.clear();
				 annotations = this.executeCorpus();

				 indexer.doProfileUpdate(annotations, metadata, ontoURI.toString());
			 }			

			 Factory.deleteResource(doc);	

//			 boolean result = true;
//
//			 if (result) {
//				 reloadOntologies();
//			 }

		 } catch (ResourceInstantiationException e) {
			 e.printStackTrace();
		 }		
	}

	private List<MetadataMap> executeCorpus() {

		List<MetadataMap> annotations = new ArrayList<MetadataMap>();

		try {
			controller.execute();

			AnnotationSet defaultAnnotSet = controller.getCorpus().get(0).getAnnotations();

			Set<Annotation> lookupAnnot = 
					new HashSet<Annotation>(defaultAnnotSet.get("Lookup"));

			Set<Annotation> tokenAnnot = 
					new HashSet<Annotation>(defaultAnnotSet.get("Token"));

			Set<Annotation> sentenceAnnot = 
					new HashSet<Annotation>(defaultAnnotSet.get("Sentence"));

			//Search the correspondent token and sentence for each lookup annotation 
			for (Annotation annotation : lookupAnnot) {

				Annotation token = null, sentence = null;

				//get the correspondent token
				for (Annotation tokenTemp : tokenAnnot) {
					if (tokenTemp.getStartNode().getOffset() == annotation.getStartNode().getOffset()) {
						token = tokenTemp;
						break;
					} //if (tokenTemp.getStartNode().getOffset()...
				} //for (Annotation tokenTemp : tokenAnnot)

				//get the correspondent sentence
				for (Annotation sentenceTemp : sentenceAnnot) {
					if (sentenceTemp.getStartNode().getOffset() <= annotation.getStartNode().getOffset() &&
							sentenceTemp.getEndNode().getOffset() >= annotation.getEndNode().getOffset()) {
						sentence = sentenceTemp;

						FeatureMap sentenceString = Factory.newFeatureMap();
						sentenceString.put("sentence", 
								corpus.get(0).getContent().getContent(
										sentence.getStartNode().getOffset(), sentence.getEndNode().getOffset()));
						sentence.setFeatures(sentenceString);

						break;
					} //if (sentenceTemp.getStartNode().getOffset()...
				} //for ((Annotation sentenceTemp : sentenceAnnot) 

				//Create the annotation conform Semantic Indexer Structure
				MetadataMap tempMMap = new MetadataMap();
				tempMMap.put("URI", (annotation.getFeatures().get("URI") == null)? "" : annotation.getFeatures().get("URI"));
				tempMMap.put("propertyURI", (annotation.getFeatures().get("propertyURI") == null)? "" : annotation.getFeatures().get("propertyURI"));
				tempMMap.put("classURI", (annotation.getFeatures().get("classURI") == null)? "" : annotation.getFeatures().get("classURI"));
				tempMMap.put("type", (annotation.getFeatures().get("type") == null)? "" : annotation.getFeatures().get("type"));
				tempMMap.put("offset", (annotation.getStartNode().getOffset() == null)? "" : annotation.getStartNode().getOffset());
				tempMMap.put("root", (token.getFeatures().get("root") == null)? "" : token.getFeatures().get("root"));
				tempMMap.put("category", (token.getFeatures().get("category") == null)? "" : token.getFeatures().get("category"));
				tempMMap.put("kind", (token.getFeatures().get("kind") == null)? "" : token.getFeatures().get("kind"));
				tempMMap.put("length", (token.getFeatures().get("length") == null)? "" : token.getFeatures().get("length"));
				tempMMap.put("string", (token.getFeatures().get("string") == null)? "" : token.getFeatures().get("string"));
				tempMMap.put("sentence", (sentence.getFeatures().get("sentence") == null)? "" : sentence.getFeatures().get("sentence"));

				annotations.add(tempMMap);

			} // try
		} catch (ExecutionException | InvalidOffsetException e) {

			e.printStackTrace();
		} //catch

		return annotations;
	} // executeCorpus()	
} //class SemanticAugmentationImplGate
