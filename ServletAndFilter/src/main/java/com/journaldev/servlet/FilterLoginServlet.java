
package com.journaldev.servlet;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/FilterLoginServlet")
public class FilterLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final List<String> userID = new ArrayList<String>(Arrays.asList("awi", "nas", "kan", "nan"));
	private final String password = "awi";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("fUser");
		String pwd = request.getParameter("fPwd");

		if (userID.contains(user) && password.equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("isSuccess", "true");
			session.setAttribute("user", user);
			// setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30 * 60);
			Cookie userdetails = new Cookie(user, pwd);
			userdetails.setMaxAge(30 * 60);
			response.addCookie(userdetails);

			response.sendRedirect("FilterLoginSuccess.jsp");

		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

}
