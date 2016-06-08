package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.ContentDAO;
import mobilehealth.core.domain.Content;

public class ContentController {
	
	private ContentDAO contentDAO;
	
	public ContentController() {
		contentDAO = new ContentDAO();
	}
	
	public List<Content> getAllContents() {
		List<Content> allContents = new ArrayList<Content>(); 
		allContents = contentDAO.findAll();
		return allContents;
	}
}
