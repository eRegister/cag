package fruitshop;

public class Stock {
	protected static Item[] items = new Item[] {
		new Item("Apple", 150),
		new Item("Banana", 100),
		new Item("Marakuja", 50),
		new Item("Pineapple", 500),
		new Item("Melon", 400),
		new Item("Strawberries", 1000),
		new Item("Lemon", 200)
	};
	
	public static Item[] getItems() {
		return items;
	}
	
	public static Item getItem(int id) {
		for (Item item : items) {
			if (item.getId() == id)
				return item;
		}
		return null;
	}
}
