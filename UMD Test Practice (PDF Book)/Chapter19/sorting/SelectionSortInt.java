package sorting;

import timer.StopWatch;

/**
 * Sorts a given array by locating the smallest element and swapping it with current marker 
 * index until the marker index reaches the end of the array.
 * The "sortAscending" algorithm has a run time of O(n^2)
 * @author eashaan
 *
 */
public class SelectionSortInt {

	/**
	 * Sorts the given array in ascending order
	 * @param arrayToSort array that will be sorted
	 */
	public void sortAscending(int[] arrayToSort){
		//Marker Index represents the position of which all elements to the left are checked.
		for(int markerIndex = 0; markerIndex < arrayToSort.length; markerIndex++){
			swapValues(arrayToSort, findSmallestElementIndex(arrayToSort, markerIndex), markerIndex);
		}
	}
	
	/**
	 * Finds and returns the index of the smallest element in the array
	 * @param array The array it searches
	 * @param beginIndex Index where to begin
	 * @return index of smalles element
	 */
	public int findSmallestElementIndex(int[] array, int beginIndex){
		
		int minIndex = beginIndex;
		
		for(int i = beginIndex + 1; i < array.length; i++){
			if(array[i] < array[minIndex]){
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	/**
	 * Swaps the values in the array
	 * @param array array that contains the value	
	 * @param i first index
	 * @param j second index
	 */
	public void swapValues(int[] array, int a, int b){
		if(a == b)
			return;
		array[a] = array[a] + array[b];
		array[b] = array[a] - array[b];
		array[a] = array[a] - array[b];		
	}
	
	//Tester
	public static void main(String[] args){
		StopWatch stopWatch = new StopWatch();		
		SelectionSortInt sort = new SelectionSortInt();
		int[] array = new int[10000];
		
		stopWatch.start();
		sort.sortAscending(array);
		stopWatch.stop();
	
		System.out.println("Time taken: " + stopWatch.getDeltaTime());
	}
}


