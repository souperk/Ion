package gr.souperk.ion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Kostas "souperk" Alexopoulos 
 *
 */
//TODO write javadoc
public class SouperkUtils 
{
	
	/** Logger SouperkUtils*/
	private static Logger log = LogManager.getLogger(SouperkUtils.class);
	
	public static final String NEW_LINE = "\n";
	
	
	/**
	 * Matches a String with wildcards to another one.
	 * 
	 * @param pattern the pattern to compare the text.
	 * @param text the text to compare with the pattern.
	 * @return true if text matches to the pattern else false.
	 */
	public static boolean isWildcardMatch(String pattern, String text)
	{
        String parts[] = pattern.split("\\*");

        if(parts.length == 1)
        	return pattern.equals(text);
        
        for (String card : parts)
        {
            int idx = text.indexOf(card);
            
            if(idx == -1)
            {
                return false;
            }

            text = text.substring(idx + card.length());
        }
		
		return true;
	}
	
	public static boolean startsWithIngoreCase(String base, String str)
	{
		return base.toLowerCase().startsWith(str.toLowerCase());
	}
}
