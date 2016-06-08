package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelateLocationTag;
import mobilehealth.core.domain.RelatePersonLocation;

public class RelatePersonLocationDAO extends GenericDAO<RelatePersonLocation>
{
	public RelatePersonLocationDAO() {
		super(RelatePersonLocation.class);
	}

	
	public RelatePersonLocation findByAccess(int idPerson, int Location, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idPerson", idPerson);
		parameters.put("idLocation", Location);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}
	
}
