package ua.nure.koval.hotel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.service.UserService;
import ua.nure.koval.hotel.util.ParamValidation;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/register_user")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us;
	private ParamValidation pv = null;
	

	public RegistrationController() {
		super();
		if (us == null) {
			us = new UserService();
		}
		pv = new ParamValidation();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("message") != null) {
			request.getRequestDispatcher("show_message.jsp").forward(request,response);	
		} else {
			System.out.println("Something wrong");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String locale = "en";
		HttpSession session = request.getSession(true);
/*		if(pv.checkForMissing(login, password, fName, lName, email, locale)) {
			String message = "One or more of the fields are empty";
			session.setAttribute("message", message);
		} else {
			String message = us.registerUser(login, password, fName, lName, email, locale);
			session.setAttribute("message", message);
		}*/
		String message = us.registerUser(login, password, fName, lName, email, locale);
		session.setAttribute("message", message);
		response.sendRedirect("register_user");
	}
	
}
