package mobilehealth.sac.ontology;

import mobilehealth.core.domain.Domain;

public class Link {
	
	private Resource resource;
	
	/* URI do Link na Ontologia de Perfil de usuário */
	private String uri;
	
	/* URI da Ontologia de dominio para a palavra (root) */
	private String domainURI;
	
	private int quantity;
	
	private String root;
	
	private Domain domain;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDomainURI() {
		return domainURI;
	}

	public void setDomainURI(String domainURI) {
		this.domainURI = domainURI;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getKeyOntology() {
		return resource.getKeyOntology() + "_" + root.replace(" ", "");
	}
}
