<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean, com.hotelmanagement.dto.BookingBean, java.util.ArrayList, java.util.Iterator" %>
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
	<%
		UserBean userBean = (UserBean)session.getAttribute("userBean");
	%> 
	<h3>Welcome <%=userBean.getUser_name() %></h3>
	<table align="center" >
		<thead>
			<tr>
				<td>Booking ID</td>
				<td>Room ID</td>
				<td>Booked From</td>
				<td>Booked To</td>
				<td>Number of Adults</td>
				<td>Number of Children</td>
				<td>Amount</td>
			</tr>
		</thead>
		<%
				BookingBean bookingBean;
				ArrayList<BookingBean> bookingList = (ArrayList<BookingBean>)request.getAttribute("bookingList");
				Iterator<BookingBean> bookingBeanIterator = bookingList.iterator();
				while(bookingBeanIterator.hasNext()){
					bookingBean = bookingBeanIterator.next();
					
		%>
			<tr>
				<td><%=bookingBean.getBooking_id() %></td>
				<td><%=bookingBean.getRoom_id() %></td>
				<td><%=bookingBean.getBooked_from() %></td>
				<td><%=bookingBean.getBooked_to() %></td>
				<td><%=bookingBean.getNo_of_adults() %></td>
				<td><%=bookingBean.getNo_of_children() %></td>
				<td><%=bookingBean.getAmount() %></td>
			</tr>
		<%
				}
		%>
	</table><br><br>
	<form action="login" method="post">
		<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>