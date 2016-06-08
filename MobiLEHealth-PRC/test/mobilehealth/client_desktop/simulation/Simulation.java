package mobilehealth.client_desktop.simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


import mobilehealth.prc.api.external.FacadeLocal;
//import ubi.api.communic.local.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.File;
import mobilehealth.core.domain.Frequency;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Learning;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Privacy;
import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.core.domain.Scores;
import mobilehealth.core.domain.Tag;
import mobilehealth.core.domain.Times;


public class Simulation 
{
	private IUbi iUbi = FacadeLocal.getInstance();
	
	public void geraLocais (int numeroLocais) 
	{	
		Random gerador = new Random();
		
		for ( int i = 1; i <= numeroLocais; i++)
		{			
			
			// Gera dados aleatoriamente para um locais
			Location local = new Location();
			local.setIdGoogle("googleId"+i);
			local.setTitle("Local "+i);
			local.setCountry("Brasil");
			local.setState("Estado"+gerador.nextInt(5)+1);
			local.setCity("Cidade"+gerador.nextInt(10)+1);
			local.setNeighborhood("Bairro"+gerador.nextInt(5)+1);
			local.setStreetName("Logradouro"+gerador.nextInt(20)+1);
			local.setStreetNumber(gerador.nextInt(1000)+1);
			local.setDescription("Complemento");
			local.setCEP("0");	// TODO Antonio, mudei CEP para String, pois permite mais formatos, ok?
			local.setRating(gerador.nextFloat());
			local.setLatitude(gerador.nextFloat());
			local.setLongitude(gerador.nextFloat());
			local.setAltitude(gerador.nextFloat());	
			local.setDateCreation(Calendar.getInstance());
			
			// Dados referentes ao histograma de iterações com conteudos
			local.setFrequency(geraFreqViews(10, i));
			
		//	local.setListTags(geraTags(10));
			
			local.setListTypes(geraTipos(5));

			// Insere os dados do usuário na simulação
			iUbi.createLocation(local);
		}		
	}
		
	public void geraPessoas(int numeroPessoas) 
	{					
		int k = 1;
		Random gerador = new Random();        
		Calendar hoje = Calendar.getInstance();
		
		for (int i = 1; i <= numeroPessoas; i++)
		{		
			
			k = 3 - k; // bom macete (pega sempre 1 ou 2 de forma alternada)
			
			// Gera dados aleatoriamente para um usuário
			Person pessoa = new Person();
			pessoa.setNameFirst("DaLua"+i);
			pessoa.setNameLast("Silva");
			pessoa.setEmail("teste"+i+"@lesmobile.com.br");
			pessoa.setPassword("senha");
			Calendar dataTemp = Calendar.getInstance();
			dataTemp.set(gerador.nextInt(113)+1900, gerador.nextInt(12), gerador.nextInt(28)+1);
			pessoa.setDateBirth(dataTemp);
			pessoa.setDateRegister(Calendar.getInstance());
			pessoa.setGender(k);
			pessoa.setIncome(gerador.nextInt(10)*700 + 700);
			pessoa.setPhone("7777-7777");
			pessoa.setReligion(gerador.nextInt(20));
			pessoa.setRace(gerador.nextInt(10));
			pessoa.setDisease("prof"+gerador.nextInt(100)); // TODO Antonio, como nao ha ainda uma lista de constantes para profissoes, estou considerando como String. Quando tivermos uma tabela de profissoes, podemos comecar a considerar como int.
			pessoa.setRateDurationContents(gerador.nextFloat()*3600);
//			pessoa.setListTags(geraTags(10));
			
			// Dados referentes a formação e ao estilo de aprendizagem do usuário
			Learning learningTemp = new Learning();
			learningTemp.setEducationalLevel(gerador.nextInt(7));
			learningTemp.setEstiloDimenOrganizacao(gerador.nextInt(3));
			learningTemp.setEstiloDimenPercepcao(gerador.nextInt(3));
			learningTemp.setEstiloDimenProcessamento(gerador.nextInt(3));
			learningTemp.setEstiloDimenRetencao(gerador.nextInt(3));
			learningTemp.setFormationArea(gerador.nextInt(20));
			learningTemp.setFormationSubarea(gerador.nextInt(20));
			learningTemp.setFormationCourse("Curso de formação");
			pessoa.setLearning(learningTemp);
			
			// Histograma de alguns comportamentos do usuario
			Times times = new Times();
			pessoa.setTimes(times);
			
			// Privacidade
			Privacy privacy = new Privacy();
			pessoa.setPrivacy(privacy);
			
			// Scores para gamification
			Scores scores = new Scores();
			pessoa.setScores(scores);
			
			// Imagem de perfil
			File file = new File();
			file.setFullName("fullName"+i);
			file.setData("");
			file.setWidth(0);
			file.setBytes(0);
			file.setHeight(0);
			file.setOrigin(0);
			file.setSeconds(0);
			file.setType(0);
			pessoa.setFile(file);
			
			// Dados referentes ao histograma de iterações com conteudos
			pessoa.setFrequency(geraFreqViews(10, i));
			
			// TODO Antonio, nao eh util para voce as essas informacoes de interação docial de Times para refinar as recomendacoes?
			// TODO Luiz, o que achas da captura dessa dado (de interacao social)?
			
			// Insere os dados do usuário na simulação			
			iUbi.createPerson(pessoa);
		}		
	}
	
