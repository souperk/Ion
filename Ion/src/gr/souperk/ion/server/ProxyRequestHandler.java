package gr.souperk.ion.server;

import gr.souperk.ion.conf.ProxyConfiguration;
import gr.souperk.ion.server.http.HttpRequest;
import gr.souperk.ion.server.proxy.Host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class ProxyRequestHandler 
	extends RequestHandler
{
	
	/** Logger ProxyRequestHandler*/
	private static Logger log = LogManager.getLogger(ProxyRequestHandler.class);

	public ProxyRequestHandler(HttpRequest request) 
	{
		super(request);
	}
	
	@Override
	public void handle(PrintWriter out)
			throws RequestException 
	{
		Host h = ProxyConfiguration.getInstance().getHost(request);
		String serverID = "[server@" + h.getAddress() + ":" + h.getPort() + "]";
		
		log.info(serverID + "Proxing.");

		try 
		{
			
			Socket socket = new Socket(h.getAddress(), h.getPort());

			PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			sOut.println(request.toString());
			sOut.println();
			
			log.debug(serverID + "Reading.");
			
			String line ;
			
			while((line = sIn.readLine()) != null && !line.isEmpty())
			{
				log.debug(serverID + "Got line " + line + ".");

				out.println(line);
			}
			
			sOut.close();
			sIn.close();
			socket.close();
			
		} catch (IOException e) 
		{
			log.error(serverID + "Unable to connect.");
			throw new RequestException(400);
		}
	}
	

}
