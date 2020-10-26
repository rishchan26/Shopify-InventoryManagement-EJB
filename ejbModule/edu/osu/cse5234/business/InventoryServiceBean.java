package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
public class InventoryServiceBean implements InventoryService {
	private Inventory inventory;
    /**
     * Default constructor. 
     */
	
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    	this.inventory = new Inventory();
    	List<String> names = new ArrayList<String>(
				Arrays.asList("Potatoes", "Tomatoes", "Onions", "Chips", "Milk")
		);
		List<String> prices = new ArrayList<String>(
				Arrays.asList("3.50", "4.00", "2.50", "4.00", "5.60")
		);
		List<String> quantities = new ArrayList<String>(
				Arrays.asList("3", "4", "2", "4", "5")
		);
		List<Item> items = new ArrayList<Item>();
		for(int i = 0; i < names.size(); i++) {
			Item it = new Item();
			it.setName(names.get(i));
			it.setPrice(prices.get(i));
			it.setQuantity(quantities.get(i));
			items.add(it);
		}
		this.inventory.setItems(items);
    }
    
    @Override
    public Inventory getAvailableInventory() {
    	return this.inventory;
    }
    @Override
	public boolean validateQuantity(List<Item> items) {
    	for(Item item : items) {
    		for(Item inv : this.inventory.getItems()) {
    			if(item.getName().equals(inv.getName())) {
    				int itemQuantity = Integer.parseInt(item.getQuantity());
    				int invQuantity = Integer.parseInt(inv.getQuantity());
    				if(itemQuantity > invQuantity) {
    					return false;
    				}
    			}
    		}
    	}
    	return true;
    }
    @Override
	public boolean updateInventory(List<Item> items) {
    	for(Item item : items) {
    		for(Item inv : this.inventory.getItems()) {
    			if(item.getName().equals(inv.getName())) {
    				int itemQuantity = Integer.parseInt(item.getQuantity());
    				int invQuantity = Integer.parseInt(inv.getQuantity());
    				invQuantity -= itemQuantity;
    				inv.setQuantity(Integer.toString(invQuantity));
    			}
    		}
    	}
    	return true;
    }

}
