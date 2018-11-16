package ua.nure.koval.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.service.UserService;
import ua.nure.koval.hotel.util.Authentication;
import ua.nure.koval.hotel.util.ParamValidation;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login_check")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = null;
	private RequestService rs = null;
	
	public LoginController() {
		super();
		us = new UserService();
		rs = new RequestService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = null;
		if (session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
		}
		if (user.getRole().getName().equalsIgnoreCase("manager")) {
			List<Request> requests = rs.getUnprocessed();
			request.setAttribute("requests", requests);
			request.getRequestDispatcher("manager_cabinet.jsp").forward(request,response);
		} else if (user.getRole().getName().equalsIgnoreCase("client")){
			List<Request> requests = rs.getByUser(user);
			session.setAttribute("requests", requests);
			request.getRequestDispatcher("client_cabinet.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(Authentication.check(login, password)) {
			User user = us.getUserByLogin(login);
			System.out.println(user);
			session.setAttribute("user", user);
			response.sendRedirect("login_check");
		} else {
			session.setAttribute("message", "Failure");
			request.getRequestDispatcher("show_message.jsp").forward(request,response);
		}
	}

}
