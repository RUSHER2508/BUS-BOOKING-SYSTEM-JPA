package com.bbs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;

public interface DAOUser {
	//user manipulation
		public Boolean createUser(User user); //For create user operation
		
		//For  update user operation
		public Boolean updateUser(Integer userId,String oldPassword,String newPassword);
		
		public Boolean loginUser(Integer userId,String password); //For  login of user
		
		public User searchUser(Integer userId);   //For searching of user using user id
		
		//For delete operation of user
		public Boolean deleteUser(Integer userId,String password);
		
		
		//ticket booking
		public Ticket bookTicket(Ticket ticket);  //For booking tickets
		
		public Boolean cancelTicket(Integer bookingId,Integer userId);	 //For cancelling ticket
		
		//For getting ticket
		Ticket getTicket(Integer bookingId, Integer userId);
		
		 //For checking availability from bus id and date
		public Integer checkAvailability(Integer busId,java.sql.Date availdate);
		
		//For checking availability from source,destination and date
		public List <Bus>checkAvailability(String source,String destination,java.sql.Date date);


}
