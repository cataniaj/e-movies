/*
 *	Author:      Jérémy Leyvraz
 *	Date:        22 sept. 2016
 */

package fr.imag.session;

import javax.ejb.Remote;
import javax.json.JsonObject;

@Remote
public interface CartLocal {
    
	public boolean addItem(Item item);
    public boolean addOneCopy(Item item);
    
    public boolean removeItem(Item item);
    public boolean removeOneCopy(Item item);
    
    public JsonObject getAllItem();
    public boolean remove();
    
    public boolean validate();
    
}
