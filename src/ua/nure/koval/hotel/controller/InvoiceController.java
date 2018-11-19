package ua.nure.koval.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.service.InvoiceService;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.service.RoomService;

/**
 * Servlet implementation class InvoiceController
 */
@WebServlet("/invoice")
public class InvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InvoiceService invServ = null;
    private RequestService reqServ = null;
    private RoomService roomServ = null;
    
    public InvoiceController() {
        super();
        invServ = new InvoiceService();
        reqServ = new RequestService();
        roomServ = new RoomService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("create_invoice.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Long requestId = Long.parseLong(request.getParameter("id"));
		Request req = reqServ.getRequestById(requestId);
		if (request.getParameter("room") != null && !request.getParameter("room").equals("")) {
			System.out.println(request.getParameter("room"));
			Long roomId = Long.parseLong(request.getParameter("room"));
			req.setRoomId(roomId);
			reqServ.updateRequest(req);
		}
		if (req.getRoomId() != 0L && req.getRoomId() != null) {
			session.setAttribute("message", invServ.createFromRequest(req));
			request.getRequestDispatcher("show_message.jsp").forward(request, response);
		} else {
			List<Room> rooms = roomServ.getFreeRooms();
			List<Request> requests = new ArrayList<Request>();
			requests.add(req);
			session.setAttribute("requests", requests);
			session.setAttribute("rooms", rooms);	
			response.sendRedirect("invoice");
		}
	}

}
