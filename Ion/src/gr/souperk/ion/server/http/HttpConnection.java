package gr.souperk.ion.server.http;

import gr.souperk.ion.server.proxy.Host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * {@code HttpConnection} is a bundle containing all the connection related data
 * such as the request and the response as well as information about client and
 * potential host server.
 * 
 * @since 1.4.0
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class HttpConnection 
{
	/** {@code BufferedReader} for reading data from client.*/
	private BufferedReader in;

	/** {@code PrintWriter} for sending data to client.*/
	private PrintWriter out;
	
	/** The {@code HttpRequest}.*/
	private HttpRequest request;

	/** The {@code HttpResponse} to be returned to client.*/
	private HttpResponse response;
	
	/** The potential host server for the client's http request.*/
	private Host host;
	
	/**
	 * Constructor of {@code HttpConnection}.
	 * 
	 * @param in the input stream of the connection.
	 * @param out the output stream of the connection.
	 * @throws IOException if it is unable to connect to input or output stream.
	 */
	public HttpConnection(InputStream in, OutputStream out) 
			throws IOException 
	{
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintWriter(out);
	}
	
	/**
	 * @return {@code PrintWriter} for sending data to client.
	 */
	public PrintWriter getOut()
	{
		return out;
	}
	
	/**
	 * @return {@code BufferedReader} for reading data from client.
	 */
	public BufferedReader getIn()
	{
		return in;
	}
	
	/**
	 * @return {@code HttpRequest} .
	 */
	public HttpRequest getRequest()
	{
		return request;
	}

	/**
	 * Sets the {@code HttpRequest} to the desired value.
	 * @param request the value of {@code HttpRequest}.
	 */
	public void setRequest(HttpRequest request)
	{
		this.request = request;
	}
		
	/**
	 * @return The {@code HttpResponse} to be returned to client.
	 */
	public HttpResponse getResponse()
	{
		return response;
	}
	
	/**
	 * Sets the {@code HttpResponse} to the desired value.
	 * @param response The value of {@code HttpResponse}
	 */
	public void setResponse(HttpResponse response)
	{
		this.response = response;
	}
	
	/**
	 * @return The potential host server for the client's {@code HttpRequest}.
	 */
	public Host getHost()
	{
		return host;
	}
	
	/**
	 * Sets the {@code Host} to the desired value.
	 * @param host The value of {@code Host}
	 */
	public void setHost(Host host)
	{
		this.host = host;
	}
	
	/**
	 * Closes the input and output stream.
	 * @throws IOException If it's unable to close input stream or output stream.
	 */
	public void close() 
			throws IOException
	{
		in.close();
		out.close();
	}
	
}