package gr.souperk.ion.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gr.souperk.ion.conf.ProxyConfiguration;
import gr.souperk.ion.server.http.HttpRequest;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class RequestHandlerFactory 
{
	
	/** Logger RequestHandlerFactory*/
	private static Logger log = LogManager
			.getLogger(RequestHandlerFactory.class);
	
	
	/**
	 * 
	 * @param request http request to be handled.
	 * @return the handler for the request.
	 */
	public static RequestHandler getHandler(HttpRequest request)
	{
		RequestHandler handler = null;
			
		if(ProxyConfiguration.getInstance().exists(request.getHeader("Host")))
		{
			handler = new ProxyRequestHandler(request);
			log.debug("handler set to ProxyRequestHandler");
		}
		else
		{
			handler = new LocalRequestHandler(request);
			log.debug("handler set to LocalRequestHandler");
		}
		
		return handler;
	}
}