	public void geraConteudos (int numeroConteudos) 
	{			
		Random gerador = new Random();
				
		Calendar hoje = Calendar.getInstance();
		
		for ( int i = 1; i <= numeroConteudos; i++)
		{			

			//Gera os dados aleatóriamente para um conteúdo
			Content content = new Content();
			content.setType(1);
			content.setSubtype(gerador.nextInt(4));		// Tipo: Texto = 0; Página = 1; imagem = 2; vídeo = 3; Indefinido = 4; 
			content.setVisibility(1);
			content.setTitle("Conteúdo "+i);
			content.setDescription("Descrição do Conteúdo");
			Calendar dataTemp = Calendar.getInstance();
			dataTemp.set(gerador.nextInt(113)+1900, gerador.nextInt(12), gerador.nextInt(28)+1);
			content.setDateCreation(dataTemp);
			// content.setAge( hoje.get(Calendar.YEAR) - dataTemp.get(Calendar.YEAR) ); // TODO Antonio, criei metodo para calcular age automaticamente
			// TODO Antonio, as chaves estrangeiras no JPA sao referenciadas pelo objeto, e nao apenas pelo ID.
			content.setAuthor( "NomedoAutor"+gerador.nextInt(50)); // Autor
			content.setUrlOnline("http://www.enderco"+i+".com");
			content.setBytesOnline(gerador.nextInt(10000)+300);
			content.setSecondsOnline(gerador.nextInt(1800)+30);
			content.setRateAcceptance(gerador.nextFloat()*100);
			content.setRateColabPonder(gerador.nextFloat()*10);
//			content.setListTags(geraTags(10));
			
			// Dados referentes ao histograma de iterações com usuarios
			// TODO Antonio, observe que adicionei 2 histogramas em Frequency para acumular a visualização dos conteudos (no dia 24 e na semana 7),
			// porem, esses atributos nao sao usados nos seus algoritmos. Nao seria util ter essa informacao?
			content.setFrequency(geraFreqViews(10, i));
			
			// Insere o conteúdo na simulação
			iUbi.createContent(content);
		}
	}
		
	public Context geraContext () 
	{	
		//Gera History para calculo de recomendação ubiqua
		Random gerador = new Random();
		Calendar dataTemp = Calendar.getInstance();
		
		Context context = new Context(Context.TYPE_REGULAR);
		
		context.setType(1);
		List<Location> listLocations = iUbi.getAllLocation();
		context.setLocation(listLocations.get(gerador.nextInt(iUbi.getAllLocation().size())));
	
		context.setTimeStamp(dataTemp);
		context.setBattery(15);
		context.setConnectionSpeed(500);
		context.setPersonSpeed(0);
		
		return context;
	}
	
