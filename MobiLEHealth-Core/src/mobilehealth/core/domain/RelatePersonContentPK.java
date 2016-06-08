package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.type.IntegerType;

@Entity
@Table(name="relate_person_content")
@IdClass(RelatePersonContentPK.class)
//@AssociationOverrides({
	//@AssociationOverride(name = "person",   joinColumns = @JoinColumn(name = "id_person")),
	//@AssociationOverride(name = "content",  joinColumns = @JoinColumn(name = "id_content")) })
public class RelatePersonContentPK{
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="id_person")
	private Integer person;
	
	@Id
	@Column(name="id_content")
	private Integer content;

	@Id
	@Column(name = "status")
	private int status;
	// Status desse conteudo. Ele anula a necessidade de ter uma outra lista de conetúdos.
	

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public long getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public long getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
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
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		RelatePersonContentPK other = (RelatePersonContentPK) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
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
