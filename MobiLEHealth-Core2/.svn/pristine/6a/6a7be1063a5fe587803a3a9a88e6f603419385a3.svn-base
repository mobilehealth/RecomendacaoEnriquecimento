package mobilehealth.core.domain.phr.measurement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mobilehealth.core.domain.Person;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(schema="phr")
public class BloodPressure {
	
	@Id
	@SequenceGenerator(name = "blood_pressure_seq", sequenceName = "blood_pressure_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_pressure_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	private float systolic;
	
	private float diastolic;
	
	private float pulse;
	
	private boolean irregularHeartbeat;
	
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

	public float getSystolic() {
		return systolic;
	}

	public void setSystolic(float systolic) {
		this.systolic = systolic;
	}

	public float getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(float diastolic) {
		this.diastolic = diastolic;
	}

	public float getPulse() {
		return pulse;
	}

	public void setPulse(float pulse) {
		this.pulse = pulse;
	}

	public boolean isIrregularHeartbeat() {
		return irregularHeartbeat;
	}

	public void setIrregularHeartbeat(boolean irregularHeartbeat) {
		this.irregularHeartbeat = irregularHeartbeat;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

}
