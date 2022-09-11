import java.util.Arrays;

public class MyVector {
    private final static int GROWTH_COEFFICIENT = 2;
    private final static int INITIAL_CAPACITY = 8;

    private int capacity;
    private int size;

    private int[] container;

    //constructors
    MyVector(){
        container = new int[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }

    MyVector(int newSize){
        container = new int[newSize];
        size = newSize;
        capacity = newSize;
    }

    MyVector(int[] array){
        container = new int[array.length];
        container = Arrays.copyOf(array, array.length);
        size = array.length;
        capacity = array.length;
    }
    //memory related functions

    //dynamic array operations

    //interaction with other arrays
}
