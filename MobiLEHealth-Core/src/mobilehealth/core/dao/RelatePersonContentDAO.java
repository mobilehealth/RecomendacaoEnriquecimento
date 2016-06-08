package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import mobilehealth.core.domain.RelatePersonContent;

public class RelatePersonContentDAO extends GenericDAO<RelatePersonContent> {
	
	public RelatePersonContentDAO() {
		super(RelatePersonContent.class);
	}
	
	public RelatePersonContent findByAccess(int idPerson, int idContent, DateTime relationDate) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idPerson", idPerson);
		parameters.put("idContent", idContent);
		parameters.put("relationDate", relationDate);
		return super.findOneResult("findByAccess", parameters);
	}
	
	public RelatePersonContent findByPrimaryKey(int idPerson, int idContent) {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idPerson", idPerson);
		parameters.put("idContent", idContent);
		//return super.findOneResult("findByAccess", parameters);
		return null;
		
		
	}
	
	public List<RelatePersonContent> findByPerson(int idPerson) {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("idPerson", idPerson);
			return (List<RelatePersonContent>) super.findResults("findByPerson", parameters);
		}
	
	
	
}
