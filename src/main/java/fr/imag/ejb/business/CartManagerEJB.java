/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.ejb.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.json.JsonObject;

import fr.imag.ejb.dbaccess.CartDatabaseAccessEJB;
import fr.imag.entities.User;
import fr.imag.sessionBean.CartLocal;
import fr.imag.sessionBean.Item;

@Stateful
public class CartManagerEJB implements CartLocal{
	@EJB private CartDatabaseAccessEJB dbAccess;

	private List<Item> allItemInTheCart;
	private User customer;
	
	@PostConstruct
	public void initialize(){
		allItemInTheCart = new ArrayList<Item>();
	}

	public boolean addItem(Item item) {
		try{
			for(Item i : allItemInTheCart){
				if(i.getIdProduct() == item.getIdProduct()){
					i.setQuantity(i.getQuantity()+1);
					return true;
				}
			}
			allItemInTheCart.add(item);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean removeItem(Item item) {
		try{
			for(Item i : allItemInTheCart){
				if(i.getIdProduct() == item.getIdProduct()){
					allItemInTheCart.remove(i);
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}

	public JsonObject getAllItem() {
		return null;
	}

	public boolean remove() {
		try{
			allItemInTheCart.clear();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@PreDestroy
	public void clear(){
		try{
			allItemInTheCart = null;
		}catch(Exception e){
		}
	}

	public boolean addOneCopy(Item item) {
		try{
			for(Item i : allItemInTheCart){
				if(i.getIdProduct() == item.getIdProduct()){
					i.setQuantity(i.getQuantity()+1);
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}

	public boolean removeOneCopy(Item item) {
		try{
			for(Item i : allItemInTheCart){
				if(i.getIdProduct() == item.getIdProduct()){
					allItemInTheCart.remove(i);
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
    public boolean validate(){
		// TODO creation order, orderLine
    	return true;
    }
	
	private void updateStockWhenRemoveOneItem(){
		// TODO creation order, orderLine
	}
	
	private void updateStockWhenAddOneItem(){
		// TODO update stock + 1
	}
	
	private void updateStockWhenRemoveCart(){
		// TODO update stock - 1
	}

}
