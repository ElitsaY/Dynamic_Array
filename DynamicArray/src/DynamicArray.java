import java.util.Arrays;

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
          container = new Object[newSize];
          size = newSize;
          capacity = newSize;
     }

     DynamicArray(Object[] newContainer){
          container = Arrays.copyOf(newContainer, newContainer.length);
          size = newContainer.length;
          capacity = newContainer.length;;
     }

     //getters and setters
<<<<<<< HEAD
=======
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
               throw new IndexOutOfBoundsException("The index is out og the array scope");
          }
          return container[index];
     }

     public void setElementAtIndex(int index, Object element){
          if(index < 0 || index >= size){
               throw new IndexOutOfBoundsException("The index is out og the array scope");
          }
          container[index] = element;
     }
>>>>>>> helpUpdate

     //memory related functions

     //dynamic array operations

     //interacting with other array methods
}
