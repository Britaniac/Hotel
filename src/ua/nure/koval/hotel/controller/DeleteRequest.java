package ua.nure.koval.hotel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.Role;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.service.UserService;

@WebServlet("/delete_request")
public class DeleteRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestService rs = null;
	UserService us = null;
	
	public DeleteRequest(){
		super();
		rs = new RequestService();
		us = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("show_message.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Long id = Long.parseLong(request.getParameter("id"));
		Request req = rs.getRequestById(id);
		User user = (User) session.getAttribute("user");
		if (req.getUserID() == user.getId()) {
			delete(req, session);
		} else if (user.getRole().equals(Role.MANAGER)) {
			delete(req, session);
		}
		response.sendRedirect("delete_request");
	}
	
	private void delete(Request req, HttpSession session) {
		if (rs.deleteRequest(req)) {
			session.setAttribute("message", "Succes");
		} else {
			session.setAttribute("message", "Failure");
		}
	}

}
