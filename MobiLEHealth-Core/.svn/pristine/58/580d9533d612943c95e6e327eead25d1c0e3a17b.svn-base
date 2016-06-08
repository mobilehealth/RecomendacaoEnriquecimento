package mobilehealth.core.controller;

import org.joda.time.DateTime;

import mobilehealth.core.dao.RelatePersonPersonDAO;
import mobilehealth.core.domain.RelatePersonPerson;

public class RelatePersonPersonController
{
	
	private RelatePersonPersonDAO relatePersonPersonDAO;
	
	public RelatePersonPersonController() {
		relatePersonPersonDAO = new RelatePersonPersonDAO();
	}
	
	public RelatePersonPerson getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relatePersonPersonDAO.findByAccess(idPerson, idContent, relationDate);
	}
	

}
