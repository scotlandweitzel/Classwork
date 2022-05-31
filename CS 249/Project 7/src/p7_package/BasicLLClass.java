package p7_package;

/**
 * Class wrapper for a Java linked list, with management operations
 * 
 * @author Scotland Weitzel
 */

public class BasicLLClass
{
// Constants
   public static final int FAILED_ACCESS = -999999;
   public static final int INSERT_AFTER = 1003;
   public static final int INSERT_BEFORE = 1002;
   public static final int REMOVE = 1004;
   public static final int REPLACE = 1001;
   public static final int RETRIEVE = 1003;
   
// Member Data
   BasicNode headRef;
   
// Embedded BasicNode Class
   public class BasicNode
   {
   // Member Data
      BasicNode nextRef;
      int value;
      
   /**
    * Initialization Constructor for Basic Node
    * @param newVal integer value to store in the node
    */
      public BasicNode( int newVal )
      {
         nextRef = null;
         value = newVal;
      }

   /**
    * Copy Constructor for Basic Node
    * @param copied BasicNode object to copy from
    */
      public BasicNode( BasicNode copied )
      {
         nextRef = null;
         value = copied.value;
      }
   }
   
   /**
    * Default Constructor for Linked List
    */
   BasicLLClass()
   {
      headRef = null;
   }
   
/**
 * Copy Constructor for Linked List
 * @param copied BasicLLClass object to be copied
 */
   BasicLLClass( BasicLLClass copied )
   {
      BasicNode wkgRef = copied.headRef;
      
      // check if copied headRef is null
      if( wkgRef == null )
      {
         headRef = null;
      }
      
      // otherwise make a separate list to copy each node separately
      else
      {
         headRef = new BasicNode( wkgRef );
         BasicNode copyRef = wkgRef.nextRef;
         BasicNode localRef = headRef;
         while( copyRef.nextRef != null )
         {
            localRef.nextRef = new BasicNode( copyRef );
            wkgRef = wkgRef.nextRef;
            copyRef = copyRef.nextRef;
            localRef = localRef.nextRef;
         }
      }
   }

   
/**
 * Access at Index
 * <p>
 * Utility method used by getAtIndex and removeAtIndex to access and possibly
 * remove element depending on control code
 * <p>
 * Note: Uses only one loop
 * @param controlCode - int value with either RETRIEVE or REMOVE
 * @param index - int faux index of element to be retrieved or removed
 * @return int value at element or FAILED_ACCESS
 */
   private int accessAtIndex( int controlCode, int index )
   {
      int loopIndex = 0;
      BasicNode wkgRef = headRef;
      BasicNode specifiedItem = getRefAtIndex( wkgRef, index );
      
      //
      if( controlCode == REMOVE )
      {
         while( loopIndex < index - 1)
         {
            wkgRef = wkgRef.nextRef;
            loopIndex++;
         }
         wkgRef.nextRef = wkgRef.nextRef.nextRef;
      }
      
      // return the item found at the index
         return specifiedItem.value;
   }
   
/**
 * Clear
 * <p>
 * Clears list of all valid items by setting head ref to null
 */
   public void clear()
   {
      headRef = null;
   }
   
/**
 * Get at Index
 * <p>
 * Accesses item in array at specified index within the list
 * @param accessIndex - int index of requested element value
 * @return int accessed value, or FAILED_ACCESS in failure
 */
   public int getAtIndex( int accessIndex )
   {
      boolean indexOutOfRange = getCurrentSize() <= accessIndex;
      
      // Check if headRef is null and return FAILED access if it is empty or the
      // requested index is out of range
      if( headRef == null || indexOutOfRange)
      {
         return FAILED_ACCESS;
      }
      
      // otherwise, get the value at the specified index
      return accessAtIndex( RETRIEVE, accessIndex );
   }
   
/**
 * Get Current Size
 * <p>
 * Gets the current size of the list
 * @return int size of the list
 */
   public int getCurrentSize()
   {
      BasicNode wkgRef = headRef;
      return getCurrentSizeHelper( wkgRef );
   }
   
/**
 * Get Current Size - helper
 * <p>
 * Counts each item in the list through recursion to determine the size
 * @param wkgRef - BasicNode ref starts with head ref and is used for 
 * recursion
 * @return int size of 1 to add to a total sum after recursion
 */
   private int getCurrentSizeHelper( BasicNode wkgRef )
   {  
      int currentSize = 1;
      
      // Check if list is empty
      if( wkgRef == null )
      {
         return 0;
      }
      // loop through list recursively adding to the size with each level
      else
      {
         currentSize += getCurrentSizeHelper( wkgRef.nextRef );
      }
      
      // return the size of the list
      return currentSize;
   }
   
/**
 * Get Reference at Index
 * <p>
 * Gets the item at a specified faux index in the list
 * @param wkgRef - BasicNode reference
 * @param index - int faux index for requested element
 * @return BasicNode reference to element at faux index
 */
   private BasicNode getRefAtIndex( BasicNode wkgRef, int index )
   {
      // check if we are at the specified item
      if( index == 0 )
      {
         return wkgRef;
      }
      
      // if this runs, we are still traversing the list; run the function again
      // on the next reference and reduce the index parameter by 1
      else
      {
         return getRefAtIndex( wkgRef.nextRef, index - 1);
      }
   }
   
/**
 * Is Empty
 * <p>
 * Tests if the headRef is null, if so, the list is empty
 * @return boolean result of test
 */
   public boolean isEmpty()
   {
      return ( headRef == null );
   }
   
/**
 * Remove at Index
 * <p>
 * Removes item from array at specified index if the index is within bounds
 * <p>
 * Note: Must call accessAtIndex with REMOVE to conduct operation
 * @param removeIndex - int faux index corresponding to the item tobe removed
 * @return int value if successful, FAILED_ACCESS otherwise
 */
   public int removeAtIndex( int removeIndex )
   {
      boolean indexOutOfRange = getCurrentSize() <= removeIndex;
      
      // if the list is empty or the index requested is out of range, then 
      // FAILED_ACCESS is returned
      if( headRef == null || indexOutOfRange)
      {
         return FAILED_ACCESS;
      }
      
      else
      {
         return accessAtIndex( REMOVE, removeIndex );
      }
   }
   
/**
 * Set at Index
 * <p>
 * Sets item in list at specified faux index
 * <p>
 * Note: REPLACE flag indicates a replacement of the value at specified index
 * <p>
 * Note: INSERT_BEFORE: the new value is inserted before the item at the
 * specified index
 * <p>
 * Note: INSERT_AFTER: the new value is inserted after the item at the
 * specified index
 * <p>
 * Note: Either INSERTs with an index of 0 and empty list, the item is
 * placed as the head reference
 * @param setIndex - int faux index where insertion will be completed
 * @param newValue - int value of the node to be added to the list
 * @param replaceFlag - int value to determine control of
 * @return boolean expression for success or failure of insertion
 */
   public boolean setAtIndex( int setIndex, int newValue, int replaceFlag )
   {
      int workingIndex = 0;
      BasicNode wkgRef = headRef;
      BasicNode insertNode = new BasicNode( newValue );
      
      // Check if list is empty
      if( getCurrentSize() == 0 )
      {
         // if so, make the new node the head reference and report success
         headRef = insertNode;
         return true;
      }
      
      // operation for replacing
      else if( replaceFlag == REPLACE )
      {
         // go to necessary location in the list to start replacing
         while( workingIndex < setIndex - 1)
         {
            wkgRef = wkgRef.nextRef;
            workingIndex++;
         }
         // replace the node at the specified index and report success
         insertNode.nextRef = wkgRef.nextRef.nextRef;
         wkgRef.nextRef = insertNode;
         // but first check if we are replacing the head reference
         if( setIndex == 0 )
         {
            headRef = insertNode;
         }
         return true;
      }
      
      // operation for inserting before
      else if( replaceFlag == INSERT_BEFORE )
      {
         // check if we are inserting at the head
         if( setIndex == 0 )
         {
            // insert at the head ref, update the head ref, and report success
            insertNode.nextRef = headRef;
            headRef = insertNode;
            return true;
         }
         // go to necessary location in the list to start replacing
         while( workingIndex < setIndex - 1 )
         {
            wkgRef = wkgRef.nextRef;
            workingIndex++;
         }
         // replace the node before the specified index and report success
         insertNode.nextRef = wkgRef.nextRef;
         wkgRef.nextRef = insertNode;
         return true;
      }
      
      // operation for inserting after
      else if( replaceFlag == INSERT_AFTER )
      {
         // go to the necessary location in the list to start replacing
         while( workingIndex < setIndex )
         {
            wkgRef = wkgRef.nextRef;
            workingIndex++;
         }
         // replace the node after the specified index and report success
         insertNode.nextRef = wkgRef.nextRef;
         wkgRef.nextRef = insertNode;
         return true;
      }
      
   // return false if no insertion can be completed
      return false;
   }
}
