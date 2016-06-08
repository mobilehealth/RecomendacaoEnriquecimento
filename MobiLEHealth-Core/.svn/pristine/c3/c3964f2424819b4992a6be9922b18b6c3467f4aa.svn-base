package mobilehealth.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Jonathan Darlan
 * @date 19/11/2014
 *
 */

@Entity
@Table(schema="public")
@NamedQueries({
	@NamedQuery(name = "findByName", query = "select u from Domain u where u.domainName = :domainName") 
})
public class Domain {

	@Id
	@SequenceGenerator(name = "domain_seq", sequenceName = "domain_seq", schema="public")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domain_seq")
	private Integer id;

	private String domainName;
	
	private String domainOntologyFile;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainOntologyFile() {
		return domainOntologyFile;
	}

	public void setDomainOntologyFile(String domainOntologyFile) {
		this.domainOntologyFile = domainOntologyFile;
	}
	
}
