package mobilehealth.sac.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import mobilehealth.core.domain.Person;
import mobilehealth.sac.ontology.Link;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.semanticrepository.UserProfileOntology;

public class OWLAPITest {
	
	public static void main(String[] args) {
		//find(1);
		//saveUser();
		//findUser();
		//deleteUser();
		loadOntology();
	}
	
	public static void loadOntology(){
		
		try{
			File file = new File("app/ontology/profile2.owl");
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			OWLDataFactory factory = OWLManager.getOWLDataFactory();
			OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
			IRI iri = ontology.getOntologyID().getOntologyIRI();
			System.out.println(iri.toString());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void saveUser() {
		UserProfileOntology userProfile = null;
		try {
			userProfile = new UserProfileOntology();
			
			Person person = new Person();
			person.setId(1);
			
			User user = new User();
			user.setPerson(person);
			
			userProfile.saveUser(user);
			
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

	}
	
	public static void findUser() {
		UserProfileOntology userProfile = null;
		try {
			userProfile = new UserProfileOntology();
			
			User user = userProfile.findUser(2);
			
			if(user != null) {
				System.out.println("Encontrou");
			} else {
				System.out.println("N�o encontrou");
			}
			
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteUser() {
		UserProfileOntology userProfile = null;
		try {
			userProfile = new UserProfileOntology();
			
			Person person = new Person();
			person.setId(1);
			
			User user = new User();
			user.setPerson(person);
			
			userProfile.deleteUser(user);
			
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		
	}

	public static void find(int id) {
		UserProfileOntology userProfile = null;

		try {
			userProfile = new UserProfileOntology();

			Resource resource2 = userProfile.findResource("public", "table", "id", id);

			if (resource2 != null) {
				System.out.println("#Find: Achou");
			} else {
				System.out.println("#Find: N�o achou");
			}
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

	}

	public static void save(int id) {
		UserProfileOntology userProfile = null;

		try {
			userProfile = new UserProfileOntology();

			// Teste salvar
			Resource resource = new Resource();
			resource.setSchema("public");
			resource.setTableName("table");
			resource.setFieldName("id");
			resource.setFieldValue(id);
			resource.setDateProcess(new DateTime());
			resource.setQuantityToken(1);

			List<Link> links = new ArrayList<Link>();

			for (int i = 1; i <= 5; i++) {
				Link link = new Link();
				link.setRoot("word" + i);
				link.setDomainURI("http://domain.uri/domain#" + i);
				link.setQuantity(1);
				link.setResource(resource);
				links.add(link);
			}
			resource.setLinks(links);

			userProfile.saveResource(resource);
			System.out.println("#Save: Criado resource " + id);

		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

	}

	public static void delete(int id) {
		UserProfileOntology userProfile = null;

		try {
			userProfile = new UserProfileOntology();

			// Teste salvar
			Resource resource = new Resource();
			resource.setSchema("public");
			resource.setTableName("table");
			resource.setFieldName("id");
			resource.setFieldValue(id);
			// resource.setDateProcess(new DateTime());

			userProfile.deleteResource(resource);
			System.out.println("#Delete: deletado recurso " + id);

		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}

	}
}
