package com.bbs.app;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.bbs.beans.Availability;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;
import com.bbs.exception.BusCreateFailException;
import com.bbs.exception.BusDeleteFailException;
import com.bbs.exception.BusNotFoundException;
import com.bbs.exception.DeleteUserException;
import com.bbs.exception.LoginException;
import com.bbs.exception.NotIntegerFormatException;
import com.bbs.exception.RegisterException;
import com.bbs.exception.TicketBookingException;
import com.bbs.exception.UpdateException;
import com.bbs.services.AdminServiceImpl;
import com.bbs.services.AdminServices;
import com.bbs.services.UserServiceImpl;
import com.bbs.services.UserServices;


public class App 
{
	//Declaring variables and methods as static
	static int userId;
	static int adminId;
	static String password;
	static Integer bookingId;
	static UserServices services = new UserServiceImpl();
	static AdminServices adminservices = new AdminServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Boolean loop = true;
		while(loop){

			//the MAIN MENU//
			System.out.println("*********************************************");
			System.out.println("****      RUSHER BUS BOOKING SYSTEM      ****");
			System.out.println("*********************************************");
			System.out.println("** [1] Login                               **");
			System.out.println("** [2] Create Profile                      **");
			System.out.println("** [3] Admin Login                         **");
			System.out.println("** [4] Exit                                **");
			System.out.println("*********************************************");
			System.out.println("*********************************************");
			System.out.println("Enter your choice");
			int firstChoice = sc.nextInt();
			Boolean login=false;
			if(firstChoice==1)
			{

				try {
					login = loginUser();
				} catch (LoginException e) {
					e.printStackTrace();
				} 
				if(login){
					System.out.println("Login Successful");
					boolean loop1 = true ;
					while(loop1)
					{
						System.out.println("** [1] Search Profile               **");
						System.out.println("** [2] Update Profile               **");
						System.out.println("** [3] Delete Profile               **");
						System.out.println("** [4] Book Ticket                  **");
						System.out.println("** [5] Cancel Ticket                **");
						System.out.println("** [6] Get Ticket                   **");
						System.out.println("** [7] Check availability           **");
						System.out.println("** [8] Exit                         **");


						System.out.println("***************************************");
						System.out.println("***************************************\n");
						{
							System.out.print("ENTER CHOICE: ");
							Integer	choice = sc.nextInt();

							if(choice ==1) {
								try{
									searchUser();
								}
								catch(Exception e){
									e.printStackTrace();
								}
							}
							else if(choice ==2) {
								try {
									updateUser();
								} catch (UpdateException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==3) {
								try {
									deleteUser();
								} catch (DeleteUserException e) {
									e.printStackTrace();
								}
							}

							else if(choice ==4) {
								try {
									bookTicket();
								} catch (TicketBookingException e) {
									e.printStackTrace();
								}
							}
							else if(choice ==5) {
								cancelTicket();
							}
							else if(choice ==6) {
								getTicket();
							}

							else if(choice ==7) {
								checkAvailability();
							}					
							else if(choice == 8) {
								System.out.println("Thank you for visiting");
								loop1=false;
							}
							else
							{
								System.out.println("Login Failed");
								loop1 = false;
							}

						}

					}

				}
			}
			else if(firstChoice == 2) {
				try {
					createUser();
				} catch (RegisterException e) {
					e.printStackTrace();
				}

			}
			else if(firstChoice ==3) {
				Boolean admin = false;
				try {
					admin = adminLogin();
				}
				catch(LoginException e) {
					e.printStackTrace();
				}
				if(admin) {
					System.out.println("Login Succesful");
					boolean loop2 = true ;
					while(loop2) {
						System.out.println("** [1] Create bus               **");
						System.out.println("** [2] Update bus               **");
						System.out.println("** [3] Search bus               **");
						System.out.println("** [4] Delete bus               **");
						System.out.println("** [5] Search between source and destination        **");
						System.out.println("** [6] Exit                                **");


						System.out.println("***************************************");
						System.out.println("***************************************\n");

						System.out.print("ENTER CHOICE: ");
						Integer	choice = sc.nextInt();
						if(choice ==1) {
							try {
								createBus();
							}catch(BusCreateFailException e) {
								e.printStackTrace();
							}
						}
						else if(choice ==2) {
							try {
								updateBus();
							}catch(UpdateException e) {
								e.printStackTrace();
							}
						}
						else if(choice ==3) {
							try {
								searchBus();
							} catch (BusNotFoundException e) {
								e.printStackTrace();
							}
						}
						else if(choice == 4) {
							try {
								deleteBus();
							}catch(BusDeleteFailException e) {
								e.printStackTrace();
							}
						}
						else if(choice == 5) {
							searchBusBetween();
						}
						else if(choice ==6 ){
							System.out.println("Thank you for visiting");
							loop2 = false;
						}
						else {
							System.out.println("Unsuccessfull");
						}

					}

				}

			}
			else if(firstChoice ==4) {
				loop =  false;
				System.err.println("Thank you for visiting");

			}

		}



	}	




