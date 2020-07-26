
package com.journaldev.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Tutorial - Servlet Example
 * 
 * Servlet context vs Servlet config ???
 * 
 * Servlet context -- application level Servlet congig -- Servlet level
 * 
 * RequestDispatcher is created from servlet context since it is global level
 * 
 * redirect is present in response since it is going to external application
 * 
 * 
 * @WebServlet should extend httpservlet and shoul have urlpattern
 * 
 */
@WebServlet(description = "Login Servlet", urlPatterns = { "/LoginServlet", "/ls" }, initParams = {
		@WebInitParam(name = "user", value = "awi"), @WebInitParam(name = "password", value = "awi") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		// we can create DB connection resource here and set it to Servlet context
		if (getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db")
				&& getServletContext().getInitParameter("dbUser").equals("mysql_user")
				&& getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd")) {
			getServletContext().setAttribute("DB_Success", "True");
			System.out.println("<LoginServlet> <init>");
		}

		else
			throw new ServletException("DB Connection error");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		// get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		// logging example
		System.out.println("User=" + user + "::password=" + pwd);

		if (userID.equals(user) && password.equals(pwd)) {
			Cookie c = new Cookie("USERLOGGEDIN", "true");
			response.addCookie(c);
			response.sendRedirect("LoginSuccess.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			// rd.forward(request, response);
			rd.include(request, response);
			out.println("<font color=red>Either user name or password is wrong.</font>");

		}

	}
}