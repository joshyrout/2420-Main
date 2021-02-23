package ceRecursion;

import java.util.ArrayList;

public class Recursion {

    public static void main(String[] args) {

        System.out.println("Hailstone numbers");
        System.out.println("-----------------");
        hailstone(3);
        hailstone(16);
        hailstone(17);
        hailstone(24);
    }

    public static void hailstone(int seed){
        if(seed <= 0){
            throw new IllegalArgumentException();
        }
        if(seed == 1){
            System.out.println(seed);
        } else if(seed % 2 == 0){
            System.out.print(seed + " ");
            hailstone(seed/2);
        } else {
            System.out.print(seed + " ");
            hailstone((3 * seed) + 1);
        }
    }
}
