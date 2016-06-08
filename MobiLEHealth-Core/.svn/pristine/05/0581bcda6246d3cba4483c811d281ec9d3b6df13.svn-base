package mobilehealth.prc.recommendation.data;


import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelateContentTag;
import mobilehealth.core.domain.RelateLocationTag;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.core.domain.RelatePersonTag;
import mobilehealth.core.domain.Tag;
import mobilehealth.core.domain.Times;

public class GenerateRelates {

	private int sizePerson;
	private int sizeContent;
	private int sizeLocation;
	private int sizeTag;
	private int numeroDeFaixas;
	
	IUbi managerServer = FacadeLocal.getInstance();

	public static void main(String[] args) {

		(new GenerateRelates()).run();
		
	}

	public void run() {

		// Definição do Número de Faixas de Domínios
		this.numeroDeFaixas = 3;
		
		//	1 - Relações entre duas pessoas em um local
		//		Relações de amizades, de solicitações, de visualizações, de rejeições, de deletes
				System.out.println("Construção de Relações Person-Person-Location");
				//geraRelacoesPersonPersonLocation(0.05);
				System.out.println("Construção de Relações Person-Person-Location: Ok!!!");

		//	2 - Relações entre uma pessoa e um conteúdo em um local
		//		Relações de favorito, de visualizações ou aceites, de rejeições, de avaliação
				System.out.println("\n\nConstrução de Relações Person-Content-Location...");
				//geraRelacoesPersonContentLocation(0.05);
				System.out.println("\n\nConstrução de Relações Person-Content-Location: Ok!!!");
		
		//	3 - Relações entre uma pessoa e um local
		//		Relações de favorito, de presença, de Avaliação
				System.out.println("\n\nConstrução de Relações Person-Location");
				//geraRelacoesPersonLocation(0.05);
				System.out.println("\n\nConstrução de Relações Person-Location: Ok!!!");
		
		// 	4 -	Relações entre um conteúdo e um local
		//		Relação de sobre, de citado, de relacionado
				System.out.println("\n\nConstrução de Relações Content-Location");
				//geraRelacoesContentLocation(0.05);
				System.out.println("\n\nConstrução de Relações Content-Location: Ok!!!");
				
		//	5 -	Relações de uma tag com uma pessoa ou conteúdo ou local
				System.out.println("\n\nConstrução de Relações com Tags");
			  	geraRelacoesTags(0.05);
				System.out.println("\n\nConstrução de Relações com Tags: Ok!!!");
				
				System.out.println("\n Relações Geradas: Ok!!!");
		
		
	}

	private void geraRelacoesPersonPersonLocation(double taxaPersonPerson) {

		Random random = new Random();
		List<Person> people = managerServer.getAllPerson();
		List<Location> locais = managerServer.getAllLocation();
		
		this.sizePerson = people.size();
		this.sizeLocation = locais.size();
		
		Person person1 = null;
		Person person2 = null;
		Location location = null;

		int tamanhoFaixaPerson = (int) ((sizePerson-1)/numeroDeFaixas);
		int tamanhoFaixaLocation = (int) ((sizeLocation-1)/numeroDeFaixas);
		
		JOptionPane.showMessageDialog(null, "Tamanho do location: " + locais.size());
		
		for(int i = 0; i < sizePerson; i++){
 
			person1 = people.get(i);
			
			int numeroDaFaixa = (int) (i / tamanhoFaixaPerson);
			
			
			for(int j = 0; j < (sizePerson); j++){
				int k = 0;
				if (random.nextFloat() < 0.8){
					k = random.nextInt(tamanhoFaixaPerson) + tamanhoFaixaPerson*numeroDaFaixa;
					int a = random.nextInt((tamanhoFaixaLocation) + (int) ((tamanhoFaixaLocation)*numeroDaFaixa));
					
					JOptionPane.showMessageDialog(null, "busca valor: " + a);
					
					location = locais.get(a);			
				} else {
					k = random.nextInt(sizePerson-1); // -1 para não estourar a lista - Jerffeson
					location = locais.get(random.nextInt(sizeLocation-1));	// -1 para não estourar a lista - Jerffeson									
				}					
				if (person1.getId() != people.get(k).getId() && !alreadyAssociatedPP(people.get(k), person1.getListRelatePersonPerson())) 
				{
					person2 = people.get(k);
					geraRelacaoPersonPersonLocation(person1, person2, location, 1);//geraTipoRelatePersonPersonLocation());
				}
			}
		}
	}