	//For updating user
	private static void updateUser() throws UpdateException {
		User user = new User();
		user.setUserId(userId);  //Setting up userId
		System.out.println("Enter your old Password");
		String oldpassword = sc.next(); //Setting up password by confirming oldPassword
		System.out.println("Enter your new Password");
		String newPassword = sc.next();//Setting up newPassword
		boolean update = services.updateUser(userId, oldpassword, newPassword);
		if(update)
		{
			//if true 
			System.out.println("Profile Updated");
		}
		else {
			//if false
			System.err.println("Failed to Update");
			//Customized exception
			throw new UpdateException("Updation Fail Exception");
		}


	}

	//For Login of user
	private static boolean loginUser()throws LoginException {

		boolean checkLogin = true;
		while(checkLogin)
		{
			System.out.println("Enter userid:");
			//Checking if input is in Integer format or not by idCheck()
			Integer tempId=services.idCheck(sc.next());
			if(tempId!=null) {
				userId=tempId;
				checkLogin = false;
			}else {
				System.err.print("User id should be number !");
				throw new NotIntegerFormatException();

			}
		}
		System.out.println("Enter password:");
		password=sc.next();	
		Boolean login = services.loginUser(userId, password); //Invoking the loginUser()
		if(login)
		{
			//if true
			return true;
		}else {
			//if false
			throw new LoginException("Login Failed Exception");
		}

	}
	//For Profile delete of user
	private static void deleteUser() throws DeleteUserException {

		boolean delete =services.deleteUser(userId, password);  //Invoking deleteUser()
		if(delete){
			//if true
			System.out.println("Profile sucessfully Deleted");
		}else{
			//if false throw customised exception
			throw new DeleteUserException("User Profile deletion Failed");
		}
	}

	//For searching bus
	private static void searchBus() throws BusNotFoundException {
		boolean busCheck = true;
		Integer busId = 0;
		while(busCheck)
		{
			System.out.println("Enter BusId");
			//Checking if input is in Integer format or not
			Integer tempId=services.idCheck(sc.next());
			if(tempId!=null) {
				busId = tempId;
				busCheck = false;
			}else {
				System.err.println("User id should be number !");
				throw new NotIntegerFormatException();

			}
			Bus b = adminservices.searchBus(busId); //Calling searchBus()
			System.out.println(b);

		}


	}
	//For searching of user
	private static void searchUser() {
		//Calling searchUser()
		User search = services.searchUser(userId);
		System.out.println(search); // For printing of the search result

	}
	//For checking the availability of Bus
	private static void checkAvailability() {
		Availability available = new Availability();
		System.out.println("Enter Source point");
		String source = sc.next();
		System.out.println("Enter Destination point");
		String destination = sc.next();
		System.out.println("Enter Date (YYYY-MM-DD)");
		String tempDate=sc.next();
		Date date=Date.valueOf(tempDate); //Converting java.sql date to java.util date
		available.setAvailableDate(date);//Setting up the date
		//Calling the checkAvailability() and storing in List format
		List<Bus> list = 
				services.checkAvailability(source,destination,date);

		//Iterator for retreiving values
		for(Bus bs:list)
		{	
			System.out.println(bs);//Printing the buses
			//Calling the checkAvailabilty()

			int avail = services.checkAvailability(bs.getBusId(), date);
			System.out.println("Available Seats:"+avail);
		}

	}

