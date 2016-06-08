package mobilehealth.prc.api.external;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.ExternalAccounts;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.Tag;

public class FacadeLocal implements IUbi
{
    private static FacadeLocal instance;
	private ManagerServer manager;
    
    private FacadeLocal() 
    {
    	manager = ManagerServer.getInstance();
    }
    
    public static FacadeLocal getInstance() 
    {
        if (instance == null) {
           instance = new FacadeLocal();
        }
        
        return instance;
     }
	
    
	
	@Override
	public boolean login(String email, String pass, Context contexto) {
		return manager.login(email, pass, contexto);
	}

	@Override
	public boolean logout(long idPerson, Context contexto) {
		return manager.logout(idPerson, contexto);
	}

	@Override
	public Person getPerson(long idPerson) {
		return manager.getPerson(idPerson);
	}

	@Override
	public Person getPerson(String email) {
		return manager.getPerson(email);
	}

	@Override
	public List<Person> getAllPerson() {
		return manager.getAllPerson();
	}
	
	@Override
	public int getCountPerson() {
		return manager.getCountPerson();
	}

	@Override
	public List<Person> searchPerson(long idPerson, Object param, Context contexto) {
		return manager.searchPerson(idPerson, param, contexto);
	}

	@Override
	public boolean createPerson(Person person, Context contexto) {
		return manager.createPerson(person, contexto);
	}

	@Override
	public boolean createPerson(Person person) {
		return manager.createPerson(person);
	}
	
	@Override
	public boolean editPerson(Person person, Context contexto) {
		return manager.editPerson(person, contexto);
	}

	@Override
	public boolean editPerson(Person person) {
		return manager.editPerson(person);
	}

	@Override
	public boolean removePerson(long idPerson, Context contexto) {
		return manager.removePerson(idPerson, contexto);
	}

