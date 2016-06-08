package mobilehealth.core.dao;

import java.util.HashMap;
import java.util.Map;

import mobilehealth.core.domain.Domain;

public class DomainDAO extends GenericDAO<Domain> {
	
	public DomainDAO() {
		super(Domain.class);
	}
	
	public Domain findByName(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("domainName", name);
		return super.findOneResult("findByName", parameters);
	}
}
