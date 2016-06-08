package mobilehealth.core.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Jonathan Darlan
 * @date 01/08/2014
 * 
 */

@Entity
@Table(schema="public")
public class Content {

	// Indentifers of content type  
	public static final int TYPE_POST 		= 0;
	public static final int TYPE_EVENT 		= 1;
	public static final int TYPE_GROUP 		= 2;
	public static final int TYPE_CHALLENGE 	= 3;

	// Visibility of content. Defined by author
	public static final int VIS_PUBLIC 		= 0;
	public static final int VIS_FRIENDS 	= 1;
	public static final int VIS_OWNER 		= 2;

	// Subtype of POST type 
	public static final int SUBTYPE_POST_TEXT	= 0;
	public static final int SUBTYPE_POST_IMAGE	= 1;
	public static final int SUBTYPE_POST_VIDEO	= 2;
	public static final int SUBTYPE_POST_PAGE	= 3;
	public static final int SUBTYPE_POST_FILE	= 4;

	// GROUP Visibility
	public static final int VIS_GROUP_PUBLIC = 0;
	public static final int VIS_GROUP_CLOSED = 1;
		
	private float P1;
	private float P2;
	private float P3;

	
	
	
	// Tipo do conteudo
		@Column(name = "type")
		@Expose(serialize=true, deserialize=true)
		private int type;					
		
		// Subtipo do conteudo especifico, referente ao seu tipo
		@Column(name = "subtype")
		@Expose(serialize=true, deserialize=true)
		private int subtype;
		
		// Visibilidade da existencia de um conteudo criado
		@Column(name = "visibility")
		@Expose(serialize=true, deserialize=true)
		private int visibility;
		
		// Titulo
		@Column(name = "title")
		@Expose(serialize=true, deserialize=true)
		private String title; 				
		
		// Autor do conteúdo, quando possivel
		@Column(name = "author")
		@Expose(serialize=true, deserialize=true)
		private String author;
		
		// Descricao (conteudo propriamente dito quando Content for apenas texto)
		@Column(name = "description")
		@Expose(serialize=true, deserialize=true)
		private String description; 		
		
		// Data de criacao do conteudo por seu autor
		@Temporal(TemporalType.DATE)
		@Column(name = "date_creation")
		@Expose(serialize=true, deserialize=true)
		private Calendar dateCreation; 		

		// Data de criacao do conteudo por seu autor
		@Temporal(TemporalType.DATE)
		@Column(name = "date_add")
		@Expose(serialize=true, deserialize=true)
		private Calendar dateAdd; 		

		// Link externo (caso nao seja apenas texto)
		@Column(name = "url_online")
		@Expose(serialize=true, deserialize=true)
		private String urlOnline; 		
		
		// Tamanho de um arquivo online
		@Column(name = "bytes_online")
		@Expose(serialize=true, deserialize=true)
		private int bytesOnline;
		
		// Duracao de um video online
		@Column(name = "seconds_online")
		@Expose(serialize=true, deserialize=true)
		private int secondsOnline;
		
		// Avaliacao do conteudo, que eh a media de todas as avaliacoes pessoais de todos os users
		@Column(name = "rating")
		@Expose(serialize=true, deserialize=true)
		private float rating;
		
		// Taxa de aceitacao
		@Column(name = "rate_acceptance")
		@Expose(serialize=true, deserialize=true)
		private float rateAcceptance;		
		
		// Media das avaliações colaborativas ponderadas
		@Column(name = "rate_colab_ponder")
		@Expose(serialize=true, deserialize=true)
		private float rateColabPonder;	

		//Variáveis utilizadas para simular o retorno da Semelhança Semântica
		//Semelhança Semântica com Diabetes
		@Column(name = "SS1")
		@Expose(serialize=true, deserialize=true)
		private float SS1;
		
		//Semelhança Semântica com ELA
		@Column(name = "SS2")
		@Expose(serialize=true, deserialize=true)
		private float SS2;
/*
		//Semelhança Semântica com Direito
		@Column(name = "SS3")
		@Expose(serialize=true, deserialize=true)
		private float SS3;
		
		//Semelhança Semântica com Contabilidade
		@Column(name = "SS4")
		@Expose(serialize=true, deserialize=true)
		private float SS4;
*/
			
		//------------------------------------------------------------------------------------
		// Especificos
		//------------------------------------------------------------------------------------
		
