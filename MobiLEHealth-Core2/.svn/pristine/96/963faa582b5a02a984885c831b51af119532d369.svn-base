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
public class Condition {

	@Id
	@SequenceGenerator(name = "condition_seq", sequenceName = "condition_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_conditionstatus")
	private ConditionStatus status;
	
	@Type(type = "timestamp")
	private DateTime startDate;
	
	@Type(type = "timestamp")
	private DateTime endDate;
	
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

	public ConditionStatus getStatus() {
		return status;
	}

	public void setStatus(ConditionStatus status) {
		this.status = status;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	

}
