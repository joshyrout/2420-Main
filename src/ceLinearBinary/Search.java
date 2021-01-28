package ceLinearBinary;

public class Search {

    public static int linear(int[] numbers, int key){

        if(numbers.length <= 0 ){
            return -3;
        }
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == key){
                return i;
            }
        }
        return -1;
    }

    public static int binary(int[] numbers, int key){
        if(numbers.length <= 0 ){
            return -3;
        }
        if(isSorted(numbers)){
            int min = 0;
            int max = numbers.length - 1;
            while(min <= max){
                int middle = (min + max) / 2;

                if (numbers[middle] == key){
                    return key;
                } else if (numbers[middle] < key ){
                    min = middle + 1;
                }else{
                    max = middle - 1;
                }
            }
            return -1;
        } else {
            return -2;
        }
    }
    public static boolean isSorted(int[] array){
        for(int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }
}
