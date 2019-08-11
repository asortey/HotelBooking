package com.hotelmanagement.service;

import java.util.Date;
import java.util.List;

import com.hotelmanagement.dao.AdminDAO;
import com.hotelmanagement.dao.IAdminDAO;
import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;

public class AdminService implements IAdminService{

	private IAdminDAO dao;
	
	public AdminService() throws BookingException{
		dao = new AdminDAO();
	}
	
	@Override
	public int addHotel(HotelBean hotelBean) throws BookingException {
		return dao.addHotel(hotelBean);
	}

	@Override
	public void deleteHotel(int hotel_id) throws BookingException {
		dao.deleteHotel(hotel_id);
	}

	@Override
	public int addRoom(RoomDetailsBean roomDetailsBean) throws BookingException {
		return dao.addRoom(roomDetailsBean);
	}

	@Override
	public void deleteRoom(int room_id) throws BookingException {
		dao.deleteRoom(room_id);
	}

	@Override
	public List<RoomDetailsBean> getRoomDetails() throws BookingException {
		return dao.getRoomDetails();
	}

	@Override
	public void updateHotelDetails(int hotelId, String parameterName, String value) throws BookingException {
		dao.updateHotelDetails(hotelId, parameterName, value);
	}

	@Override
	public void updateRoomDetails(int roomId, String parameterName, String value) throws BookingException {
		dao.updateRoomDetails(roomId, parameterName, value);
	}

	@Override
	public List<BookingBean> getBookingsOfHotel(int hotelID) throws BookingException {
		return dao.getBookingsOfHotel(hotelID);
	}

	@Override
	public List<BookingBean> getBookingsOfHotelforDate(Date date) throws BookingException {
		return dao.getBookingsOfHotelforDate(date);
	}

	@Override
	public List<UserBean> viewGuestList(int hotelID) throws BookingException {
		return dao.viewGuestList(hotelID);
	}

}
