package gr.souperk.ion.server;

import static gr.souperk.ion.properties.PropertiesTool.*;
import gr.souperk.ion.properties.ServerProperties;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

public class RequestHandler
{
	public static void handle(PrintWriter out, String request)
		throws InvalidRequestException, IOException
	{
		String args[] = request.split(" ");
		
		if(args.length != 3 || 
				!args[0].equals(ServerProperties.getInstance().getProperty(COMMAND_GET)) ||
				!args[2].equals(ServerProperties.getInstance().getProperty(HTTP_VERSION)))
		{
			throw new InvalidRequestException(400);
		}
		
		if(args[1].endsWith("/"))
			args[1] = args[1] + ServerProperties.getInstance().getProperty(DEFAULT_RETURN);	
		
		if(args[1].startsWith("/"))
				args[1] = "resources" + args[1];
		else
			args[1] = "resources/" + args[1];
		
		File f = new File(args[1]);
		
		if(printFile(out, f))
		{
			return ;
		}else
		{
			throw new InvalidRequestException(404);
		}
	}
	
	private static boolean printFile(PrintWriter out, File f) 
			throws IOException
	{
		if(f.exists())
		{
			File success = new File(ServerProperties.getInstance().getProperty(CODE_200));
			
			out.println(FileUtils.readFileToString(success));
			out.println();
			out.println(FileUtils.readFileToString(f));
			
			return true;
		}
		
		return false;
	}
}