	private void geraRelacoesPersonContentLocation(double taxaPersonContent) {

		Random random = new Random();
		List<Person> persons = managerServer.getAllPerson();
		List<Content>  contents= managerServer.getAllContent();
		List<Location> locations = managerServer.getAllLocation();
		
		this.sizePerson = persons.size();
		this.sizeContent = contents.size();
		this.sizeLocation = locations.size();		

		Person person = null;
		Content content = null;
		Location location = null;

		int tamanhoDaFaixaPerson = (int) (sizePerson/numeroDeFaixas);
		int tamanhoDaFaixaContent = (int) (sizeContent/numeroDeFaixas);
		int tamanhoDaFaixaLocation = (int) (sizeLocation/numeroDeFaixas);
		

		for(int i = 0; i < sizePerson; i++){
 
			person = persons.get(i);
			int numeroDaFaixa = (int) (i / tamanhoDaFaixaPerson);
			
			for(int j = 0; j < sizeContent*taxaPersonContent; j++){
				int k = 0;
				if (random.nextFloat() < 0.8){
					k = random.nextInt(tamanhoDaFaixaContent) + tamanhoDaFaixaContent*numeroDaFaixa;
					location = locations.get(random.nextInt(tamanhoDaFaixaLocation) + (int) (tamanhoDaFaixaLocation*numeroDaFaixa));					
				} else {
					k = random.nextInt(sizeContent);
					location = locations.get(random.nextInt(sizeLocation));										
				}					
				if (!alreadyAssociatedPC(contents.get(k), person.getListRelatePersonContent())) 
				{
					content = contents.get(k);
					geraRelacaoPersonContentLocation(person, content, location, geraTipoRelatePersonContentLocation());
				}
			}
		}
	}

	private void geraRelacoesPersonLocation(double taxaPersonLocation) {

		Random random = new Random();
		List<Person> persons = managerServer.getAllPerson();
		List<Location> locations = managerServer.getAllLocation();
		
		this.sizePerson = persons.size();
		this.sizeLocation = locations.size();
		
		Person person = null;
		Location location = null;

		int tamanhoDaFaixaPerson = (int) (sizePerson/numeroDeFaixas);
		int tamanhoDaFaixaLocation = (int) (sizeLocation/numeroDeFaixas);

		for(int i = 0; i < sizePerson; i++){

			person = persons.get(i);

			int numeroDaFaixa = (int) (i / tamanhoDaFaixaPerson);

			for(int j = 0; j < sizeLocation*taxaPersonLocation; j++){
				if (random.nextFloat() < 0.8){
					location = locations.get(random.nextInt(tamanhoDaFaixaLocation) + (int) (tamanhoDaFaixaLocation*numeroDaFaixa));					
				} else {
					location = locations.get(random.nextInt(sizeLocation));										
				}					
				geraRelacaoPersonLocation(person, location, geraTipoRelatePersonLocation());
			}
		}
	}
	
	private void geraRelacoesContentLocation(double taxaContentLocation) {

		Random random = new Random();
		List<Content>  contents= managerServer.getAllContent();
		List<Location> locations = managerServer.getAllLocation();
		
		this.sizeContent = contents.size();
		this.sizeLocation = locations.size();
		
		Content content = null;
		Location location = null;

		int tamanhoDaFaixaContent = (int) (sizeContent/numeroDeFaixas);
		int tamanhoDaFaixaLocation = (int) (sizeLocation/numeroDeFaixas);
				
		for(int i = 0; i < sizeContent; i++){
 			
			content = contents.get(i);
 			
 			int numeroDaFaixa = (int) (i / tamanhoDaFaixaContent);
 			
 			for(int j = 0; j < sizeLocation*taxaContentLocation; j++){
				if (random.nextFloat() < 0.8){
					location = locations.get(random.nextInt(tamanhoDaFaixaLocation) + (int) (tamanhoDaFaixaLocation*numeroDaFaixa));					
				} else {
					location = locations.get(random.nextInt(sizeLocation));										
				}					
				geraRelacaoContentLocation(content, location, geraTipoRelateContentLocation());
			}
		}
	}
	
