package gr.souperk.ion;

/**
 * 
 * @author Kostas "souperk" Alexopoulos 
 *
 */
//TODO write javadoc
public class SouperkUtils 
{
	
	/**
	 * Matches a String with wildcards to another one.
	 * 
	 * @param pattern the pattern to compare the text.
	 * @param text the text to compare with the pattern.
	 * @return true if text matches to the pattern else false.
	 */
	public static boolean wildcardMatch(String pattern, String text)
	{
        String parts[] = pattern.split("\\*");

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
}
