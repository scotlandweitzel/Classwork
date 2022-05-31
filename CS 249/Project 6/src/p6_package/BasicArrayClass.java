package p6_package;

public class BasicArrayClass
{
   /**
    * default constant capacity
    */
   private static final int DEFAULT_CAPACITY = 10;
   /**
    * default failed access constant
    */
   public static final int FAILED_ACCESS = -999999;
   
   
   /**
    * constant used for allowing setAtIndex to replace value at index
    */
   public static final int REPLACE = 1001;
   /**
    * constant used for allowing setAtIndex to insert value at index
    */
   public static final int INSERT_BEFORE = 1002;
   /**
    * constant used for allowing setAtIndex to insert value at index
    */
   public static final int INSERT_AFTER = 1003;
   /**
    * constant used for allowing accessAtIndex to remove an item
    */
   public static final int REMOVE = 1004;
   /**
    * constant used for allowing accessAtIndex to retrieve an item
    */
   public static final int RETRIEVE = 1005;
   
   
   /**
    * member integer array
    */
   private int[] localArray;
   /**
    * size of the array
    */
   private int arraySize;
   /**
    * capacity of the array
    */
   private int arrayCapacity;
   
   
   /**
    * Default Constructor
    * <p>
    * default constructor, initializes array to default capacity
    */
   protected BasicArrayClass()
   {
      arraySize = 0;
      arrayCapacity = DEFAULT_CAPACITY;
      localArray = new int[arrayCapacity];
   }
   
   /**
    * Initialization Constructor
    * <p>
    * initialization constructor, initializes array to specified capacity
    * @param capacity - integer value for specified capacity of the array
    */
   protected BasicArrayClass( int capacity )
   {
      arraySize = 0;
      arrayCapacity = capacity;
      localArray = new int[arrayCapacity];
   }
   
   /**
    * Copy Constructor
    * <p>
    * copy constructor, initializes array to size and capacity of copied array, 
    * then copies only the elements up to the given size
    * @param copied - BasicArrayClass object to be copied
    */
   protected BasicArrayClass( BasicArrayClass copied )
   {
      arraySize = copied.arraySize;
      arrayCapacity = copied.arrayCapacity;
      localArray = new int[arrayCapacity];
      
      int index = 0;
      while( index < arraySize )
      {
         localArray[index] = copied.localArray[index];
         index++;
      }
   }
   
   
   /**
    * Access At Index
    * <p>
    * utility method used by getAtIndex and removeAtIndex to access and possibly 
    * remove item depending on given control code
    * <p>
    * Note: uses only one loop
    * @param controlCode - integer value with either RETRIEVE or REMOVE
    * @param index - integer index of element to be retrieve/removed
    * @return integer value at element or FAILED_ACCESS if out of bounds
    */
   private int accessAtIndex( int controlCode, int index )
   {
      int valueAtIndex = localArray[index];
      int removeIndex = index;
      
      if( controlCode == REMOVE )
      {
         while( removeIndex < arraySize - 1 )
         {
            localArray[removeIndex] = localArray[removeIndex + 1];
            removeIndex++;
         }
         arraySize--;
      }
   
      
      return valueAtIndex;
   }
   
   
   /**
    * Check For Resize
    * <p>
    * checks for need to resize; if this is necessary, creates new array with 
    * double the original capacity, loads data from original array to new one, 
    * then sets localArray to new array
    */
   protected void checkForResize()
   {
      if( arraySize == arrayCapacity )
      {
         int[] expandedArray;
         int index = 0;
         arrayCapacity *= 2;
         expandedArray = new int[arrayCapacity];
         
         while( index < arraySize )
         {
            expandedArray[index] = localArray[index];
            index++;
         }
         
         localArray = expandedArray;
      }
   }
   
   
   /**
    * Clear
    * <p>
    * clears the array of all valid values by setting array size to zero, values 
    * remain in array but are not accessible
    */
   protected void clear()
   {
      arraySize = 0;
   }
   
   
   /**
    * Get At Index
    * <p>
    * accesses item in array at specified index if index is within array bounds
    * <p>
    * Note: calls accessAtIndex with RETRIEVE to conduct action
    * @param accessIndex - integer index of requested element value
    * @return accessed value if successful, FAILED_ACCESS if not
    */
   protected int getAtIndex( int accessIndex )
   {
      int valueAtIndex = FAILED_ACCESS;
      if( accessIndex < arraySize )
      {
         valueAtIndex = accessAtIndex( RETRIEVE, accessIndex);
      }
      return valueAtIndex;
   }
   
   
   /**
    * Get Current Capacity
    * <p>
    * gets current capacity of the array
    * @return integer capacity of the array
    */
   protected int getCurrentCapacity()
   {
      return arrayCapacity;
   }
   
