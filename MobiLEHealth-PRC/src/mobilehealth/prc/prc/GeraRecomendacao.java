package mobilehealth.prc.prc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.prc.phr.ListaConteudos;
import mobilehealth.prc.recommendation.data.Generate;
//import mobilehealth.client_desktop.simulation.data.GeneratePerson;
//import mobilehealth.client_desktop.simulation.data.GenerateRelates;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.recommendation.Ubiqua;

public class GeraRecomendacao {

	IUbi iUbi = FacadeLocal.getInstance();

	private ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida = null;
	private ArrayList<RelatePersonContent> resultadoRecomendacaoConjunta = null;
	//private ArrayList<RelatePersonContent> resultadoRecomendacaoUbiqua;
	
	private double contadorVP = 0;
	private double contadorFP = 0;			

	private double contadorVPU = 0;
	private double contadorFPU = 0;			
	private double contadorVPH = 0;
	private double contadorFPH = 0;			
	private double contadorVPC = 0;
	private double contadorFPC = 0;			


	private int sizePerson;
	private int sizeContent;
	private int sizeLocation;
	private int numeroDeFaixas;
	private int numeroDeTesteUsuarioDefinidoContextoLivre;
	private int numeroDeTesteUsuarioLivreContextoDefinido;
	private int idPerson;
	
	
	public static void main(String[] args) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date date; 
		
				
		while(true){
		
			try {
				
				System.out.println("\n\n\n\n\n\t -------->>>>>> Executou a recomenda��o <<<<<<<----------\n\n\n\n\n");
				(new GeraRecomendacao()).run();
				date  = new Date();
				System.out.println("Ultima recomenda��o: " + dateFormat.format(date));
			
				// 5 horas para gerar uma nova recomenda��o
				Thread.sleep(60000);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	/*		
		while(true){
		
		(new GeraRecomendacao()).run();
		try {
			Thread.sleep(900000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
*/		
	}


	public void run() {

		this.numeroDeFaixas = 5;
		System.out.println("\n          -   AN�LISE DA SIMULA��O - RECOMENDA��O CONJUNTA  -");
		System.out.println("\n ----------------------------------------------------------");
		System.out.println("\n 1 - Gerando Inst�ncias e Rela��es... Aguarde Por Favor!!!");
		//(new Generate()).run();
	
		System.out.println("\n     Inst�ncias e Rela��es Gerada Com Sucesso!!!");
				
		System.out.println("\n 2 - Gerando Lista de Colaboradores... Aguarde Por Favor!!!");
		geraListaColaboradores();		
		System.out.println("\n     Lista de Colaboradores Gerada Com Sucesso!!!");
		
		System.out.println("\n 3 - Gerando Lista Colaborativa Ponderada... Aguarde Por Favor!!!");
		geraListaColaborativaPonderada();
		System.out.println("\n     Lista Colaborativa Ponderada Gerada Com Sucesso!!!");
		
		System.out.println("\n 4 - Gerando Lista Baseada Em Conte�do... Aguarde Por Favor!!!");
		geraListaBaseadaEmConteudo();
		System.out.println("\n     Lista Baseada Em Conte�do Gerada Com Sucesso!!!");

		System.out.println("\n 5 - Gerando Lista Baseada Na Ubiquidade... Aguarde Por Favor!!!");
		geraListaFinais();
		System.out.println("\n     Lista Baseada Na Ubiquidade Gerada Com Sucesso!!!");

		System.out.println("\n 6 - Analisando Lista de Colaboradores... Aguarde Por Favor!!!");
		// Necessita dos campos Learming preenchidos
		analisaListadeColaboradores();
		
		System.out.println("\n 7 - Analisando Lista Colaborativa Ponderada... Aguarde Por Favor!!!");
		analisaListasDeConteudos(1);

		System.out.println("\n 8 - Analisando Lista Baseada Em Conte�do... Aguarde Por Favor!!!");
		analisaListasDeConteudos(2);
	
		
	}
	
	private void geraListaColaboradores(){
		
		iUbi.gerarListaColaboradores(10);
		
	}
	
	private void geraListaColaborativaPonderada(){
		
		iUbi.gerarListaColaborativa(10);
		
	}

	private void geraListaBaseadaEmConteudo(){
		
		iUbi.gerarListaBaseadaConteudo(10);
		
	}
	
	private void geraListaFinais(){
		
		List<Person> persons = new ArrayList<Person>();
		List<Person> listPerson2 = iUbi.getAllPerson();
		List<Location> locations = iUbi.getAllLocation();
		ListaConteudos listaConteudos = new ListaConteudos();
		
		List<Integer> minhaLista = new ArrayList<Integer>();
		Connection c = null;
		Statement stmt = null;
		String sql = "select p.id as id_person from public.person p where p.id not in(select r.id_person from public.recommendation r where r.visited = false group by r.id_person having count(*) > 2 ) and p.id not in(select u.person_id from app.users u where type = true)";
		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mobilehealth2","postgres", "postgres");
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			 // Cria uma ArrayLista, onde os valores ser�o adicionados e enviados atrazes do return
	       
	        int id;
	        //Enquanto existir valores no ResultSet
	        // o loop sera efetuado
	        while (rs.next()) {

	           id = Integer.parseInt(rs.getString("id_person"));

	            minhaLista.add(id);

	        }	
			
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(int b = 0; b < minhaLista.size(); b++){
			for(int a = 0; a < listPerson2.size(); a++){
				if(minhaLista.get(b) == listPerson2.get(a).getId()){
					System.out.println(a + " - "  +listPerson2.get(a).getId());
					persons.add(listPerson2.get(a));
					break;
				}
			}
		}
		
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\nTamanho da lista: " + minhaLista.size());
		
		Random random = new Random();
		this.sizePerson = persons.size();
		this.sizeLocation = locations.size();
		int tamanhoFaixaLocation = (int) (sizeLocation/numeroDeFaixas);
		
		if(tamanhoFaixaLocation == 0){
			tamanhoFaixaLocation++;
		}
		

		this.contadorVPU = 0;
		this.contadorFPU = 0;			

		this.contadorVPH = 0;
		this.contadorFPH = 0;			

		this.contadorVPC = 0;
		this.contadorFPC = 0;			

		System.out.println("\n ---------------------------------------------------------");		
		System.out.println("\n - Caso A - Perfil Definido - Contexto Neutro             ");
		System.out.println("\n ---------------------------------------------------------");
		
		
		//System.out.println("\n\n\n---------  " + sizePerson + "     --------------- \n\n\n");
		
		if(sizePerson > 0){
		
		for(int i = 0; i < sizePerson; i++) {
			
			Person personTemp = persons.get(i);
			int indiceLocation = (int) (i*0.2);
			
			if(indiceLocation >= locations.size()){
				indiceLocation = locations.size()-1;
			}
			Context context;
				if(locations.size() != 0){
					context = generateContexto(personTemp, null, locations.get(indiceLocation), Context.TYPE_REGULAR);
					// Atribui esse contexto � pessoa selecionada
					personTemp.getListContexts().add(context);
				} else {
					
					context = generateContexto(personTemp, null, null, Context.TYPE_REGULAR);
					// Atribui esse contexto � pessoa selecionada
					personTemp.getListContexts().add(context);
					
				}
				
				ArrayList<RelatePersonContent> recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
				
				// Objeto da recomenda��o ubiqua
				Ubiqua ubiqua = new Ubiqua();
				
				
				// Gera��o da lista ubiqua (em tempo real devido a sensibilidade ao contexto)
				recomendacaoUbiqua = ubiqua.geraListaUbiqua(personTemp, context, 20);
	
				
				if(recomendacaoUbiqua == null){
					
				} else {
						
					// Analisa Recomenda��o Ub�qua
					analisaListasDeConteudos(3, i, recomendacaoUbiqua);
				}
				
				// Solicita Recomenda��o Hibrida
				resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(personTemp, recomendacaoUbiqua);
	
				
				if(resultadoRecomendacaoHibrida == null){
					
				} else {
					
					// Analisa Recomenda��o H�brida
					analisaListasDeConteudos(4, i, resultadoRecomendacaoHibrida);
				}
				
				// Solicita Recomenda��o H�brida
				resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);
	
				if((resultadoRecomendacaoConjunta == null)){
					
				} else {
					// Analisa Recomenda��o Conjunta
					analisaListasDeConteudos(5, i, resultadoRecomendacaoConjunta);
				}
				
				if(resultadoRecomendacaoConjunta != null){
				
					listaConteudos.mostrar(resultadoRecomendacaoConjunta);
		
				} else {
					
					System.out.println("\n\n\n------------->>>>> Lista vazia   <<<<<----------------\n\n\n");
					
				}
		}

		
		imprime(3);
		imprime(4);
		imprime(5);

		this.contadorVPU = 0;
		this.contadorFPU = 0;			

		this.contadorVPH = 0;
		this.contadorFPH = 0;			

		this.contadorVPC = 0;
		this.contadorFPC = 0;			

		System.out.println("\n ---------------------------------------------------------");		
		System.out.println("\n - Caso B - Perfil Neutro - Contexto Definido             ");
		System.out.println("\n ---------------------------------------------------------");

		for(int i = 0; i < sizeLocation*0.2; i++){

			int indice = random.nextInt(tamanhoFaixaLocation) + (int) (tamanhoFaixaLocation*4);
			
			if(indice >= persons.size()){
				
				indice = persons.size()-1;
				
			}
			
			
			
			Person personTemp = persons.get(indice);
		
			Context context = generateContexto(personTemp, null, locations.get(i), Context.TYPE_REGULAR);
			// Atribui esse contexto � pessoa selecionada
			personTemp.getListContexts().add(context);
		
			ArrayList<RelatePersonContent> recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
			
			// Objeto da recomenda��o ubiqua
			Ubiqua ubiqua = new Ubiqua();
			
			// Gera��o da lista ubiqua (em tempo real devido a sensibilidade ao contexto)
			recomendacaoUbiqua = ubiqua.geraListaUbiqua(personTemp, context, 20);
			
			if(recomendacaoUbiqua == null){
	
			} else {
				
				// Analisa Recomenda��o Ub�qua
				analisaListasDeConteudos(3, i, recomendacaoUbiqua);
			}
				// Solicita Recomenda��o Hibrida
				resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(personTemp, recomendacaoUbiqua);
	
			if(resultadoRecomendacaoHibrida == null){
				
			} else {
				// Analisa Recomenda��o H�brida
				analisaListasDeConteudos(4, i, resultadoRecomendacaoHibrida);
			}
			
			
				// Solicita Recomenda��o H�brida
				resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);
		
			if(resultadoRecomendacaoConjunta == null){
				
			} else {
				// Analisa Recomenda��o Conjunta
				analisaListasDeConteudos(5, i, resultadoRecomendacaoConjunta);
			}
				//listaConteudos.mostrar(resultadoRecomendacaoConjunta);
			
		}
	}
		imprime(3);
		imprime(4);
		imprime(5);
		
	}

	private void analisaListadeColaboradores() {
		
		List<Person> persons = iUbi.getAllPerson();
		
		this.sizePerson = persons.size();

		this.contadorVP = 0;
		this.contadorFP = 0;			
		
		for(int i = 0; i < sizePerson; i++){
 
			List<RelatePersonPerson> colaboradores = persons.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_COLLABS);			
			
			for(int j = 0; j < colaboradores.size(); j++){

					if (persons.get(i).getLearning().getFormationArea() == colaboradores.get(j).getPerson2().getLearning().getFormationArea()) {
						contadorVP++;					
					} else {
						contadorFP++;
					}

				
			}
		}
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n |        Resultado da Lista de Colaboradores       |");
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n   - Verdadeiros Positivos: " + contadorVP);
		System.out.println("\n   - Falsos Positivos: " + contadorFP);
		//System.out.println("\n   - M�trica Precision: " + (contadorVP/contadorVP+contadorFP));
		//System.out.println("\n   - False Positive Rate: " + (contadorFP/(contadorFP+200-contadorVP)));
		System.out.println("\n ----------------------------------------------------");
	}

	private void analisaListasDeConteudos(int tipoLista) {
		
		
		List<Person> persons = iUbi.getAllPerson();
		
		this.sizePerson = persons.size();

		this.contadorVP = 0;
		this.contadorFP = 0;			
		
		for(int i = 0; i < sizePerson; i++){
 
			List<RelatePersonContent> contents = persons.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED);			
			
			for(int j = 0; j < contents.size(); j++){
				if((contents.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_PONDER && tipoLista == 1) ||
				   (contents.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONTENT && tipoLista == 2)) {					
					if (Math.abs(persons.get(i).getSS1() - contents.get(j).getContent().getSS1()) > 3 ||
						Math.abs(persons.get(i).getSS2() - contents.get(j).getContent().getSS2()) > 3 )//||
					//	Math.abs(persons.get(i).getSS3() - contents.get(j).getContent().getSS3()) > 3 ||
					//	Math.abs(persons.get(i).getSS4() - contents.get(j).getContent().getSS4()) > 3 ) 
					{
						contadorFP++;					
					} else {
						contadorVP++;
					}
				}
			}
		}
		String temp = "";
		if(tipoLista == 1) temp = "Colaborativa Ponderada     |";
		if(tipoLista == 2) temp = "Baseada em Conte�do        |";
		if(tipoLista == 3) temp = "Ub�qua			          |";
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n |     Resultado da Lista "+ temp);
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n   - Verdadeiros Positivos: " + contadorVP);
		System.out.println("\n   - Falsos Positivos: " + contadorFP);
		//System.out.println("\n   - M�trica Precision: " + (contadorVP/contadorVP+contadorFP));
		//System.out.println("\n   - False Positive Rate: " + (contadorFP/(contadorFP+200-contadorVP)));
		System.out.println("\n ----------------------------------------------------");
	}

	private void analisaListasDeConteudos(int tipoLista, Person person, ArrayList<RelatePersonContent> recomendacao) {
			
		for(int j = 0; j < recomendacao.size(); j++){
			if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_UBIQ && tipoLista == 3){					
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) */
				{
					contadorFPU++;					
				} else {
					contadorVPU++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_HYBRID && tipoLista == 4)
			{
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) */
				{
					this.contadorFPH++;					
					this.contadorVPH++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONJUNTA && tipoLista == 5){
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) */
				{
					contadorFPC++;					
				} else {
					contadorVPC++;
				}
			}
		}
	}
	
	private void analisaListasDeConteudos(int tipoLista, int i, ArrayList<RelatePersonContent> recomendacao) {
		
		for(int j = 0; j < recomendacao.size(); j++) {
			if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_UBIQ && tipoLista == 3){
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					contadorFPU++;					
				} else {
					contadorVPU++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_HYBRID && tipoLista == 4)
			{
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					this.contadorFPH++;					
				} else {
					this.contadorVPH++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONJUNTA && tipoLista == 5){
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					contadorFPC++;					
				} else {
					contadorVPC++;
				}
			}
		}
	}
	
	private void imprime(int tipoLista){

		if(tipoLista == 3) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Lista Baseada na Ubiquidade     |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPU);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPU);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}
		else if(tipoLista == 4) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Recomenda��o H�brida         |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPH);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPH);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}
		else if(tipoLista == 5) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Recomenda��o Conjunta        |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPC);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPC);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}

	}
	
	private Context generateContexto(Person person, Content content, Location location, int status) {
		Context context = new Context();
		context.setPerson(person);
		context.setLocation(location);
		context.setContent(null);
		context.setDevice(new Device());
		context.setTimeStamp(null);
		context.setBattery(50);
		context.setConnection(Context.CONNECTION_WIFI);
		context.setConnectionSpeed(300);		
		context.setPersonSpeed(0);
		context.setType(status);
				
		return context;
	}


	public int getIdPerson() {
		return idPerson;
	}


	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

}































































