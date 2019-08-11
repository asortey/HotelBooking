<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Booking</title>
<style>
	<%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
	
	<h1>Hotel Booking Management System</h1><br>
	<% String message = (String)request.getAttribute("message");
		String error = (String) request.getAttribute("error");
	   if(message!=null){%>
	   <h2><%=message %></h2>
	   <%} %>
	   <%if(error!=null){ %>
	   <h3 style="color: red;"><%=error %></h3>
	   <%} %>
	<form action="login">
		<input type="submit" value="Sign In" name="submit"/><br></br>
		<input type="submit" value="Sign Up" name="submit"/>
	</form>
</body>
</html>