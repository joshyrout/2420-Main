package ceLinearBinary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    int[] numArray = {0,1,2,3,4,5,6,7,8,9};
    int[] emptyArray = {};
    int[] unsortedArray = {0,5,2,3,4,1,6,7,8,9};

    @Test
    void linear_elementNotInArray_returnNegativeOne(){
        assertEquals(-1, Search.linear(numArray, 22));
        }


    @Test
    void linear_firstElement_returnElement0(){
        assertEquals(0, Search.linear(numArray, 0));
    }

    @Test
    void linear_lastElement_returnLastElement(){
        assertEquals(9, Search.linear(numArray, 9));
    }

    @Test
    void linear_middleElement_returnMiddleElementRoundedDown(){
        assertEquals(4, Search.linear(numArray, 4));
    }
    @Test
    void linear_emptyArray_returnNegativeOne(){

        assertEquals(-3, Search.linear(emptyArray, 3));
    }

    @Test
    void binary_arrayNotSorted_returnNegativeTwo(){
        assertEquals(-2, Search.binary(unsortedArray, 2));
    }

    @Test
    void binary_elementNotInArray_returnNegativeOne(){
        assertEquals(-1, Search.binary(numArray, 22));
    }


    @Test
    void binary_firstElement_returnElement0(){
        assertEquals(0, Search.binary(numArray, 0));
    }

    @Test
    void binary_lastElement_returnLastElement(){
        assertEquals(9, Search.binary(numArray, 9));
    }

    @Test
    void binary_middleElement_returnMiddleElementRoundedDown(){
        assertEquals(4, Search.binary(numArray, 4));
    }
    @Test
    void binary_emptyArray_returnNegativeOne(){

        assertEquals(-3, Search.binary(emptyArray, 3));
    }

}
