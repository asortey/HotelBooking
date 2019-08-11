package com.hotelmanagement.ui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelmanagement.dao.IAdminDAO;
import com.hotelmanagement.dao.ICommonDAO;
import com.hotelmanagement.dao.ICustomerDAO;
import com.hotelmanagement.dto.BookingBean;
import com.hotelmanagement.dto.HotelBean;
import com.hotelmanagement.dto.RoomDetailsBean;
import com.hotelmanagement.dto.UserBean;
import com.hotelmanagement.exception.BookingException;
import com.hotelmanagement.service.AdminService;
import com.hotelmanagement.service.CommonService;
import com.hotelmanagement.service.CustomerService;
import com.hotelmanagement.service.IAdminService;
import com.hotelmanagement.service.ICommonService;
import com.hotelmanagement.service.ICustomerService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("submit");
		RequestDispatcher dispatcher =null;
		if(option.equals("Sign In")) {
			dispatcher = request.getRequestDispatcher("/pages/login.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Sign Up")) {
			dispatcher = request.getRequestDispatcher("/pages/newUser.jsp");
			dispatcher.forward(request, response);
		}
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String option = request.getParameter("submit");
		List<HotelBean> hotelList ;
		List<RoomDetailsBean> roomList;
		ICommonService commonService = null;
		ICustomerService customerService=null;
		IAdminService adminService=null;
		RequestDispatcher dispatcher =null;
		try {
			customerService = new CustomerService();
			commonService = new CommonService();
			adminService = new AdminService();
		} catch (BookingException e1) {
			e1.printStackTrace();
		}
		
		hotelList = new ArrayList<HotelBean>();
		try {
			hotelList = commonService.getHotelDetails();
		} catch (BookingException e) {
			e.printStackTrace();
		}
		session.setAttribute("hotelList", hotelList);
		
		roomList = new ArrayList<RoomDetailsBean>();
		try {
			roomList = adminService.getRoomDetails();
		} catch (BookingException e) {
			e.printStackTrace();
		}
		session.setAttribute("roomList", roomList);
		
		if(option.equals("Submit")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String mobile_no = request.getParameter("mobile_no");
			String phone = request.getParameter("phone");
			String role="customer";
			int id=0;
			UserBean userBean = new UserBean(password, role, username, mobile_no, phone, address, email);
			try {
				id = customerService.registerUser(userBean);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			
				request.setAttribute("id", id);
				dispatcher = request.getRequestDispatcher("/pages/success.jsp");
				dispatcher.forward(request, response);

		}
		
		else if(option.equals("Home Page")) {
			if(session!=null) {
				session.invalidate();
				dispatcher = request.getRequestDispatcher("/pages/homeScreen.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		
		else if(option.equals("Login")) {
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			UserBean userBean=null;
			try {
				userBean = commonService.login(username, password);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			String page="";
			if(userBean==null) {
				String error="Please enter valid login credentials";
				request.setAttribute("error", error);
				page="/pages/homeScreen.jsp";
			}
			else {
				session.setAttribute("userBean", userBean);
				
				if(userBean.getRole().equals("admin")) {
					page="/pages/adminPage.jsp";
				}
				else {
					page="/pages/userPage.jsp";
				}
			}
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Back to menu")) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			String page="";
			if(userBean.getRole().equals("admin")) {
				page="/pages/adminPage.jsp";
			}
			else {
				page="/pages/userPage.jsp";
			}
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Add new Hotel")) {
			dispatcher = request.getRequestDispatcher("/pages/addHotel.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Submit Hotel Details")) {
			String hotel_name = request.getParameter("hotel_name");
			String city = request.getParameter("city");
			String address = request.getParameter("address");
			String description = request.getParameter("description");
			String avg_rate = request.getParameter("avg_rate_per_night");
			int avgrate = Integer.parseInt(avg_rate);
			String phone_no_1 = request.getParameter("phone_no1");
			String phone_no_2 = request.getParameter("phone_no2");
			String rating = request.getParameter("rating");
			String email = request.getParameter("email");
			String fax = request.getParameter("fax");
			int id=0;
			HotelBean hotelBean = new HotelBean(city, hotel_name, address, description, avgrate, phone_no_1, phone_no_2, rating, email, fax);
			try {
				id = adminService.addHotel(hotelBean);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("id", id);
			String message = "Hotel successfully registered with hotel ID - ";
			request.setAttribute("message", message);
			dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Delete a hotel")) {
			
			dispatcher = request.getRequestDispatcher("/pages/deleteHotel.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Add new room")) {
			dispatcher = request.getRequestDispatcher("/pages/addRoom.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Add Room")) {
			int hotel_id = Integer.parseInt(request.getParameter("hotelID"));
			String roomNo = request.getParameter("roomNo");
			String roomType = request.getParameter("roomType");
			int per_night_rate = Integer.parseInt(request.getParameter("per_night_rate"));
			String availabilityRoom = request.getParameter("availability");
			int id=0;
			RoomDetailsBean roomDetailsBean = new RoomDetailsBean(hotel_id, roomNo, roomType, per_night_rate);
			try {
				id = adminService.addRoom(roomDetailsBean);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("id", id);
			String message = "Room successfully registered with room ID - ";
			request.setAttribute("message", message);
			dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Delete a room")) {
			
			dispatcher = request.getRequestDispatcher("/pages/deleteRoom.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("View all hotels")) {
			dispatcher = request.getRequestDispatcher("/pages/hotelDetails.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Update existing hotel")) {
			dispatcher = request.getRequestDispatcher("/pages/updateHotelDetails.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Update Hotel")) {
			int hotelId = Integer.parseInt(request.getParameter("hotelID"));
			String parameterName = request.getParameter("parameter"); 
			String value = request.getParameter("parameterValue");
			try {
				adminService.updateHotelDetails(hotelId, parameterName, value);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("id", hotelId);
			String message = "Hotel successfully updated of hotel ID - ";
			request.setAttribute("message", message);
			dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Update existing room")) {
			dispatcher = request.getRequestDispatcher("/pages/updateRoomDetails.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Update Room")) {
			int roomId = Integer.parseInt(request.getParameter("roomID"));
			String parameterName = request.getParameter("parameter");
			String value = request.getParameter("parameterValue");
			try {
				adminService.updateRoomDetails(roomId, parameterName, value);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("id", roomId);
			String message = "Room successfully updated of room ID - ";
			request.setAttribute("message", message);
			dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Search hotel rooms")) {
			dispatcher = request.getRequestDispatcher("/pages/searchHotelRooms.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Search")) {
			String city = request.getParameter("city");
			List<RoomDetailsBean> hotelRoomList = new ArrayList<RoomDetailsBean>();
			
			try {
				hotelRoomList = customerService.searchHotelRooms(city);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			if(hotelRoomList.isEmpty()) {
				String message = "No hotels in this city, please select another one.";
				request.setAttribute("message", message);
				dispatcher = request.getRequestDispatcher("/pages/searchHotelRooms.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("hotelRoomList", hotelRoomList);
				dispatcher = request.getRequestDispatcher("/pages/showHotelRooms.jsp");
				dispatcher.forward(request, response);
			}
		}
		else if(option.equals("Book Room")) {
			dispatcher = request.getRequestDispatcher("/pages/selectCity.jsp");
			dispatcher.forward(request, response);
		}
		else if(option.equals("Search City")) {
			String city = request.getParameter("city");
			request.setAttribute("city", city);
			List<RoomDetailsBean> hotelRoomList = new ArrayList<RoomDetailsBean>();
			
			try {
				hotelRoomList = customerService.searchHotelRooms(city);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			if(hotelRoomList.isEmpty()) {
				String message = "No hotels in this city, please select another one.";
				request.setAttribute("message", message);
				dispatcher = request.getRequestDispatcher("/pages/selectCity.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("hotelRoomList", hotelRoomList);
				dispatcher = request.getRequestDispatcher("/pages/bookHotelRooms.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		else if(option.equals("Book Hotel Room")) {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int room_id = Integer.parseInt(request.getParameter("room_id"));
			String checkindate = request.getParameter("checkindate");
			String checkoutdate = request.getParameter("checkoutdate");
			int no_of_adults = Integer.parseInt(request.getParameter("no_of_adults"));
			int no_of_children = Integer.parseInt(request.getParameter("no_of_children"));
			Date booked_from=null;
			Date booked_to=null;
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				booked_from = format.parse(checkindate);
				booked_to = format.parse(checkoutdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			RoomDetailsBean roomBean=null;
			try {
				roomBean = customerService.getRoomDetails(room_id);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			int perNightRate = roomBean.getPer_night_rate();
			long diff = booked_to.getTime() - booked_from.getTime();
			int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);;
			int amount = days*perNightRate;
			
			BookingBean bookingBean = new BookingBean(room_id, user_id, booked_from, booked_to, no_of_adults, no_of_children, amount);
			request.setAttribute("bookingBean", bookingBean);
			request.setAttribute("days", days);
			dispatcher = request.getRequestDispatcher("/pages/confirmBooking.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Confirm")) {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int room_id = Integer.parseInt(request.getParameter("room_id"));
			String checkindate = request.getParameter("booked_from");
			String checkoutdate = request.getParameter("booked_to");
			int no_of_adults = Integer.parseInt(request.getParameter("no_of_adults"));
			int no_of_children = Integer.parseInt(request.getParameter("no_of_children"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			Date booked_from1=null;
			Date booked_to1=null;
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				booked_from1 = format.parse(checkindate);
				booked_to1 = format.parse(checkoutdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int id=0;
			BookingBean bookingBean = new BookingBean(room_id, user_id, booked_from1, booked_to1, no_of_adults, no_of_children, amount);
			System.out.println(bookingBean);
			try {
				id = customerService.confirmBooking(bookingBean);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("id", id);
			String message = "Room successfully booked with booking ID - ";
			request.setAttribute("message", message);
			dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("View Bookings")) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			int user_id = userBean.getUser_id();
			List<BookingBean> bookingList = new ArrayList<BookingBean>();
			try {
				bookingList = customerService.getBookings(user_id);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("bookingList", bookingList);
			dispatcher = request.getRequestDispatcher("/pages/showBookings.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("View bookings of specific hotel")) {
			dispatcher = request.getRequestDispatcher("/pages/selectHotelForBookings.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Submit Hotel")) {
			int hotelID = Integer.parseInt(request.getParameter("hotel"));
			List<BookingBean> hotelBookingList=new ArrayList<BookingBean>();
			
			try {
				hotelBookingList=adminService.getBookingsOfHotel(hotelID);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("hotelBookingList", hotelBookingList);
			dispatcher = request.getRequestDispatcher("/pages/showBookingsOfHotel.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("View bookings of specific date")) {
			dispatcher = request.getRequestDispatcher("/pages/selectDateForBookings.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Show Bookings")) {
			String date = request.getParameter("date");
			Date currDate = null;
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				currDate = format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			List<BookingBean> hotelBookingList=new ArrayList<BookingBean>();
			try {
				hotelBookingList = adminService.getBookingsOfHotelforDate(currDate);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			request.setAttribute("hotelBookingList", hotelBookingList);
			dispatcher = request.getRequestDispatcher("/pages/showBookingsOfHotel.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("View guest list for a specific hotel")) {
			dispatcher = request.getRequestDispatcher("/pages/selectHotel.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Submit hotel")) {
			int hotelID = Integer.parseInt(request.getParameter("hotel"));
			List<UserBean> userList = new ArrayList<UserBean>();
			try {
				userList = adminService.viewGuestList(hotelID);
			} catch (BookingException e) {
				e.printStackTrace();
			}
			System.out.println(userList);
			request.setAttribute("userList", userList);
			dispatcher = request.getRequestDispatcher("/pages/viewGusetList.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(option.equals("Log Out")) {
			if(session!=null) {
				session.invalidate();
				String message = "You have successfully logged out!!!";
				request.setAttribute("message", message);
				dispatcher = request.getRequestDispatcher("/pages/homeScreen.jsp");
				dispatcher.forward(request, response);
			}
		}
		else{
			String hiddenValue=request.getParameter("delete");
			
			if(hiddenValue.equals("hotel")) {
				hotelList=(ArrayList<HotelBean>)session.getAttribute("hotelList");
				Iterator<HotelBean> hotelIterator = hotelList.iterator();
				//String delcount1 = request.getParameter("deleteValue");
				int delcount = Integer.parseInt(option);
				int count=0;
				int id=0;
				while(hotelIterator.hasNext())
				{
					HotelBean hotel = hotelIterator.next();
					if(count==delcount)
					{
						id = hotel.getHotel_id();
						try {
							adminService.deleteHotel(id);
						} catch (BookingException e) {
							e.printStackTrace();
						}
					}
					count++;
				}
				String message = "Hotel is successfully deleted of Hotel ID - ";
				request.setAttribute("id", id);
				request.setAttribute("message", message);
				dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
				dispatcher.forward(request, response);
			}
			else if(hiddenValue.equals("bookRoom")) {
				String city = request.getParameter("city");
				List<RoomDetailsBean> hotelRoomList = new ArrayList<RoomDetailsBean>();
				try {
					hotelRoomList = customerService.searchHotelRooms(city);
				} catch (BookingException e) {
					e.printStackTrace();
				}
				Iterator<RoomDetailsBean> roomDetailsBeanIterator = hotelRoomList.iterator();
				int roomCount = Integer.parseInt(option);
				int count=0;
				RoomDetailsBean roomDetailsBean=null;
				while(roomDetailsBeanIterator.hasNext()) {
					if(count==roomCount) {
						roomDetailsBean = roomDetailsBeanIterator.next();
						break;
					}
					roomDetailsBean = roomDetailsBeanIterator.next();
					count++;
				}
				request.setAttribute("roomDetailsBean", roomDetailsBean);
				dispatcher = request.getRequestDispatcher("/pages/enterBookingDetails.jsp");
				dispatcher.forward(request, response);
			}
			else if(hiddenValue.equals("room")){
				roomList=(ArrayList<RoomDetailsBean>)session.getAttribute("roomList");
				Iterator<RoomDetailsBean> roomIterator = roomList.iterator();
				//String delcount1 = request.getParameter("deleteValue");
				int delcount = Integer.parseInt(option);
				int count=0,id=0;
				System.out.println(delcount);
				while(roomIterator.hasNext())
				{
					RoomDetailsBean room = roomIterator.next();
					if(count==delcount)
					{
						id = room.getRoom_id();
						try {
							adminService.deleteRoom(id);;
						} catch (BookingException e) {
							e.printStackTrace();
						}
						break;
					}
					count++;
				}
				request.setAttribute("id", id);
				String message = "Room successfully deleted of room ID - ";
				request.setAttribute("message", message);
				dispatcher = request.getRequestDispatcher("/pages/successPage.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
