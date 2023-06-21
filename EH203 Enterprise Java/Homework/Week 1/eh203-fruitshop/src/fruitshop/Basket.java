package fruitshop;

import java.util.ArrayList;

public class Basket {
	protected ArrayList<Item> items = new ArrayList<Item>();

	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public double totalCost() {
		double cost = 0.0;
		for (Item item : items)
			cost += item.getCost();	
		return cost;
	}
	
	public double checkout() {
		double cost = totalCost();
		items = new ArrayList<Item>();
		return cost;
	}
}
