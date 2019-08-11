package com.hotelmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;
import com.hotelmanagement.util.ConnectionUtil;

public class CustomerDAO implements ICustomerDAO {

	private Connection connection;
	
	public CustomerDAO() throws BookingException{
		super();
		try {
			connection = ConnectionUtil.initializeDatabase();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int getUserID(String username) throws BookingException{
		String query = "select user_id from users where user_name=?";
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1,username );
			resultSet=statement.executeQuery();
	
			while(resultSet.next()) {
				id  = resultSet.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in getting userId");
		}
		return id;
	}

	
	@Override
	public int registerUser(UserBean userBean) throws BookingException {
		String query = "insert into users values(null,?,?,?,?,?,?,?)";
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, userBean.getPassword());
			statement.setString(2, userBean.getRole());
			statement.setString(3, userBean.getUser_name());
			statement.setString(4, userBean.getMobile_no());
			statement.setString(5, userBean.getPhone());
			statement.setString(6, userBean.getAddress());
			statement.setString(7, userBean.getEmail());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in executing query.");
			
		}
		id = getUserID(userBean.getUser_name());
		return id;
	}

	@Override
	public List<RoomDetailsBean> searchHotelRooms(String city) throws BookingException {
		String query = "select * from roomdetails where availability='Y' AND hotel_id in (select hotel_id from hotels where city=?)";
		List<RoomDetailsBean> hotelRoomList = new ArrayList<RoomDetailsBean>();
		ResultSet resultSet=null;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, city);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int room_id = resultSet.getInt("room_id");
				int hotel_id = resultSet.getInt("hotel_id");
				String room_no = resultSet.getString("room_no");
				String roomm_type = resultSet.getString("room_type");
				int nightRate = resultSet.getInt("per_night_rate");
				char availibility = 'Y';
				hotelRoomList.add(new RoomDetailsBean(room_id, hotel_id, room_no, roomm_type, nightRate, availibility));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in executing query.");
			
		}
		return hotelRoomList;
	}

	@Override
	public RoomDetailsBean getRoomDetails(int room_id) throws BookingException {
		String query = "select * from roomdetails where room_id=?";
		ResultSet resultSet=null;
		RoomDetailsBean roomDetailsBean=null;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setInt(1, room_id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int hotel_id = resultSet.getInt("hotel_id");
				String room_no = resultSet.getString("room_no");
				String roomm_type = resultSet.getString("room_type");
				int nightRate = resultSet.getInt("per_night_rate");
				char availibility = 'Y';
				roomDetailsBean = new RoomDetailsBean(room_id, hotel_id, room_no, roomm_type, nightRate, availibility);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in executing query.");
			
		}
		return roomDetailsBean;
	}

	@Override
	public int confirmBooking(BookingBean bookingBean) throws BookingException {
		String query = "insert into bookingdetails values(null,?,?,?,?,?,?,?)";
		String room_id = Integer.toString(bookingBean.getRoom_id());
		String user_id = Integer.toString(bookingBean.getUser_id());
		Date booked_from = new Date(bookingBean.getBooked_from().getTime());
		Date booked_to = new Date(bookingBean.getBooked_to().getTime());
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, room_id);
			statement.setString(2, user_id);
			statement.setDate(3,booked_from );
			statement.setDate(4, booked_to);
			statement.setInt(5, bookingBean.getNo_of_adults());
			statement.setInt(6, bookingBean.getNo_of_children());
			statement.setInt(7, bookingBean.getAmount());
			
			statement.executeUpdate();
		
			ResultSet rs=statement.executeQuery("select * from bookingdetails");
			if(rs.last()){
				id=rs.getInt("booking_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in executing query.");
		}
		return id;
	}

	@Override
	public List<BookingBean> getBookings(int user_id) throws BookingException {
		String query="select * from bookingdetails where user_id=?";
		List<BookingBean> bookingList = new ArrayList<BookingBean>();
		ResultSet resultSet=null;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, user_id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int booking_id = resultSet.getInt("booking_id");
				int room_id = resultSet.getInt("room_id");
				java.util.Date booked_from = resultSet.getDate("booked_from");
				java.util.Date booked_to = resultSet.getDate("booked_to");
				int no_of_adults = resultSet.getInt("no_of_adults");
				int no_of_children = resultSet.getInt("no_of_children");
				int amount = resultSet.getInt("amount");
				bookingList.add(new BookingBean(booking_id, room_id, user_id, booked_from, booked_to, no_of_adults, no_of_children, amount));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookingList;
	}
	
}
