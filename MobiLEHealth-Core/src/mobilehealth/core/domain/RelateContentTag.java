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

import mobilehealth.core.domain.Content;


@Entity
@Table(name="relate_content_tag",schema="public")
@IdClass(RelateContentTagPK.class)
@AssociationOverrides({
	@AssociationOverride(name = "content",   joinColumns = @JoinColumn(name = "id_content")),
	@AssociationOverride(name = "tag",  joinColumns = @JoinColumn(name = "id_tag")) })
public class RelateContentTag implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_content")
	private Content content;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_tag")
	private Tag tag;	
	
	// ocorrencias dessa tag em content
	@Column(name = "count_rel")
	private int countRel = 0;

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public Tag getTag() {
		return tag;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
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