	public void geraRelacaoPersonLocais (int numeroLocaisPorPessoa) 
	{	
		Random gerador = new Random();
		
		List<Person> listaPessoas = iUbi.getAllPerson();
		List<Location> listaLocais = iUbi.getAllLocation();
		
		for (int i = 0; i < listaPessoas.size(); i++) 
		{

			Person person = listaPessoas.get(i);
			
			int numeroMyLocais = gerador.nextInt(numeroLocaisPorPessoa)+1;			
			
			for (int j = 0; j < numeroMyLocais; j++) 
			{
				// Numeros aleatorios sem repetição
				List<Integer> listaAleatoria = new ArrayList<Integer>();  
				for (int t = 0; t < listaLocais.size(); t++) {  
					listaAleatoria.add(t);
				}
				Collections.shuffle(listaAleatoria); 
				int indiceLocal = listaAleatoria.get(i);
				
				Location location = listaLocais.get(indiceLocal);
								
				// Data aleatoria
				Calendar dataTemp = Calendar.getInstance();	
				dataTemp.set(2013, gerador.nextInt(12), gerador.nextInt(28)+1);
				
				// Chave composta
				RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
				relatePersonLocation.setPerson(person);
				relatePersonLocation.setLocation(location);						
				relatePersonLocation.setDateRelation(dataTemp);				
				relatePersonLocation.setFinality(gerador.nextInt(10));
				relatePersonLocation.setRatePerson(gerador.nextFloat()*10);
				// TODO Luiz e Antonio (lembra luiz sobre essa questao), por termos agora apenas uma lista, precisa-se preencher o status para criar sublistas. 
				// Aqui no caso sao os locais "favoritos" da pessoa, correto?
				// Precisamos pensar como esses locais favoritos sao adicionados pela pessoa na prática.
				// Por exemplo, adicionados manualmente, e/ou adicionados automaticamente quando o perceber-se que sao locais proximos, ou que, o usuário passa por lah com frequencia.
				relatePersonLocation.setStatus(RelatePersonLocation.STATUS_LOCATION_FAVORITE);
				// TODO Antonio DICA IMPORTANTE: la nos algoritmos de recomendação, evita ficar dando get na peristencia. 
				// QUANDO FOR POSSÍVEL, eh mais eficiente vc pegar a lista toda, e manipular a lista.
				// Mas, lembre-se de atualizar as instancias se elas forem modificadas. EX: simulacao.setPerson(person);
				
				person.getListRelatePersonLocation().add(relatePersonLocation);
//				iUbi.setPerson(person);
			}				
		}
	}  
	
	public void geraRelacaoPersonContent (int numeroMaxConteudosPorPessoa) 
	{	
		Random gerador = new Random();
		
		List<Person> listaPessoas = iUbi.getAllPerson();
		List<Content> listaConteudos = iUbi.getAllContent();
		
		for (int i = 0; i < listaPessoas.size(); i++) 
		{

			Person person = listaPessoas.get(i);

			int numeroMyContents = gerador.nextInt(numeroMaxConteudosPorPessoa);
			
			for (int j = 0; j < numeroMyContents; j++) 
			{			
				// Numeros aleatorios sem repetição
				List<Integer> listaAleatoria = new ArrayList<Integer>();  
				for (int t = 0; t < listaConteudos.size(); t++) {  
					listaAleatoria.add(t);
				}
				Collections.shuffle(listaAleatoria); 
				int indiceContent = listaAleatoria.get(i);
				
				Content content = listaConteudos.get(indiceContent);
				
				// Data aleatoria
				Calendar dataTemp = Calendar.getInstance();	
				dataTemp.set(2013, gerador.nextInt(12), gerador.nextInt(28)+1);
				
				// Chave composta
				RelatePersonContent relatePersonContent = new RelatePersonContent();
				relatePersonContent.setPerson(person);
				relatePersonContent.setContent(content);				
//				relatePersonContent.setDateSuggested(dataTemp);
				relatePersonContent.setCountSuggested(gerador.nextInt(4));
//				relatePersonContent.setCountViews(gerador.nextInt(4));		// TODO Antonio, nao seria melhor alimentar o histograma de views? 
				relatePersonContent.setRatePerson(gerador.nextFloat());		
				relatePersonContent.setStatus(RelatePersonContent.STATUS_CONTENT_FAVORITE); 
				
				//tempRWC.setStatus(gerador.nextInt(5)); 
				// TODO Antonio, pq estavas preenchendo de forma aleatoria o status?
				// TODO Antonio, por termos agora apenas uma lista de conteudos, precisa-se preencher o status para diferenciar essa lista de conteudos favoritos da lista de conteudos recomendados.
				
				person.getListRelatePersonContent().add(relatePersonContent);
				
//				iUbi.setPerson(listaPessoas.get(i));
			}	
		}
	}  
	

