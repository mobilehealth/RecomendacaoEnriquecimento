package mobilehealth.prc.api.external;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.ExternalAccounts;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.Tag;

public interface IUbi 
{	
	// TODO Cliente Android: ver se precisa de mais algum metodo?
	
	//Session
	public boolean login(String email, String pass, Context contexto);
	public boolean logout(long idPerson, Context contexto);
	// Person
	public Person getPerson(long idPerson);
	public Person getPerson(String email);
	public List<Person> getAllPerson();
	public int getCountPerson();
	public List<Person> searchPerson(long idPerson, Object param, Context contexto);
	public boolean createPerson(Person person, Context contexto);
	public boolean editPerson(Person person, Context contexto);
	public boolean editPerson(Person person);
	public boolean createPerson(Person person);
	public boolean removePerson(long idPerson, Context contexto);
	public boolean viewPerson(long idPerson1, long idPerson2, Context contexto);
	public boolean rejectRecommPerson(long idPerson1, long idPerson2, Context contexto);
	public boolean requestFriendship(long idPerson1, long idPerson2, Context contexto);
	public boolean cancelRequestFriendship(long idPerson1, long idPerson2, Context contexto);
	public boolean rejectFriendship(long idPerson1, long idPerson2, Context contexto);
	public boolean acceptFriendship(long idPerson1, long idPerson2, Context contexto);
	public boolean removeFriendship(long idPerson1, long idPerson2, Context contexto);
	public boolean sendMessage(long idPerson1, long idPerson2, String messages, Context contexto);
	
	// Content
	public Content getContent(long id);
	public List<Content> getAllContent();
	public int getCountContent();
	public List<Content> searchContent(long idPerson, Object param, int contentType, Context contexto);
	public boolean createContent(long idPerson, Content content, Context contexto);
	public boolean createContent(Content content);
	public boolean editContent(long idPerson, Content content, Context contexto);
	boolean editContent(Content content);
	public boolean removeContent(long idPerson, long idContent, Context contexto);
	public boolean viewContent(long idPerson, long idContent, Context contexto);
	public boolean rejectRecommContent(long idPerson, long idContent, Context contexto);
	public boolean addContent(long idPerson, long idContent, Context contexto);
	public boolean rateContent(long idPerson, long idContent, int rate, Context contexto);
	public boolean commentContent(long idPerson, long idContent, String comment, Context contexto);
	// Location
	public Location getLocation(long id);
	public List<Location> getAllLocation();
	public int getCountLocation();
	public List<Location> searchLocation(long idPerson, Object param, Context contexto);
	public boolean createLocation(long idPerson, Location location, Context contexto);
	public boolean createLocation(Location location);
	public boolean editLocation(long idPerson, Location location, Context contexto);
	public boolean editLocation(Location location);
	public boolean removeLocation(long idPerson, long idLocation, Context contexto);
	public boolean viewLocation(long idPerson, long idLocation, Context contexto);
	public boolean rejectRecommLocation(long idPerson, long idLocation, Context contexto);
	public boolean addLocation(long idPerson, long idLocation, Context contexto);
	public boolean rateLocation(long idPerson, long idLocation, int rate, Context contexto);
	public boolean commentLocation(long idPerson, long idLocation, String comment, Context contexto);
	// Tags
	public boolean createAndUpdateTags(String text, Person person, Content content, Location location);
	public boolean createTags(String text);
	public List<Tag> getAllTag();
	// Externalaccounts
	public boolean addExternalAccount(long idperson, ExternalAccounts externalAccounts, Context contexto);
	public boolean removeExternalAccount(long idPerson, String accountName, Context contexto);
	public List<ExternalAccounts> getAllExternalAccounts();
	
	
	//-----------------------------------------------------------------------------------------------------
	// RECOMMENDER
	//-----------------------------------------------------------------------------------------------------
	// TODO Bruno, Luiz e Antonio: revisar quais desses metodos sao realmente necessarios para o cliente.
	/**
	 * Gera e persiste no BD lista de "colaboradores" (pessoas com conteudos semelhantes aos do usuario).
	 */
	boolean gerarListaColaboradores(int tamanhoListaColaboradores);
	
