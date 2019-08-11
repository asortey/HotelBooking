package com.hotelmanagement.service;

import java.util.List;

import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;

public interface ICustomerService {
	public int registerUser(UserBean userBean)throws BookingException;
	public List<RoomDetailsBean> searchHotelRooms(String city) throws BookingException;
	public RoomDetailsBean getRoomDetails(int room_id) throws BookingException;
	public int confirmBooking(BookingBean bookingBean) throws BookingException;
	public List<BookingBean> getBookings(int user_id) throws BookingException;
	
}
