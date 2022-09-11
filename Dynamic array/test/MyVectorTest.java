import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyVectorTest {

    //constructor tests
    @Test
    public void testDefaultConstructor(){
        MyVector vector = new MyVector();
        assertEquals(vector.capacity(), vector.getInitialCapacity(),
                "The capacity should be equal to the initial capacity");
        assertEquals(0, vector.size(), "The size should be 0. The dynamic array is empty");
    }

    @Test
    public void testConstructorWithNegativeSizeArgument(){
        assertThrows(IllegalArgumentException.class, ()-> new MyVector( -5),
                "The constructor should throw exception when negative size is passed as argument");
    }

    @Test
    public void testConstructWithPositiveSizeArgument(){
        MyVector vector = new MyVector(5);
        assertEquals(5, vector.size(), "The size of the constructed dynamic array should be 5");
    }

    @Test
    public void testConstructorWithArrayArgument(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        assertEquals(vector.size(), array.length,
                "The size of the dynamic array should be equal to the static array length");
        for(int i = 0; i < array.length; i++){
            assertEquals(array[i], vector.getElementAtIndex(i), "The elements should be equal");
        }
    }

    //memory related functions tests

    @Test
    public void testReserveFuncWithNegativeRequiredCapacity(){
        MyVector vector = new MyVector();
        assertThrows(IllegalArgumentException.class, ()->vector.reserve(-1),
        "The functions should throw exception.Negative space can not be reserved. ");
    }

    @Test
    public void testReserveFuncWithRequiredCapacityLargerThanInitialCapacity(){
        MyVector vector = new MyVector();
        vector.reserve(20);
        assertEquals(20, vector.capacity(), "The capacity should be equal to the required capacity");
    }

    @Test
    public void testReserveFuncWithRequiredCapacityLessThanInitialCapacity(){
        MyVector vector = new MyVector();
        vector.reserve(5);
        assertEquals(vector.getInitialCapacity(), vector.capacity(),
                "The capacity should be equal to the initial capacity");
    }

    @Test
    public void testResizeFuncWithNegativeNewSize(){
        MyVector vector = new MyVector();
        assertThrows(IllegalArgumentException.class, ()->vector.resize(-1),
                "The size of the dynamic array can not be negative");
    }

    @Test
    public void testResizeFuncThatExpandsNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.resize(20);
        assertEquals(20, vector.capacity(), "The dynamic array capacity should be equal 20");
    }

    @Test
    public void testResizeFuncThatShrinksNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.resize(3);
        assertEquals(3, vector.size(), "The dynamic arrayn size should be equal to 3");
        assertEquals(array[2], vector.getElementAtIndex(vector.size() - 1), "The last element should be 3");
    }

    @Test
    public void testResizeFuncWithEmptyDynamicArray(){
        MyVector vector = new MyVector();
        vector.resize(3);
        assertEquals(3, vector.capacity(), "The capacity should be 3");
    }

    @Test
    public void testShrinkToFitFuncWithNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.reserve(10);
        vector.shrinkToFit();
        assertEquals(array.length, vector.capacity(), "The capacity should be equal to the array length");
    }

    @Test
    public void testShrinkToFitFuncWithEmptyDynamicArray(){
        MyVector vector = new MyVector();
        vector.shrinkToFit();
        int minimumSpace = 1;
        assertEquals(minimumSpace, vector.capacity(), "The capacity should be equal to the minimum space = 1");
    }
}