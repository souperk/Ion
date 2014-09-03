package gr.souperk.ion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * @author Kostas "souperk" Alexopoulos 
 *
 */
//TODO write javadoc
public class SouperkUtils 
{
	
	public static final String NEW_LINE = "\n";
	
	private static ApplicationContext context;
	
	public static <T> T getBean(Class<T> bean)
	{
		if(context == null)
			context = new FileSystemXmlApplicationContext("conf/spring.xml");
		
		return context.getBean(bean);
	}
	
	public static void close()
	{
		((FileSystemXmlApplicationContext)context ).close();
	}
	
	public static boolean startsWithIngoreCase(String base, String str)
	{
		return base.toLowerCase().startsWith(str.toLowerCase());
	}
}
