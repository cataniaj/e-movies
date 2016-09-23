/*
 *	Author:      Jérémy Leyvraz
 *	Date:        23 sept. 2016
 */

package fr.imag.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.json.JsonObject;

import fr.imag.entities.User;

public class Cart implements CartLocal{
	
	private List<Item> allItemInTheCart;
	private User customer;
	
	@PostConstruct
	public void initialize(){
		allItemInTheCart = new ArrayList<Item>();
	}

	@Override
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

	@Override
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

	@Override
	public JsonObject getAllItem() {
		return null;
	}

	@Override
	public boolean remove() {
		try{
			allItemInTheCart.clear();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@PreDestroy
	public boolean delete(){
		try{
			allItemInTheCart = null;
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
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

	@Override
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
	
	@Override
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
