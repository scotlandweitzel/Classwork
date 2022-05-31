package p6_package;

/**
 * Iterator class inherited from BasicArrayClass; conducts iterator operations
 * 
 * @author Scotland Weitzel
 *
 * @param <BasicArrayClass>
 */
public class IteratorClass extends BasicArrayClass
{
   /**
    * constant character for display
    */
   private final char SPACE = ' ';
   /**
    * constant character for display
    */
   private final char LEFT_BRACKET = '[';
   /**
    * constant character for display
    */
   private final char RIGHT_BRACKET = ']';
   
   
   /**
    * current index of iterator
    */
   private int currentIndex;
   
   
   /**
    * Default Constructor
    * <p>
    * default constructor for the IteratorClass
    */
   public IteratorClass()
   {
      super();
      currentIndex = 0;
   }
   
   /**
    * Initialization Constructor
    * <p>
    * initialization constructor for the IteratorClass
    * @param initCapacity - integer value at which to set initial array capacity
    */
   public IteratorClass( int initCapacity )
   {
      super( initCapacity );
      currentIndex = 0;
   }
   
   /**
    * Copy Constructor
    * <p>
    * copy constructor for the IteratorClass
    * @param copied - IteratorClass object to be copied
    */
   public IteratorClass( IteratorClass copied )
   {
      super( copied );
      currentIndex = copied.currentIndex;
   }
   
   
   /**
    * Clear
    * <p>
    * clears the array
    */
   public void clear()
   {
      super.clear();
   }
   
   
   /**
    * Get At Current Location
    * <p>
    * gets value at iterator cursor location
    * @return integer value returned; FAILED_ACCESS if not in array
    */
   public int getAtCurrent()
   {
      return super.getAtIndex( currentIndex );
   }
   
   
   /**
    * Is At First Element
    * <p>
    * reports if iterator cursor is at first element
    * @return boolean result; true if at beginning, otherwise false
    */
   public boolean isAtFirstElement()
   {
      return currentIndex == 0;
   }
   
   
   /**
    * Is At Last Element
    * <p>
    * reports if iterator cursor is at the last element
    * @return boolean result; true if at end, otherwise false
    */
   public boolean isAtLastElement()
   {
      return currentIndex == super.getCurrentSize() - 1;
   }
   
   
   /**
    * Is Empty
    * <p>
    * reports if list is empty
    * @return boolean result; true if empty, otherwise false
    */
   public boolean isEmpty()
   {
      return super.isEmpty();
   }
   
   
   /**
    * Move To Next Location
    * <p>
    * if possible, moves iterator cursor one position to the right, or next and
    * checks first if list is empty
    * @return boolean result; true if successful, otherwise false
    */
   public boolean moveNext()
   {
      if( !isAtLastElement() )
      {
         currentIndex++;
         return true;
      }
      
      return false;
   }
   
   
   /**
    * Move To Previous Location
    * <p>
    * if possible, moves iterator cursor one position to the left, or prev. and
    * checks first if the list is empty
    * @return
    */
   public boolean movePrev()
   {
      if( !isAtFirstElement() )
      {
         currentIndex--;
         return true;
      }
      
      return false;
   }
  
   
   /**
    * Remove At Current Location
    * <p>
    * removes and returns a data value from the iterator cursor position
    * <p>
    * Note: cursor must be located at succeeding element unless last item removd
    * @return integer value removed from list, or FAILED_ACCESS if not in array
    */
   public int removeAtCurrent()
   {
      return super.removeAtIndex( currentIndex );
   }
   
   
   /**
    * Replace Value At Current Location
    * <p>
    * replaces value at iterator cursor with new value
    * @param newValue - integer value to be inserted into the list
    * @return boolean result; true if successful, otherwise false
    */
   public boolean replaceAtCurrent( int newValue )
   {
      return super.setAtIndex( currentIndex, newValue, REPLACE);
   }
   
   
   /**
    * Run Diagnostic Display
    * <p>
    * shows space-delimited list with cursor location indicated
    */
   public void runDiagnosticDisplay()
   {
      int index = 0;
      
      while( index < super.getCurrentSize() )
      {
         if( index == currentIndex )
         {
            System.out.print( LEFT_BRACKET );
            System.out.print( super.getAtIndex( index ) );
            System.out.print( RIGHT_BRACKET );
            System.out.print( SPACE );
         }
         else
         {
            System.out.print( super.getAtIndex( index ) );
            System.out.print( SPACE );
         }
         index++;
      }
      System.out.println();
   }
   
   
   /**
    * Set After Current Location
    * <p>
    * inserts new value after value at iterator cursor
    * <p>
    * Note: current value must remain the same after data set
    * @param newValue - integer value to be inserted into the list
    * @return boolean result; true if successful, otherwise false
    */
   public boolean setAfterCurrent( int newValue )
   {
      return super.setAtIndex( currentIndex, newValue, INSERT_AFTER );
   }
   
   
   /**
    * Set Before Current Location
    * <p>
    * inserts new value before value at iterator cursor
    * <p>
    * Note: current value must remain the same after data set
    * @param newValue - integer value to be inserted into the list
    * @return boolean result; true if successful, otherwise false
    */
   public boolean setBeforeCurrent( int newValue )
   {
      if( super.getCurrentSize() != 0 )
      {
         currentIndex++;
      }
      return super.setAtIndex( currentIndex, newValue, INSERT_BEFORE );
   }
   
   
   /**
    * Set To First Element
    * <p>
    * sets iterator cursor to beginning of the list
    * @return boolean result; true if successful, otherwise false
    */
   public boolean setToFirstElement()
   {
      currentIndex = 0;
      return true;
   }
   
   
   /**
    * Set To Last Element
    * <p>
    * sets iterator cursor to end of the list
    * @return boolean result; true if successful, otherwise false
    */
   public boolean setToLastElement()
   {
      currentIndex = super.getCurrentSize() - 1;
      return true;
   }
}
