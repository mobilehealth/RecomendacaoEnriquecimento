package mobilehealth.core.controller;

import org.joda.time.DateTime;

import mobilehealth.core.dao.RelateContentLocationDAO;
import mobilehealth.core.dao.RelatePersonContentDAO;
import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelatePersonContent;


public class RelateContentLocationController
{

	private RelateContentLocationDAO relateContentLocationDAO;
	
	public RelateContentLocationController() {
		relateContentLocationDAO = new RelateContentLocationDAO();
	}
	
	public RelateContentLocation getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relateContentLocationDAO.findByAccess(idPerson, idContent, relationDate);
	}
	
	
}
