package mobilehealth.core.phr.dao;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.phr.measurement.PeakFlow;

public class PeakFlowDAO extends GenericDAO<PeakFlow> {

	public PeakFlowDAO() {
		super(PeakFlow.class);
	}

}
