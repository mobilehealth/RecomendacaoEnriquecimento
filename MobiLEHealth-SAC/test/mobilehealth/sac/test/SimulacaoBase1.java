package mobilehealth.sac.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Person;
import mobilehealth.sac.SACExternalService;
import mobilehealth.sac.SACExternalServiceImpl;
import mobilehealth.sac.augmentation.SemanticAugmentation;
import mobilehealth.sac.ontology.Access;
import mobilehealth.sac.ontology.Resource;
import mobilehealth.sac.ontology.User;
import mobilehealth.sac.util.DownloadURL;

public class SimulacaoBase1 {

	private SemanticAugmentation semanticAugmentation;

	public static void main(String[] args) {
		SimulacaoBase1 simulacao = new SimulacaoBase1();

		// simulacao.executeContent();
		// simulacao.executeAcesses();
		//simulacao.checkContentIndexes();
		simulacao.checkUserIndexes();
		//simulacao.specifyAccess();
		
		System.exit(0);
	}

	private void checkUserIndexes() {
		
		List<ResultS1> results = new ArrayList<ResultS1>();
		SACExternalService sacService = new SACExternalServiceImpl();
		
		for(int i = 0; i < 9; i++) {
			
			Person person = new Person();
			person.setId(i+1);
			
			System.out.println("Results for user " + person.getId());
			for (Entry<String, Double> entry : sacService.getDegreeOfInterestsDomains(person).entrySet()) {
				ResultS1 r = new ResultS1();
				r.setId(person.getId());
				r.setDomain(entry.getKey());
				r.setIndex(entry.getValue());
				results.add(r);
				
				System.out.println(person.getId() + ": " + entry.getKey() + " - " + entry.getValue());
			}

		}
		
		for(ResultS1 r : results) {
			System.out.println("- " + r.getId() + " - " + r.getDomain() + " = " + r.getIndex());
		}
		
	}

	public SimulacaoBase1() {
		semanticAugmentation = new SemanticAugmentation();
	}

	public void checkContentIndexes() {
		
		List<ResultS1> results = new ArrayList<ResultS1>();
		SACExternalService sacService = new SACExternalServiceImpl();
		
		for (int i = 0; i < 9; i++) {
			Content content = new Content();
			content.setId(i+1);
			
			System.out.println("Result from Resource: " + content.getId());
			for (Entry<String, Double> entry : sacService.getRelationshipLevelDomains(content).entrySet()) {
				ResultS1 r = new ResultS1();
				r.setId(content.getId());
				r.setDomain(entry.getKey());
				r.setIndex(entry.getValue());
				results.add(r);
			}
		}
		
		for(ResultS1 r : results) {
			System.out.println("- " + r.getId() + " - " + r.getDomain() + " = " + r.getIndex());
		}
	}

	private Map<Integer, String> getContents() {
		Map<Integer, String> contents = new HashMap<Integer, String>();

		/* Diabetes */
		contents.put(1, "http://www.minhavida.com.br/saude/temas/diabetes");
		contents.put(2, "http://www.diabetes.org/diabetes-basics/symptoms/");
		contents.put(3, "http://en.wikipedia.org/wiki/Diabetes_mellitus");

		/* als */
		contents.put(4, "http://pt.wikipedia.org/wiki/Esclerose_lateral_amiotr%C3%B3fica");
		contents.put(5, "http://www.alsa.org/about-als/what-is-als.html");
		contents.put(6, "http://en.wikipedia.org/wiki/Amyotrophic_lateral_sclerosis");

		/* others */
		contents.put(7, "http://g1.globo.com/politica/operacao-lava-jato/noticia/2014/11/juiz-determina-que-paulo-roberto-costa-va-cpi-no-dia-2-de-dezembro.html");
		contents.put(8, "http://www.nytimes.com/2014/11/23/business/international/at-spains-door-a-welcome-mat-for-entrepreneurs-.html?ref=international&_r=0");
		contents.put(9, "http://well.blogs.nytimes.com/2014/11/20/living-with-cancer-gravy-days/?ref=health");

		return contents;
	}

