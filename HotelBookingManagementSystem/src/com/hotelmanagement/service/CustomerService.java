package com.hotelmanagement.service;

import java.util.List;

import com.hotelmanagement.dao.CustomerDAO;
import com.hotelmanagement.dao.ICustomerDAO;
import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;

public class CustomerService implements ICustomerService {
	
	private ICustomerDAO dao;
	
	public CustomerService() throws BookingException{
		dao = new CustomerDAO();
	}
	
	@Override
	public int registerUser(UserBean userBean) throws BookingException {
		return dao.registerUser(userBean);
	}

	@Override
	public List<RoomDetailsBean> searchHotelRooms(String city) throws BookingException {
		return dao.searchHotelRooms(city);
	}

	@Override
	public RoomDetailsBean getRoomDetails(int room_id) throws BookingException {
		return dao.getRoomDetails(room_id);
	}

	@Override
	public int confirmBooking(BookingBean bookingBean) throws BookingException {
		return dao.confirmBooking(bookingBean);
	}

	@Override
	public List<BookingBean> getBookings(int user_id) throws BookingException {
		return dao.getBookings(user_id);
	}

}
