<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean, com.hotelmanagement.dto.HotelBean, java.util.ArrayList, java.util.Iterator" %>
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
				<td>Hotel ID:</td>
				<td><select name="hotelID">
				<%
				
				HotelBean hotelBean;
				ArrayList<HotelBean> hotelList = (ArrayList<HotelBean>)session.getAttribute("hotelList");
				Iterator<HotelBean> HotelBeanIterator = hotelList.iterator();
				int i=0;
				while(HotelBeanIterator.hasNext()){
					hotelBean = HotelBeanIterator.next();
					
			%>
				
					<option value="<%=hotelBean.getHotel_id() %>"><%=hotelBean.getHotel_id() %></option>
			<%
				}
			%>
					</select>
				</td>
			</tr>
			<tr>
				<td><select name="parameter">
						<option value="city">City</option>
						<option value="hotel_name">Hotal Name</option>
						<option value="address">Address</option>
						<option value="description">Description</option>
						<option value="avg_rate_per_night">Average Rate per Night</option>
						<option value="phone_no1">Phone No. 1</option>
						<option value="phone_no2">Phone No. 2</option>
						<option value="rating">Rating</option>
						<option value="email">Email</option>
						<option value="fax">Fax</option>
					</select>
				</td>
				<td><input type="text" name="parameterValue" required=""/></td>
			</tr>	
		</table><br>
		<input type="submit" name="submit" value="Update Hotel"><br><br>
		</form>
	<form action="login" method="post">
		<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>