package mobilehealth.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerDAO {
	
	private static EntityManagerDAO instance;
	
	private final static String UNIT_NAME = "mobilehealthPU";
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public EntityManagerDAO() {

		entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
				
	}
	
	public static EntityManagerDAO getInstance() {
		
		if(instance == null) {
			instance = new EntityManagerDAO();
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
