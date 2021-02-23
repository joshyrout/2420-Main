package m01;

/** 
 * Represents a freight container that is defined by its length, 
 * width, and country of origin.
 * 
 * @author StarterCode + Joshua Routledge
 *
 */
public class Container implements Comparable<Container> {
	private String country;  // country of origin
	private int id;          // identification number
	private int length;      // in ft
	private double weight;   // in tons
	
	public Container(int id, int length, double weight, String country ) {
		this.id = id;
		this.length = length;
		this.weight = weight;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}	

	public int getLength() {
		return length;
	}

	public double getWeight() {
		return weight;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return String.format("%4d: %2dft %ft %s", id, length, weight, country);
	}


	@Override
	public int compareTo(Container o) {
		return this.country.compareTo(o.country);
	}
}
