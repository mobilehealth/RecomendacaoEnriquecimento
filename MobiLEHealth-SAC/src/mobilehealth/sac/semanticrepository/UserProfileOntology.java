package mobilehealth.sac.semanticrepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChangeException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import mobilehealth.core.domain.Domain;
import mobilehealth.core.domain.Person;
import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Link;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;

/**
 * Creates an abstraction layer for accessing User Profile Ontologies file. The user profile
 * ontology is in OWL standard.
 * 
 * @author jdarlan
 * 
 */
public class UserProfileOntology {

	private File file;
	private OWLOntologyManager manager;
	private OWLDataFactory factory;
	private OWLOntology ontology;
	private IRI iri;

	/**
	 * Default Constructor Initializes attributes
	 * 
	 * @throws OWLOntologyCreationException
	 */
	public UserProfileOntology() throws OWLOntologyCreationException {
		file = new File("app/ontology/profile.owl");
		manager = OWLManager.createOWLOntologyManager();
		factory = OWLManager.getOWLDataFactory();
		ontology = manager.loadOntologyFromOntologyDocument(file);
		iri = ontology.getOntologyID().getOntologyIRI();
	}

	/**
	 * Find a Resource with all relations: - Links - Access
	 * 
	 * @param schema
	 * @param tableName
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Resource findResource(String schema, String tableName, String fieldName, int fieldValue) {

		Resource resource = null;

		OWLObjectProperty opHasLink = getObjectProperty("hasLink");
		OWLObjectProperty opAccessedFor = getObjectProperty("accessedFor");
		OWLClass resourceClass = factory.getOWLClass(IRI.create(iri + "#Resource"));

		OWLReasonerFactory reasonerFactory = new ElkReasonerFactory();
		OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);

		for (OWLIndividual resourceIndividual : resourceClass.getIndividuals(ontology)) {

			String lSchema = getLiteralDataProperty(resourceIndividual, "schema");
			String lTable = getLiteralDataProperty(resourceIndividual, "tableName");
			String lFieldName = getLiteralDataProperty(resourceIndividual, "fieldName");
			String sFieldValue = getLiteralDataProperty(resourceIndividual, "fieldValue");
			String sQuantityToken = getLiteralDataProperty(resourceIndividual, "quantityToken");
			String sDateProcess = getLiteralDataProperty(resourceIndividual, "date");

			/* Corre��o de erro quando no localiza o recurso */
			int lFieldValue = 0;
			if (!sFieldValue.isEmpty()) {
				lFieldValue = Integer.parseInt(sFieldValue);
			}

			int lQuantityToken = 0;
			if (!sQuantityToken.isEmpty()) {
				lQuantityToken = Integer.parseInt(sQuantityToken);
			}

			DateTime lDateProcess = new DateTime();
			if (!sDateProcess.isEmpty()) {
				DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
				lDateProcess = dtf.parseDateTime(sDateProcess);
			}

