package mobilehealth.prc.eclipselink;

import java.util.List;

import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.ExternalAccounts;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelateContentLocation;
import mobilehealth.core.domain.RelateContentLocationPK;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonContentPK;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonLocationPK;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.core.domain.RelatePersonPersonPK;
import mobilehealth.core.domain.Tag;

/**
 * @author Bruno de Sousa Monteiro
 * @date 23/07/2013
 */

public interface IData 
{
	//--------------------------------------------------------
	// CONTENT
	//--------------------------------------------------------
	public int getLastIdContent();
	public int getCountContent();

	public boolean insertContent(Content content);
	public boolean removeContent(long id);
	public boolean setContent(Content content);
	public Content getContent(long id);
	public List<Content> getAllContent();
	public List<Content> searchContent(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// Context
	//-----------------------------------------------------------------------------------------------------
	public boolean insertContext(Context context);
	public boolean removeContext(long id);
	public boolean setContext(Context context);
	public Context getContext(long id);
	public List<Context> getAllContext();
	public List<Context> searchContext(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// Device
	//-----------------------------------------------------------------------------------------------------
	
	
	public boolean insertDevice(Device device);
	public boolean removeDevice(long id);
	public boolean setDevice(Device device);
	public Device getDevice(long id);
	public Device getDevice(String imei);
	public List<Device> getAllDevice();
	public List<Device> searchDevice(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// Location
	//-----------------------------------------------------------------------------------------------------
	public int getLastIdLocation();
	public int getCountLocation();
	
	public boolean insertLocation(Location location);
	public boolean removeLocation(long id);
	public boolean setLocation(Location location);
	public Location getLocation(long id);
	public List<Location> getAllLocation();
	public List<Location> searchLocation(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// PERSON
	//-----------------------------------------------------------------------------------------------------
	public boolean login(String email, String passEncripted);
	public int getLastIdPerson();
	public int getCountPerson();

	public boolean insertPerson(Person person);
	public boolean removePerson(long id);
	public boolean setPerson(Person person);
	public Person getPerson(String email);
	public Person getPerson(long id);
	public List<Person> getAllPerson();
	public List<Person> searchPerson(Object param);

	//-----------------------------------------------------------------------------------------------------
	// TAG
	//-----------------------------------------------------------------------------------------------------
	public boolean insertTag(Tag tag);
	public boolean removeTag(long id);
	public boolean setTag(Tag tag);
	public Tag getTag(long id);
	public Tag getTag(String name);
	public List<Tag> getAllTag();
	
	//-----------------------------------------------------------------------------------------------------
	// ExternalAccounts
	//-----------------------------------------------------------------------------------------------------
	public List<ExternalAccounts> getAllExternalAccounts();
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonContent
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelatePersonContent(RelatePersonContent relatePersonContent);
	public boolean removeRelatePersonContent(RelatePersonContentPK relatePersonContentPK);
	public boolean setRelatePersonContent(RelatePersonContent relatePersonContent);
	public RelatePersonContent getRelatePersonContent(RelatePersonContentPK relatePersonContentPK);
	public List<RelatePersonContent> getAllRelatePersonContent();
	public List<RelatePersonContent> searchRelatePersonContent(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonPerson
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelatePersonPerson(RelatePersonPerson relatePersonPerson);
	public boolean removeRelatePersonPerson(RelatePersonPersonPK relatePersonPersonPK);
	public boolean setRelatePersonPerson(RelatePersonPerson relatePersonPerson);
	public RelatePersonPerson getRelatePersonPerson(RelatePersonPersonPK relatePersonPersonPK);
	public List<RelatePersonPerson> getAllRelatePersonPerson();
	public List<RelatePersonPerson> searchRelatePersonPerson(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// RelatePersonLocation
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelatePersonLocation(RelatePersonLocation relatePersonLocation);
	public boolean removeRelatePersonLocation(RelatePersonLocationPK relatePersonLocationPK);
	public boolean setRelatePersonLocation(RelatePersonLocation relatePersonLocation);
	public RelatePersonLocation getRelatePersonLocation(RelatePersonLocationPK relatePersonLocationPK);
	public List<RelatePersonLocation> getAllRelatePersonLocation();
	public List<RelatePersonLocation> searchRelatePersonLocation(Object param);
	
	//-----------------------------------------------------------------------------------------------------
	// RelateContentLocation
	//-----------------------------------------------------------------------------------------------------
	public boolean insertRelateContentLocation(RelateContentLocation relateContentLocation);
	public boolean removeRelateContentLocation(RelateContentLocationPK relateContentLocationPK);
	public boolean setRelateContentLocation(RelateContentLocation relateContentLocation);
	public RelateContentLocation getRelateContentLocation(RelateContentLocationPK relateContentLocationPK);
	public List<RelateContentLocation> getAllRelateContentLocation();
	public List<RelateContentLocation> searchRelateContentLocation(Object param);

}
