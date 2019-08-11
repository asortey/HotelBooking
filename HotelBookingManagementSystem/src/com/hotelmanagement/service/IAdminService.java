package com.hotelmanagement.service;

import java.util.Date;
import java.util.List;

import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;

public interface IAdminService {
	public int addHotel(HotelBean hotelBean) throws BookingException;
	public void deleteHotel(int hotel_id) throws BookingException;
	public int addRoom(RoomDetailsBean roomDetailsBean) throws BookingException;
	public void deleteRoom(int room_id) throws BookingException;
	public List<RoomDetailsBean> getRoomDetails() throws BookingException;
	public void updateHotelDetails(int hotelId, String parameterName, String value) throws BookingException;
	public void updateRoomDetails(int roomId, String parameterName, String value) throws BookingException;
	public List<BookingBean> getBookingsOfHotel(int hotelID) throws BookingException;
	public List<BookingBean> getBookingsOfHotelforDate(Date date) throws BookingException;
	public List<UserBean> viewGuestList(int hotelID) throws BookingException;
}