	private void geraRelacoesTags(double taxaTags) {
		Random random = new Random();
		List<Person> persons = managerServer.getAllPerson();
		List<Content>  contents= managerServer.getAllContent();
		List<Location> locations = managerServer.getAllLocation();
		List<Tag> tags = managerServer.getAllTag();
		
		this.sizePerson = persons.size();
		this.sizeContent = contents.size();
		this.sizeLocation = locations.size();
		this.sizeTag = tags.size();


		
		Person person = null;
		Content content = null;
		Location location = null;
		Tag tag = null;

		int tamanhoDaFaixaTag = (int) (sizeTag/numeroDeFaixas);
		int tamanhoDaFaixaPerson = (int) (sizePerson/numeroDeFaixas);
		int tamanhoDaFaixaContent = (int) (sizeContent/numeroDeFaixas);
		int tamanhoDaFaixaLocation = (int) (sizeLocation/numeroDeFaixas);

		// 1 - Gera Relaçoes de Tags com Persons
		for(int i = 0; i < sizePerson; i++){
			
			person = persons.get(i); 			
			
			int numeroDaFaixa = (int) (i / tamanhoDaFaixaPerson);
			
			for(int j = 0; j < sizeTag*taxaTags; j++){
				if (random.nextFloat() < 0.8){
					tag = tags.get(random.nextInt(tamanhoDaFaixaTag) + (int) (tamanhoDaFaixaTag*numeroDaFaixa));
				} else {
					tag = tags.get(random.nextInt(sizeTag));
				}
				person.getListRelatePersonTag().add(generateRelatePersonTag(person, tag));
			}
			managerServer.editPerson(person);
		}
		
		// 2 - Gera Relaçoes de Tags com Contents
		for(int i = 0; i < sizeContent; i++){
 			content = contents.get(i); 			

 			int numeroDaFaixa = (int) (i / tamanhoDaFaixaContent);
			
			for(int j = 0; j < sizeTag*taxaTags; j++){
				if (random.nextFloat() < 0.8){
					tag = tags.get(random.nextInt(tamanhoDaFaixaTag) + (int) (tamanhoDaFaixaTag*numeroDaFaixa));
				} else {
					tag = tags.get(random.nextInt(sizeTag));
				}
				content.getListRelateContentTag().add(generateRelateContentTag(content, tag));
			}
			managerServer.editContent(content);
		}
		
		// 3 - Gera Relaçoes de Tags com Locations
		for(int i = 0; i < sizeLocation; i++){
			location = locations.get(i); 			
			
			int numeroDaFaixa = (int) (i / tamanhoDaFaixaLocation);
			
			for(int j = 0; j < sizeTag*taxaTags; j++){
				if (random.nextFloat() < 0.8){
					tag = tags.get(random.nextInt(tamanhoDaFaixaTag) + (int) (tamanhoDaFaixaTag*numeroDaFaixa));
				} else {
					tag = tags.get(random.nextInt(sizeTag));
				}
				location.getListRelateLocationTag().add(generateRelateLocationTag(location, tag));
			}
			managerServer.editLocation(location);
		}
	}
	
