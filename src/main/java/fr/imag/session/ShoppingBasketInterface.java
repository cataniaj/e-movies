/*
 *	Author:      Jérémy Leyvraz
 *	Date:        22 sept. 2016
 */

package fr.imag.session;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ShoppingBasketInterface {
    public void addProduct(String title);
    public void removeProduct(String title);
    public List<String> getContents();
    public void remove();
}
