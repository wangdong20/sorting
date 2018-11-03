import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = new int[20];
		System.out.println("Bubble sort: ");
		generateRandom(array);
		printArray(array);
		bubbleSort(array);
		printArray(array);
		
		System.out.println("Selection sort: ");
		generateRandom(array);
		printArray(array);
		selectionSort(array);
		printArray(array);
		
		System.out.println("Insertion sort: ");
		generateRandom(array);
		printArray(array);
		insertionSort(array);
		printArray(array);
		
		System.out.println("Quick sort: ");
		generateRandom(array);
		printArray(array);
		quickSort(array, 0, array.length - 1);
		printArray(array);
		
		System.out.println("Merge sort: ");
		generateRandom(array);
		printArray(array);
		mergeSort(array, 0, array.length - 1);
		printArray(array);
		
		char arr[] = {'g', 'e', 'e', 'k', 's', 'f', 'o', 
                'r', 'g', 'e', 'e', 'k', 's'
                };
		
		System.out.println("Counting sort: ");
		printCharArray(arr);
		countingSort(arr);
		printCharArray(arr);
		
		double d[] = new double[]{0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
		
		System.out.println("Bucket sort: ");
//		generateRandom(d);
		printDoubleArray(d);
		bucketSort(d);
		printDoubleArray(d);
		
		generateRandom(array, 1000);
		System.out.println("Radix sort: ");
		printArray(array);
		radixSort(array);
	}
	
	public static void printArray(int[] array) {
		for(int a : array) {
			System.out.print(a + " ");
		}
		System.out.println();
	}
	
	private static void printCharArray(char[] array) {
		for(char c : array) {
			System.out.print(c + " ");
		}
		System.out.println();
	}
	
	private static void printDoubleArray(double[] array) {
		for(double d : array) {
			System.out.print(d + " ");
		}
		System.out.println();
	}
	
//	private static void generateRandom(double[] array) {
//		for(int i = 0; i < array.length; i++) {
//			array[i] = Math.random();
//		}
//	}
	
	private static void generateRandom(int[] array) {
		for(int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 100);
		}
	}
	
	private static void generateRandom(int[] array, int exp) {
		for(int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * exp);
		}
	}
	
	public static void bubbleSort(int[] array) {
		for(int i = array.length - 1; i > 1; i--) {
			boolean sorted = true;
			for(int j = 0; j < i; j++) {
				if(array[j] > array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					sorted = false;
				}
			}
			if(sorted) {
				break;
			}
		}
	}
	
	public static void selectionSort(int[] array) {
		int maxIndex = 0;
		for(int i = array.length; i > 1; i--) {
			for(int j = 0; j < i; j++) {
				if(array[j] > array[maxIndex]) {
					maxIndex = j;
				}
			}
			int temp = array[i - 1];
			array[i - 1] = array[maxIndex];
			array[maxIndex] = temp;
			maxIndex = 0;
		}
	}
	
	public static void insertionSort(int[] array) {
		int temp = 0;
		int j = 0;
		for(int i = 1; i < array.length; i++) {
			temp = array[i];
			for(j = i - 1; j >= 0 && temp < array[j]; j--) {
				array[j+1] = array[j];
			}
			array[j + 1] = temp;
		}
	}
	
	private static int partition(int[]array, int low, int high) {
		if(low >= high) {
			return low;
		} else {
			int pivot = array[high];
			int index = low;
			for(int i = low; i < high; i++) {
				if(array[i] < pivot) {
					int temp = array[index];
					array[index] = array[i];
					array[i] = temp;
					index++;
				}
			}
			array[high] = array[index];
			array[index] = pivot;
			return index;
		}
	}
	
	public static void quickSort(int[] array, int low, int high) {
		if(low < high) {
			int mid = partition(array, low, high);
			quickSort(array, low, mid - 1);
			quickSort(array, mid + 1, high);
		}
	}
	
	private static void merge(int[] array, int low, int mid, int high) {
		int output[] = new int[high - low + 1];
		int j = low;
		int k = mid + 1;
		for(int i = 0; i < output.length; i++) {
			if(j <= mid && k <= high && array[j] < array[k]) {
				output[i] = array[j];
				j++;
			} else if(j <= mid && k <= high && array[j] >= array[k]) {
				output[i] = array[k];
				k++;
			} else {
				if(j <= mid) {
					output[i] = array[j];
					j++;
				} else if(k <= high) {
					output[i] = array[k]; 
					k++;
				}
			}
		}
		
		for(int i = 0; i < output.length; i++) {
			array[low] = output[i];
			low++;
		}
	}
	
	public static void mergeSort(int[] array, int low, int high) {
		if(low < high) {
			int mid = (low + high) / 2;
			mergeSort(array, low, mid);
			mergeSort(array, mid + 1, high);
			merge(array, low, mid, high);
		}
	}
	
	public static void countingSort(char[] array) {
		int n = array.length;
		char[] output = new char[n];
		int count[] = new int[256];
		
		for(int i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		
		for(int i = 0; i < array.length; i++) {
			count[array[i]]++;
		}
		
		for(int i = 1; i < count.length; i++) {
			count[i] = count[i - 1] + count[i];
		}
		
		// This may work, but not good idea to build output
//		int index = 0;
//		for(int i = 1; i < count.length; i ++) {
//			if(count[i] > count[i - 1]) {
//				for(int j = 0; j < (count[i] - count[i-1]); j++) {
//					array[index] = (char) i;
//					index++;
//				}
//			}
//		}
		
		for(int i = 0; i < array.length; i++) {
			output[count[array[i]] - 1] = array[i];
			count[array[i]]--;
		}
		
		for(int i = 0; i < n; i++) {
			array[i] = output[i];
		}
		
	}
	
	public static void bucketSort(double[] array) {
		List<Double> buckets[] = new LinkedList[10];
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<Double>();
		}
		
		for(Double d : array) {
			buckets[(int)(d * 10)].add(d);
		}
		
		for(int i = 0; i < buckets.length; i++) {
			Collections.sort(buckets[i]);
		}
		
		int index = 0;
		for(int i = 0; i < buckets.length; i++) {
			for(int j = 0; j < buckets[i].size(); j++) {
				array[index] = buckets[i].get(j);
				index++;
			}
		}
	}
	
	public static void radixSort(int[] array) {
		int max = getMax(array);
		for(int exp = 1; max / exp > 0; exp *= 10) {
			countingSort(array, exp);
			printArray(array);
		}
	}
	
	private static int getMax(int[] array) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < array.length; i++) {
			if(max < array[i]) {
				max = array[i];
			}
		}
		return max;
	}
	
	public static void countingSort(int[] array, int exp) {
		int count[] = new int[10];
		int output[] = new int[array.length];
		int i;
		for(i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		for(i = 0; i < array.length; i++) {
			count[(array[i] / exp) % 10]++;
		}
		for(i = 1; i < count.length; i++) {
			count[i] = count[i] + count[i - 1];
		}
		
		// Build the output array 
		for(i = array.length - 1; i >= 0; i--) {
			output[count[(array[i] / exp) % 10] - 1] = array[i];
			count[(array[i] / exp) % 10]--;
		}
		
		// Copy the output array to arr[], so that arr[] now 
        // contains sorted numbers according to curent digit 
        for (i = 0; i < array.length; i++) 
            array[i] = output[i]; 
	}

}
