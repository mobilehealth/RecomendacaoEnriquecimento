package mobilehealth.sac.domain;

public class DomainData {
	public String label;
	public String iri;
	public int qtdPalavras;
	
	//Construtor �til para o m�todo que recebe o dom�nio e retorna uma lista com <Label, IRI, qtdPalavras>
	public DomainData(String label, String iri, int qtdPalavras){
		this.label = label;
		this.iri = iri;
		this.qtdPalavras = qtdPalavras;
	}
	
	//Construtor �til para o m�todo que recebe o resource e retorna uma lista <Texto, IRI>
	public DomainData(String label, String iri){
		this.label = label;
		this.iri = iri;
		this.qtdPalavras = -1;
	}
	
	
	
}
