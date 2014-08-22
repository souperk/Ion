package gr.souperk.ion.server.proxy;

import gr.souperk.ion.server.http.HttpHeader;
import gr.souperk.ion.server.http.HttpRequest;

//TODO rename this class.
//TODO turn this class from a String holder to a Rule holder. Note first decide what is a Rule.
public class Rule 
{
	/** The HttpHeader the rule is for*/
	private HttpHeader header;
	
	/** The expected value for the header.*/
	private String expectedValue;
	
	/**
	 * 
	 * @param header the HttpHeader the rule is for.
	 * @param expectedValue the expected value for the header.
	 */
	public Rule(HttpHeader header, String expectedValue) 
	{
		this.header = header;
		this.expectedValue = expectedValue;
	}
	
	public boolean isValid(HttpRequest request)
	{
		return false;
	}
	
}
