package gr.souperk.ion.server.proxy;

import gr.souperk.ion.SouperkUtils;

//TODO rename this class.
public class Rule 
{
	private String url;
	
	public Rule(String url) 
	{
		this.url = url;
	}
	
	public String getURL()
	{
		return this.url;
	}
	
	public boolean meetsRule(String url)
	{
		return SouperkUtils.isWildcardMatch(this.url, url);
	}
}
