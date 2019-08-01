package com.bbs.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bbs.beans.Admin;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;

public class JPAAdminImpl implements AdminDaoBBS{
	private static EntityManagerFactory  emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
	Bus bus = new Bus();
	Admin admin = new Admin();
	@Override
	public Boolean createBus(Bus bus) {
		
		
		Boolean state = false;

		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(bus);
			em.getTransaction().commit();
			em.close();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}
	

	@Override
	public Boolean updateBus(String source,String destination,Double price,Integer busId)
	{
		Boolean state = false;
		try {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	  bus = em.find(Bus.class,busId);
	  bus.setSource(source);
	  bus.setDestination(destination);
	  bus.setPrice(price);
		em.getTransaction().commit();
		em.close();
		state = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}
	

	@Override
	public Bus searchBus(Integer busId) {
		 EntityManager em = emf.createEntityManager();
	       Query query = em.createQuery("from Bus u where u.busId= :busid ");
	       query.setParameter("busid", busId );
	       em.getTransaction().begin();
	       List bus=query.getResultList();
	       em.getTransaction().commit();
	      Bus bus1= (Bus) bus.get(0);
	      return bus1;
	}

	@Override
	public Boolean deletebus(Integer busId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Bus bus = em.find(Bus.class,busId );
		em.remove(bus);
		em.getTransaction().commit();
		return true;
	}
	

	@Override
	public List<Bus> busBetween(String source, String destination) {

		EntityManager em = emf.createEntityManager();
		TypedQuery<Bus> query = em.createQuery("Select b from Bus b where b.source= :source and b.destination= :destination",Bus.class);
		query.setParameter("source",source);
		query.setParameter("destination",destination);
		List<Bus> buses = query.getResultList();
		bus = buses.get(0);
		em.close();
		return buses;
	
	}

	@Override
	public Boolean adminLogin(Integer adminId, String password) {
		try{
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Admin> query = em.createQuery("from Admin where adminId= :id and password= :passwd", Admin.class);
			query.setParameter("id", adminId);
			query.setParameter("passwd", password);
			List<Admin> admins = query.getResultList();
			admin = admins.get(0);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	
}
}