package mobilehealth.prc.controller;

import java.util.Calendar;
import java.util.List;

import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.Scores;
import mobilehealth.core.domain.Times;
import mobilehealth.core.domain.Frequency;
import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;

public class FillOutServer 
{
	IData iData = DataEclipseLink.getInstance();
	
	/**
	 * Recebe o Context (semi preenchido) e complementa com informacoes que so podem ser preenchidas no Server
	 */
    public Person fillContextAndScoresAndTimes(Person person, Context contexto, int contextType)
    {
		boolean flagDeviceJaExiste = false;
		boolean flagLocationJaExiste = false;
    	ManagerServerLocation managerServerLocation = ManagerServerLocation.getInstance();
    	
    	if (contexto == null) 
    	{
    		// Contexto criado para usuario passivo.
    		// usuario passivo: recebeu a acao, ou seja, que nao realizou a acao e que, inclusive, pode estar offline.
			Context contextNew = new Context();
			contexto = contextNew;
		}
    	else 
    	{
    		// Contexto criado para usuario ativo.
    		// usuario ativo: realiza a acao.
    		
    		// TIMES
    		Times times = person.getTimes();
    		times = this.fillTimes(times);
    		    		
    		
    		// DEVICE
			Device deviceNow = contexto.getDevice(); // device vindo do Android dentro do contexto
			// Verifica se ja existe
			List<Device> listDevice = person.getListDevices();
			for (Device deviceTemp : listDevice) 
			{
				if(deviceTemp.getImei()!=null && deviceNow.getImei()!=null && (deviceTemp.getImei().compareToIgnoreCase(deviceNow.getImei()) == 0)) {
					flagDeviceJaExiste = true;
					deviceNow = deviceTemp;	// Se ja existe, atualiza pelo device que ja existe, para evitar duplicar device no BD
				}
			}
			if(flagDeviceJaExiste == false) {
				// Se nao existe, adiciona
				deviceNow = new Device();
				deviceNow.setPerson(person);
				listDevice.add(deviceNow);
				person.setListDevices(listDevice);
			}   		
    		
			// LOCATION
			Location locationNow = contexto.getLocation();	// location vindo do Android dentro do contexto
/*			// Verifica se ja existe
			List<Context> listContext = person.getListContexts();
			for (Context contextTemp : listContext) 
			{
				Location locationTemp = contextTemp.getLocation();
				
				if (locationTemp != null) 
				{
					long latTemp = (long) (locationTemp.getLatitude()*1000);
					long latNow =  (long)  (locationNow.getLatitude()*1000);
					long lonTemp = (long) (locationTemp.getLongitude()*1000);
					long lonNow =  (long)  (locationNow.getLongitude()*1000);
					
					if( latTemp == latNow && lonTemp == lonNow ) {
						flagLocationJaExiste = true;
						locationNow = locationTemp;	// Se ja existe, atualiza pelo location que ja existe, para evitar duplicar no BD
					}
				}
			}
			
			if(flagLocationJaExiste == false) {
				// Se nao existe, adiciona
				locationNow = managerServerLocation.reverseGeocoding( locationNow ); // complementa objeto com dados do GoogleMaps
			} 
			
			// TODO PROBLEMA GRAVE: mesmo que a coordenada seja a mesma, sempre que um contexto eh criado, um novo Location tb eh criado no BD.
			// Solucao parcial: amenizei o problema, verificando se ja existe um local proximo, para evitar replicar.
			
			// TODO ATENCAO: location.setPerson() so eh usado quando o Location eh criado manualmente! Nunca usar isso para Locations que preenchem context.
*/			
			
			// Atualiza Times
			person.setTimes( times );
			
			// Atualiza Device
    		contexto.setDevice(deviceNow);
    		
    		// Atualiza Location
    		contexto.setLocation( locationNow );
    	}
    	
    	// CONTEXT (atributos obrigatorios)
		contexto.setType(contextType);
		contexto.setPerson(person);
		contexto.setTimeStamp(Calendar.getInstance());
		
		// Adiciona Context a Person
		List<Context> listContexts = person.getListContexts();
		listContexts.add(contexto);
		person.setListContexts(listContexts);
		
		// SCORES
		Scores scores = person.getScores();
		
		switch (contextType)  
		{
			case Context.TYPE_CHALLENGE_ADDED:		scores.setChallengeAdded(		scores.getChallengeAdded()+1); break;
			case Context.TYPE_CHALLENGE_COMMENTED: 	scores.setChallengeCommented(	scores.getChallengeCommented()+1); break;
			case Context.TYPE_CHALLENGE_CREATED:	scores.setChallengeCreated(		scores.getChallengeCreated()+1); break;
			case Context.TYPE_CHALLENGE_DELETED:	scores.setChallengeDeleted(		scores.getChallengeDeleted()+1); break;
			case Context.TYPE_CHALLENGE_EDITED:		scores.setChallengeEdited(		scores.getChallengeEdited()+1); break;
			case Context.TYPE_CHALLENGE_RATED:		scores.setChallengeRated(		scores.getChallengeRated()+1); break;
			case Context.TYPE_CHALLENGE_REJECTED:	scores.setChallengeRejected(	scores.getChallengeRejected()+1); break;
			case Context.TYPE_CHALLENGE_VIEWED:		scores.setChallengeViewed(		scores.getChallengeViewed()+1); break;
			case Context.TYPE_CHALLENGE_WARNED:		scores.setChallengeWarned(		scores.getChallengeWarned()+1); break;
			case Context.TYPE_CHALLENGE_SEARCH:		scores.setChallengeSearch(		scores.getChallengeSearch()+1); break;
	
			case Context.TYPE_EVENT_ADDED:		scores.setEventAdded(		scores.getEventAdded()+1); break;
			case Context.TYPE_EVENT_COMMENTED: 	scores.setEventCommented(	scores.getEventCommented()+1); break;
			case Context.TYPE_EVENT_CREATED:	scores.setEventCreated(		scores.getEventCreated()+1); break;
			case Context.TYPE_EVENT_DELETED:	scores.setEventDeleted(		scores.getEventDeleted()+1); break;
			case Context.TYPE_EVENT_EDITED:		scores.setEventEdited(		scores.getEventEdited()+1); break;
			case Context.TYPE_EVENT_RATED:		scores.setEventRated(		scores.getEventRated()+1); break;
			case Context.TYPE_EVENT_REJECTED:	scores.setEventRejected(	scores.getEventRejected()+1); break;
			case Context.TYPE_EVENT_VIEWED:		scores.setEventViewed(		scores.getEventViewed()+1); break;
			case Context.TYPE_EVENT_WARNED:		scores.setEventWarned(		scores.getEventWarned()+1); break;
			case Context.TYPE_EVENT_SEARCH:		scores.setEventSearch(		scores.getEventSearch()+1); break;
			
			case Context.TYPE_POST_ADDED:		scores.setPostAdded(		scores.getPostAdded()+1); break;
			case Context.TYPE_POST_COMMENTED: 	scores.setPostCommented(	scores.getPostCommented()+1); break;
			case Context.TYPE_POST_CREATED:		scores.setPostCreated(		scores.getPostCreated()+1); break;
			case Context.TYPE_POST_DELETED:		scores.setPostDeleted(		scores.getPostDeleted()+1); break;
			case Context.TYPE_POST_EDITED:		scores.setPostEdited(		scores.getPostEdited()+1); break;
			case Context.TYPE_POST_RATED:		scores.setPostRated(		scores.getPostRated()+1); break;
			case Context.TYPE_POST_REJECTED:	scores.setPostRejected(		scores.getPostRejected()+1); break;
			case Context.TYPE_POST_VIEWED:		scores.setPostViewed(		scores.getPostViewed()+1); break;
			case Context.TYPE_POST_WARNED:		scores.setPostWarned(		scores.getPostWarned()+1); break;
			case Context.TYPE_POST_SEARCH:		scores.setPostSearch(		scores.getPostSearch()+1); break;
			
			case Context.TYPE_GROUP_ADDED:		scores.setGroupAdded(		scores.getGroupAdded()+1); break;
			case Context.TYPE_GROUP_COMMENTED: 	scores.setGroupCommented(	scores.getGroupCommented()+1); break;
			case Context.TYPE_GROUP_CREATED:	scores.setGroupCreated(		scores.getGroupCreated()+1); break;
			case Context.TYPE_GROUP_DELETED:	scores.setGroupDeleted(		scores.getGroupDeleted()+1); break;
			case Context.TYPE_GROUP_EDITED:		scores.setGroupEdited(		scores.getGroupEdited()+1); break;
			case Context.TYPE_GROUP_RATED:		scores.setGroupRated(		scores.getGroupRated()+1); break;
			case Context.TYPE_GROUP_REJECTED:	scores.setGroupRejected(	scores.getGroupRejected()+1); break;
			case Context.TYPE_GROUP_VIEWED:		scores.setGroupViewed(		scores.getGroupViewed()+1); break;
			case Context.TYPE_GROUP_WARNED:		scores.setGroupWarned(		scores.getGroupWarned()+1); break;
			case Context.TYPE_GROUP_SEARCH:		scores.setGroupSearch(		scores.getGroupSearch()+1); break;
			
			case Context.TYPE_LOCATION_ADDED:		scores.setLocationAdded(		scores.getLocationAdded()+1); break;
			case Context.TYPE_LOCATION_COMMENTED: 	scores.setLocationCommented(	scores.getLocationCommented()+1); break;
			case Context.TYPE_LOCATION_CREATED:		scores.setLocationCreated(		scores.getLocationCreated()+1); break;
			case Context.TYPE_LOCATION_DELETED:		scores.setLocationDeleted(		scores.getLocationDeleted()+1); break;
			case Context.TYPE_LOCATION_EDITED:		scores.setLocationEdited(		scores.getLocationEdited()+1); break;
			case Context.TYPE_LOCATION_RATED:		scores.setLocationRated(		scores.getLocationRated()+1); break;
			case Context.TYPE_LOCATION_REJECTED:	scores.setLocationRejected(		scores.getLocationRejected()+1); break;
			case Context.TYPE_LOCATION_VIEWED:		scores.setLocationViewed(		scores.getLocationViewed()+1); break;
			case Context.TYPE_LOCATION_WARNED:		scores.setLocationWarned(		scores.getLocationWarned()+1); break;
			case Context.TYPE_LOCATION_SEARCH:		scores.setLocationSearch(		scores.getLocationSearch()+1); break;
			
			case Context.TYPE_PERSON_CREATED_FIRST:	scores.setPersonCreatedFirst(	scores.getPersonCreatedFirst()+1); break;
			case Context.TYPE_PERSON_CREATED_AGAIN:	scores.setPersonCreatedAgain(	scores.getPersonCreatedAgain()+1); break;
			case Context.TYPE_PERSON_COMMENT: 		scores.setPersonComment(		scores.getPersonComment()+1); break;
			case Context.TYPE_PERSON_COMMENTED: 	scores.setPersonCommented(		scores.getPersonCommented()+1); break;
			case Context.TYPE_PERSON_DELETED:		scores.setPersonDeleted(		scores.getPersonDeleted()+1); break;
			case Context.TYPE_PERSON_EDITED:		scores.setPersonEdited(			scores.getPersonEdited()+1); break;
			case Context.TYPE_PERSON_VIEW:			scores.setPersonView(			scores.getPersonView()+1); break;
			case Context.TYPE_PERSON_VIEWED:		scores.setPersonViewed(			scores.getPersonViewed()+1); break;
			case Context.TYPE_PERSON_WARN:			scores.setPersonWarn(			scores.getPersonWarn()+1); break;
			case Context.TYPE_PERSON_WARNED:		scores.setPersonWarned(			scores.getPersonWarned()+1); break;
			case Context.TYPE_PERSON_SEARCH:		scores.setPersonSearch(			scores.getPersonSearch()+1); break;
	
			case Context.TYPE_PERSON_FRIEND_REQUEST:		scores.setPersonFriendRequest(		scores.getPersonFriendRequest()+1); break;
			case Context.TYPE_PERSON_FRIEND_REQUESTED:		scores.setPersonFriendRequested(	scores.getPersonFriendRequested()+1); break;
			case Context.TYPE_PERSON_FRIEND_START:			scores.setPersonFriendAccept(		scores.getPersonFriendAccept()+1); break;
			case Context.TYPE_PERSON_FRIEND_REJECT:			scores.setPersonFriendReject(		scores.getPersonFriendReject()+1); break;
			case Context.TYPE_PERSON_FRIEND_REJECTED:		scores.setPersonFriendRejected(		scores.getPersonFriendRejected()+1); break;
			case Context.TYPE_PERSON_FRIEND_DELETE:			scores.setPersonFriendDelete(		scores.getPersonFriendDelete()+1); break;
			case Context.TYPE_PERSON_FRIEND_DELETED:		scores.setPersonFriendDeleted(		scores.getPersonFriendDeleted()+1); break;
			case Context.TYPE_PERSON_FRIEND_REQUEST_CANCEL: 	scores.setPersonFriendRequestCancel(	scores.getPersonFriendRequestCancel()+1); break;
			case Context.TYPE_PERSON_FRIEND_REQUEST_CANCELED:	scores.setPersonFriendRequestCanceled(	scores.getPersonFriendRequestCanceled()+1); break;
	
			case Context.TYPE_REGULAR:	scores.setRegular(	scores.getRegular()+1); break;
			case Context.TYPE_LOGIN:	scores.setLogin(	scores.getLogin()+1); break;
			case Context.TYPE_LOGOUT:	scores.setLogout(	scores.getLogout()+1); break;
			
			case Context.TYPE_EXTERNALACCOUNT_CREATED:		scores.setExternalaccountCreated(	scores.getExternalaccountCreated()+1); break;
			case Context.TYPE_EXTERNALACCOUNT_DELETED:		scores.setExternalaccountDeleted(	scores.getExternalaccountDeleted()+1); break;
			
			case Context.TYPE_CHAT_RECEIVE:	scores.setChatReceive(	scores.getChatReceive()+1); break;
			case Context.TYPE_CHAT_SEND:	scores.setChatSend(		scores.getChatSend()+1); break;
			case Context.TYPE_CHAT_PERSON:	scores.setChatPersons(	scores.getChatPersons()+1); break;
			case Context.TYPE_SHARING:		scores.setSharing(		scores.getSharing()+1); break;
	
			case Context.TYPE_CHALLENGE_ANSWER_RIGHT:	scores.setChallengeAnswerRight(	scores.getChallengeAnswerRight()+1); break;
			case Context.TYPE_CHALLENGE_ANSWER_WRONG:	scores.setChallengeAnswerWrong(	scores.getChallengeAnswerWrong()+1); break;
	
			default:
				//System.out.println("CONTEXTO DESCONHECIDO"); break;
		}
		
		// Atualiza Scores
		person.setScores(scores);
		
		return person;
    }
    
    
	/**
	 * Recebe o Times e o incrementa com base no dia e hora atual
	 */
    public Times fillTimes(Times times)
    {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // 0..23
		int day =  calendar.get(Calendar.DAY_OF_WEEK); // 1..7
		times.plusH24Actions(hour);
		times.plusH7Actions(day-1);
		
    	return times;
    }
    
    
	/**
	 * Recebe o Frequency e complementa com informacoes que so podem ser preenchidas no Server
	 */
    public Frequency fillFrequencyPositive(Frequency frequency)
    {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // 0..23
		int day =  calendar.get(Calendar.DAY_OF_WEEK); // 1..7
		frequency.plusH24Positive(hour);
		frequency.plusH7Positive(day-1);
		
		return frequency;
    }
    
    public Frequency fillFrequencyNegative(Frequency frequency)
    {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // 0..23
		int day =  calendar.get(Calendar.DAY_OF_WEEK); // 1..7
		frequency.plusH24Negative(hour);
		frequency.plusH7Negative(day-1);
		
		return frequency;
    }
	
}
