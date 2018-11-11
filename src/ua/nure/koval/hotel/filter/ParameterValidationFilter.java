package ua.nure.koval.hotel.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.koval.hotel.util.ParamValidation;

/**
 * Servlet Filter implementation class ParameterValidationFilter
 */
@WebFilter("/ParameterValidationFilter")
public class ParameterValidationFilter implements Filter {
	private ParamValidation pv = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Enumeration<String> paramNames = request.getParameterNames();
		ArrayList<String> paramValues = new ArrayList<>();
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			paramValues.add(request.getParameter(name));
		}
		if(pv.checkForMissing(paramValues)) {
			String message = "One or more of the parameters are missing";
			session.setAttribute("message", message);
			request.getRequestDispatcher("show_message.jsp").forward(request,response);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		pv = new ParamValidation();
	}

}