		// GROUP
		// visibilidade do interior do grupo: publico ou fechado
		@Column(name = "visibility_group")
		@Expose(serialize=true, deserialize=true)
		private int visibilityGroup;
		
		
		
		// EVENT
		@Temporal(TemporalType.DATE)
		@Column(name = "date_event")
		@Expose(serialize=true, deserialize=true)
		private Calendar dateEvent;
		
		/*
		// CHALLENGE
		@OneToMany(mappedBy="content", cascade=CascadeType.ALL)
		@Expose(serialize=true, deserialize=false)
		private List<Alternatives> listAlternatives = new ArrayList<Alternatives>();
		*/
		//------------------------------------------------------------------------------------
		// 1:1
		//------------------------------------------------------------------------------------	
		// Arquivo relacionada ao conteudo
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name="id_file")
		@Expose(serialize=true, deserialize=false)
		private File file;	
		
		// Histograma acumulado de ACEITES e REJEICOES realizadas nesse conteudo, por todas as pessoas
		//@OneToOne(cascade = CascadeType.ALL)		
		@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
		@JoinColumn(name="id_frequency")
		@Expose(serialize=true, deserialize=false)
		private Frequency frequency;	
		
		//------------------------------------------------------------------------------------
		// N:M com atributos
		//------------------------------------------------------------------------------------
		// Lista de RELAÇÕES COM PESSOAS
		//@OneToMany(mappedBy = "content", cascade=CascadeType.PERSIST)
		@OneToMany(mappedBy = "content", cascade=CascadeType.ALL)
		@Expose(serialize=true, deserialize=false)
	    private List<RelatePersonContent> listRelatePersonContent = new ArrayList<RelatePersonContent>();

		// Lista de RELAÇÕES COM lOCAIS
		//@OneToMany(mappedBy = "content", cascade=CascadeType.PERSIST)
		@OneToMany(mappedBy = "content", cascade=CascadeType.ALL)
		@Expose(serialize=true, deserialize=false)
	    private List<RelateContentLocation> listRelateContentLocation = new ArrayList<RelateContentLocation>();
		
		// Lista de RELAÇÕES COM TAGS
		// Lista de Tags que descrevem o conteúdo
	    @OneToMany(mappedBy="content", cascade=CascadeType.ALL)
	    @Expose(serialize=true, deserialize=false)
	    private List<RelateContentTag> listRelateContentTag = new ArrayList<RelateContentTag>();
	
		/*
		 * Setters and Getters
		 */
	
	    public int getId() {
			return id;
		}
	    
		public void setId(int id) {
			this.id = id;
		}

				
		public List<RelateContentTag> getListRelateContentTag() {
			return listRelateContentTag;
		}

		public void setListRelateContentTag(List<RelateContentTag> listRelateContentTag) {
			this.listRelateContentTag = listRelateContentTag;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}
		
		public Frequency getFrequency() {
			return frequency;
		}

		public void setFrequency(Frequency frequency) {
			this.frequency = frequency;
		}
		
		public List<RelateContentLocation> getListRelateContentLocation() {
			return listRelateContentLocation;
		}
		
		public void setListRelateContentLocation(ArrayList<RelateContentLocation> listRelateContentLocation) {
			this.listRelateContentLocation = listRelateContentLocation;
		}
		
		public void setListRelateContentLocation(List<RelateContentLocation> listRelateContentLocation) {
			this.listRelateContentLocation = listRelateContentLocation;
		}
		
		public float getRateAcceptance() {
			return rateAcceptance;
		}

		public void setRateAcceptance(float rateAcceptance) {
			this.rateAcceptance = rateAcceptance;
		}
		
		public float getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}
		
		public float getRateColabPonder() {
			return rateColabPonder;
		}

		public void setRateColabPonder(float rateColabPonder) {
			this.rateColabPonder = rateColabPonder;
		}
		
		public List<RelatePersonContent> getListRelatePersonContent() {
			return listRelatePersonContent;
		}

		public void setListRelatePersonContent(ArrayList<RelatePersonContent> listRelatePersonContent) {
			this.listRelatePersonContent = listRelatePersonContent;
		}
		
		public int getAge()	
		{
		    Calendar calendar = Calendar.getInstance();
		    
		    if(dateCreation == null){ // Caso a data esteja vazia no banco - Jerffeson
		    	//JOptionPane.showConfirmDialog(null, "retornando 0");
		    	return 0;
		    } else {
		    
			    int idade = calendar.get(Calendar.YEAR) - dateCreation.get(Calendar.YEAR);
			    
			    if (calendar.get(Calendar.MONTH) < dateCreation.get(Calendar.MONTH)) {
			        idade--;
			    } 
			    else {
			    	if (calendar.get(Calendar.MONTH) == dateCreation.get(Calendar.MONTH)) {
				        if (calendar.get(Calendar.DAY_OF_MONTH) < dateCreation.get(Calendar.DAY_OF_MONTH)) {
				            idade--;
				        }
			    	}
			    }
			    return idade;
		    }
		}
		
		
		
		
		
		public float getP1() {
			return P1;
		}

		public void setP1(float p1) {
			this.P1 = p1;
		}

		public float getP2() {
			return P2;
		}

		public void setP2(float p2) {
			this.P2 = p2;
		}

		public float getP3() {
			return P3;
		}

		public void setP3(float p3) {
			this.P3 = p3;
		}

		public float getSS1() {
			return SS1;
		}

		public void setSS1(float ss1) {
			this.SS1 = ss1;
		}

		public float getSS2() {
			return SS2;
		}

		public void setSS2(float ss2) {
			this.SS2 = ss2;
		}
