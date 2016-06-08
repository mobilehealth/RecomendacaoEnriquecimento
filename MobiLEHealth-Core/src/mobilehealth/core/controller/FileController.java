package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.FileDAO;
import mobilehealth.core.domain.File;

public class FileController
{
	
	

	private FileDAO fileDAO;
	
	public FileController() {
		fileDAO = new FileDAO();
	}
	
	public List<File> getAllFiles() {
		List<File> allFiles = new ArrayList<File>(); 
		allFiles = fileDAO.findAll();
		return allFiles;
	}
	
}
