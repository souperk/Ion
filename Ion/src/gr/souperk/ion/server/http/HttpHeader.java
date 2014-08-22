package gr.souperk.ion.server.http;

import gr.souperk.ion.SouperkUtils;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class HttpHeader 
{
	private String name;
	
	//TODO finish this method
	public boolean equals(Object obj)
	{
		if(!(obj instanceof String))
			return false;
		
		if(!SouperkUtils.startsWithIngoreCase((String)obj, name))
			return false;
		
		
		return true;
	}
}