	public void geraRelacaoPersonPerson (int numeroRelacoes) 
	{			
		
		// Falta gerar as solicitações de amizades, assim como as rejeições e exclusões
		// int numeroSolicitacoes = taxaRelacoes / 5;
		// int numeroRejeicoes = taxaRelacoes / 5;
		// int numeroExclusoes = taxaRelacoes / 10;
		
		Random gerador = new Random();
		
		List<Person> listaPessoas = iUbi.getAllPerson();

		for (int i = 0; i < listaPessoas.size(); i++) 
		{

			Person person = listaPessoas.get(i);
			
			// Adiciona 20 amigos para cada pessoa (1000/50 = 20)
			for (int j = 0; j < 20; j++) 
			{
				// Numeros aleatorios sem repetição
				List<Integer> listaAleatoria = new ArrayList<Integer>();  
				for (int t = 0; t < listaPessoas.size(); t++) {  
					listaAleatoria.add(t);
				}
				Collections.shuffle(listaAleatoria); 
				int indiceDoAmigo = listaAleatoria.get(i);
				
				// Amigo sorteado
				Person personAmigo = listaPessoas.get(indiceDoAmigo);
				
				// Data aleatoria
				Calendar dataTemp = Calendar.getInstance();		
				dataTemp.set(gerador.nextInt(3)+2010, gerador.nextInt(12), gerador.nextInt(28)+1);
				
				// IDA
				RelatePersonPerson relatePersonPerson1 = new RelatePersonPerson();
				relatePersonPerson1.setPerson1(person);
				relatePersonPerson1.setPerson2(personAmigo);
				relatePersonPerson1.setStatus(RelatePersonPerson.STATUS_FRIEND);
				relatePersonPerson1.setCommomPersons(gerador.nextInt(20)); // TODO Pendencia: criar algoritmo para contar amigos em comum
//				relatePersonPerson1.setDateFriendship(dataTemp);
				relatePersonPerson1.setAffinityRate(gerador.nextFloat()*10); // TODO Pendencia: criar algoritmo para calcular isso
				person.getListRelatePersonPerson().add(relatePersonPerson1);
//				iUbi.setPerson(person);
				
				// VOLTA
				RelatePersonPerson relatePersonPerson2 = new RelatePersonPerson();
				relatePersonPerson2.setPerson1(personAmigo);
				relatePersonPerson2.setPerson2(person);
				relatePersonPerson2.setStatus(RelatePersonPerson.STATUS_FRIEND);
				relatePersonPerson2.setCommomPersons(gerador.nextInt(20)); 
//				relatePersonPerson2.setDateFriendship(dataTemp);
				relatePersonPerson2.setAffinityRate(gerador.nextFloat()*10); 
				personAmigo.getListRelatePersonPerson().add(relatePersonPerson2);
//				iUbi.setPerson(personAmigo);
			} // for j
		} // for i
	}


	public void geraRelacaoContentLocais (int numeroLocaisPorConteudo) 
	{		
		Random gerador = new Random();
		
		List<Content> listaConteudos = iUbi.getAllContent();
		List<Location> listaLocais = iUbi.getAllLocation();
		
		for (int i = 0; i < listaConteudos.size(); i++) 
		{

			Content content = listaConteudos.get(i);
			
			int numeroMyLocais = gerador.nextInt(numeroLocaisPorConteudo);			
			
			for (int j = 0; j < numeroMyLocais; j++) 
			{
				// Numeros aleatorios sem repetição
				List<Integer> listaAleatoria = new ArrayList<Integer>();  
				for (int t = 0; t < listaLocais.size(); t++) {  
					listaAleatoria.add(t);
				}
				Collections.shuffle(listaAleatoria); 
				int indiceLocation = listaAleatoria.get(i);
				
				Location location = listaLocais.get(indiceLocation);
				
				// Data aleatoria
				Calendar dataTemp = Calendar.getInstance();	
				dataTemp.set(2013, gerador.nextInt(12), gerador.nextInt(28)+1);
	
				// Chave composta:
				RelateContentLocation relateContentLocation = new RelateContentLocation();
				relateContentLocation.setContent(content);
				relateContentLocation.setLocation(location);	
				relateContentLocation.setDateRelation(dataTemp);				
				relateContentLocation.setStatus(gerador.nextInt(6));
				
				content.getListRelateContentLocation().add(relateContentLocation);
//				iUbi.setContent(content);
			} // for j
		} // for i
	}  
	
	
	public Frequency geraFreqViews (int taxaVisualizacao, int id) 
	{	
		Frequency frequency = new Frequency();
		
		int countHourPositivo [] = new int[24];
		int countHourNegativo [] = new int[24];
		int countDayPositivo [] = new int[7];
		int countDayNegativo [] = new int[7];
				
		for (int i = 0; i < 24; i++)
		{	
			Random gerador = new Random();
			countHourPositivo [i] = gerador.nextInt(taxaVisualizacao);
			countHourNegativo [i] = gerador.nextInt(taxaVisualizacao);
		}
		
		for (int i = 0; i < 7; i++)
		{	
			Random gerador = new Random();
			countDayPositivo [i] = gerador.nextInt(taxaVisualizacao);
			countDayNegativo [i] = gerador.nextInt(taxaVisualizacao);
		}	
				
		return frequency;
	}

