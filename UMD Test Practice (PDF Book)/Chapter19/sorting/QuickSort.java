package sorting;

import timer.StopWatch;

/**
 * Similar to merge sort, it divides the array into two pieces,
 * sorts them individually through recursion, and merges them back together.
 * On average, it has a O(nlog(n)) but can be faster than MergeSort. 
 * One bad thing though: worst case scenario, its runtime can be O(n^2)
 * 
 * To find the pivot where the array is split, move two pointers from
 * left and right until you find the "pivot", just like you would
 * use two fingers to find the center of mass of a stick. 
 * @author eashaan
 *
 */
public class QuickSort {

	public void sort(int[] array, int from, int to){
		if(from >= to) return;
		int p = partition(array, from, to);
		sort(array, from, p);
		sort(array, p + 1, to);
	}
	
	private int partition(int[] array, int from, int to){
		int pivot = array[from];
		int i = from - 1;
		int j = to + 1;
		while(i < j){
			i++;
			while(array[i] < pivot)i++;
			j--;
			while(array[j] > pivot)j--;
			if(i < j)
				swap(array, i, j);
		}
		
		return j;
	}
	
	private void swap(int[] array, int a, int b){
		if(a == b)
			return;
		array[a] = array[a] + array[b];
		array[b] = array[a] - array[b];
		array[a] = array[a] - array[b];		
	}
	
	public static void main(String[] args){
		StopWatch stopWatch = new StopWatch();		
		QuickSort sort = new QuickSort();
		int[] array = {24, 12, 5, 1, 8, 23, 62, 15, 63};
		
		stopWatch.start();
		sort.sort(array, 0, array.length - 1);
		stopWatch.stop();
	
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + ", ");
		}
		
		System.out.println("Time taken: " + stopWatch.getDeltaTime());
	}
}
