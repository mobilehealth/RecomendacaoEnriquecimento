package mobilehealth.prc.recommendation.data;

import java.io.Serializable;


public class TitleDescriptionUrl implements Serializable {

	private static final long serialVersionUID = 3737375833725727911L;
	private Integer id;
	private Integer dominio;
	private String description;
	private String title;
	private String urlonline;
	
	public TitleDescriptionUrl(Integer id, Integer dominio, String description, String title, String urlonline) {
		this.id = id;
		this.setDominio(dominio);
		this.description = description;
		this.title = title;
		this.urlonline = urlonline;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrlOnline() {
		return urlonline;
	}
	public void setUrlOnline(String urlonline) {
		this.urlonline = urlonline;
	}
	
	@Override
	public String toString() {
		return "TitleDescriptionUrl [id=" + id + ", description=" + description + ", title=" + title + ", urlonline=" + urlonline + "]";
	}

	public Integer getDominio() {
		return dominio;
	}

	public void setDominio(Integer dominio) {
		this.dominio = dominio;
	}
	
}