	/**
	 * Gera e persiste no BD lista de conteudos recomendados com base na lista de "colaboradores".
	 */
	boolean gerarListaColaborativa(int tamanhoListaColaborativa);
	
	/**
	 * Gera e persiste no BD lista de conteudos recomendados com base apenas na semelhança de conteudos com o usuario.
	 */
	boolean gerarListaBaseadaConteudo(int tamanhoListaBaseadaConteudo);
	
	/**
	 * Gera e persiste no BD lista de conteudos recomendados com base no contexto do usuario.
	 */
	ArrayList<RelatePersonContent> gerarListaHibrida(Person person, ArrayList<RelatePersonContent> ubiqua);

	/**
	 * Gera lista de conteudos recomendados com base no contexto do usuario e análise da recomendação conjunta.
	 */
	ArrayList<RelatePersonContent> gerarListaConjunta(ArrayList<RelatePersonContent> listaHibrida);

	/**
	 * Gera lista de conteudos recomendados com base no contexto do usuario (interface para cliente android).
	 */
	ArrayList<RelatePersonContent> gerarRecomendacao(Person person, ArrayList<RelatePersonContent> ubiqua);

	


	
	/*
	
	//-----------------------------------------------------------------------------------------------------
	// PERSON 
	//-----------------------------------------------------------------------------------------------------
	public boolean login(String email, String pass);
	public boolean logout(String email);

	public boolean insertPerson(Person person);
	public boolean removePerson(long id);
	public boolean setPerson(Person person);
	public Person getPerson(long id);
	public Person getPerson(String email);
	public List<Person> searchPerson(String param);
	public List<Person> getAllPersons();
	public int getCountPerson();

	//-----------------------------------------------------------------------------------------------------
	// CONTENT
	//-----------------------------------------------------------------------------------------------------
	public boolean insertContent(Content content);
	public boolean removeContent(long id);
	public boolean setContent(Content content);
	public Content getContent(long id);
	public List<Content> searchContent(String param, int type);
	public List<Content> getAllContents();
	public int getCountContent();
	
	//-----------------------------------------------------------------------------------------------------
	// CONTEXT
	//-----------------------------------------------------------------------------------------------------
	public Context getContext(long id);
	public List<Context> getAllContexts(long idPerson);
	
	//-----------------------------------------------------------------------------------------------------
	// DEVICE
	//-----------------------------------------------------------------------------------------------------
	public Device getDevice(long id);
	public Device getDevice(String imei);
	public List<Device> getAllDevice();
	public List<Device> searchDevice(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// LOCATION
	//-----------------------------------------------------------------------------------------------------
	public Location getLocation(long id);
	public boolean insertLocation(Location location);
	public boolean removeLocation(long id);
	public boolean setLocation(Location location);
	public List<Location> getAllLocation();
	public List<Location> searchLocation(Object param);
	public int getCountLocation();
	
	//-----------------------------------------------------------------------------------------------------
	// TAG
	//-----------------------------------------------------------------------------------------------------
	public boolean addTagToPerson(String name, Person person);
	public boolean addTagToContent(String name, Content content);
	public boolean removeTagFromPerson(String name, Person person);
	public boolean removeTagFromContent(String name, Content content);

	public Tag getTag(long id);
	public Tag getTag(String name);
	public List<Tag> getAllTags();
	
	/*
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonPerson
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelatePersonPerson(RelatePersonPerson relatePersonPerson);
	public boolean setRelatePersonPerson(RelatePersonPerson relatePersonPerson);
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonContent
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelatePersonContent(RelatePersonContent relatePersonContent);
	public boolean setRelatePersonContent(RelatePersonContent relatePersonContent);
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonLocation
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelatePersonLocation(RelatePersonLocation relatePersonLocation);
	public boolean setRelatePersonLocation(RelatePersonLocation relatePersonLocation);
	
	//-----------------------------------------------------------------------------------------------------
	// RelateContentLocation
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelateContentLocation(RelateContentLocation relateContentLocation);
	public boolean setRelateContentLocation(RelateContentLocation relateContentLocation);
	*/

}
