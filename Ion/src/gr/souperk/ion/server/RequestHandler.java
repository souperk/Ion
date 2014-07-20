package gr.souperk.ion.server;

import java.io.PrintWriter;

public class RequestHandler
{
	public static void handle(PrintWriter out, String request)
	{
		String args[] = request.split(" ");
		
		if(args.length != 3)
		{
			printCode(400);
			return;
		}
	}
	
	private static void printCode(int code)
	{
		
	}
}
