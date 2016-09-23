/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.sessionBean;

import javax.ejb.Local;
import javax.json.JsonObject;

@Local
public interface CartLocal {

public boolean addItem(Item item);
public boolean addOneCopy(Item item);

public boolean removeItem(Item item);
public boolean removeOneCopy(Item item);

public JsonObject getAllItem();
public boolean remove();

public boolean validate();

}
