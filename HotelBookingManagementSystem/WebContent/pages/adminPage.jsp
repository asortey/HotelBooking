<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="com.hotelmanagement.dto.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
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
	<form action="login" method="post">
		<h4>Please select one of the options</h4>
		<input type="submit" name="submit" value="Add new Hotel"></br><br>
		<input type="submit" name="submit" value="Update existing hotel"></br><br>
		<input type="submit" name="submit" value="Delete a hotel"></br><br>
		<input type="submit" name="submit" value="Add new room"></br><br>
		<input type="submit" name="submit" value="Update existing room"></br><br>
		<input type="submit" name="submit" value="Delete a room"></br><br>
		<input type="submit" name="submit" value="View all hotels"></br><br>
		<input type="submit" name="submit" value="View bookings of specific hotel"></br><br>
		<input type="submit" name="submit" value="View bookings of specific date"></br><br>
		<input type="submit" name="submit" value="View guest list for a specific hotel"></br><br>
		<input type="submit" name="submit" value="Log Out">
		
	</form>
</body>
</html>