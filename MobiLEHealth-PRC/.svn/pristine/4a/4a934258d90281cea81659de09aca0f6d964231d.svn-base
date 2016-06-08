package mobilehealth.prc.recommendation.data;

import javax.swing.JOptionPane;

import com.sun.codemodel.JOp;

public class Generate {

	/**
	 * @param args
	 */
	
	int idPerson;
	
	public static void main(String[] args) {
		
		(new Generate()).run();

	}
/*	
	public Generate(int id){
		this.idPerson = id;
		
		run();
	}
	
*/

	public void run() {

		System.out.println("\nConstrução de Persons...");
		//(new GeneratePerson()).run();  // depois comentar 
		System.out.println("\nConstrução de Contents...");
		//(new GenerateContent()).run();
		System.out.println("\nConstrução de Locations...");
		(new GenerateLocation()).run();
		System.out.println("\nConstrução de Tags...");
		//(new GenerateTag()).run();
		
		System.out.println("\nConstrução de Relações...");
		
		(new GenerateRelates()).run();
		
		//GenerateRelates gr = new GenerateRelates(idPerson);
		
			
	}

}
