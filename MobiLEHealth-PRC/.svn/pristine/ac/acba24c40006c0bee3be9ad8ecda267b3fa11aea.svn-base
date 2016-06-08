package mobilehealth.prc.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.prc.api.external.IUbi;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.ExternalAccounts;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.Tag;
import mobilehealth.prc.prc.IRecommender;
import mobilehealth.prc.prc.Recommend;

/*
 * OBS: todas as regras de negocio devem se concentrar aqui em Manager. 
 * Caso a classe fique muito grande, criar managers especializados.
 */

public class ManagerServer implements IUbi
{
	private static ManagerServer instance;
	private IRecommender iRecommender;
	//private IData iData;
	//private IAnalyzer iAnalyzer;
	//private ICollector iCollector;
	
	private ManagerServerContent managerServerContent;
	private ManagerServerLocation managerServerLocation;
	private ManagerServerPerson managerServerPerson;
	private ManagerServerTag managerServerTag;
	private ManagerServerSession managerServerSession;
	private ManagerServerExternalaccounts managerServerExternalaccounts;
	//private ManagerServerTag managerServerTag;
	
	private ManagerServer()
	{
		this.iRecommender = new Recommend();
		this.managerServerContent = ManagerServerContent.getInstance();
		this.managerServerLocation = ManagerServerLocation.getInstance();
		this.managerServerPerson = ManagerServerPerson.getInstance();
		this.managerServerTag = ManagerServerTag.getInstance();
		this.managerServerSession = ManagerServerSession.getInstance();
		this.managerServerExternalaccounts = ManagerServerExternalaccounts.getInstance();

		//this.iData = DataEclipseLink.getInstance();
		//this.iCollector = new Collector();
		//this.iAnalyzer = new Analyzer();
	}
	
    public static ManagerServer getInstance()
    {
        if (instance == null) {
            instance = new ManagerServer();
        }
        return instance;
    }
	
	// Uma vez que a pessoa passa a existir, atualizar periodicamente seu perfil
	// TODO Pendente: definir politica de atualizacao do perfil pelo Analyzer (LuizJr ou Argemiro).
	// TODO Pendente: criar Thread periodica
	// TODO Duvida: as listas de recomendacao sao atualizadas aqui no manager? ou atualizadas dentro de updateProfile() do Analyzer?
    
	//-----------------------------------------------------------------------------------------------------
	// SESSION
	//-----------------------------------------------------------------------------------------------------
    @Override
    public boolean login(String email, String passEncripted, Context contexto) {
    	return managerServerSession.login(email, passEncripted, contexto);
	}
	
	@Override
	public boolean logout(long idPerson, Context contexto) {
		return managerServerSession.logout(idPerson, contexto);
	}

	//-----------------------------------------------------------------------------------------------------
	// PERSON
	//-----------------------------------------------------------------------------------------------------
	@Override
	public Person getPerson(long idPerson) {
		return managerServerPerson.getPerson(idPerson);
	}
	
	@Override
	public Person getPerson(String email) {
		return managerServerPerson.getPerson(email);
	}
	
	@Override
	public List<Person> getAllPerson() {
		return managerServerPerson.getAllPerson();
	}

	@Override
	public int getCountPerson() {
		return managerServerPerson.getCountPerson();
	}
	
	@Override
	public List<Person> searchPerson(long idPerson, Object param, Context contexto) {
		return managerServerPerson.searchPerson(idPerson, param, contexto);
	}
	
	@Override
	public boolean createPerson(Person person, Context contexto) {
		return managerServerPerson.createPerson(person, contexto);
	}

	public boolean createPerson(Person person) {
		return managerServerPerson.createPerson(person);
	}

	@Override
	public boolean editPerson(Person person, Context contexto) {
		return managerServerPerson.editPerson(person, contexto);
	}

	public boolean editPerson(Person person) {
		return managerServerPerson.editPerson(person);
	}
	
