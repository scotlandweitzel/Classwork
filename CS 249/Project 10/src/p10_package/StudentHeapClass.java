package p10_package;

/**
 * Array-based StudentClass max heap class used as a priority queue for 
 * StudentClass data; prioritized by GPA using StudentClass compareTo method
 * @author dagongent1
 * <p>
 * For Reference: A node at index [k] will have children at indices [2k+1] and 
 * [2k+2] in a heap array
 * <p>
 * For Reference: A node at index [k] will have a parent at index [(k-1)/2]
 */
public class StudentHeapClass
{
/**
 * Class Constants
 */
   public final int DEFAULT_ARRAY_CAPACITY = 10;;
   public final int PARENT = 1001;
   public final int LEFT = 2002;
   public final int RIGHT = 3003;
   
/**
 * Class Data
 */
   private int arrayCapacity;
   private int arraySize;
   private boolean displayFlag;
   
   private StudentClass[] heapArray;
   
/**
 * Default Constructor
 * <p>
 * Sets up array management conditions and default display flag setting
 */
   public StudentHeapClass()
   {
      arrayCapacity = DEFAULT_ARRAY_CAPACITY;
      arraySize = 0;
      heapArray = new StudentClass[ arrayCapacity ];
      displayFlag = false;
   }
   
/**
 * Copy Constructor
 * <p>
 * Copies array and array management conditions and default display flag setting
 * 
 * @param copied - StudentHeapClass object to be copied
 */
   public StudentHeapClass( StudentHeapClass copied )
   {
      arrayCapacity = copied.arrayCapacity;
      arraySize = copied.arraySize;
      heapArray = new StudentClass[ arrayCapacity ];
      
      displayFlag = false;
      
      int index = 0;
      while( index < arraySize )
      {
         heapArray[index] = copied.heapArray[index];
         index++;
      }
   }
   
/**
 * Add Item
 * <p>
 * Accepts StudentData item and adds it to heap
 * <p>
 * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data addition
 * <p>
 * Note: must check for resize before attempting to add an item
 * 
 * @param newItem - StudentClass data item to be added
 */
   public void addItem( StudentClass newItem )
   {
   // display flag printing
      if( displayFlag )
      {
      // display 
         System.out.println( "\nAdding new process: " + newItem.toString() );
      }
      
   // check for resize
      checkForResize();
   // save index of where the item will be added (end of array)
      int currentIndex = arraySize;
   // add item to the array
      heapArray[currentIndex] = newItem;
   // increase array size
      arraySize++;
   // bubble up to correct heap
      bubbleUpArrayHeap( currentIndex );
   }
   
/**
 * Bubble Up Array Heap
 * <p>
 * Recursive operation to reset data in the correct order for the max heap after 
 * the new data addition
 * 
 * @param currentIndex - index of current item being assessed, and moved up as 
 * needed
 */
   private void bubbleUpArrayHeap( int currentIndex )
   {
   // check if the current index is greater than zero, to prevent an infinite 
   // loop in which the bubble up method constantly calculates 0 as the parent 
   // index 
      if( currentIndex > 0 )
      {
      // get the parent node for the current item
         int parentIndex = (currentIndex - 1) / 2;
      // compare the added item to its parent
         if( heapArray[currentIndex].compareTo( heapArray[parentIndex] ) > 0)
         {
         // display flag printouts
            if( displayFlag )
            {
               System.out.println( "   - Bubble up:" );
               System.out.println( "     - Swapping parent: " 
                                   + heapArray[parentIndex].toString() 
                                   + " with child: " 
                                   + heapArray[currentIndex].toString() );
            }
         // swap parent and child and bubble up with the new parent node
            StudentClass parentItem = heapArray[parentIndex];
            heapArray[parentIndex] = heapArray[currentIndex];
            heapArray[currentIndex] = parentItem;
            bubbleUpArrayHeap( parentIndex );
         }
      }
   }
   
/**
 * Check For Resize
 * <p>
 * Automatic resize operation used prior to any new data addition in the heap
 * <p>
 * Tests for full heap array, and resizes to twice the current capacity
 */
   private void checkForResize()
   {
      if( arraySize == arrayCapacity )
      {
         arrayCapacity *= 2;
         StudentClass[] expandedArray = new StudentClass[ arrayCapacity ];
         
         int index = 0;
         while( index < arraySize )
         {
            expandedArray[index] = heapArray[index];
            index++;
         }
         
         heapArray = expandedArray;
      }
   }
   
/**
 * Tests for empty heap
 * 
 * @return StudentClass item removed
 */
   public boolean isEmpty()
   {
      return arraySize == 0;
   }
   
/**
 * Removes StudentClass data item from top of max heap, thus being the student 
 * with the highest GPA
 * <p>
 * Note: Uses trickleDownArrayHeap to resove unbalanced heap after data removal
 * 
 * @return StudentClass item removed
 */
   public StudentClass removeItem()
   {
   // remove item only if the array isn't empty
      if( !isEmpty() )
      {
      // set current index
         int currentIndex = 0;
      // save item to be removed
         StudentClass removedItem = heapArray[currentIndex];
      // replace head node with node furthest bottom and furthest right in heap
         heapArray[0] = heapArray[arraySize - 1];
      // decrease array size
         arraySize--;
      // display flag operations
         if( displayFlag )
         {
            System.out.println( "\nRemoving process: " 
                                + heapArray[currentIndex] );
         }
      // trickle down to balance heap
         trickleDownArrayHeap( currentIndex );
      // return the removed item
         return removedItem;
      }
      
   // otherwise, removal cannot be completed; heap is empty
      else
      {
         return null;
      }
   }
   
/**
 * Set Display Flag
 * <p>
 * Utility method to set the display flag for displaying internal operations of 
 * the heap bubble and trickle operations
 * 
 * @param setState - flag used to set the state to display, or not
 */
   public void setDisplayFlag( boolean setState )
   {
      displayFlag = setState;
   }
   
/**
 * Show Array
 * <p>
 * Dumps array to screen as is, no filtering or management
 */
   public void showArray()
   {
      int index = 0;
      while( index < arraySize )
      {
         System.out.println( heapArray[index].toString() );
         index++;
      }
   }
   
/**
 * Trickle Down Array Heap
 * <p>
 * Recursive operation to reset data in the correct order for the max heap after 
 * data removal
 * 
 * @param currentIndex - index of currrent item being assessed, and moved down 
 * as required
 */
   private void trickleDownArrayHeap( int currentIndex )
   {
   // initialize variable for larger child and last index (for readability)
      int comparingIndex = currentIndex;
      int lastIndex = arraySize - 1;
   // initialize variables for enumeration
      int leftChild = 0;
      int rightChild = 0;
      
   // calculate the indexes of the children
      int leftChildIndex = ( currentIndex * 2 ) + 1;
      int rightChildIndex = ( currentIndex * 2 ) + 2;
      
   // check if the children exist in the heap
      if( leftChildIndex <= lastIndex )
      {
         leftChild = LEFT;
      }
      if( rightChildIndex <= lastIndex )
      {
         rightChild = RIGHT;
      }
      
   // when there are two children, determine the the greater
      if( leftChild == LEFT && rightChild == RIGHT )
      {
      // compare left child to right child to find the larger
         if( heapArray[leftChildIndex].compareTo(heapArray[rightChildIndex] ) 
             > 0 )
         {
            comparingIndex = leftChildIndex;
         }
         else
         {
            comparingIndex = rightChildIndex;
         }
      }
      
   // otherwise, determine which child exists
      else if( leftChild == LEFT )
      {
         comparingIndex = leftChildIndex;
      }
      
      else if( rightChild == RIGHT )
      {
         comparingIndex = rightChildIndex;
      }
      
   // compare the current node to the determined comparison node
      if( comparingIndex != currentIndex )
      {
      // perform swap if the current node is less than the comparison node
         if( heapArray[comparingIndex].compareTo(heapArray[currentIndex]) > 0 )
         {
         // display flag operations
            if( displayFlag )
            {
               System.out.println( "   - Trickle down:" );
               System.out.println( "     - Swapping parent: " 
                                   + heapArray[currentIndex].toString() 
                                   + " with child: " 
                                   + heapArray[comparingIndex].toString() );
            }
         // swap operation
            StudentClass temp = heapArray[currentIndex];
            heapArray[currentIndex] = heapArray[comparingIndex];
            heapArray[comparingIndex] = temp;
            trickleDownArrayHeap( comparingIndex );
         }
      }
   }
}