/*
		public float getSS3() {
			return SS3;
		}

		public void setSS3(float ss3) {
			this.SS3 = ss3;
		}
		public float getSS4() {
			return SS4;
		}

		public void setSS4(float ss4) {
			this.SS4 = ss4;
		}
	*/		
	
	@Id
	@SequenceGenerator(name = "content_seq", sequenceName = "content_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "content_seq")
	private Integer id;
	
	// Average of all rating of all users (May be calculate?)
	private float averageRating;
	
	// avarage?!
	private float acceptanceRate;
	
	/*
	 * Setters and Getters
	 */


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSubtype() {
		return subtype;
	}

	public void setSubtype(int subtype) {
		this.subtype = subtype;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Calendar dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getUrlOnline() {
		return urlOnline;
	}

	public void setUrlOnline(String urlOnline) {
		this.urlOnline = urlOnline;
	}

	public int getBytesOnline() {
		return bytesOnline;
	}

	public void setBytesOnline(int bytesOnline) {
		this.bytesOnline = bytesOnline;
	}

	public int getSecondsOnline() {
		return secondsOnline;
	}

	public void setSecondsOnline(int secondsOnline) {
		this.secondsOnline = secondsOnline;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public float getAcceptanceRate() {
		return acceptanceRate;
	}

	public void setAcceptanceRate(float acceptanceRate) {
		this.acceptanceRate = acceptanceRate;
	}

	public int getVisibilityGroup() {
		return visibilityGroup;
	}

	public void setVisibilityGroup(int visibilityGroup) {
		this.visibilityGroup = visibilityGroup;
	}

	public Calendar getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Calendar dateEvent) {
		this.dateEvent = dateEvent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(acceptanceRate);
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + Float.floatToIntBits(averageRating);
		result = prime * result + bytesOnline;
		result = prime * result + ((dateAdd == null) ? 0 : dateAdd.hashCode());
		result = prime * result
				+ ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result
				+ ((dateEvent == null) ? 0 : dateEvent.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = (int) (prime * result + id);
		result = prime * result + secondsOnline;
		result = prime * result + subtype;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + type;
		result = prime * result
				+ ((urlOnline == null) ? 0 : urlOnline.hashCode());
		result = prime * result + visibility;
		result = prime * result + visibilityGroup;
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (Float.floatToIntBits(acceptanceRate) != Float
				.floatToIntBits(other.acceptanceRate))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (Float.floatToIntBits(averageRating) != Float
				.floatToIntBits(other.averageRating))
			return false;
		if (bytesOnline != other.bytesOnline)
			return false;
		if (dateAdd == null) {
			if (other.dateAdd != null)
				return false;
		} else if (!dateAdd.equals(other.dateAdd))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateEvent == null) {
			if (other.dateEvent != null)
				return false;
		} else if (!dateEvent.equals(other.dateEvent))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (secondsOnline != other.secondsOnline)
			return false;
		if (subtype != other.subtype)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (urlOnline == null) {
			if (other.urlOnline != null)
				return false;
		} else if (!urlOnline.equals(other.urlOnline))
			return false;
		if (visibility != other.visibility)
			return false;
		if (visibilityGroup != other.visibilityGroup)
			return false;
		return true;
	}
	
}
