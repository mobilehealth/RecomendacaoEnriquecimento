package mobilehealth.prc.controller;

import java.util.Calendar;
import java.util.List;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.File;
import mobilehealth.core.domain.Frequency;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonContentPK;

public class ManagerServerContent {
	private static ManagerServerContent instance;
	private IData iData;
	private ManagerServerTag managerServerTag;
	private ManagerServerLocation managerServerLocation;
	private FillOutServer fillOutServer;

	private ManagerServerContent() {
		this.iData = DataEclipseLink.getInstance();
		this.managerServerTag = ManagerServerTag.getInstance();
		this.managerServerLocation = ManagerServerLocation.getInstance();
		this.fillOutServer = new FillOutServer();
	}

	public static ManagerServerContent getInstance() {
		if (instance == null) {
			instance = new ManagerServerContent();
		}
		return instance;
	}

	public Content getContent(long id) {
		return iData.getContent(id);
	}

	public List<Content> getAllContent() {
		return iData.getAllContent();
	}
	
	public int getCountContent()
	{
		return iData.getAllContent().size();
	}

	public List<Content> searchContent(long idPerson, Object param,
			int contentType, Context contexto) {
		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTEXTO E SCORE (continua preenchendo outros atributos)
		switch (contentType) {
		case Content.TYPE_POST:
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_POST_SEARCH);
			break;
		case Content.TYPE_EVENT:
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_EVENT_SEARCH);
			break;
		case Content.TYPE_GROUP:
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_GROUP_SEARCH);
			break;
		case Content.TYPE_CHALLENGE:
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_CHALLENGE_SEARCH);
			break;
		default:
			System.out.println("Tipo de conteudo invalido");
			break;
		}

		return iData.searchContent(param);
	}

	public boolean createContent(long idPerson, Content content, Context contexto) {
		boolean flagOK = false;
		boolean flagUpdateP = false;
		boolean flagUpdateC = false;

		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTENT (continua preenchendo)
		content.setDateCreation(Calendar.getInstance());
		content.setFile(new File());
		content.setFrequency(new Frequency());

		// Verifica se as relacoes ja existem:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_CONTENT_FAVORITE);

		// Se ja tiver sido criado, nao cria nova relacao nem produz qq efeito
		if (iData.getRelatePersonContent(rPC) == null) {
			// TAG (cria tags com base na descricao)
			managerServerTag.createAndUpdateTags(content.getDescription(),
					person, content, null);

			// LOCATION
			Location location = contexto.getLocation(); // Pega location
														// semi-preenchido do
														// cliente
			location = managerServerLocation.reverseGeocoding(location); // Termina
																			// de
																			// preencher
																			// location

			// RELACAO-PERSON-CONTENT
			List<RelatePersonContent> listRelatePersonContent = person.getListRelatePersonContent();

			// Adiciona STATUS_FAVORITE_OR_OWNER
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd.setStatus(RelatePersonContent.STATUS_CONTENT_FAVORITE);
			relatePersonContentAdd.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?

			// TIMES: P-C
			relatePersonContentAdd.setTimes(fillOutServer.fillTimes(relatePersonContentAdd.getTimes()));

			// Adiciona RelatePersonContent e atualiza Person
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);

			// RELACAO-CONTENT-LOCATION
			List<RelateContentLocation> listContentLocation = content.getListRelateContentLocation();

			// Adiciona MOTIVE_CREATED
			RelateContentLocation relateContentLocation = new RelateContentLocation();
			relateContentLocation.setContent(content);
			relateContentLocation.setLocation(location);
			relateContentLocation.setDateRelation(Calendar.getInstance());
			relateContentLocation.setStatus(RelateContentLocation.STATUS_CONTENT_VISUALIZED);
			listContentLocation.add(relateContentLocation);
			content.setListRelateContentLocation(listContentLocation);

			// CONTEXTO E SCORE
			if (content.getType() == Content.TYPE_POST) {
				person = fillOutServer.fillContextAndScoresAndTimes(person,
						contexto, Context.TYPE_POST_CREATED);
			}

			// Atualiza Person
			flagUpdateP = iData.setPerson(person);

			// Atualiza Content
			flagUpdateC = iData.setContent(content);

			if (flagUpdateP == true && flagUpdateC == true) {
				flagOK = true;
			}
		} // if

		return flagOK;
	}

	public boolean createContent(Content content) {
		boolean flagOK = false;
		
		// CONTENT (continua preenchendo)
		content.setDateCreation(Calendar.getInstance());
		content.setFile(new File());
		content.setFrequency(new Frequency());
				
		// Atualiza Content
		flagOK = iData.setContent(content);
	
		return flagOK;
	}

	public boolean editContent(long idPerson, Content content, Context contexto) {
		boolean flagPerson = false;
		boolean flagContent = false;
		boolean flagOK = false;

		// PERSON
		Person person = iData.getPerson(idPerson);

		// CONTEXTO E SCORE (continua preenchendo outros atributos)
		if (content.getType() == Content.TYPE_POST) {
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_POST_EDITED);
		}

		// setContent
		flagContent = iData.setContent(content);

		// setPerson
		flagPerson = iData.setPerson(person);

		if (flagPerson == true && flagContent == true) {
			flagOK = true;
		}

		return flagOK;
	}

	public boolean editContent(Content content) {

		boolean flagOK = false;

		// setContent
		flagOK = iData.setContent(content);

		return flagOK;
	}

	
	public boolean removeContent(long idPerson, long idContent, Context contexto) {
		boolean flagEmpty = false;
		boolean flagPerson = false;
		boolean flagContent = false;
		boolean flagOK = false;

		// PERSON
		Person person = iData.getPerson(idPerson);

		// CONTENT
		Content content = iData.getContent(idContent);

		// Verifica se a relacao ja existe:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_CONTENT_REJECTED);

		// Se ja tiver sido deletado ou rejeitado, nao cria nova relacao
		if (iData.getRelatePersonContent(rPC) == null) {
			flagEmpty = true;
		}

		// RELACAO-PERSON-CONTENT
		List<RelatePersonContent> listRelatePersonContent = person
				.getListRelatePersonContent();
		// Deleta STATUS_FAVORITE_OR_OWNER
		for (RelatePersonContent relatePersonContent : listRelatePersonContent) {
			if (relatePersonContent.getStatus() == RelatePersonContent.STATUS_CONTENT_FAVORITE
					&& relatePersonContent.getPerson().getId() == person
							.getId()
					&& relatePersonContent.getContent().getId() == content
							.getId()) {
				listRelatePersonContent.remove(relatePersonContent);
				System.out.println("DEVE SER REMOVIDA 1: "
						+ relatePersonContent.getStatus() + " "
						+ relatePersonContent.getPerson().getId() + " "
						+ relatePersonContent.getContent().getId());
				RelatePersonContentPK rPK = new RelatePersonContentPK();
				rPK.setPerson(relatePersonContent.getPerson().getId());
				rPK.setContent(relatePersonContent.getContent().getId());
				rPK.setStatus(relatePersonContent.getStatus());
				iData.removeRelatePersonContent(rPK);
				break;
			}
		}
		person.setListRelatePersonContent(listRelatePersonContent);
		// Adiciona STATUS_REJECTED_OR_DELETED
		if (flagEmpty == true) {
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd
					.setStatus(RelatePersonContent.STATUS_CONTENT_REJECTED);
			relatePersonContentAdd
					.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);
		}

		// CONTEXTO E SCORE (continua preenchendo outros atributos)
		if (content.getType() == Content.TYPE_POST) {
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_POST_DELETED);
		}

		// setPerson
		flagPerson = iData.setPerson(person);

		if (flagPerson == true && flagContent == true) {
			flagOK = true;
		}

		return flagOK;
	}

	public boolean viewContent(long idPerson, long idContent, Context contexto) {
		boolean flagUpdate = false;

		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTENT
		Content content = iData.getContent(idContent); // conteudo visualizado

		// Verifica se a relacao ja existe:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_CONTENT_VISUALIZED);

		// Se ja tiver sido visto, nao cria nova relacao nem produz qq efeito
		if (iData.getRelatePersonContent(rPC) == null) {
			// RELACAO-PERSON-CONTENT
			List<RelatePersonContent> listRelatePersonContent = person
					.getListRelatePersonContent();

			// Adiciona STATUS_VISUALIZED
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd.setStatus(RelatePersonContent.STATUS_CONTENT_VISUALIZED);
			relatePersonContentAdd.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?

			// TIMES: P-C
			relatePersonContentAdd.setTimes(fillOutServer
					.fillTimes(relatePersonContentAdd.getTimes()));

			// Adiciona e atualiza Person
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);

			// CONTEXTO E SCORE
			if (content.getType() == Content.TYPE_POST) {
				person = fillOutServer.fillContextAndScoresAndTimes(person,
						contexto, Context.TYPE_POST_VIEWED);
			}

			// Atualiza Person
			flagUpdate = iData.setPerson(person);
		}

		return flagUpdate;
	}

	public boolean rejectRecommContent(long idPerson, long idContent,
			Context contexto) {
		boolean flagEmpty = false;
		boolean flagUpdate = false;

		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTENT
		Content content = iData.getContent(idContent); // conteudo recomendado
														// (que esta sendo
														// rejeitado)

		// Verifica se a relacao ja existe:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_CONTENT_REJECTED);

		// Se ja tiver sido rejeitado, nao cria nova relacao
		if (iData.getRelatePersonContent(rPC) == null) {
			flagEmpty = true;
		}

		// RELACAO-PERSON-CONTENT
		List<RelatePersonContent> listRelatePersonContent = person
				.getListRelatePersonContent();

		// Deleta STATUS_SUGGESTED
		for (RelatePersonContent relatePersonContent : listRelatePersonContent) {
			if (relatePersonContent.getStatus() == RelatePersonContent.STATUS_RECOMMENDED
					&& relatePersonContent.getPerson().getId() == person
							.getId()
					&& relatePersonContent.getContent().getId() == content
							.getId()) {
				listRelatePersonContent.remove(relatePersonContent);
				System.out.println("DEVE SER REMOVIDA 1: "
						+ relatePersonContent.getStatus() + " "
						+ relatePersonContent.getPerson().getId() + " "
						+ relatePersonContent.getContent().getId());
				RelatePersonContentPK rPK = new RelatePersonContentPK();
				rPK.setPerson(relatePersonContent.getPerson().getId());
				rPK.setContent(relatePersonContent.getContent().getId());
				rPK.setStatus(relatePersonContent.getStatus());
				iData.removeRelatePersonContent(rPK);
				break;
			}
		}
		person.setListRelatePersonContent(listRelatePersonContent);

		// Adiciona STATUS_REJECTED_OR_DELETED
		if (flagEmpty == true) {
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd.setStatus(RelatePersonContent.STATUS_CONTENT_REJECTED);
			relatePersonContentAdd.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?

			// Adiciona RelatePersonContent e atualiza Person
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);

			// FREQUENCY (rejeicao)
			person.setFrequency(fillOutServer.fillFrequencyNegative(person
					.getFrequency()));
		}

		// CONTEXTO E SCORE
		if (content.getType() == Content.TYPE_POST) {
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_POST_REJECTED);
		}

		// Atualiza Person
		flagUpdate = iData.setPerson(person);

		return flagUpdate;
	}

	public boolean addContent(long idPerson, long idContent, Context contexto) {
		boolean flagUpdate = false;

		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTENT
		Content content = iData.getContent(idContent); // conteudo adicionado

		// Verifica se a relacao ja existe:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_CONTENT_FAVORITE);

		// Se ja tiver sido adicionado, ou for dono, nao cria nova relacao nem
		// produz qq efeito
		if (iData.getRelatePersonContent(rPC) == null) {
			// RELACAO-PERSON-CONTENT
			List<RelatePersonContent> listRelatePersonContent = person
					.getListRelatePersonContent();

			// Deleta STATUS_SUGGESTED
			for (RelatePersonContent relatePersonContent : listRelatePersonContent) {
				if (relatePersonContent.getStatus() == RelatePersonContent.STATUS_RECOMMENDED
						&& relatePersonContent.getPerson().getId() == person
								.getId()
						&& relatePersonContent.getContent().getId() == content
								.getId()) {
					listRelatePersonContent.remove(relatePersonContent);
					System.out.println("DEVE SER REMOVIDA 1: "
							+ relatePersonContent.getStatus() + " "
							+ relatePersonContent.getPerson().getId() + " "
							+ relatePersonContent.getContent().getId());
					RelatePersonContentPK rPK = new RelatePersonContentPK();
					rPK.setPerson(relatePersonContent.getPerson().getId());
					rPK.setContent(relatePersonContent.getContent().getId());
					rPK.setStatus(relatePersonContent.getStatus());
					iData.removeRelatePersonContent(rPK);
					break;
				}
			}
			person.setListRelatePersonContent(listRelatePersonContent);

			// Deleta STATUS_REJECTED_OR_DELETED
			for (RelatePersonContent relatePersonContent : listRelatePersonContent) {
				if (relatePersonContent.getStatus() == RelatePersonContent.STATUS_CONTENT_REJECTED
						&& relatePersonContent.getPerson().getId() == person
								.getId()
						&& relatePersonContent.getContent().getId() == content
								.getId()) {
					listRelatePersonContent.remove(relatePersonContent);
					System.out.println("DEVE SER REMOVIDA 1: "
							+ relatePersonContent.getStatus() + " "
							+ relatePersonContent.getPerson().getId() + " "
							+ relatePersonContent.getContent().getId());
					RelatePersonContentPK rPK = new RelatePersonContentPK();
					rPK.setPerson(relatePersonContent.getPerson().getId());
					rPK.setContent(relatePersonContent.getContent().getId());
					rPK.setStatus(relatePersonContent.getStatus());
					iData.removeRelatePersonContent(rPK);
					break;
				}
			}
			person.setListRelatePersonContent(listRelatePersonContent);

			// Adiciona STATUS_FAVORITE_OR_OWNER
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd
					.setStatus(RelatePersonContent.STATUS_CONTENT_FAVORITE);
			relatePersonContentAdd
					.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?

			// TIMES: P-C
			relatePersonContentAdd.setTimes(fillOutServer
					.fillTimes(relatePersonContentAdd.getTimes()));

			// Adiciona RelatePersonContent e atualiza Person
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);

			// FREQUENCY (aceitacao)
			person.setFrequency(fillOutServer.fillFrequencyPositive(person
					.getFrequency()));

			// CONTEXTO E SCORE
			if (content.getType() == Content.TYPE_POST) {
				person = fillOutServer.fillContextAndScoresAndTimes(person,
						contexto, Context.TYPE_POST_ADDED);
			}

			// Atualiza Person
			flagUpdate = iData.setPerson(person);

		} // if

		return flagUpdate;
	}

	public boolean rateContent(long idPerson, long idContent, int rateFromPerson, Context contexto) {
		boolean flagEmpty = false;
		boolean flagUpdate = false;

		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTENT
		Content content = iData.getContent(idContent); // conteudo avaliado

		// Verifica se a relacao ja existe:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_CONTENT_VALUED);

		// Se ja tiver sido avaliado, muda a nota, mas, nao cria nova relacao
		RelatePersonContent relatePersonContentBD = iData
				.getRelatePersonContent(rPC);

		if (relatePersonContentBD == null) {
			flagEmpty = true;
		}

		// RELACAO-PERSON-CONTENT
		List<RelatePersonContent> listRelatePersonContent = person.getListRelatePersonContent();

		if (flagEmpty == true) {
			// Cria relacao STATUS_RATED
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setRatePerson(rateFromPerson); // Importante!
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd.setStatus(RelatePersonContent.STATUS_CONTENT_VALUED);
			relatePersonContentAdd
					.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?

			// TIMES: P-C
			relatePersonContentAdd.setTimes(fillOutServer
					.fillTimes(relatePersonContentAdd.getTimes()));

			// Adiciona RelatePersonContent e atualiza Person
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);
		} else {
			// Atualiza relacao com o novo rate
			relatePersonContentBD.setRatePerson(rateFromPerson);
			listRelatePersonContent.set(
					listRelatePersonContent.indexOf(relatePersonContentBD),
					relatePersonContentBD);
			person.setListRelatePersonContent(listRelatePersonContent);
		}

		// CONTEXTO E SCORE
		if (content.getType() == Content.TYPE_POST) {
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_POST_RATED);
		}

		// Atualiza Person
		flagUpdate = iData.setPerson(person);

		return flagUpdate;
	}

	public boolean commentContent(long idPerson, long idContent,
			String comment, Context contexto) {
		boolean flagEmpty = false;
		boolean flagUpdate = false;

		// PERSON
		Person person = iData.getPerson(idPerson); // usuario logado

		// CONTENT
		Content content = iData.getContent(idContent); // conteudo comentado

		// Verifica se a relacao ja existe:
		RelatePersonContentPK rPC = new RelatePersonContentPK();
		rPC.setPerson(person.getId());
		rPC.setContent(content.getId());
		rPC.setStatus(RelatePersonContent.STATUS_COMMENTED);

		// Se ja tiver sido comentado, adiciona comentario, mas, nao cria nova
		// relacao
		RelatePersonContent relatePersonContentBD = iData
				.getRelatePersonContent(rPC);

		if (relatePersonContentBD == null) {
			flagEmpty = true;
		}

		// TAG (cria tags com base no comentario)
		managerServerTag.createAndUpdateTags(comment, person, content, null);

		// RELACAO-PERSON-CONTENT
		List<RelatePersonContent> listRelatePersonContent = person
				.getListRelatePersonContent();

		if (flagEmpty == true) {
			// Cria relacao STATUS_COMMENTED
			RelatePersonContent relatePersonContentAdd = new RelatePersonContent();
			relatePersonContentAdd.setPerson(person);
			relatePersonContentAdd.setContent(content);
			relatePersonContentAdd.setDateRelation(Calendar.getInstance());
			relatePersonContentAdd
					.setStatus(RelatePersonContent.STATUS_COMMENTED);
			relatePersonContentAdd
					.setFinality(RelatePersonContent.FINALITY_OTHER); // TODO
																		// Duvida:
																		// como
																		// preencher?
			/*
			// Add comentario
			List<String> listComments = relatePersonContentAdd.getListComments();
			listComments.add(comment);
			relatePersonContentAdd.setListComments(listComments);
			 */
			
			// TIMES: P-C
			relatePersonContentAdd.setTimes(fillOutServer
					.fillTimes(relatePersonContentAdd.getTimes()));

			// Adiciona RelatePersonContent e atualiza Person
			listRelatePersonContent.add(relatePersonContentAdd);
			person.setListRelatePersonContent(listRelatePersonContent);
		} else {
			/*
			// Atualiza relacao com o novo comentario
			List<String> listComments = relatePersonContentBD.getListComments();
			listComments.add(comment);
			relatePersonContentBD.setListComments(listComments);
			*/
			
			// TIMES: P-C
			relatePersonContentBD.setTimes(fillOutServer
					.fillTimes(relatePersonContentBD.getTimes()));

			// Atualiza RelatePersonContent e atualiza Person
			listRelatePersonContent.set(
					listRelatePersonContent.indexOf(relatePersonContentBD),
					relatePersonContentBD);
			person.setListRelatePersonContent(listRelatePersonContent);
		}

		// CONTEXTO E SCORE
		if (content.getType() == Content.TYPE_POST) {
			person = fillOutServer.fillContextAndScoresAndTimes(person,
					contexto, Context.TYPE_POST_COMMENTED);
		}

		// Atualiza Person
		flagUpdate = iData.setPerson(person);

		return flagUpdate;
	}
}
