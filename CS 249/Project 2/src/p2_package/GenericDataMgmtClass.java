package p2_package;

/**
 * Class wrapper for a Java array of generic data,
 * with additional management operations
 * 
 * @author Scotland Weitzel
 *
 */

public class GenericDataMgmtClass<GenericData extends Comparable<GenericData>>
{
   // constants
   public static final int DEFAULT_CAPACITY = 10;
   
   // member data
   private int arrayCapacity;
   private int arraySize;
   private Object[] localArray;
   
   /**
    * Default Constructor
    * <p>
    * Initializes array to default capacity
    */
   public GenericDataMgmtClass()
   {
      arraySize = 0;
      arrayCapacity = DEFAULT_CAPACITY;
      localArray = new Object[arrayCapacity];
   }
   
   /**
    * Initialization Constructor
    * <p>
    * Initializes array to specified capacity
    * 
    * @param capacity integer to specify array capacity
    */
   public GenericDataMgmtClass( int capacity )
   {
      arraySize = 0;
      arrayCapacity = capacity;
      localArray = new Object[arrayCapacity];
   }
   
   /**
    * Access at index
    * <p>
    * This method accesses the element in an array
    * and returns it as GenericData
    * 
    * @param accessIndex integer to specify 
    * 
    * @return GenericData item found at specified index
    */
   @SuppressWarnings( "unchecked" )
   public GenericData accessItemAt( int accessIndex )
   {
      return (GenericData)localArray[accessIndex];
   }
   
   /**
    * Append item
    * <p>
    * Appends an item to the end of an array if it is not full
    * 
    * @param newValue GenericData value to specify what is being added
    * 
    * @return boolean expression indicating if appending was successful
    */
   public boolean appendItem( GenericData newValue )
   {
      // item is added when the array is not full, size increased
      if( !( this.isFull() ) )
      {
         localArray[arraySize] = newValue;
         arraySize++;
         return true;
      }
      
      // a full array results in false being returned
      return false;
   }
   
   /**
    * Clear
    * <p>
    * Clears array of all valid values by setting size to zero
    */
   public void clear()
   {
      arraySize = 0;
   }
   
   /**
    * Get current capacity
    * <p>
    * Gets current capacity of the array
    * 
    * @return array capacity
    */
   public int getCurrentCapacity()
   {
      return arrayCapacity;
   }
   
   /**
    * Get current size
    * <p>
    * Gets current size of the array
    * 
    * @return array size
    */
   public int getCurrentSize()
   {
      return arraySize;
   }
   
   /**
    * Insert data
    * <p>
    * Inserts item to array at specified index if array isn't full
    * 
    * @param insertIndex integer value representing specified index
    * @param newValue GenericData value representing new data
    * 
    * @return boolean value indicating if data insertion was successful
    */
   public boolean insertData( int insertIndex, GenericData newValue )
   {
      // declare index to update array
      int index;
      
      // data is inserted if the array is not full, size is increased
      if( !( this.isFull() ) )
      {
         arraySize++;
         index = arraySize - 1;
         while( index > insertIndex )
         {
            localArray[index] = localArray[index - 1];
            index--;
         }
         localArray[insertIndex] = newValue;
         return true;
      }
      
      // a full array results in false being returned
      return false;
      
   }
   
   /**
    * Is empty
    * <p>
    * Tests if array is empty by comparing array size to zero
    * 
    * @return boolean value indicating result of test
    */
   public boolean isEmpty()
   {
      return ( arraySize == 0 );
   }
   
   /**
    * Is full
    * <p>
    * Tests if array is full by comparing size to capacity
    * 
    * @return boolean value indicating result of test
    */
   public boolean isFull()
   {
      return ( arraySize == arrayCapacity );
   }
   
   /**
    * Remove data
    * <p>
    * Removes item from the array at a specified index
    * 
    * @param removeIndex integer to be specified index
    * 
    * @return removed value if successful, null if not
    */
   @SuppressWarnings( "unchecked" )
   public GenericData removeData( int removeIndex )
   {
      // initialization of variables
      GenericData removedValue = null;
      int index = removeIndex;
      
      // test to see if removeIndex is valid
      if( removeIndex > arraySize - 1)
      {
         return removedValue;
      }
      
      // save value to be removed
      removedValue = (GenericData)localArray[index];
      
      // move array elements down
      while( index < arraySize - 1)
      {
         localArray[index] = localArray[index + 1];
         index++;
      }
      
      // update size of array and return removed element
      arraySize--;
      return removedValue;
   }
   
