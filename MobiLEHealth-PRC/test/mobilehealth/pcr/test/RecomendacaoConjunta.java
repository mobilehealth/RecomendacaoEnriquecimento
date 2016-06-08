package mobilehealth.pcr.test;

import java.util.ArrayList;
import java.util.Random;

import mobilehealth.core.domain.RelatePersonContent;

/*
 * fatorTempo....: Quantidade de genes que atendem ao requisito tempo.
 * fatorTipo.....: Quantidade de genes com tipo de conte�do n�o repetido.
 * fatorAutor....: Quantidade de genes com autor do conte�do n�o repetido.
 * fatorIgualdade: Quantidade de genes que n�o s�o iguais.
 * 
 * avaliacao=0,6(fatorTempo)+0,4(fatorTipo+fatorAutor+fatorIgualdade)  ==> Funcao fitness
 * 
 */

public class RecomendacaoConjunta {
    
    private ArrayList<ArrayList<RelatePersonContent>> populacao;
    private int tamCromossomo = 5;
    private int tamPopulacao = 1000;
    private double avaliacao[];
    private ArrayList<RelatePersonContent> melhorRepresentacao;
    private double melhorAvaliacao = -100000;
    private int geracao = 0;
    
    private void iniciarPopulacao(ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida) {
    	
        this.populacao = new ArrayList<ArrayList<RelatePersonContent>>();
               
        Random random = new Random();			 
        for(int i = 0; i < tamPopulacao; i++){
        	ArrayList<RelatePersonContent> cromoTemporario = new ArrayList<RelatePersonContent>();
            for(int j = 0; j < tamCromossomo; j++){
            	cromoTemporario.add(resultadoRecomendacaoHibrida.get(random.nextInt(resultadoRecomendacaoHibrida.size())));
            	cromoTemporario.get(j).setSuggestedMethod(RelatePersonContent.SUGG_METH_CONJUNTA);
            }
            this.populacao.add(cromoTemporario);
        }
    }
       
    private void avaliarPopulacao(){
        
        avaliacao = new double[tamPopulacao];
        
        //Percorre todos os individuos da popula��o e os avalia
        for(int i = 0; i < tamPopulacao; i++){

			int fatorTempo = 0;
			int fatorTipo = 0;
			int fatorAutor = 0;
			int fatorIgualdade = 0;

			int auxTipo = 0;
			int auxAutor = 0;
			int auxIgualdade = 0;
			
			//Verifica quantos genes do cromossomo atendem ao fator tempo (rela��o usu�rio x conteudo)
			for(int j = 0; j < tamCromossomo; j++) {
				
				if(populacao.get(i).get(j).getRatePerson() >= populacao.get(i).get(j).getContent().getSecondsOnline()) {
					fatorTempo++;
				}
				
			}
			
			//Verifica quantos genes do cromossomo atendem ao fator tipo e autor (rela��o conteudo x conteudo)
			for(int j = 0; j < tamCromossomo-1; j++) {
				
				for(int k = j; k < tamCromossomo; k++) {
					
					// Verifica se existe tipos de conte�dos iguais.
        			if(populacao.get(i).get(j).getContent().getSubtype() == populacao.get(i).get(k).getContent().getSubtype()){
        				auxTipo++;
        			}
					
                	// Verifica se existem autores iguais
        			if(populacao.get(i).get(j).getContent().getAuthor() == populacao.get(i).get(k).getContent().getAuthor()){
        				auxAutor++;
        			}
                	// Verifica se existem conte�dos iguais
        			if (populacao.get(i).get(j).getContent().getId() == populacao.get(i).get(k).getContent().getId()) {
        				auxIgualdade++;
        			}        				

				
				}
				
			}
			
			// Calcula os fatores baseado na quantidade de repeticoes de cada fator
			fatorTipo = tamCromossomo - auxTipo;
			fatorAutor = tamCromossomo - auxAutor;
			fatorIgualdade = tamCromossomo - auxIgualdade;
			
			// Fun��o Fitness
			avaliacao[i] = 0.6 * (fatorTempo) + 0.4 * (fatorTipo + fatorAutor + fatorIgualdade);
			
			// Armazena o melhor individuo at� o momento
			if(avaliacao[i] > melhorAvaliacao){
                melhorAvaliacao = avaliacao[i];
                melhorRepresentacao = populacao.get(i);
            }

		}
		
    }

