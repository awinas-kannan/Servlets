package com.journaldev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		System.out.println("<LogoutServlet> <init>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String user = request.getParameter("user");
		String logoutrequest = request.getParameter("from");
		System.out.println("LogoutServlet " + user);
		System.out.println("LogoutServlet Request Type " + logoutrequest);

		if ("removeCookie".equals(logoutrequest)) {
			System.out.println("<LogoutServlet> <removeCookie>");
			Cookie loginCookie = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("CookieUser")) {
						loginCookie = cookie;
						break;
					}
				}
			}
			if (loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);

			}

		} else if ("removeSession".equals(logoutrequest)) {
			System.out.println("<LogoutServlet> <removeSession>");
			HttpSession session = request.getSession(false);
			if (session != null) {
				System.out.println("session not null");
				session.removeAttribute("sessionUser");
				// session.invalidate();
			}
		}

		response.sendRedirect("login.jsp");

	}

}