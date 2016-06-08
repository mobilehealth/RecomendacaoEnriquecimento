import mobilehealth.core.dao.PersonDAO;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;


public class Teste {
	public static void main(String[] args) {
		PersonDAO dao = new PersonDAO();
		
		Person p1 = new Person();
		p1.setNameFirst("teste3");
		
		Content content = new Content();
		content.setUrlOnline("http://2");
		
		RelatePersonContent rpc = new RelatePersonContent();
		
		rpc.setContent(content);
		rpc.setPerson(p1);
		
		p1.getRelatesPersonContet().add(rpc);
		
		dao.update(p1);
	}
}
