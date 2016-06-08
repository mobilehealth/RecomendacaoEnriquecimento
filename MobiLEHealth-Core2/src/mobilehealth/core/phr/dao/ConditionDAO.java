package mobilehealth.core.phr.dao;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.phr.healthhistory.Condition;

public class ConditionDAO extends GenericDAO<Condition>{

	public ConditionDAO() {
		super(Condition.class);
	}
	
	
}
