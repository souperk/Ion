package gr.souperk.ion.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer
{
	/** Status for stopping the main loop and closing the server after all 
	 * client sockets are closed.*/
	public static final int SERVER_STOP 		= 1;
	
	/** Status for forcing the server to stop terminating all client sockets.*/
	public static final int SERVER_FORCESTOP 	= 2;
	
	/** Status for stopping the main loop. Active client sockets aren't affected by this status.
	 * Also changing status to {@code SERVER_RUNNING} will resume the server.*/
	public static final int SERVER_PAUSE 		= 3;
	
	/** Normal status for running.*/
	public static final int SERVER_RUNNING 		= 4;
	
	private int port;
	private int status;
	
	/**
	 * 
	 * @param port The port the server will listen on.
	 */
	public WebServer(int port) 
	{
		this.port = port;
	}
	
	/**
	 * Sets status on {@code SERVER_RUNNING}.
	 * Runs the main loop until the status is changed.
	 */
	public void start()
	{
		ServerSocket srvSocket;
		boolean running = true;
		status = SERVER_RUNNING;
		
		try {
			srvSocket = new ServerSocket(port);
			
			while(running)
			{
				switch (status) {
				case SERVER_STOP:
					running = false;
					break;
				case SERVER_FORCESTOP:
					System.exit(1);
					break;
				case SERVER_PAUSE:
					break;
				case SERVER_RUNNING: //TODO do something about never closing client sockets.
					Socket clientSocket = srvSocket.accept();
					
					new ServerThread(clientSocket).start();
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the Server Status to {@code SERVER_STOP}
	 */
	public void stop()
	{
		setStatus(SERVER_STOP);
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
}
