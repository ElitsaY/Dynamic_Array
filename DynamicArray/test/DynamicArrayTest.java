import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    public static class ComplexType{
        private final int number;
        private final char symbol;

        ComplexType(int number, char symbol){
            this.number = number;
            this.symbol = symbol;
        }
    }

    //constructor tests
    @Test
    public void testDefaultConstructor(){
        DynamicArray<Integer> vector = new DynamicArray<>();
        assertEquals(vector.capacity(), vector.getInitialCapacity(),
                "The capacity should be equal to the initial capacity");
        assertEquals(0, vector.size(), "The size should be 0. The dynamic array is empty");
    }

    @Test
    public void testConstructorWithNegativeSizeArgument() {
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray<Integer>(-5),
                "The constructor should throw exception when negative size is passed as argument");
    }
    @Test
    public void testConstructWithPositiveSizeArgument() {
        DynamicArray<Integer> vector = new DynamicArray<>(5);
        assertEquals(5, vector.size(), "The size of the constructed dynamic array should be 5");
    }

    @Test
    public void testConstructorWithArrayOfPrimitiveType() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        assertEquals(vector.size(), array.size(),
                "The size of the dynamic array should be equal to the static array length");
        for (int i = 0; i < array.size(); i++) {
            assertEquals(array.get(i), vector.getElementAtIndex(i), "The elements should be equal");
        }
    }

    @Test
    public void testConstructorWithArrayOfComplexType() {
        List<ComplexType> complexArray = new ArrayList<>();
        complexArray.add(new ComplexType(19, 'e'));
        complexArray.add(new ComplexType(10, 'l'));
        complexArray.add(new ComplexType(20, 't'));
        complexArray.add(new ComplexType(21, 'z'));

        DynamicArray<ComplexType> vector = new DynamicArray<>(complexArray);

        assertEquals(vector.size(), complexArray.size(),
                "The size of the dynamic array should be equal to the static array length");
        for (int i = 0; i < complexArray.size(); i++) {
            assertEquals(complexArray.get(i), vector.getElementAtIndex(i), "The elements should be equal");
        }
    }

    //getters and setters tests
    @Test
    public void testSetElementAtIndexFuncWithInvalidIndex(){
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        assertThrows(IndexOutOfBoundsException.class, ()-> vector.setElementAtIndex( -1, 20),
                "The function should throw exception. The dynamic array can not have negative indexes");
        assertThrows(IndexOutOfBoundsException.class, ()->vector.setElementAtIndex(4, 20),
                "The function should throw exception. Can not assign value to non existing element");
    }

    @Test
    public void testSetElementAtIndexFuncWithValidIndex(){
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.setElementAtIndex(2, 19);
        assertEquals(19, vector.getElementAtIndex(2), "The 3rd value should be updated to 19");
    }

    @Test
    public void testGetElementAtIndexFuncWithInvalidIndex(){
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        assertThrows(IndexOutOfBoundsException.class, ()-> vector.getElementAtIndex( -1),
                "The function should throw exception. The dynamic array can not have negative indexes");
        assertThrows(IndexOutOfBoundsException.class, ()->vector.getElementAtIndex(6),
                "The function should throw exception. Can not get non existing element");
    }

    //memory related functions tests
    @Test
    public void testReserveFuncWithNegativeRequiredCapacity() {
       DynamicArray<Integer> vector = new DynamicArray<>();
        assertThrows(IllegalArgumentException.class, () -> vector.reserve(-1),
                "The functions should throw exception.Negative space can not be reserved. ");
    }

    @Test
    public void testReserveFuncWithRequiredCapacityLargerThanInitialCapacity() {
        DynamicArray<Integer> vector = new DynamicArray<>();
        vector.reserve(20);
        assertEquals(20, vector.capacity(), "The capacity should be equal to the required capacity");
    }

    @Test
    public void testReserveFuncWithRequiredCapacityLessThanInitialCapacity() {
        DynamicArray<Integer> vector = new DynamicArray<>();
        vector.reserve(5);
        assertEquals(vector.getInitialCapacity(), vector.capacity(),
                "The capacity should be equal to the initial capacity");
    }

    @Test
    public void testResizeFuncWithNegativeNewSize() {
        DynamicArray<Integer> vector = new DynamicArray<>();
        assertThrows(IllegalArgumentException.class, () -> vector.resize(-1),
                "The size of the dynamic array can not be negative");
    }

    @Test
    public void testResizeFuncThatExpandsNonEmptyDynamicArray() {
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.resize(20);
        assertEquals(20, vector.capacity(), "The dynamic array capacity should be equal 20");
    }

    @Test
    public void testResizeFuncThatShrinksNonEmptyDynamicArray() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.resize(3);
        assertEquals(3, vector.size(), "The dynamic array size should be equal to 3");
        assertEquals(array.get(2), vector.getElementAtIndex(vector.size() - 1), "The last element should be 3");
    }

    @Test
    public void testResizeFuncWithEmptyDynamicArray() {
        DynamicArray<Integer> vector = new DynamicArray<>();
        vector.resize(3);
        assertEquals(3, vector.capacity(), "The capacity should be 3");
    }

    @Test
    public void testShrinkToFitFuncWithNonEmptyDynamicArray() {
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.reserve(10);
        vector.shrinkToFit();
        assertEquals(array.size(), vector.capacity(), "The capacity should be equal to the array length");
    }

    @Test
    public void testShrinkToFitFuncWithEmptyDynamicArray() {
        DynamicArray<Integer> vector = new DynamicArray<>();
        vector.shrinkToFit();
        int minimumSpace = 1;
        assertEquals(minimumSpace, vector.capacity(), "The capacity should be equal to the minimum space = 1");
    }

    //dynamic array operations tests
    @Test
    public void testEmptyFuncWithEmptyDynamicArray(){
        DynamicArray<Integer> vector = new DynamicArray<>();
        assertTrue(vector.empty(), "The dynamic array should be empty");
    }

    @Test
    public void testEmptyFuncWithNonEmptyDynamicArray(){
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);
        assertFalse(vector.empty(), "The array is not empty");
    }

    @Test
    public void testClearFuncWithNonEmptyDynamicArray(){
        List<Integer> array = Arrays.asList(1, 2, 3);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.clear();
        assertEquals(0, vector.size(), "The array should be empty");
    }

    @Test
    public void testAddFunctionWithNonEmptyDynamicArray(){
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.add(6);
        assertEquals(array.size() * 2,  vector.capacity(), "The capacity should be double the original capacity");
        assertEquals(6, vector.getElementAtIndex(vector.size() -1 ), "The last element should be six");

        vector.add(7);
        assertEquals(7, vector.getElementAtIndex(vector.size() -1 ), "The last element should be 7");
    }
    @Test
    public void testAddFunctionWithNonEmptyDynamicArrayOgComplexType(){
        List<ComplexType> complexArray = new ArrayList<>();
        complexArray.add(new ComplexType(19, 'e'));
        complexArray.add(new ComplexType(10, 'l'));
        complexArray.add(new ComplexType(20, 't'));
        complexArray.add(new ComplexType(21, 'z'));

        DynamicArray<ComplexType> vector = new DynamicArray<>(complexArray);


        vector.add(new ComplexType(17, 'g'));
        assertEquals(complexArray.size() * 2,  vector.capacity(),
                "The capacity should be double the original capacity");
        ComplexType lastElement = (ComplexType) vector.getElementAtIndex(vector.size() - 1);
        assertTrue(17 == lastElement.number && 'g' == lastElement.symbol,
                "The inserted pair of int and char should be the same as the last element");

        vector.add(new ComplexType(2, 'i'));
        lastElement = (ComplexType) vector.getElementAtIndex(vector.size() - 1);
        assertTrue(2 == lastElement.number && 'i' == lastElement.symbol,
                "The inserted pair of int and char should be the same as the last element");
    }

    @Test
    public void testAddFuncWithEmptyDynamicArray(){
        DynamicArray<Integer> vector = new DynamicArray<>();
        vector.add(19);
        vector.add(9);
        assertEquals(2, vector.size(), "The size should be 2");
        assertEquals(9, vector.getElementAtIndex(vector.size() - 1), "The last element should be 9");

        vector.add(7);
        assertEquals(7, vector.getElementAtIndex(vector.size() - 1), "The last element should be 7");
    }

    @Test
    public void testRemoveFuncOnEmptyDynamicArray(){
        DynamicArray<Integer> vector = new DynamicArray<>();
        assertThrows(IndexOutOfBoundsException.class, ()-> vector.remove(),
                "The function should throw exception because the array is empty. No elements ca be removed");
    }

    @Test
    public void testRemoveFuncWithNonEmptyDynamicArray(){
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        vector.remove();
        assertEquals(array.size() - 1, vector.size(),
                "The size of the dynamic array should be 1 less than array length");
        assertEquals(4, vector.getElementAtIndex(vector.size() - 1),
                "The last element should be equal to 4");

        vector.remove();
        //the result should be as the above tests but with one less position in the static array
        assertEquals(3, vector.size());
        assertEquals(3, vector.getElementAtIndex(vector.size() - 1));
    }

    @Test
    public void testRemoveFunctionWithNonEmptyDynamicArrayOgComplexType(){
        List<ComplexType> complexArray = new ArrayList<>();
        complexArray.add(new ComplexType(19, 'e'));
        complexArray.add(new ComplexType(10, 'l'));
        complexArray.add(new ComplexType(20, 't'));
        complexArray.add(new ComplexType(21, 'z'));

        DynamicArray<ComplexType> vector = new DynamicArray<>(complexArray);


        vector.remove();
        assertEquals(complexArray.size() - 1,  vector.size(),
                "The size of the vector should be one less than the size of the complex array");
        ComplexType lastElement = (ComplexType) vector.getElementAtIndex(vector.size() - 1);
        assertTrue(20 == lastElement.number && 't' == lastElement.symbol,
                "The inserted pair of int and char should be the same as the last element");

        vector.remove();
        //the result should be as the above tests but with one less position in the static array
        lastElement = (ComplexType) vector.getElementAtIndex(vector.size() - 1);
        assertEquals(2, vector.size());
        assertTrue(10 == lastElement.number && 'l' == lastElement.symbol,
                "The inserted pair of int and char should be the same as the last element");
    }

    //interacting with other array methods
    public void testConcatenateFunc(){
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        DynamicArray<Integer> vector = new DynamicArray<>(array);

        List<Integer> array2 = Arrays.asList(6, 7, 8);
        DynamicArray<Integer> vector2 = new DynamicArray<>(array2);

        assertEquals(8, vector.size(), "The size is the sum of the two arrays");
        //the following code check the elements in linear time
        for(int i = 0; i < vector.size(); i++){
            if( i < array.size()){
                assertEquals(array.get(i), vector.getElementAtIndex(i), "The values of the array should be the same as the dyn array");
            }
            else {
                assertEquals(array2.get(i - array.size()), vector.getElementAtIndex(i),
                        "The values of the array2 should be the same as the dyn array");
            }
        }// end of for
    }//end of test

    @Test
    public void testSwapFunctions(){
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        DynamicArray<Integer> vector = new DynamicArray<>(array);
        vector.reserve(10);
        int capacity1 = vector.capacity();

        List<Integer> array2 = Arrays.asList(6, 7, 8);
        DynamicArray<Integer> vector2 = new DynamicArray<>(array2);
        vector2.reserve(15);
        int capacity2 = vector2.capacity();

        vector.swap(vector2);

        //checking the swap pf the capacity value
        assertEquals(capacity1, vector2.capacity());
        assertEquals(capacity2, vector.capacity());

        for(int i = 0; i < vector.size(); i++){
            assertEquals(array2.get(i), vector.getElementAtIndex(i),
                    "The elements of the first dyn array should be the same the elements of the second static array");
        }
        for(int i = 0; i < vector2.size(); i++){
            assertEquals(array.get(i), vector2.getElementAtIndex(i),
                    "The elements of the second dyn array should be the same the elements of the first static array");
        }
    }
}