	public ArrayList<Tag> geraTags (int numeroTags) 
	{	
		Random gerador = new Random();
		ArrayList<Tag> listTagsTemporaria = new ArrayList<Tag>(); 
		
		for(int k = 1; k <= numeroTags; k++){
			
			Tag tagTemporaria = new Tag();
			
			// TODO Antonio, nao deves usar setId, em nenhuma classe entidade, pois esses valores sao criados automaticamente pelo sequence.
			//tagTemporaria.setId(gerador.nextInt(100));
			
			//tagTemporaria.setName("Tag" + tagTemporaria.getId());
			// TODO Antonio, estava dando erro aqui quando persistia no BD, por isso, add a linha abaixo para corrigir: criar nomes na sequencia.
			tagTemporaria.setName("Tag"+k);
			
			tagTemporaria.setCount(gerador.nextInt()*10);
			
			listTagsTemporaria.add(tagTemporaria);
		}

		return listTagsTemporaria;
	}
	
	public ArrayList<String> geraTipos (int numeroTipos) 
	{	
		Random gerador = new Random();
		ArrayList<String> listTiposTemporaria = new ArrayList<String>(); 
		
		for(int k = 1; k <= numeroTipos; k++){
			
			listTiposTemporaria.add("Tipo " + gerador.nextInt(200));
		}
		return listTiposTemporaria;
	
	}
	
	public void imprimeLocations() 
	{	
		System.out.println("\nLista de Locais");
	
		for ( int i = 1; i <= iUbi.getCountLocation(); i++)
		{			
			Location location = iUbi.getLocation(i);
			// Imprime para simples verificação
			System.out.println("-----------------");
			System.out.println("ID: "+ location.getId());
			System.out.println("GoogleID: "+ location.getIdGoogle());
			System.out.println("Nome do Local: "+ location.getTitle());
			System.out.println("País: "+ location.getCountry());
			System.out.println("Estado: "+ location.getState());
			System.out.println("Cidade: "+ location.getCity());
			System.out.println("Bairro: "+ location.getNeighborhood());
			System.out.println("Logradouro: "+ location.getStreetName());
			System.out.println("Número: "+ location.getStreetNumber());
			System.out.println("Complemento: "+ location.getDescription());
			System.out.println("Cod. Postal: "+ location.getCEP());
			System.out.println("Avaliação: "+ location.getRating());
			System.out.println("Latidude: "+ location.getLatitude());
			System.out.println("Longitude: "+ location.getLongitude());
			System.out.println("Altidude: "+ location.getAltitude());
			//System.out.println("Frequência Positiva: "+ simulacao.getLocationID(i).getFreqViewsPositivo().toString());

			System.out.println("\n\n");
		}				
	}
	
