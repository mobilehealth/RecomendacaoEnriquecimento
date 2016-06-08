package mobilehealth.sac.test;

import java.util.Date;

import org.joda.time.DateTime;

import mobilehealth.core.dao.PersonDAO;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.phr.healthhistory.Allergy;
import mobilehealth.core.phr.dao.AllergyDAO;
import mobilehealth.core.phr.dao.AllergyReactionDAO;
import mobilehealth.core.phr.dao.AllergyTypeDAO;

public class TesteAllergySave {
	public static void main(String[] args) {
		
		Allergy a = new Allergy();
		a.setAllergenCode("aaa");
		a.setFirstObserved(new Date());
		a.setName("aaa");
		a.setObservation("aaa");
		
		
		PersonDAO pdao = new PersonDAO();
		Person p = pdao.find(1);
		
		a.setPerson(p);
		
		AllergyReactionDAO ardao = new AllergyReactionDAO();
		a.setReaction(ardao.find(1));
		
		AllergyTypeDAO atdao = new AllergyTypeDAO();
		a.setType(atdao.find(1));
		
		AllergyDAO adao = new AllergyDAO();
		adao.save(a);
		
	}
}
