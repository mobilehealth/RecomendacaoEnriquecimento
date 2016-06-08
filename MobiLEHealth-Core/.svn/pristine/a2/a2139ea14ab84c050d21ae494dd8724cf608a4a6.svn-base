package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="privacy", schema="public")
public class Privacy implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final int SHOW_NO = 0;
	public static final int SHOW_YES = 1;
	
	@Id
	@SequenceGenerator(name = "privacy_id_seq", sequenceName = "privacy_id_seq", allocationSize = 1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privacy_id_seq")  
	@Column(name = "id")
	private Integer id;
	
	// Exibir local do usuário?
	@Column(name = "show_place")
	private int showPlace;	
	
	// Exibir distância do usuário?
	@Column(name = "show_distance")
	private int showDistance; 
	
	//---------------------------------------------------------------------------------------
	// Getters and Setters
	//----------------------------------------------------------------------------------------
	public int getShowPlace() {
		return showPlace;
	}
	public void setShowPlace(int showPlace) {
		this.showPlace = showPlace;
	}
	public int getShowDistance() {
		return showDistance;
	}
	public void setShowDistance(int showDistance) {
		this.showDistance = showDistance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	
}
