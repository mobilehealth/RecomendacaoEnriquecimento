package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonPerson;

public class RelatePersonPersonDAO extends GenericDAO<RelatePersonPerson>
{
	public RelatePersonPersonDAO() {
		super(RelatePersonPerson.class);
	}
	
	public  RelatePersonPerson findByAccess(int idPerson1, int idPerson2, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idPerson1", idPerson1);
		parameters.put("idPerson2", idPerson2);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}

}
