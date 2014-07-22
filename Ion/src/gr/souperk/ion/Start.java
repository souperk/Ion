package gr.souperk.ion;

import gr.souperk.ion.server.WebServer;

/**
 * 
 * @author Kostas "souperk" Alexopoulos (kostas@alcinia.net)
 *
 */
public class Start 
{
	public static void main(String[] args) 
	{
		new WebServer(1026).start();
	}
}
