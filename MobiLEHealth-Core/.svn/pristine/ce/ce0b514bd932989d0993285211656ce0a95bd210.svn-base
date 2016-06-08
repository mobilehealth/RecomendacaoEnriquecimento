package mobilehealth.core.domain.phr.healthhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import mobilehealth.core.domain.Person;

@Entity
@Table(schema="phr")
public class Immunization {

	@Id
	@SequenceGenerator(name = "immunization_seq", sequenceName = "immunization_seq", schema="phr")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "immunization_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	private String name;
	
	@Type(type = "timestamp")
	private DateTime dateReceived;
	
	private String numberSequence;
	
	private String adverseEffect;
	
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

	public DateTime getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(DateTime dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getNumberSequence() {
		return numberSequence;
	}

	public void setNumberSequence(String numberSequence) {
		this.numberSequence = numberSequence;
	}

	public String getAdverseEffect() {
		return adverseEffect;
	}

	public void setAdverseEffect(String adverseEffect) {
		this.adverseEffect = adverseEffect;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
}
