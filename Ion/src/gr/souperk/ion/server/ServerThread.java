package gr.souperk.ion.server;

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
	
	//TODO create compatibility with various HTTP headers.
	/**
	 * Reads the client request and prints the server response.
	 */
	@Override
	public void run() 
	{
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		
			String line = in.readLine();
			HttpRequest request = new HttpRequest(line);
			
			String address = clientSocket.getLocalAddress().toString();
			log.debug("Reading client " + address + " request.");
			
			while(!line.isEmpty())
			{
				log.debug("Got line " + line + " from client " + address + ".");
				
				request.addHeader(line);
				line = in.readLine();
			}
			
			try {
				
				log.debug("Proccessing client " + address + " request.");
				RequestHandler.handle(out, request);
			
			} catch (RequestException e) 
			{
				out.println(e.getCode());
				log.info("Got code " + e.getCode() + " for client " + address + " request");
			}
			
			in.close();
			out.close();
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
