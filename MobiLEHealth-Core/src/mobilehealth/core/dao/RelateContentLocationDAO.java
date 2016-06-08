package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelatePersonContent;


public class RelateContentLocationDAO extends GenericDAO<RelateContentLocation>
{
	public RelateContentLocationDAO() {
		super(RelateContentLocation.class);
	}

	public RelateContentLocation findByAccess(int idContent, int idLocation, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idContent", idContent);
		parameters.put("idLocation", idLocation);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}
	
	
}
