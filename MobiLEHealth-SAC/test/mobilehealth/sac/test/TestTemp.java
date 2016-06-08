package mobilehealth.sac.test;

import java.util.List;

import mobilehealth.sac.augmentation.ContentAnalyzer;

public class TestTemp {
	
	public static void main(String[] args) {
		
		ContentAnalyzer contentAnalyzer = new ContentAnalyzer();
		contentAnalyzer.analyze("Diabetes","en");
		System.out.println("Finished");
		
		/* The application does not end. Probably the GATE is not stopped. */
		System.exit(0);
		
	}
	
}
