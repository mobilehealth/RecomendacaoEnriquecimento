package mobilehealth.client_desktop.simulation.data;


import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.prc.controller.inputdata.SecurityUbi;
import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Content;

public class GenerateContent {

	private final int size = 120;
	private int numeroDeFaixas = 3;
	private int tamanhoDaFaixa = 40;

	
	//private static ManagerServer managerServer = ManagerServer.getInstance();
	IUbi managerServer = FacadeLocal.getInstance();
	ContainerTitleDescriptionUrl_teste containerTitleDescriptionUrl_teste = new ContainerTitleDescriptionUrl_teste();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new GenerateContent()).run();
				
	}
			
	public void run() {
		
		List<Content> con = new ArrayList<Content>();
		List<TitleDescriptionUrl> listTitleDescriptionUrl_teste = new ArrayList<TitleDescriptionUrl>();
		
		listTitleDescriptionUrl_teste = containerTitleDescriptionUrl_teste.getTitleDescriptionUrl();
		
		Random random = new Random();
		
		System.out.println("\nConstrução de Contents:");

		for (int i = 0; i < size; i++) {

			Content content = new Content();
						
			content.setBytesOnline( random.nextInt(5000) + 1000  );
			content.setDateAdd( generateDate(i) );
			
			content.setDescription(listTitleDescriptionUrl_teste.get(i).getDescription());
			content.setSecondsOnline( 0 );
			
			//SUBTYPE_POST_PAGINA = 3;
			content.setSubtype(3);
			content.setTitle(listTitleDescriptionUrl_teste.get(i).getTitle());
			
			//TYPE_POST = 0;
			content.setType(Content.TYPE_POST);
			content.setUrlOnline(listTitleDescriptionUrl_teste.get(i).getUrlOnline());
			content.setVisibility(Content.VIS_PUBLIC);
			
			//SEMELHANÇA SEMÂNTICA
			content.setSS1(generateSemelhanca(i, 1));
			content.setSS2(generateSemelhanca(i, 2));
			//content.setSS3(generateSemelhanca(i, 3));
			//content.setSS4(generateSemelhanca(i, 4));
			con.add(content);
		}

		System.out.println("\nGerados:" + con.size()+" Contents.");
		saveServer(con);
		System.out.println("\nSalvos:" + con.size()+" Contents.");
		//saveInFile(con);
	}

	private void saveServer(List<Content> con) {
		//Random random = new Random();
		
		for(Content content : con) {
			managerServer.createContent(content);
		}
		
	}

	private void saveInFile(List<Content> con) {

		try {
			PrintWriter out = new PrintWriter("teste_content.txt");

			for (Content content : con) {

				String strdate = null;

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

				if (content.getDateCreation() != null) {
					strdate = sdf.format(content.getDateCreation().getTime());
				}

				String imprimir = content.getDescription() + "|"
						+ content.getTitle() + "|" + content.getUrlOnline();

				out.println(imprimir);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private String generateTitle (int i) {
		
		String title = "";
		if (i < this.size/3) { 
			title = "Artigo sobre Esclerose Lateral Amiotrófica" + i;
		} else if (i < (2*this.size)/3) {
			title = "Artigo sobre Diabetes" + i;
		} else {
			title = "Artigos sobre assuntos diversos" + i;
		}			

		return title;
	}	
		
/*		
		if (i < this.size* 0.6) {
			title = "Artigo de Diabetes" + i;
		} else {
			title = "Outros Artigos" + i;
		}	
*/			
/*
		if (i < this.size* 0.2) {
			title = "Artigo de Ciência da Computação" + i;
		} else if (i < this.size* 0.4) {
			title = "Artigo de Medicina" + i;
		} else if (i < this.size* 0.6) {
			title = "Artigo de Direito" + i;
		} else if (i < this.size*0.8) {
			title = "Artigo de Contabilidade" + i;
		} else {
			title = "Outros Artigo" + i;
		}
		
		return title;
	}
*/	
	private String generateDescricao (int i) {
		
		String descricao = "";

		
		if (i < this.size/3) { 
			descricao = "Descricao do artigo sobre Esclerose Lateral Amiotrófica" + i;
		} else if (i < (2*this.size)/3) {
			descricao = "Descricao do artigo sobre Diabetes" + i;
		} else {
			descricao = "Descricao do artigo sobre assuntos diversos" + i;
		}			

		return descricao;		
	}
/*		
		if (i < this.size* 0.6) { 
			descricao = "Descricao do artigo sobre Diabetes" + i;
		} else {
			descricao = "Descricao do artigo de Outros temas" + i;
		} 
/*		
		if (i < this.size* 0.2) { 
			descricao = "Descricao do artigo de Ciência da Computação" + i;
		} else if (i < this.size* 0.4) {
			descricao = "Descricao do artigo de Medicina" + i;
		} else if (i < this.size* 0.6) {
			descricao = "Descricao do artigo de Direito" + i;
		} else if (i < this.size*0.8) {
			descricao = "Descricao do artigo de Contabilidade" + i;
		} else {
			descricao = "Descricao do artigo de Outros temas" + i;
		}			

		return descricao;		
	}
*/	
	private float generateSemelhanca(int i, int k) {

		Random random = new Random();
		float SS = 0;
		float range = random.nextFloat()*3;

		if (((int) (i + tamanhoDaFaixa)/tamanhoDaFaixa) == k ) {
			SS = range + 7;
		} else {
			SS = range;
		}		
		if (i >= tamanhoDaFaixa*4) 
			SS = range;
		
		return SS;
	}
	
	private Calendar generateDate(int i) {

		int year, month, day;
		int age = 0;

		Calendar date = Calendar.getInstance();

		Calendar currentDate = Calendar.getInstance();
		int currentYear = currentDate.get(Calendar.YEAR);
		int currentMonth = currentDate.get(Calendar.MONTH);
		int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

		Random random = new Random();

		if (i < this.size/2) { 
			age = 0;
		} else if (i < 3* this.size / 4) { 
			age = 1;
		} else { 
			age = 2;
		}

		year = currentYear - age;
		month = random.nextInt(currentMonth + 1);
		if (month == currentMonth) {
			day = random.nextInt(currentDay) + 1;
		} else {
			date.set(year, month, 1);
			date.getActualMaximum(Calendar.DAY_OF_MONTH);
			day = date.get(Calendar.DAY_OF_MONTH);
		}

		date.set(year, month, day);

		return date;
	}
		
}
