package m01;

import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * CSIS-2420 Midterm1
 * 
 * @author StarterCode + Joshua Routledge
 *
 */
public class Midterm1 {

	public static void main(String[] args) {
		Container[] containers = {
				new Container(1234, 20, 1.9, "China"),
				new Container(1235, 40, 3.97, "USA"),
				new Container(1236, 40, 4.22, "China"),
				new Container(1237, 20, 2.16, "Ghana"),
				new Container(1238, 20, 2.1, "USA"),
				new Container(1239, 40, 4.08, "Italy"),
				new Container(1240, 40, 3.81, "China"),
				new Container(1241, 40, 4.2, "USA"),
				new Container(1242, 20, 1.82, "Italy")
		};
		
		StdOut.println("Containers: ");
		StdOut.println("=========== ");
		for(Container c : containers) {
			StdOut.println(c);
		}	
		System.out.println();
		
		StdOut.println("= = = =    Part 1   = = = =\n");
		StdOut.println("Containers by natural order:");
		StdOut.println("============================");
		Arrays.sort(containers);
		for(int i = 0; i < containers.length; i++){
			StdOut.println(containers[i].toString());
		}
		StdOut.println();
		
		StdOut.println("Containers in reverse order:");
		StdOut.println("============================");
		Arrays.sort(containers, Collections.reverseOrder());
		for(int i = 0; i < containers.length; i++){
			StdOut.println(containers[i].toString());
		}
		StdOut.println();

		StdOut.println("= = = =    Part 2    = = = =\n");
		StdOut.println("Foreign Containers:");
		StdOut.println("===================");
		Arrays.sort(containers);
		for(int i = 0; i < containers.length; i++){
			if(!containers[i].getCountry().equals("USA")){
				StdOut.println(containers[i].toString());
			}
		}
	}

}
