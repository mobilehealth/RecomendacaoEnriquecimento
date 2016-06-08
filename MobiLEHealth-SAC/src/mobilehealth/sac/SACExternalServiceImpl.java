package mobilehealth.sac;

import java.util.HashMap;
import java.util.Map;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import mobilehealth.core.controller.DomainController;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Domain;
import mobilehealth.core.domain.Person;
import mobilehealth.sac.augmentation.MetricsEngine;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.semanticrepository.UserProfileOntology;

/**
 * implementation of SACExternalService interface
 * 
 * @author Jonathan Darlan
 * @date 01/08/2014
 * 
 */
public class SACExternalServiceImpl implements SACExternalService {
	
	@Override
	public Map<String, Double> getDegreeOfInterestsDomains(Person person) {
		Map<String, Double> degreeOfInterestDomains = new HashMap<String, Double>();
		DomainController domainController = new DomainController();
		
		for(Domain domain : domainController.getAllDomains()) {
			double index = getDegreeofInterest(person, domain.getDomainName());
			degreeOfInterestDomains.put(domain.getDomainName(), index);
		}
		
		return degreeOfInterestDomains;
	}

	@Override
	public Double getDegreeofInterest(Person person, String domain) {
		double degreeOfInterest = 0;
		MetricsEngine metricsEngine = new MetricsEngine();
		UserProfileOntology userProfileOntology;
		
		try {
			userProfileOntology = new UserProfileOntology();
			User user = userProfileOntology.findUser(person.getId());
			degreeOfInterest = metricsEngine.calculeUIIndex(user, domain);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		
		return degreeOfInterest;
	}

	@Override
	public Map<String, Double> getRelationshipLevelDomains(Content content) {
		
		Map<String, Double> relationshipsLevelDomain = new HashMap<String, Double>();
		DomainController domainController = new DomainController();
		
		for(Domain domain : domainController.getAllDomains()) {
			double index = getRelationshipLevelDomain(content, domain.getDomainName());
			relationshipsLevelDomain.put(domain.getDomainName(), index);
		}
		
		return relationshipsLevelDomain;
	}

	@Override
	public Double getRelationshipLevelDomain(Content content, String domain) {
		
		double relationshipLevelDomain = 0;
		MetricsEngine metricsEngine = new MetricsEngine();
		UserProfileOntology userProfileOntology;
		
		try {
			userProfileOntology = new UserProfileOntology();
			Resource resource = userProfileOntology.findResource("public", "content", "id", content.getId());
			if(resource != null) {
				relationshipLevelDomain = metricsEngine.cacluleCDRIndex(resource, domain);
			}
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

		return relationshipLevelDomain;
	}
	
}
