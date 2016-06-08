package mobilehealth.sac.augmentation;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.semanticrepository.UserProfileOntology;

/**
 * 
 * @author Jonathan Darlan
 * @date 07/10/2014
 */
public class SemanticIndexer {

	private UserProfileOntology userProfile;

	/**
	 * Default Constructor
	 */
	public SemanticIndexer() {
		try {
			userProfile = new UserProfileOntology();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save a resource in user profile ontology.
	 * 
	 * @param resource
	 */
	public void resource(Resource resource) {
		userProfile.saveResource(resource);
	}

	/**
	 * 
	 * @param access
	 */
	public void access(Access access) {

		/*
		 * Tive que obter o user e o resource para poder usar o findAccess, estava dando
		 * nullException
		 */
		User user = userProfile.findUser(access.getUser().getPerson().getId());

		if (user == null) {
			userProfile.saveUser(access.getUser());
		}

		Resource resource = userProfile.findResource(access.getResource().getSchema(), access.getResource().getTableName(), access.getResource().getFieldName(), access.getResource().getFieldValue());
		if (resource != null) {
			access.setUser(userProfile.findUser(access.getUser().getPerson().getId()));
			access.setResource(userProfile.findResource(access.getResource().getSchema(), access.getResource().getTableName(), access.getResource().getFieldName(), access.getResource().getFieldValue()));

			if (userProfile.findAccess(access.getUser(), access.getResource(), access.getDateAccess()) == null) {
				userProfile.saveAccess(access);
			}
		}

	}
}
