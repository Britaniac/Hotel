package ua.nure.koval.hotel.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.RoomClass;
import ua.nure.koval.hotel.service.RequestService;

/**
 * Servlet implementation class CreateRequestFromParams
 */
@WebServlet("/request_by_params")
public class CreateRequestFromParams extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RequestService reqServ = null;
    	
    public CreateRequestFromParams() {
        super();
        reqServ = new RequestService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int capacity = (int) session.getAttribute("capacity");
		RoomClass rClass = (RoomClass) session.getAttribute("class");
		LocalDate date = (LocalDate) session.getAttribute("date");
		if (reqServ.createFromParams(capacity, rClass, date, user.getId())) {
			session.setAttribute("message", "Success");
			request.getRequestDispatcher("show_message.jsp").forward(request,response);
		} else {
			session.setAttribute("message", "Failure");
			request.getRequestDispatcher("show_message.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("capacity") == null || request.getParameter("class") == null || request.getParameter("to") == null) {
			session.setAttribute("message", "Failure");
			request.getRequestDispatcher("show_message.jsp").forward(request,response);
		} else {
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			RoomClass rClass = RoomClass.fromString(request.getParameter("class"));
			LocalDate date = LocalDate.parse(request.getParameter("to"));
			session.setAttribute("capacity", capacity);
			session.setAttribute("class", rClass);
			session.setAttribute("date", date);
			response.sendRedirect("request_by_params");			
		}
	}

}
