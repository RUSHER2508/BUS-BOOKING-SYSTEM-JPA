package com.bbs.app;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.bbs.beans.Ticket;
import com.bbs.beans.User;
import com.bbs.services.UserServiceImpl;
import com.bbs.services.UserServices;

public class App2 {
	public static void main(String[] args) {
		User user = new User();
		UserServices services = new UserServiceImpl();
		Scanner sc = new Scanner(System.in);
/*		System.out.println("Enter the username");
		String username = sc.next();
		user.setUsername(username);
		System.out.println("Enter the email id");
		String email = sc.next();
		user.setEmail(email);
		System.out.println("Enter password");
		String password = sc.next();
		user.setPassword(password);
		System.out.println("Enter contact no");
		user.setContact(sc.nextLong());
		Boolean s =services.createUser(user);
		System.out.println(s);
*/
/*		User s = services.searchUser(1);
		System.out.println(s);
*/		
/*		Boolean c= services.deleteUser(1,"123456");
System.out.println(c);
*/
	/*	services.updateUser(1, "pwd");
		System.out.println(services.searchUser(1));
		
		services.deleteUser(1);
		System.out.println(services.searchUser(1));
	*/

	/*int userId = 1;
	String password="pwd";
	Boolean s =services.loginUser(userId, password);
	System.out.println(s);						ticket.setAvailDate(sc.next());

	*/
		
	/*Ticket t = services.getTicket(301, 1);
		System.out.println(t);
	*/
		
		String date = "2019-07-29";
		java.util.Date availdate;
		int busId=101;
			try {
				availdate = new SimpleDateFormat("yy-MM-dd").parse(date);
				java.sql.Date date1 = new java.sql.Date(availdate.getTime());
				 Integer v= services.checkAvailability(busId, date1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		Ticket ticket = new Ticket();
	/*	ticket.setBookingId(602);
		ticket.setUserId(1);
		String date = "2019-07-29";
		java.util.Date availdate1;
		try {
			availdate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date availdate = new Date(availdate1.getTime());
			ticket.setJourneyDate(availdate);
			ticket.setNumofSeats(4);
		services.bookTicket(ticket);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		
		
	/*	Available available = new Available();
		Bus bus =new Bus();
		System.out.println("Enter soure");
		String source = sc.next();
		System.out.println("Enter the destintion");
		String destination = sc.next();
		String date = "2019-08-26";
		java.util.Date availdate;
		try {
			availdate = new SimpleDateFormat("yy-MM-dd").parse(date);
			java.sql.Date date1 = new java.sql.Date(availdate.getTime());
			  services.checkAvailability(source, destination, date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	*/
		}

}
