package gr.souperk.ion;

import gr.souperk.ion.server.WebServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 
 * @since 1.0.0
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class Start 
{
	
	/** Logger Start*/
	private static Logger log = LogManager.getLogger(Start.class);

	
	public static void main(String[] args) 
	{
		log.info("Server Starting.");
				
		SouperkUtils.getBean(WebServer.class).start();

		log.info("Server Stopping.");
		
	}
}
