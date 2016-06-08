package mobilehealth.core.controller;

import mobilehealth.core.dao.RelatePersonLocationDAO;
import mobilehealth.core.domain.RelatePersonLocation;

import org.joda.time.DateTime;


public class RelatePersonLocationController
{
	
	private RelatePersonLocationDAO relatePersonLocationDAO;
	
	public RelatePersonLocationController() {
		relatePersonLocationDAO = new RelatePersonLocationDAO();
	}
	
	public RelatePersonLocation getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relatePersonLocationDAO.findByAccess(idPerson, idContent, relationDate);
	}
	

}
