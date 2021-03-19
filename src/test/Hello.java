package test;

public class Hello {

    public static void main(String[] args) {

        int N = 10;
        int count = N * N;
        for(int i = 0; i < count; i++){
            int inner = i / N;
            int outer = i % N;
            System.out.println(inner + "  " + outer);
        }


    }


}
