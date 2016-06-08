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

import mobilehealth.core.domain.Location;


@Entity
@Table(name="relate_location_tag", schema="public")
@IdClass(RelateLocationTagPK.class)
//@AssociationOverrides({
	//@AssociationOverride(name = "location",   joinColumns = @JoinColumn(name = "id_location")),
	//@AssociationOverride(name = "tag",  joinColumns = @JoinColumn(name = "id_tag")) })
public class RelateLocationTag implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_location")
	private Location location;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_tag")
	private Tag tag;
	
	// ocorrencias dessa tag em location
	@Column(name = "count_rel")
	private int countRel = 0;

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getCountRel() {
		return countRel;
	}

	public void setCountRel(int countRel) {
		this.countRel = countRel;
	}
	
}


