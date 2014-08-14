package gr.souperk.ion.server;

import java.io.PrintWriter;

public interface RequestHandler
{
	public void handle(PrintWriter out, HttpRequest request) throws RequestException;
}
