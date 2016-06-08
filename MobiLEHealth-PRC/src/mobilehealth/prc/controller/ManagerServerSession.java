package mobilehealth.prc.controller;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Person;

public class ManagerServerSession 
{
	private static ManagerServerSession instance;
	private IData iData;
	private FillOutServer fillOutServer;

	
	private ManagerServerSession()
	{
		this.iData = DataEclipseLink.getInstance();
		this.fillOutServer = new FillOutServer();
	}
	
    public static ManagerServerSession getInstance()
    {
        if (instance == null) {
            instance = new ManagerServerSession();
        }
        return instance;
    }
	
    
	public boolean login(String email, String passEncripted, Context contexto)
	{
		// login
		boolean flagLogin = iData.login(email, passEncripted);
		boolean flagUpdate = false;
		boolean flagOK = false;
		
		System.out.println("flagLogin = " + flagLogin);
		
		if(flagLogin == true)
		{
			// PERSON
			Person person = iData.getPerson(email);
			System.out.println("person = " + person);
			
			if(person.getStatus() == Person.STATUS_DELETED)
			{
				System.out.println("USUARIO COM STATUS DELETED");
			}
			else
			{
				// ONLINE
				person.setOnline(Person.ONLINE);
									
				// CONTEXTO E SCORE (continua preenchendo outros atributos)
				//TODO problema desconhecido na linha abaixo.
		 		//person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOGIN);
				
				// Atualiza Person
		 		flagUpdate = iData.setPerson(person);
				System.out.println("flagUpdate = " + flagUpdate);
			} 
		}
		
		if(flagLogin == true && flagUpdate == true) {
			flagOK = true;
		}
		return flagOK;
	}
	
	
	public boolean logout(long idPerson, Context contexto)
	{
		boolean flagOK = false;
		
    	// PERSON
    	Person person = iData.getPerson(idPerson);
    	
    	if(person != null)
    	{
			// OFFLINE
			person.setOnline(Person.OFFLINE);

			// CONTEXTO E SCORE (continua preenchendo outros atributos)
	 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_LOGOUT);

    		// Atualiza Person
    		flagOK = iData.setPerson(person);
    	}
    	
		return flagOK;
	}
	
}
