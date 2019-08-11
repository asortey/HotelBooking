package com.hotelmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;
import com.hotelmanagement.util.ConnectionUtil;

public class AdminDAO implements IAdminDAO {

	private Connection connection;
	
	public AdminDAO() throws BookingException{
		super();
		try {
			connection = ConnectionUtil.initializeDatabase();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	private int getHotelID(String hotel_name) throws BookingException{
		String query = "select hotel_id from hotels where hotel_name=?";
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1,hotel_name );
			resultSet=statement.executeQuery();
	
			while(resultSet.next()) {
				id  = resultSet.getInt("hotel_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in getting hotel_id");
		}
		return id;
	}
	
	private int getRoomID(String hotel_id)throws BookingException{
		String query = "select room_id from roomdetails where hotel_id=?";
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1,hotel_id );
			resultSet=statement.executeQuery();
	
			while(resultSet.next()) {
				id  = resultSet.getInt("room_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookingException("Error in getting room_id");
		}
		return id;
	}
	@Override
	public int addHotel(HotelBean hotelBean) throws BookingException {
		String query = "insert into hotels values(null,?,?,?,?,?,?,?,?,?,?)";
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, hotelBean.getCity());
			statement.setString(2, hotelBean.getHotel_name());
			statement.setString(3, hotelBean.getAddress());
			statement.setString(4, hotelBean.getDescription());
			statement.setInt(5, hotelBean.getAvg_rate_per_night());
			statement.setString(6, hotelBean.getPhone_no1());
			statement.setString(7, hotelBean.getPhone_no2());
			statement.setString(8, hotelBean.getRating());
			statement.setString(9, hotelBean.getEmail());
			statement.setString(10, hotelBean.getFax());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		id = getHotelID(hotelBean.getHotel_name());
		return id;
	}
	@Override
	public void deleteHotel(int hotel_id) throws BookingException {
		String query="delete from hotels where hotel_id=?";
		int rows=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, hotel_id);
			rows=statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int addRoom(RoomDetailsBean roomDetailsBean) throws BookingException {
		String query ="insert into roomdetails values(null,?,?,?,?,'Y')";
		ResultSet resultSet=null;
		int id=0;
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1,roomDetailsBean.getHotel_id() );
			statement.setString(2, roomDetailsBean.getRoom_no());
			statement.setString(3, roomDetailsBean.getRoomm_type());
			statement.setInt(4, roomDetailsBean.getPer_night_rate());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String hotelid = Integer.toString(roomDetailsBean.getHotel_id());
		id = getRoomID(hotelid);
		return id;
	}
	@Override
	public void deleteRoom(int room_id) throws BookingException {
		String query="delete from roomdetails where room_id=?";
		int rows=0;
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, room_id);
			rows=statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<RoomDetailsBean> getRoomDetails() throws BookingException {
		List<RoomDetailsBean> roomList = new ArrayList<RoomDetailsBean>();
		String query = "select * from roomdetails";
		
		try(
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
			){
			
				while(resultSet.next()) {
					int room_id = resultSet.getInt("room_id");
					int hotel_id = resultSet.getInt("hotel_id");
					String room_no = resultSet.getString("room_no");
					String roomm_type = resultSet.getString("room_type");
					int per_night_rate = resultSet.getInt("per_night_rate");
					
					roomList.add(new RoomDetailsBean(room_id, hotel_id, room_no, roomm_type, per_night_rate));	
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roomList;
	}
	@Override
	public void updateHotelDetails(int hotelId, String parameterName, String value) throws BookingException {
		String query = "update hotels set " + parameterName + "=? where hotel_id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, value);
			preparedStatement.setInt(2, hotelId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void updateRoomDetails(int roomId, String parameterName, String value) throws BookingException {
		String query = "update roomdetails set " + parameterName + "=? where room_id=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, value);
			preparedStatement.setInt(2, roomId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<BookingBean> getBookingsOfHotel(int hotelID) throws BookingException {
		String query = "select * from bookingdetails b where b.room_id in (select r.room_id from roomdetails r where r.hotel_id = ?) ";
		List<BookingBean> hotelBookingList = new ArrayList<BookingBean>();
		ResultSet resultSet = null;
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, hotelID);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int booking_id = resultSet.getInt("booking_id");
				int room_id = resultSet.getInt("room_id");
				int user_id = resultSet.getInt("user_id");
				java.util.Date booked_from = resultSet.getDate("booked_from");
				java.util.Date booked_to = resultSet.getDate("booked_to");
				int no_of_adults = resultSet.getInt("no_of_adults");
				int no_of_children = resultSet.getInt("no_of_children");
				int amount = resultSet.getInt("amount");
				hotelBookingList.add(new BookingBean(booking_id, room_id, user_id, booked_from, booked_to, no_of_adults, no_of_children, amount));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotelBookingList;
	}
	@Override
	public List<BookingBean> getBookingsOfHotelforDate(Date date) throws BookingException {
		java.sql.Date currDate = new java.sql.Date(date.getTime());
		String query = "SELECT * FROM bookingdetails WHERE ? BETWEEN BOOKED_FROM AND BOOKED_TO";
		List<BookingBean> hotelBookingList = new ArrayList<BookingBean>();
		ResultSet resultSet = null;
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDate(1, currDate);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int booking_id = resultSet.getInt("booking_id");
				int room_id = resultSet.getInt("room_id");
				int user_id = resultSet.getInt("user_id");
				java.util.Date booked_from = resultSet.getDate("booked_from");
				java.util.Date booked_to = resultSet.getDate("booked_to");
				int no_of_adults = resultSet.getInt("no_of_adults");
				int no_of_children = resultSet.getInt("no_of_children");
				int amount = resultSet.getInt("amount");
				hotelBookingList.add(new BookingBean(booking_id, room_id, user_id, booked_from, booked_to, no_of_adults, no_of_children, amount));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotelBookingList;
	}
	@Override
	public List<UserBean> viewGuestList(int hotelID) throws BookingException {
		String query = "select * from users u where u.user_id in (select b.user_id from bookingdetails b where b.room_id in (select r.room_id from roomdetails r where r.hotel_id=?))";
		List<UserBean> userList = new ArrayList<UserBean>();
		ResultSet resultSet = null;
		
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, hotelID);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				int user_id = resultSet.getInt("user_id");
				String user_name = resultSet.getString("user_name");
				String mobile_no = resultSet.getString("mobile_no");
				String phone = resultSet.getString("phone");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				userList.add(new UserBean(user_id, user_name, mobile_no, phone, address, email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	

}