	public void imprimePessoas() 
	{	
		System.out.println("\nLista de Usuários");
		
		for ( int i = 1; i <= iUbi.getCountPerson(); i++)
		{			
			Person person = iUbi.getPerson(i);
			// Imprime para simples verificação os dados da pessoa
			System.out.println("-------------------------------------------");
			System.out.println("Usuário ID: " + person.getId());
			System.out.println("Nome: " + person.getNameFirst());
			System.out.println("Sobrenome: " + person.getNameLast());
			System.out.println("Email: " + person.getEmail());
			System.out.println("Senha: " + person.getPassword());
			System.out.println("Aniversário: " + person.getAge()+" anos.");
			System.out.println("Sexo: " + person.getGender());
			System.out.println("Renda Familiar: R$ " + person.getIncome()+",00");
			System.out.println("Religiao: " + person.getReligion());
			System.out.println("Cor/Raça: " + person.getRace());
			System.out.println("Doença: " + person.getDisease());
			System.out.println("Nível de Formação: " + person.getLearning().getEducationalLevel());
			System.out.println("Estilo Organizacional: " + person.getLearning().getEstiloDimenOrganizacao());
			System.out.println("Estilo de Percepção: " + person.getLearning().getEstiloDimenPercepcao());
			System.out.println("Estilo de Processamento: " + person.getLearning().getEstiloDimenProcessamento());
			System.out.println("Estilo de Retenção: " + person.getLearning().getEstiloDimenRetencao());
			//System.out.println("Grande Área de Conhecimento: "+simulacao.getPersonID(i).getLearning().getGrandeArea());
			System.out.println("Área de Conhecimento: " + person.getLearning().getFormationArea());
			//System.out.println("Subárea de Conhecimento: " + person.getLearning().getSubarea());
			System.out.println("Curso de Formação: " + person.getLearning().getFormationCourse());
			//System.out.println("Frequência Positiva: "+ simulacao.getPersonID(i).getFreqViewsPositivo().toString());
			System.out.println("\n\n");

		}				
	}

	public void imprimeConteudos() 
	{
		System.out.println("\nLista de Conteúdos");
	
		for ( int i = 1; i <= iUbi.getCountContent(); i++)
		{			
			Content content = iUbi.getContent(i);
			
			// Imprime para simples verificação			
			System.out.println("-----------------");
			System.out.println("ID: " + content.getId());
			System.out.println("Tipo: " + content.getType());
			System.out.println("Subtipo: " + content.getSubtype());
			System.out.println("Visibilidade: " + content.getVisibility());
			System.out.println("Título: " + content.getTitle());
			System.out.println("Descricao: " + content.getDescription());
			System.out.println("Idade: " + content.getAge());
			System.out.println("Autor: " + content.getAuthor());
			System.out.println("Link: " + content.getUrlOnline());
			System.out.println("Numero de Bytes: " + content.getBytesOnline());
			System.out.println("Tempo de visualização: " + content.getSecondsOnline());
			//System.out.println("Frequência Positiva: "+ simulacao.getContentID(i).getFreqViewsPositivo().toString());
			// TODO Antonio, Dúvida: acho que houve confusao quanto ao atributo "duracao" (nome antigo, que mudei para secondsOnline).
			// Eu tinha entendido que esse atributo armazenaria o tempo do conteudo (quando for um video online). 
			// Porem, parece que pretendes aguardar outra informação, neh? Seria o acumulo de segundos que os usuarios viram esse video?
			// TODO Luiz, tem como capturar essa informacao no Player?
			
			System.out.println("\n\n");
		}				
	}

