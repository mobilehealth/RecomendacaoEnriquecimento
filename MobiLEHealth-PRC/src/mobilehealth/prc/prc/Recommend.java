package mobilehealth.prc.prc;

import java.util.ArrayList;
import java.util.Collection;

import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.recommendation.BaseadaConteudo;
import mobilehealth.prc.recommendation.ColaborativaPonderada;
import mobilehealth.prc.recommendation.Hibrida;
import mobilehealth.prc.recommendation.RecomendacaoConjunta;



public class Recommend implements IRecommender
{
	private ColaborativaPonderada colaborativaPonderada = new ColaborativaPonderada();
	private BaseadaConteudo baseadaConteudo = new BaseadaConteudo();
	private Hibrida hibrida = new Hibrida();
	private RecomendacaoConjunta RC = new RecomendacaoConjunta();
	int idPerson;
	
	@Override
	public Collection<RelatePersonPerson> getListRecommendedPersons(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<RelatePersonContent> getListRecommendedContents(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean gerarListaColaboradores(int tamanhoListaColaboradores) 
	{
		return colaborativaPonderada.gerarListaColaboradores(tamanhoListaColaboradores);
	}
	
	@Override
	public boolean gerarListaColaborativa(int tamanhoListaColaborativa)
	{
		return colaborativaPonderada.gerarListaColaborativa(tamanhoListaColaborativa);
	}
	
	@Override
	public boolean gerarListaBaseadaConteudo(int tamanhoListaBaseadaConteudo)
	{
		return baseadaConteudo.gerarListaBaseadaConteudo(tamanhoListaBaseadaConteudo);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarListaHibrida(Person person, ArrayList<RelatePersonContent> ubiqua ) 
	{
		return hibrida.gerarListaHibrida(person, ubiqua);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarListaConjunta(ArrayList<RelatePersonContent> listaHibrida) 
	{
		return RC.algoritmoGenetico(listaHibrida);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarRecomendacao(Person person,  ArrayList<RelatePersonContent> ubiqua ) {		
		return RC.algoritmoGenetico(hibrida.gerarListaHibrida(person, ubiqua));
	}

}
