package mobilehealth.core.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mobilehealth.core.domain.phr.healthhistory.Allergy;

/**
 * 
 * @author Jonathan Darlan
 * @date 30/07/2014
 * 
 */

@Entity
@Table(schema="public", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Person {

	@Id
	@SequenceGenerator(name = "person_seq", sequenceName = "person_seq", schema="public")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
	private int id;

	/* Unique identifier. Used as User. */
	private String email;

	private String password;

	/* Status: Active or Deleted (or created, simulation) */
	private int status;
	
	@Column(name = "name_first")
	private String nameFirst;
	
	@Column(name = "name_last")
	private String nameLast;

	@Column(name = "date_birth")
	private Calendar dateBirth;

	@Column(name = "date_register")
	private Calendar dateRegister;

	private int gender;

	private String phone;

	private float income;

	private int religion;

	private int race;

	@OneToMany(mappedBy = "person", targetEntity = RelatePersonContent.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RelatePersonContent> relatesPersonContet;
	
	@OneToMany(mappedBy = "person", targetEntity = Allergy.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Allergy> allergies;
	
	/**
	 * 
	 * @return age
	 */
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		int age = calendar.get(Calendar.YEAR) - dateBirth.get(Calendar.YEAR);

		if (calendar.get(Calendar.MONTH) < dateBirth.get(Calendar.MONTH)) {
			age--;
		} else {
			if (calendar.get(Calendar.MONTH) == dateBirth.get(Calendar.MONTH)) {
				if (calendar.get(Calendar.DAY_OF_MONTH) < dateBirth.get(Calendar.DAY_OF_MONTH)) {
					age--;
				}
			}
		}
		return age;
	}

	/*
	 * Getters and Setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	public Calendar getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Calendar dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int genre) {
		this.gender = genre;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public int getReligion() {
		return religion;
	}

	public void setReligion(int religion) {
		this.religion = religion;
	}

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
		this.race = race;
	}

	public List<RelatePersonContent> getRelatesPersonContet() {
		return relatesPersonContet;
	}

	public void setRelatesPersonContet(List<RelatePersonContent> relatesPersonContet) {
		this.relatesPersonContet = relatesPersonContet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allergies == null) ? 0 : allergies.hashCode());
		result = prime * result + ((dateBirth == null) ? 0 : dateBirth.hashCode());
		result = prime * result + ((dateRegister == null) ? 0 : dateRegister.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + gender;
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(income);
		result = prime * result + ((nameFirst == null) ? 0 : nameFirst.hashCode());
		result = prime * result + ((nameLast == null) ? 0 : nameLast.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + race;
		result = prime * result + ((relatesPersonContet == null) ? 0 : relatesPersonContet.hashCode());
		result = prime * result + religion;
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
		Person other = (Person) obj;
		if (allergies == null) {
			if (other.allergies != null)
				return false;
		} else if (!allergies.equals(other.allergies))
			return false;
		if (dateBirth == null) {
			if (other.dateBirth != null)
				return false;
		} else if (!dateBirth.equals(other.dateBirth))
			return false;
		if (dateRegister == null) {
			if (other.dateRegister != null)
				return false;
		} else if (!dateRegister.equals(other.dateRegister))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(income) != Float.floatToIntBits(other.income))
			return false;
		if (nameFirst == null) {
			if (other.nameFirst != null)
				return false;
		} else if (!nameFirst.equals(other.nameFirst))
			return false;
		if (nameLast == null) {
			if (other.nameLast != null)
				return false;
		} else if (!nameLast.equals(other.nameLast))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (race != other.race)
			return false;
		if (relatesPersonContet == null) {
			if (other.relatesPersonContet != null)
				return false;
		} else if (!relatesPersonContet.equals(other.relatesPersonContet))
			return false;
		if (religion != other.religion)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
