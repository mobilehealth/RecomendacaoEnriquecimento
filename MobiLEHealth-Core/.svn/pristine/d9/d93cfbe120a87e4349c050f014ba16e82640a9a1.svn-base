package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RelateContentTagPK implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_content")
	private Integer content; // Alterado para Integer
	
	@Id
	@Column(name="id_tag")
	private Integer tag;

	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}
	
}

