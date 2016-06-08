package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;
import mobilehealth.core.dao.ContextDAO;

import mobilehealth.core.domain.Context;

public class ContextController
{	
	private ContextDAO contextDAO;
	
	public ContextController() {
		contextDAO = new ContextDAO();
	}
	
	public List<Context> getAllContexts() {
		List<Context> allContexts = new ArrayList<Context>(); 
		allContexts = contextDAO.findAll();
		return allContexts;
	}


}
