package gr.souperk.ion.server.handle;

import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;
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
	implements RequestHandler
{
	
	/** Logger ProxyRequestHandler*/
	private static Logger log = LogManager.getLogger(ProxyRequestHandler.class);

	
	@Override
	public void handle(HttpConnection connection)
			throws RequestException 
	{
		Host host = connection.getHost();
		String serverID = "[server@" + host.getAddress() + ":" + host.getPort() + "]";
		
		log.info(serverID + "Proxing.");

		try 
		{
			
			Socket socket = new Socket(host.getAddress(), host.getPort());

			PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			sOut.println(connection.getRequest().toString());
			sOut.println();
			
			log.debug(serverID + "Reading.");
			
			String line ;
			
			while((line = sIn.readLine()) != null && !line.isEmpty())
			{
				log.debug(serverID + "Got line " + line + ".");

				connection.getOut().println(line);
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
