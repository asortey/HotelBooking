<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean, com.hotelmanagement.dto.BookingBean,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	<%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
	<h1>Hotel Booking Management System</h1>
	<%	UserBean userBean = (UserBean)session.getAttribute("userBean");
		BookingBean bookingBean = (BookingBean)request.getAttribute("bookingBean");
		int days = (int)request.getAttribute("days");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    String booked_from = df.format(bookingBean.getBooked_from());
	    String booked_to = df.format(bookingBean.getBooked_to() );
	%> 
	<h3>Welcome <%=userBean.getUser_name() %></h3>
	<form action="login" method="post">
	
		<table align="center">
			<tr>
				<td>User ID:</td>
				<td><input type="text" value="<%=bookingBean.getUser_id() %>" name="user_id" readonly></td>
			</tr>
			<tr>
				<td>Room ID:</td>
				<td><input type="text" value="<%=bookingBean.getRoom_id() %>" name="room_id" readonly></td>
			</tr>
			<tr>
				<td>Booked From:</td>
				<td><input type="text" value="<%=booked_from%>" name="booked_from" readonly></td>
			</tr>
			<tr>
				<td>Booked To:</td>
				<td><input type="text" value="<%=booked_to%>" name="booked_to" readonly></td>
			</tr>
			<tr>
				<td>No. of days:</td>
				<td><input type="text" value="<%=days%>" name="days" readonly/></td>
			</tr>
			<tr>
				<td>Number of Adults:</td>
				<td><input type="text" value="<%=bookingBean.getNo_of_adults() %>" name="no_of_adults" readonly></td>
			</tr><tr>
				<td>Number of Children:</td>
				<td><input type="text" value="<%=bookingBean.getNo_of_children() %>" name="no_of_children" readonly></td>
			</tr>
			<tr>
				<td>Amount:</td>
				<td><input type="text" value="<%=bookingBean.getAmount()%>" name="amount" readonly/></td>
			</tr>
		</table>
		<input type="submit" value="Confirm" name="submit"/><br><br>
			<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>