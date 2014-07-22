package gr.souperk.ion.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A {@code Thread} for communicating with the client.
 * 
 * @author Kostas "souperk" Alexopoulos (kostas@alcinia.net)
 *
 */
public class ServerThread 
	extends Thread
{
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
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
			
			
			String request = in.readLine();	
			
			try {
				RequestHandler.handle(out, request);
			} catch (RequestException e) 
			{
				out.println(e.getCode());
			}
			out.flush();
			
			in.close();
			out.close();
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
