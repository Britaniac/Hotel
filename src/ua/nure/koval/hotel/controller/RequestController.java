package ua.nure.koval.hotel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.koval.hotel.service.RequestService;

/**
 * Servlet implementation class RequestController
 */
@WebServlet("/request")
public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestService rs = null;

    public RequestController() {
        super();
        rs = new RequestService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
