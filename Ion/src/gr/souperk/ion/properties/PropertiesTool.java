package gr.souperk.ion.properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Basic Utility class holding the key for various properties
 * and holds the hardcoded default properties.
 * 
 * @author Kostas "souperk" Alexopoulos (kostas@alcinia.net)
 *
 */

//TODO Finish javadoc

public class PropertiesTool
{
	public static String DEFAUL_CONF_FILE = "/resources/ion.conf";
	
	public static String COMMAND_GET = "command.get";
	public static String HTTP_VERSION = "http.version";
	public static String DEFAULT_RETURN = "default.return";
	
	public static String CODE_200 = "code.200";

	public static String CODE_400 = "code.400";
	public static String CODE_404 = "code.404";
	
	/**
	 * 
	 * @return The default properties for the server.
	 */
	public static Configuration defaults()
	{
		Configuration conf = new PropertiesConfiguration();
		
		conf.addProperty(COMMAND_GET, "GET");
		conf.addProperty(HTTP_VERSION, "HTTP/1.1");
		conf.addProperty(DEFAULT_RETURN, "index.html");
  
		conf.addProperty(CODE_200, "resources/200.html");
		
		conf.addProperty(CODE_400, "resources/400.html");
		conf.addProperty(CODE_404, "resources/404.html");

		return conf;
	}
}
