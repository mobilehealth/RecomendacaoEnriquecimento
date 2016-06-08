package mobilehealth.prc.recommendation;


import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.sun.codemodel.JOp;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.controller.DomainController;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Domain;
import mobilehealth.core.domain.Person;
import mobilehealth.sac.SACExternalServiceImpl;

public class SemelhancaSemantica {

	private IData iData = DataEclipseLink.getInstance();

	
	
	public float getSemelhancaSemanticaUsuarios (Person person1, Person person2) {
		
		SACExternalServiceImpl SS = new SACExternalServiceImpl();
		
		float semelhancaSemanticaUsuario =  0;
		
		List<Person> listPerson = iData.getAllPerson();
		List<Content> listContent = iData.getAllContent();
		//Person person1 = iData.getPerson(idA);
		//Person person1 = listPerson.get((int) idA);
		//IAnalyzer semantic = new Analyzer();

			//Person person2 = iData.getPerson(idB);
			//semelhancaSemantica = 40 - (Math.abs(person1.getSS1() - person2.getSS1()) + Math.abs(person1.getSS2() - person2.getSS2()) + Math.abs(person1.getSS3() - person2.getSS3())+ Math.abs(person1.getSS4() - person2.getSS4()));
			Map<String, Double> x1 = SS.getDegreeOfInterestsDomains(person1);
			Map<String, Double> x2 = SS.getDegreeOfInterestsDomains(person2);
			
			double soma = 0;
			int cont = 0;
			DomainController dc = new DomainController();
			for(Domain d : dc.getAllDomains()) {
				soma += Math.abs(x1.get(d.getDomainName()) - x2.get(d.getDomainName()));
			}
			
			/*while(soma == 0){
				try {
					
					System.out.println("Soma = " + soma);
					Thread.sleep(5000);
					
					for(Domain d : dc.getAllDomains()) {
						soma += Math.abs(x1.get(d.getDomainName()) - x2.get(d.getDomainName()));
					}
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			System.out.println("Chegou aq");
			*/

			semelhancaSemanticaUsuario = (float) (20 - soma)/2;
		
			System.out.println("\n\n ------> Retorno da semalhança semantica Usuario "+ semelhancaSemanticaUsuario +" <---------\n");
		return semelhancaSemanticaUsuario;
	}
	
	
public float getSemelhancaSemanticaUsuarioConteudo(Person person, Content content) {
		
		SACExternalServiceImpl SS = new SACExternalServiceImpl();
		
		float semelhancaSemanticaUsuarioConteudo =  0;
		
		List<Person> listPerson = iData.getAllPerson();
		List<Content> listContent = iData.getAllContent();
		//Person person1 = iData.getPerson(idA);
		//Person person1 = listPerson.get((int) idA);
		//IAnalyzer semantic = new Analyzer();

			//Person person2 = iData.getPerson(idB);
			//semelhancaSemantica = 40 - (Math.abs(person1.getSS1() - person2.getSS1()) + Math.abs(person1.getSS2() - person2.getSS2()) + Math.abs(person1.getSS3() - person2.getSS3())+ Math.abs(person1.getSS4() - person2.getSS4()));
			Map<String, Double> x1 = SS.getDegreeOfInterestsDomains(person);
			Map<String, Double> x2 = SS.getRelationshipLevelDomains(content);
			
			double soma = 0;
			
			DomainController dc = new DomainController();
			for(Domain d : dc.getAllDomains()) {
				soma += Math.abs(x1.get(d.getDomainName()) - x2.get(d.getDomainName()));
			}
			
			semelhancaSemanticaUsuarioConteudo = (float) (20 - soma)/2;
			
			
		System.out.println("\n\n ------> Retorno da semalhança semantica Conmteudo " + semelhancaSemanticaUsuarioConteudo + " <--------- \n");
			
		return semelhancaSemanticaUsuarioConteudo;
	}

	
	/*
	
	public float getSemelhancaSemantica (int tipo, long idA, long idB) {
		
		float semelhancaSemantica =  0;
		
		List<Person> listPerson = iData.getAllPerson();
		List<Content> listContent = iData.getAllContent();
		Person person1 = iData.getPerson(idA);
		//Person person1 = listPerson.get((int) idA);
		//IAnalyzer semantic = new Analyzer();
		if(tipo == 1){
<<<<<<< .mine
			Person person2 = iData.getPerson(idB);
			//semelhancaSemantica = 40 - (Math.abs(person1.getSS1() - person2.getSS1()) + Math.abs(person1.getSS2() - person2.getSS2()) + Math.abs(person1.getSS3() - person2.getSS3())+ Math.abs(person1.getSS4() - person2.getSS4()));
			semelhancaSemantica = 20 - (Math.abs(person1.getSS1() - person2.getSS1()) + Math.abs(person1.getSS2() - person2.getSS2()));
=======
			Person person2 = iData.getPerson(idB);;
			semelhancaSemantica = 40 - (Math.abs(person1.getSS1() - person2.getSS1()) + Math.abs(person1.getSS2() - person2.getSS2()));// + Math.abs(person1.getSS3() - person2.getSS3())+ Math.abs(person1.getSS4() - person2.getSS4()));
>>>>>>> .r145
		} else if (tipo == 2) {
			Content content = iData.getContent(idB);
<<<<<<< .mine
			//semelhancaSemantica = 40 - (Math.abs(person1.getSS1() - content.getSS1()) + Math.abs(person1.getSS2() - content.getSS2()) + Math.abs(person1.getSS3() - content.getSS3())+ Math.abs(person1.getSS4() - content.getSS4()));
			semelhancaSemantica = 20 - (Math.abs(person1.getSS1() - content.getSS1()) + Math.abs(person1.getSS2() - content.getSS2()));
=======
			semelhancaSemantica = 40 - (Math.abs(person1.getSS1() - content.getSS1()) + Math.abs(person1.getSS2() - content.getSS2()));// + Math.abs(person1.getSS3() - content.getSS3())+ Math.abs(person1.getSS4() - content.getSS4()));
>>>>>>> .r145
		}

		
  		//semantic.init("//Users/DaLua/SVN/ubi_server_analyzer/Ontologias");

		//float semelhancaA = semantic.getUserInterest("3006", "http://localhost/health20/Onto20#Health", false);
		//float semelhancaB = semantic.getUserInterest("3008", "http://www.semanticweb.org/jonathan/ontologies/2013/11/dataStructure#DataStructure", false);
		
		//System.out.println("Semelhança: " + 10*(1 - (semelhancaA - semelhancaB)));
		 
		
		return semelhancaSemantica;
	}
	
	*/
}
