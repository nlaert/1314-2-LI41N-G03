package ls.utils;

public class Arrays2 {
    
	
    public static int indexOfLowerBound(int[] a, int n) {
    	
    	int beginIndex = 0;
    	int endIndex = a.length;
    	int mid;
    	
    	while( beginIndex < endIndex ){
    		mid = endIndex + beginIndex / 2;
    		if( n > a[mid] ) beginIndex = mid + 1;
    		else endIndex = mid - 1;
    	}
    	
    	return beginIndex;
    }

}
