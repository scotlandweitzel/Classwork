package p7_package;

/**
 * Iterator class that conducts iterator operations on a linked list engine
 * 
 * @author Scotland Weitzel
 */
public class IteratorClass 
{
/**
 * Constants 
 */
   private final char SPACE = ' ';
   private final char LEFT_BRACKET = '[';
   private final char RIGHT_BRACKET = ']';
   
/**
 * Member Data
 */
   private int currentIndex;
   BasicLLClass llData;
   
/*
 * Default Constructor
 */
   public IteratorClass()
   {
      currentIndex = 0;
      llData = new BasicLLClass();
   }
   
/**
 * Copy Constructor
 * @param copied IteratorClass to be copied
 */
   public IteratorClass( IteratorClass copied )
   {
      currentIndex = 0;
      llData = new BasicLLClass( copied.llData );
   }
   
/**
 * Clear
 * <p>
 * Clears the list of data
 */
   public void clear()
   {
      llData.clear();
   }
   
/**
 * Get at Current
 * <p>
 * Gets value at iterator cursor location   
 * @return boolean result of accessing data at the cursor
 */
   public int getAtCurrent()
   {
      return llData.getAtIndex( currentIndex );
   }
   
/**
 * Is at First Element
 * <p>
 * Reports if iterator cursor is at the first element   
 * @return boolean result of test
 */
   public boolean isAtFirstElement()
   {
      if( !isEmpty() )
      {
         return currentIndex == 0;
      }
      return false;
   }
   
/**
 * Is at Last Element
 * <p>
 * Reports if iterator cursor is at the last element
 * @return boolean result of test
 */
   public boolean isAtLastElement()
   {
      if( !isEmpty() )
      {
         return currentIndex == llData.getCurrentSize() - 1;
      }
      return false;
   }
   
   public boolean isEmpty()
   {
      return llData.isEmpty();
   }
   
/**
 * Move Next
 * <p>
 * Moves the cursor to the right if it can
 * @return boolean success or failure of moving the cursor
 */
   public boolean moveNext()
   {
   // check if cursor can be moved and if the list is empty
      if( currentIndex != llData.getCurrentSize() - 1  && !isEmpty() )
      {
      // move the cursor right
         currentIndex++;
         return true;
      }
      
   // otherwise, cursor cannot move
      return false;
   }
   
/**
 * Move Previous
 * <p>
 * Moves the cursor to the left if it can
 * @return boolean success or failure of moving the cursor
 */
   public boolean movePrev()
   {
   // check if cursor can be moved and if the list is empty
      if( currentIndex != 0 && !isEmpty())
      {
      // move the cursor left
         currentIndex--;
         return true;
      }
   
   // otherwise, cursor cannot move
      return false;
   }
   
/**
 * Remove at Current
 * <p>
 * Removes and returns a data value from the cursor position   
 * @return int value removed from the list, or FAILED_ACCESS in failure
 */
   public int removeAtCurrent()
   {
      return llData.removeAtIndex( currentIndex );
   }
   
/**
 * Replace at Current
 * <p>
 * Replaces value at iterator cursor with new value
 * @param newValue
 * @return
 */
   public boolean replaceAtCurrent( int newValue)
   {
      return llData.setAtIndex( currentIndex, newValue, BasicLLClass.REPLACE );
   }
   
/**
 * Run Diagnostic Display
 * <p>
 * Shows a space-delimited list with cursor location indicated
 */
   public void runDiagnosticDisplay()
   {
      int index = 0;
      while( index < llData.getCurrentSize() )
      {
      // prints brackets around item in list to show cursor
         if( index == currentIndex )
         {
            System.out.print( LEFT_BRACKET );
            System.out.print( llData.getAtIndex( index ) );
            System.out.print( RIGHT_BRACKET );
            System.out.print( SPACE );
         }
         
      // prints any other item in the linked list
         else
         {
            System.out.print( llData.getAtIndex( index ) );
            System.out.print( SPACE );
         }
         index++;
      }
      
   // end line
      System.out.println();
   }
   
/**
 * Set After Current
 * <p>
 * Inserts new value after the value at the iterator cursor
 * @param newValue - integer to be inserted into the list
 * @return result of insertion, true if successful, false otherwise
 */
   public boolean setAfterCurrent( int newValue )
   {
      return llData.setAtIndex( currentIndex, newValue, BasicLLClass.INSERT_AFTER );
   }
   
/**
 * Set Before Current
 * <p>
 * Inserts new value before the value at the iterator cursor
 * @param newValue - integer to be inserted into the list
 * @return result of insertion, true if successful, false otherwise
 */
   public boolean setBeforeCurrent( int newValue )
   {
      boolean success = llData.setAtIndex( currentIndex, newValue, 
                                           BasicLLClass.INSERT_BEFORE );
      
   // update cursor to the correct position
      moveNext();
      return success;
      
   }
   
/**
 * Set to First Element
 * <p>
 * Sets the iterator to the beginning of the list 
 * @return result of move, true if successful, false otherwise
 */
   public boolean setToFirstElement()
   {
      if( !isEmpty() )
      {
         currentIndex = 0;
         return true;
      }
      
      return false;
   }
   
/**
 * Set to Last Element
 * <p>
 * Sets the iterator to the end of the list
 * @return result of move, true if successful, false otherwise
 */
   public boolean setToLastElement()
   {
      if( !isEmpty() )
      {
         currentIndex = llData.getCurrentSize() - 1;
         return true;
      }
      
   // 
      return false;
   }
}
