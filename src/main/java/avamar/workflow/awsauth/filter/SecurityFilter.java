package avamar.workflow.awsauth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Security filter
 * 
 * @author sunny
 */
public class SecurityFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Object user = httpServletRequest.getSession().getAttribute("user");
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			String uri = httpServletRequest.getRequestURI();
			if(uri.indexOf("/login") != -1 || uri.indexOf("jsp/login.jsp") != -1) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}
		}
	}

	public void destroy() {
	}
}
