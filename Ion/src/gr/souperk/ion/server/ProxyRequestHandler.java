package gr.souperk.ion.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProxyRequestHandler 
	implements RequestHandler
{
	
	/** Logger ProxyRequestHandler*/
	private static Logger log = LogManager.getLogger(ProxyRequestHandler.class);

	@Override
	public void handle(PrintWriter out, HttpRequest request)
			throws RequestException 
	{
		try {
			PrintWriter sOut = new PrintWriter(socket.getOutputStream());
			BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			//TODO something
		}
	}
	

}