			if (lSchema.equals(schema) && lTable.equals(tableName) && lFieldName.equals(fieldName) && lFieldValue == fieldValue) {
				resource = new Resource();
				resource.setSchema(lSchema);
				resource.setTableName(lTable);
				resource.setFieldName(lFieldName);
				resource.setFieldValue(lFieldValue);
				resource.setUri(((OWLEntity) resourceIndividual).getIRI().toString());
				resource.setDateProcess(new DateTime(lDateProcess));
				resource.setQuantityToken(lQuantityToken);

				/* Resource Access */
				Set<OWLIndividual> accessIndividuals = resourceIndividual.getObjectPropertyValues(opAccessedFor, ontology);
				List<Access> allAccess = new ArrayList<Access>();
				for (OWLIndividual accessIndividual : accessIndividuals) {

					Access access = new Access();
					access.setResource(resource);
					access.setUri(((OWLEntity) accessIndividual).getIRI().toString());

					String sDateAccess = getLiteralDataProperty(accessIndividual, "date");
					if (!sDateAccess.isEmpty()) {
						DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
						DateTime lDateAccess = dtf.parseDateTime(sDateAccess);
						access.setDateAccess(lDateAccess);
					}

					OWLIndividual userIndividual = (OWLIndividual) resourceIndividual.getObjectPropertyValues(opAccessedFor, ontology).toArray()[0];
					String sUserId = getLiteralDataProperty(userIndividual, "id");
					int userId = 0;
					if (!sUserId.isEmpty()) {
						userId = Integer.parseInt(sUserId);
					}

					User user = new User();
					Person person = new Person();
					person.setId(userId);
					user.setPerson(person);

					access.setUser(user);

					allAccess.add(access);
				}
				resource.setAccess(allAccess);

				Set<OWLIndividual> linkIndividuals = resourceIndividual.getObjectPropertyValues(opHasLink, ontology);

				List<Link> links = new ArrayList<Link>();
				for (OWLIndividual linkIndividual : linkIndividuals) {

					// linkIndividual.asOWLNamedIndividual().getAnnotations(ontology,
					// factory.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI()));

					String lRoot = getLiteralDataProperty(linkIndividual, "root");
					String lDomainUri = getLiteralDataProperty(linkIndividual, "domainURI");
					String lDomainName = getLiteralDataProperty(linkIndividual, "domainName");
					String sQuantity = getLiteralDataProperty(linkIndividual, "quantity");

					int lQuantity = 0;
					if (!sQuantity.isEmpty()) {
						lQuantity = Integer.parseInt(sQuantity);
					}

					Domain domain = new Domain();
					domain.setDomainName(lDomainName);

					Link link = new Link();
					link.setDomainURI(lDomainUri);
					link.setQuantity(lQuantity);
					link.setRoot(lRoot);
					link.setResource(resource);
					link.setUri(((OWLEntity) linkIndividual).getIRI().toString());
					link.setDomain(domain);

					links.add(link);
				}
				resource.setLinks(links);

				break;
			}
		}

		return resource;
	}

	/**
	 * Save resource in profile ontology file
	 * 
	 * @param resource
	 */
	public void saveResource(Resource resource) {

		/* if resource exist all link should be deleted */
		if (findResource(resource.getSchema(), resource.getTableName(), resource.getFieldName(), resource.getFieldValue()) != null) {
			deleteLinks(resource);
			deleteDataProperty(resource);
		}

		OWLObjectProperty opHasLink = getObjectProperty("hasLink");
		OWLObjectProperty opIsLink = getObjectProperty("isLink");
		OWLClass resourceClass = factory.getOWLClass(IRI.create(iri + "#Resource"));
		OWLClass linkClass = factory.getOWLClass(IRI.create(iri + "#Link"));
		OWLNamedIndividual individual = getNamedIndividual(resource.getKeyOntology());

		try {

			Set<OWLAxiom> axioms = new HashSet<OWLAxiom>();

			/* link individual to Resource class */
			axioms.add(factory.getOWLClassAssertionAxiom(resourceClass, individual));

			/* add individual data porperties */
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("schema"), individual, resource.getSchema()));
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("tableName"), individual, resource.getTableName()));
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("fieldName"), individual, resource.getFieldName()));
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("fieldValue"), individual, resource.getFieldValue()));
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("date"), individual, resource.getDateProcess().toString("dd/MM/YYYY hh:mm:ss")));

			if (resource.getQuantityToken() > 0) {
				axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("quantityToken"), individual, resource.getQuantityToken()));
			}

			/* get resource links */
			for (Link link : resource.getLinks()) {

				/* get link individual */
				OWLNamedIndividual linkIndividual = getNamedIndividual(link.getKeyOntology());

				/* link individual to Link class */
				axioms.add(factory.getOWLClassAssertionAxiom(linkClass, linkIndividual));

				/* add individual data properties */
				axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("root"), linkIndividual, link.getRoot()));
				axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("domainURI"), linkIndividual, link.getDomainURI()));
				axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("quantity"), linkIndividual, link.getQuantity()));
				axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("domainName"), linkIndividual, link.getDomain().getDomainName()));
				axioms.add(factory.getOWLObjectPropertyAssertionAxiom(opHasLink, individual, linkIndividual));
				axioms.add(factory.getOWLObjectPropertyAssertionAxiom(opIsLink, linkIndividual, individual));

			}

			manager.addAxioms(ontology, axioms);
			manager.saveOntology(ontology);

		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Delete a Resource and your dependences: Links
	 * 
	 * @param resource
	 */
	public void deleteResource(Resource resource) {

		deleteLinks(resource);

		List<OWLNamedIndividual> individuals = new ArrayList<OWLNamedIndividual>();
		individuals.add(getNamedIndividual(resource.getKeyOntology()));

		deleteIndividuals(individuals);

	}

	/**
	 * 
	 * @param resource
	 */
	private void deleteDataProperty(Resource resource) {
		// TODO Salatiel: Deletar os data properties do resource para evitar duplicacao
		// Obtendo refer�ncia para a entidade

		Set<OWLAxiom> collectedAxioms = new TreeSet<OWLAxiom>();
		OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(iri + "#" + resource.getKeyOntology()));
		Set<OWLIndividualAxiom> individualAxioms = ontology.getAxioms(ind);

		for (OWLIndividualAxiom axiom : individualAxioms) {
			// Apenas rela��es do tipo DataPropertyAssertion
			if ((axiom.getAxiomType().toString()).equals("DataPropertyAssertion")) {
				collectedAxioms.add(axiom);
			}
		}

		for (OWLAxiom axiom : collectedAxioms) {
			try {
				manager.applyChange(new RemoveAxiom(ontology, axiom));
			} catch (OWLOntologyChangeException e) {

			}
		}

		try {
			manager.saveOntology(ontology);
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * 
	 * @param resource
	 */
	private void deleteLinks(Resource resource) {

		List<OWLNamedIndividual> individuals = new ArrayList<OWLNamedIndividual>();

		for (Link link : resource.getLinks()) {
			individuals.add(factory.getOWLNamedIndividual(IRI.create(iri + "#" + link.getResource().getKeyOntology() + "_" + link.getRoot())));
		}

		deleteIndividuals(individuals);

	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<Access> findAccessByUser(User user) {
		List<Access> accesses = new ArrayList<Access>();

		// Segunda vers�o, feita diretamente pelo m�todo
		OWLClass userClass = factory.getOWLClass(IRI.create(iri + "#User"));
		OWLObjectProperty opHasAccess = factory.getOWLObjectProperty(IRI.create(iri + "#hasAccess"));

		for (OWLIndividual userIndividual : userClass.getIndividuals(ontology)) {
			String lId = getLiteralDataProperty(userIndividual, "id");

			/* Corre��o de erro quando no localiza o recurso */
			int userId = 0;
			if (!lId.isEmpty()) {
				userId = Integer.parseInt(lId);
			}

			if (userId == user.getPerson().getId()) {
				user = new User();
				user.getPerson().setId(userId);
				user.setUri(((OWLEntity) userIndividual).getIRI().toString());

				/* Gets Access */
				Set<OWLIndividual> accessIndividuals = userIndividual.getObjectPropertyValues(opHasAccess, ontology);
				for (OWLIndividual accessIndividual : accessIndividuals) {
					Access access = new Access();
					access.setUser(user);
					access.setUri(((OWLEntity) accessIndividual).getIRI().toString());

					String sDateAccess = getLiteralDataProperty(accessIndividual, "date");
					if (!sDateAccess.isEmpty()) {
						DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
						DateTime lDateAccess = dtf.parseDateTime(sDateAccess);
						access.setDateAccess(lDateAccess);
					}
					
					Set<OWLIndividual> resourceIndividuals = accessIndividual.getObjectPropertyValues(getObjectProperty("hasResource"), ontology);
					for (OWLIndividual resourceIndividual : resourceIndividuals) {
						
						String schema = getLiteralDataProperty(resourceIndividual, "schema");
						String tableName = getLiteralDataProperty(resourceIndividual, "tableName");
						String fieldName = getLiteralDataProperty(resourceIndividual, "fieldName");
						String sFieldValue = getLiteralDataProperty(resourceIndividual, "fieldValue");

						int fieldValue = 0;
						if (!sFieldValue.isEmpty()) {
							fieldValue = Integer.parseInt(sFieldValue);
						}
						
						Resource resrouce = findResource(schema, tableName, fieldName, fieldValue);
						access.setResource(resrouce);
						
						/*
						 * Para a modelagem do sistema � para existir apenas um recurso vinculado ao acess.
						 */
						break;
					}

					accesses.add(access);

				}
				break;
			}

		}
		return accesses;
	}

	public void deleteAccess(Access access) {

		OWLEntityRemover remover = new OWLEntityRemover(manager, Collections.singleton(ontology));
		OWLNamedIndividual indAccess = factory.getOWLNamedIndividual(IRI.create(access.getUri()));

		indAccess.accept(remover);

		manager.applyChanges(remover.getChanges());
		try {
			manager.saveOntology(ontology);
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}

	}

	public void saveAccess(Access access) {

		OWLObjectProperty opHasResource = getObjectProperty("hasResource");
		OWLObjectProperty opAccessBy = getObjectProperty("accessedBy");
		OWLObjectProperty opAccessFor = getObjectProperty("accessFor");
		OWLObjectProperty opHasAccess = getObjectProperty("hasAccess");

		OWLClass accessClass = factory.getOWLClass(IRI.create(iri + "#Access"));

		try {

			Set<OWLAxiom> axioms = new HashSet<OWLAxiom>();

			/* Vincula o individuo a classe resource */
			OWLNamedIndividual ind = getNamedIndividual(access.getKeyOntology());
			axioms.add(factory.getOWLClassAssertionAxiom(accessClass, ind));

			/* Adiciona os data properties do individuo */
			// Duplica data?
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("date"), ind, access.getDateAccess().toString("dd/MM/YYYY hh:mm:ss")));

			// Adicionando rela��o ACCESS com RESOURCE
			OWLNamedIndividual linkIndividual = getNamedIndividual(access.getResource().getKeyOntology());
			axioms.add(factory.getOWLObjectPropertyAssertionAxiom(opHasResource, ind, linkIndividual));

			// Adicionando rela��o RESOURCE com ACCESS
			axioms.add(factory.getOWLObjectPropertyAssertionAxiom(opAccessFor, linkIndividual, ind));

			// Adicionando rela��o ACCESS com USER
			OWLNamedIndividual linkIndividual2 = getNamedIndividual("" + access.getUser().getKeyOntology());
			axioms.add(factory.getOWLObjectPropertyAssertionAxiom(opAccessBy, ind, linkIndividual2));

			// Adicionando rela��o USER com ACCESS
			axioms.add(factory.getOWLObjectPropertyAssertionAxiom(opHasAccess, linkIndividual2, ind));

			manager.addAxioms(ontology, axioms);
			manager.saveOntology(ontology);

		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param user
	 */
	public void saveUser(User user) {

		try {
			OWLClass userClass = factory.getOWLClass(IRI.create(iri + "#User"));
			OWLNamedIndividual ind = getNamedIndividual(user.getKeyOntology());

			Set<OWLAxiom> axioms = new HashSet<OWLAxiom>();

			/* Link class and individuo */
			axioms.add(factory.getOWLClassAssertionAxiom(userClass, ind));

			/* add individual data properties */
			axioms.add(factory.getOWLDataPropertyAssertionAxiom(getDataProperty("id"), ind, user.getPerson().getId()));

			/* save axioms ontology */
			manager.addAxioms(ontology, axioms);
			manager.saveOntology(ontology);

		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}

	}

	public User findUser(int id) {

		User user = null;

		OWLClass userClass = factory.getOWLClass(IRI.create(iri + "#User"));
		OWLObjectProperty opHasAccess = getObjectProperty("hasAccess");
		OWLObjectProperty opHasResource = getObjectProperty("hasResource");

		/* Search in all Resource Class individuals for individual with id parameter */
		for (OWLIndividual userIndividual : userClass.getIndividuals(ontology)) {

			String lId = getLiteralDataProperty(userIndividual, "id");

			/* convert string to integer */
			int userId = 0;
			if (!lId.isEmpty()) {
				userId = Integer.parseInt(lId);
			}

			/* User founded */
			if (userId == id) {

				user = new User();
				user.getPerson().setId(userId);
				user.setUri(((OWLEntity) userIndividual).getIRI().toString());

				/* Get Accesses */
				Set<OWLIndividual> accessIndividuals = userIndividual.getObjectPropertyValues(opHasAccess, ontology);
				List<Access> allAccess = new ArrayList<Access>();
				for (OWLIndividual accessIndividual : accessIndividuals) {

					Access access = new Access();
					access.setUser(user);
					access.setUri(((OWLEntity) accessIndividual).getIRI().toString());

					String sDateAccess = getLiteralDataProperty(accessIndividual, "date");
					if (!sDateAccess.isEmpty()) {
						DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
						DateTime lDateAccess = dtf.parseDateTime(sDateAccess);
						access.setDateAccess(lDateAccess);
					}

					/* Access must be only resource linked */
					OWLIndividual resourceIndividual = (OWLIndividual) accessIndividual.getObjectPropertyValues(opHasResource, ontology).toArray()[0];

					String lSchema = getLiteralDataProperty(resourceIndividual, "schema");
					String lTable = getLiteralDataProperty(resourceIndividual, "tableName");
					String lFieldName = getLiteralDataProperty(resourceIndividual, "fieldName");
					String sFieldValue = getLiteralDataProperty(resourceIndividual, "fieldValue");

					/* Convert String to Int */
					int lFieldValue = 0;
					if (!sFieldValue.isEmpty()) {
						lFieldValue = Integer.parseInt(sFieldValue);
					}

					/* set resource relates to access */
					access.setResource(findResource(lSchema, lTable, lFieldName, lFieldValue));

					allAccess.add(access);
				}

				/* set access relate to user */
				user.setAccess(allAccess);

				break;
			}

		}

		return user;
	}

	/**
	 * 
	 * @param resource
	 */
	public void deleteUser(User user) {

		List<OWLNamedIndividual> individuals = new ArrayList<OWLNamedIndividual>();
		individuals.add(getNamedIndividual(user.getKeyOntology()));
		deleteIndividuals(individuals);

	}

	/**
	 * 
	 * @param individuals
	 */
	private void deleteIndividuals(List<OWLNamedIndividual> individuals) {

		OWLEntityRemover remover = new OWLEntityRemover(manager, Collections.singleton(ontology));

		for (OWLNamedIndividual individual : individuals) {
			individual.accept(remover);
		}

		try {
			manager.applyChanges(remover.getChanges());
			manager.saveOntology(ontology);
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param keyOntology
	 * @return
	 */
	private OWLNamedIndividual getNamedIndividual(String keyOntology) {
		return factory.getOWLNamedIndividual(IRI.create(iri + "#" + keyOntology));
	}

	/**
	 * 
	 * @param dataPropertyLiteral
	 * @return
	 */
	private OWLDataProperty getDataProperty(String dataPropertyLiteral) {
		return factory.getOWLDataProperty(IRI.create(iri + "#" + dataPropertyLiteral));
	}

	/**
	 * 
	 * @param objectPropertyLiteral
	 * @return
	 */
	private OWLObjectProperty getObjectProperty(String objectPropertyLiteral) {
		return factory.getOWLObjectProperty(IRI.create(iri + "#" + objectPropertyLiteral));
	}

	/**
	 * 
	 * @param individual
	 * @param dataPropertyLiteral
	 * @return
	 */
	private String getLiteralDataProperty(OWLIndividual individual, String dataPropertyLiteral) {
		String dpValue = "";

		Set<OWLLiteral> dpValues = individual.getDataPropertyValues(getDataProperty(dataPropertyLiteral), ontology);
		if (!dpValues.isEmpty()) {
			dpValue = ((OWLLiteral) dpValues.toArray()[0]).getLiteral();
		}

		return dpValue;
	}

	/**
	 * 
	 * @param user
	 * @param resource
	 * @param dateTime
	 * @return access
	 */
	public Access findAccess(User user, Resource resource, DateTime dateTime) {

		Access access = null;
		int x;

		for (Access userAccess : findAccessByUser(user)) {

			boolean isEqual = true;

			if (!userAccess.getResource().getSchema().equals(resource.getSchema())) {
				isEqual = false;
			}

			if (isEqual && !userAccess.getResource().getTableName().equals(resource.getTableName())) {
				isEqual = false;
			}

			if (isEqual && !userAccess.getResource().getFieldName().equals(resource.getFieldName())) {
				isEqual = false;
			}

			if (isEqual && userAccess.getResource().getFieldValue() != resource.getFieldValue()) {
				isEqual = false;
			}

			if (isEqual && dateTime.getMillis() != userAccess.getDateAccess().getMillis()) {
				isEqual = false;
			}

			if (isEqual) {
				access = userAccess;
				break;
			}

		}

		return access;
	}

}
