package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.TagDAO;
import mobilehealth.core.domain.Tag;

public class TagController
{

	
private TagDAO TagDAO;
	
	public TagController() {
		TagDAO = new TagDAO();
	}
	
	public List<Tag> getAllTags() {
		List<Tag> allTags = new ArrayList<Tag>(); 
		allTags = TagDAO.findAll();
		return allTags;
	}

}
