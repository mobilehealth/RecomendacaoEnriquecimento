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
public class Cholesterol {

	@Id
	@SequenceGenerator(name = "cholesterol_seq", sequenceName = "cholesterol_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cholesterol_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	private float ldl;
	
	private float hdl;
	
	private float triglycerides;
	
	private float TotalCholesterol;

	@Type(type = "timestamp")
	private DateTime dateTime;

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

	public float getLdl() {
		return ldl;
	}

	public void setLdl(float ldl) {
		this.ldl = ldl;
	}

	public float getHdl() {
		return hdl;
	}

	public void setHdl(float hdl) {
		this.hdl = hdl;
	}

	public float getTriglycerides() {
		return triglycerides;
	}

	public void setTriglycerides(float triglycerides) {
		this.triglycerides = triglycerides;
	}

	public float getTotalCholesterol() {
		return TotalCholesterol;
	}

	public void setTotalCholesterol(float totalCholesterol) {
		TotalCholesterol = totalCholesterol;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}
