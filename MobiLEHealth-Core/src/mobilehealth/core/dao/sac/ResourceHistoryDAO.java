package mobilehealth.core.dao.sac;

import java.util.HashMap;
import java.util.Map;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.sac.ResourceHistory;

public class ResourceHistoryDAO  extends GenericDAO<ResourceHistory>{

	public ResourceHistoryDAO() { 
		super(ResourceHistory.class);
	}

	public ResourceHistory findLastHistory(String schema, String table, String fieldName, int fieldValue) {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("schema", schema);
		parameters.put("table", table);
		parameters.put("fieldName", fieldName);
		parameters.put("fieldValue", fieldValue);

		return super.findOneResult("LastHistory", parameters);
	}

}
