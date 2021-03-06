package br.edu.unibratec.psc.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.PersistentObjectException;

import br.edu.unibratec.psc.model.entity.EntityInterface;

public abstract class AbstractDAO<T extends EntityInterface> implements InterfaceDAO<T>
{
	
	private Class<T> aEntityClass;
	
	public void defineEntityClass(Class<T> pEntityClass) {
		this.aEntityClass = pEntityClass;
	}
	
	public Class<T> getEntityClass() {
		return this.aEntityClass;
	}
	
	public EntityManager getEntityManager() {
		return UtilJPA.getEntityManager();
	}
	
	/*
	 * Method to Insert/Create a new Registry in the Database
	 */
	public void insert(T pObject) {
		insert(pObject, UtilJPA.getEntityManager(), true);
	}
	
	public void insert(T pObject, EntityManager pEntityManager, boolean pCloseEntityManager) {
		try {
			if ( pObject == null ) {
				throw new ObjetoNuloPersistenciaException();
			}
			EntityTransaction transaction = pEntityManager.getTransaction();
			transaction.begin();
			
			pEntityManager.persist(pObject);
			
			transaction.commit();
			
			if ( pCloseEntityManager ) {
				UtilJPA.closeEntityManager(pEntityManager);
			}
		} catch (PersistentObjectException poe) {
			System.out.println("Dados do objeto que se tentou persistir, sem sucesso:\n");
			System.out.println(pObject.toString());
		} catch (ObjetoNuloPersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Method to Update/Merge a Registry in the Database
	 */
	public void update(T pObject) {
		update(pObject, UtilJPA.getEntityManager(), true);
	}
	
	public void update(T pObject, EntityManager pEntityManager, boolean pCloseEntityManager) {
		EntityTransaction transaction = pEntityManager.getTransaction();
		transaction.begin();
		
		pEntityManager.merge(pObject);
		
		transaction.commit();
		
		UtilJPA.closeEntityManager(pEntityManager);
	}
	
	/*
	 * Method to Delete/Remove a Registry in the Database, by receiving an Entity's
	/*/
	public void deleteObject(T pEntity) {
		deleteObject((Class<T>) pEntity.getClass(), pEntity, UtilJPA.getEntityManager(), true);
	}
	public void deleteObject(Class<T> pClass, T pEntity) {
		deleteObject(pClass, pEntity, UtilJPA.getEntityManager(), true);
	}
	public void deleteObject(
		Class<T>			pClass, 
		T					pEntidade, 
		EntityManager		pEntityManager, 
		boolean				pCloseEntityManager
	) {
		EntityTransaction transaction = pEntityManager.getTransaction();
		transaction.begin();
		
		T registryToBeDeleted = pEntityManager.find(pClass, pEntidade.getPrimaryKey());
		pEntityManager.remove(registryToBeDeleted);
		
		transaction.commit();
		
		UtilJPA.closeEntityManager(pEntityManager);
	}
	
	/*
	 * Method to Delete/Remove a Registry in the Database, by receiving an Entity's Primary Key
	*/
	public void deleteByPrimaryKey(Class<T> pClass, Object pPrimaryKey) {
		deleteByPrimaryKey(pClass, pPrimaryKey, UtilJPA.getEntityManager(), true);
	}
	public void deleteByPrimaryKey(Class<T> pClass, Object pPrimaryKey, EntityManager pEntityManager, boolean pCloseEntityManager) {
		EntityTransaction transaction = pEntityManager.getTransaction();
		transaction.begin();
		
		T registry = pEntityManager.find(pClass, pPrimaryKey);
		pEntityManager.remove(registry);
		
		transaction.commit();
		
		UtilJPA.closeEntityManager(pEntityManager);
	}
	
	/**
	 * Method to Select/Find a Registry in the Database, by receiving an Entity's Primary Key
	 */
	public T selectByPrimaryKey(Class<T> pClass, Object pPrimaryKey) {
		return selectByPrimaryKey(pClass, pPrimaryKey, UtilJPA.getEntityManager(), true);
	}
	public T selectByPrimaryKey(Class<T> pClass, Object pPrimaryKey, EntityManager pEntityManager, boolean pCloseEntityManager) {
		T registry = 
			selectByPrimaryKey(
				pClass, 
				pPrimaryKey, 
				pEntityManager, 
				pCloseEntityManager, 
				false);
		
		return registry;
	}
	public T selectByPrimaryKey(Class<T> pClass, Object pPrimaryKey, EntityManager pEntityManager, boolean pCloseEntityManager, boolean pLoadLazyAttributes) {
		T registry = pEntityManager.find(pClass, pPrimaryKey);
		
		if ( pLoadLazyAttributes ) {
			registry.loadLazyAttributes();
		}
		
		UtilJPA.closeEntityManager(pEntityManager);
		
		return registry;
	}
	
	/**
	 * Method to Select/Find a Registry in the Database, by receiving an Entity's Primary Key
	 */
	public T selectByEntity(Class<T> pClass, T pEntity) {
		return selectByEntity(pClass, pEntity, UtilJPA.getEntityManager(), true);
	}
	public T selectByEntity(
		Class<T>		pClass, 
		T				pEntity, 
		EntityManager	pEntityManager, 
		boolean			pCloseEntityManager
	) {
		T registry = 
			selectByEntity(
				pClass, 
				pEntity,
				pEntityManager,
				pCloseEntityManager,
				false
			);
		
		return registry;
	}
	public T selectByEntity(
		Class<T>		pClass, 
		T				pEntity, 
		EntityManager	pEntityManager, 
		boolean			pCloseEntityManager,
		boolean			pLoadLazyAttributes
	) {
		T registry = pEntityManager.find(
			pClass, 
			pEntity.getPrimaryKey());
		
		registry.loadLazyAttributes();
		
		if ( pCloseEntityManager ) {
			UtilJPA.closeEntityManager(pEntityManager);
		}
		
		return registry;
	}
	
	public List<T> findAll() {
		EntityManager em = getEntityManager();
		
		String query = " Select t From " + getEntityClass().getSimpleName() + " t ";
		
		TypedQuery<T> typedQuery = 
			em.createQuery(
				query, 
				getEntityClass());
		
		List<T> resultset = typedQuery.getResultList();
		
		return resultset;
	}
	
}