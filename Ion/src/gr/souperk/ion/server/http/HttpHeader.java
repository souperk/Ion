package gr.souperk.ion.server.http;

import java.util.ArrayList;
import java.util.List;

/**
 * More of a utility class for managing HttpHeaders and avoid duplications.
 * 
 * @since 1.4
 * @author Kostas "souperk" Alexopoulos
 *
 */

//TODO create fields for Headers.
//TODO decide whether i will make a toString.
public class HttpHeader 
{
	private int id;
	private String name;
	
	private static List<HttpHeader> heads;
	
	private HttpHeader(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 
	 * @param id the id of header.
	 * @return the HttpHeader on position id. Practically the returned Header will have id same as id param.
	 */
	public static HttpHeader getHeader(int id)
	{
		if(heads == null)
			init();
	
		return heads.get(id);
	}

	/**
	 * 
	 * @param name the name of the header.
	 * @return the HttpHeader with name same as name param.
	 */
	public static HttpHeader getHeader(String name)
	{
		if(heads == null)
			init();
		
		for(HttpHeader head : heads)
			if(head.getName().equals(name))
				return head;
		
		return null;
	}
	
	/**
	 * Creates the heads list and adds all <code>HttpHeader</code>s with it id same as their insertion order.
	 * 
	 */
	//TODO provide more flexibly ways of loading headers.
	private static void init()
	{
		heads = new ArrayList<HttpHeader>();
		
		//Add more headers.
		heads.add(new HttpHeader(1, "Host"));
		
	}
	
	/**
	 * @return the id of the Header.
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * 
	 * @return the name of the Header.
	 */
	public String getName()
	{
		return this.name;
	}
}
