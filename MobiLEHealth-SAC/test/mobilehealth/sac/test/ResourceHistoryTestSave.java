package mobilehealth.sac.test;

import java.util.Date;

import mobilehealth.core.dao.sac.ResourceHistoryDAO;
import mobilehealth.core.domain.sac.ResourceHistory;

public class ResourceHistoryTestSave {
	
	public static void main(String[] args) {
		ResourceHistory rh =  new ResourceHistory();
		ResourceHistoryDAO rhdao = new ResourceHistoryDAO();
		
		rh.setSchema("a");
		rh.setTable("aa");
		rh.setFieldName("aaa");
		rh.setFieldValue(1);
		rh.setRegistrationDate(new Date());
		
		rhdao.save(rh);
	}
	
}
