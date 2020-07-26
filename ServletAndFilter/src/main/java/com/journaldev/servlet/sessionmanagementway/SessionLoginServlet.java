
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 * https://www.journaldev.com/1877/servlet-tutorial-java
 */
@WebServlet("/SessionLoginServlet")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final List<String> userID = new ArrayList<String>(Arrays.asList("awi", "nas", "kan", "nan"));
	private final String password = "awi";

	public void init() throws ServletException {

		System.out.println("<SessionLoginServlet> <init>");

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("sessionUser");
		String pwd = request.getParameter("sessionPwd");
		
		if (userID.contains(user) && password.equals(pwd)) {
			HttpSession session = request.getSession();
			System.out.println("Session Id" + session.getId());
			session.setAttribute("sessionUser", user);
			session.setAttribute("user", user);
			// setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30 * 60);
			/*if ("awi".equals(user)) {

				// Url rewriting (used when cookies are disabled in browser :: cookie have
				// jsessionid ::)
				// If cookies are not disabled, you won’t see jsessionid in the URL because
				// Servlet Session API will use cookies in that case.
				String encodedURL = response.encodeRedirectURL("SessionLoginSuccess.jsp");
				System.out.println(encodedURL);
				response.sendRedirect(encodedURL);
			} else {*/
				response.sendRedirect("SessionLoginSuccess.jsp");
			//}
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

}
