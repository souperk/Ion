package gr.souperk.ion.server;

import gr.souperk.ion.SouperkUtils;
import gr.souperk.ion.conf.ServerConfiguration;
import gr.souperk.ion.server.handle.RequestHandler;
import gr.souperk.ion.server.handle.RequestHandlerFactory;
import gr.souperk.ion.server.http.HttpConnection;

import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A {@code Thread} for communicating with the client.
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO Write javadoc.
//TODO Decide to make s Thread specified configuration.
public class ServerThread 
	extends Thread
{
	
	/** Logger ServerThread*/
	private static Logger log = LogManager.getLogger(ServerThread.class);
	
	private Socket socket ;
	
	@SuppressWarnings("unused")
	private ServerConfiguration conf;
	
	public ServerThread(ServerConfiguration conf) 
	{
		this.conf = conf;
	}
	
	public void setClientSocket(Socket socket)
	{
		this.socket = socket;
	}
	
	/**
	 * Reads the client request and prints the server response.
	 */
	@Override
	public void run() 
	{
		try {

			String address = "[" + socket.getLocalAddress().toString() + "]"; 
			
			HttpConnection connection = new HttpConnection(socket.getInputStream(), socket.getOutputStream());

			try {
				
				log.debug(address + "Proccessing request.");
				
				for(RequestHandler handler : SouperkUtils.getBean(RequestHandlerFactory.class).getHandlers())
					handler.handle(connection);
			
			} catch (RequestException e) 
			{
				//TODO print fancy stuff for codes.
				connection.getOut().println(e.getCode());
				log.info(address + "Got code " + e.getCode() + ".");
			}
			
			connection.close();
			socket.close();
			
		} catch (IOException e) {
			//TODO catch exception
		}
	}
}
