package com.bbs.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.bbs.beans.Availability;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;

public class JPAUserImpl implements DAOUser{

	User user = new User();
	private static EntityManagerFactory  emf = Persistence.createEntityManagerFactory("myPersistanceUnit");
	@Override
	public Boolean createUser(User user) {
		Boolean state = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			System.out.println("hello");
			em.getTransaction().commit();
			em.close();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return state;
	}	

	@Override
	public Boolean updateUser(Integer userId,String oldPassword,String newPassword) {
		Boolean state = true;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			javax.persistence.Query query = em.createQuery("Update User u set u.password= :password where userId= :id and password= :passwd");
			query.setParameter("password", newPassword);
			query.setParameter("id", userId);
			query.setParameter("passwd", oldPassword);
			int updateCount = query.executeUpdate(); 
	        em.getTransaction().commit();
			em.close();
			state = true;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return state;
	}

	
	

	@Override
	public Boolean loginUser(Integer userId, String password) {
		try{
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<User> query = em.createQuery("from User u where userId= :id and password= :passwd", User.class);
			query.setParameter("id", userId);
			query.setParameter("passwd", password);
			List<User> users = query.getResultList();
			user = users.get(0);
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	@Override
	public User searchUser(Integer userId) {
	       EntityManager em = emf.createEntityManager();
	       Query query = em.createQuery("from User u where u.userId= :userid ");
	       query.setParameter("userid", userId );
	       em.getTransaction().begin();
	       List user=query.getResultList();
	       em.getTransaction().commit();
	      User user1= (User) user.get(0);
		return user1;
	}
	

	@Override
	public Boolean deleteUser(Integer userId, String password) {
		EntityManager em = emf.createEntityManager();
		Boolean login = loginUser(userId, password);
		if(login)
		{
			em.getTransaction().begin();
			User user = em.find(User.class,userId );
			em.remove(user);
			em.getTransaction().commit();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Ticket bookTicket(Ticket ticket) {
		Bus bus = new Bus();
		EntityManager em = emf.createEntityManager();
		Availability available = new Availability();
		int totalseats = checkAvailability(ticket.getBusId(),(java.sql.Date)ticket.getAvailDate());
		if(ticket.getNumofSeats()<=totalseats) {
			try {
				em.getTransaction().begin();
				javax.persistence.Query query = em.createQuery("Update Availability a set a.availableSeats= :availSeats where a.busId = :busId and a.availableDate = :date");
				query.setParameter("availSeats",totalseats - ticket.getNumofSeats());
				query.setParameter("busId", ticket.getBusId());
				query.setParameter("date",(java.sql.Date)ticket.getAvailDate());
				ticket.setJourneyDate(ticket.getAvailDate());
				ticket.setAvailId(ticket.getAvailId());
				int updateAvail = query.executeUpdate();
				em.persist(ticket);
				em.getTransaction().commit();
				em.close();
				System.out.println(ticket);

				return ticket;

			} catch (Exception e) {
				e.printStackTrace();
				
			}
	 
		}
		return ticket;
	}

	@Override
	public Boolean cancelTicket(Integer bookingId, Integer userId) {
	       EntityManager em  =  emf.createEntityManager();
	       Ticket ticket = getTicket(bookingId, userId);
	       int busId = ticket.getBusId();
	       int seats = ticket.getNumofSeats();
	       em.getTransaction().begin();
	       Query query = em.createQuery("DELETE FROM Ticket b where b.bookingId = :bookingId");
	       query.setParameter("bookingId", bookingId );
	       Boolean state = false;
	       Integer i = query.executeUpdate();
	       if(i>0)
	       {
	    	   TypedQuery<Availability> q = em.createQuery("FROM Availability a where a.busId = :busId",Availability.class);
	    	   q.setParameter("busId", busId );
	    	   List<Availability> available = q.getResultList();
	    	   Availability avail = available.get(0);
	    	   int availSeats = avail.getAvailableSeats();
	    	   availSeats = availSeats + seats;
	    	   Query q1 = em.createQuery("UPDATE Availability a set a.availableSeats = :avail where a.busId = :busId");
	    	   q1.setParameter("avail", availSeats);
	    	   q1.setParameter("busId", busId);
	    	   Integer i1 = q1.executeUpdate();
	    	   em.getTransaction().commit();
	    	   if(i1 > 0 )
	    	   {
	    		   state = true;
	    	   }
	       }
			return state;
		}
	

	@Override
	public Ticket getTicket(Integer bookingId, Integer userId) {
		Ticket ticket = new Ticket();
		EntityManager em = emf.createEntityManager();
		TypedQuery<Ticket> query = em.createQuery("from Ticket t where userId= :userId and bookingId= :bookingId", Ticket.class);
		query.setParameter("userId", userId);
		query.setParameter("bookingId", bookingId);
		List<Ticket> tickets = query.getResultList();
		ticket = tickets.get(0);
		em.close();
		return ticket;
	}

	

	@Override
	public Integer checkAvailability(Integer busId, Date availdate) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		javax.persistence.Query query = em.createQuery("select a from Availability a where a.busId = :busId and a.availableDate = :availdate");
		
		query.setParameter("busId", busId);
		query.setParameter("availdate", availdate);
		List<Availability> avail = query.getResultList();
		Availability seats = avail.get(0);
		int availSeats = seats.getAvailableSeats();		
		em.getTransaction().commit();
		return availSeats;
	}


	@Override
	public List<Bus> checkAvailability(String source, String destination, Date date) {
		Bus bus = new Bus();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		javax.persistence.Query query = em.createQuery("select b from Bus b join Availability a on b.busId = a.busId  where b.source= :source and b.destination= :destination and a.availableDate= :date");
		query.setParameter("source", source);
		query.setParameter("destination", destination);
		query.setParameter("date", date);
		List<Bus> buses = query.getResultList();
		em.getTransaction().commit();
		bus = buses.get(0);
		System.out.println(bus);
		em.close();
		return buses;
	}

}
