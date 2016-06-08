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
public class PeakFlow {

	@Id
	@SequenceGenerator(name = "peak_flow_seq", sequenceName = "peak_flow_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peak_flow_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	private float pef;
	
	private float fev1;
	
	private float fev6;
	
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

	public float getPef() {
		return pef;
	}

	public void setPef(float pef) {
		this.pef = pef;
	}

	public float getFev1() {
		return fev1;
	}

	public void setFev1(float fev1) {
		this.fev1 = fev1;
	}

	public float getFev6() {
		return fev6;
	}

	public void setFev6(float fev6) {
		this.fev6 = fev6;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}
