package SortTask;

import java.util.Arrays;

public class SortArrTest {
    public boolean arrsAreEqual(double[] arr1, double[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public boolean checkArr(double[] arr) {
        boolean k = true;
        for(int i=0; i<arr.length-1; i++){
            if(arr[i] > arr[i+1]){
                k=false;
                break;
            }
        }
        return k;
    }

    public boolean arrHasLength(double[] arr, int len) {
        return arr.length == len;
    }
}
