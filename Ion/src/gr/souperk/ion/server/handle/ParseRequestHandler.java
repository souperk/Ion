package gr.souperk.ion.server.handle;

import java.io.IOException;

import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;
import gr.souperk.ion.server.http.HttpRequest;

public class ParseRequestHandler 
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
