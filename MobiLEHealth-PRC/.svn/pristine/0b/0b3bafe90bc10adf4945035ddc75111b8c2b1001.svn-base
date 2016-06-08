package mobilehealth.pcr.test;

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
		
		int personSize = iData.getCountPerson();
		int contentSize = iData.getCountContent();
		
		float aparenciaFinal[][] = new float[personSize][contentSize];
		
		List<Person> listPerson = iData.getAllPerson();
		List<Content> listContent = iData.getAllContent();
		
		for(int i = 0; i < listPerson.size(); i++)
		{
			Person person = listPerson.get(i);
			
			for(int j = 0; j < listContent.size(); j++)
			{		
				Content content = listContent.get(i);
				
				aparenciaFinal[i][j] = 0;
				
				float aparenciaMetaDados = 0;
				float aparenciaTags = 0;
				float aparenciaLocais = 0;
				float aparenciaFrequencia = 0;
				float aparenciaSemantica = 0;
				float aparenciaHistorico = 0;
				
				for(int k = 0; k < listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); k++) 
				{	
					if(listContent.get(j).getType() == listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getType()) aparenciaMetaDados+= 1;
					if(listContent.get(j).getSubtype() == listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getSubtype()) aparenciaMetaDados+=1;
					
					aparenciaMetaDados -= Math.abs(listContent.get(j).getAge() - listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getAge());
					
					if(listContent.get(j).getAuthor() == listPerson.get(i).getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(k).getContent().getAuthor()) aparenciaMetaDados+=30;
					
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
				for(int k = 0; k < 24; k++) {
					aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH24PositiveV()[k] - listContent.get(j).getFrequency().getH24PositiveV()[k]/24);
					aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH24NegativeV()[k] - listContent.get(j).getFrequency().getH24NegativeV()[k]/24);
				}
				for(int k = 0; k < 7; k++) {
					aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7PositiveV()[k] - listContent.get(j).getFrequency().getH7PositiveV()[k]/7);	
					aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7NegativeV()[k] - listContent.get(j).getFrequency().getH7NegativeV()[k]/7);
					aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7PositiveV()[k] - listContent.get(j).getFrequency().getH7PositiveV()[k]/7);	// TODO DIVISÃO ALTERADA POR 7
					aparenciaFrequencia -= Math.abs(listPerson.get(i).getFrequency().getH7NegativeV()[k] - listContent.get(j).getFrequency().getH7NegativeV()[k]/7);
				}

				aparenciaFrequencia = aparenciaFrequencia/62;
				//TODO: USAR PARA CHAMAR A CLASSE SACExternalServiceImpl
				SemelhancaSemantica SS = new SemelhancaSemantica();
				aparenciaSemantica = SS.getSemelhancaSemanticaUsuarioConteudo(listPerson.get(i), listContent.get(j));
				
				//----------------------------------------------------------------------------------------------
				//Analisar aparência no histórico de conteúdos do usuário
				//----------------------------------------------------------------------------------------------

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
