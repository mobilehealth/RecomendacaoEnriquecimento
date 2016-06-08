package mobilehealth.core.domain;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mobilehealth.core.dao.GenericDAO;

@Entity
@Table(name="file", schema="public")
@SequenceGenerator(name = "file_id_seq", sequenceName = "file_id_seq", allocationSize = 1)  
public class File
{
	private static final long serialVersionUID = 1L;
	
	// Constantes do atributo: origin
	public static final int ORIGIN_CAMERA = 1;
	public static final int ORIGIN_FILE = 2;
	
	public static final int TYPE_IMAGE = 11;
	public static final int TYPE_VIDEO = 12;
	public static final int TYPE_AUDIO = 13;
	public static final int TYPE_OTHER = 14;
	
	@Id
	//@SequenceGenerator(name = "file_id_seq", sequenceName = "file_id_seq") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_id_seq")  
	@Column(name = "id")
	@OneToMany(cascade = CascadeType.ALL)
	private Integer id;
	
	
	@Column(name = "bytes")
	private int bytes;
	
	
	@Column(name = "data")
	private byte[] data;
	
	
	// Caminho, nome e extensao
	
	@Column(name = "full_name")
	private String fullName;

	
	@Column(name = "height")
	private int height;
	
	
	@Column(name = "origin")
	private int origin;
	
	
	@Column(name = "seconds")
	private int seconds;
	
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "width")
	private int width;
	
	
	
	
	
	
	
	//-----------------------------------------------------------
	// Getters and Setters
	//-----------------------------------------------------------
	public void setData(String fullName) 
	{
		java.io.File file = new java.io.File(fullName);
	   
	    try
	    {
	    	// Create byte array large enough to hold the content of the file.
	    	byte fileContent[] = new byte[(int)file.length()];
	     
	    	this.data = fileContent;

	    	/*
	    	 * 
	    	FileInputStream fin = new FileInputStream(file);
 			// Read content of the file in byte array
			fin.read(fileContent);
			//create string from byte array
			String strFileContent = new String(fileContent);
			System.out.println("File content : ");
			System.out.println(strFileContent);
			*/
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Problema ao gerar vetor de bytes. " + e);
	    }
	}
	
	public void setHeightAndWidth(String fullName, int type)
	{
	    FileInputStream fis;
	    
		try 
		{
			fis = new FileInputStream(fullName);
			
		    if(type == File.TYPE_IMAGE) 
		    {
			    BufferedImage image = ImageIO.read(fis);
				height = image.getHeight();
				width = image.getWidth();
		    }
		    
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}  
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public int getBytes() {
		return bytes;
	}
	
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getOrigin() {
		return origin;
	}
	
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
}
