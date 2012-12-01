package org.ceciic.wurfl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mobile.device.wurfl.WurflDevice;

import net.sourceforge.wurfl.core.web.ServletContextWURFLHolder;
import net.sourceforge.wurfl.core.web.WurflWebConstants;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.MarkUp;
import net.sourceforge.wurfl.core.WURFLManager;

public class DeviceDetectionFilter implements Filter, WurflWebConstants{
	
	private static final Log log = LogFactory.getLog(DeviceDetectionFilter.class);

	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRquest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		ServletContextWURFLHolder wurflHolder = (ServletContextWURFLHolder) httpRquest.getSession().getServletContext().getAttribute(WURFL_HOLDER_KEY);

		WURFLManager wurfl =  wurflHolder.getWURFLManager();
		Device device = wurfl.getDeviceForRequest(httpRquest);
		
		WurflDevice wurflDevice = new WurflDevice(device);
		
		boolean isMobile = wurflDevice.isMobile();
		
		log.debug("Device: " + device.getId());
		log.debug("isMobile=="+isMobile);
		log.debug("url=="+httpRquest.getRequestURL());
		
		
		if(httpRquest.getRequestURL().indexOf("index.html") != -1
				|| httpRquest.getRequestURL().toString().endsWith("ceciic.cloudfoundry.com/")
				|| httpRquest.getRequestURL().toString().endsWith("ceciic.cloudfoundry.com")){
			if(isMobile){
				httpResponse.sendRedirect("/mpages/Home.faces");
			}
			else{
				httpResponse.sendRedirect("/pages/Home.html");
			}
		}
		else{
			chain.doFilter(request, response);
		}
		
		
//		if(isMobile){
//			if(httpRquest.getRequestURL().indexOf("/mpages/") != -1){
//				log.debug("mobile goes to mobile");
//				chain.doFilter(request, response);
//			}
//			else if(httpRquest.getRequestURL().indexOf("/pages/") != -1){
//				log.debug("mobile goes to normal");
//				httpResponse.sendRedirect("/mpages/Home.faces");
//			}
//			else{
//				chain.doFilter(request, response);
//			}
//		}
//		else{
//			if(httpRquest.getRequestURL().indexOf("/mpages/") != -1){
//				log.debug("normal goes to mobile");
//				httpResponse.sendRedirect("/pages/Home.html");
//			}
//			else if(httpRquest.getRequestURL().indexOf("/pages/") != -1){
//				log.debug("normal goes to normal");
//				chain.doFilter(request, response);
//			}
//			else{
//				chain.doFilter(request, response);
//			}
//		}

		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	
	}

}
