package gr.souperk.ion.server;

import gr.souperk.ion.server.http.HttpRequest;

import java.io.PrintWriter;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */

public abstract class RequestHandler
{
	protected HttpRequest request;
	
	public RequestHandler(HttpRequest request) 
	{
		this.request = request;
	}
	
	public abstract void handle(PrintWriter out) throws RequestException;
}
