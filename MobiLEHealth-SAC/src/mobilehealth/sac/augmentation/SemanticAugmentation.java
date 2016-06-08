package mobilehealth.sac.augmentation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobilehealth.core.dao.sac.ResourceHistoryDAO;
import mobilehealth.core.domain.sac.ResourceHistory;
import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Link;
import mobilehealth.sac.ontology.Resource;

/**
 * Execute the semantic augmentation of any resource (content, user, ...) There
 * is distinct processing for each resource type.
 * 
 * @author Jonathan Darlan
 * @date 15/09/2014
 */
public class SemanticAugmentation {

	private ContentAnalyzer contentAnalyzer;
	private SemanticIndexer semanticIndexer;

	/**
	 * default constructor
	 */
	public SemanticAugmentation() {
		init();
	}

	/**
	 * Initialize everything that will be used to semantic augmentation
	 */
	private void init() {
		contentAnalyzer = new ContentAnalyzer();
		semanticIndexer = new SemanticIndexer();
	}

	public void finalize() {
		contentAnalyzer.finalize();
	}

	/**
	 * Execute the semantic augmentation for a resource.
	 * 
	 * @param resource
	 */
	public void execute(Resource resource) {

		if (Preprocessor.validate(resource)) {

			/* Run NLP and seek domain ontology concepts */
			List<Link> links = contentAnalyzer.analyze(resource.getText(), resource.getLanguage());

			/* Associates resource with links */
			resource.setLinks(links);

			/* Semantic indexation of the resource in the UPO */
			semanticIndexer.resource(resource);

			/* Save history augmentation */
			saveHistory(resource);
		}
	}

	public void execute(Access access) {

		if (Preprocessor.validate(access)) {
			semanticIndexer.access(access);
			
			/* Save history augmentation */
			saveHistory(access);
		}

	}

	public void executeMeasurements(Resource resource) {
		
		if (Preprocessor.validate(resource)) {
			
			Map<String,String> measurements = new HashMap<String, String>();
			
			String[] temp = resource.getText().split("|");
			String textSearch = temp[0];
			
			for(int i = 1; i < temp.length; i++) {
				String[] temp2 = temp[i].split(";");
				measurements.put(temp2[0], temp2[1]);
			}
			
			/* Run NLP and seek domain ontology concepts */
			List<Link> links = contentAnalyzer.analyze(textSearch, measurements);

			/* Associates resource with links */
			resource.setLinks(links);

			/* Semantic indexation of the resource in the UPO */
			semanticIndexer.resource(resource);

			/* Save history augmentation */
			saveHistory(resource);
		}
		
	}

	public static void saveHistory(Resource resource) {
		
		ResourceHistory resourceHistory = new ResourceHistory();
		resourceHistory.setSchema(resource.getSchema());
		resourceHistory.setTable(resource.getTableName());
		resourceHistory.setFieldName(resource.getFieldName());
		resourceHistory.setFieldValue(resource.getFieldValue());
		resourceHistory.setRegistrationDate(new Date());

		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		resourceHistoryDAO.save(resourceHistory);
	}
	
	public static void saveHistory(Access access) {
		
		ResourceHistory resourceHistory = new ResourceHistory();
		resourceHistory.setSchema("public");
		resourceHistory.setTable("relatePersonContent");
		resourceHistory.setFieldName("id");
		resourceHistory.setFieldValue(access.getResource().getFieldValue());
		resourceHistory.setRegistrationDate(new Date());

		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		resourceHistoryDAO.save(resourceHistory);

		
	}

}
