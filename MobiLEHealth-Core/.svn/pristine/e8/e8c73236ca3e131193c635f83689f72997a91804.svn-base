package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;


public class RelatePersonLocationPK implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_person")
	private Integer person;
	
	
	
	@Id
	@Column(name="id_location")
	private Integer location;

	@Id
	@Column(name = "status")
	private int status;

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public int getPerson() {
		return person;
	}

	
	public void setPerson(int person) {
		this.person = person;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	//-----------------------------------------------------------
	// hashCode and equals
	//-----------------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatePersonLocationPK other = (RelatePersonLocationPK) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
