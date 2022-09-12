import java.util.Arrays;
import java.util.Collection;

public class DynamicArray<E>{
     private final static int INITIAL_CAPACITY = 8;
     private final static int GROWTH_COEFFICIENT = 2;

     private int size;
     private int capacity;

     private Object[] container;

     //Constructors
     DynamicArray(){
          container = new Object[INITIAL_CAPACITY];
          size = 0;
          capacity = INITIAL_CAPACITY;
     }

     DynamicArray(int newSize) {
          if(newSize < 1){
               throw new IllegalArgumentException("Dynamic array with negative size can not be constructed");
          }
          container = new Object[newSize];
          size = newSize;
          capacity = newSize;
     }

     DynamicArray(Collection<? extends E> newContainer){
          container = newContainer.toArray();
          size = newContainer.size();
          capacity = newContainer.size();
     }

     //getters and setters
      public int size() {
           return size;
      }

      public int capacity() {
          return capacity;
      }

      public final int getInitialCapacity(){
          return INITIAL_CAPACITY;
      }

     public Object getElementAtIndex(int index) {
          if(index < 0 || index >= size) {
               throw new IndexOutOfBoundsException("The index is out of the array scope");
          }
          return container[index];
     }

     public void setElementAtIndex(int index, Object element){
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


     //interacting with other array methods
}
