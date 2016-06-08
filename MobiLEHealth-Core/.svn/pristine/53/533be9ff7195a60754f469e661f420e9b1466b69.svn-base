package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import mobilehealth.core.dao.GenericDAO;
import mobilehealth.core.domain.Person;

@Entity
@Table(name="externalaccounts", schema="public")
public class ExternalAccounts extends GenericDAO<ExternalAccounts>{
	
	private static final long serialVersionUID = 1L;

	// TODO Luiz, quais atributos sao requeridos pelo OAUTH?
	
	
	public ExternalAccounts() {
		super(ExternalAccounts.class);
	}
	
	@Id
	@SequenceGenerator(name = "externalaccounts_id_seq", sequenceName = "externalaccounts_id_seq", allocationSize = 1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "externalaccounts_id_seq")  
	@Column(name = "id")
	@Expose(serialize=true)
	private Integer id;
	
	@Column(name = "service_name")
	@Expose(serialize=true)
	private String serviceName;
	
	// TODO Pendencia Bruno ADD no BD
	@Column(name = "url")
	@Expose(serialize=true)
	private String url;
	
	// TODO Pendencia Bruno ADD no BD
	@Column(name = "scope")
	@Expose(serialize=true)
	private String scope;
	
	@Column(name = "login")
	@Expose(serialize=true)
	private String login;
	
	@Column(name = "token")
	@Expose(serialize=true)
	private String token;
	
	//------------------------------------------------------------------------
	// N:1
	//------------------------------------------------------------------------
	@ManyToOne
	@JoinColumn(name="id_person")
	private Person person;
	
	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "ExternalAccounts [id=" + id + ", serviceName=" + serviceName
				+ ", url=" + url + ", scope=" + scope + ", login=" + login
				+ ", token=" + token + ", person=" + person + "]";
	}
	
}
