package gr.souperk.ion.server.handle;

import static gr.souperk.ion.conf.PropertiesTool.CODE_200;
import static gr.souperk.ion.conf.PropertiesTool.COMMAND_GET;
import static gr.souperk.ion.conf.PropertiesTool.DEFAULT_RETURN;
import static gr.souperk.ion.conf.PropertiesTool.HTTP_VERSION;
import gr.souperk.ion.conf.ServerConfiguration;
import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Kostas "souperk" Alexopoulos 
 *
 */
//TODO write javadoc
//TODO add more compatibility with http headers.
public class LocalRequestHandler
	implements RequestHandler
{

	/** Logger RequestHandler*/
	private static Logger log = LogManager.getLogger(LocalRequestHandler.class);
	
	private ServerConfiguration conf;
	
	public LocalRequestHandler(ServerConfiguration conf) 
	{
		this.conf = conf;
	}
	
	/**
	 * 
	 * @param out client output stream/ 
	 * @param request client request.
	 * @throws RequestException if request seems invalid.
	 * @throws IOException on problems while communicating with client.
	 */
	//TODO Write javadoc
	public void handle(HttpConnection connection) 
			throws RequestException
	{
		if(connection.getRequest().isEmpty())
		{
			log.debug("Invalid request returning code 400.");
			throw new RequestException(400);
		}
		
		String args[] = connection.getRequest().getCommand().split(" ");
		
		if(args.length != 3 || 
				!args[0].equals(conf.getProperty(COMMAND_GET)) ||
				!args[2].equals(conf.getProperty(HTTP_VERSION)))
		{
			throw new RequestException(400);
		}
		
		if(args[1].endsWith("/"))
			args[1] = args[1] + conf.getProperty(DEFAULT_RETURN);	
		
		if(args[1].startsWith("/"))
				args[1] = "resources" + args[1];
		else
			args[1] = "resources/" + args[1];
		
		File f = new File(args[1]);
		
		if(printFile(connection.getOut(), f))
		{
			return ;
		}else
		{
			throw new RequestException(404);
		}
			
	}
	
	/**
	 * Convenience method for printing files.
	 * <br></br>
	 * Note : along with file HTTP 200 code is printed.
	 * 
	 * @param out The {@code PrintWriter} 
	 * @param f file to print
	 * @return true if successfully printed the file else false.
	 */
	//TODO move to SouperkUtils
	private boolean printFile(PrintWriter out, File f) 
	{
		try {
			
			if(f.exists())
			{
				System.out.println(f.getAbsolutePath());
				File success = new File(conf.getString(CODE_200));
				
				out.println(FileUtils.readFileToString(success));
				out.println();
				out.println(FileUtils.readFileToString(f));
				
				return true;
			}
			
		}catch(IOException e) 
		{
			log.debug("Thrown IO Exception on LocalRequestHandler.printFile()");
		}
		
		return false;
	}
	
}