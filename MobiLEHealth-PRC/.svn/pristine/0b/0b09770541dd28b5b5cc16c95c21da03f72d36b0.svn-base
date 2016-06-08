package mobilehealth.prc.eclipselink;

import java.util.List;

import mobilehealth.core.dao.ContentDAO;
import mobilehealth.core.dao.ContextDAO;
import mobilehealth.core.dao.DeviceDAO;
import mobilehealth.core.dao.ExternalAccountsDAO;
import mobilehealth.core.dao.LocationDAO;
import mobilehealth.core.dao.PersonDAO;
import mobilehealth.core.dao.RelateContentLocationDAO;
import mobilehealth.core.dao.RelatePersonContentDAO;
import mobilehealth.core.dao.RelatePersonLocationDAO;
import mobilehealth.core.dao.RelatePersonPersonDAO;
import mobilehealth.core.dao.TagDAO;
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

public class DataEclipseLink implements IData
{
    private static DataEclipseLink instance;
    
    private ContentDAO contentDAO = new ContentDAO();
    private ContextDAO contextDAO = new ContextDAO();
    private DeviceDAO deviceDAO = new DeviceDAO();
    private LocationDAO locationDAO = new LocationDAO();
    private PersonDAO personDAO = new PersonDAO();
    private ExternalAccountsDAO externalAccountsDAO = new ExternalAccountsDAO();
    private RelatePersonContentDAO relatePersonContentDAO = new RelatePersonContentDAO();
    private RelatePersonPersonDAO relatePersonPersonDAO = new RelatePersonPersonDAO();
    private RelatePersonLocationDAO relatePersonLocationDAO = new RelatePersonLocationDAO();
    private RelateContentLocationDAO relateContentLocationDAO = new RelateContentLocationDAO();
    private TagDAO tagDAO = new TagDAO();

    
   	public DataEclipseLink() 
   	{
   	}
   	
       public static DataEclipseLink getInstance()
       {
           if (instance == null) 
           {
               instance = new DataEclipseLink();
           }
           return instance;
       }
   	
   	//-----------------------------------------------------------------------------------------------------
   	// PERSON
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean login(String email, String passEncripted) 
   	{  
   		boolean flagLogin = false;
   		Person person = this.getPerson(email);
   		
   		// Compara as duas senhas criptografadas
   		if(person != null) 
   		{
   			if(person.getPassword().compareToIgnoreCase(passEncripted) == 0 ) {
   				flagLogin = true;
   			}
   		}
   		return flagLogin;
   	}
   	
   	@Override
   	public int getLastIdPerson() 
   	{ 
   		return personDAO.getLastId();
   	}
   	
   	@Override
   	public int getCountPerson() 
   	{
   		return personDAO.getCount();
   	}
   	
