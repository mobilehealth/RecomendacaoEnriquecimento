package mobilehealth.core.domain.phr.measurement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="phr")
public class BloodGlucoseContext {
	
	@Id
	@SequenceGenerator(name = "blood_glucose_context_seq", sequenceName = "blood_glucose_context_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_glucose_context_seq")
	private int id;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Insert in table:
	 * 
	 * After breakfast
	 * After dinner
	 * After exercise
	 * After lunch
	 * After meal
	 * Before bedtime
	 * Before breakfast
	 * Before dinner
	 * Before exercise
	 * Before lunch
	 * Before meal
	 * Fasting
	 * Ignore
	 * Non-fasting
	 */
}
