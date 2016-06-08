package mobilehealth.sac.augmentation;

import mobilehealth.core.controller.DomainController;
import mobilehealth.core.domain.Domain;
import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Link;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.semanticrepository.SemanticRepository;

public class MetricsEngine {

	/*
	 * CDR - Content Domain Relation
	 */
	public Double cacluleCDRIndex(Resource resource, String domain) {
		double CDRIndex = 0;
		CDRIndex = (calculeRDCPIndex(resource, domain) + calculeRLPDIndex(resource, domain)) / 2;
		return CDRIndex;
	}

	/*
	 * RLPD - Resource's Links Percentage with Domain
	 */
	private Double calculeRLPDIndex(Resource resource, String domainName) {

		double RLPDindex = 0;

		double qtdLinksDomain = 0;
		double qtdLinkResource = 0;

		for (Link link : resource.getLinks()) {
			if(link.getDomain().getDomainName().equals(domainName)) {
				qtdLinksDomain += link.getQuantity();
			}
			qtdLinkResource += link.getQuantity();
		}

		RLPDindex = qtdLinksDomain / qtdLinkResource;

		return RLPDindex;

	}

	/*
	 * RDCP - Resource's Domain Concepts percentage
	 */
	private Double calculeRDCPIndex(Resource resource, String domainName) {
		double RDCPIndex = 0;
		DomainController domainController = new DomainController();
		SemanticRepository semanticRepository = new SemanticRepository();
		Domain domain = domainController.getDomain(domainName);
		double qtdConceptsResource = 0;
		double qtdConecptsDomain = 0;
		
		for(Link link : resource.getLinks()) {
			if(link.getDomain().getDomainName().equals(domainName))
				qtdConceptsResource++;
		}
		
		
		qtdConecptsDomain = semanticRepository.getAllIndividuals(domain).size();

		if (qtdConecptsDomain > 0) {
			RDCPIndex = qtdConceptsResource / qtdConecptsDomain;
		}
		
		return RDCPIndex;
	}
	
	/*
	 * UI - User Interest
	 */
	public Double calculeUIIndex(User user, String domain) {
		
		double UIIndex = 0;
		double qtdAccessDomain = 0;
		double ma = 0, mb = 0;
		double sumCDRIndex = 0;
		
		for(Access access : user.getAccess()) {
			double CDRIndex = cacluleCDRIndex(access.getResource(), domain);
			
			/* Only access related to a domiain*/
			if(CDRIndex >= 0.3) {
				qtdAccessDomain++;
				sumCDRIndex += CDRIndex;
			}
		}
		
		if(qtdAccessDomain > 0) {
			mb = sumCDRIndex / qtdAccessDomain;
		}
		
		if(user.getAccess().size() > 0) {
			ma = qtdAccessDomain / user.getAccess().size();
		}
		
		UIIndex = ma * mb;
		
		return UIIndex;
	}
}
