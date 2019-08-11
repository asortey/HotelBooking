<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean, com.hotelmanagement.dto.RoomDetailsBean, com.hotelmanagement.dto.HotelBean, java.util.ArrayList, java.util.Iterator" %>
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
	<%UserBean userBean = (UserBean)session.getAttribute("userBean");%> 
	<h3>Welcome <%=userBean.getUser_name() %></h3>
	
	
	<form action="login" method="post">
		<table align="center">
			<tr>
				<td>Room ID:</td>
				<td><select name="roomID">
				<%
				
				RoomDetailsBean roomDetailsBean;
				ArrayList<RoomDetailsBean> roomList = (ArrayList<RoomDetailsBean>)session.getAttribute("roomList");
				Iterator<RoomDetailsBean> RoomDetailsBeanIterator = roomList.iterator();
				int i=0;
				while(RoomDetailsBeanIterator.hasNext()){
					roomDetailsBean = RoomDetailsBeanIterator.next();
					
			%>
				
					<option value="<%=roomDetailsBean.getRoom_id() %>"><%=roomDetailsBean.getRoom_id() %></option>
			<%
				}
			%>
					</select>
				</td>
			</tr>
			<tr>
				<td><select name="parameter">
						<option value="room_no">Room No</option>
						<option value="room_type">Room Type</option>
						<option value="per_night_rate">Per Night rate</option>
						<option value="availability">Availability</option>
						</select>
				</td>
				<td><input type="text" name="parameterValue" required=""/></td>
			</tr>	
		</table><br>
		<input type="submit" name="submit" value="Update Room"><br><br>
	</form>
	<form action="login" method="post">
		<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>