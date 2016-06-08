package mobilehealth.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mobilehealth.core.domain.Context;

public class EntityManagerDAO extends GenericDAO<EntityManager> {
	
	private static EntityManagerDAO instance;
	
	private final static String UNIT_NAME = "mobilehealthPU";
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	

	public EntityManagerDAO() {
		super(EntityManager.class);
		entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
				
	}
	

	public EntityManagerDAO(int a) {
		//super(EntityManager.class);
		entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
				
	}
	
	
	public static EntityManagerDAO getInstance() {
		
		if(instance == null) {
			instance = new EntityManagerDAO(1);
		}
		
		return instance;
	}
	
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
