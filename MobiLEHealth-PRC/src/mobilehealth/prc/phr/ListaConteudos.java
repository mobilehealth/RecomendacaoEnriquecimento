package mobilehealth.prc.phr;

import java.util.ArrayList;
import java.util.Date;
//import java.util.HashSet;
import java.util.List;

import mobilehealth.core.dao.RecommendationDAO;
//import mobilehealth.core.dao.RelatePersonContentDAO;
import mobilehealth.core.domain.Recommendation;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.prc.temp.InsertRecommendation;

public class ListaConteudos {
	
	
	// Algoritmo de Ordena��o
	public List<RelatePersonContent> insertionSort(List<RelatePersonContent> array) {
		
		for (int i = 0; i < array.size() - 1; i++) {
		
			int j = i + 1;
			RelatePersonContent tmp = array.get(j);
		
			// O conteudo de maior SS1 fica nas primeiras posi��es da lista
			while (j > 0 && tmp.getContent().getSS1() > array.get(j-1).getContent().getSS1()) {
		
				array.remove(j);
				array.add(j, array.get(j-1));
				
		
				j--;
		
			}
		
			array.remove(j);
			array.add(j, tmp);
			
		}
		return array;
	}
	

	public void mostrar(ArrayList<RelatePersonContent> resultadoRecomendacaoConjunta) {
		// TODO Auto-generated method stub

		//System.out.println("\n\n\n\nTamanho da lista" + resultadoRecomendacaoConjunta.size());
		
		for(RelatePersonContent p: resultadoRecomendacaoConjunta){
			
			System.out.println("idPerson: " + p.getPerson().getId());
			InsertRecommendation.insert(p.getPerson().getId(), p.getContent().getId());
/*			recommendation.setContent(p.getContent());
			recommendation.setPerson(p.getPerson());
			recommendation.setDate(new Date());
			r.save(recommendation);
*/			
		}

	}

}
