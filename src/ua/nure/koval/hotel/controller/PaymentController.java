package ua.nure.koval.hotel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.entity.Invoice;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.service.InvoiceService;
import ua.nure.koval.hotel.service.RequestService;
import ua.nure.koval.hotel.util.Payment;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/payment")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InvoiceService invServ = null;

    public PaymentController() {
        super();
        invServ = new InvoiceService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("show_message.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		response.sendRedirect("payment");
	}

	private void process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Long id = Long.parseLong(request.getParameter("id"));
		Invoice invoice = invServ.getById(id);
		Payment payment = new Payment();
		if (payment.payForInvoice(invoice)) {
			session.setAttribute("message", "Success");
		} else {
			session.setAttribute("message", "Failure");
		}
	}

}
