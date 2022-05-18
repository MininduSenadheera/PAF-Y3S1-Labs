package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthAPI
 */
@WebServlet("/AuthAPI")
public class AuthAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User userObj = new User();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String authStatus = userObj.login(request.getParameter("txtUsername"),request.getParameter("txtPassword"));
		
		String output = "";
		
		if (authStatus.equals("success")) {
			output = "{\"status\":\"success\", \"data\": \"\"}"; 
			session.setAttribute("Username",request.getParameter("txtUsername")); 
		} else {
			output = "{\"status\":\"error\", \"data\": \"" + authStatus + "\"}";
		}
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		session.invalidate(); 
		response.getWriter().write("success");
	}

}
