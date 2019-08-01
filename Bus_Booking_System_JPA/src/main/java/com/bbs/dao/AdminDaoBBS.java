package com.bbs.dao;

import java.util.HashMap;
import java.util.List;

import com.bbs.beans.Bus;

public interface AdminDaoBBS {

	//bus manipulations
	public Boolean createBus(Bus bus);   //For create operation of bus
	
	//For update operation of bus
	public Boolean updateBus(String source,String destination,Double price,Integer busId);
	
	//For search operation using bus id
	public Bus searchBus(Integer busId);
	
	//For delete operation 
	public Boolean deletebus(Integer busId);
	
	//For searching bus between source and destination
	public List<Bus> busBetween(String source,String destination);

	//admin
	public Boolean adminLogin(Integer adminId, String password); //For admin login

}