/*package mobilehealth.prc.prc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import mobilehealth.pcr.test.BaseadaConteudo;
import mobilehealth.prc.recommendation.Ubiqua;
import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.prc.phr.ListaConteudos;
//import mobilehealth.client_desktop.simulation.data.GeneratePerson;
//import mobilehealth.client_desktop.simulation.data.GenerateRelates;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.recommendation.ColaborativaPonderada;
import mobilehealth.prc.recommendation.Hibrida;

public class GeraRecomendacao {

	IUbi iUbi = FacadeLocal.getInstance();

	private ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida;
	private ArrayList<RelatePersonContent> resultadoRecomendacaoConjunta;
	//private ArrayList<RelatePersonContent> resultadoRecomendacaoUbiqua;
	
	private double contadorVP = 0;
	private double contadorFP = 0;			

	private double contadorVPU = 0;
	private double contadorFPU = 0;			
	private double contadorVPH = 0;
	private double contadorFPH = 0;			
	private double contadorVPC = 0;
	private double contadorFPC = 0;			


	private int sizePerson;
	private int sizeContent;
	private int sizeLocation;
	private int numeroDeFaixas;
	private int numeroDeTesteUsuarioDefinidoContextoLivre;
	private int numeroDeTesteUsuarioLivreContextoDefinido;
	
	
	public static void main(String[] args) {
		
		while(true){
			
			try {
				(new GeraRecomendacao()).run();
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void run() {
		

		this.numeroDeFaixas = 5;
		System.out.println("\n          -   AN�LISE DA SIMULA��O - RECOMENDA��O CONJUNTA  -");
		System.out.println("\n ----------------------------------------------------------");
		System.out.println("\n 1 - Gerando Inst�ncias e Rela��es... Aguarde Por Favor!!!");
		//(new Generate()).run();
		
		
		//Generate g = new Generate(getIdPerson());

		System.out.println("\n     Inst�ncias e Rela��es Gerada Com Sucesso!!!");
	
		System.out.println("\n 2 - Gerando Lista de Colaboradores... Aguarde Por Favor!!!");
		
		geraListaColaboradores();		
		System.out.println("\n     Lista de Colaboradores Gerada Com Sucesso!!!");
		
		System.out.println("\n 3 - Gerando Lista Colaborativa Ponderada... Aguarde Por Favor!!!");
		
		//ColaborativaPonderada cp = new ColaborativaPonderada(getIdPerson());
		geraListaColaborativaPonderada();
		System.out.println("\n     Lista Colaborativa Ponderada Gerada Com Sucesso!!!");
		
		System.out.println("\n 4 - Gerando Lista Baseada Em Conte�do... Aguarde Por Favor!!!");
		
		//BaseadaConteudo bc = new BaseadaConteudo();
		geraListaBaseadaEmConteudo();
		System.out.println("\n     Lista Baseada Em Conte�do Gerada Com Sucesso!!!");

		
		
		System.out.println("\n 5 - Gerando Lista Baseada Na Ubiquidade... Aguarde Por Favor!!!");
		geraListaFinais();
		System.out.println("\n     Lista Baseada Na Ubiquidade Gerada Com Sucesso!!!");

		System.out.println("\n 6 - Analisando Lista de Colaboradores... Aguarde Por Favor!!!");
		analisaListadeColaboradores();
		
		System.out.println("\n 7 - Analisando Lista Colaborativa Ponderada... Aguarde Por Favor!!!");
		analisaListasDeConteudos(1);

		System.out.println("\n 8 - Analisando Lista Baseada Em Conte�do... Aguarde Por Favor!!!");
		analisaListasDeConteudos(2);
	
		
		
		
	}
	
	private void geraListaColaboradores(){
		//JOptionPane.showMessageDialog(null, "Ponderada colaboradoRES " + getIdPerson());
		iUbi.gerarListaColaboradores(10);
		
	}
	
	private void geraListaColaborativaPonderada(){
		//JOptionPane.showMessageDialog(null, "Ponderada " + getIdPerson());
		iUbi.gerarListaColaborativa(10);
		
	}

	private void geraListaBaseadaEmConteudo(){
		
		iUbi.gerarListaBaseadaConteudo(10);
		
	}
	
	private void geraListaFinais(){
		
		List<Person> persons = iUbi.getAllPerson();
		List<Location> locations = iUbi.getAllLocation();

		Random random = new Random();
		this.sizePerson = persons.size();
		this.sizeLocation = locations.size();
		int tamanhoFaixaLocation = (int) (sizeLocation/numeroDeFaixas);

		this.contadorVPU = 0;
		this.contadorFPU = 0;			

		this.contadorVPH = 0;
		this.contadorFPH = 0;			

		this.contadorVPC = 0;
		this.contadorFPC = 0;			

		System.out.println("\n ---------------------------------------------------------");		
		System.out.println("\n - Caso A - Perfil Definido - Contexto Neutro             ");
		System.out.println("\n ---------------------------------------------------------");
		
			
			Context context = generateContexto(person, null, locations.get(contadorPessoas), Context.TYPE_REGULAR);
			// Atribui esse contexto � pessoa selecionada
			person.getListContexts().add(context);
		
			ArrayList<RelatePersonContent> recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
			
			// Objeto da recomenda��o ubiqua
			Ubiqua ubiqua = new Ubiqua();
			
			// Gera��o da lista ubiqua (em tempo real devido a sensibilidade ao contexto)
			recomendacaoUbiqua = ubiqua.geraListaUbiqua(person, context, 20);

			// Analisa Recomenda��o Ub�qua
			analisaListasDeConteudos(3, contadorPessoas, recomendacaoUbiqua);

			//Hibrida h = new Hibrida(getIdPerson());
			
			// Solicita Recomenda��o Hibrida
			resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(person, recomendacaoUbiqua);

			// Analisa Recomenda��o H�brida
			analisaListasDeConteudos(4, contadorPessoas, resultadoRecomendacaoHibrida);

			// Solicita Recomenda��o H�brida
			resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);

			// Analisa Recomenda��o Conjunta
			analisaListasDeConteudos(5, contadorPessoas, resultadoRecomendacaoConjunta);
			
		
			ListaConteudos l = new ListaConteudos();
			
			l.mostrar(resultadoRecomendacaoConjunta, getIdPerson());
			
			
		//ListaConteudos l = new ListaConteudos();
		
		//l.mostrar(resultadoRecomendacaoConjunta);
		
		imprime(3);
		imprime(4);
		imprime(5);

		this.contadorVPU = 0;
		this.contadorFPU = 0;			

		this.contadorVPH = 0;
		this.contadorFPH = 0;			

		this.contadorVPC = 0;
		this.contadorFPC = 0;			

		System.out.println("\n ---------------------------------------------------------");		
		System.out.println("\n - Caso B - Perfil Neutro - Contexto Definido             ");
		System.out.println("\n ---------------------------------------------------------");

		
		for(int i = 0; i < sizeLocation*0.2; i++){
			context = new Context();
			recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
			context = generateContexto(person, null, locations.get(i), Context.TYPE_REGULAR);
			// Atribui esse contexto � pessoa selecionada
			person.getListContexts().add(context);
		
				
			// Objeto da recomenda��o ubiqua
			ubiqua = new Ubiqua();
			
			// Gera��o da lista ubiqua (em tempo real devido a sensibilidade ao contexto)
			recomendacaoUbiqua = ubiqua.geraListaUbiqua(person, context, 20);

			// Analisa Recomenda��o Ub�qua
			analisaListasDeConteudos(3, i, recomendacaoUbiqua);

			// Solicita Recomenda��o Hibrida
			resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(person, recomendacaoUbiqua);

			// Analisa Recomenda��o H�brida
			analisaListasDeConteudos(4, i, resultadoRecomendacaoHibrida);

			// Solicita Recomenda��o H�brida
			resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);

			// Analisa Recomenda��o Conjunta
			analisaListasDeConteudos(5, i, resultadoRecomendacaoConjunta);
		}
		imprime(3);
		imprime(4);
		imprime(5);
		
	}

	private void analisaListadeColaboradores() {
		
		List<Person> persons = iUbi.getAllPerson();
		
		this.sizePerson = persons.size();

		this.contadorVP = 0;
		this.contadorFP = 0;			
		
		for(int i = 0; i < sizePerson; i++){
 
			List<RelatePersonPerson> colaboradores = persons.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_COLLABS);			
			
			for(int j = 0; j < colaboradores.size(); j++){
				if (persons.get(i).getLearning().getFormationArea() == colaboradores.get(j).getPerson2().getLearning().getFormationArea()) {
					contadorVP++;					
				} else {
					contadorFP++;
				}
			}
		}
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n |        Resultado da Lista de Colaboradores       |");
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n   - Verdadeiros Positivos: " + contadorVP);
		System.out.println("\n   - Falsos Positivos: " + contadorFP);
		//System.out.println("\n   - M�trica Precision: " + (contadorVP/contadorVP+contadorFP));
		//System.out.println("\n   - False Positive Rate: " + (contadorFP/(contadorFP+200-contadorVP)));
		System.out.println("\n ----------------------------------------------------");
	}

	private void analisaListasDeConteudos(int tipoLista) {
		
		
		List<Person> persons = iUbi.getAllPerson();
		
		this.sizePerson = persons.size();

		this.contadorVP = 0;
		this.contadorFP = 0;			
		
		for(int i = 0; i < sizePerson; i++){
 
			List<RelatePersonContent> contents = persons.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED);			
			
			for(int j = 0; j < contents.size(); j++){
				if((contents.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_PONDER && tipoLista == 1) ||
				   (contents.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONTENT && tipoLista == 2)) {					
					if (Math.abs(persons.get(i).getSS1() - contents.get(j).getContent().getSS1()) > 3 ||
						Math.abs(persons.get(i).getSS2() - contents.get(j).getContent().getSS2()) > 3 )//||
					//	Math.abs(persons.get(i).getSS3() - contents.get(j).getContent().getSS3()) > 3 ||
					//	Math.abs(persons.get(i).getSS4() - contents.get(j).getContent().getSS4()) > 3 ) 
					{
						contadorFP++;					
					} else {
						contadorVP++;
					}
				}
			}
		}
		String temp = "";
		if(tipoLista == 1) temp = "Colaborativa Ponderada     |";
		if(tipoLista == 2) temp = "Baseada em Conte�do        |";
		if(tipoLista == 3) temp = "Ub�qua			          |";
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n |     Resultado da Lista "+ temp);
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n   - Verdadeiros Positivos: " + contadorVP);
		System.out.println("\n   - Falsos Positivos: " + contadorFP);
		//System.out.println("\n   - M�trica Precision: " + (contadorVP/contadorVP+contadorFP));
		//System.out.println("\n   - False Positive Rate: " + (contadorFP/(contadorFP+200-contadorVP)));
		System.out.println("\n ----------------------------------------------------");
	}

	private void analisaListasDeConteudos(int tipoLista, Person person, ArrayList<RelatePersonContent> recomendacao) {
			
		for(int j = 0; j < recomendacao.size(); j++){
			if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_UBIQ && tipoLista == 3){					
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 )
				{
					contadorFPU++;					
				} else {
					contadorVPU++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_HYBRID && tipoLista == 4)
			{
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) 
				{
					this.contadorFPH++;					
				} else {
					this.contadorVPH++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONJUNTA && tipoLista == 5){
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 )
				{
					contadorFPC++;					
				} else {
					contadorVPC++;
				}
			}
		}
	}
	
	private void analisaListasDeConteudos(int tipoLista, int i, ArrayList<RelatePersonContent> recomendacao) {
		
		for(int j = 0; j < recomendacao.size(); j++) {
			if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_UBIQ && tipoLista == 3){
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					contadorFPU++;					
				} else {
					contadorVPU++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_HYBRID && tipoLista == 4)
			{
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					this.contadorFPH++;					
				} else {
					this.contadorVPH++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONJUNTA && tipoLista == 5){
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					contadorFPC++;					
				} else {
					contadorVPC++;
				}
			}
		}
	}
	
	private void imprime(int tipoLista){

		if(tipoLista == 3) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Lista Baseada na Ubiquidade     |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPU);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPU);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}
		else if(tipoLista == 4) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Recomenda��o H�brida         |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPH);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPH);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}
		else if(tipoLista == 5) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Recomenda��o Conjunta        |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPC);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPC);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}

	}
	
	private Context generateContexto(Person person, Content content, Location location, int status) {
		Context context = new Context();
		context.setPerson(person);
		context.setLocation(location);
		context.setContent(null);
		context.setDevice(new Device());
		context.setTimeStamp(null);
		context.setBattery(50);
		context.setConnection(Context.CONNECTION_WIFI);
		context.setConnectionSpeed(300);		
		context.setPersonSpeed(0);
		context.setType(status);
				
		return context;
	}


	public int getIdPerson() {
		return idPerson;
	}


	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

}





































































































/*
 
package mobilehealth.prc.prc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import mobilehealth.prc.recommendation.Ubiqua;
import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.prc.phr.ListaConteudos;
//import mobilehealth.client_desktop.simulation.data.GeneratePerson;
//import mobilehealth.client_desktop.simulation.data.GenerateRelates;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.recommendation.ColaborativaPonderada;
import mobilehealth.prc.recommendation.Hibrida;

public class GeraRecomendacao {

	IUbi iUbi = FacadeLocal.getInstance();

	private ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida;
	private ArrayList<RelatePersonContent> resultadoRecomendacaoConjunta;
	//private ArrayList<RelatePersonContent> resultadoRecomendacaoUbiqua;
	
	private double contadorVP = 0;
	private double contadorFP = 0;			

	private double contadorVPU = 0;
	private double contadorFPU = 0;			
	private double contadorVPH = 0;
	private double contadorFPH = 0;			
	private double contadorVPC = 0;
	private double contadorFPC = 0;			


	private int sizePerson;
	private int sizeContent;
	private int sizeLocation;
	private int numeroDeFaixas;
	private int numeroDeTesteUsuarioDefinidoContextoLivre;
	private int numeroDeTesteUsuarioLivreContextoDefinido;
	private int idPerson;
	
	
	public static void main(String[] args) {
		
		//while(true){
		
			(new GeraRecomendacao()).run();
		
		//}
	}


	public void run() {
		

		this.numeroDeFaixas = 5;
		System.out.println("\n          -   AN�LISE DA SIMULA��O - RECOMENDA��O CONJUNTA  -");
		System.out.println("\n ----------------------------------------------------------");
		System.out.println("\n 1 - Gerando Inst�ncias e Rela��es... Aguarde Por Favor!!!");
		//(new Generate()).run();
		
		setIdPerson(Integer.parseInt(JOptionPane.showInputDialog(null, "ID do usuario")));
		
		//Generate g = new Generate(getIdPerson());

		System.out.println("\n     Inst�ncias e Rela��es Gerada Com Sucesso!!!");
	
		System.out.println("\n 2 - Gerando Lista de Colaboradores... Aguarde Por Favor!!!");
		geraListaColaboradores();		
		System.out.println("\n     Lista de Colaboradores Gerada Com Sucesso!!!");
		
		System.out.println("\n 3 - Gerando Lista Colaborativa Ponderada... Aguarde Por Favor!!!");
		geraListaColaborativaPonderada();
		System.out.println("\n     Lista Colaborativa Ponderada Gerada Com Sucesso!!!");
		
		System.out.println("\n 4 - Gerando Lista Baseada Em Conte�do... Aguarde Por Favor!!!");
		geraListaBaseadaEmConteudo();
		System.out.println("\n     Lista Baseada Em Conte�do Gerada Com Sucesso!!!");

		
		
		System.out.println("\n 5 - Gerando Lista Baseada Na Ubiquidade... Aguarde Por Favor!!!");
		geraListaFinais();
		System.out.println("\n     Lista Baseada Na Ubiquidade Gerada Com Sucesso!!!");

		System.out.println("\n 6 - Analisando Lista de Colaboradores... Aguarde Por Favor!!!");
		analisaListadeColaboradores();
		
		System.out.println("\n 7 - Analisando Lista Colaborativa Ponderada... Aguarde Por Favor!!!");
		analisaListasDeConteudos(1);

		System.out.println("\n 8 - Analisando Lista Baseada Em Conte�do... Aguarde Por Favor!!!");
		analisaListasDeConteudos(2);
	
		
		//
		
	}
	
	private void geraListaColaboradores(){
		
		iUbi.gerarListaColaboradores(10);
		
	}
	
	private void geraListaColaborativaPonderada(){
		ColaborativaPonderada c = new ColaborativaPonderada(getIdPerson());
		iUbi.gerarListaColaborativa(10);
		
	}

	private void geraListaBaseadaEmConteudo(){
		
		iUbi.gerarListaBaseadaConteudo(10, getIdPerson());
		
	}
	
	private void geraListaFinais(){
		
		List<Person> persons = iUbi.getAllPerson();
		List<Location> locations = iUbi.getAllLocation();

		Random random = new Random();
		this.sizePerson = persons.size();
		this.sizeLocation = locations.size();
		int tamanhoFaixaLocation = (int) (sizeLocation/numeroDeFaixas);

		this.contadorVPU = 0;
		this.contadorFPU = 0;			

		this.contadorVPH = 0;
		this.contadorFPH = 0;			

		this.contadorVPC = 0;
		this.contadorFPC = 0;			

		System.out.println("\n ---------------------------------------------------------");		
		System.out.println("\n - Caso A - Perfil Definido - Contexto Neutro             ");
		System.out.println("\n ---------------------------------------------------------");
		
		for(int i = 0; i < sizePerson*0.2; i++) {
			
			Person personTemp = persons.get(i);
			
			Context context = generateContexto(personTemp, null, locations.get(i), Context.TYPE_REGULAR);
			// Atribui esse contexto � pessoa selecionada
			personTemp.getListContexts().add(context);
		
			ArrayList<RelatePersonContent> recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
			
			// Objeto da recomenda��o ubiqua
			Ubiqua ubiqua = new Ubiqua();
			
			// Gera��o da lista ubiqua (em tempo real devido a sensibilidade ao contexto)
			recomendacaoUbiqua = ubiqua.geraListaUbiqua(personTemp, context, 20);

			// Analisa Recomenda��o Ub�qua
			analisaListasDeConteudos(3, i, recomendacaoUbiqua);

			//Hibrida h = new Hibrida(getIdPerson());
			
			// Solicita Recomenda��o Hibrida
			resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(personTemp, recomendacaoUbiqua);

			// Analisa Recomenda��o H�brida
			analisaListasDeConteudos(4, i, resultadoRecomendacaoHibrida);

			// Solicita Recomenda��o H�brida
			resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);

			// Analisa Recomenda��o Conjunta
			analisaListasDeConteudos(5, i, resultadoRecomendacaoConjunta);
			
			
			
		}

		
		ListaConteudos l = new ListaConteudos();
		
		l.mostrar(resultadoRecomendacaoConjunta);
		
		imprime(3);
		imprime(4);
		imprime(5);

		this.contadorVPU = 0;
		this.contadorFPU = 0;			

		this.contadorVPH = 0;
		this.contadorFPH = 0;			

		this.contadorVPC = 0;
		this.contadorFPC = 0;			

		System.out.println("\n ---------------------------------------------------------");		
		System.out.println("\n - Caso B - Perfil Neutro - Contexto Definido             ");
		System.out.println("\n ---------------------------------------------------------");

		for(int i = 0; i < sizeLocation*0.2; i++){

			Person personTemp = persons.get(random.nextInt(tamanhoFaixaLocation) + (int) (tamanhoFaixaLocation*4));
		
			Context context = generateContexto(personTemp, null, locations.get(i), Context.TYPE_REGULAR);
			// Atribui esse contexto � pessoa selecionada
			personTemp.getListContexts().add(context);
		
			ArrayList<RelatePersonContent> recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
			
			// Objeto da recomenda��o ubiqua
			Ubiqua ubiqua = new Ubiqua();
			
			// Gera��o da lista ubiqua (em tempo real devido a sensibilidade ao contexto)
			recomendacaoUbiqua = ubiqua.geraListaUbiqua(personTemp, context, 20);

			// Analisa Recomenda��o Ub�qua
			analisaListasDeConteudos(3, i, recomendacaoUbiqua);

			// Solicita Recomenda��o Hibrida
			resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(personTemp, recomendacaoUbiqua);

			// Analisa Recomenda��o H�brida
			analisaListasDeConteudos(4, i, resultadoRecomendacaoHibrida);

			// Solicita Recomenda��o H�brida
			resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);

			// Analisa Recomenda��o Conjunta
			analisaListasDeConteudos(5, i, resultadoRecomendacaoConjunta);
		}
		imprime(3);
		imprime(4);
		imprime(5);
		
	}

	private void analisaListadeColaboradores() {
		
		List<Person> persons = iUbi.getAllPerson();
		
		this.sizePerson = persons.size();

		this.contadorVP = 0;
		this.contadorFP = 0;			
		
		for(int i = 0; i < sizePerson; i++){
 
			List<RelatePersonPerson> colaboradores = persons.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_COLLABS);			
			
			for(int j = 0; j < colaboradores.size(); j++){
				if (persons.get(i).getLearning().getFormationArea() == colaboradores.get(j).getPerson2().getLearning().getFormationArea()) {
					contadorVP++;					
				} else {
					contadorFP++;
				}
			}
		}
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n |        Resultado da Lista de Colaboradores       |");
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n   - Verdadeiros Positivos: " + contadorVP);
		System.out.println("\n   - Falsos Positivos: " + contadorFP);
		//System.out.println("\n   - M�trica Precision: " + (contadorVP/contadorVP+contadorFP));
		//System.out.println("\n   - False Positive Rate: " + (contadorFP/(contadorFP+200-contadorVP)));
		System.out.println("\n ----------------------------------------------------");
	}

	private void analisaListasDeConteudos(int tipoLista) {
		
		
		List<Person> persons = iUbi.getAllPerson();
		
		this.sizePerson = persons.size();

		this.contadorVP = 0;
		this.contadorFP = 0;			
		
		for(int i = 0; i < sizePerson; i++){
 
			List<RelatePersonContent> contents = persons.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED);			
			
			for(int j = 0; j < contents.size(); j++){
				if((contents.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_PONDER && tipoLista == 1) ||
				   (contents.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONTENT && tipoLista == 2)) {					
					if (Math.abs(persons.get(i).getSS1() - contents.get(j).getContent().getSS1()) > 3 ||
						Math.abs(persons.get(i).getSS2() - contents.get(j).getContent().getSS2()) > 3 )//||
					//	Math.abs(persons.get(i).getSS3() - contents.get(j).getContent().getSS3()) > 3 ||
					//	Math.abs(persons.get(i).getSS4() - contents.get(j).getContent().getSS4()) > 3 ) 
					{
						contadorFP++;					
					} else {
						contadorVP++;
					}
				}
			}
		}
		String temp = "";
		if(tipoLista == 1) temp = "Colaborativa Ponderada     |";
		if(tipoLista == 2) temp = "Baseada em Conte�do        |";
		if(tipoLista == 3) temp = "Ub�qua			          |";
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n |     Resultado da Lista "+ temp);
		System.out.println("\n ----------------------------------------------------");
		System.out.println("\n   - Verdadeiros Positivos: " + contadorVP);
		System.out.println("\n   - Falsos Positivos: " + contadorFP);
		//System.out.println("\n   - M�trica Precision: " + (contadorVP/contadorVP+contadorFP));
		//System.out.println("\n   - False Positive Rate: " + (contadorFP/(contadorFP+200-contadorVP)));
		System.out.println("\n ----------------------------------------------------");
	}

	private void analisaListasDeConteudos(int tipoLista, Person person, ArrayList<RelatePersonContent> recomendacao) {
			
		for(int j = 0; j < recomendacao.size(); j++){
			if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_UBIQ && tipoLista == 3){					
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) 
				{
					contadorFPU++;					
				} else {
					contadorVPU++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_HYBRID && tipoLista == 4)
			{
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) 
				{
					this.contadorFPH++;					
				} else {
					this.contadorVPH++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONJUNTA && tipoLista == 5){
				if (Math.abs(person.getSS1() - recomendacao.get(j).getContent().getSS1()) > 3 ||
					Math.abs(person.getSS2() - recomendacao.get(j).getContent().getSS2()) > 3 )/*||
					Math.abs(person.getSS3() - recomendacao.get(j).getContent().getSS3()) > 3 ||
					Math.abs(person.getSS4() - recomendacao.get(j).getContent().getSS4()) > 3 ) 
				{
					contadorFPC++;					
				} else {
					contadorVPC++;
				}
			}
		}
	}
	
	private void analisaListasDeConteudos(int tipoLista, int i, ArrayList<RelatePersonContent> recomendacao) {
		
		for(int j = 0; j < recomendacao.size(); j++) {
			if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_UBIQ && tipoLista == 3){
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					contadorFPU++;					
				} else {
					contadorVPU++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_HYBRID && tipoLista == 4)
			{
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					this.contadorFPH++;					
				} else {
					this.contadorVPH++;
				}
			}
			else if(recomendacao.get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONJUNTA && tipoLista == 5){
				if (i < 20 &&  recomendacao.get(j).getContent().getSS1() > 6 ||
					i >=20 && i < 40 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=40 && i < 60 &&  recomendacao.get(j).getContent().getSS2() > 6 ||
					i >=60 && i < 80 &&  recomendacao.get(j).getContent().getSS2() > 6 )
				{
					contadorFPC++;					
				} else {
					contadorVPC++;
				}
			}
		}
	}
	
	private void imprime(int tipoLista){

		if(tipoLista == 3) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Lista Baseada na Ubiquidade     |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPU);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPU);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}
		else if(tipoLista == 4) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Recomenda��o H�brida         |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPH);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPH);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}
		else if(tipoLista == 5) {
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n |     Resultado da Recomenda��o Conjunta        |");
			System.out.println("\n ----------------------------------------------------");
			System.out.println("\n   - Verdadeiros Positivos: " + this.contadorVPC);
			System.out.println("\n   - Falsos Positivos: " + this.contadorFPC);
			//System.out.println("\n   - M�trica Precision: " + (contadorVP/(contadorVP+contadorFP)));
			//System.out.println("\n   - False Positive Rate:: " + (contadorFP/(contadorFP+200-contadorVP)));
			System.out.println("\n ----------------------------------------------------");
		}

	}
	
	private Context generateContexto(Person person, Content content, Location location, int status) {
		Context context = new Context();
		context.setPerson(person);
		context.setLocation(location);
		context.setContent(null);
		context.setDevice(new Device());
		context.setTimeStamp(null);
		context.setBattery(50);
		context.setConnection(Context.CONNECTION_WIFI);
		context.setConnectionSpeed(300);		
		context.setPersonSpeed(0);
		context.setType(status);
				
		return context;
	}


	public int getIdPerson() {
		return idPerson;
	}


	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

}
  
 
 
  
  */

