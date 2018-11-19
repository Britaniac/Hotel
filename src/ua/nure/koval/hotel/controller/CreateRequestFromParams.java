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
		request.getRequestDispatcher("show_message.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		response.sendRedirect("request_by_params");
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (request.getParameter("capacity") == null || request.getParameter("class") == null || request.getParameter("duration") == null) {
			session.setAttribute("message", "Failure");
		} else {
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			RoomClass rClass = RoomClass.fromString(request.getParameter("class"));
			int duration = Integer.parseInt(request.getParameter("duration"));
			User user = (User) session.getAttribute("user");
			if (reqServ.createFromParams(capacity, rClass, duration, user.getId())) {
				session.setAttribute("message", "Success");
			} else {
				session.setAttribute("message", "Failure");
			}
		}		
	}
	

}
