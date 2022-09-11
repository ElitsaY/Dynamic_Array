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
        //container = new int[array.length];
        container = Arrays.copyOf(array, array.length);
        size = array.length;
        capacity = array.length;
    }
    //memory related functions

    public void reserve(int requiredCapacity){
        if(requiredCapacity < 1){
            //exception
        }

        if (capacity < requiredCapacity){
            container = Arrays.copyOf(container, requiredCapacity);
            capacity = requiredCapacity;
        }
    }
    //resize
    public void resize(int newCapacity){
        if(newCapacity < 1){
            //exception
        }

        container = Arrays.copyOf(container, newCapacity);
        capacity = newCapacity;

        if( size > newCapacity) {
            size = newCapacity;
        }
    }

    public void shrinkToFit(){
        container = Arrays.copyOf(container, size);
        capacity = size;
    }

    //dynamic array operations

    //interaction with other arrays
}
