package com.bbs.services;

import java.util.HashMap;
import java.util.List;

import com.bbs.beans.Bus;
import com.bbs.dao.AdminDaoBBS;
import com.bbs.dao.JPAAdminImpl;


public class AdminServiceImpl implements AdminServices{
AdminDaoBBS dao = new JPAAdminImpl();
	@Override
	public Boolean createBus(Bus bus) {
		return dao.createBus(bus);
	}

	

	@Override
	public Bus searchBus(Integer busId) {
		return dao.searchBus(busId);
	}

	@Override
	public Boolean deletebus(Integer busId) {
		return dao.deletebus(busId);
	}

	@Override
	public List<Bus> busBetween(String source, String destination) {
		return dao.busBetween(source, destination);
	}

	@Override
	public Boolean adminLogin(Integer adminId, String password) {
		return dao.adminLogin(adminId, password);
	}



	@Override
	public Boolean updateBus(String source, String destination, Double price, Integer busId) {
		return dao.updateBus(source, destination, price, busId);
	}

}