   	@Override
   	public boolean insertPerson(Person person) 
   	{
   		boolean flagOK = false;
        try {
 
        	personDAO.save(person);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
        
   	}
   	
   	@Override
   	public boolean removePerson(long id) 
   	{
   		return personDAO.removeById(id);
   	}
   	
   	@Override
   	public boolean setPerson(Person person) 
   	{
   		boolean flagOK = false;
        try {
 
        	personDAO.update(person);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   	}
   	
   	@Override
   	public Person getPerson(long id) 
   	{
   		return personDAO.getById(id);
   	}
   	
   	@Override
   	public Person getPerson(String email) 
   	{
   		Person person = new Person();
   		person.setEmail(email);
   		return personDAO.findOneByExample(person);
   	}
   	
   	@Override
   	public List<Person> searchPerson(Object param) 
   	{
   		Person person = new Person();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("String") == 0)
   		{
   			String paramStr = (String) param;
   			person.setEmail(paramStr);
   			person.setNameFirst(paramStr);
   			person.setNameLast(paramStr);
   			person.setDisease(paramStr);
   			person.setDescription(paramStr);
   			if( paramStr.contains("masculino") || paramStr.contains("homem") ) {
   				person.setGender(Person.GENRE_MALE);
   			}
   			if(paramStr.contains("feminino") || paramStr.contains("mulher") ) {
   				person.setGender(Person.GENRE_FEMALE);
   			}			
   		}
   	
   		return personDAO.findByExample(person);
   	}
   	
   	@Override
   	public List<Person> getAllPerson() 
   	{
   		return personDAO.findAll();
   	}
   	
   	//-----------------------------------------------------------------------------------------------------
   	// CONTENT
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public int getLastIdContent() 
   	{
   		return contentDAO.getLastId();
   	}
   	
   	@Override
   	public int getCountContent() 
   	{
   		return contentDAO.getCount();
   	}
   	
   	@Override
   	public boolean insertContent(Content content) 
   	{
   		
   		boolean flagOK = false;
        try {
 
        	contentDAO.save(content);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   		
   	}
   	
   	@Override
   	public boolean removeContent(long id) 
   	{
   		return contentDAO.removeById(id);
   	}
   	
   	@Override
   	public boolean setContent(Content content) 
   	{
   		
   		boolean flagOK = false;
        try {
 
        	contentDAO.update(content);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   		
   	}
   	
   	@Override
   	public Content getContent(long id) 
   	{
           return contentDAO.getById(id);
   	}

   	@Override
   	public List<Content> searchContent(Object param) 
   	{
   		Content content = new Content();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("String") == 0)
   		{
   			String paramStr = (String) param;
   			content.setDescription(paramStr);
   			content.setTitle(paramStr);
   		}
   		
   		return contentDAO.findByExample(content);
   	}
   	
   	@Override
   	public List<Content> getAllContent() 
   	{
   		return contentDAO.findAll();
   	}
   	
   	//-----------------------------------------------------------------------------------------------------
   	// TAG
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertTag(Tag tag) 
   	{	
   		
   		boolean flagOK = false;
        try {
 
        	tagDAO.save(tag);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}
   	
   	@Override
   	public boolean removeTag(long id) 
   	{
   		return tagDAO.removeById(id);
   	}
   	
   	@Override
   	public boolean setTag(Tag tag) 
   	{	
   		
   		boolean flagOK = false;
        try {
 
        	tagDAO.update(tag);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}
   	
   	@Override
   	public Tag getTag(long id) 
   	{
           return tagDAO.getById(id);
   	}
   	
   	@Override
   	public Tag getTag(String name) 
   	{
   		Tag tag = new Tag();
   		tag.setName(name);
   		return tagDAO.findOneByExample(tag);
   	}
   	
   	@Override
   	public List<Tag> getAllTag() 
   	{
   		return tagDAO.findAll();
   	}

   	//-----------------------------------------------------------------------------------------------------
   	// Context
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertContext(Context context) 
   	{	
   		
   		boolean flagOK = false;
        try {
 
        	contextDAO.save(context);	
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   	}

   	@Override
   	public boolean removeContext(long id) 
   	{
   		return contextDAO.removeById(id);	
   	}

   	@Override
   	public boolean setContext(Context context) 
   	{	
   		boolean flagOK = false;
        try {
 
        	contextDAO.update(context);	
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   	}

   	@Override
   	public Context getContext(long id) 
   	{
   		return contextDAO.getById(id);
   	}

   	@Override
   	public List<Context> getAllContext() 
   	{
   		return contextDAO.findAll();
   	}

   	@Override
   	public List<Context> searchContext(Object param) 
   	{
   		Context context = new Context(Context.TYPE_REGULAR);
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("Integer") == 0)
   		{
   			int paramInt = (int) param;
   			context.setType(paramInt);
   		}

   		return contextDAO.findByExample(context);
   	}

   	//-----------------------------------------------------------------------------------------------------
   	// Device
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertDevice(Device device) 
   	{
   		
   		boolean flagOK = false;
        try {
 
        	deviceDAO.save(device);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   	}

   	@Override
   	public boolean removeDevice(long id) 
   	{
   		return deviceDAO.removeById(id);	
   	}

   	@Override
   	public boolean setDevice(Device device) 
   	{
   		
   		boolean flagOK = false;
        try {
 
        	deviceDAO.update(device);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
   	}

   	@Override
   	public Device getDevice(long id) 
   	{
   		return deviceDAO.getById(id);
   	}

   	@Override
   	public Device getDevice(String imei) 
   	{
   		Device device = new Device();
   		device.setImei(imei);
   		return deviceDAO.findOneByExample(device);
   	}
   	
   	@Override
   	public List<Device> getAllDevice() 
   	{
   		return deviceDAO.findAll();
   	}

   	@Override
   	public List<Device> searchDevice(Object param) 
   	{
   		Device device = new Device();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("String") == 0)
   		{
   			String paramStr = (String) param;
   			device.setSoVersion(paramStr);
   		}
   		
   		return deviceDAO.findByExample(device);
   	}

   	//-----------------------------------------------------------------------------------------------------
   	// Location
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertLocation(Location location) {
   		
   		boolean flagOK = false;
        try {
 
        	locationDAO.save(location);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
 
   	}

   	@Override
   	public boolean removeLocation(long id) {
   		return locationDAO.removeById(id);
   	}

   	@Override
   	public boolean setLocation(Location location) {
   		
   		boolean flagOK = false;
        try {
 
        	locationDAO.update(location);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public Location getLocation(long id) {
   		return locationDAO.getById(id);
   	}

   	@Override
   	public List<Location> getAllLocation() {
   		return locationDAO.findAll();
   	}

   	@Override
   	public List<Location> searchLocation(Object param) 
   	{
   		Location location = new Location();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("String") == 0)
   		{
   			String paramStr = (String) param;
   			location.setStreetName(paramStr);
   			location.setTitle(paramStr);
   			location.setState(paramStr);
   			location.setNeighborhood(paramStr);
   			location.setCity(paramStr);
   			location.setCountry(paramStr);
   			location.setDescription(paramStr);
   		}
   		
   		return locationDAO.findByExample(location);
   	}

   	@Override
   	public int getLastIdLocation() {
   		return locationDAO.getLastId();
   	}

   	@Override
   	public int getCountLocation() {
   		return locationDAO.getCount();
   	}
   	
   	//-----------------------------------------------------------------------------------------------------
   	// ExternalAccounts
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public List<ExternalAccounts> getAllExternalAccounts() {
   		return externalAccountsDAO.findAll();
   	}
   	
   	//-----------------------------------------------------------------------------------------------------
   	// RelatePersonContent
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertRelatePersonContent(RelatePersonContent relatePersonContent) 
   	{
   		boolean flagOK = false;
        try {       	
        	
        	relatePersonContentDAO.save(relatePersonContent);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public boolean removeRelatePersonContent(RelatePersonContentPK relatePersonContentPK) 
   	{
   		return relatePersonContentDAO.removeById(relatePersonContentPK);
   	}

   	@Override
   	public boolean setRelatePersonContent(RelatePersonContent relatePersonContent) 
   	{
   		
   		boolean flagOK = false;
        try {
 
        	relatePersonContentDAO.update(relatePersonContent);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public RelatePersonContent getRelatePersonContent(RelatePersonContentPK relatePersonContentPK) 
   	{
   		return relatePersonContentDAO.getById(relatePersonContentPK);
   	}

   	@Override
   	public List<RelatePersonContent> getAllRelatePersonContent() 
   	{
   		return relatePersonContentDAO.findAll();
   	}

   	@Override
   	public List<RelatePersonContent> searchRelatePersonContent(Object param) 
   	{
   		// TODO PENDENCIA: melhorar todos os searches
   		
   		RelatePersonContent relatePersonContent = new RelatePersonContent();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("Integer") == 0)
   		{
   			int paramInt = (int) param;
   			relatePersonContent.setStatus(paramInt);
   		}
   		
   		return relatePersonContentDAO.findByExample(relatePersonContent);
   	}

   	//-----------------------------------------------------------------------------------------------------
   	// RelatePersonPerson
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertRelatePersonPerson(RelatePersonPerson relatePersonPerson) 
   	{
   		boolean flagOK = false;
        try {
 
        	relatePersonPersonDAO.save(relatePersonPerson);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;
	}

   	@Override
   	public boolean removeRelatePersonPerson(RelatePersonPersonPK relatePersonPersonPK) 
   	{
   		return relatePersonPersonDAO.removeById(relatePersonPersonPK);
   	}

   	@Override
   	public boolean setRelatePersonPerson(RelatePersonPerson relatePersonPerson) 
   	{
   		boolean flagOK = false;
        try {
 
        	relatePersonPersonDAO.update(relatePersonPerson);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public RelatePersonPerson getRelatePersonPerson(RelatePersonPersonPK relatePersonPersonPK) 
   	{
   		return relatePersonPersonDAO.getById(relatePersonPersonPK);
   	}

   	@Override
   	public List<RelatePersonPerson> getAllRelatePersonPerson() 
   	{
   		return relatePersonPersonDAO.findAll();
   	}

   	@Override
   	public List<RelatePersonPerson> searchRelatePersonPerson(Object param) 
   	{
   		RelatePersonPerson relatePersonPerson = new RelatePersonPerson();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("Integer") == 0)
   		{
   			int paramInt = (int) param;
   			relatePersonPerson.setStatus(paramInt);
   		}
   		
   		return relatePersonPersonDAO.findByExample(relatePersonPerson);
   	}

   	//-----------------------------------------------------------------------------------------------------
   	// RelatePersonLocation
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertRelatePersonLocation(RelatePersonLocation relatePersonLocation) {
   		
   		boolean flagOK = false;
        try {
 
        	relatePersonLocationDAO.save(relatePersonLocation);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public boolean removeRelatePersonLocation(RelatePersonLocationPK relatePersonLocationPK) {
   		return relatePersonLocationDAO.removeById(relatePersonLocationPK);
   	}

   	@Override
   	public boolean setRelatePersonLocation(RelatePersonLocation relatePersonLocation) {
   		
   		boolean flagOK = false;
        try {
 
        	relatePersonLocationDAO.update(relatePersonLocation);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public RelatePersonLocation getRelatePersonLocation(RelatePersonLocationPK relatePersonLocationPK) {
   		return relatePersonLocationDAO.getById(relatePersonLocationPK);
   	}

   	@Override
   	public List<RelatePersonLocation> getAllRelatePersonLocation() {
   		return relatePersonLocationDAO.findAll();
   	}

   	@Override
   	public List<RelatePersonLocation> searchRelatePersonLocation(Object param) 
   	{
   		RelatePersonLocation relatePersonLocation = new RelatePersonLocation();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("Integer") == 0)
   		{
   			int paramInt = (int) param;
   			relatePersonLocation.setStatus(paramInt);
   		}
   		
   		return relatePersonLocationDAO.findByExample(relatePersonLocation);
   	}

   	//-----------------------------------------------------------------------------------------------------
   	// RelateContentLocation
   	//-----------------------------------------------------------------------------------------------------
   	@Override
   	public boolean insertRelateContentLocation(RelateContentLocation relateContentLocation) {
   		
   		boolean flagOK = false;
        try {
 
        	 relateContentLocationDAO.save(relateContentLocation);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public boolean removeRelateContentLocation(RelateContentLocationPK relateContentLocationPK) {
   		return relateContentLocationDAO.removeById(relateContentLocationPK);
   	}

   	@Override
   	public boolean setRelateContentLocation(RelateContentLocation relateContentLocation) {
   		
   		boolean flagOK = false;
        try {
 
        	relateContentLocationDAO.update(relateContentLocation);
        	flagOK = true;
        	
        } 
        catch (Exception e) 
        {
        }
        
		return flagOK;

   	}

   	@Override
   	public RelateContentLocation getRelateContentLocation(RelateContentLocationPK relateContentLocationPK) {
   		return relateContentLocationDAO.getById(relateContentLocationPK);
   	}

   	@Override
   	public List<RelateContentLocation> getAllRelateContentLocation() {
   		return relateContentLocationDAO.findAll();
   	}

   	@Override
   	public List<RelateContentLocation> searchRelateContentLocation(Object param) 
   	{
   		RelateContentLocation relateContentLocation = new RelateContentLocation();
   		String str = "";
   		str = param.getClass().getSimpleName();
   		
   		if( str.compareToIgnoreCase("Integer") == 0)
   		{
   			int paramInt = (int) param;
   			relateContentLocation.setStatus(paramInt);
   		}
   		
   		return relateContentLocationDAO.findByExample(relateContentLocation);
   	}  	
   	
}
