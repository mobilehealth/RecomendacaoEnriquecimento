package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RelatePersonTagPK implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_person")
	private Integer person;
//	private Long person;
	
	@Id
	@Column(name="id_tag")
	private Integer tag; // foi alterado para INTEGER
	//private Long tag;

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
}
