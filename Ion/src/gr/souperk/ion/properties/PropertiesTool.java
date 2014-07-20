package gr.souperk.ion.properties;

import java.util.HashMap;
import java.util.Map;

/*
 * Just o key holder for properties.
 * 
 * @author kostas
 *
 */

//TODO Create java doc
public class PropertiesTool 
{
	public static String COMMAND_GET = "command.get";
	public static String HTTP_VERSION = "http.version";
	public static String DEFAULT_RETURN = "default.return";
	
	public static String CODE_200 = "code.200";

	public static String CODE_400 = "code.400";
	public static String CODE_404 = "code.404";
	
	public static Map<String, String> defaults()
	{
		Map<String, String> props = new HashMap<String, String>();
		
		props.put(COMMAND_GET, "GET");
		props.put(HTTP_VERSION, "HTTP/1.1");
		props.put(DEFAULT_RETURN, "index.html");
  
		props.put(CODE_200, "resources/200.html");
		
		props.put(CODE_400, "resources/400.html");
		props.put(CODE_404, "resources/404.html");

		return props;
	}
}
