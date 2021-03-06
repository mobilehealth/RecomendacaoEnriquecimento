package mobilehealth.core.domain.phr.healthhistory;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import mobilehealth.core.domain.Person;

@Entity
@Table(schema="phr")
public class Allergy {

	@Id
	@SequenceGenerator(name = "allergy_seq", sequenceName = "allergy_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergy_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_allergyreaction")
	private AllergyReaction reaction;

	@ManyToOne
	@JoinColumn(name = "id_allergytype")
	private AllergyType type;
	
	@Type(type = "timestamp")
	private Date firstObserved;
	
	private String allergenCode;
	
	private String observation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AllergyReaction getReaction() {
		return reaction;
	}

	public void setReaction(AllergyReaction reaction) {
		this.reaction = reaction;
	}

	public AllergyType getType() {
		return type;
	}

	public void setType(AllergyType type) {
		this.type = type;
	}

	public Date getFirstObserved() {
		return firstObserved;
	}

	public void setFirstObserved(Date firstObserved) {
		this.firstObserved = firstObserved;
	}

	public String getAllergenCode() {
		return allergenCode;
	}

	public void setAllergenCode(String allergenCode) {
		this.allergenCode = allergenCode;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
}
