<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
	<%
		String user = request.getParameter("user");
	%>
	<h3>
		Hi
		<%=user%>
		, Login successful.
	</h3>
	<a href="login.jsp">Login Page</a>
	<br>
	<br>

	<%
		Cookie[] cook = request.getCookies();
		for (Cookie c : cook) {
	%>
	<%=c.getName()%>
	<%=c.getValue()%>
	<%=c.getComment()%>
	<%=c.getSecure()%>
	<%=c.getMaxAge()%>
	<%=c.getPath()%>
	<%=c.getDomain()%>
	<%=c.getVersion()%>
	<br>
	<%
		}
	%>

	<form action="LogoutServlet" method="post">
		<input type="hidden" name="user" id="user" value="<%=user%>" /> <input
			type="hidden" name="from" id="from" value="removeCookie" /> <input
			type="submit" value="Logout">
	</form>

</body>
</html>