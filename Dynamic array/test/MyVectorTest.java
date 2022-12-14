import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyVectorTest {

    //constructor tests
    @Test
    public void testDefaultConstructor() {
        MyVector vector = new MyVector();
        assertEquals(vector.capacity(), vector.getInitialCapacity(),
                "The capacity should be equal to the initial capacity");
        assertEquals(0, vector.size(), "The size should be 0. The dynamic array is empty");
    }

    @Test
    public void testConstructorWithNegativeSizeArgument() {
        assertThrows(IllegalArgumentException.class, () -> new MyVector(-5),
                "The constructor should throw exception when negative size is passed as argument");
    }

    @Test
    public void testConstructWithPositiveSizeArgument() {
        MyVector vector = new MyVector(5);
        assertEquals(5, vector.size(), "The size of the constructed dynamic array should be 5");
    }

    @Test
    public void testConstructorWithArrayArgument() {
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        assertEquals(vector.size(), array.length,
                "The size of the dynamic array should be equal to the static array length");
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], vector.getElementAtIndex(i), "The elements should be equal");
        }
    }

    //memory related functions tests

    @Test
    public void testReserveFuncWithNegativeRequiredCapacity() {
        MyVector vector = new MyVector();
        assertThrows(IllegalArgumentException.class, () -> vector.reserve(-1),
                "The functions should throw exception.Negative space can not be reserved. ");
    }

    @Test
    public void testReserveFuncWithRequiredCapacityLargerThanInitialCapacity() {
        MyVector vector = new MyVector();
        vector.reserve(20);
        assertEquals(20, vector.capacity(), "The capacity should be equal to the required capacity");
    }

    @Test
    public void testReserveFuncWithRequiredCapacityLessThanInitialCapacity() {
        MyVector vector = new MyVector();
        vector.reserve(5);
        assertEquals(vector.getInitialCapacity(), vector.capacity(),
                "The capacity should be equal to the initial capacity");
    }

    @Test
    public void testResizeFuncWithNegativeNewSize() {
        MyVector vector = new MyVector();
        assertThrows(IllegalArgumentException.class, () -> vector.resize(-1),
                "The size of the dynamic array can not be negative");
    }

    @Test
    public void testResizeFuncThatExpandsNonEmptyDynamicArray() {
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.resize(20);
        assertEquals(20, vector.capacity(), "The dynamic array capacity should be equal 20");
    }

    @Test
    public void testResizeFuncThatShrinksNonEmptyDynamicArray() {
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.resize(3);
        assertEquals(3, vector.size(), "The dynamic array size should be equal to 3");
        assertEquals(array[2], vector.getElementAtIndex(vector.size() - 1), "The last element should be 3");
    }

    @Test
    public void testResizeFuncWithEmptyDynamicArray() {
        MyVector vector = new MyVector();
        vector.resize(3);
        assertEquals(3, vector.capacity(), "The capacity should be 3");
    }

    @Test
    public void testShrinkToFitFuncWithNonEmptyDynamicArray() {
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.reserve(10);
        vector.shrinkToFit();
        assertEquals(array.length, vector.capacity(), "The capacity should be equal to the array length");
    }

    @Test
    public void testShrinkToFitFuncWithEmptyDynamicArray() {
        MyVector vector = new MyVector();
        vector.shrinkToFit();
        int minimumSpace = 1;
        assertEquals(minimumSpace, vector.capacity(), "The capacity should be equal to the minimum space = 1");
    }

    //dynamic array operations tests
    @Test
    public void testEmptyFuncWithEmptyDynamicArray(){
        MyVector vector = new MyVector();
        assertTrue(vector.empty(), "The dynamic array should be empty");
    }

    @Test
    public void testEmptyFuncWithNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        assertFalse(vector.empty(), "The array is not empty");
    }

    @Test
    public void testClearFuncWithNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.clear();
        assertEquals(0, vector.size(), "The array should be empty");
    }

    @Test
    public void testAddFunctionWithNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.add(6);
        assertEquals(array.length * 2,  vector.capacity(), "The capacity should be double the original capacity");
        assertEquals(6, vector.getElementAtIndex(vector.size() -1 ), "The last element should be six");

        vector.add(7);
        assertEquals(7, vector.getElementAtIndex(vector.size() -1 ), "The last element should be 7");
    }

    @Test
    public void testAddFuncWithEmptyDynamicArray(){
        MyVector vector = new MyVector();
        vector.add(19);
        vector.add(9);
        assertEquals(2, vector.size(), "The size should be 2");
        assertEquals(9, vector.getElementAtIndex(vector.size() - 1), "The last element should be 9");

        vector.add(7);
        assertEquals(7, vector.getElementAtIndex(vector.size() - 1), "The last element should be 7");
    }

    @Test
    public void testRemoveFuncOnEmptyDynamicArray(){
        MyVector vector = new MyVector();
        assertThrows(IndexOutOfBoundsException.class, ()-> vector.remove(),
                "The function should throw exception because the array is empty. No elements ca be removed");
    }

    @Test
    public void testRemoveFuncWithNonEmptyDynamicArray(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);

        vector.remove();
        assertEquals(array.length - 1, vector.size(), "The size of the dynamic array should be 1 less than array length");
        assertEquals(array[array.length - 2], vector.getElementAtIndex(vector.size() - 1),
                "The last element should be equal to 4");

        vector.remove();
        //the result should be as the above tests but with one less position in the static array
        assertEquals(3, vector.size());
        assertEquals(3, vector.getElementAtIndex(vector.size() - 1));
    }

    //getters and setters exception check
    @Test
    public void testSetElementAtIndexFuncWithInvalidIndex(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        assertThrows(IndexOutOfBoundsException.class, ()-> vector.setElementAtIndex( -1, 20),
                "The function should throw exception. The dynamic array can not have negative indexes");
        assertThrows(IndexOutOfBoundsException.class, ()->vector.setElementAtIndex(6, 20),
                "The function should throw exception. Can not assign value to non existing element");
    }

    @Test
    public void testSetElementAtIndexFuncWithValidIndex(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.setElementAtIndex(2, 19);
        assertEquals(19, vector.getElementAtIndex(2), "The 3rd value should be updated to 19");
    }

    @Test
    public void testGetElementAtIndexFuncWithInvalidIndex(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        assertThrows(IndexOutOfBoundsException.class, ()-> vector.getElementAtIndex( -1),
                "The function should throw exception. The dynamic array can not have negative indexes");
        assertThrows(IndexOutOfBoundsException.class, ()->vector.getElementAtIndex(6),
                "The function should throw exception. Can not get non existing element");
    }

    //interaction with other section
    @Test
    public void testConcatenateFunc(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);

        int[] array2 = {6, 7, 8};
        vector.concatenate(array2);

        assertEquals(8, vector.size(), "The size is the sum of the two arrays");
        //the following code check the elements in linear time
        for(int i = 0; i < vector.size(); i++){
            if( i < array.length){
                assertEquals(array[i], vector.getElementAtIndex(i), "The values of the array should be the same as the dyn array");
            }
            else {
                assertEquals(array2[i - array.length], vector.getElementAtIndex(i),
                        "The values of the array2 should be the same as the dyn array");
            }
        }// end of for
    }//end of test

    @Test
    public void testSwapFunctions(){
        int[] array = {1, 2, 3, 4, 5};
        MyVector vector = new MyVector(array);
        vector.reserve(10);
        int capacity1 = vector.capacity();

        int[] array2 = {6, 7, 8};
        MyVector vector2 = new MyVector(array2);
        vector2.reserve(15);
        int capacity2 = vector2.capacity();

        vector.swap(vector2);

        //checking the swap pf the capacity value
        assertEquals(capacity1, vector2.capacity());
        assertEquals(capacity2, vector.capacity());

        for(int i = 0; i < vector.size(); i++){
            assertEquals(array2[i], vector.getElementAtIndex(i),
                    "The elements of the first dyn array should be the same the elements of the second static array");
        }
        for(int i = 0; i < vector2.size(); i++){
            assertEquals(array[i], vector2.getElementAtIndex(i),
                    "The elements of the second dyn array should be the same the elements of the first static array");
        }
    }

}