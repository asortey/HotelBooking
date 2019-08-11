<%@page import="com.hotelmanagement.dto.RoomDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean, java.util.ArrayList, java.util.Iterator" %>
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
	<%UserBean userBean = (UserBean)session.getAttribute("userBean");
		String city = (String)request.getAttribute("city");
	%> 
	<h3>Welcome <%=userBean.getUser_name() %></h3>
	<form action="login" method="post">
	<table align="center">
			<thead>
				<tr>
					<td>Hotel ID</td>
					<td>Room ID</td>
					<td>Room No</td>
					<td>Room Type</td>
					<td>Per Night Rate</td>
					<td>Avalibility</td>
				</tr>
			</thead>
			<%
				
				RoomDetailsBean roomDetailsBean;
				ArrayList<RoomDetailsBean> hotelRoomList = (ArrayList<RoomDetailsBean>)request.getAttribute("hotelRoomList");
				Iterator<RoomDetailsBean> RoomDetailsBeanIterator = hotelRoomList.iterator();
				int i=0;
				while(RoomDetailsBeanIterator.hasNext()){
					roomDetailsBean = RoomDetailsBeanIterator.next();
					
			%>
				<tr>
					<td><%=roomDetailsBean.getHotel_id()%></td>
					<td><%=roomDetailsBean.getRoom_id()%></td>
					<td><%=roomDetailsBean.getRoom_no()%></td>
					<td><%=roomDetailsBean.getRoomm_type()%></td>
					<td><%=roomDetailsBean.getPer_night_rate()%></td>
					<td><%=roomDetailsBean.getAvailability()%></td>
					<td><button type="submit" value="<%=i%>" name="submit">Book Room</button></td>
					</tr>
			<%
				i++;
				}
			%>
			</table>
			<input type="hidden" name="delete" value="bookRoom"/>
			<input type="hidden" name="city" value="<%=city %>"/><br><br>
			<input type="submit" name="submit" value="Back to menu"/>
		</form>
</body>
</html>