package mobilehealth.sac.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DownloadURL {

	public static String download(String url) {

		URL url2;
		String text = "";

		try {
			url2 = new URL(url);

			InputStream is = url2.openStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String linha = br.readLine();

			while (linha != null) {
				linha = br.readLine();
				text += linha;
			}

			br.close();
			isr.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!text.isEmpty()) {
			text = Util.removeHtmlMarkup(text);
		}

		return text;

	}

}
