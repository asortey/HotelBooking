<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.hotelmanagement.dto.UserBean" %>

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
	<%String message =(String)request.getAttribute("message"); %>
	<form action="login" method="post">
		<%if(message!=null){
		%>
			<h4 style="color: red;"><%=message%></h4>
		<% }%>
		City : <input type="text" name="city" required=""/><br><br>
		<input type="submit" name="submit" value="Search City" /><br><br>
		</form>
	<form action="login" method="post">
			<input type="submit" name="submit" value="Back to menu"/>
	</form>
</body>
</html>