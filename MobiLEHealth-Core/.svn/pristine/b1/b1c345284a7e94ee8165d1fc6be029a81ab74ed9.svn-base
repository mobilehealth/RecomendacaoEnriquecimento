package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import mobilehealth.core.domain.Person;

@Entity
@Table(name="relate_person_tag", schema="public")
@IdClass(RelatePersonTagPK.class)
@AssociationOverrides({
	@AssociationOverride(name = "person",   joinColumns = @JoinColumn(name = "id_person")),
	@AssociationOverride(name = "tag",  joinColumns = @JoinColumn(name = "id_tag")) })
public class RelatePersonTag implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_person")
	private Person person;

	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_tag")
	private Tag tag;
		
	// ocorrencias dessa tag em person
	@Column(name = "count_rel")
	private int countRel = 0;

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public int getCountRel() {
		return countRel;
	}

	public void setCountRel(int countRel) {
		this.countRel = countRel;
	}

}
