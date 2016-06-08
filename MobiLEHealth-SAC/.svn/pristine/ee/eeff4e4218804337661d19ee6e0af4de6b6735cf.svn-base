package mobilehealth.sac.augmentation;

import java.util.ArrayList;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

public class LanguageIdentifierImplLanguageDetection implements LanguageIdentifier {

	public LanguageIdentifierImplLanguageDetection() {
		
		// Local onde estão os arquivos profiles para cada linguagem
		/* Maq UFERSA */
		String profileDirectory = "C:/dev/ide/workspaces/eclipse/mobiLEHealth/MobiLEHealth-SAC/app/LanguageDetection/profiles";
		
		try {
			init(profileDirectory);
		} catch (Exception e) {

		}
	}

	public String getLanguageIdentification(String content) {
		String result = "";
		try {
			result = detect(content);
		} catch (Exception e) {

		}
		return result;
	}

	public void init(String profileDirectory) throws LangDetectException {
		DetectorFactory.loadProfile(profileDirectory);
	}

	public String detect(String text) throws LangDetectException {
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.detect();
	}

	public ArrayList<Language> detectLangs(String text) throws LangDetectException {
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.getProbabilities();
	}

}
