package mobilehealth.core.controller;

import org.joda.time.DateTime;

import mobilehealth.core.dao.RelateLocationTagDAO;
import mobilehealth.core.domain.RelateLocationTag;


public class RelateLocationTagController
{
	
	private RelateLocationTagDAO relateLocationTagDAO;
	
	public RelateLocationTagController() {
		relateLocationTagDAO = new RelateLocationTagDAO();
	}
	
	public RelateLocationTag getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relateLocationTagDAO.findByAccess(idPerson, idContent, relationDate);
	}

}
