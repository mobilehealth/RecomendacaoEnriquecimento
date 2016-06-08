package mobilehealth.prc.controller;

import java.util.List;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;

import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.ExternalAccounts;
import mobilehealth.core.domain.Person;

public class ManagerServerExternalaccounts 
{
	private static ManagerServerExternalaccounts instance;
	private IData iData;
	private FillOutServer fillOutServer;

	
	private ManagerServerExternalaccounts()
	{
		this.iData = DataEclipseLink.getInstance();
		this.fillOutServer = new FillOutServer();
	}
	
    public static ManagerServerExternalaccounts getInstance()
    {
        if (instance == null) {
            instance = new ManagerServerExternalaccounts();
        }
        return instance;
    }

	public boolean addExternalAccount(long idperson, ExternalAccounts externalAccounts, Context contexto) 
	{
		boolean flagOK = false;
		
		// PERSON e EXTERNALACCOUNTS
		Person person = this.iData.getPerson(idperson); // usuario logado
		externalAccounts.setPerson(person);
		person.getListExternalAccounts().add(externalAccounts);

    	// CONTEXTO
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_EXTERNALACCOUNT_CREATED);
		
 		// Atualiza Person
 		flagOK = iData.setPerson(person);
 		
		return flagOK;
	}
		
	@SuppressWarnings("finally")
	public boolean removeExternalAccount(long idPerson, String accountName, Context contexto) 
	{
		boolean result = false;
		
		try{
			Person person = this.iData.getPerson(idPerson);
						
			if(person.getListExternalAccounts()!=null && person.getListExternalAccounts().size()>0)
			{
				for(int i=0; i<person.getListExternalAccounts().size(); i++)
				{
					ExternalAccounts account = person.getListExternalAccounts().get(i);
					if(account.getServiceName().equals(accountName) && account.getPerson().getId() == idPerson)
					{
						person.getListExternalAccounts().remove(account);
						result = true;
						i++;
					}
				}
				
				if(result) 
				{
			    	// CONTEXTO
			 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_EXTERNALACCOUNT_DELETED);
			    	
			    	// Atualiza Person
					iData.setPerson(person);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// TODO Duvida: Luiz, o return tem que ficar dentro do finally mesmo?
			return result;
		}
	}
	
	public List<ExternalAccounts> getAllExternalAccounts() {
		return this.iData.getAllExternalAccounts();
	}
}
