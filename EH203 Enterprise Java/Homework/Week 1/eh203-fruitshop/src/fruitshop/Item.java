package fruitshop;

public class Item {
	protected static int nextId = 1;
	
	protected int id;
	protected String name;
	protected double cost;
	
	public Item(String name, float cost) {
		this.id = nextId++;
		this.name = name;
		this.cost = cost;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}	
}
