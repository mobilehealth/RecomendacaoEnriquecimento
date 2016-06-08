package mobilehealth.prc.recommendation;


import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelatePersonContent;

public class Ubiqua {

	public ArrayList<RelatePersonContent> geraListaUbiqua (Person person, Context history, int tamanhoDaListaUbiqua){
		
		ArrayList<RelatePersonContent> recomendacaoUbiqua = new ArrayList<RelatePersonContent>();
		
		List<RelatePersonContent> listRelateContent = person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED);
		
		// getListRecommendedContents
		float adequacaoFrequency[] = new float[listRelateContent.size()];
		float adequacaoSituacao[] = new float[listRelateContent.size()];
		float adequacaoLocations[] = new float[listRelateContent.size()];
		float adequacaoDispositivo[] = new float[listRelateContent.size()];
		float adequacaoFinal[] = new float[listRelateContent.size()];		
		
		for(int j = 0; j < listRelateContent.size(); j++) {
			
			// Verifica adequação ao horário e ao dia da semana
			adequacaoFrequency[j] = 0;
			//adequacaoFrequency[j] += listRelateContent.get(j).getContent().getFrequency().getH24PositiveV()[history.getTimeStamp().HOUR_OF_DAY];
			//adequacaoFrequency[j] -= listRelateContent.get(j).getContent().getFrequency().getH24NegativeV()[history.getTimeStamp().HOUR_OF_DAY];
//			adequacaoFrequency[j] += listRelateContent.get(j).getContent().getFrequency().getH7PositiveV()[history.getTimeStamp().DAY_OF_WEEK];
//			adequacaoFrequency[j] -= listRelateContent.get(j).getContent().getFrequency().getH7NegativeV()[history.getTimeStamp().DAY_OF_WEEK];
		
			adequacaoSituacao[j] = 0;
			if(history.getPersonSpeed() > 20 ) {
				if (listRelateContent.get(j).getContent().getSubtype() == 0)	
					adequacaoSituacao[j] += -10;
				else if (listRelateContent.get(j).getContent().getSubtype() == 1)	
					adequacaoSituacao[j] += -5;	
				else if (listRelateContent.get(j).getContent().getSubtype() == 2)	
					adequacaoSituacao[j] += 5;
				else if (listRelateContent.get(j).getContent().getSubtype() == 3)	
					adequacaoSituacao[j] += 10;
			}
			
			// Verifica adequação ao local (locations: se local definido implica em peso maior, senão ver próximos)
			adequacaoLocations[j] = 0;
			
			for(int k = 0; k < listRelateContent.get(j).getContent().getListRelateContentLocation().size(); k++) {
				
				if(listRelateContent.get(j).getContent().getListRelateContentLocation().get(k).getLocation().getId() == history.getLocation().getId()) {
					
					if (listRelateContent.get(j).getContent().getListRelateContentLocation().get(k).getStatus() == RelateContentLocation.STATUS_ABOUT) 
						adequacaoLocations[j] += 200;
					else if (listRelateContent.get(j).getContent().getListRelateContentLocation().get(k).getStatus() == RelateContentLocation.STATUS_CITED)
						adequacaoLocations[j] += 20;
					else if (listRelateContent.get(j).getContent().getListRelateContentLocation().get(k).getStatus() == RelateContentLocation.STATUS_RELATED)
						adequacaoLocations[j] += 5;
				}
			}
						
			// Verifica adequação a situação do dispositivo (bateria e conexão)
			adequacaoDispositivo[j] = 0;
			if (history.getBattery() <= 20) adequacaoDispositivo[j] +=  history.getBattery() - listRelateContent.get(j).getContent().getBytesOnline()/1024;
			if (history.getBattery() <= 20) adequacaoDispositivo[j] +=  history.getBattery() - listRelateContent.get(j).getContent().getSecondsOnline()/60;			
			if (history.getConnectionSpeed() < 300 ) adequacaoDispositivo[j] += history.getConnectionSpeed() - listRelateContent.get(j).getContent().getBytesOnline()/1024;
			
			adequacaoFinal[j] = adequacaoFrequency[j] + adequacaoSituacao[j] + adequacaoLocations[j] + adequacaoDispositivo[j];
		}
		//Normatização dos Valores
		{
			float menor = 10000;
			float maior = -10000;				
			for(int j = 0; j < listRelateContent.size(); j++){
				if(adequacaoFinal[j] < menor) {
					menor = adequacaoFinal[j];
				}
				if(adequacaoFinal[j] > maior) {
					maior = adequacaoFinal[j];					
				}
			}
			
			for(int j = 0; j < listRelateContent.size(); j++){					
					adequacaoFinal[j] = 10*(adequacaoFinal[j] + Math.abs(menor) + 1)/(maior + Math.abs(menor) + 1);					
			}		
						
			//Escolha das lista de conteúdos mais adequados						
			for (int k = 0; k < tamanhoDaListaUbiqua; k++){
				float maiorAdequacao = -100000;
				int jMaior = -1;
				for(int j = 0; j < listRelateContent.size(); j++){
					if(adequacaoFinal[j] > maiorAdequacao) {
						maiorAdequacao = adequacaoFinal[j];						
						jMaior = j;
					}				
				}
				if(jMaior != -1) {
					RelatePersonContent recommendedRelationContent = new RelatePersonContent();
					recommendedRelationContent.setPerson(person);
					recommendedRelationContent.setContent(listRelateContent.get(jMaior).getContent());
					recommendedRelationContent.setRatePerson(adequacaoFinal[jMaior]);
					recommendedRelationContent.setStatus(RelatePersonContent.STATUS_RECOMMENDED);
					recommendedRelationContent.setSuggestedMethod(RelatePersonContent.SUGG_METH_UBIQ);
					adequacaoFinal[jMaior] = -1000;
					recomendacaoUbiqua.add(recommendedRelationContent);					
				}
			}

		}			

		return recomendacaoUbiqua;
	}	
}
