package org.ceciic.ws.rest.jsonp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class JSONRquestWrapper extends HttpServletRequestWrapper {

	private Map headerMap;

	public void addHeader(String name, String value) {
		headerMap.put(name, new String(value));
	}

	public JSONRquestWrapper(HttpServletRequest request) {
		super(request);
		headerMap = new HashMap ();
		for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();){
			Object key = e.nextElement();
			headerMap.put(key, request.getHeader(key.toString()));
		}
	}

	public Enumeration getHeaderNames() {
		return Collections.enumeration(headerMap.keySet());
	}

	public String getHeader(String name) {
		Object value;
		if ((value = headerMap.get("" + name)) != null)
			return value.toString();
		else
			return ((HttpServletRequest) getRequest()).getHeader(name);

	}
	
	public Enumeration getHeaders(String name){
		List headerList = new ArrayList ();
		headerList.add(headerMap.get("" + name));
		
		return  Collections.enumeration(headerList);
	}
}
