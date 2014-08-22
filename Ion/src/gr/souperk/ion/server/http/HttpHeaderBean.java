package gr.souperk.ion.server.http;

/**
 * A bean class for holding a value for a HttpHeader.
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
public class HttpHeaderBean 
{
	private HttpHeader header;
	private String value;
	
	public HttpHeaderBean(String name, String value) 
	{
		this.header = HttpHeader.getHeader(name);
		this.value = value;
	}
	
	public HttpHeaderBean(HttpHeader header, String value) 
	{
		this.header = header;
		this.value = value;
	}
	
	public HttpHeader getHeader()
	{
		return this.header;
	}
	
	public String getName()
	{
		return this.header.getName();
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(getName());
		sb.append(":");
		sb.append(getValue());
		
		return sb.toString();
	}
	
}
