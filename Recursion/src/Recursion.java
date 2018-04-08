import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Recursion {
	//public static int [] array = new int [] {30, 42, -293,2,3,4000,5,6,7,8,9};
	public static void main (String[] args){
		/*
		List<Integer> numbers = new ArrayList<Integer>()
				{{
					add(42);
					add(30);
					add(-293);
					add(43);
					add(43);
					add(433);
					add(4331);
					add(44);
				}};
		*/
		//int minValue =findMin(array);
		//System.out.println(minValue);
		
		//System.out.println(sublist(array,0,2));
		//printArr(array);
//		List<Integer> result = sort(numbers);
//		for(Integer number: result) {
//			System.out.println(number);
//		}
		//System.out.println(fib(60));
				//System.out.println(fibdynamic(3));
				int a =1;
				int b =1;
				int c=0;
				int d=0;
				System.out.println(trees(a,b,c,d, -1));
				
	}
	
	
	public static int findMin(int[] array) {
		if (array.length == 1) {
			return array [0];
		}else {
			int minOfRest = findMin(sublist(array, 1, array.length-1));
			if (array[0] > minOfRest) {
				return array[0];
			}
			else return minOfRest;
		}
		
	}
	
	public static int[] sublist(int[] inputArr, int start, int end) {
		if (end < start) {
			System.out.println(String.format("start:%d end:%d", start, end));
		}
		int[] arrayNewElem = new int[end-start+1];
		for (int j=0; j<arrayNewElem.length; j++) {
			if (start + j >= inputArr.length) System.out.println(String.format("%d %d",start, j));
			arrayNewElem[j]= inputArr[start+j] ;
		}
		return arrayNewElem;
	}
	
	static void printArr(int[] arr) {

		for (int i = 0; i <= arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	
	//end of task1
	
	
	public static List<Integer> sort(List<Integer> list) {
		if(list.size() == 1) {
			return list;
		}
		
		List<Integer> arrayLeft = sort(list.subList(0, list.size()/2));
		List<Integer> arrayRight = sort(list.subList(list.size()/2,list.size()));
		List<Integer> result = new ArrayList<>();
		//List<Integer> arrayLeft = sort(list.subList(start, (start+end)/2), start, (start +end)/2);
		//List<Integer> arrayRight = sort(list.subList((start+end)/2, end-1), (start + end)/2, end);
		//List<Integer>result = new ArrayList<Integer>();
		//int[] arrayLeft = sort(sublist(arrayToSort, start, (start+end)/2), start, (start+end)/2);
		//int[] arrayRight = sort(sublist(arrayToSort, (start+end)/2, end - 1), (start+end)/2 + 1, end - 1);
		//int[] result = new int[arrayToSort.length]; //warning
		int i=0, j=0, k=0;
		
		for(; i<arrayLeft.size() && j<arrayRight.size(); k++) {
			if (arrayLeft.get(i) < arrayRight.get(j)) {
				result.add(arrayLeft.get(i));
				i++;
			} else {
				result.add(arrayRight.get(j));
				j++;
			}
		}
		
		for(; i < arrayLeft.size(); i++, k++) {
			result.add(arrayLeft.get(i));
		}
		
		for (; j < arrayRight.size(); j++, k++) {
			result.add(arrayRight.get(j));
		}
		
		
		return result;
	}

	static HashMap<Integer, Long> memory = new HashMap<>();
	public static Long fib(int n) {
		if (n == 0 || n == 1) {
			return new Long(1);
		}
		if (memory.containsKey(n)) {
			return memory.get(n);
		}
		else {
			memory.put(n, fib(n-1) + fib(n-2));
			return memory.get(n);
		}
//		int fibP = fib(n-1);
//		int fibA = fib(n-2);
//		return fibP + fibA;
		
		
		
	}
	public static int fibdynamic(int n) {
		int current=1;
		int pr2=0;
		int pr1=1;
		for(int i=0; i<n; i++) {
			current = pr1 +pr2;
			pr2=pr1;
			pr1=current;
			
		}
		return current;
	}
	
	
	static long trees(int aCount, int bCount, int cCount, int dCount, int previous) {
	      /* prev = 0 -> A
	              = 1 -> B
	              = 2 -> C
	              = 3 -> D
	      */
	      if (aCount + bCount + cCount + dCount == 0) {
	        return 1;
	      }
	 
	      long count = 0;
	      if (previous != 0 && aCount > 0) {
	        count += trees(aCount - 1, bCount, cCount, dCount, 0);
	      }
	      if (previous != 1 && bCount > 0) {
	        count += trees(aCount, bCount - 1, cCount, dCount, 1);
	      }
	      if (previous != 2 && cCount > 0) {
	         count += trees(aCount, bCount, cCount - 1, dCount, 2);
	      }
	      if (previous != 3 && dCount > 0) {
	        count += trees(aCount, bCount, cCount, dCount - 1, 3);
	      }
	 
	      return count;
	 
	    }
	
	public int count11(String str) {
		  if(str.equals("") || str.length()<2){
		    return 0;
		  }
		  if(str.charAt(0)=='1' && str.charAt(1)=='1'){
		    return 1 + count11(str.substring(2));
		    
		  }
		  
		  return count11(str.substring(1));
		}
	
	
	
	public boolean strCopies(String str, String sub, int n) {
		 if(helpFunc(str, sub) == n) return true;
		 else return false;
		}

		public int helpFunc(String str, String sub){
		   if(str.equals("") || str.length()<sub.length()){
		    return 0;
		  }
		  
		  if(str.substring(0, sub.length()).equals(sub)){
		    return 1 + helpFunc(str.substring(1), sub);
		  }
		  return helpFunc(str.substring(1), sub);
		}
		
		public int powerN(int base, int n){
			  if(n==0){
			    return 1;
			  }
			  return base * powerN(base, n-1);
			}
		
		public String changeXY(String str) {
			  if(str.equals("")){
			    return str;
			  }
			  
			  if(str.charAt(0) == 'x'){
			    return "y" + changeXY(str.substring(1));
			  }
			  
			  return str.charAt(0) + changeXY(str.substring(1));
			}
		
		public boolean array6(int[] nums, int index) {
			  if(index> nums.length-1 || nums.length<1){
			    return false;
			  }
			  
			  if(nums[index]%10 == 6){
			    return true;
			  }
			  
			  return array6(nums, index+1);
			}
		//put stars between adjacent characters
		public String allStar(String str) {
			  if(str.equals("") || str.length() == 1){
			    return str;
			  }
			  return str.charAt(0) + "*" + allStar(str.substring(1));
			}
		
		public int countPairs(String str) {
			  if(str.equals("") || str.length()<3){
			    return 0;
			  }
			  
			  if(str.charAt(0) == str.charAt(2)){
			    return 1 + countPairs(str.substring(1));
			  }
			  
			  return countPairs(str.substring(1));
			}
		
		//remove repeating characters
		public String stringClean(String str) {
			  if(str.equals("") || str.length()<2){
			    return str;
			  }
			  
			  if(str.charAt(0) == str.charAt(1)){
			    return  stringClean(str.substring(1));
			  }
			  
			  return str.charAt(0) + stringClean(str.substring(1));
			}
		
		//recursive backtracking for checking if elements of array can form given sum
		public boolean groupSum(int start, int[] nums, int target) {
		    if (start >= nums.length) {
		    	return target == 0;
		    }
		    return groupSum(start + 1, nums, target - nums[start])|| groupSum(start + 1, nums, target);
		}
		
		//Given a string and a non-empty substring sub, 
		//compute recursively the largest substring which starts and ends with sub and return its length.
		public int strDist(String str, String sub) {
			if(str.equals("") || str.length()<sub.length()){
				return 0;
			}

			if(str.substring(0, sub.length()).equals(sub)){
				if(str.substring(str.length()-sub.length(), str.length()).equals(sub)){
					return str.substring(0, str.length()).length();
				}
				else{
					return strDist(str.substring(0, str.length()-1), sub);
				}

			}

			return strDist(str.substring(1), sub);
		}

}
	

