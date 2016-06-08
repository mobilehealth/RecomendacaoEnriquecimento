package mobilehealth.core.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(schema = "public")
@NamedQueries({ @NamedQuery(name = "findByAccess", query = "select u from RelatePersonContent u where u.person.id = :idPerson and u.content.id = :idContent and u.dateRelation = :relationDate") })
@NamedQuery(name = "findByPerson", query = "select u from RelatePersonContent u where u.person.id = :idPerson")
public class RelatePersonContent {

	// OBS: Para saber se person eh o dono, pesquisar pelo autor em Person
	public static final int STATUS_INDEFINITE = 0; //
	public static final int STATUS_CONTENT_FAVORITE = 1;
	public static final int STATUS_CONTENT_VISUALIZED = 2; // Conteúdo
															// visualizado por
															// usuário
	public static final int STATUS_CONTENT_REJECTED = 3; // Conteúdo rejeitado
															// por usuário
	public static final int STATUS_CONTENT_VALUED = 4; // Conteúdo avaliado por
														// usuário

	public static final int STATUS_COMMENTED = 5; // Conteudos comentado por
													// usuário

	public static final int STATUS_RECOMMENDED = 6; // Conteudos recomendado ao
													// usuário

	public static final int SUGG_METH_PONDER = 21;
	public static final int SUGG_METH_CONTENT = 22;
	public static final int SUGG_METH_UBIQ = 23;
	public static final int SUGG_METH_HYBRID = 24;
	public static final int SUGG_METH_CONJUNTA = 25;
	public static final int SUGG_METH_USER = 26; // Recomendacao sugerida por
													// outro usuario
													// (compartilhamento).

	public static final int FINALITY_EDUC = 11;
	public static final int FINALITY_WORK = 12;
	public static final int FINALITY_HOME = 13;
	public static final int FINALITY_FUN = 14;
	public static final int FINALITY_OTHER = 15;

	@Id
	@SequenceGenerator(name = "relate_person_content_seq", sequenceName = "relate_person_content_seq", schema = "public")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relate_person_content_seq")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_person")
	private Person person;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_content")
	private Content content;
	
//	@Type(type = "timestamp")
//	private DateTime relationDate;
//
	//@Id
	@Column(name = "status")
	private int status;
	// Status desse conteudo. Ele anula a necessidade de ter uma outra lista de
	// conetúdos.

	// Data de inicio da relacao criada
	@Temporal(TemporalType.DATE)
	@Column(name = "date_relation")
	private Calendar dateRelation;

	// Metodo utilizado para recomendar este conteudo
	@Column(name = "suggested_method")
	private int suggestedMethod;

	// Conta quantas vezes foi sugerido
	@Column(name = "count_suggested")
	private int countSuggested;

	// Finalidade: Educação, Trabalho, moradia, lazer, outros.
	@Column(name = "finality")
	private int finality;

	// Avaliação pessoal de person sobre esse conteudo
	@Column(name = "rate_person")
	private float ratePerson;

	@Column(name = "i1")
	private float i1;

	@Column(name = "i2")
	private float i2;

	@Column(name = "i3")
	private float i3;

	// ------------------------------------------------------------------------
	// 1:1
	// ------------------------------------------------------------------------
	// Histograma acumulado de TODAS as acoes interacoes construtivas (create,
	// add, view, rate, comment) realizadas por essa pessoa, sobre esse conteudo
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_times")
	// @Expose(serialize=true, deserialize=false)
	private Times times = new Times();

	// -----------------------------------------------------------
	// Atributos relacionados a Contents especificos
	// -----------------------------------------------------------
	// CHALLENGE:
	@Column(name = "score_challenge_total")
	private int scoreChallengeTotal; // pontuação total do usuário

	// GROUP:
	@Column(name = "score_group_posts")
	private int scoreGroupPosts; // quantidade de postagens nesse grupo

	// -----------------------------------------------------------
	// 1:N
	// -----------------------------------------------------------
	// Lista de postagens de comentário

	/*
	 * @ElementCollection
	 * 
	 * @CollectionTable(name ="comments_content", joinColumns = {
	 * 
	 * @JoinColumn(name="id_person_relate_person_content",
	 * referencedColumnName="id_person"),
	 * 
	 * @JoinColumn(name="id_content_relate_person_content",
	 * referencedColumnName="id_content"),
	 * 
	 * @JoinColumn(name="status_relate_person_content",
	 * referencedColumnName="status")})
	 * 
	 * @Column(name="text") private List<String> listComments = new
	 * ArrayList<String>();
	 */

	/*
	 * Setters and Getters
	 */

	/*
	 * Setters and Getters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

/*	public DateTime getRelationDate() {
		return relationDate;
	}

	public void setRelationDate(DateTime accessDate) {
		this.relationDate = accessDate;
	}
*/
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFinality() {
		return finality;
	}

	public void setFinality(int finality) {
		this.finality = finality;
	}

	public Times getTimes() {
		return times;
	}

	public void setTimes(Times times) {
		this.times = times;
	}

	public float getRatePerson() {
		return ratePerson;
	}

	public void setRatePerson(float ratePerson) {
		this.ratePerson = ratePerson;
	}

	public int getSuggestedMethod() {
		return suggestedMethod;
	}

	public void setSuggestedMethod(int suggestedMethod) {
		this.suggestedMethod = suggestedMethod;
	}

	public int getCountSuggested() {
		return countSuggested;
	}

	public void setCountSuggested(int countSuggested) {
		this.countSuggested = countSuggested;
	}

	public int getScoreGroupPosts() {
		return scoreGroupPosts;
	}

	public void setScoreGroupPosts(int scoreGroupPosts) {
		this.scoreGroupPosts = scoreGroupPosts;
	}

	public int getScoreChallengeTotal() {
		return scoreChallengeTotal;
	}

	public void setScoreChallengeTotal(int scoreChallengeTotal) {
		this.scoreChallengeTotal = scoreChallengeTotal;
	}

	public Calendar getDateRelation() {
		return dateRelation;
	}

	public void setDateRelation(Calendar dateRelation) {
		this.dateRelation = dateRelation;
	}

	public String getStatusAsString() {
		String str = "";

		/*
		 * switch (status) { case STATUS_FAVORITE_OR_OWNER: str = "Favorito";
		 * break; case STATUS_SUGGESTED: str = "Sugerido"; break; case
		 * STATUS_VISUALIZED: str = "Visualizado"; break; case
		 * STATUS_REJECTED_OR_DELETED: str = "Rejeitado"; break; case
		 * STATUS_RATED: str = "Avaliado"; break; case STATUS_NEAR: str =
		 * "Proximo"; break; default: str = "Indefinido"; break; }
		 * 
		 * 
		 * else if (this.status == 8) return
		 * "Recomendação Colaborativa Ponderada"; else if (this.status == 9)
		 * return "Recomendação Baseada em Conteúdo"; else if (this.status ==
		 * 10) return "Recomendação Ubíqua"; else if (this.status == 11) return
		 * "Recomendação Híbrida (Final)"; return "";
		 */

		return str;

	}

	public float getI1() {
		return i1;
	}

	public void setI1(float i1) {
		this.i1 = i1;
	}

	public float getI2() {
		return i2;
	}

	public void setI2(float i2) {
		this.i2 = i2;
	}

	public float getI3() {
		return i3;
	}

	public void setI3(float i3) {
		this.i3 = i3;
	}

}
