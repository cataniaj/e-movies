/*
 *	Author:      Jérémy Leyvraz
 *	Date:        22 sept. 2016
 */

package fr.imag.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.json.JsonObject;

@Stateful
public class Cart implements CartLocal{
	
	private List<Item> allItemInTheCart;
	
	@PostConstruct
	public void initialize(){
		allItemInTheCart = new ArrayList<Item>();
	}
	
	private boolean findItemInTheCart(Item item){
		for(Item i : allItemInTheCart){
			if(i.getIdProduct() == item.getIdProduct()){
				return true;
			}
		}
		return false;
	}

	@Override
	public void addItem(Item item) {
		for(Item i : allItemInTheCart){
			if(i.getIdProduct() == item.getIdProduct()){
				i.setQuantity(i.getQuantity()+1);
				return ;
			}
		}
		allItemInTheCart.add(item);
	}

	@Override
	public void removeItem(Item item) {
		
	}

	@Override
	public JsonObject getAllItem() {
		return null;
	}

	@Override
	public void remove() {
		allItemInTheCart.clear();
	}
	
	@PreDestroy
	public void delete(){
		allItemInTheCart = null;
	}
	
}