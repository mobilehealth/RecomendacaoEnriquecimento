/*package mobilehealth.prc.recommendation;

import java.util.List;

import javax.swing.JOptionPane;

import com.sun.codemodel.JOp;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;

public class BaseadaConteudo 
{
	private IData iData = DataEclipseLink.getInstance();
	
	public boolean gerarListaBaseadaConteudo (int tamanhoDaListaBaseadaConteudo, int idPerson)
	{			
		boolean flagOK = true;
		
		int personSize = iData.getCountPerson();
		int contentSize = iData.getCountContent();
		
		float aparenciaFinal[] = new float[contentSize];
		
		List<Person> listPerson = iData.getAllPerson();
		List<Content> listContent = iData.getAllContent();
		
		// Procura na lista de pessoas o usuario
		int contadorPessoas = 0;
		while(listPerson.get(contadorPessoas).getId() != idPerson){
					
			contadorPessoas++;
					
		}

		Person person = listPerson.get(contadorPessoas);
			
			for(int j = 0; j < listContent.size(); j++)
			{		
				Content content = listContent.get(j);
				
				aparenciaFinal[j] = 0;
				
				float aparenciaMetaDados = 0;
				float aparenciaTags = 0;
				float aparenciaLocais = 0;
				float aparenciaFrequencia = 0;
				float aparenciaSemantica = 0;
				float aparenciaHistorico = 0;
				
				for(int k = 0; k < person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); k++) 
				{	
					if(listContent.get(j).getType() == person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getType()) aparenciaMetaDados+= 1;
					if(listContent.get(j).getSubtype() == person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getSubtype()) aparenciaMetaDados+=1;
					
					aparenciaMetaDados -= Math.abs(listContent.get(j).getAge() - person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getAge());
					
					if(listContent.get(j).getAuthor() == person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getAuthor()) aparenciaMetaDados+=30;
					
					aparenciaMetaDados -= Math.abs(listContent.get(j).getBytesOnline() - person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getBytesOnline())/10000;
					aparenciaMetaDados -= Math.abs(listContent.get(j).getSecondsOnline() - person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getSecondsOnline())/1000;					
				}				
				
				aparenciaMetaDados += listContent.get(j).getRateColabPonder();
				
				aparenciaMetaDados += listContent.get(j).getRateAcceptance();
				
				//Analisa Tags
				for(int k = 0; k < person.getListRelatePersonTag().size(); k++) {
					for(int l = 0; l < listContent.get(j).getListRelateContentTag().size(); l++) {
						if(person.getListRelatePersonTag().get(k).getTag().getName() == listContent.get(j).getListRelateContentTag().get(l).getTag().getName())
							aparenciaTags += (person.getListRelatePersonTag().get(k).getCountRel() + listContent.get(j).getListRelateContentTag().get(l).getCountRel())/2;
						
					}
				}

				//Analisa Locations
				for(int k = 0; k < person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); k++) {
					for(int l = 0; l < listContent.get(j).getListRelateContentLocation().size(); l++) {
						if(person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(k).getLocation().getId() == listContent.get(j).getListRelateContentLocation().get(l).getLocation().getId())
							aparenciaLocais++;
					}
				}
		
				//Analisa Frequency
				for(int k = 0; k < 24; k++) {
					aparenciaFrequencia -= Math.abs(person.getFrequency().getH24PositiveV()[k] - listContent.get(j).getFrequency().getH24PositiveV()[k]/24);
					aparenciaFrequencia -= Math.abs(person.getFrequency().getH24NegativeV()[k] - listContent.get(j).getFrequency().getH24NegativeV()[k]/24);
				}
				for(int k = 0; k < 7; k++) {
					aparenciaFrequencia -= Math.abs(person.getFrequency().getH7PositiveV()[k] - listContent.get(j).getFrequency().getH7PositiveV()[k]/7);	
					aparenciaFrequencia -= Math.abs(person.getFrequency().getH7NegativeV()[k] - listContent.get(j).getFrequency().getH7NegativeV()[k]/7);
					//aparenciaFrequencia -= Math.abs(person.getFrequency().getH7PositiveV()[k] - listContent.get(j).getFrequency().getH7PositiveV()[k]/24);	// TODO DIVISÃO ALTERADA POR 7
					//aparenciaFrequencia -= Math.abs(person.getFrequency().getH7NegativeV()[k] - listContent.get(j).getFrequency().getH7NegativeV()[k]/24);
				}

				aparenciaFrequencia = aparenciaFrequencia/62;
				//TODO: USAR PARA CHAMAR A CLASSE SACExternalServiceImpl
				SemelhancaSemantica SS = new SemelhancaSemantica();
				

				aparenciaSemantica = SS.getSemelhancaSemanticaUsuarioConteudo(person, listContent.get(j));
								
				//----------------------------------------------------------------------------------------------
				//Analisar aparência no histórico de conteúdos do usuário
				//----------------------------------------------------------------------------------------------

				aparenciaFinal[j] = aparenciaMetaDados + aparenciaTags + aparenciaLocais + aparenciaFrequencia + aparenciaSemantica;
				
			}
			
			//Normatização dos Valores
			{
				float menor = 10000;
				float maior = -10000;		
				
				for(int j = 0; j < contentSize; j++)
				{
					if(aparenciaFinal[j] < menor) 
					{
						menor = aparenciaFinal[j];
					}
					if(aparenciaFinal[j] > maior) 
					{
						maior = aparenciaFinal[j];					
					}
				}

				for(int j = 0; j < contentSize; j++)
				{ //TODO: CÁLCULO PARA SABER O QUANTO UM CONTEÚDO SE RELACIONA COM UM DOMÍNIO.					
						aparenciaFinal[j] = (float) 10*(aparenciaFinal[j] + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1);					
				}				
			}			
		
			//Escolha das lista de conteúdos mais adequados						
			for (int k = 0; k < tamanhoDaListaBaseadaConteudo; k++)
			{
				float maiorAparencia = -100000;
				int iMaior = -1;
				int jMaior = -1;
				for(int j = 0; j < listContent.size(); j++){
					if(aparenciaFinal[j] > maiorAparencia) {
						maiorAparencia = aparenciaFinal[j];
						jMaior = j;
					}				
				}
				if(iMaior != -1) {
					RelatePersonContent recommendedRelationContent = new RelatePersonContent();
					recommendedRelationContent.setPerson(person);
					recommendedRelationContent.setContent(listContent.get(jMaior));	
					recommendedRelationContent.setRatePerson(aparenciaFinal[jMaior]);
					recommendedRelationContent.setI2(0);
					recommendedRelationContent.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
					recommendedRelationContent.setSuggestedMethod(RelatePersonContent.SUGG_METH_CONTENT);
					aparenciaFinal[jMaior] = -1000;
					person.getListRelatePersonContent().add(recommendedRelationContent);
				
				}
			}
			
			// simulacao.setPerson(listPerson.get(i));
		//}
				
		return flagOK;
	}

}

*/


