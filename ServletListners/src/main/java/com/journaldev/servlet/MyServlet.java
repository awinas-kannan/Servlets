package com.journaldev.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//http://localhost:8080/ServletListners/MyServlet
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext ctx = request.getServletContext();

		// ServletContextAttributeListner
		ctx.setAttribute("User", "Pankaj");
		String user = (String) ctx.getAttribute("User");
		ctx.removeAttribute("User");

		// requestAttributeListener
		request.setAttribute("requestname", "awi");
		request.getAttribute("name");
		request.removeAttribute("requestname");

		// SessionListner
		HttpSession session = request.getSession();

		// SessionAttributeListner
		session.setAttribute("Name", "awi");
		session.removeAttribute("Name");

		// SessionListner
		session.invalidate();

		PrintWriter out = response.getWriter();
		out.write("Hi " + user);
	}

}