	private void geraRelacaoPersonPersonLocation(Person person1, Person person2, Location location, int tipo) {
		int status1 = 0;
		int status2 = 0;
		int status3 = 0;
		int status4 = 0;
		int status5 = 0;
		int status6 = 0;
		if(tipo == 1) {
			status1 = RelatePersonPerson.STATUS_FRIEND;
			status2 = status1;
			status3 = RelatePersonLocation.STATUS_FRIEND_START;
			status4 = status3;
			status5 = Context.TYPE_PERSON_FRIEND_START;
			status6 = status5;
		}
		else if(tipo == 2) {
			status1 = RelatePersonPerson.STATUS_REQUEST;
			status2 = RelatePersonPerson.STATUS_REQUESTED;
			status3 = RelatePersonLocation.STATUS_FRIEND_REQUEST;
			status4 = RelatePersonLocation.STATUS_FRIEND_REQUESTED;
			status5 = Context.TYPE_PERSON_FRIEND_REQUEST;
			status6 = Context.TYPE_PERSON_FRIEND_REQUESTED;

		}
		else if(tipo == 3) {
			status1 = RelatePersonPerson.STATUS_VIEW;
			status2 = RelatePersonPerson.STATUS_VIEWED;
			status3 = RelatePersonLocation.STATUS_FRIEND_VISIT;
			status4 = RelatePersonLocation.STATUS_FRIEND_VISITED;
			status5 = Context.TYPE_PERSON_FRIEND_VIEW;
			status6 = Context.TYPE_PERSON_FRIEND_VIEWED;

		}
		else if(tipo == 4) {
			status1 = RelatePersonPerson.STATUS_REJECT;
			status2 = RelatePersonPerson.STATUS_REJECTED;
			status3 = RelatePersonLocation.STATUS_FRIEND_REJECT;
			status4 = RelatePersonLocation.STATUS_FRIEND_REJECTED;
			status5 = Context.TYPE_PERSON_FRIEND_REJECT;
			status6 = Context.TYPE_PERSON_FRIEND_REJECTED;

		}
		else if(tipo == 5) {
			status1 = RelatePersonPerson.STATUS_DELETE;
			status2 = RelatePersonPerson.STATUS_DELETED;
			status3 = RelatePersonLocation.STATUS_FRIEND_DELETE;
			status4 = RelatePersonLocation.STATUS_FRIEND_DELETED;
			status5 = Context.TYPE_PERSON_FRIEND_DELETE;
			status6 = Context.TYPE_PERSON_FRIEND_DELETED;
		}
		
		//Construção da 1º Relação Person-Person
		RelatePersonPerson relatePersonPerson1 = generateRelatePersonPerson(person1, person2, status1);

		//Construção da 2ª Relação Person-Person
		RelatePersonPerson relatePersonPerson2 = generateRelatePersonPerson(person2, person1, status2);

		//Construção da Relação Person1-Location
		RelatePersonLocation relatePersonLocation1 = generateRelatePersonLocation(person1, location, status3);
		
		//Construção da Relação Person2-Location
		RelatePersonLocation relatePersonLocation2 = generateRelatePersonLocation(person2, location, status4);
		
		// Construção do contexto de Person1
		Context context1 = generateContexto(person1, null, location, status5);
		
		// Construção do contexto de Person2
		Context context2 = generateContexto(person2, null, location, status6);
		
		// Adição das Relações às Entidades
		person1.getListRelatePersonPerson().add(relatePersonPerson1);
		person1.getListRelatePersonLocation().add(relatePersonLocation1);
		//person1.getListContexts().add(context1);
		managerServer.editPerson(person1);
		
		person2.getListRelatePersonPerson().add(relatePersonPerson2);
		//person2.getListRelatePersonPerson().add(relatePersonPerson2);
		person2.getListRelatePersonLocation().add(relatePersonLocation2);
		//person2.getListContexts().add(context2);
		managerServer.editPerson(person2, context2);
		
		location.getListRelatePersonLocation().add(relatePersonLocation1);
		location.getListRelatePersonLocation().add(relatePersonLocation2);
		managerServer.editLocation(location);
		
	}

	private void geraRelacaoPersonContentLocation(Person person, Content content, Location location, int tipo) {
		int status1 = 0;
		int status2 = 0;
		int status3 = 0;
		int status4 = 0;
		if(tipo == 1) {
			status1 = RelatePersonContent.STATUS_CONTENT_FAVORITE;
			status2 = RelatePersonLocation.STATUS_CONTENT_FAVORITE;
			status3 = RelateContentLocation.STATUS_CONTENT_FAVORITED;
			status4 = Context.TYPE_POST_ADDED;
		}
		else if(tipo == 2) {
			status1 = RelatePersonContent.STATUS_CONTENT_VALUED;
			status2 = RelatePersonLocation.STATUS_CONTENT_VALUED;
			status3 = RelateContentLocation.STATUS_CONTENT_VALUED;
			status4 = Context.TYPE_POST_RATED;
		}
		else if(tipo == 3) {
			status1 = RelatePersonContent.STATUS_CONTENT_VISUALIZED;
			status2 = RelatePersonLocation.STATUS_CONTENT_VISUALIZED;
			status3 = RelateContentLocation.STATUS_CONTENT_VISUALIZED;
			status4 = Context.TYPE_POST_VIEWED;
		}
		else if(tipo == 4) {
			status1 = RelatePersonContent.STATUS_CONTENT_REJECTED;
			status2 = RelatePersonLocation.STATUS_CONTENT_REJECTED;
			status3 = RelateContentLocation.STATUS_CONTENT_REJECTED;
			status4 = Context.TYPE_POST_REJECTED;
		}

		//Construção da Relação Person-Content
		RelatePersonContent relatePersonContent = generateRelatePersonContent(person, content, status1);

		//Construção da Relação Person-Location
		RelatePersonLocation relatePersonLocation = generateRelatePersonLocation(person, location, status2);

		//Construção da Relação Content-Location
		RelateContentLocation relateContentLocation = generateRelateContentLocation(content, location, status3);
		
		// Construção do contexto de Person
		Context context = generateContexto(person, content, location, status4);
			
		// Adição das Relações às Entidades
		person.getListRelatePersonContent().add(relatePersonContent);
		person.getListRelatePersonLocation().add(relatePersonLocation);
		person.getListContexts().add(context);
		managerServer.editPerson(person);
		
		content.getListRelatePersonContent().add(relatePersonContent);
		content.getListRelateContentLocation().add(relateContentLocation);
		managerServer.editContent(content);

		location.getListRelatePersonLocation().add(relatePersonLocation);
		location.getListRelateContentLocation().add(relateContentLocation);
		managerServer.editLocation(location);
		
	}

