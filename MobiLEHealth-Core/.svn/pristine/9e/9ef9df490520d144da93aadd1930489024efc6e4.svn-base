package mobilehealth.prc.controller;

import java.util.Calendar;
import java.util.List;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;
import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.dao.PersonDAO;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.File;
import mobilehealth.core.domain.Frequency;
import mobilehealth.core.domain.Learning;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.Privacy;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.core.domain.RelatePersonPersonPK;
import mobilehealth.core.domain.Scores;
import mobilehealth.core.domain.Times;

public class ManagerServerPerson 
{
	private static ManagerServerPerson instance;
	private IData iData;
	private FillOutServer fillOutServer;

	private ManagerServerPerson()
	{
		this.iData = DataEclipseLink.getInstance();
		this.fillOutServer = new FillOutServer();
	}
	
    public static ManagerServerPerson getInstance()
    {
        if (instance == null) {
            instance = new ManagerServerPerson();
        }
        return instance;
    }
	
    
	public Person getPerson(long idPerson)
	{
		return iData.getPerson(idPerson);
	}
	
	
	public Person getPerson(String email)
	{
		return iData.getPerson(email);
	}
	
	
	public List<Person> getAllPerson()
	{
		return iData.getAllPerson();
	}

	public int getCountPerson()
	{
		return iData.getAllPerson().size();
	}

	
	public List<Person> searchPerson(long idPerson, Object param, Context contexto)
	{
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
		
		// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_PERSON_SEARCH);
		
		return iData.searchPerson(param);
	}
	
	
	public boolean createPerson(Person person, Context contexto)
	{
		boolean flagOK = false;
		String email = person.getEmail();
		
		if( iData.getPerson(email) == null)
		{
			System.out.println("usuario nao existe, sera criado do zero");
			
	    	// PERSON (continua preenchendo outros atributos)
	    	person.setDateRegister(Calendar.getInstance());
	    	person.setStatus(Person.STATUS_ACTIVE);
	    	person.setFile(new File());
	    	person.setFrequency(new Frequency());
	    	person.setLearning(new Learning());
	    	person.setPrivacy(new Privacy());
	    	person.setScores(new Scores());
	    	person.setTimes(new Times());
	    	
			// CONTEXTO E SCORE (continua preenchendo outros atributos)
	 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_PERSON_CREATED_FIRST);
			
	        // insertPerson
	 		flagOK = iData.insertPerson(person);
		}
		else
		{			
			System.out.println("usuario ja existe: " + person.getEmail());
			
			Person personAntigo = iData.getPerson(email);
			
			if(personAntigo.getStatus() == Person.STATUS_DELETED)
			{
				System.out.println("Esta DELETED e sera agora restaurado");
				
		    	// PERSON (atualiza person antigo)
				personAntigo.setStatus(Person.STATUS_ACTIVE);
				personAntigo.setNameFirst(person.getNameFirst());
				personAntigo.setNameLast(person.getNameLast());
				personAntigo.setDateBirth(person.getDateBirth());
				personAntigo.setPassword(person.getPassword());
								
				// CONTEXTO E SCORE (continua preenchendo outros atributos)
				personAntigo = fillOutServer.fillContextAndScoresAndTimes(personAntigo, contexto, Context.TYPE_PERSON_CREATED_AGAIN);
						 		
		        // setPerson
		 		flagOK = iData.setPerson(personAntigo);
			}
			else
			{
				System.out.println("Esta ACTIVE e nao pode ser criado novamente");
			}
		}
				
