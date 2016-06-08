package mobilehealth.core.controller;

import org.joda.time.DateTime;

import mobilehealth.core.dao.RelatePersonContentDAO;
import mobilehealth.core.domain.RelatePersonContent;

public class RelatePersonContentController {
	
	private RelatePersonContentDAO relatePersonContentDAO;
	
	public RelatePersonContentController() {
		relatePersonContentDAO = new RelatePersonContentDAO();
	}
	
	public RelatePersonContent getRelateByAccess(int idPerson, int idContent, DateTime relationDate) {
		return relatePersonContentDAO.findByAccess(idPerson, idContent, relationDate);
	}
}
