package gr.souperk.ion.server;

import gr.souperk.ion.conf.ProxyConfiguration;
import gr.souperk.ion.server.proxy.Host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */

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
		Host h = ProxyConfiguration.getInstance().getHost(request.getHeader("Host").trim());
		
		log.info("Proxing to connect on server " + h.getAddress() + " on port " + h.getPort() + ".");

		try 
		{
			
//			Socket socket = new Socket(h.getAddress(), h.getPort());
			Socket socket = new Socket("google.gr", 80);

			
			PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			Iterator<Entry<String, String>> it = request.getHeaders().entrySet().iterator();

			log.debug("Printing : " + request.getCommand());
			sOut.println(request.getCommand());
			
			while(it.hasNext())
			{
				Map.Entry<String, String> pair = (Map.Entry<String , String>) it.next();
	
				log.debug("Printing : " + pair.getKey() + ":" + pair.getValue());
				
				sOut.println(pair.getKey() + ":" + pair.getValue());
				it.remove();
			}
			
			sOut.println();
			
			log.debug("Reading from server " + h.getAddress() + " on port " + h.getPort() + ".");
			
			String line ;
			
			while((line = sIn.readLine()) != null && !line.isEmpty())
			{
				log.debug(line);
				out.println(line);
			}
			
			sOut.close();
			sIn.close();
			socket.close();
			
			log.info("Connection with server " + h.getAddress() + " on port " + h.getPort() + ".");
			
		} catch (IOException e) 
		{
			log.error("Unable to connect on server " + h.getAddress() + " on port " + h.getPort() + ".");
			throw new RequestException(400);
		}
	}
	

}
