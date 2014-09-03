package gr.souperk.ion.server.handle;

import java.io.IOException;

import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;
import gr.souperk.ion.server.http.HttpRequest;


/**
*
* @since 1.4.0
* @author Kostas "souperk" Alexopoulos 
*
*/
//TODO write javadoc.
public class RequestParser 
	implements RequestHandler
{

	@Override
	public void handle(HttpConnection connection) 
			throws RequestException 
	{
		try {
			connection.setRequest(new HttpRequest(connection.getIn()));
		} catch (IOException e) 
		{
			//TODO catch Exception.
			e.printStackTrace();
		}
		
	}

}
