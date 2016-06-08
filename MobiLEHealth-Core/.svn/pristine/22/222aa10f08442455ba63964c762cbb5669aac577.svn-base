package mobilehealth.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import mobilehealth.core.dao.GenericDAO;

@Entity
@Table(name="learning", schema="public")
public class Learning extends GenericDAO<Learning>
{	
	public Learning() {
		super(Learning.class);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	
	public static final int DIMEN_PERCEP_SENSORIAL = 11;
	public static final int DIMEN_PERCEP_INTUITIVO = 12;

	public static final int DIMEN_RETEN_VISUAL = 21;
	public static final int DIMEN_RETEN_VERBAL = 22;
	
	public static final int DIMEN_PROCESS_ATIVO = 31;
	public static final int DIMEN_PROCESS_REFLEXIVO = 32;
	
	public static final int DIMEN_ORGANIZ_SEQUENCIAL = 41;
	public static final int DIMEN_ORGANIZ_GLOBAL = 42;
	
	public static final int LEVEL_FUNDAMENTAL_INCOMPLETO = 1;
	public static final int LEVEL_FUNDAMENTAL_COMPLETO = 2;
	public static final int LEVEL_ENSINO_MEDIO_INCOMPLETO = 3;
	public static final int LEVEL_ENSINO_MEDIO_COMPLETO = 4;
	public static final int LEVEL_SUPERIOR_INCOMPLETO = 5;
	public static final int LEVEL_SUPERIOR_COMPLETO = 6;
	public static final int LEVEL_POS_GRADUACAO = 7;
	
	// TODO Antonio, sabes onde conseguir tabela das areas de formacao (formationArea)?
	
	@Id
	@SequenceGenerator(name = "learning_id_seq", sequenceName = "learning_id_seq", allocationSize = 1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "learning_id_seq")  
	@Column(name = "id")
	private Integer id;
	
	// Nivel de escolaridade
	@Column(name = "educational_level")
	private int educationalLevel;
	
	// TODO Pendente, se conseguir tabela, mudar para int (chave estrangeira)
	// Curso de formacao
	@Column(name = "formation_course")
	private String formationCourse; 

	// Grande Area de formacao
	@Column(name = "formation_grande_area")
	private int formationGrandeArea;			

	// Area de formacao
	@Column(name = "formation_area")
	private Integer formationArea;			
	
	// SubArea de formacao
	@Column(name = "formation_subarea")
	private int formationSubarea;	
	
	// Estilo de aprendizagem: Felder-Silvermann (1988 e 2005), e Soloman e Felder (2012) 
	// TODO Luiz estrategia para, com base nos conteudos consumidos, preencher essas dimensoes
	@Column(name = "estilo_dimen_percepcao")
	private int estiloDimenPercepcao;
	
	@Column(name = "estilo_dimen_retencao")
	private int estiloDimenRetencao;
	
	@Column(name = "estilo_dimen_processamento")
	private int estiloDimenProcessamento;
	
	@Column(name = "estilo_dimen_organizacao")
	private int estiloDimenOrganizacao;
	

	//--------------------------------------------------------------------------
	// Getters and Setters
	//---------------------------------------------------------------------------
	public int getEducationalLevel() {
		return educationalLevel;
	}
	public void setEducationalLevel(int educationalLevel) {
		this.educationalLevel = educationalLevel;
	}
	public String getFormationCourse() {
		return formationCourse;
	}
	public void setFormationCourse(String formationCourse) {
		this.formationCourse = formationCourse;
	}
	public int getEstiloDimenPercepcao() {
		return estiloDimenPercepcao;
	}
	public void setEstiloDimenPercepcao(int estiloDimenPercepcao) {
		this.estiloDimenPercepcao = estiloDimenPercepcao;
	}
	public int getEstiloDimenRetencao() {
		return estiloDimenRetencao;
	}
	public void setEstiloDimenRetencao(int estiloDimenRetencao) {
		this.estiloDimenRetencao = estiloDimenRetencao;
	}
	public int getEstiloDimenProcessamento() {
		return estiloDimenProcessamento;
	}
	public void setEstiloDimenProcessamento(int estiloDimenProcessamento) {
		this.estiloDimenProcessamento = estiloDimenProcessamento;
	}
	public int getEstiloDimenOrganizacao() {
		return estiloDimenOrganizacao;
	}
	public void setEstiloDimenOrganizacao(int estiloDimenOrganizacao) {
		this.estiloDimenOrganizacao = estiloDimenOrganizacao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getFormationGrandeArea() {
		return formationGrandeArea;
	}
	
	public void setFormationGrandeArea(int formationGrandeArea) {
		this.formationGrandeArea = formationGrandeArea;
	}

	public Integer getFormationArea() {
	
		return formationArea;
	}
	public void setFormationArea(Integer formationArea) {
		this.formationArea = formationArea;
	}

	public int getFormationSubarea() {
		return formationSubarea;
	}
	public void setFormationSubarea(int formationSubarea) {
		this.formationSubarea = formationSubarea;
	}

}
