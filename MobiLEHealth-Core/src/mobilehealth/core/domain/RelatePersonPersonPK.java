package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RelatePersonPersonPK implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_person1")
	private Integer person1;
	
	@Id
	@Column(name="id_person2")
	private Integer person2;

	@Id
	@Column(name = "status")
	private int status;
	
	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public int getPerson1() {
		return person1;
	}

	public void setPerson1(int person1) {
		this.person1 = person1;
	}

	public int getPerson2() {
		return person2;
	}

	public void setPerson2(int person2) {
		this.person2 = person2;
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
		result = prime * result + ((person1 == null) ? 0 : person1.hashCode());
		result = prime * result + ((person2 == null) ? 0 : person2.hashCode());
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
		RelatePersonPersonPK other = (RelatePersonPersonPK) obj;
		if (person1 == null) {
			if (other.person1 != null)
				return false;
		} else if (!person1.equals(other.person1))
			return false;
		if (person2 == null) {
			if (other.person2 != null)
				return false;
		} else if (!person2.equals(other.person2))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
