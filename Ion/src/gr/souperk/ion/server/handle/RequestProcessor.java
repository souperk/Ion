package gr.souperk.ion.server.handle;

import static gr.souperk.ion.conf.PropertiesTool.CODE_200;
import static gr.souperk.ion.conf.PropertiesTool.COMMAND_GET;
import static gr.souperk.ion.conf.PropertiesTool.DEFAULT_RETURN;
import static gr.souperk.ion.conf.PropertiesTool.HTTP_VERSION;
import gr.souperk.ion.conf.ServerConfiguration;
import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;
import gr.souperk.ion.server.http.HttpResponse;
import gr.souperk.ion.server.proxy.Host;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
*
* @since 1.4.0
* @author Kostas "souperk" Alexopoulos 
*
*/
//TODO write javadoc
public class RequestProcessor 
	implements RequestHandler
{
	
	/** Logger RequestProcessor*/
	private static Logger log = LogManager.getLogger(RequestProcessor.class);

	private ServerConfiguration conf;
	
	public RequestProcessor(ServerConfiguration conf) 
	{
		this.conf = conf;
	}
	
	@Override
	public void handle(HttpConnection connection) 
			throws RequestException 
	{
		if(connection.getRequest().isEmpty())
			throw new RequestException(400);

		connection.setResponse(new HttpResponse());
		
		if(connection.getHost() == null) 
		{
			log.debug("Local Handling");
			localHandle(connection);
		
		}
		else 
		{
			log.debug("Proxy Handling");
			proxyHandle(connection);
		}
		
	}
	
	private void proxyHandle(HttpConnection connection)
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
				connection.getResponse().add(line);
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

	private void localHandle(HttpConnection connection) 
			throws RequestException
	{
		if(connection.getRequest().isEmpty())
		{
			log.debug("Invalid request returning code 400.");
			throw new RequestException(400);
		}
		
		String args[] = connection.getRequest().getCommand().split(" ");
		
		if(args.length != 3 || 
				!args[0].equals(conf.getProperty(COMMAND_GET)) ||
				!args[2].equals(conf.getProperty(HTTP_VERSION)))
		{
			throw new RequestException(400);
		}
		
		if(args[1].endsWith("/"))
			args[1] = args[1] + conf.getProperty(DEFAULT_RETURN);	
		
		if(args[1].startsWith("/"))
				args[1] = "resources" + args[1];
		else
			args[1] = "resources/" + args[1];
		
		
		try {
			
			File file = new File(args[1]);
			
			if(file.exists())
			{
				File success = new File(conf.getString(CODE_200));
				connection.getResponse().setCode(FileUtils.readFileToString(success));
				connection.getResponse().setMessage(FileUtils.readFileToString(file));
			}
			else
				throw new RequestException(404);

		} catch (IOException e) 
		{
			throw new RequestException(404);
		}
	}

}