	//For Booking the ticket
	private static void bookTicket() throws TicketBookingException {
		Ticket ticket = new Ticket();

		System.out.println("Enter source point");
		String checksource=sc.next();
		System.out.println("Enter Destination point");
		String checkdestination=sc.next();
		System.out.println("Enter date of journey(yyyy-mm-dd)");
		String tempDate=sc.next();
		Date date=Date.valueOf(tempDate); //Converting java.sql Date to java.util Date
		ticket.setAvailDate(date);//Setting the journeyDate
		//Calling the checkAvailability() and storing in List format
		List<Bus> list = 
				services.checkAvailability(checksource,checkdestination,date);

		//Iterator for retrieving the values
		for(Bus busAvailable:list)
		{	
			System.out.println(busAvailable);
			//Calling the checkAvailabilty()
			int availableSeat = services.checkAvailability(busAvailable.getBusId(), date);
			System.out.println("Available Seats:"+availableSeat);
		}

		System.out.println("Enter the bus id");
		int busId=sc.nextInt();
		ticket.setBusId(busId);

		ticket.setUserId(userId);
		ticket.setSource(checksource);
		ticket.setDestination(checkdestination);

		Integer availSeats=services.checkAvailability(busId, date);
		if(availSeats!=null){
			System.out.println("Total available seats are: "+availSeats);
		}

		System.out.println("Enter number of seats to book");
		ticket.setNumofSeats(sc.nextInt());
		Ticket bookTicket = services.bookTicket(ticket);
		if(bookTicket!=null){
			//if true
			System.out.println("Ticket sucessfully Booked");

			System.out.println(services.getTicket(ticket.getBookingId(),userId));
		}else{
			//if false throw customised exception
			throw new TicketBookingException("Ticket Booking Fail Exception");
		}	

	}
	//For getting the ticket info
	private static void getTicket() {
		System.out.println("Enter BookingId");
		//Calling getTicket()
		Ticket ticket = services.getTicket(sc.nextInt(), userId);
		if(ticket != null) {
			//if not null
			System.out.println(ticket);
		}else {
			System.out.println("No Tickets Found");
		}	
	}
	//For cancelling ticket	
	private static void cancelTicket() {
		System.out.println("Enter BookingId");
		bookingId = sc.nextInt();
		//Calling the getTicket()
		Ticket ticket = services.getTicket(bookingId, userId);
		System.out.println(ticket);
		System.err.println("To confirm delete press yes");
		String decision = sc.next();
		if(decision.equalsIgnoreCase("yes")) {
			//Calling the cancelTicket()
			Boolean b = services.cancelTicket(bookingId, userId);

			if(b){
				//if true
				System.out.println("Ticket Successfully Cancelled");
			}
			else {
				System.err.println("No Tickets Found");
			}
		}

	}

