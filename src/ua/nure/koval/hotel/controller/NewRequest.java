package ua.nure.koval.hotel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.service.RoomService;
import ua.nure.koval.hotel.util.RoomCapacityComparator;
import ua.nure.koval.hotel.util.RoomClassComparator;
import ua.nure.koval.hotel.util.RoomCostComparator;

/**
 * Servlet implementation class CreateRequest
 */
@WebServlet("/new_request")
public class NewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomService rs = null;
	
    public NewRequest() {
        super();
        rs = new RoomService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("create_request.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Room> rooms = rs.getFreeRooms();
		if(request.getParameter("sort") != null) {
			if (request.getParameter("sort").equalsIgnoreCase("cost")) {
				Collections.sort(rooms, new RoomCostComparator());
			} else if (request.getParameter("sort").equalsIgnoreCase("capacity")) {
				Collections.sort(rooms, new RoomCapacityComparator());
			} else if (request.getParameter("sort").equalsIgnoreCase("class")) {
				Collections.sort(rooms, new RoomClassComparator());
			}
		}
		session.setAttribute("rooms", rooms);
		response.sendRedirect("new_request");
	}

}
