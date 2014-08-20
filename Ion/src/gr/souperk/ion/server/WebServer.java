package gr.souperk.ion.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class WebServer
{
	
	/** Logger WebServer*/
	private static Logger log = LogManager.getLogger(WebServer.class);
	
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
		ServerSocket srv;
		boolean flag = true;
		
		setStatus(SERVER_RUNNING);;
		
		try {
			log.info("Opening server on port " + port + ".");
			srv = new ServerSocket(port);
			
			while(flag)
			{
				switch (status) {
				case SERVER_STOP:
					flag = false;
					break;
				
				case SERVER_FORCESTOP:
					System.exit(1);
					break;
				
				case SERVER_PAUSE:
					break;
					
				case SERVER_RUNNING: //TODO do something about never closing client sockets.
					Socket clientSocket = srv.accept();
					
					log.info("Connection established with " + clientSocket.getLocalAddress().toString() + ".");
					
					new ServerThread(clientSocket).start();
					break;
				}
			}

			srv.close();
			
		} catch (IOException e) 
		{
			log.error("Port " + port + " already occupied.");
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
		switch (status) {
		case SERVER_STOP:
			log.debug("Server Status changed to SERVER_STOP.");
			break;
		case SERVER_FORCESTOP:
			log.debug("Server Status changed to SERVER_FORCESTOP.");
			break;
		case SERVER_PAUSE:
			log.debug("Server Status changed to SERVER_PAUSE.");
			break;
		case SERVER_RUNNING:
			log.debug("Server Status changed to SERVER_RUNNING.");
			break;
		}
		
		this.status = status;
	}
}
