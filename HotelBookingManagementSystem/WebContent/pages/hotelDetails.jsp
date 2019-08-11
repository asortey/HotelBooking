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
			<thead>
				<tr>
					<td>Hotel ID</td>
					<td>Hotel Name</td>
					<td>City</td>
					<td>Address</td>
					<td>Description</td>
					<td>Average Rate per Night</td>
					<td>Email</td>
					<td>Fax</td>
					<td>Rating</td>
					<td>Phone 1</td>
					<td>Phone 2</td>	
				</tr>
			</thead>
			<%
				
				HotelBean hotelBean;
				ArrayList<HotelBean> hotelList = (ArrayList<HotelBean>)session.getAttribute("hotelList");
				Iterator<HotelBean> HotelBeanIterator = hotelList.iterator();
				int i=0;
				while(HotelBeanIterator.hasNext()){
					hotelBean = HotelBeanIterator.next();
					
			%>
				<tr>
					<td><%=hotelBean.getHotel_id() %></td>
					<td><%=hotelBean.getHotel_name() %></td>
					<td><%=hotelBean.getCity()%></td>
					<td><%=hotelBean.getAddress() %></td>
					<td><%=hotelBean.getDescription() %></td>
					<td><%=hotelBean.getAvg_rate_per_night() %></td>
					<td><%=hotelBean.getEmail() %></td>
					<td><%=hotelBean.getFax() %></td>
					<td><%=hotelBean.getRating() %></td>
					<td><%=hotelBean.getPhone_no1() %></td>
					<td><%=hotelBean.getPhone_no2() %></td>
					</tr>
			<%
				i++;
				}
				
			%>
			</table><br><br>
			<input type="submit" name="submit" value="Back to menu"/>
		</form>
</body>
</html>