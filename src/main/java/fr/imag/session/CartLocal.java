/*
 *	Author:      Jérémy Leyvraz
 *	Date:        22 sept. 2016
 */

package fr.imag.session;

import javax.ejb.Remote;
import javax.json.JsonObject;

@Remote
public interface CartLocal {
    public void addItem(Item item);
    public void removeItem(Item item);
    public JsonObject getAllItem();
    public void remove();
}
