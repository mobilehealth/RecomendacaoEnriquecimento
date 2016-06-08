package mobilehealth.core.domain.phr.measurement;

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
public class BloodGlucose {
	
	@Id
	@SequenceGenerator(name = "blood_glucose_seq", sequenceName = "blood_glucose_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_glucose_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	/* in mg/dL */
	private float measure;
	
	@ManyToOne
	@JoinColumn(name = "id_bloodglucosecontext")
	private BloodGlucoseContext context;
	
	@Type(type = "timestamp")
	private DateTime datetime;

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

	public float getMeasure() {
		return measure;
	}

	public void setMeasure(float meansure) {
		this.measure = meansure;
	}

	public BloodGlucoseContext getContext() {
		return context;
	}

	public void setContext(BloodGlucoseContext context) {
		this.context = context;
	}

	public DateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(DateTime datetime) {
		this.datetime = datetime;
	}
	
}
