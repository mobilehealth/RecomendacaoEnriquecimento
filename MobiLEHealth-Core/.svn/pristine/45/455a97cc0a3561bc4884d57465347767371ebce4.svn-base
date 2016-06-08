package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.DeviceDAO;
import mobilehealth.core.domain.Device;

public class DeviceController
{

	
	private DeviceDAO deviceDAO;
	
	public DeviceController() {
		deviceDAO = new DeviceDAO();
	}
	
	public List<Device> getAllDevices() {
		List<Device> allDevices = new ArrayList<Device>();
		allDevices = deviceDAO.findAll();
		return allDevices;
	}

}
