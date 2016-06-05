package sorting;

import timer.StopWatch;

/**
 * It takes an array and divides it into two. It sorts each half individually and combines the two halves.
 * Time: O(nlog(n))
 * @author eashaan
 *
 */
public class MergeSort {

	public void sort(int[] array){
		if(array.length <= 1)
			return;
		
		int[] first = new int[array.length/2];
		int[] second = new int[array.length - first.length];
		
		System.arraycopy(array, 0, first, 0, first.length);
		System.arraycopy(array, first.length, second, 0, second.length);
		
		sort(first);
		sort(second);
		
		merge(array, first, second);
	}
	
	private void merge(int[] array, int[] first, int[] second){
		int iFirst = 0, iSecond = 0;
		
		int j = 0;
		while(iFirst < first.length && iSecond < second.length){
			if(first[iFirst] < second[iSecond]){
				array[j] = first[iFirst];
				iFirst++;
			}
			else{
				array[j] = second[iSecond];
				iSecond++;
			}
			j++;
		}
		
		System.arraycopy(first, iFirst, array, j, first.length - iFirst);
		System.arraycopy(second, iSecond, array, j, second.length - iSecond);

	}
	
	public static void main(String[] args){
		StopWatch watch = new StopWatch();
		MergeSort sort = new MergeSort();
		int[] array = new int[10000000];
		
		watch.start();
		sort.sort(array);
		watch.stop();
		
		/*for(int e : array){
			System.out.print(e + ", ");
		}*/
		
		System.out.println(". Time taken: " + watch.getDeltaTime());
	}
}
