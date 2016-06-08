package mobilehealth.sac.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mobilehealth.core.controller.DomainController;
import mobilehealth.core.domain.Domain;

public class DomainGenerator {

	public static void main(String[] args) {
		
		DomainController domainController = new DomainController();
		Map<String, String> domainsMap = new HashMap<String, String>();
		
		/* Jonathan Notebook */
		//domainsMap.put("diabetes", "D:\\dev\\ide\\eclipse\\workspaces\\mobilehealth\\MobiLEHealth-SAC\\app\\ontology\\MobiLEHealth_Diabetes.owl");
		//domainsMap.put("als", "D:\\dev\\ide\\eclipse\\workspaces\\mobilehealth\\MobiLEHealth-SAC\\app\\ontology\\MobiLEHealth_ALS.owl");
		
		/* Jonathan UFERSA */
		domainsMap.put("diabetes", "C:\\dev\\ide\\workspaces\\eclipse\\mobiLEHealth\\MobiLEHealth-SAC\\app\\ontology\\MobiLEHealth_Diabetes.owl");
		domainsMap.put("als", "C:\\dev\\ide\\workspaces\\eclipse\\mobiLEHealth\\MobiLEHealth-SAC\\app\\ontology\\MobiLEHealth_ALS.owl");
		
		for (Entry<String, String> entry : domainsMap.entrySet()) {
			Domain domain = new Domain();
			domain.setDomainName(entry.getKey());
			domain.setDomainOntologyFile(entry.getValue());
			domainController.save(domain);
		}
		
		System.exit(0);
	}
}