	public void executeContent() {
		Map<Integer, String> x = getContents();

		for (Entry<Integer, String> y : x.entrySet()) {
			String text = DownloadURL.download(y.getValue());

			Resource resource = new Resource();
			resource.setSchema("public");
			resource.setTableName("content");
			resource.setFieldName("id");
			resource.setFieldValue(y.getKey());
			resource.setDateProcess(DateTime.now());
			resource.setText(text);

			if (!resource.getText().isEmpty()) {
				semanticAugmentation.execute(resource);
			}
		}
	}

	public void executeAcesses() {
		int[][] x = getAccesses();

		for (int i = 0; i < 30; i++) {

			Person person = new Person();
			person.setId(x[i][0]);

			User user = new User();
			user.setPerson(person);

			Resource resource = new Resource();
			resource.setSchema("public");
			resource.setTableName("content");
			resource.setFieldName("id");
			resource.setFieldValue(x[i][1]);

			Access access = new Access();
			access.setUser(user);
			access.setResource(resource);
			access.setDateAccess(DateTime.now());
			System.out.println(x[i][0] + " - " + x[i][1]);
			semanticAugmentation.execute(access);

		}
	}

	private int[][] getAccesses() {

		int[][] accesses = new int[30][2];

		/* Usuário 1 */
		accesses[0][0] = 1;
		accesses[0][1] = 1;

		accesses[1][0] = 1;
		accesses[1][1] = 2;

		accesses[2][0] = 1;
		accesses[2][1] = 3;

		accesses[3][0] = 1;
		accesses[3][1] = 4;

		accesses[4][0] = 1;
		accesses[4][1] = 7;

		/* Usuário 2 */
		accesses[5][0] = 2;
		accesses[5][1] = 1;

		accesses[6][0] = 2;
		accesses[6][1] = 4;

		accesses[7][0] = 2;
		accesses[7][1] = 5;

		accesses[8][0] = 2;
		accesses[8][1] = 6;

		accesses[9][0] = 2;
		accesses[9][1] = 7;

		/* Usuário 3 */
		accesses[10][0] = 3;
		accesses[10][1] = 1;

		accesses[11][0] = 3;
		accesses[11][1] = 4;

		accesses[12][0] = 3;
		accesses[12][1] = 7;

		accesses[13][0] = 3;
		accesses[13][1] = 8;

		accesses[14][0] = 3;
		accesses[14][1] = 9;

		/* Usuário 4 */
		accesses[15][0] = 4;
		accesses[15][1] = 1;

		accesses[16][0] = 4;
		accesses[16][1] = 2;

		accesses[17][0] = 4;
		accesses[17][1] = 3;

		/* Usuário 5 */
		accesses[18][0] = 5;
		accesses[18][1] = 4;

		accesses[19][0] = 5;
		accesses[19][1] = 5;

		accesses[20][0] = 5;
		accesses[20][1] = 6;

		/* Usuário 6 */
		accesses[21][0] = 6;
		accesses[21][1] = 7;

		accesses[22][0] = 6;
		accesses[22][1] = 8;

		accesses[23][0] = 6;
		accesses[23][1] = 9;

		/* Usuário 7 */
		accesses[24][0] = 7;
		accesses[24][1] = 1;

		accesses[25][0] = 7;
		accesses[25][1] = 2;

		accesses[26][0] = 7;
		accesses[26][1] = 3;

		accesses[27][0] = 7;
		accesses[27][1] = 7;

		/* Usuário 8 */
		accesses[28][0] = 8;
		accesses[28][1] = 3;

		/* Usuário 9 */
		accesses[29][0] = 9;
		accesses[29][1] = 6;

		return accesses;
	}
	
	private void specifyAccess() {
		Person person = new Person();
		person.setId(9);

		User user = new User();
		user.setPerson(person);

		Resource resource = new Resource();
		resource.setSchema("public");
		resource.setTableName("content");
		resource.setFieldName("id");
		resource.setFieldValue(6);

		Access access = new Access();
		access.setUser(user);
		access.setResource(resource);
		access.setDateAccess(DateTime.now());
		
		semanticAugmentation.execute(access);
	}
}
