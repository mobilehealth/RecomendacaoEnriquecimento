package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelateContentTag;

public class RelateContentTagDAO extends GenericDAO<RelateContentTag>
{
	public RelateContentTagDAO() {
		super(RelateContentTag.class);
	}
	
	public RelateContentTag findByAccess(int idContent, int idTag, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idContent", idContent);
		parameters.put("idTag", idTag);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}
}
