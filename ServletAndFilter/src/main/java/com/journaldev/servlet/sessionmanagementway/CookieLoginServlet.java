
package com.journaldev.servlet.sessionmanagementway;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 * 
 * https://www.journaldev.com/1877/servlet-tutorial-java
 */
@WebServlet("/CookieLoginServlet")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final List<String> userID = new ArrayList<String>(Arrays.asList("awi", "nas", "kan", "nan"));
	private final String password = "awi";

	public void init() throws ServletException {

		System.out.println("<CookieLoginServlet> <init>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean cookieCheck = false;
		String user = request.getParameter("userCookie");
		String pwd = request.getParameter("pwdCookie");
		if (request.getCookies() != null) {
			Cookie[] cook = request.getCookies();
			for (Cookie c : cook) {
				System.out.println(c.getName() + " - " + c.getValue());

				if (c.getName().equals("CookieUser") && c.getValue().equals(user)) {
					System.out.println("Present in cookie");
					cookieCheck = true;
				}

			}
		}
		if (cookieCheck || hardCodedCheck(user, pwd)) {
			Cookie loginCookie = new Cookie("CookieUser", user);

			loginCookie.setMaxAge(2 * 60); // 120 Seconds

			loginCookie.setComment("Cookie to check whether logged in or not");

			// Only in the particular domain request the cookie will be preset
			// request.getCookie().get("CookieUser") will be null in localhost if some other
			// domain is set

			// loginCookie.setDomain("te.sella.it");

			System.out.println("request.isSecure() " + request.isSecure());
			if (request.isSecure()) {
				loginCookie.setSecure(true);
			}

			loginCookie.setHttpOnly(true);

			// loginCookie.setPath("/servlet");

			loginCookie.setVersion(5);

			response.addCookie(loginCookie);
			response.sendRedirect("CookieLoginSuccess.jsp?user=" + user);
		} else {
			// RequestDispatcher interface is used to forward the request to another
			// resource that can be HTML, JSP or another servlet in the same context....
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			response.addCookie(new Cookie("Error", "Invalid"));
			// This cookie will not be in request, because is is forward / include
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

	private boolean hardCodedCheck(String user, String pwd) {
		System.out.println("hardCodedCheck");
		return userID.contains(user) && password.equals(pwd);
	}

}
