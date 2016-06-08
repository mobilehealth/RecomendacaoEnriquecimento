package mobilehealth.core.domain.phr.healthhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(schema="phr")
public class LabTestResult {

	@Id
	@SequenceGenerator(name = "lab_test_result_seq", sequenceName = "lab_test_result_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lab_test_result_seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_labtest")
	private LabTest labTest;
	
	private String testName;
	
	@Type(type = "timestamp")
	private DateTime date;
	
	private float resultValue;
	
	private String resultUnit;
	
	@ManyToOne
	@JoinColumn(name = "id_flag")
	private LabTestResultFlag flag;
	
	private String observation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LabTest getLabTest() {
		return labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public float getResultValue() {
		return resultValue;
	}

	public void setResultValue(float resultValue) {
		this.resultValue = resultValue;
	}

	public String getResultUnit() {
		return resultUnit;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public LabTestResultFlag getFlag() {
		return flag;
	}

	public void setFlag(LabTestResultFlag flag) {
		this.flag = flag;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
}
