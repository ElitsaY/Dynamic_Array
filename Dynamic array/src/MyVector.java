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
        if(newSize < 1){
            throw new IllegalArgumentException("Can not construct array with negative size");
        }
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

    //getters and setters
    public int size(){
        return size;
    }

    public int capacity(){
        return capacity;
    }

    public final int getInitialCapacity(){
        return INITIAL_CAPACITY;
    }

    public int getElementAtIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("The index is out og the array scope");
        }
        return container[index];
    }

    public void setElementAtIndex(int index, int element){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("The index is out og the array scope");
        }
        container[index] = element;
    }

    //memory related functions
    public void reserve(int requiredCapacity){
        if(requiredCapacity < 1){
            throw new IllegalArgumentException("The requiredCapacity should be more than 1");
        }

        if (capacity < requiredCapacity){
            container = Arrays.copyOf(container, requiredCapacity);
            capacity = requiredCapacity;
        }
    }
    //resize
    public void resize(int newCapacity){
        if(newCapacity < 1){
            throw new IllegalArgumentException("The newCapacity should be more than 1");
        }

        container = Arrays.copyOf(container, newCapacity);
        capacity = newCapacity;

        if( size > newCapacity) {
            size = newCapacity;
        }
    }

    public void shrinkToFit(){
        if(size == 0){
            final int minimumSpace = 1;
            container = Arrays.copyOf(container, minimumSpace);
            capacity = minimumSpace;
        }
        else {
            container = Arrays.copyOf(container, size);
            capacity = size;
        }
    }

    //dynamic array operations

    public boolean empty(){
        return size == 0;
    }

    public void clear(){
        size = 0;
    }

    public void add(int element){
        if( size + 1 >= capacity){
            resize(size * GROWTH_COEFFICIENT);
        }
        container[size++] = element;
    }

    public void remove(){
        if(size <= 0){
            throw new IndexOutOfBoundsException("The dynamic array is already empty");
        }
        container[--size] = 0;
    }

    //interaction with other arrays functions
    public void swap(MyVector other){
       int[] tempContainer;
       int tempSize, tempCapacity;

       tempContainer = other.container;
       tempSize = other.size;
       tempCapacity = other.capacity;

       other.container = container;
       other.size = size;
       other.capacity = capacity;

       container = tempContainer;
       size = tempSize;
       capacity = tempCapacity;
    }

    public void concatenate(int[] array){
        for(int element: array){
            this.add(element);
        }
    }

}
