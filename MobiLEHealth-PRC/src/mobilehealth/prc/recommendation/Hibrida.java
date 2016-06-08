/*package mobilehealth.prc.recommendation;

import java.util.ArrayList;

import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;

public class Hibrida 
{
	
	
private int idPerson;
	
	public Hibrida(int idPerson){
		
		setIdPerson(idPerson);
	}
	
	
	public Hibrida(){
		
	}
	
	
	public int getIdPerson() {
		return idPerson;
	}





	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	
	
	
	
	
	
	public ArrayList<RelatePersonContent> gerarListaHibrida(Person person, ArrayList<RelatePersonContent> recomendacaoUbiqua)
	{			
		ArrayList<RelatePersonContent> recomendacaoHibrida = new ArrayList<RelatePersonContent>();

		ArrayList<RelatePersonContent> recomendacaoTemporaria = new ArrayList<RelatePersonContent>();
		 
		// Inserção dos elementos da Lista de Recomendação do Ubiqua
		for(int j = 0; j < recomendacaoUbiqua.size(); j++) 
		{	
			RelatePersonContent relacaoHibrida = new  RelatePersonContent();		
			boolean jaExiste = false;
			
			for(int k = 0; k < recomendacaoTemporaria.size(); k++) 
			{
				if(recomendacaoUbiqua.get(j).getContent().getId() == recomendacaoTemporaria.get(k).getContent().getId())
				{
					jaExiste = true;
				}
			}
			
			if(!jaExiste) 
			{
				relacaoHibrida.setContent(recomendacaoUbiqua.get(j).getContent());
				relacaoHibrida.setPerson(recomendacaoUbiqua.get(j).getPerson());
				relacaoHibrida.setCountSuggested(1);
				
				relacaoHibrida.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
				relacaoHibrida.setSuggestedMethod(RelatePersonContent.SUGG_METH_HYBRID);
				
				relacaoHibrida.setRatePerson(0);
				relacaoHibrida.setI1(0);
				relacaoHibrida.setI2(0);
				relacaoHibrida.setI3(recomendacaoUbiqua.get(j).getRatePerson());
				recomendacaoTemporaria.add(relacaoHibrida);
			}
		}
		
		// Inserção dos elementos da Lista de Recomendação do Usuário
		for(int j = 0; j < person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).size(); j++) 
		{			
			for(int k = 0; k < recomendacaoTemporaria.size(); k++) 
			{
				if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getContent().getId() == recomendacaoTemporaria.get(k).getContent().getId())
				{				
					if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_PONDER) 
						recomendacaoTemporaria.get(k).setI1(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getRatePerson());
					if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONTENT) 
						recomendacaoTemporaria.get(k).setI2(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getRatePerson());				
				}
			}
		}
		
		// Cálculo da Recomendação Híbrida (final)
		for(int j = 0; j < recomendacaoTemporaria.size(); j++) 
		{
			recomendacaoTemporaria.get(j).setRatePerson((recomendacaoTemporaria.get(j).getI1()*recomendacaoTemporaria.get(j).getPerson().getP1() + recomendacaoTemporaria.get(j).getI2()*recomendacaoTemporaria.get(j).getPerson().getP2()+recomendacaoTemporaria.get(j).getI3()*recomendacaoTemporaria.get(j).getPerson().getP3())/(recomendacaoTemporaria.get(j).getPerson().getP1()+recomendacaoTemporaria.get(j).getPerson().getP2()+recomendacaoTemporaria.get(j).getPerson().getP3()));
		}

		//Escolha das lista de conteúdos mais adequados						
		for (int k = 0; k < 10; k++)
		{
			float maiorAdequacao = -100000;
			int jMaior = -1;
			
			for(int j = 0; j < recomendacaoTemporaria.size(); j++)
			{
				if(recomendacaoTemporaria.get(j).getRatePerson() > maiorAdequacao) 
				{
					maiorAdequacao = recomendacaoTemporaria.get(j).getRatePerson();						
					jMaior = j;
				}				
			}
			
			if(jMaior != -1) 
			{
				recomendacaoHibrida.add(recomendacaoTemporaria.get(jMaior));
				recomendacaoTemporaria.remove(jMaior);
			}
		}

		return recomendacaoHibrida;
	}
}


*/



package mobilehealth.prc.recommendation;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;

