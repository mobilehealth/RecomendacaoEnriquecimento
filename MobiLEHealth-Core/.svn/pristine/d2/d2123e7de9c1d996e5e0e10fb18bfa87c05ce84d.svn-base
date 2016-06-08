package mobilehealth.core.domain;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Content;

@Entity
@Table(name="relate_content_location", schema="public")
@IdClass(RelateContentLocationPK.class)
@AssociationOverrides({
	@AssociationOverride(name = "content", joinColumns = @JoinColumn(name = "id_content")),
	@AssociationOverride(name = "location",  joinColumns = @JoinColumn(name = "id_location")) })
public class RelateContentLocation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_INDEFINITE = 0;	// 
	
	public static final int STATUS_CONTENT_FAVORITED = 1;	// Conteúdo visualizado nesse local
	public static final int STATUS_CONTENT_VISUALIZED = 2;	// Conteúdo visualizado nesse local
	public static final int STATUS_CONTENT_REJECTED = 3;	// Conteúdo rejeitado nesse local
	public static final int STATUS_CONTENT_VALUED = 4;		// Conteúdo avaliado nesse local
	
	public static final int STATUS_ABOUT = 5;		// Conteúdo é sobre o local
	public static final int STATUS_CITED = 6;		// Conteúdo cita o local
	public static final int STATUS_RELATED = 7;		// Conteúdo é sobre assunto relacionado ao local

	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_content")
	private Content content;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_location")
	private Location location;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_relation")
	private Calendar dateRelation;

	// Motivo da criacao dessa relacao
	@Column(name = "status")
	private int status;

	//------------------------------------------------------------------------------------
	// Getters and Setters
	//------------------------------------------------------------------------------------
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Calendar getDateRelation() {
		return dateRelation;
	}

	public void setDateRelation(Calendar dateRelation) {
		this.dateRelation = dateRelation;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusAsString () 
	{
		String str = "";
		
		switch (status) 
		{
			case STATUS_INDEFINITE : str = "Relação não definida"; break;
			case STATUS_CONTENT_VISUALIZED : str = "Conteudo visualisado nesse local"; break;
			case STATUS_CONTENT_REJECTED : str = "Conteudo rejeitado nesse local"; break;
			case STATUS_CONTENT_VALUED : str = "Conteudo avaliado nesse local"; break;
			case STATUS_ABOUT : str = "Conteudo refere-se diretamente a este local"; break;
			case STATUS_CITED : str = "Conteudo cita este local"; break;
			case STATUS_RELATED : str = "Conteudo é relacionado indiretamente a este local"; break;
			
			default: str = "Indefinido"; break;
			
		}
		
		return str;
	}
	
	
}
