
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Login Page</title>
</head>
<body>
	<br> Sevle<%-- if action="ls" its failing in some condition --%>
	<form action="LoginServlet" method="post">

		Username: <input type="text" name="user"> <br> Password:
		<input type="password" name="pwd"> <br> <input
			type="submit" value="Login">
	</form>
	<br> Cookie
	<form action="CookieLoginServlet" method="post">

		Username: <input type="text" name="userCookie"> <br>
		Password: <input type="password" name="pwdCookie"> <br> <input
			type="submit" value="Login">


	</form>
	
	<%-- <%
		Cookie[] cook = request.getCookies();
		for (Cookie c : cook) {
	%>
	<%=c.getName()%>
	<%=c.getValue()%>
	<br>
	<%
		}
	%> --%>
	
	<br> Session
	<form action="SessionLoginServlet" method="post">

		Username: <input type="text" name="sessionUser"> <br>
		Password: <input type="password" name="sessionPwd"> <br> <input
			type="submit" value="Login">


	</form>
	
	<br> Filter
	<form action="FilterLoginServlet" method="post">

		Username: <input type="text" name="fUser"> <br>
		Password: <input type="password" name="fPwd"> <br> <input
			type="submit" value="Login">


	</form>
</body>
</html>