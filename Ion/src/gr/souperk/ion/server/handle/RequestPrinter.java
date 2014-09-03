package gr.souperk.ion.server.handle;

import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;

/**
*
* @since 1.4.0
* @author Kostas "souperk" Alexopoulos 
*
*/

public class RequestPrinter 
	implements RequestHandler
{

	@Override
	public void handle(HttpConnection connection) 
			throws RequestException 
	{
		connection.getOut().println(connection.getResponse().toString());
		connection.getOut().println();
		
		connection.getOut().flush();
		
	}

}
