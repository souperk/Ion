package gr.souperk.ion.server.handle;

import gr.souperk.ion.conf.ProxyConfiguration;
import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;
import gr.souperk.ion.server.proxy.Host;

/**
*
* @since 1.4.0
* @author Kostas "souperk" Alexopoulos 
*
*/
//TODO implement this.
public class RequestManipulator 
	implements RequestHandler
{
	private ProxyConfiguration conf;

	public RequestManipulator(ProxyConfiguration conf) 
	{
		this.conf = conf;
	}
	
	@Override
	public void handle(HttpConnection connection) 
			throws RequestException 
	{
		Host host = conf.getHost(connection.getRequest());
		
		if(host != null)
		{
			connection.setHost(host);
		}
	}

}
