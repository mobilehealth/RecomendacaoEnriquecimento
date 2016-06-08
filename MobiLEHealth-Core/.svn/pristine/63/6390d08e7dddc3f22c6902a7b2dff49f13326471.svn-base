package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.ExternalAccountsDAO;
import mobilehealth.core.domain.ExternalAccounts;
import mobilehealth.core.domain.Person;

public class ExternalAccountsController
{
	
	
	private ExternalAccountsDAO externalAccountsDAO;
	
	public ExternalAccountsController() {
		externalAccountsDAO = new ExternalAccountsDAO();
	}
	
	public List<ExternalAccounts> getAllExternalAccounts() {
		List<ExternalAccounts> allExternalAccounts = new ArrayList<ExternalAccounts>();
		allExternalAccounts = externalAccountsDAO.findAll();
		return allExternalAccounts;
	}
	
}
