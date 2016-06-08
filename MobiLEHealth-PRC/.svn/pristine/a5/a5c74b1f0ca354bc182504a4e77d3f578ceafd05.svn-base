package mobilehealth.prc.controller;

import java.util.Calendar;
import java.util.List;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.prc.collector.services.FillLocation;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Frequency;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonLocationPK;


public class ManagerServerLocation 
{
	private static ManagerServerLocation instance;
	private IData iData;
	private ManagerServerTag managerServerTag;
	private FillOutServer fillOutServer;
	
	
	private ManagerServerLocation()
	{
		this.iData = DataEclipseLink.getInstance();
		this.managerServerTag = ManagerServerTag.getInstance();
		this.fillOutServer = new FillOutServer();
	}
	
    public static ManagerServerLocation getInstance()
    {
        if (instance == null) {
            instance = new ManagerServerLocation();
        }
        return instance;
    }
	
    
	public Location getLocation(long id) 
	{
		return iData.getLocation(id);
	}
	
	
	public List<Location> getAllLocation()
	{
		return iData.getAllLocation();
	}
	
	public int getCountLocation()
	{
		return iData.getAllLocation().size();
	}
	
	public List<Location> searchLocation(long idPerson, Object param, Context contexto)
	{
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
				
		// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_SEARCH);
		
		return iData.searchLocation(param);
	}
	

	public boolean createLocation(long idPerson, Location location, Context contexto) 
	{
		boolean flagUpdate = false;
		
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
		
		// LOCATION (continua preenchendo)
		location.setDateCreation(Calendar.getInstance());
		location.setFrequency(new Frequency());
		
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_LOCATION_FAVORITE);
		
		// Se ja tiver sido criado, nao cria nova relacao nem produz qq efeito
		if(iData.getRelatePersonLocation(rPK) == null) 
		{
	    	// TAG (cria tags com base na descricao)
			managerServerTag.createAndUpdateTags(location.getDescription(), person, null, location);
			
			// RELACAO-PERSON-LOCATION
			List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
			// Adiciona STATUS_FAVORITE_OR_OWNER
			RelatePersonLocation relatePersonLocationAdd = new RelatePersonLocation();
			relatePersonLocationAdd.setPerson(person);
			relatePersonLocationAdd.setLocation(location);
			relatePersonLocationAdd.setDateRelation(Calendar.getInstance());
			relatePersonLocationAdd.setStatus(RelatePersonLocation.STATUS_LOCATION_FAVORITE);
			relatePersonLocationAdd.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?	
			
			// TIMES: P-L
			relatePersonLocationAdd.setTimes( fillOutServer.fillTimes( relatePersonLocationAdd.getTimes() ) );
			
			// Adiciona RelatePersonLocation e atualiza Person 
			listRelatePersonLocation.add(relatePersonLocationAdd);
			person.setListRelatePersonLocation( listRelatePersonLocation );

			// CONTEXTO E SCORE
	 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_CREATED);
			