   /**
    * Get Current Size
    * <p>
    * gets current size of the array
    * @return integer size of the array
    */
   protected int getCurrentSize()
   {
      return arraySize;
   }
   
   
   /**
    * Is Empty
    * <p>
    * tests for size of array to equal zero, no valid values stored in the array
    * @return boolean result; true if empty, false otherwise
    */
   protected boolean isEmpty()
   {
      return arraySize == 0;
   }
   
   
   /**
    * Remove At Index
    * <p>
    * removes item from array at specified index if index is within array bounds
    * <p>
    * Note: each data item from the element immediately above the remove index 
    * to the end of the array is moved down by one element
    * <p>
    * Note: must call accessAtIndex with REMOVE to conduct action
    * @param removeIndex - integer index of element value to be removed
    * @return integer value if successful, FAILED_ACCESS if not
    */
   protected int removeAtIndex( int removeIndex )
   {
      int valueAtIndex = FAILED_ACCESS;
      if( removeIndex < arraySize )
      {
         valueAtIndex = accessAtIndex( REMOVE, removeIndex );
      }
      return valueAtIndex;
   }
   
   
   /**
    * Set At Index
    * <p>
    * sets item in array at specified index
    * <p>
    * Note: If REPLACE is used, new value overwrites value at given index
    * <p>
    * Note: if INSERT_BEFORE is used, new value is inserted prior to the value 
    * at the given index moving all other elements up by one
    * <p>
    * Note: if INSERT_AFTER is used, new value is inserted after the value at 
    * the given index moving all other elements up by one
    * <p>
    * Note: if either INSERT_XXXXX constants are used with index zero and an 
    * empty array, new value is inserted at index zero
    * <p>
    * Note: method checks for available array capacity and adjusts it as needed 
    * prior to inserting new item
    * <p>
    * Note: method must also check for correct array boundaries depending upon 
    * INSERT/REPLACE state
    * @param setIndex - integer index of element at which value is to be insert.
    * @param newValue - integer value to be placed in array
    * @param replaceFlag - integer flag to indicate insertion/replacement
    * @return boolean result; true if inserted, false if incorrect index was 
    * used
    */
   protected boolean setAtIndex( int setIndex, int newValue, int replaceFlag )
   {
      checkForResize();
      int index = arraySize;
      
      if( !isEmpty() )
      {
         if( replaceFlag == REPLACE )
         {
            localArray[setIndex] = newValue;
            arraySize++;
            return true;
         }
         else if( replaceFlag == INSERT_AFTER )
         {
            while(index > setIndex + 1)
            {
               localArray[index] = localArray[index - 1];
               index--;
            }
            localArray[setIndex + 1] = newValue;
            arraySize++;
            return true;
         }
         else if( replaceFlag == INSERT_BEFORE )
         {
            while(index >= setIndex)
            {
               localArray[index] = localArray[index - 1];
               index--;
            }
            localArray[setIndex - 1] = newValue;
            arraySize++;
            return true;
         }
      }
      
      else
      {
         localArray[0] = newValue;
         arraySize++;
         return true;
      }
      
      return false;
   }
   
}
