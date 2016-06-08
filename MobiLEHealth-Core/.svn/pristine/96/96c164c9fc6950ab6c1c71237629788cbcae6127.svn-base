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


import mobilehealth.core.domain.Person;

@Entity
@Table(name="relate_person_person", schema="public")
@IdClass(RelatePersonPersonPK.class)
@AssociationOverrides({
	@AssociationOverride(name = "person1",   joinColumns = @JoinColumn(name = "id_person1")),
	@AssociationOverride(name = "person2",   joinColumns = @JoinColumn(name = "id_person2")) })

public class RelatePersonPerson implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final int STATUS_INDEFINITE = 0;	// 
	public static final int STATUS_FRIEND = 1; 		// Person1 e Person2 são amigos
	public static final int STATUS_REQUEST = 2; 	// Person1 requisitou amizade com Person2
	public static final int STATUS_REQUESTED = 3; 	// Person1 teve amizade requisitada por Person2 
	public static final int STATUS_DELETE = 4;		// Person1 deletou amizade com Person2
	public static final int STATUS_DELETED = 5;		// Person1 teve amizade deletada por Person2
	public static final int STATUS_REJECT = 6;		// Person1 rejeitou amizade com Person2
	public static final int STATUS_REJECTED = 7;	// Person1 teve amizade rejeitada por Person2
	public static final int STATUS_VIEW = 8; 		// Person1 visitou perfil de Person2
	public static final int STATUS_VIEWED = 9; 		// Person1 teve perfil visitado por Person2

	public static final int STATUS_CHATTING = 10; 	// Person1 conversa com person2 ou Person2 conversa com Person1 
	
	public static final int STATUS_COLLABS = 11;	// Person1 adciona person 2 para lista de colaboradores
	
	public static final int STATUS_RECOMMENDED = 12;	// Person1 é recomendado a person 2.

	//OBS: o status STATUS_REJECTED tambem se aplica quando a recomendacao de uma pessoa eh descartada.
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_person1")
	private Person person1;
	
	@Id
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn(name="id_person2")
	private Person person2;
	
	@Id
	@Column(name = "status")
	private int status;
	
	// TODO DUVIDA: Antonio, esse atributo eh utilizado apenas para as relacoes STATUS_SUGGESTED, OK? Nao seria melhor armazenar em STATUS_CONSTANT?
	// Taxa de afinidade entre essas duas pessoas 
	@Column(name = "affinity_rate")
	private float affinityRate;
	
	// TODO PENDENCIA: Esse dado pode ser derivado, mas, persisto para evitar delay. (apenas para STATUS_CONSTANT)
	@Column(name = "commom_persons")
	private int commomPersons;
	
	// Data de inicio da relacao criada
	@Temporal(TemporalType.DATE)
	@Column(name = "date_relation")
	private Calendar dateRelation;
	
	//------------------------------------------------------------------------
	// 1:1
	//------------------------------------------------------------------------
	// Histograma acumulado de TODAS as interacoes construtivas (view, request, chat) realizadas por essa pessoa (P1), sobre essa outra pessoa (P2). (apenas para STATUS_CONSTANT)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_times")
	@Expose(serialize=true, deserialize=false)
	private Times times;	
	
	//------------------------------------------------------------------------
	// 1:N
	//------------------------------------------------------------------------
	
	
	/*
	@ElementCollection
	@CollectionTable(name ="messages", joinColumns = {
        @JoinColumn(name="id_person1_relate_person_person", referencedColumnName="id_person1"),
        @JoinColumn(name="id_person2_relate_person_person", referencedColumnName="id_person2"),
        @JoinColumn(name="status_relate_person_person", referencedColumnName="status")})
	private List<String> listMessages = new ArrayList<String>();
	
	*/
	
	// TODO Duvida: como calcular um indice de afinidade com base nos outros atributos???
	// TODO Pendencia: estudar tecnicas de Analise de Rede Social
	
	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public float getAffinityRate() {
		return affinityRate;
	}
	public void setAffinityRate(float affinityRate) {
		this.affinityRate = affinityRate;
	}
	public int getCommomPersons() {
		return commomPersons;
	}
	public void setCommomPersons(int commomPersons) {
		this.commomPersons = commomPersons;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Person getPerson1() {
		return person1;
	}
	public void setPerson1(Person person1) {
		this.person1 = person1;
	}
	public Person getPerson2() {
		return person2;
	}
	public void setPerson2(Person person2) {
		this.person2 = person2;
	}
	public Calendar getDateRelation() {
		return dateRelation;
	}
	public void setDateRelation(Calendar dateRelation) {
		this.dateRelation = dateRelation;
	}
	
	/*
	public List<String> getListMessages() {
		return listMessages;
	}
	public void setListMessages(List<String> listMessages) {
		this.listMessages = listMessages;
	}
	
	*/
	
	public Times getTimes() {
		return times;
	}
	public void setTimes(Times times) {
		this.times = times;
	}
	
	
	public String getStatusAsString() 
	{
		String str = "";
		
		switch (status) 
		{
			case STATUS_FRIEND: str = "P1 eh Amigo de P2"; break;
			case STATUS_REQUEST: str = "P1 solicitou amizade de P2"; break;
			case STATUS_REQUESTED: str = "P1 foi solicitado para iniciar amizade com P2"; break;
			case STATUS_DELETE: str = "P1 removeu o amigo P2"; break;
			case STATUS_DELETED: str = "P1 foi removido por P2"; break;
			case STATUS_REJECT: str = "P1 rejeitou solicitacao de amizade de P2"; break;
			case STATUS_REJECTED: str = "P1 foi rejeitado ao enviar solicitacao de amizade para P2"; break;
			case STATUS_VIEWED: str = "P1 foi visualizado para P2"; break;
			default: str = "Indefinido"; break;
		}
		
		return str;
	}	

	@Override
	public String toString() {
		return "RelatePersonPerson [person1=" + person1.getId() + ", person2=" + person2.getId() + ", status=" + status + "]";
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person1 == null) ? 0 : person1.hashCode());
		result = prime * result + ((person2 == null) ? 0 : person2.hashCode());
		result = prime * result + status;
		return result;
	}
	
	// NAO MODIFICAR!!!
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatePersonPerson other = (RelatePersonPerson) obj;
		if (person1 == null) {
			if (other.person1 != null)
				return false;
		} else if (person1.getId() != other.person1.getId())
			return false;
		if (person2 == null) {
			if (other.person2 != null)
				return false;
		} else if (person2.getId() != other.person2.getId())
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	

}
