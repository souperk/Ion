package gr.souperk.ion;

import gr.souperk.ion.conf.PropertiesTool;
import gr.souperk.ion.conf.ServerProperties;
import gr.souperk.ion.server.WebServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 
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
		
		new WebServer(Integer.parseInt(ServerProperties.getInstance().getProperty(PropertiesTool.PORT))).start();

		log.info("Server Stopping.");
	}
}
