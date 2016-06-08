package mobilehealth.core.domain.phr.healthhistory;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import mobilehealth.core.domain.Person;

@Entity
@Table(schema = "phr")
public class LabTest {

	@Id
	@SequenceGenerator(name = "lab_test_seq", sequenceName = "lab_test_seq", schema="phr")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lab_test_seq")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;

	private String labName;
	
	@Type(type = "timestamp")
	private DateTime date;

	private String observation;

	@OneToMany(mappedBy = "labTest", targetEntity = LabTestResult.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LabTestResult> results;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<LabTestResult> getResults() {
		return results;
	}

	public void setResults(List<LabTestResult> results) {
		this.results = results;
	}

}
