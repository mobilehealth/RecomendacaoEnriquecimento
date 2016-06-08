package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelatePersonTag;

public class RelatePersonTagDAO extends GenericDAO<RelatePersonTag>
{
	public RelatePersonTagDAO() {
		super(RelatePersonTag.class);
	}
	
	
	public  RelatePersonTag findByAccess(int idPerson, int idTag, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idPerson1", idPerson);
		parameters.put("idTag", idTag);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}

}
