package ls.utils;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class Arrays2Test {
   private static int[] getDuplicateInts(int len) {
        int[] res = new int[len];
        for(int i = 0 ; i<len ; ++i) {
            res[i] = i/2;
        }
        return res;
    }

    private static int[] getInts(int len, int value) {
        int[] res = new int[len];
        Arrays.fill(res, value);
        return res;
    }

    @Test
    public void indexOfLowerBound_value_in_index() {
        final int len = 10;
        final int index = 7;
        // Arrange
        int[] v = getDuplicateInts(len);
        
        // Act
        int res = Arrays2.indexOfLowerBound(v, v[index]);
 
        // Assert
        assertEquals(v[index], v[res]);        
        assertEquals(v[res], v[res+1]);        
    }

    @Test
    public void indexOfLowerBound_empty_array() {
        int[] v = {};
        
        assertEquals(0, Arrays2.indexOfLowerBound(v, 7));        
    }
    
    @Test
    public void indexOfLowerBound_one_value() {
        final int len = 10;
        final int value = 1;
        // Arrange
        int[] v = getInts(len, value);
      
        // Assert
        assertEquals(0, Arrays2.indexOfLowerBound(v, value));        
        assertEquals(0, Arrays2.indexOfLowerBound(v, value-1));        
        assertEquals(v.length, Arrays2.indexOfLowerBound(v, value+1));
        				//é suposto o lowerbound retornar um indice fora do array??        
     }
    
    @Test
    public void indexOfLowerBound_multiple_values(){
    	int [] v = new int [10];
    	for (int i = 0; i<v.length; i++)
    		v[i] = i;
    	assertEquals(0,Arrays2.indexOfLowerBound(v, -1));
    	assertEquals(5,Arrays2.indexOfLowerBound(v, 5));
    	assertEquals(9,Arrays2.indexOfLowerBound(v, 10));
    }
    

}
