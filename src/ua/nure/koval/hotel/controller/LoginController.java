package ua.nure.koval.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.service.Authentication;
import ua.nure.koval.hotel.service.ParamValidation;
import ua.nure.koval.hotel.service.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login_check")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParamValidation pv = null;
	private UserService us = null;
	
	public LoginController() {
		super();
		pv = new ParamValidation();
		us = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = null;
		if (session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
		}
		if (user.getRole().getName().equalsIgnoreCase("manager")) {
			request.getRequestDispatcher("manager_cabinet.jsp").forward(request,response);
		} else if (user.getRole().getName().equalsIgnoreCase("client")){
			request.getRequestDispatcher("client_cabinet.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if (pv.checkForMissing(login, password)) {
			session.setAttribute("message", "Incorrect credentials");
			request.getRequestDispatcher("show_message.jsp").forward(request,response);	
		} else {
			if(Authentication.check(login, password)) {
				User user = us.getUserByLogin(login);
				session.setAttribute("user", user);
				response.sendRedirect("login_check");
			}
		}
	}

}