	private void geraRelacaoPersonLocation(Person person, Location location, int tipo) {
		int status1 = 0;
		int status2 = 0;
		if(tipo == 1) {
			status1 = RelatePersonLocation.STATUS_LOCATION_FAVORITE;
			status2 = Context.TYPE_LOCATION_ADDED;
		}
		else if(tipo == 2) {
			status1 = RelatePersonLocation.STATUS_LOCATION_PRESENCE;
			status2 = Context.TYPE_LOCATION_VIEWED;
		}
		else if(tipo == 3) {
			status1 = RelatePersonLocation.STATUS_LOCATION_VALUED;
			status2 = Context.TYPE_LOCATION_RATED;
		}
		//Construção da Relação Person-Location
		RelatePersonLocation relatePersonLocation = generateRelatePersonLocation(person, location, status1);
		
		// Construção do contexto de Person
		Context context = generateContexto(person, null, location, status2);
			
		// Adição das Relações às Entidades
		person.getListRelatePersonLocation().add(relatePersonLocation);
		person.getListContexts().add(context);
		managerServer.editPerson(person);

		location.getListRelatePersonLocation().add(relatePersonLocation);
		managerServer.editLocation(location);
		
	}

	private void geraRelacaoContentLocation(Content content, Location location, int tipo) {
		int status1 = 0;
		if(tipo == 1) {
			status1 = RelateContentLocation.STATUS_ABOUT;
		}
		else if(tipo == 2) {
			status1 = RelateContentLocation.STATUS_CITED;
		}
		else if(tipo == 3) {
			status1 = RelateContentLocation.STATUS_RELATED;
		}
		//Construção da Relação Person-Location
		RelateContentLocation relateContentLocation = generateRelateContentLocation(content, location, status1);
		
		// Adição das Relações às Entidades
		content.getListRelateContentLocation().add(relateContentLocation);
		managerServer.editContent(content);

		location.getListRelateContentLocation().add(relateContentLocation);
		managerServer.editLocation(location);
		
	}
		
	private boolean alreadyAssociatedPP(Person person, List<RelatePersonPerson> listRelatePersonPerson) {

		boolean result = false;

		for (RelatePersonPerson rpp : listRelatePersonPerson) {
			if (person.getId() == rpp.getPerson2().getId()) {
				result = true;
			}
		}

		return result;
	}

	private boolean alreadyAssociatedPC(Content content, List<RelatePersonContent> listRelatePersonContent) {

		boolean result = false;
		
		for (RelatePersonContent rpc : listRelatePersonContent) {
			if (content.getId() == rpc.getContent().getId()) {
				result = true;
			}
		}

		return result;
	}

	private Context generateContexto(Person person, Content content, Location location, int status) {
		Context context = new Context();
		context.setPerson(person);
		context.setLocation(location);
		context.setContent(null);
		context.setDevice(new Device());
		context.setTimeStamp(null);
		context.setBattery(50);
		context.setConnection(Context.CONNECTION_WIFI);
		context.setConnectionSpeed(300);		
		context.setPersonSpeed(0);
		context.setType(status);
				
		return context;
	}

	private RelatePersonPerson generateRelatePersonPerson(Person person1, Person person2, int status) 
	{
		RelatePersonPerson relatePersonPerson = new RelatePersonPerson();
		relatePersonPerson.setPerson1(person1);
		relatePersonPerson.setPerson2(person2);
		relatePersonPerson.setAffinityRate(0);
		relatePersonPerson.setCommomPersons(0);
		relatePersonPerson.setStatus(status);
		relatePersonPerson.setDateRelation(null);
		//relatePersonPerson.setListMessages(null);
		relatePersonPerson.setTimes(null);
	
		return relatePersonPerson;
	}
	
