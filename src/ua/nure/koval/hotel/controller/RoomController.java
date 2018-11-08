package ua.nure.koval.hotel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.service.RoomService;

/**
 * Servlet implementation class RoomController
 */
@WebServlet("/rooms")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomService rs;
	
		
	public RoomController() {
		super();
		if (rs == null) {
			rs = new RoomService();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Room> rooms = rs.getAllRooms();
		req.setAttribute("roomList", rooms);
		req.getRequestDispatcher("free_rooms.jsp").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
