package mobilehealth.core.controller;

import java.util.List;

import mobilehealth.core.dao.DomainDAO;
import mobilehealth.core.domain.Domain;

public class DomainController {
	
	private DomainDAO domainDAO;
	
	public DomainController() {
		domainDAO = new DomainDAO();
	}
	
	public Domain getDomain(String name) {
		return domainDAO.findByName(name);
	}
	
	public List<Domain> getAllDomains() {
		return domainDAO.findAll();
	}

	public void save(Domain domain) {
		domainDAO.save(domain);
	}
}