	public void imprimeRelacaoPersonPerson() 
	{	
		System.out.println("\nLista de Relacoes");
	
		for ( int i = 1; i <= iUbi.getCountPerson(); i++)
		{			
			Person person = iUbi.getPerson(i);
			
			System.out.println("\nLista de Amigos de "+ person.getNameFirst()+ " " + person.getNameLast());
			System.out.println("--------------------------------");			
			for(int j = 0; j < person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).size(); j++)
			{
				System.out.println("Amigo: " + person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(j).getPerson2().getNameFirst());
				System.out.println("Amigos em comum: " + person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(j).getCommomPersons());
				System.out.println("Grau de Afinidade: " + person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(j).getAffinityRate());
				//System.out.println("Data da Amizade: " + simulacao.getPersonID(i).getListMyPersons().get(j).getInicioRelacao().);
				System.out.println("--------------------------------");			
			}
			System.out.println("\n\n");
			
		}				
	}

	public void imprimeRelacaoPersonLocais() 
	{	
		System.out.println("\nLista de Locais por Pessoa");
	
		for ( int i = 1; i <= iUbi.getCountPerson(); i++)
		{			
			Person person = iUbi.getPerson(i);
			
			System.out.println("\nLista de Locais de "+ person.getNameFirst()+ " " + person.getNameLast());
			System.out.println("--------------------------------");
			
			for(int j = 0; j < person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); j++)
			{
				System.out.println("Nome do Local: " + person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(j).getLocation().getTitle());
				System.out.println("Finalidade: " + person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(j).getFinality());
				System.out.println("Avaliação: " + person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(j).getRatePerson());
				//System.out.println("Amigo: " + simulacao.getPersonID(i).getListMyLocations().get(j).getDataRelacao());				
				System.out.println("--------------------------------");			
			}
			
			System.out.println("\n\n");
		}				
	}

	public void imprimeRelacaoPersonConteudos() 
	{	
		System.out.println("\nLista de Locais por Pessoa");
	
		for ( int i = 1; i <= iUbi.getCountPerson(); i++)  
		{
			Person person = iUbi.getPerson(i);
			
			// Imprime para simples verificação
			System.out.println("\nLista de Conteudos de "+ person.getNameFirst());
			System.out.println("--------------------------------");
			for(int j = 0; j < person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); j++)
			{
				System.out.println("Conteúdo: " + person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(j).getContent().getTitle());
			}
			System.out.println("\n\n");
		}
	}

	public void imprimeRelacaoContentLocais() 
	{	
		System.out.println("\nLista de Locais por Pessoa");
	
		for ( int i = 1; i <= iUbi.getCountContent(); i++)
		{			
			Content content = iUbi.getContent(i);
			
			System.out.println("\nLista de Locais do "+ content.getTitle());
			System.out.println("--------------------------------");			
			
			// getListMyLocations
			for(int j = 0; j < content.getListRelateContentLocation().size(); j++)
			{
				System.out.println("Nome do Local: " + content.getListRelateContentLocation().get(j).getLocation().getTitle());
				System.out.println("Finalidade: " + content.getListRelateContentLocation().get(j).getStatus());
				//System.out.println("Avaliação: " + simulacao.getContentID(i).getListRelateContentLocation().get(j).getRating());
				//System.out.println("Amigo: " + simulacao.getPersonID(i).getListMyLocations().get(j).getDataRelacao());				
				System.out.println("--------------------------------");			
			}
			System.out.println("\n\n");
			
		}				
	}
	
	public void imprimeRecomendacao() 
	{	
		// TODO Antonio, esta certo isso? i < 1
		for(int i = 0; i < 1; i++)
		{
			Person person = iUbi.getPerson(i);
			
			System.out.println("\nNome: "+ person.getNameFirst());
			System.out.println("-------------------------------------------");
			
			// getListRecommendedContents
			for (int k = 0; k < person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).size(); k++)
			{				
				System.out.println("Título: "+ person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(k).getContent().getTitle());
				System.out.println("Status: "+ person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(k).getStatus());
				System.out.println("Afinidade: "+ person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(k).getRatePerson());
				System.out.println("Status str: "+ person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(k).getStatusAsString());
			}		
		}
	}
	
	public void imprimeRecomendacaoHibrida(Person person, ArrayList<RelatePersonContent> recomendacaoHibrida) 
	{	
		System.out.println("\n-----------------RECOMENDAÇÃO HÍBRIDA ------------------");
		System.out.println("\nNome: "+ person.getNameFirst() + " "+ person.getNameLast());
		System.out.println("\nTamanho da Recomendação: "+ recomendacaoHibrida.size()+"\n");

		for(int k = 0; k < recomendacaoHibrida.size(); k++) {
			System.out.println("Título: "+ recomendacaoHibrida.get(k).getContent().getTitle());
			System.out.println("Tipo: "+ recomendacaoHibrida.get(k).getStatusAsString());
			System.out.println("Adequação: "+ recomendacaoHibrida.get(k).getRatePerson());
			System.out.println("Peso I1: "+ recomendacaoHibrida.get(k).getI1());
			System.out.println("Peso I2: "+ recomendacaoHibrida.get(k).getI2());
			System.out.println("Peso I3: "+ recomendacaoHibrida.get(k).getI3()+"\n");
		}
	
	}

		
}