	@Override
	public boolean viewPerson(long idPerson1, long idPerson2, Context contexto) {
		return manager.viewPerson(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean rejectRecommPerson(long idPerson1, long idPerson2, Context contexto) {
		return manager.rejectRecommPerson(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean requestFriendship(long idPerson1, long idPerson2, Context contexto) {
		return manager.requestFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean cancelRequestFriendship(long idPerson1, long idPerson2, Context contexto) {
		return manager.cancelRequestFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean rejectFriendship(long idPerson1, long idPerson2, Context contexto) {
		return manager.rejectFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean acceptFriendship(long idPerson1, long idPerson2, Context contexto) {
		return manager.acceptFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean removeFriendship(long idPerson1, long idPerson2, Context contexto) {
		return manager.removeFriendship(idPerson1, idPerson2, contexto);
	}

	@Override
	public boolean sendMessage(long idPerson1, long idPerson2, String messages, Context contexto) {
		return manager.sendMessage(idPerson1, idPerson2, messages, contexto);
	}

	@Override
	public Content getContent(long id) {
		return manager.getContent(id);
	}

	@Override
	public List<Content> getAllContent() {
		return manager.getAllContent();
	}

	@Override
	public int getCountContent() {
		return manager.getCountContent();
	}
	
	@Override
	public List<Content> searchContent(long idPerson, Object param, int contentType, Context contexto) {
		return manager.searchContent(idPerson, param, contentType, contexto);
	}

	@Override
	public boolean createContent(long idPerson, Content content, Context contexto) {
		return manager.createContent(idPerson, content, contexto);
	}

	@Override
	public boolean createContent(Content content) {
		return manager.createContent(content);
	}

	@Override
	public boolean editContent(long idPerson, Content content, Context contexto) {
		return manager.editContent(idPerson, content, contexto);
	}

	@Override
	public boolean editContent(Content content) {
		return manager.editContent(content);
	}

	@Override
	public boolean removeContent(long idPerson, long idContent, Context contexto) {
		return manager.removeContent(idPerson, idContent, contexto);
	}

	@Override
	public boolean viewContent(long idPerson, long idContent, Context contexto) {
		return manager.viewContent(idPerson, idContent, contexto);
	}

	@Override
	public boolean rejectRecommContent(long idPerson, long idContent, Context contexto) {
		return manager.rejectRecommContent(idPerson, idContent, contexto);
	}

	@Override
	public boolean addContent(long idPerson, long idContent, Context contexto) {
		return manager.addContent(idPerson, idContent, contexto);
	}

	@Override
	public boolean rateContent(long idPerson, long idContent, int rateFromPerson, Context contexto) {
		return manager.rateContent(idPerson, idContent, rateFromPerson, contexto);
	}

	@Override
	public boolean commentContent(long idPerson, long idContent, String comment, Context contexto) {
		return manager.commentContent(idPerson, idContent, comment, contexto);
	}

	@Override
	public Location getLocation(long id) {
		return manager.getLocation(id);
	}

	@Override
	public List<Location> getAllLocation() {
		return manager.getAllLocation();
	}

	@Override
	public int getCountLocation() {
		return manager.getCountLocation();
	}
	
	@Override
	public List<Location> searchLocation(long idPerson, Object param, Context contexto) {
		return manager.searchLocation(idPerson, param, contexto);
	}

	@Override
	public boolean createLocation(long idPerson, Location location, Context contexto) {
		return manager.createLocation(idPerson, location, contexto);
	}

	@Override
	public boolean createLocation(Location location) {
		return manager.createLocation(location);
	}

	@Override
	public boolean editLocation(long idPerson, Location location, Context contexto) {
		return manager.editLocation(idPerson, location, contexto);
	}

	@Override
	public boolean editLocation(Location location) {
		return manager.editLocation(location);
	}

	@Override
	public boolean removeLocation(long idPerson, long idLocation, Context contexto) {
		return manager.removeLocation(idPerson, idLocation, contexto);
	}

	@Override
	public boolean viewLocation(long idPerson, long idLocation, Context contexto) {
		return manager.viewLocation(idPerson, idLocation, contexto);
	}

	@Override
	public boolean rejectRecommLocation(long idPerson, long idLocation, Context contexto) {
		return manager.rejectRecommLocation(idPerson, idLocation, contexto);
	}

	@Override
	public boolean addLocation(long idPerson, long idLocation, Context contexto) {
		return manager.addLocation(idPerson, idLocation, contexto);
	}

	@Override
	public boolean rateLocation(long idPerson, long idLocation, int rate, Context contexto) {
		return manager.rateLocation(idPerson, idLocation, rate, contexto);
	}

	@Override
	public boolean commentLocation(long idPerson, long idLocation, String comment, Context contexto) {
		return manager.commentLocation(idPerson, idLocation, comment, contexto);
	}

	@Override
	public boolean createAndUpdateTags(String text, Person person, Content content, Location location) {
		return manager.createAndUpdateTags(text, person, content, location);
	}

	@Override
	public boolean createTags(String text) {
		return manager.createTags(text);
	}

	@Override
	public List<Tag> getAllTag() {
		return manager.getAllTag();
	}
	
	@Override
	public boolean addExternalAccount(long idperson, ExternalAccounts externalAccounts, Context contexto) {
		return manager.addExternalAccount(idperson, externalAccounts, contexto);
	}

	@Override
	public boolean removeExternalAccount(long idPerson, String accountName, Context contexto) {
		return manager.removeExternalAccount(idPerson, accountName, contexto);
	}

	@Override
	public List<ExternalAccounts> getAllExternalAccounts() {
		return getAllExternalAccounts();
	}

	@Override
	public boolean gerarListaColaboradores(int tamanhoListaColaboradores) {
		return manager.gerarListaColaboradores(tamanhoListaColaboradores);
	}

	@Override
	public boolean gerarListaColaborativa(int tamanhoListaColaborativa) {
		return manager.gerarListaColaborativa(tamanhoListaColaborativa);
	}

	@Override
	public boolean gerarListaBaseadaConteudo(int tamanhoListaBaseadaConteudo) {
		return manager.gerarListaBaseadaConteudo(tamanhoListaBaseadaConteudo);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarListaHibrida(Person person, ArrayList<RelatePersonContent> ubiqua) {
		return manager.gerarListaHibrida(person, ubiqua);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarListaConjunta(ArrayList<RelatePersonContent> listaHibrida) {
		return manager.gerarListaConjunta(listaHibrida);
	}

	@Override
	public ArrayList<RelatePersonContent> gerarRecomendacao(Person person, ArrayList<RelatePersonContent> ubiqua) {
		return manager.gerarRecomendacao(person, ubiqua);
	}

}
