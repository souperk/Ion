package gr.souperk.ion.conf;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Basic Utility class holding the key for various properties
 * and holds the hardcoded default properties.
 * 
 * @since 1.1.0
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO Finish javadoc
//TODO ADD more HTTP codes
public class PropertiesTool
{
	public static String DEFAUL_CONF_FILE = "conf/ion.conf";

	public static String PROXY_CONF_FILE = "conf.proxy";
	
	/** Port to start Server on*/
	public static String PORT = "port";

	public static String COMMAND_GET = "command.get";
	public static String HTTP_VERSION = "http.version";
	public static String DEFAULT_RETURN = "default.return";
	
	/** HTTP success code*/
	public static String CODE_200 = "code.200";

	/** HTTP bad request code*/
	public static String CODE_400 = "code.400";
	
	/** HTTP not found code*/
	public static String CODE_404 = "code.404";
	
	
	/**
	 * @return The default properties for the server.
	 */
	public static Configuration defaults()
	{
		Configuration conf = new PropertiesConfiguration();

		conf.addProperty(PORT, "1026");
		conf.addProperty(PROXY_CONF_FILE, "conf/proxy.xml");
		
		conf.addProperty(COMMAND_GET, "GET");
		conf.addProperty(HTTP_VERSION, "HTTP/1.1");
		conf.addProperty(DEFAULT_RETURN, "index.html");
  
		conf.addProperty(CODE_200, "resources/200.html");
		
		conf.addProperty(CODE_400, "resources/400.html");
		conf.addProperty(CODE_404, "resources/404.html");

		return conf;
	}
}
