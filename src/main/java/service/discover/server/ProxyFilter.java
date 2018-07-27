package service.discover.server;



import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ProxyFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(ProxyFilter.class);

	  @Override
	  public String filterType() {
	    return "pre";
	  }

	  @Override
	  public int filterOrder() {
	    return 1;
	  }

	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }

	  @Override
	  public Object run() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();

	    log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    Enumeration<String> enumeration=request.getHeaderNames();
	    if(null!=enumeration) {
	    	while(enumeration.hasMoreElements()) {
		    	String headerName=enumeration.nextElement();
		    	log.info("Header <"+headerName+"> : "+request.getHeader(headerName));	
		    }	
	    }
	    
	    
	    if(null!=request.getCookies()) {
	    	for(Cookie cookie : request.getCookies()) {
		    	log.info("cookies <"+cookie.getName()+"> : "+cookie.getValue());
		    }	
	    }
	    
	    return null;
	  }
	
}
