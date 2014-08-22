package gr.souperk.ion.server;

import gr.souperk.ion.server.http.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A {@code Thread} for communicating with the client.
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class ServerThread 
	extends Thread
{
	
	/** Logger ServerThread*/
	private static Logger log = LogManager.getLogger(ServerThread.class);
	
	private Socket clientSocket ;
	
	public ServerThread(Socket clientSocket) 
	{
		this.clientSocket = clientSocket ;
	}
	
	/**
	 * Reads the client request and prints the server response.
	 */
	@Override
	public void run() 
	{
		try {

			String address = "[" + clientSocket.getLocalAddress().toString() + "]"; 
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
			String line = in.readLine();
			HttpRequest request = new HttpRequest(line);
			
			log.debug(address + "Reading.");
			
			while(line != null && !line.isEmpty())
			{
				log.debug(address + "Got line " + line + ".");
				line = in.readLine();
				
				request.addHeader(line);
			}
			
			try {
				
				log.debug(address + "Proccessing request.");
				RequestHandlerFactory.getHandler(request).handle(out);
			
			} catch (RequestException e) 
			{
				//TODO print fancy stuff for codes.
				out.println(e.getCode());
				log.info(address + "Got code " + e.getCode() + ".");
			}
			
			in.close();
			out.close();
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
