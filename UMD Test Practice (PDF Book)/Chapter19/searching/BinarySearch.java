package searching;

/**
 * It assumes that list is in order.
 * It divides the array in half and keeps dividing until it finds the right number. 
 * If the bounds are equal to each other and the number is not equal, then its not
 * in the list. 
 * 
 * Has a runtime of O(log(n))
 * @author eashaan
 *
 */
public class BinarySearch {

	/**
	 * Performs Binary Search on a list
	 * @param num 
	 * @param from
	 * @param to
	 * @return index at which the num is found, or -1 one it not found
	 */
	public int search(int[] array, int num, int from, int to){
		if(to >= array.length)
			return -1;
		if(from == to){
			if(array[from] == num) return from;
			else return -1;
		}
		
		int halfIndex = (to + from)/2;
		int answer = array[halfIndex];
		
		if(num == answer){
			return halfIndex;
		}
		else if(num > answer){
			//we add 1 to halfIndex because it gets stuck between the last
			//two indices of the array because the division by 2 of halfIndex
			//always rounds down
			return search(array, num, halfIndex + 1 , to);
		}
		else if(num < answer){

			return search(array, num, from, halfIndex);
		}
		
		else 
			return -1;
	}
	
	public static void main(String[] args){
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		BinarySearch search = new BinarySearch();
		
		System.out.println(search.search(array, 12, 0, array.length - 1));
	}
}