	private RelatePersonContent generateRelatePersonContent(Person person, Content content, int status) 
	{
		RelatePersonContent relatePersonContent = new RelatePersonContent();
		
		relatePersonContent.setPerson(person);
		relatePersonContent.setContent(content);
		relatePersonContent.setStatus(status);
		relatePersonContent.setDateRelation(null);
		relatePersonContent.setFinality(RelatePersonContent.FINALITY_EDUC);
		//relatePersonContent.setListComments(null);
		relatePersonContent.setRatePerson(0);
		relatePersonContent.setTimes(null);
		relatePersonContent.setScoreGroupPosts(0);
		relatePersonContent.setScoreChallengeTotal(0);
		relatePersonContent.setSuggestedMethod(0);
	
		return relatePersonContent;
	}

	private RelatePersonLocation generateRelatePersonLocation(Person person, Location location, int status) 
	{
		RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
		relatePersonLocation.setLocation(location);
		relatePersonLocation.setPerson(person);
		relatePersonLocation.setStatus(status);
		relatePersonLocation.setFinality(RelatePersonLocation.FINALITY_OTHER);
		relatePersonLocation.setDateRelation(null);
		relatePersonLocation.setRatePerson(0);
		relatePersonLocation.setTimes(null);
		//relatePersonLocation.setListComments(null);
	
		return relatePersonLocation;
	}
		
	private RelateContentLocation generateRelateContentLocation(Content content, Location location, int status) 
	{
		RelateContentLocation relateContentLocation = new RelateContentLocation();

		relateContentLocation.setContent(content);
		relateContentLocation.setLocation(location);
		relateContentLocation.setStatus(status);
		relateContentLocation.setDateRelation(null);
	
		return relateContentLocation;
	}

	private RelatePersonTag generateRelatePersonTag(Person person, Tag tag) 
	{
		RelatePersonTag relatePersonTag = new RelatePersonTag();

		relatePersonTag.setPerson(person);
		relatePersonTag.setTag(tag);
		relatePersonTag.setCountRel(1);
	
		return relatePersonTag;
	}

	private RelateContentTag generateRelateContentTag(Content content, Tag tag) 
	{
		RelateContentTag relateContentTag = new RelateContentTag();

		relateContentTag.setContent(content);
		relateContentTag.setTag(tag);
		relateContentTag.setCountRel(1);
	
		return relateContentTag;
	}
	
	private RelateLocationTag generateRelateLocationTag(Location location, Tag tag) 
	{
		RelateLocationTag relateLocationTag = new RelateLocationTag();

		relateLocationTag.setLocation(location);
		relateLocationTag.setTag(tag);
		relateLocationTag.setCountRel(1);
	
		return relateLocationTag;
	}

	/*
	private int geraTipoRelatePersonPersonLocation() {

		Random random = new Random();
		Float sorte = random.nextFloat()*10;
		Integer tipo = 0;
		if (sorte < 5)
			tipo = 1;
		else if	(sorte < 6)
			tipo = 2;
		else if	(sorte < 8)
			tipo = 4;
		else if	(sorte < 10)
			tipo = 5;
		return tipo;
		
	}

	*/

	private int geraTipoRelatePersonContentLocation() {

		Random random = new Random();
		Float sorte = random.nextFloat()*10;
		Integer tipo = 0;
		if (sorte < 6)
			tipo = 3;
		else if	(sorte < 9)
			tipo = 1;
		else if	(sorte < 10)
			tipo  = 2;
		return tipo;		
	}
	
	private int geraTipoRelatePersonLocation() {

		Random random = new Random();
		Float sorte = random.nextFloat()*10;
		Integer tipo = 0;
		if (sorte < 6)
			tipo = 2;
		else if	(sorte < 8)
			tipo = 3;
		else if	(sorte < 10)
			tipo = 1;
		return tipo;		
	}
	
	private int geraTipoRelateContentLocation() {

		Random random = new Random();
		Float sorte = random.nextFloat()*10;
		Integer tipo = 0;
		if (sorte < 6)
			tipo = 3;
		else if	(sorte < 9)
			tipo = 2;
		else if	(sorte < 10)
			tipo  = 1;
		return tipo;
		
	}
	
}

