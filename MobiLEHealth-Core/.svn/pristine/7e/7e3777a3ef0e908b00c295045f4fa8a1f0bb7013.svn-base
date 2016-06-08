package mobilehealth.core.controller;

import org.joda.time.DateTime;

import mobilehealth.core.dao.RelatePersonPersonDAO;
import mobilehealth.core.dao.RelatePersonTagDAO;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.core.domain.RelatePersonTag;

public class RelatePersonTagController
{
	
	
	private RelatePersonTagDAO relatePersonTagDAO;
	
	public RelatePersonTagController() {
		relatePersonTagDAO = new RelatePersonTagDAO();
	}
	
	public RelatePersonTag getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relatePersonTagDAO.findByAccess(idPerson, idContent, relationDate);
	}
	

}
