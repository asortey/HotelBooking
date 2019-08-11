<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean, com.hotelmanagement.dto.HotelBean, java.util.ArrayList, java.util.Iterator"%>
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
			<col width="150">
  			<col width="200">
		<tr>
			<td>Hotel ID: </td>
			<td><select name="hotelID">
			<%
					
					HotelBean hotelBean;
					ArrayList<HotelBean> hotelList = (ArrayList<HotelBean>)session.getAttribute("hotelList");
					Iterator<HotelBean> HotelBeanIterator = hotelList.iterator();
					int i=0;
					while(HotelBeanIterator.hasNext()){
						hotelBean = HotelBeanIterator.next();
						
			%>
				
								<option value="<%=hotelBean.getHotel_id()%>"><%=hotelBean.getHotel_id()%></option>
						 
			<%
						}
			%>
				  </select></td>
		</tr>
		
		<tr>
			<td>Room No.:<span class="required">*</span> </td>
			<td><input type="text" name="roomNo" placeholder="Enter room no. eg.100" pattern="[1-9][0-9]{2}"required=""></td>
		</tr>
		
		<tr>
			<td>Room Type:<span class="required">*</span> </td>
			<td><select name="roomType">
							<option value="Non AC">Non AC</option>
							<option value="AC Prime">AC Prime</option>
							<option value="AC Delux">AC Delux</option>
							<option value="Super AC Delux">Super AC Delux</option>
						</select></td>
		</tr>
		
		<tr>
			<td>Rate per Night:<span class="required">*</span> </td>
			<td><input type="text" name="per_night_rate" pattern="[1-9][0-9]{0,3}" required=""/></td>
		</tr>
			
	</table><br><br>
			
			<input type="submit" value="Add Room" name="submit"/><br><br>
	</form>
	<form action="login" method="post">
		<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>