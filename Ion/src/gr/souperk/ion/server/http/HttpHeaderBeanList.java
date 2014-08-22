package gr.souperk.ion.server.http;

import java.util.List;

/**
 * 
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public class HttpHeaderBeanList 
{
	protected List<HttpHeaderBean> heads;
	
	public String getHeader(String name)
	{
		for(HttpHeaderBean bean : heads)
		{
			if(bean.getName().equals(name))
				return bean.getValue();
		}
		
		return null;
	}
}