			// Atualiza Person
			flagUpdate = iData.setPerson(person);
		}
		
		return flagUpdate;
	}
	
	public boolean createLocation(Location location) 
	{
		boolean flagOk = false;
		
		// LOCATION (continua preenchendo)
		location.setFrequency(new Frequency());
 		flagOk = iData.insertLocation(location);
		
		return flagOk;
	}

	
	public boolean editLocation(long idPerson, Location location, Context contexto) 
	{
		boolean flagPerson = false;
		boolean flagLocation = false;
		boolean flagOK = false;
        
		// PERSON
		Person person = iData.getPerson(idPerson);
    	
    	// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_EDITED);
 		
 		// setLocation
 		flagLocation = iData.setLocation(location);
 		
        // setPerson
 		flagPerson = iData.setPerson(person);

 		if(flagPerson == true && flagLocation == true) {
 			flagOK = true;
 		}
 		
		return flagOK;
	}
	
	public boolean editLocation(Location location) 
	{
		boolean flagOK = false;
        
 		// setLocation
		flagOK = iData.setLocation(location);
 
		return flagOK;
	}

	
	public boolean removeLocation(long idPerson, long idLocation, Context contexto) 
	{
		boolean flagEmpty = false;
		boolean flagPerson = false;
		boolean flagLocation = false;
		boolean flagOK = false;
        
		// PERSON
		Person person = iData.getPerson(idPerson);
		
		// LOCATION
		Location location = iData.getLocation(idLocation);
	

		// SE: SEGUIDOR (muda apenas o status da relacao)
		
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_INDEFINITE);
		
		// Se ja tiver sido criado, nao cria nova relacao nem produz qq efeito
		if(iData.getRelatePersonLocation(rPK) == null) {
			flagEmpty = true;
		}
		
		// RELACAO-PERSON-LOCATION
		List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
		// Deleta STATUS_FAVORITE_OR_OWNER
		for (RelatePersonLocation relatePersonLocation : listRelatePersonLocation) {
			if( relatePersonLocation.getStatus() == RelatePersonLocation.STATUS_LOCATION_FAVORITE && 
				relatePersonLocation.getPerson().getId() == person.getId() &&
				relatePersonLocation.getLocation().getId() == location.getId()	) 
			{
				listRelatePersonLocation.remove(relatePersonLocation);
				System.out.println("DEVE SER REMOVIDA 1: " + relatePersonLocation.getStatus() + " " + relatePersonLocation.getPerson().getId() + " " + relatePersonLocation.getLocation().getId());
				RelatePersonLocationPK rPKdelete = new RelatePersonLocationPK();
				rPKdelete.setPerson(relatePersonLocation.getPerson().getId());
				rPKdelete.setLocation(relatePersonLocation.getLocation().getId());
				rPKdelete.setStatus(relatePersonLocation.getStatus());
				iData.removeRelatePersonLocation(rPKdelete);
				break;
			}
		}
		person.setListRelatePersonLocation( listRelatePersonLocation );
		// Adiciona STATUS_REJECTED_OR_DELETED
		if(flagEmpty == true) {
			RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
			relatePersonLocation.setPerson(person);
			relatePersonLocation.setLocation(location);
			relatePersonLocation.setDateRelation(Calendar.getInstance());
			relatePersonLocation.setStatus(RelatePersonLocation.STATUS_CONTENT_REJECTED);
			relatePersonLocation.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?	
			listRelatePersonLocation.add(relatePersonLocation);
			person.setListRelatePersonLocation( listRelatePersonLocation );
		}


    	// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_DELETED);
 		
        // setPerson
 		flagPerson = iData.setPerson(person);

 		if(flagPerson == true && flagLocation == true) {
 			flagOK = true;
 		}
 		
		return flagOK;
	}
	

	public boolean viewLocation(long idPerson, long idLocation, Context contexto) 
	{
		boolean flagUpdate = false;
		
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
    	
		// LOCATION
    	Location location = iData.getLocation(idLocation); // local adicionado
		
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_LOCATION_PRESENCE);
    	
		// Se ja tiver sido visto, nao cria nova relacao nem produz qq efeito
		if(iData.getRelatePersonLocation(rPK) == null)
		{
			// RELACAO-PERSON-LOCATION
			List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
			
			// Adiciona STATUS_VISUALIZED
			RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
			relatePersonLocation.setPerson(person);
			relatePersonLocation.setLocation(location);
			relatePersonLocation.setDateRelation(Calendar.getInstance());
			relatePersonLocation.setStatus(RelatePersonLocation.STATUS_LOCATION_PRESENCE);
			relatePersonLocation.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?	
			
			// TIMES: P-L
			relatePersonLocation.setTimes( fillOutServer.fillTimes( relatePersonLocation.getTimes() ) );
			
			// Adiciona RelatePersonLocation e atualiza Person 
			listRelatePersonLocation.add(relatePersonLocation);
			person.setListRelatePersonLocation( listRelatePersonLocation );

			// CONTEXTO E SCORE
	 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_VIEWED);
			
			// Atualiza Person
			flagUpdate = iData.setPerson(person);
		}
		
		return flagUpdate;
	}


	public boolean rejectRecommLocation(long idPerson, long idLocation, Context contexto) 
	{
		boolean flagEmpty = false;
		boolean flagUpdate = false;
		
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
    	
		// LOCATION
    	Location location = iData.getLocation(idLocation); // local recomendado (que esta sendo rejeitado)
    	
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_INDEFINITE);
    	
		if(iData.getRelatePersonLocation(rPK) == null) {
			flagEmpty = true;
		}
		
		// RELACAO-PERSON-LOCATION
		List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
		
		// Deleta STATUS_SUGGESTED
		for (RelatePersonLocation relatePersonLocation : listRelatePersonLocation) 
		{
			if( relatePersonLocation.getStatus() == RelatePersonLocation.STATUS_LOCATION_SUGGESTED && 
				relatePersonLocation.getPerson().getId() == person.getId() &&
				relatePersonLocation.getLocation().getId() == location.getId()	) 
			{
				listRelatePersonLocation.remove(relatePersonLocation);
				System.out.println("DEVE SER REMOVIDA 1: " + relatePersonLocation.getStatus() + " " + relatePersonLocation.getPerson().getId() + " " + relatePersonLocation.getLocation().getId());
				RelatePersonLocationPK rPKdelete = new RelatePersonLocationPK();
				rPKdelete.setPerson(relatePersonLocation.getPerson().getId());
				rPKdelete.setLocation(relatePersonLocation.getLocation().getId());
				rPKdelete.setStatus(relatePersonLocation.getStatus());
				iData.removeRelatePersonLocation(rPKdelete);
				break;
			}
		}
		person.setListRelatePersonLocation( listRelatePersonLocation );
		
		// Adiciona STATUS_REJECTED_OR_DELETED
		if(flagEmpty == true) 
		{
	    	RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
			relatePersonLocation.setPerson(person);
			relatePersonLocation.setLocation(location);
			relatePersonLocation.setDateRelation(Calendar.getInstance());
			relatePersonLocation.setStatus(RelatePersonLocation.STATUS_CONTENT_REJECTED);
			relatePersonLocation.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?	
						
			// Adiciona RelatePersonLocation e atualiza Person
			listRelatePersonLocation.add(relatePersonLocation);
			person.setListRelatePersonLocation( listRelatePersonLocation );
			
			// FREQUENCY (rejeicao)
			person.setFrequency( fillOutServer.fillFrequencyNegative( person.getFrequency() ) );
		}

		// CONTEXTO E SCORE
    	person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_REJECTED);

		// Atualiza Person
		flagUpdate = iData.setPerson(person);

		return flagUpdate;
	}
	

	public boolean addLocation(long idPerson, long idLocation, Context contexto) 
	{
		boolean flagUpdate = false;
		
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
    	
		// LOCATION
    	Location location = iData.getLocation(idLocation); // local adicionado
    	
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_LOCATION_FAVORITE);
    	
		// Se ja tiver sido adicionado, ou for dono, nao cria nova relacao nem produz qq efeito
		if(iData.getRelatePersonLocation(rPK) == null) 
		{
			// RELACAO-PERSON-LOCATION
			List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
			
			// Deleta STATUS_SUGGESTED
			for (RelatePersonLocation relatePersonLocation : listRelatePersonLocation) 
			{
				if( relatePersonLocation.getStatus() == RelatePersonLocation.STATUS_LOCATION_SUGGESTED && 
					relatePersonLocation.getPerson().getId() == person.getId() &&
					relatePersonLocation.getLocation().getId() == location.getId()	) 
				{
					listRelatePersonLocation.remove(relatePersonLocation);
					System.out.println("DEVE SER REMOVIDA 1: " + relatePersonLocation.getStatus() + " " + relatePersonLocation.getPerson().getId() + " " + relatePersonLocation.getLocation().getId());
					RelatePersonLocationPK rPKdelete = new RelatePersonLocationPK();
					rPKdelete.setPerson(relatePersonLocation.getPerson().getId());
					rPKdelete.setLocation(relatePersonLocation.getLocation().getId());
					rPKdelete.setStatus(relatePersonLocation.getStatus());
					iData.removeRelatePersonLocation(rPKdelete);
					break;
				}
			}
			person.setListRelatePersonLocation( listRelatePersonLocation );
			
			// Deleta STATUS_REJECTED_OR_DELETED
			for (RelatePersonLocation relatePersonLocation : listRelatePersonLocation) 
			{
				if( relatePersonLocation.getStatus() == RelatePersonLocation.STATUS_CONTENT_REJECTED && 
					relatePersonLocation.getPerson().getId() == person.getId() &&
					relatePersonLocation.getLocation().getId() == location.getId()	) 
				{
					listRelatePersonLocation.remove(relatePersonLocation);
					System.out.println("DEVE SER REMOVIDA 1: " + relatePersonLocation.getStatus() + " " + relatePersonLocation.getPerson().getId() + " " + relatePersonLocation.getLocation().getId());
					RelatePersonLocationPK rPKdelete = new RelatePersonLocationPK();
					rPKdelete.setPerson(relatePersonLocation.getPerson().getId());
					rPKdelete.setLocation(relatePersonLocation.getLocation().getId());
					rPKdelete.setStatus(relatePersonLocation.getStatus());
					iData.removeRelatePersonLocation(rPKdelete);
					break;
				}
			}
			person.setListRelatePersonLocation( listRelatePersonLocation );
			
			// Adiciona STATUS_FAVORITE_OR_OWNER
			RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
			relatePersonLocation.setPerson(person);
			relatePersonLocation.setLocation(location);
			relatePersonLocation.setDateRelation(Calendar.getInstance());
			relatePersonLocation.setStatus(RelatePersonLocation.STATUS_LOCATION_FAVORITE);
			relatePersonLocation.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?
			
			// TIMES: P-L
			relatePersonLocation.setTimes( fillOutServer.fillTimes( relatePersonLocation.getTimes() ) );
			
			// Adiciona RelatePersonLocation e atualiza Person 
			listRelatePersonLocation.add(relatePersonLocation);
			person.setListRelatePersonLocation( listRelatePersonLocation );

			// FREQUENCY (aceitacao)
			person.setFrequency( fillOutServer.fillFrequencyPositive( person.getFrequency() ) );
			
			// CONTEXTO E SCORE
	    	person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_ADDED);
			
			// Atualiza Person
			flagUpdate = iData.setPerson(person);
		}
    	
		return flagUpdate;
	}


	public boolean rateLocation(long idPerson, long idLocation, int rateFromPerson, Context contexto) 
	{
		boolean flagEmpty = false;
		boolean flagUpdate = false;
		
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
    	
		// LOCATION
    	Location location = iData.getLocation(idLocation); // local avaliado
    	
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_LOCATION_VALUED);
    	
		// Se ja tiver sido avaliado, muda a nota, mas, nao cria nova relacao
		RelatePersonLocation relatePersonLocationBD = iData.getRelatePersonLocation(rPK);
		
		if( relatePersonLocationBD == null ) {
			flagEmpty = true;
		}
    	
		// RELACAO-PERSON-LOCATION
		List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
		
		if( flagEmpty == true )
		{
			// Cria relacao STATUS_RATED
			RelatePersonLocation relatePersonLocationAdd = new RelatePersonLocation();
			relatePersonLocationAdd.setRatePerson(rateFromPerson);
			relatePersonLocationAdd.setPerson(person);
			relatePersonLocationAdd.setLocation(location);
			relatePersonLocationAdd.setDateRelation(Calendar.getInstance());
			relatePersonLocationAdd.setStatus(RelatePersonLocation.STATUS_LOCATION_VALUED);
			relatePersonLocationAdd.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?
			
			// TIMES: P-L
			relatePersonLocationAdd.setTimes( fillOutServer.fillTimes( relatePersonLocationAdd.getTimes() ) );
			
			// Adiciona RelatePersonLocation e atualiza Person 
			listRelatePersonLocation.add(relatePersonLocationAdd);
			person.setListRelatePersonLocation( listRelatePersonLocation );
		}
		else
		{
			// Atualiza relacao com o novo rate
			relatePersonLocationBD.setRatePerson(rateFromPerson);
			listRelatePersonLocation.set(listRelatePersonLocation.indexOf(relatePersonLocationBD), relatePersonLocationBD);
			person.setListRelatePersonLocation( listRelatePersonLocation );
		}

    	// CONTEXTO E SCORE
    	person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_RATED);
		
		// Atualiza Person
		flagUpdate = iData.setPerson(person);
		
		return flagUpdate;
	}


	public boolean commentLocation(long idPerson, long idLocation, String comment, Context contexto) 
	{
		boolean flagEmpty = false;
		boolean flagUpdate = false;
		
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
    	
		// LOCATION
    	Location location = iData.getLocation(idLocation); // local que recebeu comentario
    	
    	// Verifica se a relacao ja existe:
		RelatePersonLocationPK rPK = new RelatePersonLocationPK();
		rPK.setPerson(person.getId());
		rPK.setLocation(location.getId());
		rPK.setStatus(RelatePersonLocation.STATUS_LOCATION_COMMENTED);
    	
		// Se ja tiver sido avaliado, muda a nota, mas, nao cria nova relacao
		RelatePersonLocation relatePersonLocationBD = iData.getRelatePersonLocation(rPK);
    	
		if( relatePersonLocationBD == null ) {
			flagEmpty = true;
		}
		
    	// TAG (cria tags com base no comentario)
    	managerServerTag.createAndUpdateTags(comment, person, null, location);
    	
		// RELACAO-PERSON-LOCATION
		List<RelatePersonLocation> listRelatePersonLocation = person.getListRelatePersonLocation();
    	
		if( flagEmpty == true )
		{
			// Cria relacao STATUS_COMMENTED
			RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
			relatePersonLocation.setPerson(person);
			relatePersonLocation.setLocation(location);
			relatePersonLocation.setDateRelation(Calendar.getInstance());
			relatePersonLocation.setStatus(RelatePersonLocation.STATUS_LOCATION_COMMENTED);
			relatePersonLocation.setFinality(RelatePersonLocation.FINALITY_OTHER); // TODO Duvida: como preencher?
			
			// Add comentario
			//List<String> listComments = relatePersonLocation.getListComments();
			//listComments.add(comment);
			//relatePersonLocation.setListComments(listComments);
			
			// TIMES: P-L
			relatePersonLocation.setTimes( fillOutServer.fillTimes( relatePersonLocation.getTimes() ) );
			
			// Adiciona RelatePersonLocation e atualiza Person 
			listRelatePersonLocation.add(relatePersonLocation);
			person.setListRelatePersonLocation( listRelatePersonLocation );
		}
		else
		{
			// Atualiza relacao com o novo comentario
			//List<String> listComments = relatePersonLocationBD.getListComments();
			//listComments.add(comment);
			//relatePersonLocationBD.setListComments(listComments);
			
			// TIMES: P-L
			relatePersonLocationBD.setTimes( fillOutServer.fillTimes( relatePersonLocationBD.getTimes() ) );
			
			// Atualiza RelatePersonLocation e atualiza Person
			listRelatePersonLocation.set(listRelatePersonLocation.indexOf(relatePersonLocationBD), relatePersonLocationBD);
			person.setListRelatePersonLocation( listRelatePersonLocation );
		}

    	// CONTEXTO E SCORE
    	person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOCATION_COMMENTED);
		
		// Atualiza Person
		flagUpdate = iData.setPerson(person);
		
		return flagUpdate;
	}
	
	public Location reverseGeocoding(Location location){//TODO solução temporária para manter a compatibilidade (FillOutServer, ManagerServerContent)
		return new FillLocation().reverseGeocoding(location);
	}
}
