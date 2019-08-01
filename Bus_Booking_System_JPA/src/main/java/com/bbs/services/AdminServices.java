package com.bbs.services;

import java.util.HashMap;
import java.util.List;

import com.bbs.beans.Bus;

public interface AdminServices {
	//bus manipulations
	public Boolean createBus(Bus bus);
	public Boolean updateBus(String source,String destination,Double price,Integer busId);
	public Bus searchBus(Integer busId);
	public Boolean deletebus(Integer busId);
	public List<Bus> busBetween(String source,String destination);

	//admin
	public Boolean adminLogin(Integer adminId, String password);

}
