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
	<%
		UserBean userBean = (UserBean)session.getAttribute("userBean");
	%> 
	<h3>Welcome <%=userBean.getUser_name() %></h3>
	
	<table align="center">
		<thead>
			<tr>
				<td>User ID</td>
				<td>User Name</td>
				<td>Mobile No.</td>
				<td>Phone</td>
				<td>Address</td>
				<td>Email</td>
			</tr>
		</thead>
		<%
				UserBean user =null;
				ArrayList<UserBean> userList = (ArrayList<UserBean>)request.getAttribute("userList");
				Iterator<UserBean> userBeanIterator = userList.iterator();
				while(userBeanIterator.hasNext()){
					user = userBeanIterator.next();
					
		%>
			<tr>
				<td><%= user.getUser_id()%></td>
				<td><%= user.getUser_name()%></td>
				<td><%= user.getMobile_no()%></td>
				<td><%= user.getPhone()%></td>
				<td><%= user.getAddress()%></td>
				<td><%= user.getEmail()%></td>
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