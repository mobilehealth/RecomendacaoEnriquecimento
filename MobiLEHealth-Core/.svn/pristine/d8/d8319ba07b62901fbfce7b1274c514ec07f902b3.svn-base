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
public class Medication {

	@Id
	@SequenceGenerator(name = "medication_seq", sequenceName = "medication_seq", schema="phr")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medication_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;

	private String name;
	
	private float strength;
	
	private String strengthUnit;
	
	private float dosage;
	
	private String dosageUnit;
	
	private String howTaken;
	
	private String reasonTaking;
	
	@Type(type = "timestamp")
	private DateTime startDate;
	
	@Type(type = "timestamp")
	private DateTime endDate;
	
	@Type(type = "timestamp")
	private DateTime prescriptionDate;
	
	private String prescriptionQuantity;
	
	private String instruction;

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

	public float getStrength() {
		return strength;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}

	public String getStrengthUnit() {
		return strengthUnit;
	}

	public void setStrengthUnit(String strengthUnit) {
		this.strengthUnit = strengthUnit;
	}

	public float getDosage() {
		return dosage;
	}

	public void setDosage(float dosage) {
		this.dosage = dosage;
	}

	public String getDosageUnit() {
		return dosageUnit;
	}

	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public String getHowTaken() {
		return howTaken;
	}

	public void setHowTaken(String howTaken) {
		this.howTaken = howTaken;
	}

	public String getReasonTaking() {
		return reasonTaking;
	}

	public void setReasonTaking(String reasonTaking) {
		this.reasonTaking = reasonTaking;
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

	public DateTime getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(DateTime prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public String getPrescriptionQuantity() {
		return prescriptionQuantity;
	}

	public void setPrescriptionQuantity(String prescriptionQuantity) {
		this.prescriptionQuantity = prescriptionQuantity;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
}
