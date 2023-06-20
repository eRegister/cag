import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Mountain2 implements Comparable<Mountain2>  {
	
	private String name;
	private int height;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Set<Mountain2> ts = new TreeSet<Mountain2>();
		
		ts.add(new Mountain2("Cotopaxi", 300));
		ts.add(new Mountain2("Pompei", 400));
		ts.add(new Mountain2("Kracatoa", 345));
		ts.add(new Mountain2("Shasta", 128));
		ts.add(new Mountain2("Chimborazo", 200));
		System.out.println("sorted Mountains="+ts);
	}
	
	Mountain2(String name, int height) {
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
		return(this.getName().equals(((Mountain2)o).getName()));
	}
	@Override
	public int hashCode(){
		return this.getName().hashCode();
	}
	
	@Override
	public String toString(){
		return this.getName().toString();
	}
	
	@Override
	public int compareTo(Mountain2 m){
		return(this.getName().compareTo(m.getName()));
	}

}