package mobilehealth.sac.test;

import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import mobilehealth.core.domain.Person;
import mobilehealth.sac.SACExternalService;
import mobilehealth.sac.SACExternalServiceImpl;
import mobilehealth.sac.augmentation.SemanticAugmentation;
import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Link;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.semanticrepository.UserProfileOntology;

public class SASAccessTest {

	private SemanticAugmentation semanticAugmentation;
	
	public static void main(String[] args) {
		SASAccessTest test = new SASAccessTest();
		
		test.submitAccess();
		test.testGetOntology();
		test.testUsersIndexes();
		
		System.exit(0);
	}

	public SASAccessTest() {
		semanticAugmentation = new SemanticAugmentation();
	}
	
	private void testUsersIndexes() {
		testUserIndexes(1);
		testUserIndexes(2);
		testUserIndexes(3);
	}
	
	private void testUserIndexes(int idUser) {
		
		SACExternalService sacService = new SACExternalServiceImpl();
		
		Person person = new Person();
		person.setId(idUser);
		
		System.out.println("Results for user " + person.getId());
		for (Entry<String, Double> entry : sacService.getDegreeOfInterestsDomains(person).entrySet()) {
			System.out.println(person.getId() + ": " + entry.getKey() + " - " + entry.getValue());
		}
	}

	private void testGetOntology() {
		getOntology(1);
		getOntology(2);
		getOntology(3);
	}
	
	private void submitAccess() {

		// USer 1
		testAccess(1, "public", "content", "id", 1);
		testAccess(1, "public", "content", "id", 2);

		// User 2
		testAccess(2, "public", "content", "id", 1);
		testAccess(2, "public", "content", "id", 3);
		testAccess(2, "public", "content", "id", 4);

		// User 3
		testAccess(3, "public", "content", "id", 3);
		testAccess(3, "public", "content", "id", 4);
		testAccess(3, "public", "content", "id", 2);

		
	}
	
	private void testAccess(int idUser, String schema, String tableName, String FieldName, int FieldVauel) {

		Person person = new Person();
		person.setId(idUser);

		User user = new User();
		user.setPerson(person);

		Resource resource = new Resource();
		resource.setSchema(schema);
		resource.setTableName(tableName);
		resource.setFieldName(FieldName);
		resource.setFieldValue(FieldVauel);

		Access access = new Access();
		access.setUser(user);
		access.setResource(resource);
		access.setDateAccess(DateTime.now());

		semanticAugmentation.execute(access);

	}

	public void getOntology(int idUser) {

		UserProfileOntology userProfileOntology = null;
		User user;

		try {
			userProfileOntology = new UserProfileOntology();
			
			// TODO Não está trazendo o acess
			/*
			 * Está faltando na gravação vincular no usuario o acesso e no recurso também
			 */
			user = userProfileOntology.findUser(idUser);

			System.out.println("User ID: " + user.getPerson().getId());
			System.out.println("URI: " + user.getUri());
			System.out.println("Key Ontology: " + user.getKeyOntology());

			for (Access access : user.getAccess()) {
				access.getKeyOntology();
				access.getDateAccess();
				access.getUri();
				
				Resource resource = access.getResource();

				System.out.println("Resource founded: " + resource.getFieldValue());
				System.out.println("Date Process: " + resource.getDateProcess());
				System.out.println("IRI: " + resource.getUri());
				System.out.println("Links:");
				for (Link link : resource.getLinks()) {
					System.out.println("- IRI: " + link.getUri());
					System.out.println("- Domain Name: " + link.getDomain().getDomainName());
					System.out.println("- Domain IRI: " + link.getDomainURI());
					System.out.println("- Root: " + link.getRoot());
					System.out.println("- Quantity: " + link.getQuantity());
					System.out.println("- ID Resource: " + link.getResource().getFieldValue());
				}
			}

		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

	}

}
