package ua.nure.koval.hotel.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.User;
import ua.nure.koval.hotel.entity.enums.Status;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.service.RoomService;

/**
 * Servlet implementation class CreateRequestFromRoom
 */
@WebServlet({"/request_by_room" })
public class CreateRequestFromRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestService reqServ = null;
	RoomService roomServ = null;
	
    public CreateRequestFromRoom() {
        super();
        reqServ = new RequestService();
        roomServ = new RoomService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("show_message.jsp").forward(request,response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		response.sendRedirect("request_by_room");
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (request.getParameter("room") == null || request.getParameter("duration") == null) {
			session.setAttribute("message", "Failure");
		} else {
			Long roomId = Long.parseLong(request.getParameter("room"));
			int duration = Integer.parseInt(request.getParameter("duration"));
			Room room = roomServ.getById(roomId);
			room.setStatus(Status.BOOKED);
			roomServ.updateRoom(room);
			User user = (User) session.getAttribute("user");
			if (reqServ.createFromRoom(room, duration, user.getId())) {
				session.setAttribute("message", "Success");				
			} else {
				session.setAttribute("message", "Failure");
			}
		}
	}

}
