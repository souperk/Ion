package gr.souperk.ion.server.proxy;

import java.util.regex.Pattern;

import gr.souperk.ion.server.http.HttpRequest;

//TODO rename this class.
//TODO turn this class from a String holder to a Rule holder. Note first decide what is a Rule.
//TODO write javadoc.
public class Rule 
{
	/** The HttpHeader the rule is for*/
	private String header;
	
	/** The expected value for the header.*/
	private String pattern;
	
	/**
	 * 
	 * @param header the HttpHeader the rule is for.
	 * @param expectedValue the expected value for the header.
	 */
	public Rule(String header, String expectedValue) 
	{
		this.header = header;
		this.pattern = expectedValue;
	}
	
	public String getHeader()
	{
		return this.header;
	}
	
	/**
	 * Currently checks whether the regular expression pattern matches to request.getHeader(header.getName)
	 * 
	 * @param request
	 * @return
	 */
	//TODO write code and javadoc.
	public boolean isValid(HttpRequest request)
	{
		return Pattern.matches(pattern, request.get(header).trim());
	}
	
}
