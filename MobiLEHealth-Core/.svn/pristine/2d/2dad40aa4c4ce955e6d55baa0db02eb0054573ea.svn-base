package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelateContentTag;
import mobilehealth.core.domain.RelateLocationTag;

public class RelateLocationTagDAO extends GenericDAO<RelateLocationTag>
{
	public RelateLocationTagDAO() {
		super(RelateLocationTag.class);
	}
	
	public RelateLocationTag findByAccess(int idLocation, int idTag, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idLocation", idLocation);
		parameters.put("idTag", idTag);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}
}
