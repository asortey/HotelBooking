package com.hotelmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;
import com.hotelmanagement.util.ConnectionUtil;

public class CommonDAO implements ICommonDAO {

	private Connection connection;
	
	public CommonDAO() throws BookingException{
		super();
		try {
			connection = ConnectionUtil.initializeDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public UserBean login(String username, String password) throws BookingException {
		String query = "select * from users where user_name=? and password=?";
		ResultSet resultSet=null;
		UserBean userBean=null;
		
		try (PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int user_id = resultSet.getInt("user_id");
				String role = resultSet.getString("role");
				String mobile_no = resultSet.getString("mobile_no");
				String phone = resultSet.getString("phone");
				String address = resultSet.getString("address");
				String email= resultSet.getString("email");
				
				userBean = new UserBean(user_id, password, role, username, mobile_no, phone, address, email);
			}
			
		} catch (SQLException e) {
			throw new BookingException("Error in executing query.");
		}
		
		return userBean;
	}
	@Override
	public List<HotelBean> getHotelDetails() throws BookingException {
		List<HotelBean> hotelList = new ArrayList<HotelBean>();
		String query = "select * from hotels";
		
		try(
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
			){
			
				while(resultSet.next()) {
					int hotel_id = resultSet.getInt("hotel_id");
					String city = resultSet.getString("city");
					String hotel_name = resultSet.getString("hotel_name");
					String address = resultSet.getString("address");
					String description = resultSet.getString("description");
					int avg_rate_per_night= resultSet.getInt("avg_rate_per_night");
					String phone_no1 = resultSet.getString("phone_no1");
					String phone_no2 = resultSet.getString("phone_no2");
					String rating = resultSet.getString("rating");
					String email = resultSet.getString("email");
					String fax = resultSet.getString("fax");
					
					hotelList.add(new HotelBean(hotel_id, city, hotel_name, address, description, avg_rate_per_night, phone_no1, phone_no2, rating, email, fax));
					
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hotelList;
	}
	
}
