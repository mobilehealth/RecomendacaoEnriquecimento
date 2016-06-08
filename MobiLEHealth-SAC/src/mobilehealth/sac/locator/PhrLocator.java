package mobilehealth.sac.locator;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import mobilehealth.core.dao.sac.ResourceHistoryDAO;
import mobilehealth.core.domain.phr.healthhistory.Allergy;
import mobilehealth.core.domain.phr.healthhistory.Condition;
import mobilehealth.core.domain.phr.healthhistory.Immunization;
import mobilehealth.core.domain.phr.healthhistory.LabTest;
import mobilehealth.core.domain.phr.healthhistory.LabTestResult;
import mobilehealth.core.domain.phr.healthhistory.Medication;
import mobilehealth.core.domain.phr.healthhistory.Procedure;
import mobilehealth.core.domain.phr.measurement.BloodGlucose;
import mobilehealth.core.domain.phr.measurement.BloodPressure;
import mobilehealth.core.domain.phr.measurement.Cholesterol;
import mobilehealth.core.domain.phr.measurement.PeakFlow;
import mobilehealth.core.domain.sac.ResourceHistory;
import mobilehealth.core.phr.dao.AllergyDAO;
import mobilehealth.core.phr.dao.BloodGlucoseDAO;
import mobilehealth.core.phr.dao.BloodPressureDAO;
import mobilehealth.core.phr.dao.CholesterolDAO;
import mobilehealth.core.phr.dao.ConditionDAO;
import mobilehealth.core.phr.dao.ImmunizationDAO;
import mobilehealth.core.phr.dao.LabTestDAO;
import mobilehealth.core.phr.dao.MedicationDAO;
import mobilehealth.core.phr.dao.PeakFlowDAO;
import mobilehealth.core.phr.dao.ProcedureDAO;
import mobilehealth.sac.augmentation.SemanticAugmentation;
import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.util.Parameters;

/**
 * This agent will scan MobiLEHealth database and get all contents that need to go through a
 * augmentation semantic process. This should be occur in a cycles time intervals. Should also
 * consider the historic of processing of the content.
 * 
 * @author Jonathan Darlan
 * @date 04/10/2014
 * 
 */
public class PhrLocator extends Thread {

	private static final int TYPE_HISTORY = 1;

	private static final int TYPE_MEASUREMENT = 2;

	private boolean isAlive;

	private SemanticAugmentation semanticAugmentation;

	public PhrLocator() {
		isAlive = true;
	}

