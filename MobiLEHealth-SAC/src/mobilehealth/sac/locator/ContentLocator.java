package mobilehealth.sac.locator;

import org.joda.time.DateTime;
import org.joda.time.Days;

import mobilehealth.core.controller.ContentController;
import mobilehealth.core.dao.sac.ResourceHistoryDAO;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.sac.ResourceHistory;
import mobilehealth.sac.augmentation.SemanticAugmentation;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.util.DownloadURL;
import mobilehealth.sac.util.Parameters;

/**
 * This agent will scan MobiLEHealth database and get all contents that need to go through a
 * augmentation semantic process. This should be occur in a cycles time intervals. Should also
 * consider the historic of processing of the content.
 * 
 * @author Jonathan Darlan
 * @date 01/08/2014
 * 
 */
public class ContentLocator extends Thread {

	private boolean isAlive;

	public ContentLocator() {
		isAlive = true;
	}

	public void run() {

		try {
			while (isAlive) {
				execute();

				// 5 minutes
				sleep(300000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void terminate() {
		isAlive = false;
	}

	public void execute() {

		ContentController contentController = new ContentController();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		SemanticAugmentation semanticAugmentation = new SemanticAugmentation();
		
		
		/* Get all contents to check which need to be enriched (semantic augmentation) */
		for (Content content : contentController.getAllContents()) {
			/* The semantic augmentation is performed if the is no history or last update is expired */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("public", "content", "id", content.getId());
			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				Resource resource = null;
				
				/* Create resource to send processing */
				resource = new Resource();
				resource.setSchema("public");
				resource.setTableName("content");
				resource.setFieldName("id");
				resource.setFieldValue(content.getId());
				resource.setDateProcess(DateTime.now());

				String text = "";

				/* Gets the text to be processed according to the content type */
				if (content.getUrlOnline() != null) {
					text = DownloadURL.download(content.getUrlOnline());
				} else {
					text = content.getTitle() + "\n" + content.getDescription();
				}

				resource.setText(text);

				/* Execute the semantic augmentation only if there is content */
				if (!resource.getText().isEmpty()) {
					semanticAugmentation.execute(resource);
				}
			}

		}

		
		/* Finalize objects used in semantic augmentation */
		semanticAugmentation.finalize();

	}

}
