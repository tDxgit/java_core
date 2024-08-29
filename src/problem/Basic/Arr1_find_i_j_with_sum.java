package problem.Basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// find i j 
// i,j is index of array
// i!=j 
// arr[i]+arr[j] = k


public class Arr1_find_i_j_with_sum {
	
	public static void main(String[] args) {
		
        int arr[] = {1, 5, 2, 6, 9, 4, 3, 7, 8, 4, 10, 1};
        Map<Integer, List<Integer>> map = new HashMap<>();
        int k = 10;

        for (int i = 0; i < arr.length; i++) {
            int temp = k - arr[i];
            // Nếu tìm thấy temp trong map, in ra các cặp chỉ số
            if (map.containsKey(temp)) {
                for (int j : map.get(temp)) {
                    System.out.println("[i,j]: " + i + ", " + j);
                }
            }
            // Thêm chỉ số i vào map với giá trị là arr[i]
            map.computeIfAbsent(arr[i], key -> new ArrayList<>()).add(i);
        }
    }
}
