package mobilehealth.core.phr.dao;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.phr.healthhistory.Medication;

public class MedicationDAO extends GenericDAO<Medication> {

	public MedicationDAO() {
		super(Medication.class);
	}

}
