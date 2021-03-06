/*

package mobilehealth.prc.recommendation;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.codemodel.JOp;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.recommendation.SemelhancaSemantica;


public class ColaborativaPonderada 
{
	private IData iData = DataEclipseLink.getInstance();
	

	public boolean gerarListaColaborativaPonderada(int tamanhoDaListaColaboradores, int tamanhoDaListaColaborativa,  int idPerson)
	{
		boolean flagOK = true;

		gerarListaColaboradores(tamanhoDaListaColaboradores, idPerson);
		gerarListaColaborativa(tamanhoDaListaColaborativa);
		
		return flagOK;
	}
	
	public boolean gerarListaColaboradores(int tamanhoDaListaColaboradores,  int idPerson)
	{
		boolean flagOK = true;
		
		int personSize = iData.getCountPerson();
		
		float aparenciaFinal[] = new float[personSize];

		List<Person> listPerson = iData.getAllPerson();
		

		// Procura na lista de pessoas o usuario
		int contadorPessoas = 0;
		JOptionPane.showMessageDialog(null, "1 " +idPerson);
		while(listPerson.get(contadorPessoas).getId() != idPerson){
							
			contadorPessoas++;
							
		}

		Person person = listPerson.get(contadorPessoas);
		
		
		
		for(int j = 0; j < listPerson.size(); j++)
			{
				if( contadorPessoas == j)
				{
					aparenciaFinal[j] = -1000;
				
				} else
				{	
					int aparenciaMetaDados = 0;
					int aparenciaAmigos = 0;
					int aparenciaConteudos = 0;
					int aparenciaLocais = 0;
					int aparenciaFrequencia = 0;
					float aparenciaSemantica = 0;
					
					aparenciaMetaDados -= Math.abs(person.getAge() - listPerson.get(j).getAge())/2;
					
					aparenciaMetaDados -= Math.abs(person.getGender() - listPerson.get(j).getGender()*2);
					
					aparenciaMetaDados -= Math.abs(person.getIncome() - listPerson.get(j).getIncome())/500;
					
					if (person.getReligion() == listPerson.get(j).getReligion()) aparenciaMetaDados += 2;
									
					if (person.getDisease() == listPerson.get(j).getDisease()) aparenciaMetaDados += 10;
					
					aparenciaMetaDados -= Math.abs(person.getLearning().getEducationalLevel() - listPerson.get(j).getLearning().getEducationalLevel())*2;
					
					if (person.getLearning().getEstiloDimenOrganizacao() == listPerson.get(j).getLearning().getEstiloDimenOrganizacao()) aparenciaMetaDados += 2;
					
					if (person.getLearning().getEstiloDimenPercepcao() == listPerson.get(j).getLearning().getEstiloDimenPercepcao()) aparenciaMetaDados += 2;
					
					if (person.getLearning().getEstiloDimenProcessamento() == listPerson.get(j).getLearning().getEstiloDimenProcessamento()) aparenciaMetaDados += 2;
					
					if (person.getLearning().getEstiloDimenRetencao() == listPerson.get(j).getLearning().getEstiloDimenRetencao()) aparenciaMetaDados += 2;
					
					if (person.getLearning().getFormationArea() == listPerson.get(j).getLearning().getFormationArea()) aparenciaMetaDados += 8;
										
					if (person.getLearning().getFormationSubarea() == listPerson.get(j).getLearning().getFormationSubarea()) aparenciaMetaDados +=16;
										
					// Analise de amigos coincidentes
					for(int k = 0; k < person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).size(); k++) 
					{
						for(int l = 0; l < listPerson.get(j).getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).size(); l++) 
						{	
							if (person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(k).getPerson2().getId() == listPerson.get(j).getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(l).getPerson2().getId()) aparenciaAmigos++;
						}
					}
					// Analise de conteudos coincidentes
					for(int k = 0; k < person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); k++) 
					{
						for(int l = 0; l < listPerson.get(j).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); l++) 
						{	
							if (person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getId() == listPerson.get(j).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(l).getContent().getId()) aparenciaConteudos++;
						}
					}
					// Analise de locais conincidentes
					for(int k = 0; k < person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); k++) 
					{
						for(int l = 0; l < listPerson.get(j).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); l++) 
						{	
							if (person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(k).getLocation().getId() == listPerson.get(j).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(l).getLocation().getId()) aparenciaLocais++;
						}
					}

					//Analisa Frequency
					for(int k = 0; k < 24; k++)
					{
						aparenciaFrequencia -= Math.abs(person.getFrequency().getH24PositiveV()[k] - listPerson.get(j).getFrequency().getH24PositiveV()[k]/24);
						aparenciaFrequencia -= Math.abs(person.getFrequency().getH24NegativeV()[k] - listPerson.get(j).getFrequency().getH24NegativeV()[k]/24);
					}
					for(int k = 0; k < 7; k++) 
					{
						aparenciaFrequencia -= Math.abs(person.getFrequency().getH7PositiveV()[k] - listPerson.get(j).getFrequency().getH7PositiveV()[k]/7); 
						aparenciaFrequencia -= Math.abs(person.getFrequency().getH7NegativeV()[k] - listPerson.get(j).getFrequency().getH7NegativeV()[k]/7);
						//aparenciaFrequencia -= Math.abs(person.getFrequency().getH7PositiveV()[k] - listPerson.get(j).getFrequency().getH7PositiveV()[k]/24); 
						//aparenciaFrequencia -= Math.abs(person.getFrequency().getH7NegativeV()[k] - listPerson.get(j).getFrequency().getH7NegativeV()[k]/24);
					}

					aparenciaFrequencia = aparenciaFrequencia/62;
					
					//TODO: USAR PARA CHAMAR A CLASSE SACExternalServiceImpl
					SemelhancaSemantica SS = new SemelhancaSemantica();
					//aparenciaSemantica = SS.getSemelhancaSemantica(1, listPerson.get(i).getId(), listPerson.get(j).getId());
					
					aparenciaSemantica = SS.getSemelhancaSemanticaUsuarios(person, listPerson.get(j));
					
					//TODO: OBTER AS AFINIDADES DE DOIS USU�RIOS COM CADA UM DOS DOM�NIOS INSTALADOS.				
					aparenciaFinal[j] = aparenciaMetaDados + aparenciaAmigos + aparenciaConteudos + aparenciaLocais + aparenciaFrequencia +aparenciaSemantica;					

				}
			}
			//Normatiza��o dos Valores
			{
				float menor = 10000;
				float maior = -10000;

				for(int j = 0; j < personSize; j++)
				{
					if(contadorPessoas != j){
						if(aparenciaFinal[j] < menor) 
						{
							menor = aparenciaFinal[j];
						}
						if(aparenciaFinal[j] > maior) 
						{
							maior = aparenciaFinal[j];
						}
					}
				}
							
				for(int j = 0; j < personSize; j++)
				{
					if(contadorPessoas != j)
					{ //TODO: C�LCULO PARA SABER O QUANTO UM USU�RIO SE INTERESSA POR UM DOM�NIO
						aparenciaFinal[j] = 10*(aparenciaFinal[j] + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1);
					}
				}				
			}
			
			//Sele��o dos melhores valores e adi��o � lista
			for (int k = 0; k < tamanhoDaListaColaboradores; k++){
				float maiorAparencia = -1000;
				int iMaior = -1;
				int jMaior = -1;
				
				for(int j = 0; j < personSize; j++)
				{
					if(aparenciaFinal[j] > maiorAparencia) 
					{
						maiorAparencia = aparenciaFinal[j];
						
						jMaior = j;
					}				
				}
				if(iMaior != -1) {
					RelatePersonPerson recommendedRelationPerson = new RelatePersonPerson();
					
					recommendedRelationPerson.setPerson1(person);
					recommendedRelationPerson.setPerson2(listPerson.get(jMaior));
					recommendedRelationPerson.setAffinityRate(aparenciaFinal[jMaior]);					
					aparenciaFinal[jMaior] = -1000;
					// TODO Conforme as demais classes, foi preciso adicionar o status STATUS_SUGGESTED para informar que se refere a lista de pessoas recomendadas.
					recommendedRelationPerson.setStatus(RelatePersonPerson.STATUS_COLLABS);					
					person.getListRelatePersonPerson().add(recommendedRelationPerson);
				}
			}
			// TODO Essa lista N�O precisa ser persistida
			//simulacao.setPerson(listPerson.get(i))
		return flagOK;
	}

	public boolean gerarListaColaborativa(int tamanhoDaListaColaborativa)
	{
		boolean flagOK = true;
		
		List<Person> listPerson = iData.getAllPerson();
		
		for(int i = 0; i < listPerson.size(); i++){

			ArrayList<RelatePersonContent> listTemporaria = new ArrayList<RelatePersonContent>();					
			
			List<RelatePersonPerson> Temp1 = listPerson.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_COLLABS);
			// getListRecommendedPersons 
			for(int j = 0; j < Temp1.size(); j++){
				if (i != j){
					List<RelatePersonContent> Temp2 = Temp1.get(j).getPerson2().getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE); 
					for(int k = 0; k < Temp2.size(); k++) {
						
						boolean jaExiste = false;
												
						for(int l = 0; l < listTemporaria.size(); l++){
							
							if(listTemporaria.get(l).getContent().getId() == Temp2.get(k).getContent().getId()){

								float rateTemporario = (listTemporaria.get(l).getRatePerson()*listTemporaria.get(l).getCountSuggested() + Temp1.get(j).getAffinityRate() * Temp2.get(k).getRatePerson())/(listTemporaria.get(l).getCountSuggested() + 1); 
								
								listTemporaria.get(l).setCountSuggested(listTemporaria.get(l).getCountSuggested() + 1);
								
								listTemporaria.get(l).setRatePerson(rateTemporario);
								
								jaExiste = true;
							
							}
						}						
						
						if(!jaExiste){
							RelatePersonContent relationWithContent = new RelatePersonContent();
							relationWithContent.setPerson(listPerson.get(i));
							relationWithContent.setContent(Temp2.get(k).getContent());
							relationWithContent.setRatePerson(Temp1.get(j).getAffinityRate() * Temp2.get(k).getRatePerson());			
							relationWithContent.setCountSuggested(1);
							
							relationWithContent.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
							relationWithContent.setSuggestedMethod(RelatePersonContent.SUGG_METH_PONDER);
							
							listTemporaria.add(relationWithContent);							
						}
					}
				}
			}

			//Normatiza��o dos Valores
			{
				float menor = 10000;
				float maior = -10000;
				for(int j = 0; j < listTemporaria.size(); j++){
					if(i != j){
						if(listTemporaria.get(j).getRatePerson() < menor) {
							menor = listTemporaria.get(j).getRatePerson();
						}
						if(listTemporaria.get(j).getRatePerson() > maior) {
							maior = listTemporaria.get(j).getRatePerson();
						}
					}
				}
							
				for(int j = 0; j < listTemporaria.size(); j++){
					if(i != j){
						listTemporaria.get(j).setRatePerson(10*(listTemporaria.get(j).getRatePerson() + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1));
					}
				}				
			}
			
			// Escolha dos melhores resultados
			for (int k = 0; k < tamanhoDaListaColaborativa; k++){
				float maiorIndice = -1000;
				int kMaior = -1;
				for(int l = 0; l < listTemporaria.size(); l++){
								
					if(listTemporaria.get(l).getRatePerson() > maiorIndice) {
						maiorIndice = listTemporaria.get(l).getRatePerson();
						kMaior = l;					
					}												
				}
				if(kMaior != -1) {
					//listPerson.get(i).addRecommendedContent(listTemporaria.get(kMaior));
					listPerson.get(i).getListRelatePersonContent().add(listTemporaria.get(kMaior));
					listTemporaria.remove(kMaior);
				}
			}
			
			//simulacao.setPerson(listPerson.get(i));
			
		}		
		
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

import javax.swing.border.EmptyBorder;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.recommendation.SemelhancaSemantica;


public class ColaborativaPonderada 
{
	private IData iData = DataEclipseLink.getInstance();
	


	public boolean gerarListaColaborativaPonderada(int tamanhoDaListaColaboradores, int tamanhoDaListaColaborativa)
	{
		boolean flagOK = true;

		gerarListaColaboradores(tamanhoDaListaColaboradores);
		gerarListaColaborativa(tamanhoDaListaColaborativa);
		
		return flagOK;
	}
	
	public boolean gerarListaColaboradores(int tamanhoDaListaColaboradores)
	{
		boolean flagOK = true;
		List<Person> listPerson = new ArrayList<Person>();
		List<Person> listPerson2 = iData.getAllPerson();
		
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
					//System.out.println(a + " - "  +listPerson2.get(a).getId());
					listPerson.add(listPerson2.get(a));
					break;
				}
			}
		}
		
	
		int personSize = listPerson.size();
		
		float aparenciaFinal[][] = new float[personSize][personSize];

		
		for(int i = 0; i < listPerson.size(); i++)
		{
			for(int j = 0; j < listPerson.size(); j++)
			{
				if( i == j)
				{
					aparenciaFinal[i][j] = -1000;
				}
				else
				{	
					int aparenciaMetaDados = 0;
					int aparenciaAmigos = 0;
					int aparenciaConteudos = 0;
					int aparenciaLocais = 0;
					int aparenciaFrequencia = 0;
					float aparenciaSemantica = 0;
					
					aparenciaMetaDados -= Math.abs(listPerson.get(i).getAge() - listPerson.get(j).getAge())/2;
					
					aparenciaMetaDados -= Math.abs(listPerson.get(i).getGender() - listPerson.get(j).getGender()*2);
					
					aparenciaMetaDados -= Math.abs(listPerson.get(i).getIncome() - listPerson.get(j).getIncome())/500;
					
					if (listPerson.get(i).getReligion() == listPerson.get(j).getReligion()) 
						aparenciaMetaDados += 2;
									
					if (listPerson.get(i).getDisease() == listPerson.get(j).getDisease()) 
						aparenciaMetaDados += 10;
					
					//TODO Alisson
					/* 
					aparenciaMetaDados -= Math.abs(listPerson.get(i).getLearning().getEducationalLevel() - listPerson.get(j).getLearning().getEducationalLevel())*2;
					
					if (listPerson.get(i).getLearning().getEstiloDimenOrganizacao() == listPerson.get(j).getLearning().getEstiloDimenOrganizacao()) 
						aparenciaMetaDados += 2;
					
					if (listPerson.get(i).getLearning().getEstiloDimenPercepcao() == listPerson.get(j).getLearning().getEstiloDimenPercepcao()) 
						aparenciaMetaDados += 2;
					
					if (listPerson.get(i).getLearning().getEstiloDimenProcessamento() == listPerson.get(j).getLearning().getEstiloDimenProcessamento()) 
						aparenciaMetaDados += 2;
					
					if (listPerson.get(i).getLearning().getEstiloDimenRetencao() == listPerson.get(j).getLearning().getEstiloDimenRetencao()) 
						aparenciaMetaDados += 2;
					
					if (listPerson.get(i).getLearning().getFormationArea() == listPerson.get(j).getLearning().getFormationArea()) 
						aparenciaMetaDados += 8;
										
					if (listPerson.get(i).getLearning().getFormationSubarea() == listPerson.get(j).getLearning().getFormationSubarea()) 
						aparenciaMetaDados +=16;
					*/
					
					// Analise de amigos coincidentes
					
					for(int k = 0; k < listPerson.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).size(); k++) 
					{
						for(int l = 0; l < listPerson.get(j).getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).size(); l++) 
						{	
							if (listPerson.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(k).getPerson2().getId() == listPerson.get(j).getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(l).getPerson2().getId()) 
								aparenciaAmigos++;
						}
					}
					
					// Analise de conteudos coincidentes
					for(int k = 0; k < listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); k++) 
					{
						for(int l = 0; l < listPerson.get(j).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); l++) 
						{	
							if (listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getId() == listPerson.get(j).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(l).getContent().getId()) 
								aparenciaConteudos++;
						}
					}
					
					// Analise de locais conincidentes
					for(int k = 0; k < listPerson.get(i).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); k++) 
					{
						for(int l = 0; l < listPerson.get(j).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); l++) 
						{	
							if (listPerson.get(i).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(k).getLocation().getId() == listPerson.get(j).getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(l).getLocation().getId()) 
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
									
									if(listPerson.get(j).getFrequency().getH24PositiveV()[k] == null){
									
									} else {
										
										aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH24PositiveV()[k] - listPerson.get(j).getFrequency().getH24PositiveV()[k]/24);
										aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH24NegativeV()[k] - listPerson.get(j).getFrequency().getH24NegativeV()[k]/24);
									
										
										
									}
									
								}	
						}
						
						for(int k = 0; k < 7; k++) 
						{
	
							if(listPerson.get(i).getFrequency().getH7PositiveV()[k] != null){
								
								if(listPerson.get(j).getFrequency().getH7PositiveV()[k] != null){
									
									aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7PositiveV()[k] - listPerson.get(j).getFrequency().getH7PositiveV()[k]/7); 
									aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7NegativeV()[k] - listPerson.get(j).getFrequency().getH7NegativeV()[k]/7);
									aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7PositiveV()[k] - listPerson.get(j).getFrequency().getH7PositiveV()[k]/24); 
									aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7NegativeV()[k] - listPerson.get(j).getFrequency().getH7NegativeV()[k]/24);
								
								}
							}
						}
						
					
					}
					
					
					aparenciaFrequencia = aparenciaFrequencia/62;
					
					//TODO: USAR PARA CHAMAR A CLASSE SACExternalServiceImpl
					SemelhancaSemantica SS = new SemelhancaSemantica();
					//aparenciaSemantica = SS.getSemelhancaSemantica(1, listPerson.get(i).getId(), listPerson.get(j).getId());
					
					aparenciaSemantica = SS.getSemelhancaSemanticaUsuarios(listPerson.get(i), listPerson.get(j));
					
					//TODO: OBTER AS AFINIDADES DE DOIS USU�RIOS COM CADA UM DOS DOM�NIOS INSTALADOS.				
					aparenciaFinal[i][j] = aparenciaMetaDados + aparenciaAmigos + aparenciaConteudos + aparenciaLocais + aparenciaFrequencia +aparenciaSemantica;					

				}
			}
			//Normatiza��o dos Valores
			{
				float menor = 10000;
				float maior = -10000;

				for(int j = 0; j < personSize; j++)
				{
					if(i != j){
						if(aparenciaFinal[i][j] < menor) 
						{
							menor = aparenciaFinal[i][j];
						}
						if(aparenciaFinal[i][j] > maior) 
						{
							maior = aparenciaFinal[i][j];
						}
					}
				}
							
				for(int j = 0; j < personSize; j++)
				{
					if(i != j)
					{ //TODO: C�LCULO PARA SABER O QUANTO UM USU�RIO SE INTERESSA POR UM DOM�NIO
						aparenciaFinal[i][j] = 10*(aparenciaFinal[i][j] + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1);
					}
				}				
			}
			
			//Sele��o dos melhores valores e adi��o � lista
			for (int k = 0; k < tamanhoDaListaColaboradores; k++){
				float maiorAparencia = -1000;
				int iMaior = -1;
				int jMaior = -1;
				
				for(int j = 0; j < personSize; j++)
				{
					if(aparenciaFinal[i][j] > maiorAparencia) 
					{
						maiorAparencia = aparenciaFinal[i][j];
						iMaior = i;
						jMaior = j;
					}				
				}
				if(iMaior != -1) {
					RelatePersonPerson recommendedRelationPerson = new RelatePersonPerson();
					
					recommendedRelationPerson.setPerson1(listPerson.get(i));
					recommendedRelationPerson.setPerson2(listPerson.get(jMaior));
					recommendedRelationPerson.setAffinityRate(aparenciaFinal[iMaior][jMaior]);					
					aparenciaFinal[iMaior][jMaior] = -1000;
					// TODO Conforme as demais classes, foi preciso adicionar o status STATUS_SUGGESTED para informar que se refere a lista de pessoas recomendadas.
					recommendedRelationPerson.setStatus(RelatePersonPerson.STATUS_COLLABS);					
					listPerson.get(i).getListRelatePersonPerson().add(recommendedRelationPerson);
				}
			}
			// TODO Essa lista N�O precisa ser persistida
			//simulacao.setPerson(listPerson.get(i));
		}
		
		return flagOK;
	}

	public boolean gerarListaColaborativa(int tamanhoDaListaColaborativa)
	{
		boolean flagOK = true;
		
		List<Person> listPerson = iData.getAllPerson();
		
		for(int i = 0; i < listPerson.size(); i++){

			ArrayList<RelatePersonContent> listTemporaria = new ArrayList<RelatePersonContent>();					
			
			List<RelatePersonPerson> Temp1 = listPerson.get(i).getListRelatePersonPerson(RelatePersonPerson.STATUS_COLLABS);
			// getListRecommendedPersons 
			for(int j = 0; j < Temp1.size(); j++){
				
				if (i != j){
					List<RelatePersonContent> Temp2 = Temp1.get(j).getPerson2().getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE); 
					for(int k = 0; k < Temp2.size(); k++) {
						
						boolean jaExiste = false;
												
						for(int l = 0; l < listTemporaria.size(); l++){
							
							if(listTemporaria.get(l).getContent().getId() == Temp2.get(k).getContent().getId()){

								float rateTemporario = (listTemporaria.get(l).getRatePerson()*listTemporaria.get(l).getCountSuggested() + Temp1.get(j).getAffinityRate() * Temp2.get(k).getRatePerson())/(listTemporaria.get(l).getCountSuggested() + 1); 
								
								listTemporaria.get(l).setCountSuggested(listTemporaria.get(l).getCountSuggested() + 1);
								
								listTemporaria.get(l).setRatePerson(rateTemporario);
								
								jaExiste = true;
							
							}
						}						
						
						if(!jaExiste){
							RelatePersonContent relationWithContent = new RelatePersonContent();
							relationWithContent.setPerson(listPerson.get(i));
							relationWithContent.setContent(Temp2.get(k).getContent());
							relationWithContent.setRatePerson(Temp1.get(j).getAffinityRate() * Temp2.get(k).getRatePerson());			
							relationWithContent.setCountSuggested(1);
							
							relationWithContent.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
							relationWithContent.setSuggestedMethod(RelatePersonContent.SUGG_METH_PONDER);
							
							listTemporaria.add(relationWithContent);							
						}
					}
				}
			}

			//Normatiza��o dos Valores
			{
				float menor = 10000;
				float maior = -10000;
				for(int j = 0; j < listTemporaria.size(); j++){
					if(i != j){
						if(listTemporaria.get(j).getRatePerson() < menor) {
							menor = listTemporaria.get(j).getRatePerson();
						}
						if(listTemporaria.get(j).getRatePerson() > maior) {
							maior = listTemporaria.get(j).getRatePerson();
						}
					}
				}
							
				for(int j = 0; j < listTemporaria.size(); j++){
					if(i != j){
						listTemporaria.get(j).setRatePerson(10*(listTemporaria.get(j).getRatePerson() + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1));
					}
				}				
			}
			
			// Escolha dos melhores resultados
			for (int k = 0; k < tamanhoDaListaColaborativa; k++){
				float maiorIndice = -1000;
				int kMaior = -1;
				for(int l = 0; l < listTemporaria.size(); l++){
								
					if(listTemporaria.get(l).getRatePerson() > maiorIndice) {
						maiorIndice = listTemporaria.get(l).getRatePerson();
						kMaior = l;					
					}												
				}
				if(kMaior != -1) {
					//listPerson.get(i).addRecommendedContent(listTemporaria.get(kMaior));
					listPerson.get(i).getListRelatePersonContent().add(listTemporaria.get(kMaior));
					listTemporaria.remove(kMaior);
				}
			}
			
			//simulacao.setPerson(listPerson.get(i));
			
		}		
		
		return flagOK;
	}

}

