package mobilehealth.prc.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mobilehealth.prc.eclipselink.DataEclipseLink;
import mobilehealth.prc.eclipselink.IData;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelateContentTag;
import mobilehealth.core.domain.RelateLocationTag;
import mobilehealth.core.domain.RelatePersonTag;
import mobilehealth.core.domain.Tag;

public class ManagerServerTag 
{
	private static ManagerServerTag instance;
	private IData iData;
	
	
	private ManagerServerTag()
	{
		this.iData = DataEclipseLink.getInstance();
	}
	
    public static ManagerServerTag getInstance()
    {
        if (instance == null) {
            instance = new ManagerServerTag();
        }
        return instance;
    }
        
	public boolean createAndUpdateTags(String text, Person person, Content content, Location location) 
	{
		boolean flagOK = false;
		boolean flagExiste = false;
		
		if(text != null && person != null)
		{
			Pattern PATTERN = Pattern.compile("#(\\w+)");
			Matcher matcher = PATTERN.matcher(text);
			
			while (matcher.find()) // para cada TAG encontrada no comentario
			{
				// Transforma em minuscula
				String tagName = matcher.group(1);
				tagName = tagName.toLowerCase();
				System.out.println(tagName);
			    
				// TAG ja existe no BD?
	    		Tag tag = iData.getTag(tagName);
				if(tag == null) 
				{
					// Adiciona nova TAG no BD
					System.out.println("Adiciona no BD a TAG: " + tagName);
					tag = new Tag();
					tag.setName(tagName);
					tag.setCount(1);
					flagOK = iData.insertTag(tag);
				}
				else 
				{
					// Atualiza contador da TAG
					System.out.println("Atualiza no BD a TAG: " + tagName);
					tag.setCount( tag.getCount() + 1);
					flagOK = iData.setTag(tag);
				}
	    		
				// TAG ja existe na lista de PERSON?
				flagExiste = false;
	    		List<RelatePersonTag> listRelatePersonTag = person.getListRelatePersonTag();
	    		for (RelatePersonTag relatePersonTag : listRelatePersonTag) {
					if(relatePersonTag.getTag().getName().compareTo(tag.getName()) == 0)
					{
		    			// Atualiza
						System.out.println("Atualiza em Relate Person a TAG: " + tagName);
						relatePersonTag.setCountRel( relatePersonTag.getCountRel() + 1);
		    			listRelatePersonTag.set(listRelatePersonTag.indexOf(relatePersonTag), relatePersonTag);
		    			person.setListRelatePersonTag(listRelatePersonTag);
		    			flagExiste = true;
		    			break;
					}
				}
	    		
	    		if(flagExiste == false) 
	    		{
	    			// Adiciona
	    			System.out.println("Adiciona em Relate Person a TAG: " + tagName);
	        		RelatePersonTag relatePersonTag = new RelatePersonTag();
	        		relatePersonTag.setPerson(person);
	        		relatePersonTag.setTag(tag);
	        		relatePersonTag.setCountRel(1);
	        		listRelatePersonTag.add(relatePersonTag);
	        		person.setListRelatePersonTag(listRelatePersonTag);
	    		}
	    		
	    		flagOK = iData.setPerson(person);
	    		
	    		// TAG ja existe na lista de CONTENT?
	    		if(content != null) 
	    		{
		    		flagExiste = false;
		    		List<RelateContentTag> listRelateContentTag = content.getListRelateContentTag();
		    		for (RelateContentTag relateContentTag : listRelateContentTag) {
						if(relateContentTag.getTag().getName().compareTo(tag.getName()) == 0)
						{
			    			// Atualiza
							System.out.println("Atualiza em Relate Content a TAG: " + tagName);
							relateContentTag.setCountRel( relateContentTag.getCountRel() + 1);
							listRelateContentTag.set(listRelateContentTag.indexOf(relateContentTag), relateContentTag);
			    			content.setListRelateContentTag(listRelateContentTag);
			    			flagExiste = true;
			    			break;
						}
					}
		    		
		    		if(flagExiste == false) 
		    		{
		    			// Adiciona
		    			System.out.println("Adiciona em Relate Content a TAG: " + tagName);
		    			RelateContentTag relateContentTag = new RelateContentTag();
		    			relateContentTag.setContent(content);
		    			relateContentTag.setTag(tag);
		    			relateContentTag.setCountRel(1);
		        		listRelateContentTag.add(relateContentTag);
		        		content.setListRelateContentTag(listRelateContentTag);
		    		}
		    		
		    		flagOK = iData.setContent(content);
	    		} // content

	    		// TAG ja existe na lista de LOCATION?
	    		if(location != null) 
	    		{
		    		flagExiste = false;
		    		List<RelateLocationTag> listRelateLocationTag = location.getListRelateLocationTag();
		    		for (RelateLocationTag relateLocationTag : listRelateLocationTag) {
						if(relateLocationTag.getTag().getName().compareTo(tag.getName()) == 0)
						{
			    			// Atualiza
							System.out.println("Atualiza em Relate Location a TAG: " + tagName);
							relateLocationTag.setCountRel( relateLocationTag.getCountRel() + 1);
							listRelateLocationTag.set(listRelateLocationTag.indexOf(relateLocationTag), relateLocationTag);
			    			location.setListRelateLocationTag(listRelateLocationTag);
			    			flagExiste = true;
			    			break;
						}
					}
		    		
		    		if(flagExiste == false) 
		    		{
		    			// Adiciona
		    			System.out.println("Adiciona em Relate Location a TAG: " + tagName);
		    			RelateLocationTag relateLocationTag = new RelateLocationTag();
		    			relateLocationTag.setLocation(location);
		    			relateLocationTag.setTag(tag);
		    			relateLocationTag.setCountRel(1);
		        		listRelateLocationTag.add(relateLocationTag);
		        		location.setListRelateLocationTag(listRelateLocationTag);
		    		}
		    		
		    		flagOK = iData.setLocation(location);
	    		} // location
	    		
			} // while
			
		} // if nulls
		
		return flagOK;
	}

	public boolean createTags(String text) {
		boolean flagOK = false;
		
		if(text != null)
		{
			// Transforma em minuscula				
			text = text.toLowerCase();
			
			// TAG ja existe no BD?
			Tag tag = iData.getTag(text);
			if(tag == null) 
			{
				// Adiciona nova TAG no BD
				//System.out.println("Adiciona no BD a TAG: " + tagName);
				tag = new Tag();
				tag.setName(text);
				tag.setCount(1);
				flagOK = iData.insertTag(tag);
			}
		}	
		return flagOK;
	}

	public List<Tag> getAllTag() {
		
		return iData.getAllTag();
	}
	
}