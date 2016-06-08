package mobilehealth.client_desktop.simulation.data;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonPerson;

public class GenerateRelPersonPerson {

	//private static ManagerServer managerServer = ManagerServer.getInstance();
	IUbi managerServer = FacadeLocal.getInstance();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		(new GenerateRelPersonPerson()).run();
		//(new GenerateRelPersonPerson()).executeCommons();
	}

	public void run() {

		// Obter todas as pessoas
		List<Person> people = managerServer.getAllPerson();
		Person friendship = null;
		Calendar relationshipDate = null;
		Random random = new Random();

		for (Person person : people) {

			// Percorrer uma a uma e gerar 20 relacionamentos
			for (int i = 0; i < 20; i++) {

				while (true) {

					// aleatoriamente
					int index = random.nextInt(people.size());

					/*
					 * Verifica se o sorteado não é a própria pessoa e se não
					 * existe relacionamento entre as partes
					 */
					if (person.getId() != people.get(index).getId()
							&& !alreadyAssociated(people.get(index),
									person.getListRelatePersonPerson())) {

						friendship = people.get(index);
						break;

					}

				}

				/*
				 * A cada amigo gerar uma data aleatoria de quando se
				 * conheceram, não pode-se menor do que data de nascimento
				 */
				relationshipDate = generateRelationshipDate(person, friendship);
				
				
				RelatePersonPerson relatePerson = new RelatePersonPerson();
				relatePerson.setPerson1(person);
				relatePerson.setPerson2(friendship);
				relatePerson.setStatus(RelatePersonPerson.STATUS_FRIEND);
				//relatePerson.setCommomPersons(calculateCommomFriends(person, friendship)); // TODO Pendencia: criar algoritmo para contar amigos em comum
				relatePerson.setCommomPersons(0); // TODO Pendencia: criar algoritmo para contar amigos em comum
				relatePerson.setDateRelation(relationshipDate);
				relatePerson.setAffinityRate(random.nextFloat()*10); // TODO Pendencia: criar algoritmo para calcular isso
				person.getListRelatePersonPerson().add(relatePerson);
				managerServer.editPerson(person);
				
				RelatePersonPerson relateFriendship = new RelatePersonPerson();
				relateFriendship.setPerson1(friendship);
				relateFriendship.setPerson2(person);
				relateFriendship.setStatus(RelatePersonPerson.STATUS_FRIEND);
				//relateFriendship.setCommomPersons(calculateCommomFriends(friendship, person)); // TODO Pendencia: criar algoritmo para contar amigos em comum
				relateFriendship.setCommomPersons(0); // TODO Pendencia: criar algoritmo para contar amigos em comum
				relateFriendship.setDateRelation(relationshipDate);
				relateFriendship.setAffinityRate(random.nextFloat()*10); // TODO Pendencia: criar algoritmo para calcular isso
				friendship.getListRelatePersonPerson().add(relateFriendship);
				managerServer.editPerson(friendship);

			}
			
			System.out.println("update person " + person.getId());
		}
		
		executeCommons();
	}
	
	private void executeCommons() {
		//Na teoria nao precisa desse comando, mas para garantir executei-o novamente
		List<Person> people = managerServer.getAllPerson();
		
		//Percorre todos os relacionamentos de todas as pessoas para calcular os amigos em comum.
		for (Person person : people) {
			
			for(RelatePersonPerson rpp : person.getListRelatePersonPerson()) {
				rpp.setCommomPersons(calculateCommomFriends(person, rpp.getPerson2()));
			}
			
			managerServer.editPerson(person);
			
			System.out.println("update commom friends " + person.getId());
		}
		
	}

	private int calculateCommomFriends(Person person1, Person person2) {
		
		List<RelatePersonPerson> relationsP1 = person1.getListRelatePersonPerson();
		List<RelatePersonPerson> relationsP2 = person2.getListRelatePersonPerson();
		
		int countCommomFriedns = 0;
		
		for(RelatePersonPerson rpp1 : relationsP1) {
			for(RelatePersonPerson rpp2 : relationsP2) {
				if(rpp1.getPerson2().getId() == rpp2.getPerson2().getId()) {
					countCommomFriedns++;
				}
			}
		}
		
		return countCommomFriedns;
	}

	private Calendar generateRelationshipDate(Person person1, Person person2) {

		Random random = new Random();

		Calendar currentDate = Calendar.getInstance();
		Calendar dateBirthP1 = person1.getDateBirth();
		Calendar dateBirthP2 = person2.getDateBirth();
		Calendar dateAux = Calendar.getInstance();

		// Definição do ano
		int maxYear = currentDate.get(Calendar.YEAR);
		int minYear;
		int year;

		if (dateBirthP1.get(Calendar.YEAR) >= dateBirthP2.get(Calendar.YEAR)) {
			minYear = dateBirthP1.get(Calendar.YEAR);
		} else {
			minYear = dateBirthP2.get(Calendar.YEAR);
		}

		if (minYear == maxYear) {
			year = maxYear;
		} else {
			// Sortea um ano entre o min e o max
			year = random.nextInt(maxYear - minYear) + minYear;
		}

		// definiação do mes
		int maxMonth = 12; // Dezembro
		int minMonth = 1; // Janeiro
		int month;

		// Se for o max year, o mes não pode ser maior do que o atual
		if (year == maxYear) {
			maxMonth = currentDate.get(Calendar.MONTH);
			minMonth = 1; // janeiro

			// Se for o min year, o mes não pode ser menor do que o mes de
			// nascimento
		} else if (year == minYear) {
			maxMonth = 12; // dezembro
			// Verifica de qual é o ano limite (menor) do person 1 ou 2
			if (year == dateBirthP1.get(Calendar.YEAR)) {
				minMonth = dateBirthP1.get(Calendar.MONTH);
			} else {
				minMonth = dateBirthP2.get(Calendar.MONTH);
			}
		}

		month = random.nextInt(maxMonth - minMonth) + minMonth;

		// definição do dia
		int maxDay;
		int minDay = 1;
		int day;

		// Obtem a maior data para o mês sorteado
		dateAux.set(year, month, minDay);
		maxDay = dateAux.getActualMaximum(Calendar.DATE);

		// Se for o ano atual e o mes atual, o dia não pode ser maior do que o
		// atual
		if (year == maxYear && month == maxMonth) {
			maxDay = currentDate.get(Calendar.DATE);

			// Se for o menor ano e menor mes, o dia não pode ser menor do que o
			// menor dia
		} else if (year == minYear && month == minMonth) {

			// Verifica de qual é o ano limite (menor) do person 1 ou 2
			if (year == dateBirthP1.get(Calendar.YEAR)) {
				minDay = dateBirthP1.get(Calendar.DATE);
			} else {
				minDay = dateBirthP2.get(Calendar.DATE);
			}

		}

		day = random.nextInt(maxDay - minDay) + minDay;

		dateAux.set(year, month, day);

		return dateAux;
	}

	private boolean alreadyAssociated(Person person,
			List<RelatePersonPerson> listRelatePersonPerson) {

		boolean result = false;

		for (RelatePersonPerson rpp : listRelatePersonPerson) {
			if (person.getId() == rpp.getPerson2().getId()) {
				result = true;
			}
		}

		return result;
	}
}
