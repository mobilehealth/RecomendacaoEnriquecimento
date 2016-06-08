package mobilehealth.core.phr.dao;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.phr.measurement.BloodPressure;

public class BloodPressureDAO extends GenericDAO<BloodPressure>{

	public BloodPressureDAO() {
		super(BloodPressure.class);
	}
	
	
}
