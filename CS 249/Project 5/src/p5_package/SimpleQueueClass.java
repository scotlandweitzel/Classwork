package p5_package;

/**
 * Class for managing the use of a simple queue
 * 
 * @author Scotland Weitzel
 *
 */
public class SimpleQueueClass
{
   private final int DEFAULT_CAPACITY = 10;
   
   public static final int FAILED_ACCESS = -999999;
   
   private int capacity;
   
   private int size;
   
   private int headIndex;
   
   private int tailIndex;
   
   private int[] queueData;
   
   /**
    * Default Constructor
    * <p>
    * Sets array capacity to the default capacity
    */
   public SimpleQueueClass()
   {
      capacity = DEFAULT_CAPACITY;
      size = 0;
      headIndex = 0;
      tailIndex = -1;
      queueData = new int[capacity];
   }
   
   /**
    * Initialization Constructor
    * <p>
    * Sets array capacity to the desired capacity passed as a paramater
    * 
    * @param capacitySetting integer for desired array capacity
    */
   public SimpleQueueClass( int capacitySetting )
   {
      capacity = capacitySetting;
      size = 0;
      headIndex = 0;
      tailIndex = -1;
      queueData = new int[capacity];
   }
   
   /**
    * Copy Constructor
    * <p>
    * Creates a Simple Queue object from an already existing Simple Queue object
    * @param copied SimpleQueueClass queue to be copied
    */
   public SimpleQueueClass( SimpleQueueClass copied )
   {
      int index = 0;
      
      capacity = copied.capacity;
      size = copied.size;
      headIndex = copied.headIndex;
      tailIndex = copied.tailIndex;
      queueData = new int[capacity];
      
      while( index < copied.size )
      {
         queueData[index] = copied.queueData[index];
         index++;
      }
   }
   
   /**
    * Check for Resize
    * <p>
    * Checks for resize and resizes to twice the current capacity if needed
    * 
    * @return boolean condition in which resize has occurred
    */
   private boolean checkForReSize()
   {
   // resize array when size reaches array capacity
      if( size == capacity )
      {
      // set up index to copy old array into new array
         int index = 0;
      // double capacity
         capacity *= 2;
      // set up a temporary array with new doubled capacity
         int[] tempQueue = new int[capacity];
      // copy old array into new array
         while ( index < size )
         {
            tempQueue[index] = queueData[index];
            index++;
         }
      // set main array to the doubled capacity array
         queueData = tempQueue;
      // return true to indicate resize has ocurred
         return true;
      }
      
   // otherwise, do nothing
      return false;
   }
   
   /**
    * Clear
    * <p>
    * clears the queue by setting size to zero, tail index to negative 1, and 
    * the head index to zero
    */
   public void clear()
   {
      size = 0;
      tailIndex = -1;
      headIndex = 0;
   }
   
   /**
    * Dequeue
    * <p>
    * Removes and returns value from front of queue and then updates head index
    * 
    * @return integer value at head, or FAILED_ACCESS constant
    */
   public int dequeue()
   {
   // store front of queue in temp variable
      int frontOfQueue = peekFront();
   // update head index if pulled value was valid
      if( frontOfQueue != FAILED_ACCESS )
      {
         updateHeadIndex();
         size--;
      }
   // return pulled value
      return frontOfQueue;
   }
   
   /**
    * Enqueue
    * <p>
    * Checks for resize, then enqueues the value
    * 
    * @param newValue integer value to be added to the queue, or FAILED_ACCESS
    */
   public void enqueue( int newValue )
   {
   // check for resize
      checkForReSize();
   // increment size
      size++;
   // update tail index
      updateTailIndex();
   // add data at tail index
      queueData[tailIndex] = newValue;
   }
   
   /**
    * Is Empty
    * <p>
    * Checks if the queue is empty or not
    * 
    * @return boolean condition in which the queue is empty
    */
   public boolean isEmpty()
   {
      return ( size == 0 );
   }
   
   /**
    * Peek Front
    * <p>
    * Looks at the front of the queue
    * 
    * @return value at the front of the queue, or FAILED_ACCESS
    */
   public int peekFront()
   {
   // return the head of the queue if the queue isn't empty
      if( !isEmpty() )
      {
         return queueData[headIndex];
      }
      
   // otherwise, return a FAILED_ACCESS
      return FAILED_ACCESS;
   }
   
   /**
    * Update Head Index
    * <p>
    * Updates the queue's head index to wrap around array as needed
    */
   private void updateHeadIndex()
   {
   // increment head index
      headIndex++;
   // re-wrap head index if necessary
      headIndex %= capacity;
   }
   
   /**
    * Update Tail Index
    * <p>
    * Updates the queue's tail index to wrap around array as needed
    */
   private void updateTailIndex()
   {
   // increment tail index
      tailIndex++;
   // re-wrap tail index if necessary
      tailIndex %= capacity;
   }
}
