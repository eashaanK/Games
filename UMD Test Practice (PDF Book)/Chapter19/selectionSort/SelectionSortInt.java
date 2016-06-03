package selectionSort;

public class SelectionSortInt {

	/**
	 * Sorts the given array in ascending order
	 * @param arrayToSort array that will be sorted
	 */
	public void sortAscending(int[] arrayToSort){
		//Marker Index represents the position of which all elements to the left are checked.
		for(int markerIndex = 0; markerIndex < arrayToSort.length; markerIndex++){
			
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
	
	//Tester
	public static void main(String[] args){
		SelectionSortInt sort = new SelectionSortInt();
		int[] array = {3, 5, 12, 9, 24, 12, 8};
		System.out.println("Smallest element: " + array[sort.findSmallestElementIndex(array, 0)]);
	}
}


