/**
 * Sorting demonstrates sorting and searching on an array
 * of objects.
 *
 * @author Neyo
 *
 */

public class Sorting
{
    
    public static <T extends Comparable<T>>
    void shellSort(T[] data)
    {
        final long startTime = System.nanoTime(); //record the time at the start of the program
        int comparisonCounter = 0; int swapCounter = 0; //counters for the comparisons and swaps
        int gap = data.length/2; //initialize gap to half the array size
        
        System.out.println(" |  - - - SHELL SORT - - -  |"); //prints out a header for the beginning of the algorithm
        System.out.print("array:  "); printArray(data);
        
        do
        {
            System.out.println("\n-----------"); //displays the current gap state which is the condition for the do-while loop if gap<0 stop
            System.out.println("| gap = " + gap + " |");
            System.out.println("-----------\n");
            
            boolean swapflag = true; //initialize the flag to true or false doesn't matter, since do-while control will still enter once
            do
            {
                
                swapflag = false; //set swapflag to false, this is important as you have to set it false, check for swap then set to true. If it stays false then will not continue
                
                for(int s = 0; s < data.length - gap ; s++) //iterate for loop such that it ends at the array size - 1 - gap
                {
                    System.out.println("for loop pass: " + s);
                    comparisonCounter++; //increase counter for each comparison done in for loop
                    if (data[s].compareTo(data[s + gap]) > 0)
                    {
                        System.out.print("Before swap: "); printArray(data,s,s+gap); //prints out the array before and after the swap using overloaded print method to easily view swaps
                        System.out.println("data[s]: " + data[s] + "> data[s+gap]: " +data[s+gap]);
                        swap(data, s , s+gap);
                        System.out.print("After swap : "); printArray(data,s,s+gap); System.out.println();
                        swapflag = true; //set the swapflag to true since a swap occurred
                        swapCounter++; //for same reason increase the swap counter
                    }
                    else
                    {
                        System.out.println("data[s]: " + data[s] + "  <  data[s+gap]: " + data[s + gap] + " ~ ~ NO SWAP ~ ~"); //since the data was less we print out a message saying no swaps happened
                        printArray(data,s,s+gap); System.out.println();
                    }
                }
                
                
            }while(swapflag == true); //when there's no swaps or swap flag is false exit the loop and array is sorted for current gap spacing, if gap is 0 then fully sorted
            
            
            gap = gap/2;
            System.out.println("no swaps occured for an entire pass through, gap now set to gap/2 which is, " + gap +"." + "\n");
            if(gap==0)
            {
                System.out.println("The gap is now 0, the while loop condition (gap>0) will no longer be true and control will exit the loop.");
            }
            
        }while(gap > 0);
        
        final long endTime = System.nanoTime(); //takes the time at the end of the execution of the algorithm
        System.out.println("\nThe array has now been sorted: ");
        printArray(data);
        System.out.println("Comparisons: " + comparisonCounter + ", Swaps: " + swapCounter + ", Total run time: " + (endTime-startTime)/1000.0 + " microseconds\n"); //prints out the comparisons, swaps and run time
        
    }
    
    
    /**
     * Sorts the specified array of objects using a bubble sort
     * algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void bubbleSort2(T[] data)
    {
        long startTime = System.nanoTime(); //takes the time at the beginning of execution
        int comparisonCounter = 0; int  swapCounter = 0; //initializes the comparison and swap counters
        
        int end = data.length-1; //intiliazes the size of the array, ex: array of 10 this is 9
        boolean swapflag = true; //swagflag so we can optimize
        
        System.out.println(" |  - - - BUBBLE SORT 2 - - -  |"); //header for the algorithm
        System.out.print("array:  "); printArray(data);System.out.println();
        
        
        while(end >=0 && swapflag==true) //ex: array of 10 end goes index 9 to 0
        {
            
            swapflag = false;
            for (int scan = 0 ; scan < end ; scan++) // has to go to one less than array size - 1 (or effectively to array size -2) since it compares the next index if it went to scan <= end then scan+1 will be out of array bounds.. ex: array of 10 scan goes 0 to 8, 0 to 7, 0 to 6, 0 to 5, ... 0 to 1
            {
                comparisonCounter++;
                if (data[scan].compareTo(data[scan + 1]) > 0) //compare adjecent entries in the list
                {
                    System.out.print("Before swap: "); printArray(data,scan,scan+1); //prints the array before and after swaps for easy viewing
                    swap(data, scan, scan + 1);
                    System.out.print("After swap : "); printArray(data,scan,scan+1); System.out.println();
                    swapCounter++; //increase swap since swap occurred
                    swapflag = true; //set swap to true since swap occurred
                }
                else
                {
                    System.out.println("  ~ ~ ~ NO SWAP ~ ~ ~"); //since the data was less we print out a message saying no swaps happened
                    printArray(data,scan,scan+1); System.out.println();
                }
            }
            
            end--; // runs through the array and each time decreases the number of entries needed to look through by 1. This is because bubble sort will take the highest value to the end of the array and that value is at its final location at that point
        }
        
        long endTime = System.nanoTime();// get the time at the end of execution
        
        System.out.println("Comparisons: " + comparisonCounter + ", Swaps: " + swapCounter + ", Total run time: " + (endTime-startTime)/1000.0 + " microseconds\n");//prints out the comparisons, swaps and run time
        
    }
    
    
    /**
     * Sorts the specified array of objects using a bubble sort
     * algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void bubbleSort(T[] data)
    {
        long startTime = System.nanoTime(); //takes the time at the beginning of execution
        int position, scan, swapCounter = 0, comparisonCounter = 0; //iterating variables and counter variables
        
        System.out.println(" |  - - - BUBBLE SORT - - -  |"); //header for bubble sort
        System.out.print("array:  "); printArray(data); System.out.println();
        
        for (position =  data.length - 1; position >= 0; position--) //iterates through the list each time ignoring the last index from before hand
        {
            for (scan = 0; scan < position ; scan++) // goes up to the last position - 1 (scan<position not <=) so the second last index each time
            {
                comparisonCounter++;
                if (data[scan].compareTo(data[scan + 1]) > 0) //compares adjacent indexes
                {
                    System.out.print("Before swap: "); printArray(data,scan,scan+1); //prints the array before and after swaps for easy viewing
                    swap(data, scan, scan + 1);
                    System.out.print("After swap : "); printArray(data,scan,scan+1); System.out.println();
                    swapCounter++;
                }
                else
                {
                    System.out.println("  ~ ~ ~ NO SWAP ~ ~ ~"); //since the data was less we print out a message saying no swaps happened
                    printArray(data,scan,scan+1); System.out.println();
                }
            }
        }
        
        long endTime = System.nanoTime(); //get the time at the end of execution
        System.out.println("Comparisons: " + comparisonCounter + ", Swaps: " + swapCounter + ", Total run time: " + (endTime-startTime)/1000.0 + " microseconds\n");//prints out the comparisons, swaps and run time
        
    }
    
    /**Copy of shellsort with commented out print statements
     * @param type  prints out sorted or unsorted
     * @param data
     * @param <T>
     */
    public static <T extends Comparable<T>>
    void shellSortRuntime(T[] data, String type)
    {
        long startTime = System.nanoTime();
        int comparisonCounter = 0; int swapCounter = 0;
        int gap = data.length/2;
        
        System.out.println("  - - - SHELL SORT - - -  " + data.length + type);
        //System.out.print("array:  "); printArray(data);
        
        
        do
        {
            //System.out.println("\n-----------");
            //System.out.println("| gap = " + gap + " |");
            //System.out.println("-----------\n");
            
            boolean swapflag = true;
            do
            {
                
                swapflag = false;
                
                for(int s = 0; s < data.length - gap ; s++) //iterate for loop such that it ends at the final s+gap
                {
                    //System.out.println("for loop pass: " + s);
                    comparisonCounter++;
                    if (data[s].compareTo(data[s + gap]) > 0)
                    {
                        //System.out.print("Before swap: "); printArray(data,s,s+gap);
                        //System.out.println("data[s]: " + data[s] + "> data[s+gap]: " +data[s+gap]);
                        swap(data, s , s+gap);
                        //System.out.print("After swap : "); printArray(data,s,s+gap); System.out.println();
                        swapflag = true;
                        swapCounter++;
                    }
                    //else
                    {
                        //System.out.println("data[s]: " + data[s] + ", data[s+gap]: " + data[s + gap] + " ~ NO SWAP\n");
                    }
                }
                
                
            }while(swapflag == true);
            
            
            gap = gap/2;
            //System.out.println("no swaps occured for an entire pass through, gap now set to gap/2 which is, " + gap +".");
            if(gap==0)
            {
                //System.out.println("The gap is now 0, the while loop condition (gap>0) will no longer be true and control will exit the loop.");
            }
            
        }while(gap > 0);
        
        long endTime = System.nanoTime();
        //System.out.println("The array has now been sorted: ");
        System.out.println("Comparisons: " + comparisonCounter + ", Swaps: " + swapCounter + ", Total run time: " + (endTime-startTime)/1000.0 + " microseconds\n");
        
    }
    
    
    /**
     * Copy of bubblesort2 with commented out print statements
     *
     * @param type  prints out sorted or unsorted
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void bubbleSort2Runtime(T[] data, String type)
    {
        long startTime = System.nanoTime();
        int comparisonCounter = 0; int  swapCounter = 0;
        
        int end = data.length-1; //array of 10 this is 9
        boolean swapflag = true;
        
        System.out.println("  - - - BUBBLE SORT 2 - - -  "+ data.length + type);
        
        
        while(end >=0 && swapflag==true) //ex: array of 10 end goes index 9 to 0
        {
            
            swapflag = false;
            for (int scan = 0 ; scan < end ; scan++) //ex: array of 10 scan goes 0 to 8, 0 to 7, 0 to 6, 0 to 5, ... 0 to 1
            {
                comparisonCounter++;
                if (data[scan].compareTo(data[scan + 1]) > 0)
                {
                    //System.out.print("Before swap: "); printArray(data,scan,scan+1);
                    //System.out.println("[i]: " + data[scan] + "  >  [i+1]: " +data[scan+1]);
                    swap(data, scan, scan + 1);
                    //System.out.print("After swap : "); printArray(data,scan,scan+1); System.out.println();
                    swapCounter++;
                    swapflag = true;
                }
            }
            
            end--;
        }
        
        long endTime = System.nanoTime();
        
        System.out.println("Comparisons: " + comparisonCounter + ", Swaps: " + swapCounter + ", Total run time: " + (endTime-startTime)/1000.0 + " microseconds\n");
    }
    
    
    /**
     * Copy of bubblesort with commented out print statements
     *
     * @param type  prints out sorted or unsorted
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void bubbleSortRuntime(T[] data, String type)
    {
        long startTime = System.nanoTime();
        int position, scan, swapCounter = 0, comparisonCounter = 0;
        
        System.out.println("  - - - BUBBLE SORT - - -  "+ data.length + type);
        
        
        for (position =  data.length - 1; position >= 0; position--)
        {
            for (scan = 0; scan < position ; scan++)
            {
                comparisonCounter++;
                //System.out.println(scan);
                if (data[scan].compareTo(data[scan + 1]) > 0)
                {
                    swap(data, scan, scan + 1);
                    //printArray(data);
                    swapCounter++;
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Comparisons: " + comparisonCounter + ", Swaps: " + swapCounter + ", Total run time: " + (endTime-startTime)/1000.0 + " microseconds\n");
    }
    
    
    /**Prints out the array for the inputed array
     *
     * @param data
     * @param <T>
     */
    public static <T extends Comparable<T>>
    void printArray(T[] data)
    {
        System.out.print("{ ");
        for(int i = 0; i < data.length ; i++)
        {
            if(i < data.length - 1)
                System.out.print( data[i] + ", ");
            else
                System.out.println(data[i] + " }");
        }
    }
    
    
    /**Overloaded method that is used to show the indexes that are being targeted in the array during swaps
     *
     * @param data array going in
     * @param index1 swap index1
     * @param index2 swap index2
     * @param <T>
     */
    public static <T extends Comparable<T>>
    void printArray(T[] data, int index1, int index2)
    {
        System.out.print("{ ");
        for(int i = 0; i < data.length ; i++)
        {
            if(i < data.length - 1 && !(i==index1 || i == index2))
                System.out.print( data[i] + ", ");
            
            else if((i == index1 || i == index2) && !(i==data.length-1))
                System.out.print("|" + data[i] + "|" + ", ");
            
            else if(i == index2 && i == data.length-1)
                System.out.println("|" + data[i] + "|" + " }");
            
            else
                System.out.println(data[i] + " }");
            
        }
    }
    
    
    /**
     * Swaps two elements in an array. Used by various sorting algorithms.
     *
     * @param data   the array in which the elements are swapped
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    private static <T extends Comparable<T>>
    void swap(T[] data, int index1, int index2)
    {
        T temp1 = data[index1];
        T temp2 = data[index2];
        data[index1] = temp2;
        data[index2] = temp1;
    }
    
    
    
    /**Returns a random array of Integers of size n ranging from 1 to n
     *
     * @param size of the array and range of Integers in the array
     * @return Integer array randomArray
     */
    public static Integer[] generateRandomArray(int size)
    {
        Integer[] randomArray = new Integer[(int) size];
        
        for(int i = 0 ; i < size ; i ++)
        {
            double random = Math.random();
            int randomNumber = (int) (size*random) + 1;
            randomArray[i] = randomNumber;
        }
        
        printArray(randomArray);
        return randomArray;
    }
    
    
    /**
     * Sorts the specified array of integers using the selection
     * sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void selectionSort(T[] data)
    {
        int min;
        T temp;
        
        for (int index = 0; index < data.length - 1; index++)
        {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++)
                if (data[scan].compareTo(data[min]) < 0)
                    min = scan;
            
            swap(data, min, index);
        }
    }
    
    
    /**
     * Sorts the specified array of objects using an insertion
     * sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void insertionSort(T[] data)
    {
        for (int index = 1; index < data.length; index++)
        {
            T key = data[index];
            int position = index;
            
            // shift larger values to the right
            while (position > 0 && data[position-1].compareTo(key) > 0)
            {
                data[position] = data[position - 1];
                position--;
            }
            
            data[position] = key;
        }
    }
    
    
    /**
     * Sorts the specified array of objects using the quick sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void quickSort(T[] data)
    {
        quickSort(data, 0, data.length - 1);
    }
    
    
    /**
     * Recursively sorts a range of objects in the specified array using the
     * quick sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the minimum index in the range to be sorted
     * @param max  the maximum index in the range to be sorted
     */
    private static <T extends Comparable<T>>
    void quickSort(T[] data, int min, int max)
    {
        if (min < max)
        {
            // create partitions
            int indexofpartition = partition(data, min, max);
            
            // sort the left partition (lower values)
            quickSort(data, min, indexofpartition - 1);
            
            // sort the right partition (higher values)
            quickSort(data, indexofpartition + 1, max);
        }
    }
    
    
    /**
     * Used by the quick sort algorithm to find the partition.
     *
     * @param data the array to be sorted
     * @param min  the minimum index in the range to be sorted
     * @param max  the maximum index in the range to be sorted
     */
    private static <T extends Comparable<T>>
    int partition(T[] data, int min, int max)
    {
        T partitionelement;
        int left, right;
        int middle = (min + max) / 2;
        
        // use the middle data value as the partition element
        partitionelement = data[middle];
        
        // move it out of the way for now
        swap(data, middle, min);
        
        left = min;
        right = max;
        
        while (left < right)
        {
            // search for an element that is > the partition element
            while (left < right && data[left].compareTo(partitionelement) <= 0)
                left++;
            
            // search for an element that is < the partition element
            while (data[right].compareTo(partitionelement) > 0)
                right--;
            
            // swap the elements
            if (left < right)
                swap(data, left, right);
        }
        
        // move the partition element into place
        swap(data, min, right);
        
        return right;
    }
    
    
    /**
     * Sorts the specified array of objects using the merge sort
     * algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void mergeSort(T[] data)
    {
        mergeSort(data, 0, data.length - 1);
    }
    
    
    /**
     * Recursively sorts a range of objects in the specified array using the
     * merge sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the index of the first element
     * @param max  the index of the last element
     */
    private static <T extends Comparable<T>>
    void mergeSort(T[] data, int min, int max)
    {
        if (min < max)
        {
            int mid = (min + max) / 2;
            mergeSort(data, min, mid);
            mergeSort(data, mid + 1, max);
            merge(data, min, mid, max);
        }
    }
    
    
    /**
     * Merges two sorted subarrays of the specified array.
     *
     * @param data the array to be sorted
     * @param first the beginning index of the first subarray
     * @param mid the ending index fo the first subarray
     * @param last the ending index of the second subarray
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>>
    void merge(T[] data, int first, int mid, int last)
    {
        T[] temp = (T[])(new Comparable[data.length]);
        
        int first1 = first, last1 = mid;  // endpoints of first subarray
        int first2 = mid + 1, last2 = last;  // endpoints of second subarray
        int index = first1;  // next index open in temp array
        
        //  Copy smaller item from each subarray into temp until one
        //  of the subarrays is exhausted
        while (first1 <= last1 && first2 <= last2)
        {
            if (data[first1].compareTo(data[first2]) < 0)
            {
                temp[index] = data[first1];
                first1++;
            }
            else
            {
                temp[index] = data[first2];
                first2++;
            }
            index++;
        }
        
        //  Copy remaining elements from first subarray, if any
        while (first1 <= last1)
        {
            temp[index] = data[first1];
            first1++;
            index++;
        }
        
        //  Copy remaining elements from second subarray, if any
        while (first2 <= last2)
        {
            temp[index] = data[first2];
            first2++;
            index++;
        }
        
        //  Copy merged data into original array
        for (index = first; index <= last; index++)
            data[index] = temp[index];
    }
    
    
}