    private void selecionarPais(){
        
    	int numeroDePais = tamPopulacao/2;    	
    	ArrayList<ArrayList<RelatePersonContent>> novosPais = new ArrayList<ArrayList<RelatePersonContent>>();
        
        int melhorPai = 0;
        double melhorAvaliacao = 0; 
        
        for(int k = 0; k < numeroDePais; k++){
        	for(int i = 0; i < tamPopulacao; i++){
        		if(avaliacao[i] >= melhorAvaliacao){
        			melhorAvaliacao = avaliacao[i];
        			melhorPai = i;
        		}
        	}
        	novosPais.add(populacao.get(melhorPai));         	
        }
        this.populacao = novosPais;
    }

    private void cruzar(){
        
        Random random = new Random();
        int metadePopulacao = tamPopulacao/2;
        for(int i = 0; i < metadePopulacao; i++){

        	ArrayList<RelatePersonContent> cromo1 = populacao.get(random.nextInt(metadePopulacao));
        	ArrayList<RelatePersonContent> cromo2 = populacao.get(random.nextInt(metadePopulacao));
        	ArrayList<RelatePersonContent> cromoFilho = new ArrayList<RelatePersonContent>();

            for(int j = 0; j < tamCromossomo; j++){
            	if(cromo1.get(j).getRatePerson() > cromo2.get(j).getRatePerson()){
            		cromoFilho.add(cromo1.get(j));
            	}
            	else {
            		cromoFilho.add(cromo2.get(j));
            	}           
            }
            populacao.add(cromoFilho);
        }
    }

    /*
	 N�o est� funcionando ainda - Falta ajustar as m�tricas que ser�o usadas para a muta��o e fazer o c�digo funcionar 100%
	private void mutar(){
        
        Random random = new Random();
        
        for(int cont = 1; cont < populacao.length; cont++){
            for(int cont2 = 0; cont2 < populacao[cont].length; cont2++){

                if(random.nextInt(1000) < taxaMutacao){
                    if(populacao[cont][cont2] == 0){
                        populacao[cont][cont2] = 1;
                        auxTempo[cont] += requisitos[cont2].getTemp();//somat�rio do tempo gasto para construir o release "cont".
                        auxCusto[cont] += requisitos[cont2].getResource();//somat�rio do custo para construir o release "cont".
                    
                        if(auxTempo[cont] > this.tempo || auxCusto[cont] > this.dinheiro){//verifica se a solu��o representada pelo cromossomo � consistente a cada novo gene definido.
                            populacao[cont][cont2] = 0;//caso n�o seja, elimina o �ltimo elemento inserido e encerra o la�o.
                            auxTempo[cont] = auxTempo[cont] - requisitos[cont2].getTemp();
                            auxCusto[cont] = auxCusto[cont] - requisitos[cont2].getResource();
                        }
                    }
                    else{
                        populacao[cont][cont2] = 0;
                        auxTempo[cont] = auxTempo[cont] - requisitos[cont2].getTemp();
                        auxCusto[cont] = auxCusto[cont] - requisitos[cont2].getResource();
                    }
                }
            }
        }
    }*/
    
    public ArrayList<RelatePersonContent> algoritmoGenetico(ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida){

        iniciarPopulacao(resultadoRecomendacaoHibrida);
        
//      for(geracao = 0; geracao < 100; geracao++){
        
        for(geracao = 0; geracao < 120; geracao++){

            avaliarPopulacao();
            selecionarPais();
            cruzar();
           // mutar();
        }
             
        return this.melhorRepresentacao;
    }
        
    public ArrayList<RelatePersonContent> getMelhorSolucao(){
        return melhorRepresentacao;
    }
	
	
}
