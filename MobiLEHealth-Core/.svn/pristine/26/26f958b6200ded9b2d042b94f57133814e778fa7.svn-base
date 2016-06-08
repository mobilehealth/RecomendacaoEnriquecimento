package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.LocationDAO;
import mobilehealth.core.domain.Location;

public class LocationController{
	

	private LocationDAO locationDAO;
	
	public LocationController() {
		locationDAO = new LocationDAO();
	}
	
	public List<Location> getAllLocations() {
		List<Location> allLocations = new ArrayList<Location>(); 
		allLocations = locationDAO.findAll();
		return allLocations;
	}
	
}