		return flagOK;
	}
	
	public boolean createPerson(Person person)
	{
		boolean flagOK = false;
		String email = person.getEmail();
		
		if( iData.getPerson(email) == null)
		{
			//System.out.println("usuario nao existe, sera criado do zero");
			
	    	// PERSON (continua preenchendo outros atributos)
	    	person.setDateRegister(Calendar.getInstance());
	    	person.setStatus(Person.STATUS_ACTIVE);
	    	person.setFile(new File());
	    	person.setFrequency(new Frequency());
	    	person.setLearning(new Learning());	    	 
	    	person.setPrivacy(new Privacy());
	    	person.setScores(new Scores());
	    	person.setTimes(new Times());
	    	
	    	PersonDAO p = new PersonDAO();
	    	
	        // insertPerson
	    	
	 		flagOK = true;
	 		p.save(person);
	 				
	 				//iData.insertPerson(person);
		}
		else
		{			
			//System.out.println("usuario ja existe: " + person.getEmail());
			
			Person personAntigo = iData.getPerson(email);
			
			if(personAntigo.getStatus() == Person.STATUS_DELETED)
			{
				System.out.println("Esta DELETED e sera agora restaurado");
				
		    	// PERSON (atualiza person antigo)
				personAntigo.setStatus(Person.STATUS_ACTIVE);
				personAntigo.setNameFirst(person.getNameFirst());
				personAntigo.setNameLast(person.getNameLast());
				personAntigo.setDateBirth(person.getDateBirth());
				personAntigo.setPassword(person.getPassword());
								
		        // setPerson
		 		flagOK = iData.setPerson(personAntigo);
			}
			else
			{
				System.out.println("Esta ACTIVE e nao pode ser criado novamente");
			}
		}
				
		return flagOK;
	}
	
	public boolean editPerson(Person person, Context contexto) 
	{
		boolean flagOK = false;
        
		// TODO PENDENCIA: medir percentual de status preenchido.
		// TODO DUVIDA: TAG na descricao de pessoa???
		
		// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_PERSON_EDITED);
 		
        // setPerson
 		flagOK = iData.setPerson(person);

		return flagOK;
	}
	
	public boolean editPerson(Person person) 
	{
		boolean flagOK = false;
        		
        // setPerson
 		flagOK = iData.setPerson(person);

		return flagOK;
	}
	
	public boolean removePerson(long idPerson, Context contexto) 
	{
		// OBS: ao remover uma pessoa, muda-se apenas o status para STATUS_DELETED.
		// OBS: o status eh reestabelecido para STATUS_ACTIVE quando usuario tenta criar usuario com o mesmo email da conta deletada.
		
		boolean flagOK = false;
        
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado
		person.setStatus(Person.STATUS_DELETED);
		
		// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person = fillOutServer.fillContextAndScoresAndTimes(person, contexto, Context.TYPE_PERSON_DELETED);
 		
        // setPerson
 		flagOK = iData.setPerson(person);

		return flagOK;
	}
	
	
	public boolean viewPerson(long idPerson1, long idPerson2, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario logado
		Person person2 = iData.getPerson(idPerson2); // usuario visualizado
		
		// P1-P2 -------------------------------------------------------------------------------------------------------
		List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
		
		// Verifica se a relacao CONSTATNT ja existe:
		RelatePersonPersonPK rConst1 = new RelatePersonPersonPK();
		rConst1.setPerson1(person1.getId());
		rConst1.setPerson2(person2.getId());
		rConst1.setStatus(RelatePersonPerson.STATUS_INDEFINITE);
		RelatePersonPerson relateConstant1 = iData.getRelatePersonPerson(rConst1);
		
		if(relateConstant1 == null)
		{
			// Adiciona STATUS_CONSTANT
			relateConstant1 = new RelatePersonPerson();
			relateConstant1.setPerson1(person1);
			relateConstant1.setPerson2(person2);
			relateConstant1.setDateRelation(Calendar.getInstance());
			relateConstant1.setStatus(RelatePersonPerson.STATUS_INDEFINITE);
			relateConstant1.setTimes(new Times());
			relateConstant1.setTimes( fillOutServer.fillTimes( relateConstant1.getTimes() ) );
			// Adiciona Relate e atualiza Person
			listRelatePersonPerson1.add(relateConstant1);
			person1.setListRelatePersonPerson(listRelatePersonPerson1);
		}
		else
		{
			// Edita RelatePersonPerson e atualiza Person
			relateConstant1.setTimes( fillOutServer.fillTimes( relateConstant1.getTimes() ) );
			listRelatePersonPerson1.set(listRelatePersonPerson1.indexOf(relateConstant1), relateConstant1);
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
		}
		
		// Verifica se a relacao STATUS_VIEW ja existe:
		RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
		rPP1.setPerson1(person1.getId());
		rPP1.setPerson2(person2.getId());
		rPP1.setStatus(RelatePersonPerson.STATUS_VIEW);
		RelatePersonPerson relatePersonPersonBD1 = iData.getRelatePersonPerson(rPP1);
		
		if( relatePersonPersonBD1 == null ) 
		{
			// Adiciona STATUS_VIEW
			RelatePersonPerson relatePersonPersonP1P2 = new RelatePersonPerson();
			relatePersonPersonP1P2.setPerson1(person1);
			relatePersonPersonP1P2.setPerson2(person2);
			relatePersonPersonP1P2.setDateRelation(Calendar.getInstance());
			relatePersonPersonP1P2.setStatus(RelatePersonPerson.STATUS_VIEW);
			// Adiciona RelatePersonPerson e atualiza Person
			listRelatePersonPerson1.add(relatePersonPersonP1P2);
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
			iData.setPerson(person1);
		}
		
		// P2-P1 -------------------------------------------------------------------------------------------------------
		List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
				
		// Verifica se a relacao STATUS_VIEWED ja existe:
		RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
		rPP2.setPerson1(person2.getId());
		rPP2.setPerson2(person1.getId());
		rPP2.setStatus(RelatePersonPerson.STATUS_VIEWED);
		RelatePersonPerson relatePersonPersonBD2 = iData.getRelatePersonPerson(rPP2);
		
		if( relatePersonPersonBD2 == null ) 
		{
			// Adiciona STATUS_VIEWED
			RelatePersonPerson relatePersonPersonP2P1 = new RelatePersonPerson();
			relatePersonPersonP2P1.setPerson1(person2);
			relatePersonPersonP2P1.setPerson2(person1);
			relatePersonPersonP2P1.setDateRelation(Calendar.getInstance());
			relatePersonPersonP2P1.setStatus(RelatePersonPerson.STATUS_VIEWED);
			// Adiciona RelatePersonPerson e atualiza Person
			listRelatePersonPerson2.add(relatePersonPersonP2P1);
			person2.setListRelatePersonPerson( listRelatePersonPerson2 );
			iData.setPerson(person2);
		}
		
		// COMUM -------------------------------------------------------------------------------------------------------
		// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_VIEW);
 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_PERSON_VIEWED);
		
		// Atualiza Person (P1 e P2)
		flagUpdate1 = iData.setPerson(person1);
		flagUpdate2 = iData.setPerson(person2);
		
		if(flagUpdate1 == true && flagUpdate2 == true) {
			flagOK = true;
		}
		
		return flagOK;
	}
	
	
	public boolean rejectRecommPerson(long idPerson1, long idPerson2, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario logado
		Person person2 = iData.getPerson(idPerson2); // usuario recomendado (que esta sendo rejeitado)
		
		// Verifica se relacao STATUS_RECOMMENDED existe:
		RelatePersonPersonPK rPP1Sug = new RelatePersonPersonPK();
		rPP1Sug.setPerson1(person1.getId());
		rPP1Sug.setPerson2(person2.getId());
		rPP1Sug.setStatus(RelatePersonPerson.STATUS_RECOMMENDED);
		RelatePersonPerson relatePersonPerson1Sug = iData.getRelatePersonPerson(rPP1Sug);
		
		// Somente produz efeito se houver alguma recomendacao. Pois, obviamente, so se pode rejeitar recomendacao se houver recomendacao.
		if(relatePersonPerson1Sug == null)
		{
			System.out.println("Erro: Nao ha sugestao para ser rejeitada");
		}
		else
		{
			// Verifica se relacao STATUS_REJECT ja existe:
			RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
			rPP1.setPerson1(person1.getId());
			rPP1.setPerson2(person2.getId());
			rPP1.setStatus(RelatePersonPerson.STATUS_REJECT);
			RelatePersonPerson relatePersonPersonBD1 = iData.getRelatePersonPerson(rPP1);
			
			// Verifica se relacao STATUS_REJECTED ja existe:
			RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
			rPP2.setPerson1(person2.getId());
			rPP2.setPerson2(person1.getId());
			rPP2.setStatus(RelatePersonPerson.STATUS_REJECTED);
			RelatePersonPerson relatePersonPersonBD2 = iData.getRelatePersonPerson(rPP2);
			
			// Se objetos vazios, podem ser criados
			if(relatePersonPersonBD1 == null && relatePersonPersonBD2 == null)
			{
				// P1-P2 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
							
				// Deleta STATUS_SUGGESTED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) 
				{
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_RECOMMENDED && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId()) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// Adiciona STATUS_REJECT
				RelatePersonPerson relatePersonPersonP1P2 = new RelatePersonPerson();
				relatePersonPersonP1P2.setPerson1(person1);
				relatePersonPersonP1P2.setPerson2(person2);
				relatePersonPersonP1P2.setDateRelation(Calendar.getInstance());
				relatePersonPersonP1P2.setStatus(RelatePersonPerson.STATUS_REJECT);
				// Adiciona RelatePersonPerson e atualiza Person
				listRelatePersonPerson1.add(relatePersonPersonP1P2);
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// FREQUENCY (rejeicao)
				person1.setFrequency( fillOutServer.fillFrequencyNegative( person1.getFrequency() ) );
				
				// P2-P1 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
				
				// Deleta STATUS_SUGGESTED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) 
				{
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_RECOMMENDED && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person1.getId()) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Adiciona STATUS_REJECTED
				RelatePersonPerson relatePersonPersonP2P1 = new RelatePersonPerson();
				relatePersonPersonP2P1.setPerson1(person2);
				relatePersonPersonP2P1.setPerson2(person1);
				relatePersonPersonP2P1.setDateRelation(Calendar.getInstance());
				relatePersonPersonP2P1.setStatus(RelatePersonPerson.STATUS_REJECTED);
				// Adiciona RelatePersonPerson e atualiza Person
				listRelatePersonPerson2.add(relatePersonPersonP2P1);
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// COMUM -------------------------------------------------------------------------------------------------------
				// CONTEXTO E SCORE (continua preenchendo outros atributos)
		 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_FRIEND_REJECT);
		 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_PERSON_FRIEND_REJECTED);

				// Atualiza Person (P1 e P2)
				flagUpdate1 = iData.setPerson(person1);
				flagUpdate2 = iData.setPerson(person2);
			
				if(flagUpdate1 == true && flagUpdate2 == true) {
					flagOK = true;
				}
			}
			else
			{
				System.out.println("Erro: relacoes ja existem");
			}
		} // if			
		
		return flagOK;
	}
	
	
	public boolean requestFriendship(long idPerson1, long idPerson2, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario (que requisita)
		Person person2 = iData.getPerson(idPerson2); // usuario (requisitado)
		
		// Verifica se ja recebeu convite (se sim, nao pode enviar. Evita convite cruzado)
		RelatePersonPersonPK rPP1C = new RelatePersonPersonPK();
		rPP1C.setPerson1(person1.getId());
		rPP1C.setPerson2(person2.getId());
		rPP1C.setStatus(RelatePersonPerson.STATUS_REQUESTED);
		
		// Verifica se ja enviou convite (se sim, nao pode receber. Evita convite cruzado)
		RelatePersonPersonPK rPP2C = new RelatePersonPersonPK();
		rPP2C.setPerson1(person2.getId());
		rPP2C.setPerson2(person1.getId());
		rPP2C.setStatus(RelatePersonPerson.STATUS_REQUEST);
		
		// Se P1 recebeu convite, ele nao pode enviar, ou seja, so pode enviar convite se nao tiver sido convidado.
		// Quando P1 ja tem convite, ele deve responder com um accept ou reject.
		if( iData.getRelatePersonPerson(rPP1C) == null && iData.getRelatePersonPerson(rPP2C) == null ) 
		{
			// Verifica se a relacao STATUS_REQUEST ja existe:
			RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
			rPP1.setPerson1(person1.getId());
			rPP1.setPerson2(person2.getId());
			rPP1.setStatus(RelatePersonPerson.STATUS_REQUEST);
			
			// Verifica se a relacao STATUS_REQUESTED ja existe:
			RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
			rPP2.setPerson1(person2.getId());
			rPP2.setPerson2(person1.getId());
			rPP2.setStatus(RelatePersonPerson.STATUS_REQUESTED);
			
			// Se ambos estiverem vazios, pode-se criar. Mas, se a requisicao ja tiver sido feita antes, nao produz nenhum efeito novo (nem contexto)
			if(iData.getRelatePersonPerson(rPP1) == null && iData.getRelatePersonPerson(rPP2) == null)
			{
				// P1-P2 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
				
				// Verifica se a relacao CONSTATNT ja existe:
				RelatePersonPersonPK rConst1 = new RelatePersonPersonPK();
				rConst1.setPerson1(person1.getId());
				rConst1.setPerson2(person2.getId());
				rConst1.setStatus(RelatePersonPerson.STATUS_INDEFINITE);
				RelatePersonPerson relateConstant1 = iData.getRelatePersonPerson(rConst1);
				
				if(relateConstant1 == null)
				{
					// Adiciona STATUS_CONSTANT
					relateConstant1 = new RelatePersonPerson();
					relateConstant1.setPerson1(person1);
					relateConstant1.setPerson2(person2);
					relateConstant1.setDateRelation(Calendar.getInstance());
					relateConstant1.setStatus(RelatePersonPerson.STATUS_INDEFINITE);
					// Cria e incrementa Times
					relateConstant1.setTimes(new Times());
					relateConstant1.setTimes( fillOutServer.fillTimes( relateConstant1.getTimes() ) );
					// Adiciona Relate e atualiza Person
					listRelatePersonPerson1.add(relateConstant1);
					person1.setListRelatePersonPerson( listRelatePersonPerson1 );
					flagUpdate1 = iData.setPerson(person1);
				}
				else
				{
					// Incrementa Times
					relateConstant1.setTimes( fillOutServer.fillTimes( relateConstant1.getTimes() ) );
					// Edita RelatePersonPerson e atualiza Person
					listRelatePersonPerson1.set(listRelatePersonPerson1.indexOf(relateConstant1), relateConstant1);
					person1.setListRelatePersonPerson( listRelatePersonPerson1 );
					flagUpdate1 = iData.setPerson(person1);
				}
				
				// Adiciona STATUS_REQUEST
				RelatePersonPerson relatePersonPerson1 = new RelatePersonPerson();
				relatePersonPerson1.setPerson1(person1);
				relatePersonPerson1.setPerson2(person2);
				relatePersonPerson1.setDateRelation(Calendar.getInstance());
				relatePersonPerson1.setStatus(RelatePersonPerson.STATUS_REQUEST);
				// Adiciona RelatePersonPerson e atualiza Person
				listRelatePersonPerson1.add(relatePersonPerson1);
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				System.out.println("listRelatePersonPerson1 = " + listRelatePersonPerson1);
				// Atualiza Person
				flagUpdate1 = iData.setPerson(person1);
				System.out.println("flagUpdate1 = " + flagUpdate1);
		    	
				// FREQUENCY (aceitacao)
				person1.setFrequency( fillOutServer.fillFrequencyPositive( person1.getFrequency() ) );
				
				// CONTEXTO e SCORE e TIMES
		 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_FRIEND_REQUEST);
		 		
				// Atualiza Person
				flagUpdate1 = iData.setPerson(person1);
				System.out.println("flagUpdate1 = " + flagUpdate1);
				
				// P2-P1 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();

				// Adiciona STATUS_REQUESTED
				RelatePersonPerson relatePersonPerson2 = new RelatePersonPerson();
				relatePersonPerson2.setPerson1(person2);
				relatePersonPerson2.setPerson2(person1);
				relatePersonPerson2.setDateRelation(Calendar.getInstance());
				relatePersonPerson2.setStatus(RelatePersonPerson.STATUS_REQUESTED);
				// Adiciona RelatePersonPerson e atualiza Person
				listRelatePersonPerson2.add(relatePersonPerson2);
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				System.out.println("listRelatePersonPerson2 = " + listRelatePersonPerson2);
		 		// Atualiza Person
				flagUpdate2 = iData.setPerson(person2);
				System.out.println("flagUpdate2 = " + flagUpdate2);
				
				// CONTEXTO e SCORE e TIMES
		 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_PERSON_FRIEND_REQUESTED);
				
		 		// Atualiza Person
				flagUpdate2 = iData.setPerson(person2);
				System.out.println("flagUpdate2 = " + flagUpdate2);
				
				if(flagUpdate1 == true && flagUpdate2 == true) {
					flagOK = true;
				}
			}
			else 
			{
				System.out.println("Erro: P1 ja enviou requisicao");
			}
		}
		else 
		{
			System.out.println("Erro: P1 ja recebeu requisicao");
		}

		return flagOK;
	}

	
	public boolean cancelRequestFriendship(long idPerson1, long idPerson2, Context contexto)
	{
		boolean flagDeleted1 = false;
		boolean flagDeleted2 = false;
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario logado que enviou o convite
		Person person2 = iData.getPerson(idPerson2); // usuario que recebeu convite (que sera cancelado)
    	
		// Verifica se ja existe (P1-P2) STATUS_REQUEST
		RelatePersonPersonPK rPP1C = new RelatePersonPersonPK();
		rPP1C.setPerson1(person1.getId());
		rPP1C.setPerson2(person2.getId());
		rPP1C.setStatus(RelatePersonPerson.STATUS_REQUEST);
		
		// Verifica se ja existe (P2-P1) STATUS_REQUESTED
		RelatePersonPersonPK rPP2C = new RelatePersonPersonPK();
		rPP2C.setPerson1(person2.getId());
		rPP2C.setPerson2(person1.getId());
		rPP2C.setStatus(RelatePersonPerson.STATUS_REQUESTED);
		
		// So pode cancelar Request se houver Request
		if( iData.getRelatePersonPerson(rPP1C) == null && iData.getRelatePersonPerson(rPP2C) == null )
		{
			System.out.println("Erro: nao ha STATUS_REQUEST para ser cancelado");
		}
		else
		{
			// P1-P2 -------------------------------------------------------------------------------------------------------
			List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
			
			// Deleta STATUS_REQUEST
			for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) 
			{
				if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REQUEST && 
					relatePersonPerson.getPerson1().getId() == 	person1.getId() &&
					relatePersonPerson.getPerson2().getId() == 	person2.getId() ) 
				{	
					listRelatePersonPerson1.remove(relatePersonPerson);
					System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
					RelatePersonPersonPK rPK = new RelatePersonPersonPK();
					rPK.setPerson1(relatePersonPerson.getPerson1().getId());
					rPK.setPerson2(relatePersonPerson.getPerson2().getId());
					rPK.setStatus(relatePersonPerson.getStatus());
					iData.removeRelatePersonPerson(rPK);
					flagDeleted1 = true;
					break;
				}
			}
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
			
			// CONTEXTO E SCORE (continua preenchendo outros atributos) P1
	 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_FRIEND_REQUEST_CANCEL);
			
			// Atualiza Person (P1)
			flagUpdate1 = iData.setPerson(person1);
			
			// P2-P1 -------------------------------------------------------------------------------------------------------
			List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
			
			// Deleta STATUS_REQUESTED
			for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) 
			{
				if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REQUESTED && 
					relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
					relatePersonPerson.getPerson2().getId() == 	person1.getId()) 
				{
					listRelatePersonPerson2.remove(relatePersonPerson);
					System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
					RelatePersonPersonPK rPK = new RelatePersonPersonPK();
					rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
					rPK.setPerson2(relatePersonPerson.getPerson2().getId());
					rPK.setStatus(relatePersonPerson.getStatus());
					iData.removeRelatePersonPerson(rPK);
					flagDeleted2 = true;
					break;
				}
			}
			person2.setListRelatePersonPerson( listRelatePersonPerson2 );
			
		 	person2 = fillOutServer.fillContextAndScoresAndTimes(person2, contexto, Context.TYPE_PERSON_FRIEND_REQUEST_CANCELED);
		 		
		 	// Atualiza Person (P1)
			flagUpdate2 = iData.setPerson(person2);
			
				if(flagUpdate1 == true && flagUpdate2 == true) {
					flagOK = true;
				}
			
		}
				
		return flagOK;
	}
	
	
	public boolean rejectFriendship(long idPerson1, long idPerson2, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario requested (que recebeu, e agora rejeita)
    	Person person2 = iData.getPerson(idPerson2); // usuario request (que enviou, e agora eh rejeitado)
    	
		// Verifica se ja existe (P1-P2) STATUS_REQUESTED
		RelatePersonPersonPK rPP1C = new RelatePersonPersonPK();
		rPP1C.setPerson1(person1.getId());
		rPP1C.setPerson2(person2.getId());
		rPP1C.setStatus(RelatePersonPerson.STATUS_REQUESTED);
		
		// Verifica se ja existe (P2-P1) STATUS_REQUEST
		RelatePersonPersonPK rPP2C = new RelatePersonPersonPK();
		rPP2C.setPerson1(person2.getId());
		rPP2C.setPerson2(person1.getId());
		rPP2C.setStatus(RelatePersonPerson.STATUS_REQUEST);
		
		// Se nao ha STATUS_REQUESTED nao se pode rejeitar
		if( iData.getRelatePersonPerson(rPP1C) == null && iData.getRelatePersonPerson(rPP2C) == null )
		{
			System.out.println("Erro: Nao ha STATUS_REQUESTED para ser rejeitado");
		}
		else
		{
			// Verifica se a relacao STATUS_REJECT ja existe:
			RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
			rPP1.setPerson1(person1.getId());
			rPP1.setPerson2(person2.getId());
			rPP1.setStatus(RelatePersonPerson.STATUS_REJECT);
			
			// Verifica se a relacao STATUS_REJECTED ja existe:
			RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
			rPP2.setPerson1(person2.getId());
			rPP2.setPerson2(person1.getId());
			rPP2.setStatus(RelatePersonPerson.STATUS_REJECTED);
			
			// Se as relacoes estao vazias, pode-se adicionar
			if(iData.getRelatePersonPerson(rPP1) == null && iData.getRelatePersonPerson(rPP2) == null)
			{
				// P1-P2 -------------------------------------------------------------------------------------------------------
		    	List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
				
				// Deleta STATUS_REQUESTED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) 
				{
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REQUESTED && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId() ) 
					{
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
		    	
				// Adiciona STATUS_REJECT
				RelatePersonPerson relatePersonPersonP1P2 = new RelatePersonPerson();
				relatePersonPersonP1P2.setPerson1(person1);
				relatePersonPersonP1P2.setPerson2(person2);
				relatePersonPersonP1P2.setDateRelation(Calendar.getInstance());
				relatePersonPersonP1P2.setStatus(RelatePersonPerson.STATUS_REJECT);
				// Adiciona RelatePersonPerson e atualiza Person
				listRelatePersonPerson1.add(relatePersonPersonP1P2);
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// P2-P1 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
				
				// Deleta STATUS_REQUEST
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) 
				{
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REQUEST && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person1.getId()) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Adiciona STATUS_REJECTED
				RelatePersonPerson relatePersonPersonP2P1 = new RelatePersonPerson();
				relatePersonPersonP2P1.setPerson1(person2);
				relatePersonPersonP2P1.setPerson2(person1);
				relatePersonPersonP2P1.setDateRelation(Calendar.getInstance());
				relatePersonPersonP2P1.setStatus(RelatePersonPerson.STATUS_REJECTED);
				// Adiciona RelatePersonPerson e atualiza Person
				listRelatePersonPerson2.add(relatePersonPersonP2P1);
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// COMUM -------------------------------------------------------------------------------------------------------
				// CONTEXTO E SCORE (continua preenchendo outros atributos)
		 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_FRIEND_REJECT);
		 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_PERSON_FRIEND_REJECTED);
		 		
				// Atualiza Person (P1 e P2)
				flagUpdate1 = iData.setPerson(person1);
				flagUpdate2 = iData.setPerson(person2);
				
				if(flagUpdate1 == true && flagUpdate2 == true) {
					flagOK = true;
				}
			}
			else
			{
				System.out.println("Erro: relacoes STATUS_REJECT e STATUS_REJECTED ja existem");
			}
		} // if
		
		return flagOK;
	}

	
	public boolean acceptFriendship(long idPerson1, long idPerson2, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		Person person1 = iData.getPerson(idPerson1); // usuario requested (que recebeu, e agora aceita)
    	Person person2 = iData.getPerson(idPerson2); // usuario request (que enviou, e agora eh aceito)
    	
    	// OBS: Quando uma amizade eh feita, todos os status negativos sao removidos. METODO MUITO GRANDE!
    	
		// Verifica se ja existe (P1-P2) STATUS_REQUESTED
		RelatePersonPersonPK rPP1C = new RelatePersonPersonPK();
		rPP1C.setPerson1(person1.getId());
		rPP1C.setPerson2(person2.getId());
		rPP1C.setStatus(RelatePersonPerson.STATUS_REQUESTED);
		
		// Verifica se ja existe (P2-P1) STATUS_REQUEST
		RelatePersonPersonPK rPP2C = new RelatePersonPersonPK();
		rPP2C.setPerson1(person2.getId());
		rPP2C.setPerson2(person1.getId());
		rPP2C.setStatus(RelatePersonPerson.STATUS_REQUEST);
		
		// Se nao ha STATUS_REQUESTED, nao se pode aceitar amizade
		if( iData.getRelatePersonPerson(rPP1C) == null && iData.getRelatePersonPerson(rPP2C) == null )
		{
			System.out.println("Erro: Nao ha STATUS_REQUESTED para ser aceito");
		}
		else
		{
			// Verifica se a relacao STATUS_FRIEND (P1-P2) ja existe:
			RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
			rPP1.setPerson1(person1.getId());
			rPP1.setPerson2(person2.getId());
			rPP1.setStatus(RelatePersonPerson.STATUS_FRIEND);
			
			// Verifica se a relacao STATUS_FRIEND (P2-P1) ja existe:
			RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
			rPP2.setPerson1(person2.getId());
			rPP2.setPerson2(person1.getId());
			rPP2.setStatus(RelatePersonPerson.STATUS_FRIEND);
			
			// Se estao vazios, cria-se amizade. Mas, se ja sao amigos, nao produz nenhum efeito novo (nem contexto)
			if(iData.getRelatePersonPerson(rPP1) == null && iData.getRelatePersonPerson(rPP2) == null)
			{	
				// P1-P2 -------------------------------------------------------------------------------------------------------
		    	List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
		    	
				// Deleta STATUS_REQUESTED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REQUESTED && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId() ) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// Deleta STATUS_DELETE
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_DELETE && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId() ) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// Deleta STATUS_DELETED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_DELETED && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId()) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// Deleta STATUS_REJECT
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REJECT && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId()) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// Deleta STATUS_REJECTED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REJECTED && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person2.getId() ) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );			

				// Adiciona STATUS_FRIEND
				RelatePersonPerson relatePersonPersonP1P2 = new RelatePersonPerson();
				relatePersonPersonP1P2.setPerson1(person1);
				relatePersonPersonP1P2.setPerson2(person2);
				relatePersonPersonP1P2.setDateRelation(Calendar.getInstance());
				relatePersonPersonP1P2.setStatus(RelatePersonPerson.STATUS_FRIEND);	
				// Adiciona RelatePersonPerson e Atualiza Person
				listRelatePersonPerson1.add(relatePersonPersonP1P2);
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				System.out.println("relatePersonPersonP1P2 = " + relatePersonPersonP1P2);
				
				// P2-P1 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
								
				// Deleta STATUS_REQUEST
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REQUEST && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person1.getId() ) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Deleta STATUS_DELETE
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_DELETE && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person1.getId() ) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Deleta STATUS_DELETED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_DELETED && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() &&
						relatePersonPerson.getPerson2().getId() == 	person1.getId() ) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Deleta STATUS_REJECT
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REJECT && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() &&
						relatePersonPerson.getPerson2().getId() == 	person1.getId() ) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Deleta STATUS_REJECTED
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_REJECTED && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person1.getId()) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Adiciona STATUS_FRIEND
				RelatePersonPerson relatePersonPersonP2P1 = new RelatePersonPerson();
				relatePersonPersonP2P1.setPerson1(person2);
				relatePersonPersonP2P1.setPerson2(person1);
				relatePersonPersonP2P1.setDateRelation(Calendar.getInstance());
				relatePersonPersonP2P1.setStatus(RelatePersonPerson.STATUS_FRIEND);
				listRelatePersonPerson2.add(relatePersonPersonP2P1);
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				System.out.println("relatePersonPersonP2P1 = " + relatePersonPersonP2P1);
				
				// COMUM -------------------------------------------------------------------------------------------------------
				// CONTEXTO E SCORE (continua preenchendo outros atributos)
		 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_FRIEND_START);
		 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_PERSON_FRIEND_START);
				
				// Atualiza Person (P1 e P2)
				flagUpdate1 = iData.setPerson(person1);
				flagUpdate2 = iData.setPerson(person2);

				if(flagUpdate1 == true && flagUpdate2 == true) {
					flagOK = true;
				}
			}
			else
			{
				System.out.println("Erro: P1 e P2 ja sao amigos");
			}
		} // if
		
		return flagOK;
	}
	
	
	public boolean removeFriendship(long idPerson1, long idPerson2, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario que deleta
    	Person person2 = iData.getPerson(idPerson2); // usuario deletado
    	
		// Verifica se a relacao STATUS_FRIEND (P1-P2) ja existe:
		RelatePersonPersonPK rPP1F = new RelatePersonPersonPK();
		rPP1F.setPerson1(person1.getId());
		rPP1F.setPerson2(person2.getId());
		rPP1F.setStatus(RelatePersonPerson.STATUS_FRIEND);
		
		// Verifica se a relacao STATUS_FRIEND (P2-P1) ja existe:
		RelatePersonPersonPK rPP2F = new RelatePersonPersonPK();
		rPP2F.setPerson1(person2.getId());
		rPP2F.setPerson2(person1.getId());
		rPP2F.setStatus(RelatePersonPerson.STATUS_FRIEND);
		
		// Se nao ha STATUS_FRIEND, nao ha o que se remover. Logo, nao produz nenhum efeito novo (nem contexto)
		if(iData.getRelatePersonPerson(rPP1F) == null && iData.getRelatePersonPerson(rPP2F) == null)
		{
			System.out.println("Erro: nao ha amizade para ser removida");
		}
		else
		{
			// Verifica se a relacao STATUS_DELETE ja existe:
			RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
			rPP1.setPerson1(person1.getId());
			rPP1.setPerson2(person2.getId());
			rPP1.setStatus(RelatePersonPerson.STATUS_DELETE);

			// Verifica se a relacao STATUS_DELETED ja existe:
			RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
			rPP2.setPerson1(person2.getId());
			rPP2.setPerson2(person1.getId());
			rPP2.setStatus(RelatePersonPerson.STATUS_DELETED);
			
			// Se nao ha STATUS_DELETE, pode-se adicionar
			if(iData.getRelatePersonPerson(rPP1) == null && iData.getRelatePersonPerson(rPP2) == null)
			{
				// P1-P2 -------------------------------------------------------------------------------------------------------
		    	List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
								
		    	// Deleta STATUS_FRIEND
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson1) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_FRIEND && 
						relatePersonPerson.getPerson1().getId() == 	person1.getId() &&
						relatePersonPerson.getPerson2().getId() == 	person2.getId() ) 
					{	
						listRelatePersonPerson1.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 1: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// Adiciona STATUS_DELETE
				RelatePersonPerson relatePersonPersonP1P2 = new RelatePersonPerson();
				relatePersonPersonP1P2.setPerson1(person1);
				relatePersonPersonP1P2.setPerson2(person2);
				relatePersonPersonP1P2.setDateRelation(Calendar.getInstance());
				relatePersonPersonP1P2.setStatus(RelatePersonPerson.STATUS_DELETE);	
				listRelatePersonPerson1.add(relatePersonPersonP1P2);
				person1.setListRelatePersonPerson( listRelatePersonPerson1 );
				
				// P2-P1 -------------------------------------------------------------------------------------------------------
				List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
				
				// Deleta STATUS_FRIEND
				for (RelatePersonPerson relatePersonPerson : listRelatePersonPerson2) {
					if( relatePersonPerson.getStatus() == RelatePersonPerson.STATUS_FRIEND && 
						relatePersonPerson.getPerson1().getId() == 	person2.getId() && 
						relatePersonPerson.getPerson2().getId() == 	person1.getId()) 
					{
						listRelatePersonPerson2.remove(relatePersonPerson);
						System.out.println("DEVE SER REMOVIDA 2: " + relatePersonPerson.getStatus() + " " + relatePersonPerson.getPerson1().getId() + " " + relatePersonPerson.getPerson2().getId());
						RelatePersonPersonPK rPK = new RelatePersonPersonPK();
						rPK.setPerson1(relatePersonPerson.getPerson1().getId());	// nao inverter a ordem
						rPK.setPerson2(relatePersonPerson.getPerson2().getId());
						rPK.setStatus(relatePersonPerson.getStatus());
						iData.removeRelatePersonPerson(rPK);
						break;
					}
				}
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// Adiciona STATUS_DELETED
				RelatePersonPerson relatePersonPersonP2P1 = new RelatePersonPerson();
				relatePersonPersonP2P1.setPerson1(person2);
				relatePersonPersonP2P1.setPerson2(person1);
				relatePersonPersonP2P1.setDateRelation(Calendar.getInstance());
				relatePersonPersonP2P1.setStatus(RelatePersonPerson.STATUS_DELETED);
				listRelatePersonPerson2.add(relatePersonPersonP2P1);
				person2.setListRelatePersonPerson( listRelatePersonPerson2 );
				
				// COMUM -------------------------------------------------------------------------------------------------------
				// CONTEXTO E SCORE (continua preenchendo outros atributos)
		 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_PERSON_FRIEND_DELETE);
		 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_PERSON_FRIEND_DELETED);
				
				// Atualiza Person (P1 e P2)
				flagUpdate1 = iData.setPerson(person1);
				flagUpdate2 = iData.setPerson(person2);
				
				if(flagUpdate1 == true && flagUpdate2 == true) {
					flagOK = true;
				}
				
			} 
			else
			{
				System.out.println("Erro: amizade ja foi desfeita");
			}
		} // if
		
		return flagOK;
	}
	

	public boolean sendMessage(long idPerson1, long idPerson2, String message, Context contexto) 
	{
		boolean flagUpdate1 = false;
		boolean flagUpdate2 = false;
		boolean flagOK = false;
		
		// OBS: metodo usado para enviar uma mensagem de P1 para P2
		// OBS: a lista de mensagens fica armazenada apenas na relacao STATUS_CHATTING
		
		// PERSON
		Person person1 = iData.getPerson(idPerson1); // usuario logado
		Person person2 = iData.getPerson(idPerson2); // usuario requisitado

		// MESSAGE
		String cabecalho = "" + person1.getNameFirst();
		String messageEdited = "" + cabecalho + " : " + message;
		
		// P1-P2 -------------------------------------------------------------------------------------------------------
		List<RelatePersonPerson> listRelatePersonPerson1 = person1.getListRelatePersonPerson();
		
		// Verifica se a relacao CONSTATNT ja existe:
		RelatePersonPersonPK rConst1 = new RelatePersonPersonPK();
		rConst1.setPerson1(person1.getId());
		rConst1.setPerson2(person2.getId());
		rConst1.setStatus(RelatePersonPerson.STATUS_INDEFINITE);
		RelatePersonPerson relateConstant1 = iData.getRelatePersonPerson(rConst1);
		
		if(relateConstant1 == null)
		{
			// Adiciona STATUS_CONSTANT
			relateConstant1 = new RelatePersonPerson();
			relateConstant1.setPerson1(person1);
			relateConstant1.setPerson2(person2);
			relateConstant1.setDateRelation(Calendar.getInstance());
			relateConstant1.setStatus(RelatePersonPerson.STATUS_INDEFINITE);
			// Cria e incrementa Times
			relateConstant1.setTimes(new Times());
			relateConstant1.setTimes( fillOutServer.fillTimes( relateConstant1.getTimes() ) );
			// Adiciona Relate e atualiza Person
			listRelatePersonPerson1.add(relateConstant1);
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
			iData.setPerson(person1);
		}
		else
		{
			// Incrementa Times
			relateConstant1.setTimes( fillOutServer.fillTimes( relateConstant1.getTimes() ) );
			// Edita RelatePersonPerson e atualiza Person
			listRelatePersonPerson1.set(listRelatePersonPerson1.indexOf(relateConstant1), relateConstant1);
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
		}
		
		// Verifica se a relacao STATUS_CHATTING ja existe:
		RelatePersonPersonPK rPP1 = new RelatePersonPersonPK();
		rPP1.setPerson1(person1.getId());
		rPP1.setPerson2(person2.getId());
		rPP1.setStatus(RelatePersonPerson.STATUS_CHATTING);
		RelatePersonPerson relatePersonPersonBD1 = iData.getRelatePersonPerson(rPP1);
				
		if( relatePersonPersonBD1 == null )
		{
			// Cria relacao
			RelatePersonPerson relatePersonPersonP1P2 = new RelatePersonPerson();
			relatePersonPersonP1P2.setPerson1(person1);
			relatePersonPersonP1P2.setPerson2(person2);
			relatePersonPersonP1P2.setDateRelation(Calendar.getInstance());
			relatePersonPersonP1P2.setStatus(RelatePersonPerson.STATUS_CHATTING);
			
			/*
			// Add mensagem
			List<String> listMessagesP1P2 = relatePersonPersonP1P2.getListMessages();
			listMessagesP1P2.add(messageEdited);
			relatePersonPersonP1P2.setListMessages(listMessagesP1P2);
			*/
			
			// Adiciona novo RelatePersonPerson e atualiza Person
			listRelatePersonPerson1.add(relatePersonPersonP1P2);
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
			
			// CONTEXTO E SCORE (chatting com uma nova pessoa)
	 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_CHAT_PERSON);
		}
		else
		{
			
			/*
			// Atualiza relacao com a mensagem
			List<String> listMessagesP1P2 = relatePersonPersonBD1.getListMessages();
			listMessagesP1P2.add(messageEdited);
			relatePersonPersonBD1.setListMessages(listMessagesP1P2);
			*/
			
			// Edita RelatePersonPerson e atualiza Person
			listRelatePersonPerson1.set(listRelatePersonPerson1.indexOf(relatePersonPersonBD1), relatePersonPersonBD1);
			person1.setListRelatePersonPerson( listRelatePersonPerson1 );
		}
    	
		// P2-P1 -------------------------------------------------------------------------------------------------------
		List<RelatePersonPerson> listRelatePersonPerson2 = person2.getListRelatePersonPerson();
		
		// Verifica se a relacao STATUS_CHATTING ja existe:
		RelatePersonPersonPK rPP2 = new RelatePersonPersonPK();
		rPP2.setPerson1(person2.getId());
		rPP2.setPerson2(person1.getId());
		rPP2.setStatus(RelatePersonPerson.STATUS_CHATTING);	
		RelatePersonPerson relatePersonPersonBD2 = iData.getRelatePersonPerson(rPP2);

		if( relatePersonPersonBD2 == null )
		{
			// Cria relacao
			RelatePersonPerson relatePersonPersonP2P1 = new RelatePersonPerson();
			relatePersonPersonP2P1.setPerson1(person2);
			relatePersonPersonP2P1.setPerson2(person1);
			relatePersonPersonP2P1.setDateRelation(Calendar.getInstance());
			relatePersonPersonP2P1.setStatus(RelatePersonPerson.STATUS_CHATTING);
			
			/*
			// Add mensagem
			List<String> listMessagesP2P1 = relatePersonPersonP2P1.getListMessages();
			listMessagesP2P1.add(messageEdited);
			relatePersonPersonP2P1.setListMessages(listMessagesP2P1);
			*/
			// Adiciona novo RelatePersonPerson e atualiza Person
			listRelatePersonPerson2.add(relatePersonPersonP2P1);
			person2.setListRelatePersonPerson( listRelatePersonPerson2 );
			
			// CONTEXTO E SCORE (chatting com uma nova pessoa)
	 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_CHAT_PERSON);
		}
		else
		{
			/*
			
			// Atualiza relacao com a mensagem
			List<String> listMessagesP2P1 = relatePersonPersonBD2.getListMessages();
			listMessagesP2P1.add(messageEdited);
			relatePersonPersonBD2.setListMessages(listMessagesP2P1);
			*/
			
			// Edita RelatePersonPerson e atualiza Person
			listRelatePersonPerson2.set(listRelatePersonPerson2.indexOf(relatePersonPersonBD2), relatePersonPersonBD2);
			person2.setListRelatePersonPerson( listRelatePersonPerson2 );
		}
		
		// OBS: esse UC cria dois contextos (chat_person e chat_send) na primeira comunicacao entre essas duas pessoas
		
		// COMUM -------------------------------------------------------------------------------------------------------
		// CONTEXTO E SCORE (continua preenchendo outros atributos)
 		person1 = fillOutServer.fillContextAndScoresAndTimes(person1, contexto, Context.TYPE_CHAT_SEND);
 		person2 = fillOutServer.fillContextAndScoresAndTimes(person2, null, Context.TYPE_CHAT_RECEIVE);
		
		// Atualiza Person (P1 e P2)
		flagUpdate1 = iData.setPerson(person1);
		flagUpdate2 = iData.setPerson(person2);
		
		if(flagUpdate1 == true && flagUpdate2 == true) {
			flagOK = true;
		}
		
		return flagOK;
	}
	
}
