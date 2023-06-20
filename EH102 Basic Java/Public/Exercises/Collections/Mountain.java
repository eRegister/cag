import java.util.HashSet;
import java.util.Set;


public class Mountain {
	
	private String name;
	private int height;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Set<Mountain> hs = new HashSet<Mountain>();
		
		hs.add(new Mountain("Chimborazo", 200));
		hs.add(new Mountain("Cotopaxi", 300));
		hs.add(new Mountain("Chimborazo", 400));
		System.out.println(hs);
	}
	
	Mountain(String name, int height) {
		this.height = height;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public boolean equals(Object o){
		return(this.getName().equals(((Mountain)o).getName()));
	}
	@Override
	public int hashCode(){
		return this.getName().hashCode();
	}
	
	@Override
	public String toString(){
		return this.getName().toString();
	}
}