	@Override
	public boolean removePerson(long idPerson, Context contexto) {
		return managerServerPerson.removePerson(idPerson, contexto);
	}
	
	@Override
	public boolean viewPerson(long idPerson1, long idPerson2, Context contexto) {
		return managerServerPerson.viewPerson(idPerson1, idPerson2, contexto);
	}
	
	@Override
	public boolean rejectRecommPerson(long idPerson1, long idPerson2, Context contexto) {		
		return managerServerPerson.rejectRecommPerson(idPerson1, idPerson2, contexto);
	}
	
	@Override
	public boolean requestFriendship(long idPerson1, long idPerson2, Context contexto) {
		return managerServerPerson.requestFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean cancelRequestFriendship(long idPerson1, long idPerson2, Context contexto) {
		return managerServerPerson.cancelRequestFriendship(idPerson1, idPerson2, contexto);
	}
	
	@Override
	public boolean rejectFriendship(long idPerson1, long idPerson2, Context contexto) {
		return managerServerPerson.rejectFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean acceptFriendship(long idPerson1, long idPerson2, Context contexto) {
		return managerServerPerson.acceptFriendship(idPerson1, idPerson2, contexto);
	}
	
	@Override
	public boolean removeFriendship(long idPerson1, long idPerson2, Context contexto) {
		return managerServerPerson.removeFriendship(idPerson1, idPerson2, contexto);
	}
	
	@Override
	public boolean sendMessage(long idPerson1, long idPerson2, String message, Context contexto) {
		return managerServerPerson.sendMessage(idPerson1, idPerson2, message, contexto);
	}

	//-----------------------------------------------------------------------------------------------------
	// CONTENT
	//-----------------------------------------------------------------------------------------------------
	@Override
	public Content getContent(long id) {
		return managerServerContent.getContent(id);
	}	
	
	@Override
	public List<Content> getAllContent() {
		return managerServerContent.getAllContent();
	}

	@Override
	public int getCountContent() {
		return managerServerContent.getCountContent();
	}
	
	@Override
	public List<Content> searchContent(long idPerson, Object param, int contentType, Context contexto) {
		return managerServerContent.searchContent(idPerson, param, contentType, contexto);
	}
	
	@Override
	public boolean createContent(long idPerson, Content content, Context contexto) {
		return managerServerContent.createContent(idPerson, content, contexto);
	}
		
	public boolean createContent(Content content) {
		return managerServerContent.createContent(content);
	}
	
	@Override
	public boolean editContent(long idPerson, Content content, Context contexto) {
		return managerServerContent.editContent(idPerson, content, contexto);
	}

	public boolean editContent(Content content) {
		return managerServerContent.editContent(content);
	}

	@Override
	public boolean removeContent(long idPerson, long idContent, Context contexto) {
		return managerServerContent.removeContent(idPerson, idContent, contexto);
	}
	
	@Override
	public boolean viewContent(long idPerson, long idContent, Context contexto) {
		return managerServerContent.viewContent(idPerson, idContent, contexto);
	}

	@Override
	public boolean rejectRecommContent(long idPerson, long idContent, Context contexto) {
		return managerServerContent.rejectRecommContent(idPerson, idContent, contexto);
	}
	
	@Override
	public boolean addContent(long idPerson, long idContent, Context contexto) {
		return managerServerContent.addContent(idPerson, idContent, contexto);
	}

	@Override
	public boolean rateContent(long idPerson, long idContent, int rateFromPerson, Context contexto) {
		return managerServerContent.rateContent(idPerson, idContent, rateFromPerson, contexto);
	}
	
	@Override
	public boolean commentContent(long idPerson, long idContent, String comment, Context contexto) {
		return managerServerContent.commentContent(idPerson, idContent, comment, contexto);
	}
	
	//-----------------------------------------------------------------------------------------------------
	// LOCATION
	//-----------------------------------------------------------------------------------------------------
	@Override
	public Location getLocation(long id) {
		return managerServerLocation.getLocation(id);
	}
	
	@Override
	public List<Location> getAllLocation() {
		return managerServerLocation.getAllLocation();
	}

	@Override
	public int getCountLocation() {
		return managerServerLocation.getCountLocation();
	}
	
	@Override
	public List<Location> searchLocation(long idPerson, Object param, Context contexto) {
		return managerServerLocation.searchLocation(idPerson, param, contexto);
	}
	
	@Override
	public boolean createLocation(long idPerson, Location location, Context contexto) {
		return managerServerLocation.createLocation(idPerson, location, contexto);
	}
	
	@Override
	public boolean createLocation(Location location) {
		return managerServerLocation.createLocation(location);
	}
	
	@Override
	public boolean editLocation(long idPerson, Location location, Context contexto) {
		return managerServerLocation.editLocation(idPerson, location, contexto);
	}
	
	public boolean editLocation( Location location) {
		return managerServerLocation.editLocation(location);
	}

	@Override
	public boolean removeLocation(long idPerson, long idLocation, Context contexto) {
		return managerServerLocation.removeLocation(idPerson, idLocation, contexto);
	}
	
	@Override
	public boolean viewLocation(long idPerson, long idLocation, Context contexto) {
		return managerServerLocation.viewLocation(idPerson, idLocation, contexto);
	}

	@Override
	public boolean rejectRecommLocation(long idPerson, long idLocation, Context contexto) {
		return managerServerLocation.rejectRecommLocation(idPerson, idLocation, contexto);
	}
	
	@Override
	public boolean addLocation(long idPerson, long idLocation, Context contexto) {
		return managerServerLocation.addLocation(idPerson, idLocation, contexto);
	}

	@Override
	public boolean rateLocation(long idPerson, long idLocation, int rate, Context contexto) {
		return managerServerLocation.rateLocation(idPerson, idLocation, rate, contexto);
	}

	@Override
	public boolean commentLocation(long idPerson, long idLocation, String comment, Context contexto) {
		return managerServerLocation.commentLocation(idPerson, idLocation, comment, contexto);
	}

	//-----------------------------------------------------------------------------------------------------
	// TAGS
	//-----------------------------------------------------------------------------------------------------

	@Override
	public boolean createAndUpdateTags(String text, Person person, Content content, Location location) {
		return managerServerTag.createAndUpdateTags(text, person, content, location);
	}

	@Override
	public boolean createTags(String text) {
		return managerServerTag.createTags(text);		
	}

	@Override
	public List<Tag> getAllTag() {
		return managerServerTag.getAllTag();
	}

	//-----------------------------------------------------------------------------------------------------
	// EXTERNALACCOUNTS
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean addExternalAccount(long idperson, ExternalAccounts externalAccounts, Context contexto) {
		return managerServerExternalaccounts.addExternalAccount(idperson, externalAccounts, contexto);
	}
		
	@Override
	public boolean removeExternalAccount(long idPerson, String accountName, Context contexto) {
		return managerServerExternalaccounts.removeExternalAccount(idPerson, accountName, contexto);
	}
		
	@Override
	public List<ExternalAccounts> getAllExternalAccounts() {
		return managerServerExternalaccounts.getAllExternalAccounts();
	}	
	
	//-----------------------------------------------------------------------------------------------------
	// RECOMMENDER
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean gerarListaColaboradores(int tamanhoListaColaboradores) {
		return iRecommender.gerarListaColaboradores(tamanhoListaColaboradores);
	}
	
	@Override
	public boolean gerarListaColaborativa(int tamanhoListaColaborativa) {
		return iRecommender.gerarListaColaborativa(tamanhoListaColaborativa);
	}

	@Override
	public boolean gerarListaBaseadaConteudo(int tamanhoListaBaseadaConteudo) {
		return iRecommender.gerarListaBaseadaConteudo(tamanhoListaBaseadaConteudo);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarListaHibrida(Person person, ArrayList<RelatePersonContent> ubiqua) {
		return iRecommender.gerarListaHibrida(person, ubiqua);
	}
	
	@Override
	public ArrayList<RelatePersonContent> gerarListaConjunta(ArrayList<RelatePersonContent> listaHibrida) {
		return iRecommender.gerarListaConjunta(listaHibrida);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarRecomendacao(Person person, ArrayList<RelatePersonContent> ubiqua) {
		return iRecommender.gerarRecomendacao(person, ubiqua);
	}


	

	/*
	@Override
	public boolean insertPerson(Person person) {
		return iData.insertPerson(person);
	}
	
	@Override
	public boolean removePerson(long id) {
		return this.iData.removePerson(id);
	}
	
	@Override
	public boolean setPerson(Person person) {
		return this.iData.setPerson(person);
	}
	
	@Override
	public Person getPerson(String email) {
		return this.iData.getPerson(email);
	}
	
	@Override
	public Person getPerson(long id) {
		return this.iData.getPerson(id);
	}
	
	@Override
	public List<Person> getAllPersons() {
		return this.iData.getAllPerson();
	}
	
	@Override
	public List<Person> searchPerson(String param) {
		return this.iData.searchPerson(param);
	}
	
	@Override
	public int getCountPerson() {
		return this.iData.getCountPerson();
	}
	
	//-----------------------------------------------------------------------------------------------------
	// CONTENT
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean insertContent(Content content) {
		return this.iData.insertContent(content);
	}

	@Override
	public boolean removeContent(long id) {
		return this.iData.removeContent(id);
	}

	@Override
	public boolean setContent(Content content) {
		return this.iData.setContent(content);
	}

	@Override
	public Content getContent(long id) {
		return this.iData.getContent(id);
	}

	@Override
	public List<Content> getAllContents() {
		return iData.getAllContent();
	}
	
	@Override
	public List<Content> searchContent(String param, int type) {
		return this.iData.searchContent(param);
	}

	@Override
	public int getCountContent() {
		return iData.getCountContent();
	}

	//-----------------------------------------------------------------------------------------------------
	// CONTEXT
	//-----------------------------------------------------------------------------------------------------
	@Override
	public Context getContext(long id) {
		return null;
	}

	@Override
	public List<Context> getAllContexts(long personId) {
		return null;
	}
	
	//-----------------------------------------------------------------------------------------------------
	// TAG
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean addTagToPerson(String name, Person person) 
	{
		boolean flagOK = false;
		
		// Obtem TAG
		Tag tag = iData.getTag(name);
		
		// TAG ja existe na lista de pessoa?
		ArrayList<Tag> listTags = (ArrayList<Tag>) person.getListTags();
		if( listTags.contains(tag) == true )
		{
			//System.out.println("TAG ja existe na lista de tags dessa pessoa. ");
		}
		else
		{
			// TAG ja existe no BD?
			if(tag == null)
			{
				// Se nao: cria novo elemento
				tag = new Tag();
				tag.setName(name);
				tag.setCount(1);
				iData.insertTag(tag);
				flagOK = true;
			}
			else
			{
				// Se sim: atualiza Tag (incrementa contador)
				tag.setCount( tag.getCount() + 1);
				iData.setTag(tag);
				flagOK = true;
			}
		}
		
		return flagOK;
	}
	
	@Override
	public boolean addTagToContent(String name, Content content) 
	{
		boolean flagOK = false;
		
		// Obtem TAG
		Tag tag = iData.getTag(name);
		
		// TAG ja existe na lista de conteudo?
		ArrayList<Tag> listTags = (ArrayList<Tag>) content.getListTags();
		if( listTags.contains(tag) == true )
		{
			//System.out.println("TAG ja existe na lista de tags desse conteudo. ");
		}
		else
		{
			// TAG já existe?
			if(tag == null)
			{
				// Cria novo elemento
				tag = new Tag();
				tag.setName(name);
				tag.setCount(1);
				content.getListTags().add(tag);
				iData.setContent(content);
			}
			else
			{
				// Atualiza Tag (incrementa contador)
				tag.setCount( tag.getCount() + 1);
				iData.setTag(tag);
			}
		}
		
		return flagOK;
	}
	
	@Override
	public boolean removeTagFromPerson(String name, Person person) 
	{
		boolean flagOK = false;
		
		Tag tag = iData.getTag(name);
		
		if(tag == null) 
		{
			//System.out.println("Esse nome de TAG nao existe. ");
		}
		else
		{
			person.getListTags().remove(tag);
			flagOK = iData.setPerson(person);
		}
		
		return flagOK;
	}
	
	@Override
	public boolean removeTagFromContent(String name, Content content) 
	{
		boolean flagOK = false;
		
		Tag tag = iData.getTag(name);
		
		if(tag == null) 
		{
			//System.out.println("Esse nome de TAG nao existe. ");
		}
		else
		{
			content.getListTags().remove(tag);
			flagOK = iData.setContent(content);
		}
		
		return flagOK;
	}
	

	@Override
	public Tag getTag(long id) {
		return iData.getTag(id);
	}

	@Override
	public Tag getTag(String name) {
		return iData.getTag(name);
	}

	@Override
	public List<Tag> getAllTags() {
		return iData.getAllTag();
	}

	//-----------------------------------------------------------------------------------------------------
	// DEVICE
	//-----------------------------------------------------------------------------------------------------
	@Override
	public Device getDevice(long id) {
		return iData.getDevice(id);
	}
	
	@Override
	public Device getDevice(String imei) {
		return iData.getDevice(imei);
	}

	@Override
	public List<Device> getAllDevice() {
		return iData.getAllDevice();
	}

	@Override
	public List<Device> searchDevice(Object param) {
		return iData.searchDevice(param);
	}
	
	//-----------------------------------------------------------------------------------------------------
	// LOCATION
	//-----------------------------------------------------------------------------------------------------
	@Override
	public Location getLocation(long id) {
		return iData.getLocation(id);
	}

	@Override
	public boolean insertLocation(Location location) {
		return iData.insertLocation(location);
	}

	@Override
	public boolean removeLocation(long id) {
		return iData.removeLocation(id);
	}

	@Override
	public boolean setLocation(Location location) {
		return iData.setLocation(location);
	}
	
	@Override
	public List<Location> getAllLocation() {
		return iData.getAllLocation();
	}

	@Override
	public List<Location> searchLocation(Object param) {
		return iData.searchLocation(param);
	}

	@Override
	public int getCountLocation() {
		return iData.getCountLocation();
	}
	
	/*
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonPerson - crud
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean insertRelatePersonPerson(RelatePersonPerson relatePersonPerson) {
		return this.iData.insertRelatePersonPerson(relatePersonPerson);
	}
	
	@Override
	public boolean setRelatePersonPerson(RelatePersonPerson relatePersonPerson) {
		return this.iData.setRelatePersonPerson(relatePersonPerson);
	}
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonContent - crud
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean insertRelatePersonContent(RelatePersonContent relatePersonContent) {
		return this.iData.insertRelatePersonContent(relatePersonContent);
	}
	
	@Override
	public boolean setRelatePersonContent(RelatePersonContent relatePersonContent) {
		return this.iData.setRelatePersonContent(relatePersonContent);
	}
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonLocation - crud
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean insertRelatePersonLocation(RelatePersonLocation relatePersonLocation) {
		return false;
	}

	@Override
	public boolean setRelatePersonLocation(RelatePersonLocation relatePersonLocation) {
		return false;
	}

	//-----------------------------------------------------------------------------------------------------
	// RelateContentLocation - crud
	//-----------------------------------------------------------------------------------------------------
	@Override
	public boolean insertRelateContentLocation(RelateContentLocation relateContentLocation) {
		return false;
	}

	@Override
	public boolean setRelateContentLocation(RelateContentLocation relateContentLocation) {
		return false;
	}
	*/
	
}
