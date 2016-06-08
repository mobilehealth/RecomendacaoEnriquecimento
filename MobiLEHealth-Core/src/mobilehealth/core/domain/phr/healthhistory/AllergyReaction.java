package mobilehealth.core.domain.phr.healthhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="phr")
public class AllergyReaction {

	@Id
	@SequenceGenerator(name = "allergy_reaction_seq", sequenceName = "allergy_reaction_seq", schema="phr")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergy_reaction_seq")
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
	
	
}
