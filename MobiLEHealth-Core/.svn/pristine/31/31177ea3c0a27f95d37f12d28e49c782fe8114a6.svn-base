package mobilehealth.prc.recommendation.data;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;

public class GenerateTag {
	
	//private static ManagerServer managerServer = ManagerServer.getInstance();
	IUbi managerServer = FacadeLocal.getInstance();

	public static void main(String[] args) {
		(new GenerateTag()).run();
				
	}
			
	public void run() {
		
		List<String> tags = new ArrayList<String>();
		System.out.println("\nConstrução de Tags:");

		// Tags de Diabetes
		tags.add("diabetes");
		tags.add("médico");
		tags.add("saúde");
		tags.add("hospital");
		tags.add("farmácia");
		tags.add("sangue");
		tags.add("insulina");
		tags.add("injeção");
		tags.add("exames");
		tags.add("doenças");
		tags.add("corpo");
		tags.add("físico");
		tags.add("sintomas");
		tags.add("tratamento");
		tags.add("clínica");
		tags.add("colesterol");
		tags.add("hipertenção");
		tags.add("obesidade");
		tags.add("seringa");
		tags.add("veias");	
		
		// Tags de Esclerose Lateral Amiotrófica (ELA)
		tags.add("esclerose");
		tags.add("músculos");
		tags.add("células");
		tags.add("amiotrófica");
		tags.add("cãibras");
		tags.add("tremores");
		tags.add("espasmos");
		tags.add("coluna");
		tags.add("neurológico");
		tags.add("paralisia");
		tags.add("medula");
		tags.add("degeneração");
		tags.add("atrofia");
		tags.add("fonoaudiólogos");
		tags.add("neurônio");
		tags.add("disartria");
		tags.add("fisioterapeutas");
		tags.add("disfagia");
		tags.add("nutricionista");
		tags.add("desnutrição");

		// Tags sem semântica definidas
		tags.add("matematica");
		tags.add("geografia");
		tags.add("moda");
		tags.add("artes plásticas");
		tags.add("religião");
		tags.add("cinema");
		tags.add("roupa");
		tags.add("automóvel");
		tags.add("motor");
		tags.add("desenho");
		tags.add("fotografia");
		tags.add("borracha");
		tags.add("letras");
		tags.add("clima");
		tags.add("viagem");
		tags.add("turismo");
		tags.add("compra");
		tags.add("banco");
		tags.add("natação");
		tags.add("lazer");
/*
		// Tags de Direito
		tags.add("Direito");
		tags.add("Advogado");
		tags.add("Juiz");
		tags.add("Penal");		
		tags.add("Ação");
		tags.add("Civil");
		tags.add("Processo");
		tags.add("Crime");
		tags.add("Dolo");
		tags.add("Réu");
		tags.add("Julgamento");
		tags.add("Constituição");
		tags.add("Lei");
		tags.add("Mandato");
		tags.add("Condenado");
		tags.add("Inquerito");
		tags.add("Testemunha");
		tags.add("Acusação");
		tags.add("Defesa");
		tags.add("Promotor");
		
		// tags de Contabilidade
		tags.add("Contabilidade");
		tags.add("Contador");
		tags.add("Tributação");
		tags.add("Imposto");
		tags.add("Taxa");
		tags.add("IRRF");
		tags.add("Pasep");
		tags.add("Cofins");
		tags.add("INSS");
		tags.add("Tributarista");
		tags.add("Fiscal");
		tags.add("Nota");
		tags.add("Certidão");
		tags.add("Dinheiro");
		tags.add("Calculadora");
		tags.add("Contábil");
		tags.add("Salário");
		tags.add("Desconto");
		tags.add("Juros");
		tags.add("Controlador");
*/		


		System.out.println("\nGerados:" + tags.size()+" Tags.");
		saveServer(tags);
		System.out.println("\nSalvos:" + tags.size()+" Tags.");
		
	}

	private void saveServer(List<String> tags) {
		
		for(String tag : tags) {
			managerServer.createTags(tag);
		}
		
	}

}

