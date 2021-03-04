package ceHeapSort;

import edu.princeton.cs.algs4.Heap;

public class DemoHeap {

    private static Mail[] heap;
    private static int N = 0;

    public static void main(String[] args) {
        int simSize = 25;
        System.out.println(simSize + " Random mail objects");
        System.out.println("-----------------------");
        populateHeap(simSize);
        System.out.println();
        System.out.println("Mail Delivery:");
        System.out.println("--------------");
        removeMail();

    }

    private static void populateHeap(int size){
        size = size + 1;
        heap = new Mail[size];
        for(int i = 1; i < size; i++){
            N++;
            Mail newMail = new Mail();
            heap[N] = newMail;
            swim(N);
            System.out.println(newMail);
        }
    }

    private static void removeMail(){
        while(!isEmpty()){
            Mail removedMail = heap[1];
            heap[1] = heap[N];
            heap[N] = null;
            N--;
            sink(1);
            System.out.println(removedMail);
        }
    }

    private static void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if (j < N && less(j, j+1)){
                j++;
            }
            if(!less(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }

    private static void swim(int k){
        while(k > 1 && less(k/2, k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    private static boolean less(int i, int j){
        return heap[i].compareTo(heap[j]) > 0;
    }

    private static void exch(int i, int j){
        Mail holder = heap[i];
        heap[i] = heap[j];
        heap[j] = holder;
    }

    private static boolean isEmpty(){
        return (N == 0);
    }

    private static void printHeap(){
        for(int i = 1; i < heap.length; i++) {
            System.out.println(heap[i]);
        }
    }
}
