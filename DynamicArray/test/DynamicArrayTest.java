import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    public class ComplexType{
        private int number;
        private char symbol;

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
        List<Integer> array = Arrays.asList(1, 2, 3);
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

}