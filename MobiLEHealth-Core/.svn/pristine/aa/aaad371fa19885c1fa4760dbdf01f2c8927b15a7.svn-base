package mobilehealth.sac.test;

import org.joda.time.DateTime;

import mobilehealth.sac.augmentation.SemanticAugmentation;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.util.DownloadURL;

public class TestURLYoutube {

	public static void main(String[] args) {
		
		SemanticAugmentation semanticAugmentation = new SemanticAugmentation();
		
		Resource resource = new Resource();
		resource.setSchema("public");
		resource.setTableName("content");
		resource.setFieldName("id");
		resource.setFieldValue(1);
		resource.setDateProcess(DateTime.now());

		String text = "";

		resource.setText(DownloadURL.download("https://www.youtube.com/watch?v=_TA6CguNukw"));
		
		semanticAugmentation.execute(resource);
		
	}
}