package mobilehealth.prc.recommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;

public class BaseadaConteudo 
{
	private IData iData = DataEclipseLink.getInstance();
	
	public boolean gerarListaBaseadaConteudo (int tamanhoDaListaBaseadaConteudo)
	{			
		boolean flagOK = true;
		
		
		List<Integer> minhaLista = new ArrayList<Integer>();
		Connection c = null;
		Statement stmt = null;
		String sql = "select p.id as id_person from public.person p where p.id not in(select r.id_person from public.recommendation r where r.visited = false group by r.id_person having count(*) > 2 ) and p.id not in(select u.person_id from app.users u where type = true)";
		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mobilehealth2","postgres", "postgres");
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			 // Cria uma ArrayLista, onde os valores serão adicionados e enviados atrazes do return
	       
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
		
		int personSize = iData.getCountPerson();
		
		List<Person> listPerson = new ArrayList<Person>();
		List<Person> listPerson2 = iData.getAllPerson();
		List<Content> listContent = iData.getAllContent();
		

		for(int b = 0; b < minhaLista.size(); b++){
			for(int a = 0; a < listPerson2.size(); a++){
				if(minhaLista.get(b) == listPerson2.get(a).getId()){
					System.out.println(a + " - "  +listPerson2.get(a).getId());
					listPerson.add(listPerson2.get(a));
					break;
				}
			}
		}
		
		int contentSize = iData.getCountContent();
		personSize = listPerson.size();
		float aparenciaFinal[][] = new float[personSize][contentSize];
		
		System.out.println("ListContent  = " + listContent.size());
		System.out.println("ListPerson  = " + listPerson.size());
		
		for(int i = 0; i < listPerson.size(); i++)
		{
			Person person = listPerson.get(i);
			
			for(int j = 0; j < listContent.size(); j++) 
			{		
				//Content content = listContent.get(i); -- Alterado por Jersseon 10-11-2015
				Content content = listContent.get(j);
				System.out.println("\ni = " + i + "\nj = " + j);
				aparenciaFinal[i][j] = 0;
				
				float aparenciaMetaDados = 0;
				float aparenciaTags = 0;
				float aparenciaLocais = 0;
				float aparenciaFrequencia = 0;
				float aparenciaSemantica = 0;
				float aparenciaHistorico = 0;
				
				
				
				//JOptionPane.showConfirmDialog(null, "tamanho  " + listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size());
				for(int k = 0; k < listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); k++) 
				{	
					
					
					
					//JOptionPane.showConfirmDialog(null, "Tipo " + listContent.get(j).getType());
					if(listContent.get(j).getType() == listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getType()) 
						aparenciaMetaDados+= 1;
					
					if(listContent.get(j).getSubtype() == listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getSubtype()) 
						aparenciaMetaDados+=1;
					
					aparenciaMetaDados -= Math.abs(listContent.get(j).getAge() - listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getAge());
					
					
					if(listContent.get(j).getAuthor() == listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getAuthor())
						aparenciaMetaDados+=30;
					
					aparenciaMetaDados -= Math.abs(listContent.get(j).getBytesOnline() - listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getBytesOnline())/10000;
					aparenciaMetaDados -= Math.abs(listContent.get(j).getSecondsOnline() - listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getSecondsOnline())/1000;					
				}				
				
				aparenciaMetaDados += listContent.get(j).getRateColabPonder();
				
				aparenciaMetaDados += listContent.get(j).getRateAcceptance();
				
				//Analisa Tags
				for(int k = 0; k < listPerson.get(i).getListRelatePersonTag().size(); k++) {
					
					for(int l = 0; l < listContent.get(j).getListRelateContentTag().size(); l++) {
						
						if(listPerson.get(i).getListRelatePersonTag().get(k).getTag().getName() == listContent.get(j).getListRelateContentTag().get(l).getTag().getName())
							aparenciaTags += (listPerson.get(i).getListRelatePersonTag().get(k).getCountRel() + listContent.get(j).getListRelateContentTag().get(l).getCountRel())/2;
						
					}
				}

				//Analisa Locations
				for(int k = 0; k < listPerson.get(i).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); k++) {
					for(int l = 0; l < listContent.get(j).getListRelateContentLocation().size(); l++) {
						if(listPerson.get(i).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(k).getLocation().getId() == listContent.get(j).getListRelateContentLocation().get(l).getLocation().getId())
							aparenciaLocais++;
					}
				}
		
				
				
				
				
				
				//Analisa Frequency
				
				if(listPerson.get(i).getFrequency() == null){
					
				} else {
				
					for(int k = 0; k < 24; k++)
					{
							if(listPerson.get(i).getFrequency().getH24PositiveV()[k] == null){
								
							} else {
								
								if(listContent.get(j).getFrequency().getH24PositiveV()[k] == null){
								
								} else {
									aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH24PositiveV()[k] - listContent.get(j).getFrequency().getH24PositiveV()[k]/24);
									aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH24NegativeV()[k] - listContent.get(j).getFrequency().getH24NegativeV()[k]/24);
								}
								
							}	
					}
					
