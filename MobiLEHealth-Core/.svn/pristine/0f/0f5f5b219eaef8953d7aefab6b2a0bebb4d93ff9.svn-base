package mobilehealth.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "public")
public class Recommendation {
	
	@Id
	@SequenceGenerator(name = "recommendation_seq", sequenceName = "recommendation_seq", schema = "public")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recommendation_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "id_content")
	private Content content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	private boolean visited;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Date getDate() {
		return dateCreation;
	}

	public void setDate(Date date) {
		this.dateCreation = date;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
