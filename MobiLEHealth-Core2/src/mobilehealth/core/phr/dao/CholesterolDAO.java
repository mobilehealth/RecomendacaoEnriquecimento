package mobilehealth.core.phr.dao;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.phr.measurement.Cholesterol;

public class CholesterolDAO extends GenericDAO<Cholesterol>{

	public CholesterolDAO() {
		super(Cholesterol.class);
	}
	
	
}