   /**
    * Resize
    * <p>
    * Resets the array capacity, copies current size and 
    * elements to a new array with expanded, specified capacity
    * 
    * @param newCapacity integer to specify new capacity of the array
    * 
    * @return boolean expression indicating if resize was committed
    */
   public boolean resize( int newCapacity )
   {
      // resize if array is full
      if( this.isFull() && newCapacity > arrayCapacity)
      {
         int index = 0;
         arrayCapacity = newCapacity;
         Object[] newArray = new Object[arrayCapacity];
         
         while( index < arraySize )
         {
            newArray[index] = localArray[index];
            index++;
         }
         localArray = newArray;
         return true;
      }
      // resize is not committed when array is not full or 
      // if the new capacity is smaller than the old capacity
      else
      {
         return false;
      }
   }
   
   /**
    * Bubble Sort
    * <p>
    * Sorts elements in the array by using the bubble sort algorithm
    */
   @SuppressWarnings("unchecked")
   public void runBubbleSort()
   {
      // initialize variables
      int index = 0;
      GenericData firstItem, secondItem;
      boolean swapped = false;
      
      // loop through array to sort
      while( index < arraySize - 1 )
      {
         firstItem = (GenericData)localArray[index];
         secondItem = (GenericData)localArray[index + 1];
         
         // If second element is larger, swap first and second elements
         if(firstItem.compareTo(secondItem) > 0)
         {
            swapElements(index, index + 1);
            swapped = true;
         }
         index++;
         
         if( index == arraySize - 1 && swapped)
         {
            index = 0;
            swapped = false;
         }
      }
   }
   
   /**
    * Insertion Sort
    * <p>
    * Sorts elements in the array by using the insertion sort algorithm
    */
   @SuppressWarnings("unchecked")
   public void runInsertionSort()
   {
      // Initialize variables
      int keyIndex = 1;
      int index = 0;
      GenericData key, strippedNumber, currentValue;
      
      // start insertion sort loop
      while( keyIndex < arraySize )
      {
         key = (GenericData)localArray[keyIndex];
         index = keyIndex - 1;
         currentValue = (GenericData)localArray[index];
         
         while( index >= 0 && key.compareTo(currentValue) > 0)
         {
            strippedNumber = removeData( index );
            insertData(keyIndex + 1, strippedNumber);
            index--;
         }
         
         keyIndex++;
      }
      
   }
   
   /**
    * Selection Sort
    * <p>
    * Sorts elements in the array by using the selection sort algorithm
    */
   @SuppressWarnings("unchecked")
   public void runSelectionSort()
   {
      int minValIndex = 0;
      int sortedIndex = 0;
      int unsortedIndex = 0;
      int atBeginning = 0;
      GenericData minValue;
      GenericData currentVal;
      
      // increase boundary of sorted section of array
      while( sortedIndex < arraySize - 1 )
      {
         // loop through the array to discover the smallest element
         minValIndex = sortedIndex;
         minValue = (GenericData)localArray[sortedIndex];
         unsortedIndex = sortedIndex + 1;
         while( unsortedIndex < arraySize )
         {
            currentVal = (GenericData)localArray[unsortedIndex];
            if( minValue.compareTo(currentVal) < 0 )
            {
               minValIndex = unsortedIndex;
            }
            unsortedIndex++;
         }
         
         // remove minimum value and place at beginning of array
         minValue = removeData( minValIndex );
         insertData( atBeginning, minValue);
         
         // sorted section increases here
         sortedIndex++;
      }
   }
   
   /**
    * Shell Sort
    * <p>
    * Sorts elements in the array by using the Shell sort algorithm
    */
   @SuppressWarnings( "unchecked" )
   public void runShellSort()
   {
      int gap, gapPassIndex, insertionIndex;
      GenericData tempItem, testItem;
      boolean continueSearch;
      
      for( gap = arraySize / 2; gap > 0; gap /= 2)
      {
         for( gapPassIndex = gap; gapPassIndex < arraySize; gapPassIndex++)
         {
            tempItem = (GenericData)localArray[gapPassIndex];
            insertionIndex = gapPassIndex;
            continueSearch = true;
            
            while( continueSearch && insertionIndex >= gap )
            {
               testItem = (GenericData)localArray[insertionIndex - gap];
               
               if( testItem.compareTo( tempItem ) > 0 )
               {
                  localArray[insertionIndex] = localArray[insertionIndex - gap];
                  insertionIndex -= gap;
               }
               
               else
               {
                  continueSearch = false;
               }
            }
            
            localArray[insertionIndex] = tempItem;
         }
      }
   }
   
   /**
    * Swap elements
    * <p>
    * Swaps one element in the local array at a given index with
    * another element in the array at the other given index
    * 
    * @param oneIndex integer for the first element location
    * @param otherIndex integer for the second element location
    */
   @SuppressWarnings( "unchecked" )
   public void swapElements( int oneIndex, int otherIndex)
   {
      GenericData otherElementHolder = (GenericData)localArray[otherIndex];
      localArray[otherIndex] = localArray[oneIndex];
      localArray[oneIndex] = otherElementHolder;
   }
}
