package p5_package;

/**
 * Class for managing the use of a simple stack
 * 
 * @author Scotland Weitzel
 *
 */
public class SimpleStackClass
{
   private final int DEFAULT_CAPACITY = 10;
   
   public static final int FAILED_ACCESS = -999999;
   
   private int capacity;
   
   private int size;
   
   private int stackTopIndex;
   
   private int[] stackData;
   
   
   /**
    * Default Constructor
    * <p>
    * This constructor sets capacity to the default capacity and initializes the
    * stack array
    */
   public SimpleStackClass()
   {
      capacity = DEFAULT_CAPACITY;
      size = 0;
      stackTopIndex = -1;
      stackData = new int[capacity];
   }
   
   /**
    * Initialization Constructor
    * <p>
    * This constructor sets capacity to desired capacity passed in by the 
    * parameter
    * 
    * @param capacitySetting integer for desired array capacity
    */
   public SimpleStackClass( int capacitySetting )
   {
      capacity = capacitySetting;
      size = 0;
      stackTopIndex = -1;
      stackData = new int[capacity];
   }
   
   /**
    * Copy Constructor
    * <p>
    * This creates a new stack object using a copy
    * 
    * @param copied SimpleStackClass that is to be copied
    */
   public SimpleStackClass( SimpleStackClass copied )
   {
      int index = 0;
      
      capacity = copied.capacity;
      size = copied.size;
      stackTopIndex = copied.stackTopIndex;
      stackData = new int[capacity];
      
      while( index < copied.size )
      {
         stackData[index] = copied.stackData[index];
         index++;
      }
      
   }
   
   /**
    * Check for Resize
    * <p>
    * Checks if resize is needed, and resizes to twice the current capacity 
    * if needed
    * 
    * @return boolean condition indicating whether resize took place
    */
   private boolean checkForReSize()
   {
   // if size is equal to capacity, resize array
      if( size == capacity )
      {
      // set up index to go through smaller stack
         int index = 0;
      // double stack capacity
         capacity *= 2;
      // create temporary stack with doubled capacity
         int[] tempStack = new int[capacity];
      // copy contents from small stack to the larger stack
         while( index < size )
         {
            tempStack[index] = stackData[index];
            index++;
         }
      // set current stack to new double-capacity stack
         stackData = tempStack;
      // return true to indicate resize has ocurred
         return true;
      }
      
   // otherwise, do nothing
      return false;
   }
   
   /**
    * Clear
    * <p>
    * Clears the array by setting size to zero and the head index to negative 1
    */
   public void clear()
   {
      size = 0;
      stackTopIndex = -1;
   }
   
   /**
    * Is Empty
    * <p>
    * Checks if the stack is empty
    * 
    * @return boolean condition in which the stack is empty
    */
   public boolean isEmpty()
   {
      return ( size == 0 );
   }
   
   /**
    * Peek Top
    * <p>
    * Checks what is at the top of the stack
    * 
    * @return integer value of valid item or FAILED_ACCESS constant
    */
   public int peekTop()
   {
   // return the top of the stack if the stack isn't empty 
      if( !isEmpty() )
      {
         return stackData[stackTopIndex];
      }
      
   // otherwise, return a FAILED_ACCESS
      return FAILED_ACCESS;
   }
   
   /**
    * Pop
    * <p>
    * Removes and returns data from the top of the stack
    * 
    * @return integer value of valid item or FAILED_ACCESS constant
    */
   public int pop()
   {
   // save top of stack
      int topOfStack = peekTop();
   // update top of stack if top value was valid
      if( topOfStack != FAILED_ACCESS )
      {
         stackTopIndex--;
         size--;
      }
   // return saved value
      return topOfStack;
   }
   
   /**
    * Push
    * <p>
    * Checks for resize, and then pushes value to the stack
    * 
    * @param newValue integer to be placed on the stack
    */
   public void push( int newValue )
   {
   // check for resize
      checkForReSize();
   // increment top of stack pointer
      stackTopIndex++;
   // increment stack size
      size++;
   // add the new value to the stack
      stackData[stackTopIndex] = newValue;
   }
}
