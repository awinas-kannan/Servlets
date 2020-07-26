
package com.journaldev.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		System.out.println(context.getClass());
		System.out.println("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("<<AuthenticationFilter>><<doFilter>>");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		System.out.println("Requested Resource::" + uri);

		//

		HttpSession session = req.getSession(false);
		System.out.println(session == null);
		System.out.println("uri.endsWith(\"login.jsp\")" + uri.endsWith("login.jsp"));
		System.out.println("uri.endsWith(\"LoginServlet\")" + uri.endsWith("LoginServlet"));
		System.out.println(!(uri.endsWith("login.jsp") || uri.endsWith("LoginServlet")));

		// if it is login.jsp or end with loginservlet we are not checking anything
		if (uri.endsWith("login.jsp") || uri.endsWith("LoginServlet")) {
			System.out.println("<inside >login.jsp or end  LoginServlet");
			chain.doFilter(request, response);
			return;
		}

		// If the user directly tries to access following link
		// http://localhost:8080/ServletAndFilter/CookieLoginSuccess.jsp
		// Cookie Login Check Filter
		if (uri.contains("Cookie") || "removeCookie".equals(req.getParameter("from"))) {
			if (req.getCookies() != null) {
				Cookie[] cook = req.getCookies();
				System.out.println("********* Printing Cookies ********");
				for (Cookie c : cook) {
					System.out.println(c.getName() + " - " + c.getValue());
					if (c.getName().equals("CookieUser")) {
						System.out.println("Cookie Present ");
						chain.doFilter(request, response);
						return;
					}

				}
			}
		}
		// If the user directly tries to access following link
		// http://localhost:8080/ServletAndFilter/SessionLoginSuccess.jsp
		// Session Login Check Filter
		if (uri.contains("Session") || "removeSession".equals(req.getParameter("from"))) {
			if (session != null) {
				System.out.println("creation time " + session.getCreationTime());
				System.out.println("Session ID  " + session.getId());
				Enumeration<String> params = session.getAttributeNames();
				while (params.hasMoreElements()) {
					String name = params.nextElement();
					System.out.println("params.nextElement() " + name);
					String value = request.getParameter(name);
					System.out.println(req.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
				}

				if (session.getAttribute("sessionUser") != null) {
					System.out.println("User Present in Session ");
					chain.doFilter(request, response);
					return;
				}

			}
		}

		res.sendRedirect("login.jsp");
		/*
		 * Boolean isSuccess = true; if (session != null) {
		 * System.out.println("isSuccess" + (String) session.getAttribute("isSuccess"));
		 * isSuccess = Boolean.valueOf( session.getAttribute("isSuccess") != null ?
		 * (String) session.getAttribute("isSuccess") : "false");
		 * System.out.println("isSuccess Var" + isSuccess); } if (session == null &&
		 * !(uri.endsWith("login.jsp") || uri.endsWith("LoginServlet"))) {
		 * System.out.println("Unauthorized access request");
		 * res.sendRedirect("login.jsp"); } else if (session != null && !isSuccess) { //
		 * pass the request along System.out.println("Unauthorized access request");
		 * res.sendRedirect("login.jsp"); } else { chain.doFilter(request, response); }
		 */

	}

	public void destroy() {
		// close any resources here
	}

}