					for(int k = 0; k < 7; k++) 
					{
						if(listPerson.get(i).getFrequency().getH7PositiveV() != null){
							if(listPerson.get(i).getFrequency().getH7NegativeV()[k] != null){
									
								aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7PositiveV()[k] - listContent.get(j).getFrequency().getH7PositiveV()[k]/7);	
								aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7NegativeV()[k] - listContent.get(j).getFrequency().getH7NegativeV()[k]/7);
								//aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7PositiveV()[k] - listContent.get(j).getFrequency().getH7PositiveV()[k]/24);	// TODO DIVISÃO ALTERADA POR 7
								//aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7NegativeV()[k] - listContent.get(j).getFrequency().getH7NegativeV()[k]/24);
							}
						}
					}
					
				
				}

				
				aparenciaFrequencia = aparenciaFrequencia/62;
				//TODO: USAR PARA CHAMAR A CLASSE SACExternalServiceImpl
				SemelhancaSemantica SS = new SemelhancaSemantica();
				

				aparenciaSemantica = SS.getSemelhancaSemanticaUsuarioConteudo(listPerson.get(i), listContent.get(j));
								
				//----------------------------------------------------------------------------------------------
				//Analisar aparência no histórico de conteúdos do usuário
				//----------------------------------------------------------------------------------------------				
				
				
				for(int k =0; k < listPerson.get(i).getRelatesPersonContet().size(); k++){
					
					if(listContent.get(j).getId() == listPerson.get(i).getRelatesPersonContet().get(k).getContent().getId()){
						
						System.out.println("\n>>>>>>>>>>>>>>>> Achei!!!!!! " + listContent.get(j).getId());
						aparenciaFinal[i][j] = aparenciaFinal[i][j] - 15;
					}
					
				}
				
				
				aparenciaFinal[i][j] = aparenciaMetaDados + aparenciaTags + aparenciaLocais + aparenciaFrequencia + aparenciaSemantica;
				
			}
			
			
			
			
			
			
			
			
			
			//Normatização dos Valores
			{
				float menor = 10000;
				float maior = -10000;		
				
				for(int j = 0; j < contentSize; j++)
				{
					if(aparenciaFinal[i][j] < menor) 
					{
						menor = aparenciaFinal[i][j];
					}
					if(aparenciaFinal[i][j] > maior) 
					{
						maior = aparenciaFinal[i][j];					
					}
				}

				for(int j = 0; j < contentSize; j++)
				{ //TODO: CÁLCULO PARA SABER O QUANTO UM CONTEÚDO SE RELACIONA COM UM DOMÍNIO.					
						aparenciaFinal[i][j] = 10*(aparenciaFinal[i][j] + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1);					
				}				
			}			
		
			//Escolha das lista de conteúdos mais adequados						
			for (int k = 0; k < tamanhoDaListaBaseadaConteudo; k++)
			{
				float maiorAparencia = -100000;
				int iMaior = -1;
				int jMaior = -1;
				for(int j = 0; j < listContent.size(); j++){
					if(aparenciaFinal[i][j] > maiorAparencia) {
						maiorAparencia = aparenciaFinal[i][j];
						iMaior = i;
						jMaior = j;
					}				
				}
				if(iMaior != -1) {
					RelatePersonContent recommendedRelationContent = new RelatePersonContent();
					recommendedRelationContent.setPerson(person);
					recommendedRelationContent.setContent(listContent.get(jMaior));	
					recommendedRelationContent.setRatePerson(aparenciaFinal[iMaior][jMaior]);
					recommendedRelationContent.setI2(0);
					recommendedRelationContent.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
					recommendedRelationContent.setSuggestedMethod(RelatePersonContent.SUGG_METH_CONTENT);
					aparenciaFinal[iMaior][jMaior] = -1000;
					listPerson.get(i).getListRelatePersonContent().add(recommendedRelationContent);
				
				}
			}
			
			// simulacao.setPerson(listPerson.get(i));
		}
				
		return flagOK;
	}

}



