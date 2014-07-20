package gr.souperk.ion.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer
{
	private int port;
	
	public WebServer(int port) 
	{
		this.port = port;
	}
	
	public void start()
	{
		ServerSocket srvSocket;
		
		try {
			srvSocket = new ServerSocket(port);
			
			while(true)
			{
				Socket clientSocket = srvSocket.accept();
				
				new ServerThread(clientSocket).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		
	}
}
