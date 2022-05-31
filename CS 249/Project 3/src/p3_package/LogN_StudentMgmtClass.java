package p3_package;

/**
 * Class for managing student data
 * 
 * @author Scotland Weitzel
 *
 */

public class LogN_StudentMgmtClass
{
   // constants for class
   public static final int STR_ONE_LESS_THAN_TWO = -1;
   public static final int STR_ONE_GREATER_THAN_TWO = 1;
   public static final int STR_ONE_TWO_EQUAL = 0;
   
   /**
    * Default Constructor
    * <p>
    * Nothing further will be done in this constructor
    */
   public LogN_StudentMgmtClass()
   {
      // empty default constructor
   }
   
   
   /**
    * Compare Strings
    * <p>
    * Compares two strings character by character set to lower case. The return 
    * cases are as follows:
    * <p>
    * String 1 > String 2 = return positive one
    * <p>
    * String 2 > String 1 = return negative one
    * <p>
    * Strings are alphabetically similar, but one is shorter, return difference 
    * in length
    * <p>
    * If both strings are equal alphabetically and in length, return 0
    * 
    * @param strOne - first String value to compare
    * @param strTwo - second String value to compare
    * 
    * @return integer difference specified above
    */
   public static int compareStrings( String strOne, String strTwo )
   {
   // 1. Initialize/Declare variables
      // index to iterate through strings
      int index = 0;
      // length of string one
      int lengthOne = strOne.length();
      // length of string two
      int lengthTwo = strTwo.length();
      // declare variables for characters of each string
      char loweredOne;
      char loweredTwo;
      
   // 2. Check for alphabetical Order
      while( index < lengthOne && index < lengthTwo )
      {
         // convert chars from both strings to lowercase
         loweredOne = toLowerCase( strOne.charAt( index ) );
         loweredTwo = toLowerCase( strTwo.charAt( index ) );
         // compare characters
         if( loweredOne > loweredTwo )
         {
            return STR_ONE_GREATER_THAN_TWO;
         }
         else if( loweredTwo > loweredOne )
         {
            return STR_ONE_LESS_THAN_TWO;
         }
      }
      
   // 3. Check for string length comparison
      if( lengthOne == lengthTwo )
      {
         return STR_ONE_TWO_EQUAL;
      }
      else
      {
         return lengthTwo - lengthOne;
      }
   }
   
   
   /**
    * Run Merge
    * <p>
    * Merges StudentClass objects brought in between a low and high index 
    * (inclusive) of an arrray. Note: uses locally sized single array for 
    * temporary storage
    * 
    * @param localArray - for temporary storage
    * @param lowIndex - integer for the index of array segment to be managed
    * @param middleIndex - for the index of array segment to be managed
    * @param highIndex - high index of array segment to be managed
    */
   private static void runMerge( StudentClass[] localArray, int lowIndex, 
                                int middleIndex, int highIndex )
   {
   // 1. Create left and right side arrays
      int leftCapacity = ( middleIndex - lowIndex) + 1;
      int rightCapacity = ( highIndex - middleIndex ); 
      StudentClass[] leftSide = new StudentClass[leftCapacity];
      StudentClass[] rightSide = new StudentClass[rightCapacity];
      
   // 2. Load data from localArray to storage arrays
      // initialize index
      int leftIndex = 0;
      int rightIndex = 0;
      // fill left side array
      while( leftIndex < leftCapacity )
      {
         leftSide[leftIndex] = localArray[lowIndex + leftIndex];
         leftIndex++;
      }
      // fill right side array
      while( rightIndex < rightCapacity )
      {
         rightSide[rightIndex] = localArray[middleIndex + rightIndex + 1];
         rightIndex++;
      }
      // reset indexes
      leftIndex = 0;
      rightIndex = 0;
      
   // 3. Compare and merge accordingly
      // initialize index for sorted array
      int sortedIndex = 0;
      // declare variable for comparison result
      int comparison;
      while( leftIndex < leftCapacity && rightIndex < rightCapacity )
      {
         // compare strings for both sides
         comparison = compareStrings(leftSide[leftIndex].name, 
                                     rightSide[rightIndex].name);
         // left side is less than right side
         if( comparison <= STR_ONE_LESS_THAN_TWO )
         {
            localArray[sortedIndex] = leftSide[leftIndex];
            leftIndex++;
         }
         // right side is less than left side
         else if( comparison >= STR_ONE_GREATER_THAN_TWO )
         {
            localArray[sortedIndex] = rightSide[rightIndex];
            rightIndex++;
         }
         // If they're equal, add both to sorted list
         else// if( comparison ==  STR_ONE_TWO_EQUAL )
         {
            localArray[sortedIndex] = leftSide[leftIndex];
            localArray[sortedIndex + 1] = rightSide[rightIndex];
            // increment sorted index one more time to account for double insert
            sortedIndex++;
         }
         // increment to the next spot in the sorted array
         sortedIndex++;
      }
      
   // 4. Copy any remaining elements from left side to sorted array
      while( leftIndex < leftCapacity )
      {
         localArray[sortedIndex] = leftSide[leftIndex];
         leftIndex++;
         sortedIndex++;
      }
      
   // 5. Copy any remaining elements from the right side to sorted array
      while( rightIndex < rightCapacity )
      {
         localArray[sortedIndex] = rightSide[rightIndex];
         rightIndex++;
         sortedIndex++;
      }
   }
   
   
   /**
    * Run Merge Sort
    * <p>
    * Sorts StudentClass data sorted using the merge sort algorithm with the
    * student names as the keys. Note: this method calls runMergeSortHelper that
    * recursively update the lower and upper indices of the array to be sorted
    * 
    * @param localArray - String array holding unsorted values
    * @param size - integer value holding number of values in array
    */
   public static void runMergeSort( StudentClass[] localArray, int size )
   {
   // 1. Initialize indexes to prime merge sorting
      int lowIndex = 0;
      int highIndex = size - 1;
      
   // 2. Run merge sort helper
      runMergeSortHelper(localArray, lowIndex, highIndex);
      
   }
   
   
   /**
    * Run Merge Sort Helper
    * <p>
    * Merge sort helper that recursively breaks given array segment down to 
    * smaller segments bewteen lowInde and highIndex (inclusive), then sorts 
    * data using merge sort method
    * 
    * @param localArray - StudentClass array holding unsorted values
    * @param lowIndex - lowest index of array segment to be managed, recursive
    * @param highIndex - highest index of array segment to be managed, recursive
    */
   private static void runMergeSortHelper( StudentClass[] localArray,
                                           int lowIndex, int highIndex )
   {
   // 1. Check if lowIndex is less than the upper index
      if(lowIndex < highIndex)
      {
         int midIndex = ( lowIndex + highIndex ) / 2;
         runMergeSortHelper(localArray, lowIndex, midIndex);
         runMergeSortHelper(localArray, midIndex + 1, highIndex);
         runMerge( localArray, lowIndex, midIndex, highIndex );
      }
   }
   
   
   /**
    * Run Partition
    * <p>
    * Partitions array using the first balue as the partition, and when this 
    * method is complete the partition value is in the correct location in the 
    * array
    * 
    * @param localArray - StudentClass array holding unsorted values
    * @param lowIndex - low index of array segment to be partitioned
    * @param highIndex - high index of array segment to be partitioned
    * 
    * @return index of partition pivot
    */
   private static int runPartition( StudentClass[] localArray, 
                                    int lowIndex, int highIndex )
   {
      int index = lowIndex;
      int pivotIndex = lowIndex;
      int comparison;
      
      while( index <= highIndex )
      {
         // compare value at index to value at pivot
         comparison = compareStrings( localArray[index].name, 
                                      localArray[pivotIndex].name );
         // index value is less than pivot value
         if( comparison  <= STR_ONE_LESS_THAN_TWO )
         {
            // swap pivot with value at index
            pivotIndex++;
            swapValues(localArray, pivotIndex, index);
         }
         index++;
      }
      
      // swap pivot with original pivot
      swapValues(localArray, lowIndex, pivotIndex);
      // return the pivot index
      return pivotIndex;
   }
   
   
   /**
    * Run Quick Sort
    * <p>
    * Data sorted using quick sort algorithm and the names as the keys
    * 
    * @param localArray - StudentClass array holding unsorted values
    * @param size - integer value holding the number of values in array
    */
   public static void runQuickSort( StudentClass[] localArray, int size )
   {
   // 1. Initialize indexes to prime quick sort helper
      int lowerIndex = 0;
      int higherIndex = size - 1;
      
   // 2. Start quick sort recursion
      runQuickSortHelper( localArray, lowerIndex, higherIndex );
      
   }
   
   
   /**
    * Run Quick Sort Helper
    * <p>
    * Helper method run with parameters that supports recursive access
    * 
    * @param localArray - StudentClass array holding unsorted values
    * @param lowIndex - low index of the segment of the array to be managed
    * @param highIndex - high index of the segment to be managed
    */
   private static void runQuickSortHelper( StudentClass[] localArray,
                                           int lowIndex, int highIndex )
   {
      // create pivot
      int pivot;
      // check if lower bound isn't the high bound
      if( lowIndex < highIndex )
      {
         // call partition to get pivot value
         pivot = runPartition( localArray, lowIndex, highIndex );
         runQuickSortHelper( localArray, lowIndex, pivot );
         runQuickSortHelper( localArray, pivot + 1, highIndex);
      }
   }
   
   
   /**
    * Swap Values
    * <p>
    * Swaps values within the given array
    * 
    * @param localArray - array of StudentClass data for swapping
    * @param indexOne - integer index for one of the two items to be swapped
    * @param indexOther - other index of the two items to be swapped
    */
   private static void swapValues(StudentClass[] localArray, 
                                  int indexOne, int indexOther)
   {
   // 1. Initialize temporary storage for one element
      StudentClass holdValue = localArray[indexOne];
      
   // 2. Swap the elements using the temp storage variable
      localArray[indexOne] = localArray[indexOther];
      localArray[indexOther] = holdValue;
   }
   
   
   /**
    * To Lower Case
    * <p>
    * Swaps the upper case character passed into the method with its lower case
    * counterpart
    * 
    * @param testChar - character value to be tested and possibly modified
    * 
    * @return value modified as specified
    */
   private static char toLowerCase( char testChar )
   {
      // set a variable to the lower case of the test char
      char loweredVar = Character.toLowerCase(testChar);
      if(testChar != loweredVar)
      {
         return loweredVar;
      }
      
      return testChar;
   }
   
}
