package mobilehealth.sac.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import mobilehealth.sac.domain.DomainData;

public class Util {

	/**
	 * Checks if content is a URL
	 * 
	 * @param content
	 * @return true if the content is a URL
	 */
	public static boolean isUrl(String content) {

		boolean result = false;

		try {
			URL url = new URL(content);
			URLConnection conn = url.openConnection();
			conn.connect();
			result = true;
		} catch (IOException e) {
		}

		return result;
	}

	/**
	 * 
	 * @param domainsData
	 */
	public static void sortList(ArrayList<DomainData> domainsData) {

		// Caso n�o haja nenhuma altera��o em uma itera��o, a lista j� est� ordenada
		boolean change = false;

		for (int i = 0; i < domainsData.size(); i++) {
			for (int j = 0; j < domainsData.size() - 1; j++) {
				if (domainsData.get(j).qtdPalavras < domainsData.get(j + 1).qtdPalavras) {
					DomainData temp = domainsData.get(j);
					domainsData.set(j, domainsData.get(j + 1));
					domainsData.set(j + 1, temp);
					change = true;
				}
			}
			if (!change)
				return;
		}

	}

	/**
	 * 
	 * @param text
	 * @param brokenSize
	 * @return
	 */
	public static ArrayList<String> brokenText(String text, int brokenSize) {
		ArrayList<String> list = new ArrayList<String>();

		// Remo��o de pontua��o para obten��o de apenas palavras
		text = text.replace(",", " ");
		text = text.replace(".", " ");
		text = text.replace("!", " ");
		text = text.replace(";", " ");
		text = text.replace("?", " ");
		text = text.replace("\n", " ");
		text = text.replace("\t", " ");
		String[] separator = text.split(" ");

		String textList = "";
		// Palavras Adicionadas � sequencia da quebra
		int adicionados = 0;
		// Pr�xima palavra que iniciar� a sequencia
		int proximo = 0;

		for (int i = 0; i < separator.length; i++) {
			if (textList.equals(""))
				textList += separator[i];
			else
				textList = textList + " " + separator[i];

			// Nesse ponto houve a adi��o de uma palavra na sequencia
			adicionados++;

			// Caso o ponto de quebra tenha sido alcan�ado
			if (adicionados == brokenSize) {

				// Adicionar a nova sequencia na lista de sa�da
				list.add(textList);
				// Limpar a vari�vel que recebe as palavras adicionadas
				textList = "";
				// Zerar o contador de palavras
				adicionados = 0;
				// Ir para pr�xima palavra na sequencia
				proximo++;
				// pr�ximo - 1 pois a pr�xima instru��o � o incremento do i no for
				i = proximo - 1;
			}
		}
		return list;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String removeHtmlMarkup(String text) {
		String cleanText = Jsoup.parse(text).text();
		return cleanText;
	}
}
