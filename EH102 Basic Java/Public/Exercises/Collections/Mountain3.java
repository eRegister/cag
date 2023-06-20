import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Mountain3 {
	
	private String name;
	private int height;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Mountain3 m  = new Mountain3();
		m.setName("Kili");
		HashMap<Mountain3,Integer> myMountain = new HashMap<Mountain3,Integer>();
		myMountain.put(m, 2);
		
		System.out.println("value="+myMountain.get(m));
		
		Mountain3 m2  = new Mountain3();
		m2.setName("Kili");
		
		System.out.println("value2="+myMountain.get(m2));
	}
	
	Mountain3() {
		this.height = 0;
		this.name = "myMountain";
	}
	
	Mountain3(String name, int height) {
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
		return(this.getName().equals(((Mountain3)o).getName()));
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
