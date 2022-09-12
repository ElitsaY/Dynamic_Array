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
}