	public void run() {

		semanticAugmentation = new SemanticAugmentation();

		try {
			while (isAlive) {
				execute(1);
				//execute(2);

				// 5 minutes
				sleep(300000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		semanticAugmentation.finalize();

	}

	public void terminate() {
		isAlive = false;
	}

	public void execute(int type) {

		List<Access> accesses = new ArrayList<Access>();

		switch (type) {
		case TYPE_HISTORY:

			accesses.addAll(getAllergyResources());
			accesses.addAll(getConditionResources());
			accesses.addAll(getImmunizationResources());
			accesses.addAll(getLabTestResources());
			accesses.addAll(getMedicationResources());
			accesses.addAll(getProcedureResources());

			for (Access access : accesses) {

				if (!access.getResource().getText().isEmpty()) {
					semanticAugmentation.execute(access.getResource());
					semanticAugmentation.execute(access);
				}

			}

			break;

		case TYPE_MEASUREMENT:

			accesses.addAll(getBloodGlucoseMeasurement());
			accesses.addAll(getBloodPressureMeasurement());
			accesses.addAll(getCholesterolMeasurement());
			accesses.addAll(getPeakFlowMeasurement());

			for (Access access : accesses) {
				if (!access.getResource().getText().isEmpty()) {
					semanticAugmentation.executeMeasurements(access.getResource());
					semanticAugmentation.execute(access);
				}
			}

			break;
		}

	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getPeakFlowMeasurement() {
		String tableName = "peakflow";

		List<Access> accesses = new ArrayList<Access>();
		PeakFlowDAO peakFlowDAO = new PeakFlowDAO();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();

		List<PeakFlow> peakFlows = peakFlowDAO.findAll();

		for (PeakFlow peakFlow : peakFlows) {
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", peakFlow.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(peakFlow.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText("Cholesterol|pef;" + peakFlow.getPef() + "|fev1;" + peakFlow.getFev1() + "|fev6;" + peakFlow.getFev6());

				User user = new User();
				user.setPerson(peakFlow.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getCholesterolMeasurement() {
		String tableName = "cholesterol";

		List<Access> accesses = new ArrayList<Access>();
		CholesterolDAO cholesterolDAO = new CholesterolDAO();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();

		List<Cholesterol> cholesterols = cholesterolDAO.findAll();

		for (Cholesterol cholesterol : cholesterols) {
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", cholesterol.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(cholesterol.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText("Cholesterol|hdl;" + cholesterol.getHdl() + "|ldl;" + cholesterol.getLdl() + "|Total Cholesterol;" + cholesterol.getTotalCholesterol() + "|Triglycerides;" + cholesterol.getTriglycerides());

				User user = new User();
				user.setPerson(cholesterol.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getBloodPressureMeasurement() {

		String tableName = "bloodpressure";

		List<Access> accesses = new ArrayList<Access>();
		BloodPressureDAO bloodPressureDAO = new BloodPressureDAO();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();

		List<BloodPressure> bloodPressures = bloodPressureDAO.findAll();

		for (BloodPressure bloodPressure : bloodPressures) {
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", bloodPressure.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(bloodPressure.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText("Blood Pressure|diastolic;" + bloodPressure.getDiastolic() + "|systolic;" + bloodPressure.getSystolic() + "|pulse;" + bloodPressure.getPulse());

				User user = new User();
				user.setPerson(bloodPressure.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getBloodGlucoseMeasurement() {

		String tableName = "bloodglucose";

		List<Access> accesses = new ArrayList<Access>();
		BloodGlucoseDAO bloodGlucoseDAO = new BloodGlucoseDAO();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();

		List<BloodGlucose> bloodGlucoses = bloodGlucoseDAO.findAll();

		for (BloodGlucose bloodGlucose : bloodGlucoses) {
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", bloodGlucose.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(bloodGlucose.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText("Blood Glucose|" + "measure;" + bloodGlucose.getMeasure());

				User user = new User();
				user.setPerson(bloodGlucose.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;

	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getAllergyResources() {

		String tableName = "allergy";

		List<Access> accesses = new ArrayList<Access>();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		AllergyDAO allergyDAO = new AllergyDAO();

		List<Allergy> allergies = allergyDAO.findAll();

		for (Allergy allergy : allergies) {

			/*
			 * The semantic augmentation is performed if the is no history or last update is expired
			 */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", allergy.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				String text = "";
				text += allergy.getName() + "\n";
				text += allergy.getAllergenCode() + "\n";
				text += allergy.getType().getDescription() + "\n";
				text += allergy.getReaction().getDescription() + "\n";
				text += allergy.getObservation() + "\n";

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(allergy.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText(text);

				User user = new User();
				user.setPerson(allergy.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getConditionResources() {

		String tableName = "condition";

		List<Access> accesses = new ArrayList<Access>();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		ConditionDAO conditionDAO = new ConditionDAO();

		List<Condition> conditions = conditionDAO.findAll();

		for (Condition condition : conditions) {

			/*
			 * The semantic augmentation is performed if the is no history or last update is expired
			 */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", condition.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				String text = "";
				text += condition.getName() + "\n";
				text += condition.getStatus() + "\n";
				text += condition.getObservation() + "\n";

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(condition.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText(text);

				User user = new User();
				user.setPerson(condition.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);

			}
		}

		return accesses;
	}

	/**
	 * 
	 * @return
	 */
	private List<Access> getImmunizationResources() {

		String tableName = "immunization";

		List<Access> accesses = new ArrayList<Access>();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		ImmunizationDAO immunizationDAO = new ImmunizationDAO();

		List<Immunization> immunizations = immunizationDAO.findAll();

		for (Immunization immunization : immunizations) {

			/*
			 * The semantic augmentation is performed if the is no history or last update is expired
			 */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", immunization.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				String text = "";
				text += immunization.getName() + "\n";
				text += immunization.getAdverseEffect() + "\n";
				text += immunization.getObservation() + "\n";

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(immunization.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText(text);

				User user = new User();
				user.setPerson(immunization.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	private List<Access> getLabTestResources() {

		String tableName = "labtest";

		List<Access> accesses = new ArrayList<Access>();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		LabTestDAO labTestDAO = new LabTestDAO();

		List<LabTest> labTests = labTestDAO.findAll();

		for (LabTest labTest : labTests) {

			/*
			 * The semantic augmentation is performed if the is no history or last update is expired
			 */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", labTest.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				String text = "";
				text += labTest.getLabName() + "\n";
				text += labTest.getObservation() + "\n";

				for (LabTestResult labTestResult : labTest.getResults()) {
					text += labTestResult.getTestName() + labTestResult.getResultUnit() + " = ";
					text += labTestResult.getResultValue() + "\n";
					text += labTestResult.getFlag().getDescription() + "\n";
					text += labTestResult.getObservation() + "\n";
				}

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(labTest.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText(text);

				User user = new User();
				user.setPerson(labTest.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	private List<Access> getMedicationResources() {

		String tableName = "medication";

		List<Access> accesses = new ArrayList<Access>();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		MedicationDAO medicationDAO = new MedicationDAO();

		List<Medication> medications = medicationDAO.findAll();

		for (Medication medication : medications) {

			/*
			 * The semantic augmentation is performed if the is no history or last update is expired
			 */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", medication.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				String text = "";
				text += medication.getName() + "\n";
				// text += medication.getStrength() + " = " + medication.getStrengthUnit() + "\n";
				// text += medication.getDosage() + " = " + medication.getDosageUnit() + "\n";
				text += medication.getHowTaken() + "\n";
				text += medication.getReasonTaking() + "\n";
				text += medication.getInstruction() + "\n";

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(medication.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText(text);

				User user = new User();
				user.setPerson(medication.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

	private List<Access> getProcedureResources() {

		String tableName = "procedure";

		List<Access> accesses = new ArrayList<Access>();
		ResourceHistoryDAO resourceHistoryDAO = new ResourceHistoryDAO();
		ProcedureDAO procedureDAO = new ProcedureDAO();

		List<Procedure> procedures = procedureDAO.findAll();

		for (Procedure procedure : procedures) {

			/*
			 * The semantic augmentation is performed if the is no history or last update is expired
			 */
			ResourceHistory resourceHistory = resourceHistoryDAO.findLastHistory("prh", tableName, "id", procedure.getId());

			if (resourceHistory == null || Days.daysBetween(new DateTime(resourceHistory.getRegistrationDate()), DateTime.now()).getDays() > Parameters.DAYS_FOR_REPROCESSING) {

				String text = "";
				text += procedure.getName() + "\n";
				text += procedure.getLocationBody() + "\n";
				text += procedure.getObservation() + "\n";

				/* Create resource to send processing */
				Resource resource = new Resource();
				resource.setSchema("prh");
				resource.setTableName(tableName);
				resource.setFieldName("id");
				resource.setFieldValue(procedure.getId());
				resource.setDateProcess(DateTime.now());
				resource.setText(text);

				User user = new User();
				user.setPerson(procedure.getPerson());

				Access access = new Access();
				access.setUser(user);
				access.setResource(resource);
				access.setDateAccess(DateTime.now());

				accesses.add(access);
			}
		}

		return accesses;
	}

}
