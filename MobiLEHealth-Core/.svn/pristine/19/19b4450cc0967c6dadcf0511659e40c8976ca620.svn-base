package mobilehealth.sac.ontology;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.domain.Person;

public class User {
	
	private Person person;
	
	private List<Access> access;
	
	private String uri;
	
	public User() {
		access = new ArrayList<Access>();
		person = new Person();
	}
	
	public String getKeyOntology() {
		/* ""+ -> convert to String */
		return ""+getPerson().getId();
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Access> getAccess() {
		return access;
	}

	public void setAccess(List<Access> access) {
		this.access = access;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
