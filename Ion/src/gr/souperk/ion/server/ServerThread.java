package gr.souperk.ion.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread 
	extends Thread
{
	private Socket clientSocket ;
	
	public ServerThread(Socket clientSocket) 
	{
		this.clientSocket = clientSocket ;
	}
	
	@Override
	public void run() 
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

			String request = in.readLine();	
			
			try {
				RequestHandler.handle(out, request);
			} catch (InvalidRequestException e) 
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
