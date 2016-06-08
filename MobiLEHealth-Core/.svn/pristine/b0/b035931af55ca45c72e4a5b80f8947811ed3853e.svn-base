package mobilehealth.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;

@Entity
@Table(name="relate_person_location", schema="public")
@IdClass(RelatePersonLocationPK.class)
@AssociationOverrides({
	@AssociationOverride(name = "person",   joinColumns = @JoinColumn(name = "id_person")),
	@AssociationOverride(name = "location",  joinColumns = @JoinColumn(name = "id_location")) })
public class RelatePersonLocation implements Serializable
{
	private static final long serialVersionUID = 1L;

	// OBS: Para saber se person eh o dono, pesquisar pelo autor em Person
	public static final int STATUS_INDEFINITE = 0;			// Não foi definido
	public static final int STATUS_LOCATION_FAVORITE = 1;
	public static final int STATUS_LOCATION_PRESENCE = 2;			// Usuário esteve presente no local
	public static final int STATUS_LOCATION_VALUED = 3;				// Usuário avaliou o local
	
	public static final int STATUS_CONTENT_FAVORITE = 4;		// Conteúdo avaliado nesse local
	public static final int STATUS_CONTENT_VALUED = 5;		// Conteúdo avaliado nesse local
	public static final int STATUS_CONTENT_VISUALIZED = 6;	// Conteúdo visualizado nesse local
	public static final int STATUS_CONTENT_REJECTED = 7;	// Conteúdo rejeitado nesse local
	
	public static final int STATUS_FRIEND_START = 8;		// Amizade iniciada nesse local
	public static final int STATUS_FRIEND_REQUEST = 9;		// Amizade requisitada nesse local
	public static final int STATUS_FRIEND_REQUESTED = 10;	// Amizade requisitada nesse local
	public static final int STATUS_FRIEND_VISIT = 11;		// Amizade visitada nesse local
	public static final int STATUS_FRIEND_VISITED = 12;		// Amizade visitada nesse local
	public static final int STATUS_FRIEND_REJECT = 13;		// Amizade rejeitada nesse local
	public static final int STATUS_FRIEND_REJECTED = 14;	// Amizade rejeitada nesse local
	public static final int STATUS_FRIEND_DELETE = 15;		// Amizade excluída nesse local
	public static final int STATUS_FRIEND_DELETED = 16;		// Amizade excluída nesse local
	
	public static final int STATUS_LOCATION_SUGGESTED = 17;			// Sugerido pelo sistema e por outros usuarios
	public static final int STATUS_LOCATION_COMMENTED= 18;			// Sugerido pelo sistema e por outros usuarios

	
	public static final int FINALITY_EDUC = 11;
	public static final int FINALITY_WORK = 12;
	public static final int FINALITY_HOME = 13;
	public static final int FINALITY_FUN = 14;
	public static final int FINALITY_OTHER = 15;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_person")
	private Person person;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_location")
	private Location location;
	
	@Id
	@Column(name = "status")
	private int status;

	// Finalidade: Educação, Trabalho, moradia, lazer, outros.
	@Column(name = "finality")
	private int finality;
	
	// Avaliação pessoal de person sobre esse local
	@Column(name = "rate_person")
	private float ratePerson;	
	
	// Data de inicio da relacao criada
	@Temporal(TemporalType.DATE)
	@Column(name = "date_relation")
	private Calendar dateRelation;
	
	//------------------------------------------------------------------------
	// 1:1
	//------------------------------------------------------------------------
	// Histograma acumulado de TODAS as interacoes construtivas (create, add, view, rate, comment) realizadas por essa pessoa, sobre esse local
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_times")
	@Expose(serialize=true, deserialize=false)
	private Times times = new Times();	
	
	//-----------------------------------------------------------
	// 1:N
	//-----------------------------------------------------------
	//Lista de postagens de comentário
	
	/*
	
	@ElementCollection
	@CollectionTable(name ="comments_location", joinColumns = {
        @JoinColumn(name="id_person_relate_person_location",   referencedColumnName="id_person"),
        @JoinColumn(name="id_content_relate_person_location", referencedColumnName="id_location"),
        @JoinColumn(name="status_relate_person_location", referencedColumnName="status")})
	@Column(name="text")
	private List<String> listComments = new ArrayList<String>();
	*/
	//------------------------------------------------------------------------
	// Getters and Setters
	//------------------------------------------------------------------------
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFinality() {
		return finality;
	}

	public void setFinality(int finality) {
		this.finality = finality;
	}

	public float getRatePerson() {
		return ratePerson;
	}

	public void setRatePerson(float ratePerson) {
		this.ratePerson = ratePerson;
	}

	public Calendar getDateRelation() {
		return dateRelation;
	}

	public void setDateRelation(Calendar dateRelation) {
		this.dateRelation = dateRelation;
	}

	/*
	public List<String> getListComments() {
		return listComments;
	}

	public void setListComments(List<String> listComments) {
		this.listComments = listComments;
	}

	*/
	
	public Times getTimes() {
		return times;
	}

	public void setTimes(Times times) {
		this.times = times;
	}
	
}
