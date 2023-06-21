package eh203.hitlog;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter to record all requests as visits
 */
public class HitLogFilter implements Filter {	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		
		// Gets the current system time
		long time = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		// Calculate time taken to handle request
		long timeTaken = System.currentTimeMillis() - time;
		
		// Record visit
		Visit visit = new Visit(new Date(), httpReq.getRequestURI(), request.getRemoteAddr(), timeTaken);
		Visit.record(visit);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}
