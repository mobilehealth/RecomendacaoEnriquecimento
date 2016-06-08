package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mobilehealth.core.dao.EntityManagerDAO;


public class EntityManagerController {
	
private EntityManagerDAO entityManagerDAO;
	
	public EntityManagerController() {
		entityManagerDAO = new EntityManagerDAO();
	}
	
	public List<EntityManager> getAllEntityManagers() {
		List<EntityManager> allEntityManagers = new ArrayList<EntityManager>(); 
		allEntityManagers = entityManagerDAO.findAll();
		return allEntityManagers;
	}
}