public class Hibrida 
{
	
	
	public ArrayList<RelatePersonContent> gerarListaHibrida(Person person, ArrayList<RelatePersonContent> recomendacaoUbiqua)
	{			
		ArrayList<RelatePersonContent> recomendacaoHibrida = new ArrayList<RelatePersonContent>();

		ArrayList<RelatePersonContent> recomendacaoTemporaria = new ArrayList<RelatePersonContent>();
		 
		// Inserção dos elementos da Lista de Recomendação do Ubiqua
		for(int j = 0; j < recomendacaoUbiqua.size(); j++) 
		{	
			RelatePersonContent relacaoHibrida = new  RelatePersonContent();		
			boolean jaExiste = false;
			
			for(int k = 0; k < recomendacaoTemporaria.size(); k++) 
			{
				if(recomendacaoUbiqua.get(j).getContent().getId() == recomendacaoTemporaria.get(k).getContent().getId())
				{
					jaExiste = true;
				}
			}
			
			if(!jaExiste) 
			{
				relacaoHibrida.setContent(recomendacaoUbiqua.get(j).getContent());
				relacaoHibrida.setPerson(recomendacaoUbiqua.get(j).getPerson());
				relacaoHibrida.setCountSuggested(1);
				
				relacaoHibrida.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
				relacaoHibrida.setSuggestedMethod(RelatePersonContent.SUGG_METH_HYBRID);
				
				relacaoHibrida.setRatePerson(0);
				relacaoHibrida.setI1(0);
				relacaoHibrida.setI2(0);
				relacaoHibrida.setI3(recomendacaoUbiqua.get(j).getRatePerson());
				recomendacaoTemporaria.add(relacaoHibrida);
			}
		}
		
		// Inserção dos elementos da Lista de Recomendação do Usuário
		for(int j = 0; j < person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).size(); j++) 
		{			
			for(int k = 0; k < recomendacaoTemporaria.size(); k++) 
			{
				if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getContent().getId() == recomendacaoTemporaria.get(k).getContent().getId())
				{				
					if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_PONDER) 
						recomendacaoTemporaria.get(k).setI1(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getRatePerson());
					if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONTENT) 
						recomendacaoTemporaria.get(k).setI2(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(j).getRatePerson());				
				}
			}
		}
		
		// Cálculo da Recomendação Híbrida (final)
		for(int j = 0; j < recomendacaoTemporaria.size(); j++) 
		{
			
			if(recomendacaoTemporaria.get(j).getPerson().getP1() == 0 && recomendacaoTemporaria.get(j).getPerson().getP2() == 0 && recomendacaoTemporaria.get(j).getPerson().getP3() == 0){
				// Casos os campos estejam vazios(nulos no banco)
				recomendacaoTemporaria.get(j).setRatePerson((recomendacaoTemporaria.get(j).getI1()*1 + recomendacaoTemporaria.get(j).getI2()*1+recomendacaoTemporaria.get(j).getI3()*1)/(1));
			} else {
				// Se não, executa normalmente
				recomendacaoTemporaria.get(j).setRatePerson((recomendacaoTemporaria.get(j).getI1()*recomendacaoTemporaria.get(j).getPerson().getP1() + recomendacaoTemporaria.get(j).getI2()*recomendacaoTemporaria.get(j).getPerson().getP2()+recomendacaoTemporaria.get(j).getI3()*recomendacaoTemporaria.get(j).getPerson().getP3())/(recomendacaoTemporaria.get(j).getPerson().getP1()+recomendacaoTemporaria.get(j).getPerson().getP2()+recomendacaoTemporaria.get(j).getPerson().getP3()));
				
			}
		}

		//Escolha das lista de conteúdos mais adequados						
		for (int k = 0; k < 10; k++)
		{
			float maiorAdequacao = -100000;
			int jMaior = -1;
			
			for(int j = 0; j < recomendacaoTemporaria.size(); j++)
			{
				if(recomendacaoTemporaria.get(j).getRatePerson() > maiorAdequacao) 
				{
					maiorAdequacao = recomendacaoTemporaria.get(j).getRatePerson();						
					jMaior = j;
				}				
			}
			
			if(jMaior != -1) 
			{
				recomendacaoHibrida.add(recomendacaoTemporaria.get(jMaior));
				recomendacaoTemporaria.remove(jMaior);
			}
		}

		return recomendacaoHibrida;
	}
}

