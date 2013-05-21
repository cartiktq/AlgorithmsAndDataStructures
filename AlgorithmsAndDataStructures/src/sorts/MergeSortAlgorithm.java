package sorts;

public class MergeSortAlgorithm {

	public int[] mergeSort(int[] array){
		int inputArrayLength = array.length;
		if (inputArrayLength == 1) return array;

		int i = 0, j = 0;
				
		int leftArrayLength = (int)(Math.ceil(inputArrayLength/2)); 
		int rightArrayLength = inputArrayLength - leftArrayLength;
		
		int[] leftArray = new int[leftArrayLength];
		int[] rightArray = new int[rightArrayLength];
		
		for(i = 0; i < leftArrayLength; i++){
			leftArray[i] = array[i];
		}
		
		for(i = leftArrayLength; i < inputArrayLength; i++){
			rightArray[j++] = array[i];
		}
		
		int[] sortedLeftArray = mergeSort(leftArray);
		int[] sortedRightArray = mergeSort(rightArray);
		
		int[] sortedInts = mergeArrays(sortedLeftArray, sortedRightArray);
		
		return sortedInts;
	}

	private int[] mergeArrays(int[] leftArray, int[] rightArray) {
		
		int outputArrayLength = leftArray.length + rightArray.length; 
		int[] outputArray = new int[outputArrayLength];
		int i = 0, j = 0, k = 0;
		
		while(i < leftArray.length && j < rightArray.length){
			if(leftArray[i] <= rightArray[j]){
				outputArray[k++] = leftArray[i++];
			} else {
				outputArray[k++] = rightArray[j++];
			}
		}

		if(i >= leftArray.length){
			while(k < outputArrayLength){
				outputArray[k++] = rightArray[j++];
			}
		} else if(j >= rightArray.length){
			while(k < outputArrayLength){
				outputArray[k++] = leftArray[i++];
			}
		}
		return outputArray;
	}
	
	private void printArray(int[] output){
		int i = 0;
		while(i < output.length - 1){
			System.out.print(output[i++] + ", ");
		}
		System.out.println(output[i]);
	}
	
	public static void main(String[] args){
		int[] inputArray = {1024, 512, 768, 128, 128, 384, 256, 640, 1024, 896};
		
		MergeSortAlgorithm msa = new MergeSortAlgorithm();
		System.out.print("Input Array: ");
		msa.printArray(inputArray);
		int[] output = msa.mergeSort(inputArray);
		System.out.print("Output Array: ");
		msa.printArray(output);
	}
}
