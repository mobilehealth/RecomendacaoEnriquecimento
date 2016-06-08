package mobilehealth.sac;

import java.util.Map;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;

/**
 * External interface of SAC. Contains only methods for integration with
 * MobiLEHealth.
 * 
 * @author Jonathan Darlan
 * @date 01/08/2014
 * 
 */
public interface SACExternalService {

	/**
	 * Gets the degree of interest of Person for all domains register in system
	 * 
	 * The degree of interest can vary 0-1, determining the relationship of the
	 * user with the domain knowledge. if value is greater or equal than 0.3 the
	 * user has interest in domain.
	 * 
	 * @param person
	 * @return
	 */
	public Map<String, Double> getDegreeOfInterestsDomains(Person person);

	/**
	 * Gets degree of interest of Person for a specific domain. If domain is not
	 * exist return 0 (zero) value for domain.
	 * 
	 * The degree of interest can vary 0-1, determining the relationship of the
	 * user with the domain knowledge. if value is greater or equal than 0.3 the
	 * user has interest in domain.
	 * 
	 * @param person
	 * @param domain
	 * @return
	 */
	public Double getDegreeofInterest(Person person, String domain);

	/**
	 * Gets the relationship level of content for all domains register in
	 * system.
	 * 
	 * The index can vary 0-1, determining the relationship of the content with
	 * the domain knowledge. if value is greater or equal than 0.3 the content
	 * is related with domain.
	 * 
	 * @param content
	 * @return
	 */
	public Map<String, Double> getRelationshipLevelDomains(Content content);

	/**
	 * Gets the relationship level of content for a specific domain. If domain
	 * is not exist return 0 (zero) value for domain.
	 * 
	 * The index can vary 0-1, determining the relationship of the content with
	 * the domain knowledge. if value is greater or equal than 0.3 the content
	 * is related with domain.
	 * 
	 * @param content
	 * @param domain
	 * @return
	 */
	public Double getRelationshipLevelDomain(Content content, String domain);
}
