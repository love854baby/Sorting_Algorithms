import java.util.Random;

public class SortingAlgorithms {
	static Random rand = new Random(System.currentTimeMillis());

	public static void main(String args[]) {
		int length = 11;
		int[] arr = new int[length];

		for (int i = 0; i < length; i++)
			arr[i] = randInt(1, 20);

		System.out.println("Original Array:");
		printArray(arr);

		insertionSort(arr);
		System.out.println("Insertion Sort:");
		printArray(arr);

		restoreArray(arr);

		selectionSort(arr);
		System.out.println("Selection Sort:");
		printArray(arr);

		restoreArray(arr);

		mergeSort(arr);
		System.out.println("Merge Sort:");
		printArray(arr);

		restoreArray(arr);

		inPlaceMergeSort(arr, 0, arr.length - 1);
		System.out.println("In-place Merge Sort:");
		printArray(arr);
	}

	public static int randInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	public static void restoreArray(int[] arr) {
		arr = new int[] { 3, 6, 2, 4, 5, 1, 8, 7, 9, 8, 7 };
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1])
					swap(arr, j, j - 1);
				else
					break;
			}
		} 
	}

	public static void selectionSort(int[] arr) {
		int index;
		for (int i = 0; i < arr.length; i++) {
			index = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[index])
					index = j;
			}
			if (index != i)
				swap(arr, index, i);
		}
	}

	public static void mergeSort(int[] arr) {
		int length = arr.length;

		if (length <= 1)
			return;

		int len = length / 2;
		int[] tempArr1 = new int[len];
		int[] tempArr2 = new int[length - len];

		for (int i = 0; i < length; i++) {
			if (i < len)
				tempArr1[i] = arr[i];
			else
				tempArr2[i - len] = arr[i];
		}

		mergeSort(tempArr1);
		mergeSort(tempArr2);

		int left = 0, right = 0;
		for (int i = 0; i < length; i++) {
			if (left < len && right < length - len)
				arr[i] = tempArr1[left] < tempArr2[right] ? tempArr1[left++] : tempArr2[right++];
			else if (left >= len)
				arr[i] = tempArr2[right++];
			else
				arr[i] = tempArr1[left++];
		}
	}

	public static void inPlaceMergeSort(int[] arr, int start, int end) {
		if (start == end)
			return;

		int mid = (end + start) / 2;

		inPlaceMergeSort(arr, start, mid);
		inPlaceMergeSort(arr, mid + 1, end);

		int left = start, right = mid + 1, temp;
		while (left <= mid && right <= end) {
			if (arr[left] <= arr[right])
				left++;
			else {
				temp = arr[right];
				for (int i = right; i > left; i--)
					arr[i] = arr[i - 1];
				arr[left] = temp;
				left++;
				mid++;
				right++;
			}
		}
	}

	public static void heapSort(int[] arr) {
		heapify(arr);
		int length = arr.length;
		int[] sorted = new int[length];
		for (int i = 0; i < arr.length; i++) {
			sorted[i] = arr[(length - 1) / 2];
		}
	}

	public static void heapify(int[] arr) {
		for (int i = (arr.length - 1) / 2; i >= 0; i--) {
			minHeap(arr, i);
		}
	}

	public static void minHeap(int[] arr, int root) {
		int length = arr.length, left, right;
		for (int i = (length - 1) / 2; i >= root; i--) {
			left = 2 * i;
			right = left + 1;
			if (right < length && arr[right] < arr[i]) {
				swap(arr, i, right);
				minHeap(arr, right);
			}
			if (arr[left] < arr[i]) {
				swap(arr, i, left);
				minHeap(arr, left);
			}
		}
	}
}
