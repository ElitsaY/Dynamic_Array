import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyVectorTest {

    //constructor tests
    @Test
    public void testDefaultConstructor(){
        MyVector vector = new MyVector();
        assertEquals(vector.capacity(), vector.getInitialCapacity(),
                "The capacity should be equal to the initial capacity");
        assertEquals(vector.size(), 0, "The size should be 0. The vector is empty");
    }

    @Test
    public void testConstructorWithNegativeSizeArgument(){
        assertThrows(IllegalArgumentException.class, ()-> new MyVector( -5),
                "The constructor should throw exception when negative size is passed as argument");
    }

    @Test
    public void testConstructWithPositiveSizeArgument(){
        MyVector vector = new MyVector(5);
        assertEquals(vector.size(), 5, "The size of the constructed array should be 5");
    }

    @Test
    public void testConstructorWithArrayArgument(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        assertEquals(vector.size(), array.length,
                "The size of the vector should be equal to the array length");
        for(int i = 0; i < array.length; i++){
            assertEquals(array[i], vector.getElementAtIndex(i), "The elements should be equal");
        }
    }

    //memory related functions tests

}