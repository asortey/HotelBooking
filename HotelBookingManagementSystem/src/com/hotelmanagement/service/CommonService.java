package com.hotelmanagement.service;

import java.util.List;

import com.hotelmanagement.dao.CommonDAO;
import com.hotelmanagement.dao.ICommonDAO;
import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;

public class CommonService implements ICommonService{
	
	private ICommonDAO dao;
	
	
	public CommonService() throws BookingException {
		dao = new CommonDAO();
	}


	@Override
	public UserBean login(String username, String password) throws BookingException {
		return dao.login(username, password);
	}


	@Override
	public List<HotelBean> getHotelDetails() throws BookingException {
		return dao.getHotelDetails();
	}

}
