package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="scores", schema="public")
public class Scores implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "scores_id_seq", sequenceName = "scores_id_seq", allocationSize = 1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scores_id_seq")  
	@Column(name = "id")
	private Integer id;
		
	//-------------------------------------------------------
	// Post (Content)
	//-------------------------------------------------------
	@Column(name = "post_created")
	private int postCreated = 0;

	@Column(name = "post_added")
	private int postAdded = 0;
	
	@Column(name = "post_deleted")
	private int postDeleted = 0;
	
	@Column(name = "post_commented")
	private int postCommented = 0;
	
	@Column(name = "post_viewed")
	private int postViewed = 0;
	
	@Column(name = "post_rated")
	private int postRated = 0;
	
	@Column(name = "post_rejected")
	private int postRejected = 0;
	
	@Column(name = "post_edited")
	private int postEdited = 0;
	
	@Column(name = "post_warned")
	private int postWarned = 0;
	
	@Column(name = "post_search")
	private int postSearch = 0;
	
	//-------------------------------------------------------
	// Location
	//-------------------------------------------------------
	@Column(name = "location_created")
	private int locationCreated = 0;

	@Column(name = "location_added")
	private int locationAdded = 0;
	
	@Column(name = "location_deleted")
	private int locationDeleted = 0;
	
	@Column(name = "location_commented")
	private int locationCommented = 0;
	
	@Column(name = "location_viewed")
	private int locationViewed = 0;
	
	@Column(name = "location_rated")
	private int locationRated = 0;
	
	@Column(name = "location_rejected")
	private int locationRejected = 0;
	
	@Column(name = "location_edited")
	private int locationEdited = 0;
	
	@Column(name = "location_warned")
	private int locationWarned = 0;
	
	@Column(name = "location_search")
	private int locationSearch = 0;
	
	//-------------------------------------------------------
	// Challenge (Content)
	//-------------------------------------------------------
	@Column(name = "challenge_created")
	private int challengeCreated = 0;

	@Column(name = "challenge_added")
	private int challengeAdded = 0;
	
	@Column(name = "challenge_deleted")
	private int challengeDeleted = 0;
	
	@Column(name = "challenge_commented")
	private int challengeCommented = 0;
	
	@Column(name = "challenge_viewed")
	private int challengeViewed = 0;
	
	@Column(name = "challenge_rated")
	private int challengeRated = 0;
	
	@Column(name = "challenge_rejected")
	private int challengeRejected = 0;
	
	@Column(name = "challenge_edited")
	private int challengeEdited = 0;
	
	@Column(name = "challenge_warned")
	private int challengeWarned = 0;
	
	@Column(name = "challenge_search")
	private int challengeSearch = 0;
	
	//-------------------------------------------------------
	// Event (Content)
	//-------------------------------------------------------
	@Column(name = "event_created")
	private int eventCreated = 0;

	@Column(name = "event_added")
	private int eventAdded = 0;
	
	@Column(name = "event_deleted")
	private int eventDeleted = 0;
	
	@Column(name = "event_commented")
	private int eventCommented = 0;
	
	@Column(name = "event_viewed")
	private int eventViewed = 0;
	
	@Column(name = "event_rated")
	private int eventRated = 0;
	
	@Column(name = "event_rejected")
	private int eventRejected = 0;
	
	@Column(name = "event_edited")
	private int eventEdited = 0;

	@Column(name = "event_warned")
	private int eventWarned = 0;
	
	@Column(name = "event_search")
	private int eventSearch = 0;
	
	//-------------------------------------------------------
	// Group (Content)
	//-------------------------------------------------------
	@Column(name = "group_created")
	private int groupCreated = 0;

	@Column(name = "group_added")
	private int groupAdded = 0;
	
	@Column(name = "group_deleted")
	private int groupDeleted = 0;
	
	@Column(name = "group_commented")
	private int groupCommented = 0;
	
	@Column(name = "group_viewed")
	private int groupViewed = 0;
	
	@Column(name = "group_rated")
	private int groupRated = 0;
	
	@Column(name = "group_rejected")
	private int groupRejected = 0;
	
	@Column(name = "group_edited")
	private int groupEdited = 0;
	
	@Column(name = "group_warned")
	private int groupWarned = 0;
	
	@Column(name = "group_search")
	private int groupSearch = 0;
	
	//-------------------------------------------------------
	// Person
	//-------------------------------------------------------
	// registrado pela primeira vez
	@Column(name = "person_created_first")
	private int personCreatedFirst = 0;

	// registrado apos recuparacao
	@Column(name = "person_created_again")
	private int personCreatedAgain = 0;

	@Column(name = "person_editded")
	private int personEdited = 0;

	@Column(name = "person_deleted")
	private int personDeleted = 0;
	
	@Column(name = "person_comment")		// TODO Pendencia: comentar outra pessoa. Amadurecer melhor essa ideia. Nao esta sendo usado.
	private int personComment = 0;
	
	@Column(name = "person_commented")		// TODO Pendencia: receber comentario de outra pessoa. Amadurecer melhor essa ideia. Nao esta sendo usado.
	private int personCommented = 0;
	
	// visualizacoes de perfil que realiza
	@Column(name = "person_view")
	private int personView = 0;
	
	// visualizacoes de perfil que recebe de outra pessoa
	@Column(name = "person_viewed")
	private int personViewed = 0;
	
	// denuncias feitas contra outra pessoa	// TODO Pendencia: Auto regulacao. Nao esta sendo usado.
	@Column(name = "person_warn")
	private int personWarn = 0;
	
	// denuncias recebidas por outra pessoa // TODO Pendencia: Auto regulacao. Nao esta sendo usado.
	@Column(name = "person_warned")
	private int personWarned = 0;
	
	@Column(name = "person_search")
	private int personSearch = 0;
	
	//-------------------------------------------------------
	// Friend
	//-------------------------------------------------------
	// requisicoes de amizade enviadas
	@Column(name = "person_friend_request")
	private int personFriendRequest = 0;

	// requisicoes de amizade recebidas
	@Column(name = "person_friend_requested")
	private int personFriendRequested = 0;
	
	// requisicoes de amizades aceitas (aceitou convite)
	@Column(name = "person_friend_accept")
	private int personFriendAccept = 0;
	
	// requisicoes de amizades aceitas (teve convite aceito)
	@Column(name = "person_friend_accepted")
	private int personFriendAccepted = 0;
	
	@Column(name = "person_friend_reject")
	private int personFriendReject = 0;
	
	@Column(name = "person_friend_rejected")
	private int personFriendRejected = 0;
	
	@Column(name = "person_friend_delete")
	private int personFriendDelete = 0;
	
	@Column(name = "person_friend_deleted")
	private int personFriendDeleted = 0;
	
	@Column(name = "person_friend_request_cancel")
	private int personFriendRequestCancel = 0;
	
	@Column(name = "person_friend_request_canceled")
	private int personFriendRequestCanceled = 0;
	
	//-------------------------------------------------------
	// Externalaccount
	//-------------------------------------------------------
	@Column(name = "externalaccount_created")
	private int externalaccountCreated = 0;
	
	@Column(name = "externalaccount_deleted")
	private int externalaccountDeleted = 0;
	
	//-------------------------------------------------------
	// Outras acoes
	//-------------------------------------------------------
	// score dos compartilhamentos. // TODO PENDENCIA: amadurecer UC de compartilhamento. Atualmente, nao esta sendo usado.
	@Column(name = "sharing")
	private int sharing = 0;	
	
	// quantidade de mensagens enviadas
	@Column(name = "chat_send")
	private int chatSend = 0;
	
	// quantidade de mensagens recebidas
	@Column(name = "chat_receive")
	private int chatReceive = 0;
	
	// quantidade de pessoas com que se comunica por chat
	@Column(name = "chat_persons")
	private int chatPersons = 0;
	
	// contexto criado regularmente. Usado para medir quanto tempo o usuario fica online
	@Column(name = "regular")
	private int regular = 0;
	
	@Column(name = "login")
	private int login = 0;
	
	@Column(name = "logout")
	private int logout = 0;
	
	// score nos Challenges (respostas certas)
	@Column(name = "challenge_answer_right")
	private int challengeAnswerRight = 0;

	// score nos Challenges (respostas erradas)
	@Column(name = "challenge_answer_wrong")
	private int challengeAnswerWrong = 0;

	//--------------------------------------------------------------------------------------------
	// Getters and Setters
	//--------------------------------------------------------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostCreated() {
		return postCreated;
	}

	public void setPostCreated(int postCreated) {
		this.postCreated = postCreated;
	}

	public int getPostAdded() {
		return postAdded;
	}

	public void setPostAdded(int postAdded) {
		this.postAdded = postAdded;
	}

	public int getPostDeleted() {
		return postDeleted;
	}

	public void setPostDeleted(int postDeleted) {
		this.postDeleted = postDeleted;
	}

	public int getPostCommented() {
		return postCommented;
	}

	public void setPostCommented(int postCommented) {
		this.postCommented = postCommented;
	}

	public int getPostViewed() {
		return postViewed;
	}

	public void setPostViewed(int postViewed) {
		this.postViewed = postViewed;
	}

	public int getPostRated() {
		return postRated;
	}

	public void setPostRated(int postRated) {
		this.postRated = postRated;
	}

	public int getPostRejected() {
		return postRejected;
	}

	public void setPostRejected(int postRejected) {
		this.postRejected = postRejected;
	}

	public int getPostEdited() {
		return postEdited;
	}

	public void setPostEdited(int postEdited) {
		this.postEdited = postEdited;
	}

	public int getLocationCreated() {
		return locationCreated;
	}

	public void setLocationCreated(int locationCreated) {
		this.locationCreated = locationCreated;
	}

	public int getLocationAdded() {
		return locationAdded;
	}

	public void setLocationAdded(int locationAdded) {
		this.locationAdded = locationAdded;
	}

	public int getLocationDeleted() {
		return locationDeleted;
	}

	public void setLocationDeleted(int locationDeleted) {
		this.locationDeleted = locationDeleted;
	}

	public int getLocationCommented() {
		return locationCommented;
	}

	public void setLocationCommented(int locationCommented) {
		this.locationCommented = locationCommented;
	}

	public int getLocationViewed() {
		return locationViewed;
	}

	public void setLocationViewed(int locationViewed) {
		this.locationViewed = locationViewed;
	}

	public int getLocationRated() {
		return locationRated;
	}

	public void setLocationRated(int locationRated) {
		this.locationRated = locationRated;
	}

	public int getLocationRejected() {
		return locationRejected;
	}

	public void setLocationRejected(int locationRejected) {
		this.locationRejected = locationRejected;
	}

	public int getLocationEdited() {
		return locationEdited;
	}

	public void setLocationEdited(int locationEdited) {
		this.locationEdited = locationEdited;
	}

	public int getChallengeCreated() {
		return challengeCreated;
	}

	public void setChallengeCreated(int challengeCreated) {
		this.challengeCreated = challengeCreated;
	}

	public int getChallengeAdded() {
		return challengeAdded;
	}

	public void setChallengeAdded(int challengeAdded) {
		this.challengeAdded = challengeAdded;
	}

	public int getChallengeDeleted() {
		return challengeDeleted;
	}

	public void setChallengeDeleted(int challengeDeleted) {
		this.challengeDeleted = challengeDeleted;
	}

	public int getChallengeCommented() {
		return challengeCommented;
	}

	public void setChallengeCommented(int challengeCommented) {
		this.challengeCommented = challengeCommented;
	}

	public int getChallengeViewed() {
		return challengeViewed;
	}

	public void setChallengeViewed(int challengeViewed) {
		this.challengeViewed = challengeViewed;
	}

	public int getChallengeRated() {
		return challengeRated;
	}

	public void setChallengeRated(int challengeRated) {
		this.challengeRated = challengeRated;
	}

	public int getChallengeRejected() {
		return challengeRejected;
	}

	public void setChallengeRejected(int challengeRejected) {
		this.challengeRejected = challengeRejected;
	}

	public int getChallengeEdited() {
		return challengeEdited;
	}

	public void setChallengeEdited(int challengeEdited) {
		this.challengeEdited = challengeEdited;
	}

	public int getEventCreated() {
		return eventCreated;
	}

	public void setEventCreated(int eventCreated) {
		this.eventCreated = eventCreated;
	}

	public int getEventAdded() {
		return eventAdded;
	}

	public void setEventAdded(int eventAdded) {
		this.eventAdded = eventAdded;
	}

	public int getEventDeleted() {
		return eventDeleted;
	}

	public void setEventDeleted(int eventDeleted) {
		this.eventDeleted = eventDeleted;
	}

	public int getEventCommented() {
		return eventCommented;
	}

	public void setEventCommented(int eventCommented) {
		this.eventCommented = eventCommented;
	}

	public int getEventViewed() {
		return eventViewed;
	}

	public void setEventViewed(int eventViewed) {
		this.eventViewed = eventViewed;
	}

	public int getEventRated() {
		return eventRated;
	}

	public void setEventRated(int eventRated) {
		this.eventRated = eventRated;
	}

	public int getEventRejected() {
		return eventRejected;
	}

	public void setEventRejected(int eventRejected) {
		this.eventRejected = eventRejected;
	}

	public int getEventEdited() {
		return eventEdited;
	}

	public void setEventEdited(int eventEdited) {
		this.eventEdited = eventEdited;
	}

	public int getGroupCreated() {
		return groupCreated;
	}

	public void setGroupCreated(int groupCreated) {
		this.groupCreated = groupCreated;
	}

	public int getGroupAdded() {
		return groupAdded;
	}

	public void setGroupAdded(int groupAdded) {
		this.groupAdded = groupAdded;
	}

	public int getGroupDeleted() {
		return groupDeleted;
	}

	public void setGroupDeleted(int groupDeleted) {
		this.groupDeleted = groupDeleted;
	}

	public int getGroupCommented() {
		return groupCommented;
	}

	public void setGroupCommented(int groupCommented) {
		this.groupCommented = groupCommented;
	}

	public int getGroupViewed() {
		return groupViewed;
	}

	public void setGroupViewed(int groupViewed) {
		this.groupViewed = groupViewed;
	}

	public int getGroupRated() {
		return groupRated;
	}

	public void setGroupRated(int groupRated) {
		this.groupRated = groupRated;
	}

	public int getGroupRejected() {
		return groupRejected;
	}

	public void setGroupRejected(int groupRejected) {
		this.groupRejected = groupRejected;
	}

	public int getGroupEdited() {
		return groupEdited;
	}

	public void setGroupEdited(int groupEdited) {
		this.groupEdited = groupEdited;
	}


	public int getPersonCreatedFirst() {
		return personCreatedFirst;
	}

	public void setPersonCreatedFirst(int personCreatedFirst) {
		this.personCreatedFirst = personCreatedFirst;
	}

	public int getPersonCreatedAgain() {
		return personCreatedAgain;
	}

	public void setPersonCreatedAgain(int personCreatedAgain) {
		this.personCreatedAgain = personCreatedAgain;
	}

	public int getPersonEdited() {
		return personEdited;
	}

	public void setPersonEdited(int personEdited) {
		this.personEdited = personEdited;
	}

	public int getPersonDeleted() {
		return personDeleted;
	}

	public void setPersonDeleted(int personDeleted) {
		this.personDeleted = personDeleted;
	}

	public int getPersonComment() {
		return personComment;
	}

	public void setPersonComment(int personComment) {
		this.personComment = personComment;
	}

	public int getPersonCommented() {
		return personCommented;
	}

	public void setPersonCommented(int personCommented) {
		this.personCommented = personCommented;
	}

	public int getPersonView() {
		return personView;
	}

	public void setPersonView(int personView) {
		this.personView = personView;
	}

	public int getPersonViewed() {
		return personViewed;
	}

	public void setPersonViewed(int personViewed) {
		this.personViewed = personViewed;
	}

	public int getPersonFriendRequest() {
		return personFriendRequest;
	}

	public void setPersonFriendRequest(int personFriendRequest) {
		this.personFriendRequest = personFriendRequest;
	}

	public int getPersonFriendRequested() {
		return personFriendRequested;
	}

	public void setPersonFriendRequested(int personFriendRequested) {
		this.personFriendRequested = personFriendRequested;
	}

	public int getPersonFriendAccept() {
		return personFriendAccept;
	}

	public void setPersonFriendAccept(int personFriendAccept) {
		this.personFriendAccept = personFriendAccept;
	}

	public int getPersonFriendReject() {
		return personFriendReject;
	}

	public void setPersonFriendReject(int personFriendReject) {
		this.personFriendReject = personFriendReject;
	}

	public int getPersonFriendRejected() {
		return personFriendRejected;
	}

	public void setPersonFriendRejected(int personFriendRejected) {
		this.personFriendRejected = personFriendRejected;
	}

	public int getPersonFriendDelete() {
		return personFriendDelete;
	}

	public void setPersonFriendDelete(int personFriendDelete) {
		this.personFriendDelete = personFriendDelete;
	}

	public int getPersonFriendDeleted() {
		return personFriendDeleted;
	}

	public void setPersonFriendDeleted(int personFriendDeleted) {
		this.personFriendDeleted = personFriendDeleted;
	}

	public int getExternalaccountCreated() {
		return externalaccountCreated;
	}

	public void setExternalaccountCreated(int externalaccountCreated) {
		this.externalaccountCreated = externalaccountCreated;
	}

	public int getExternalaccountDeleted() {
		return externalaccountDeleted;
	}

	public void setExternalaccountDeleted(int externalaccountDeleted) {
		this.externalaccountDeleted = externalaccountDeleted;
	}

	public int getSharing() {
		return sharing;
	}

	public void setSharing(int sharing) {
		this.sharing = sharing;
	}

	public int getRegular() {
		return regular;
	}

	public void setRegular(int regular) {
		this.regular = regular;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public int getLogout() {
		return logout;
	}

	public void setLogout(int logout) {
		this.logout = logout;
	}

	public int getPostWarned() {
		return postWarned;
	}

	public void setPostWarned(int postWarned) {
		this.postWarned = postWarned;
	}

	public int getLocationWarned() {
		return locationWarned;
	}

	public void setLocationWarned(int locationWarned) {
		this.locationWarned = locationWarned;
	}

	public int getChallengeWarned() {
		return challengeWarned;
	}

	public void setChallengeWarned(int challengeWarned) {
		this.challengeWarned = challengeWarned;
	}

	public int getEventWarned() {
		return eventWarned;
	}

	public void setEventWarned(int eventWarned) {
		this.eventWarned = eventWarned;
	}

	public int getGroupWarned() {
		return groupWarned;
	}

	public void setGroupWarned(int groupWarned) {
		this.groupWarned = groupWarned;
	}

	public int getPersonWarn() {
		return personWarn;
	}

	public void setPersonWarn(int personWarn) {
		this.personWarn = personWarn;
	}

	public int getPersonWarned() {
		return personWarned;
	}

	public void setPersonWarned(int personWarned) {
		this.personWarned = personWarned;
	}

	public int getPersonFriendRequestCanceled() {
		return personFriendRequestCanceled;
	}

	public void setPersonFriendRequestCanceled(int personFriendRequestCanceled) {
		this.personFriendRequestCanceled = personFriendRequestCanceled;
	}

	public int getChatSend() {
		return chatSend;
	}

	public void setChatSend(int chatSend) {
		this.chatSend = chatSend;
	}

	public int getChatReceive() {
		return chatReceive;
	}

	public void setChatReceive(int chatReceive) {
		this.chatReceive = chatReceive;
	}

	public int getChatPersons() {
		return chatPersons;
	}

	public void setChatPersons(int chatPersons) {
		this.chatPersons = chatPersons;
	}

	public int getChallengeAnswerRight() {
		return challengeAnswerRight;
	}

	public void setChallengeAnswerRight(int challengeAnswerRight) {
		this.challengeAnswerRight = challengeAnswerRight;
	}

	public int getChallengeAnswerWrong() {
		return challengeAnswerWrong;
	}

	public void setChallengeAnswerWrong(int challengeAnswerWrong) {
		this.challengeAnswerWrong = challengeAnswerWrong;
	}

	public int getPersonFriendAccepted() {
		return personFriendAccepted;
	}

	public void setPersonFriendAccepted(int personFriendAccepted) {
		this.personFriendAccepted = personFriendAccepted;
	}

	public int getPersonFriendRequestCancel() {
		return personFriendRequestCancel;
	}

	public void setPersonFriendRequestCancel(int personFriendRequestCancel) {
		this.personFriendRequestCancel = personFriendRequestCancel;
	}

	public int getPersonSearch() {
		return personSearch;
	}

	public void setPersonSearch(int personSearch) {
		this.personSearch = personSearch;
	}

	public int getPostSearch() {
		return postSearch;
	}

	public void setPostSearch(int postSearch) {
		this.postSearch = postSearch;
	}

	public int getLocationSearch() {
		return locationSearch;
	}

	public void setLocationSearch(int locationSearch) {
		this.locationSearch = locationSearch;
	}

	public int getChallengeSearch() {
		return challengeSearch;
	}

	public void setChallengeSearch(int challengeSearch) {
		this.challengeSearch = challengeSearch;
	}

	public int getEventSearch() {
		return eventSearch;
	}

	public void setEventSearch(int eventSearch) {
		this.eventSearch = eventSearch;
	}

	public int getGroupSearch() {
		return groupSearch;
	}

	public void setGroupSearch(int groupSearch) {
		this.groupSearch = groupSearch;
	}
	
}
