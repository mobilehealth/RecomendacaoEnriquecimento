package mobilehealth.prc.prc;


import java.util.ArrayList;
import java.util.Collection;

import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonPerson;

public interface IRecommender 
{
	/*
	 * Tendo como entrada a referencia de Person, retorna as listas listRecommendedPersons e listRecommendedContent 
	 * que atualizarão o objeto person. Dessa forma, não retorna apenas a lista de 
	 * itens recomendados, mas também as relações entre o usuário e esses itens.
	 */
	
	Collection<RelatePersonPerson> getListRecommendedPersons(Person person);
	
	Collection<RelatePersonContent> getListRecommendedContents(Person person);
	
	boolean gerarListaColaboradores(int tamanhoListaColaboradores);
	
	boolean gerarListaColaborativa(int tamanhoListaColaborativa);
	
	boolean gerarListaBaseadaConteudo(int tamanhoListaBaseadaConteudo);
	
	public ArrayList<RelatePersonContent> gerarListaHibrida(Person person,  ArrayList<RelatePersonContent> ubiqua );
	
	public ArrayList<RelatePersonContent> gerarListaConjunta(ArrayList<RelatePersonContent> listaHibrida);
	
	public ArrayList<RelatePersonContent> gerarRecomendacao(Person person,  ArrayList<RelatePersonContent> ubiqua );

}
