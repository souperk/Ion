package gr.souperk.ion.server.handle;

import gr.souperk.ion.server.RequestException;
import gr.souperk.ion.server.http.HttpConnection;

/**
 *
 * @since 1.2.0
 * @author Kostas "souperk" Alexopoulos
 *
 */
//TODO write javadoc
public interface RequestHandler
{
	public void handle(HttpConnection connection) throws RequestException;
}