	//For creating the user
	private static void createUser() throws RegisterException {
		User user = new User();
		boolean checkLogin = true;
		while(checkLogin)
		{
			System.out.println("Enter userid:");
			//Checking if input is in Integer format or not
			Integer tempId=services.idCheck(sc.next());
			if(tempId!=null) {
				userId = tempId;
				user.setUserId(userId);
				checkLogin = false;
			}else {
				System.err.println("User id should be number !");
				throw new NotIntegerFormatException();

			}
		}
		System.out.println("Enter Username:");
		user.setUsername(sc.next());
		boolean checkEmail = true;
		while(checkEmail) {
			System.out.println("Enter Email:");
			//For checking the format of email
			String temp=services.checkemail(sc.next());
			if(temp !=null) {
				user.setEmail(temp);
				checkEmail = false;
			}else {
				System.err.println("Wrong Email Format!! e.g(example@email.com)");
			}
		}

		boolean checkContact = true;
		while(checkContact) {
			System.out.println("Enter Contact No.:");
			Long temp=services.regexcontact(sc.next());
			//For checking the format of contact
			if(temp !=null) {
				user.setContact(temp);
				checkContact = false;
			}else {
				System.err.println("Contact should be of 10 digits!!");
			}
		}
		System.out.println("Enter Password:");
		user.setPassword(sc.next());
		//Calling createUser()
		boolean registration = services.createUser(user);
		if(registration) {
			//if true
			System.out.println("Registration Successful");
		}
		else {
			//if false throw customised exception
			throw new RegisterException("Registration Fail Exception");
		}

	}
	//Calling the adminLogin()
	private static Boolean adminLogin() throws LoginException {

		System.out.println("Enter Admin id:");
		int adminid = Integer.parseInt(sc.next());
		System.out.println("Enter Admin password:");
		String password = sc.next();
		//Calling the adminLogin()
		if(adminservices.adminLogin(adminid,password))
		{
			return true;
		}else {
			//if false throw customised exception
			throw new LoginException("Admin Login Fail Exception");
		}
	}
	//For creating the bus
	private static void createBus() throws BusCreateFailException{
		Bus bus = new Bus();
		System.out.println("Enter Bus Id");
		bus.setBusId(Integer.parseInt(sc.next()));
		System.out.println("Enter BusName");
		bus.setBusName(sc.next());
		System.out.print("Enter Source");
		bus.setSource(sc.next());
		System.out.println("Enter Destination");
		bus.setDestination(sc.next());
		System.out.println("Enter Total Seats");
		bus.setTotalSeats(Integer.parseInt(sc.next()));
		System.out.println("Enter Price");
		bus.setPrice(Double.parseDouble(sc.next()));
		System.out.println("Enter Bus type");
		bus.setBusType(sc.next());

		//Calling createBus()
		boolean create = adminservices.createBus(bus);
		if(create) {
			//if true
			System.out.println("Bus created");
		}
		else {
			//if false then throw customised exception
			throw new BusCreateFailException("Fail to Create Bus Exception");
		}

	}

	//For updating bus info
	private static void updateBus() throws UpdateException {
		Bus bus = new Bus();
		boolean check = true;
		System.out.println("Enter BusId:");
		Integer busId=services.idCheck(sc.next());
		if(busId!=null) {
			bus.setBusId(busId);
			check = false;
		}else {
			System.out.println("Bus id should be number !");


		}
		System.out.println("Enter New Source Location");
		String source = sc.next();
		bus.setSource(source);
		System.out.println("Enter New Destination Location");
		String destination = sc.next();
		bus.setDestination(destination);
		System.out.println("Enter New Price per seat");
		Double price = sc.nextDouble();
		bus.setPrice(price);
		//calling the updateBus()
		Boolean updateBus = adminservices.updateBus(source, destination, price, busId);
		if(updateBus) {
			//if true
			System.out.println(" Bus Info Updated");

		}
		else {
			//if false throw customized exception
			throw new UpdateException("Fail to Update Bus Exception");
		}	
	}

	//For deleting bus
	private static void deleteBus() throws BusDeleteFailException {
		System.out.println("Enter Bus Id");
		int busId = Integer.parseInt(sc.next());
		//Calling deleteBus()
		boolean deletebus = adminservices.deletebus(busId);
		if(deletebus)
		{
			//if true
			System.out.println("Bus Successfully Deleted");
		}
		else {
			//if false throw customised exception
			throw new BusDeleteFailException("Fail to Delete Bus Exception");
		}
	}
	//For searching bus
	private static void searchBusBetween() {
		System.out.println("Enter source");
		String source = sc.next();
		System.out.println("Enter the destination");
		String destination = sc.next();
		//Calling busBetween()
		List<Bus> b = adminservices.busBetween(source, destination);
		System.out.println(b);

	}
}
