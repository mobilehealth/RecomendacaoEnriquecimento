package mobilehealth.core.controller;

import org.joda.time.DateTime;

import mobilehealth.core.dao.RelateContentTagDAO;
import mobilehealth.core.domain.RelateContentTag;


public class RelateContentTagController
{
	
	
	private RelateContentTagDAO relateContentTagDAO;
	
	public RelateContentTagController() {
		relateContentTagDAO = new RelateContentTagDAO();
	}
	
	public RelateContentTag getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relateContentTagDAO.findByAccess(idPerson, idContent, relationDate);
	}
	
}
