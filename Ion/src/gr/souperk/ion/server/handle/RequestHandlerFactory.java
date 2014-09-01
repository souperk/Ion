package gr.souperk.ion.server.handle;

import java.util.List;

/**
 * 
 * @since 1.2
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc.
public class RequestHandlerFactory 
{
		
	private List<RequestHandler> handlers;
	
	public void setHandlers(List<RequestHandler> handlers)
	{
		this.handlers = handlers;
	}
	
	public List<RequestHandler> getHandlers()
	{
		return handlers;
	}
	
}
