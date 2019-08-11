<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.time.LocalDate,com.hotelmanagement.dto.UserBean, java.util.ArrayList, java.util.Iterator,com.hotelmanagement.dto.RoomDetailsBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<style type="text/css">
	<%@include file="/WEB-INF/css/style.css"%>
</style>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript">
   $( function() {
    $( ".datepicker" ).datepicker({
    	minDate : new Date()
    });
  } ); 
   $( function() {
	   var date = new Date();
	   var currentMonth = date.getMonth();
		var currentDate = date.getDate();
		var currentYear = date.getFullYear();
	    $( ".datepicker1" ).datepicker({
	    	minDate : new Date(currentYear, currentMonth, currentDate+2)
	    });
	  } ); 
  </script>
  
</head>
<body>
 	<h1>Hotel Booking Management System</h1>
	<%	UserBean userBean = (UserBean)session.getAttribute("userBean");
		RoomDetailsBean roomDetailsBean = (RoomDetailsBean)request.getAttribute("roomDetailsBean");
	%> 
	<h3>Welcome <%=userBean.getUser_name() %></h3>
	
	
	<form action="login" method="post">
		<table align="center">
			<tr>
				<td>User ID:</td>
				<td><input type="text" value="<%=userBean.getUser_id()%>" name="user_id" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Room ID:</td>
				<td><input type="text" value="<%=roomDetailsBean.getRoom_id() %>" name="room_id" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Check In Date:<span class="required">*</span></td>
				<td><input type="text" class="datepicker" name="checkindate" id="checkin"  required=""></td>
			</tr>
			<tr>
				<td>Check Out Date:<span class="required">*</span></td>
				<td><input type="text" class="datepicker1" id="checkout" name="checkoutdate"  required=""></td>
			</tr>
			<tr>
				<td>Number of Adults:</td>
				<td><select name="no_of_adults">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select></td>
			</tr>
			<tr>
				<td>Number of Children:</td>
				<td><select name="no_of_children">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
					</select></td>
			</tr>
		</table>
		<input type="submit" value="Book Hotel Room" name="submit"/><br><br>
			
	</form>
	<form action="login" method="post">
		<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>