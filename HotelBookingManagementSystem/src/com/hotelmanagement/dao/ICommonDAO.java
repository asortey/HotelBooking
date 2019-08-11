package com.hotelmanagement.dao;

import java.util.List;

import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;

public interface ICommonDAO {
	public UserBean login(String username, String password) throws BookingException;
	public List<HotelBean> getHotelDetails() throws BookingException;
}
