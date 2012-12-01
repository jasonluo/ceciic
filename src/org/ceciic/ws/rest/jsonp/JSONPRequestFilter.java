package org.ceciic.ws.rest.jsonp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JSONPRequestFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)) {
			throw new ServletException("This filter can "
					+ " only process HttpServletRequest requests");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (isJSONPRequest(httpRequest)) {
			ServletOutputStream out = response.getOutputStream();
			out.println(getCallbackParameter(httpRequest) + "(");
			
			//add header to instruct @Resource to produce json format data
			JSONRquestWrapper jsonRquestWrapper = new JSONRquestWrapper(httpRequest);
			jsonRquestWrapper.addHeader("accept", "application/json");
			
			chain.doFilter(jsonRquestWrapper, response);
			out.println(");");

			response.setContentType("text/javascript");
		} else {
			chain.doFilter(request, response);
		}
	}

	private String getCallbackParameter(HttpServletRequest httpRequest) {
		return httpRequest.getParameter("callback");
	}

	private boolean isJSONPRequest(HttpServletRequest httpRequest) {
		String callbackMethod = getCallbackParameter(httpRequest);
		return (callbackMethod != null && callbackMethod.length() > 0);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
