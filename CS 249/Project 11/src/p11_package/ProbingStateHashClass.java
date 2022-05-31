package p11_package;

public class ProbingStateHashClass
{
/**
 * Member Constants
 */
   private final int DEFAULT_TABLE_SIZE = 11;
   private final int ITEM_NOT_FOUND = -1;
   public static final int LINEAR_PROBING = 101;
   public static final int QUADRATIC_PROBING = 102;
   
/**
 * Member Fields
 */
   private int tableSize;
   private int probeFlag;
   private StateDataClass[] tableArray;
   
/**
 * Default Constructor
 */
   public ProbingStateHashClass()
   {
      tableSize = DEFAULT_TABLE_SIZE;
      probeFlag = LINEAR_PROBING;
      tableArray = new StateDataClass[tableSize];
   }
   
/**
 * Initialization Constructor
 * 
 * @param inProbeFlag - sets linear or quadratic probing
 */
   public ProbingStateHashClass( int inProbeFlag )
   {
      tableSize = DEFAULT_TABLE_SIZE;
      probeFlag = inProbeFlag;
      tableArray = new StateDataClass[tableSize];
   }
   
/**
 * Initialization Constructor
 * 
 * @param inTableSize - sets table capacity
 * @param inProbeFlag - sets linear or quadratic probing
 */
   public ProbingStateHashClass( int inTableSize, int inProbeFlag )
   {
      if( inTableSize > DEFAULT_TABLE_SIZE )
      {
         tableSize = inTableSize;
      }
      else
      {
         tableSize = DEFAULT_TABLE_SIZE;
      }
      probeFlag = inProbeFlag;
      tableArray = new StateDataClass[tableSize];
   }
   
/**
 * Copy Constructor
 */
   public ProbingStateHashClass( ProbingStateHashClass copied )
   {
      tableSize = copied.tableSize;
      probeFlag = copied.probeFlag;
      tableArray = new StateDataClass[tableSize];
      
      int index = 0;
      while( index < tableSize )
      {
         StateDataClass copiedItem = 
               new StateDataClass( copied.tableArray[index] );
         
         tableArray[index] = copiedItem;
         index++;
      }
   }
  
   
/**
 * Add Item
 * <p>
 * Adds StateDataClass data item to hash table
 * <p>
 * Note: Uses hash index value from generateHash
 * <p>
 * Note: Shows probed index with data at the point of insertion
 * <p>
 * Note: Probe attempts are limited to the current capacity of the table
 * 
 * @param newItem - StateDataClass object to be added
 * @return success of operation
 */
   public boolean addItem( StateDataClass newItem )
   {
      int hashIndex = generateHash( newItem );
      int index = hashIndex + 1;
      int powerIterator = 1;
      
      System.out.print("Indices Probed: ");
      System.out.print( hashIndex );
      
   // check hash location
      if( tableArray[hashIndex] == null )
      {
         tableArray[hashIndex] = newItem;
         System.out.println();
         System.out.println( newItem.toString() + ", " + hashIndex 
               + " -> " + hashIndex );
         return true;
      }
      
   // otherwise start linear probe
      if( probeFlag == LINEAR_PROBING )
      {
         while( index % tableSize != hashIndex )
         {
            System.out.print( ", " + index );
            if( tableArray[index] == null )
            {
               tableArray[index] = newItem;
               System.out.println();
               System.out.println( newItem.toString() + ", " + hashIndex 
                                   + " -> " + index );
               return true;
            }
            index++;
         }
      }
   // or quadratic probing if it is set
      else
      {
         while( index < tableSize )
         {
            System.out.print(", " + index);
            if( tableArray[index] == null )
            {
               tableArray[index] = newItem;
               System.out.println();
               System.out.println( newItem.toString() + ", " + hashIndex 
                                   + " -> " + index );
               return true;
            }
            powerIterator++;
            index += toPower( powerIterator, 2 );
         }
         while( index % tableSize < hashIndex )
         {
            System.out.print(", " + index % tableSize);
            if( tableArray[index % tableSize] == null )
            {
               tableArray[index % tableSize] = newItem;
               System.out.println();
               System.out.println( newItem.toString() + ", " + hashIndex 
                                   + " -> " + index % tableSize );
               return true;
            }
            powerIterator++;
            index += toPower( powerIterator, 2 );
         }
      }
      
      return false;
   }
   
/**
 * Clear Hash Table
 * <p>
 * Clears hash table by setting all bins to null
 */
   public void clearHashTable()
   {
      int index = 0;
      while( index < tableSize )
      {
         tableArray[index] = null;
         index++;
      }
   }
   
/**
 * Find Item
 * <p>
 * Returns item found
 * 
 * @param searchItem - StateDataClass value to be found; uses findItemIndex
 * @return item found, or null if not found
 */
   public StateDataClass findItem( StateDataClass searchItem )
   {
      int foundItemIndex = findItemIndex( searchItem );
      if( foundItemIndex != ITEM_NOT_FOUND )
      {
         return tableArray[foundItemIndex];
      }
      
      return null;
   }
   
/**
 * Find Item Index
 * <p>
 * Searches for item index in hash table
 * <p>
 * Note: Uses linear or quadratic probing as configured
 * <p>
 * Note: Probing attempts limited to table capacity
 * <p>
 * Note: Probed indices are reported to screen
 * 
 * @param searchItem - StateDataClass object to be found
 * @return integer index location of search item
 */
   private int findItemIndex( StateDataClass searchItem )
   {
      int hashIndex = generateHash( searchItem );
      int index = hashIndex + 1;
      int powerIterator = 1;
      
      System.out.print( "Indices Probed: " + hashIndex );
      
      if( tableArray[ hashIndex ].compareTo( searchItem ) == 0 )
      {
      // hash index is returned, and no further probing is required
         System.out.println();
         return hashIndex;
      }
      
   // start probe based on linear probing
      if( probeFlag == LINEAR_PROBING )
      {
         while( index % tableSize != hashIndex )
         {
            System.out.print( ", " + index );
            if( tableArray[index].compareTo( searchItem ) == 0 )
            {
               System.out.println();
               return index;
            }
            index++;
         }
      }
      
   // otherwise, start quadratic probing
      else
      {
         while( index < tableSize )
         {
            System.out.print( ", " + index );
            if( tableArray[index].compareTo( searchItem ) == 0 )
            {
               System.out.println();
               return index;
            }
            powerIterator++;
            index += toPower(powerIterator, 2);
         }
         while( index % tableSize < hashIndex )
         {
            System.out.print( ", " + index % tableSize);
            if( tableArray[index % tableSize].compareTo( searchItem ) == 0 )
            {
               System.out.println();
               return index % tableSize;
            }
            powerIterator++;
            index += toPower(powerIterator, 2);
         }
      }
      
      return ITEM_NOT_FOUND;
   }
   
/**
 * Generate Hash
 * <p>
 * Methods converts StateDataClass hash value to index for use in hash table by 
 * summing the Unicode/ASCII values of all letters in the state name; then finds 
 * the index
 * <p>
 * Dependencies: .charAt, .length
 * 
 * @param item StateDataClass object to be converted to a hash value
 * @return hash value
 */
   public int generateHash( StateDataClass item )
   {
      int uniSum = 0;
      int index = 0;
      while( index < item.state.length() )
      {
         uniSum += ( int )item.state.charAt( index );
         index++;
      }
      
      return uniSum % tableSize;
   }
   
/**
 * Remove Item
 * <p>
 * Removes item from hash table
 * 
 * @param toBeRemoved - StateDataClass object used for requesting data uses 
 * findItemIndex
 * @return object removed, or null if not found
 */
   public StateDataClass removeItem( StateDataClass toBeRemoved )
   {
      int removeIndex = findItemIndex( toBeRemoved );
      if( removeIndex != ITEM_NOT_FOUND )
      {
         StateDataClass removed = tableArray[ removeIndex ];
         tableArray[ removeIndex ] = null;
         return removed;
      }
      
      return null;
   }
   
/**
 * Show Hash Table Status
 * <p>
 * traverses through all array bins, finds min and max number of contiguous 
 * elements, and number of empty nodes; also shows table loading
 * <p>
 * NOTE: Generates string of used and unused bins in addition to displaying 
 * results on screen
 * 
 * @return String result of hash table analysis
 */
   public String showHashTableStatus()
   {
      int index = 0;
      int currentContiguous = 0;
      int minContiguous = 0;
      int maxContiguous = 0;
      int numEmpty = 0;
      String result = "";
      
      System.out.print( "\nHash Table Status: " );
      
   // loop through the array for analysis
      while( index < tableSize )
      {
      // the bin contains data
         if( tableArray[index] != null )
         {
         // display 'D'
            System.out.print('D');
            result += "D";
         
            currentContiguous++;
            index++;
         }
      // the bin is empty
         else
         {
         // display 'N'
            System.out.print('N');
            result += "N";
            
         // update min and maxes
            if( minContiguous == 0 )
            {
               minContiguous = currentContiguous;
               maxContiguous = currentContiguous;
            }
            else
            {
               if( currentContiguous < minContiguous )
               {
                  minContiguous = currentContiguous;
               }
               
               if( currentContiguous > maxContiguous )
               {
                  maxContiguous = currentContiguous;
               }
            }
            currentContiguous = 0;
            index++;
         }
      }
      
   // final check for min and max at the end of the array
      if( currentContiguous > maxContiguous )
      {
         maxContiguous = currentContiguous;
      }
      if( currentContiguous < minContiguous )
      {
         minContiguous = currentContiguous;
      }
      
   // print diagnostics
      System.out.println();
      System.out.println( "     Minimum contiguous bins: " + minContiguous );
      System.out.println( "     Maximum contiguous bins: " + maxContiguous );
      System.out.println( "        Number of empty bins: " + numEmpty );
      System.out.println();
      
   // array dump
      System.out.println( "Array Dump:" );
      index = 0;
      while( index < tableSize )
      {
         if( tableArray[index] != null )
         {
            System.out.println( tableArray[index].toString() );
         }
         else
         {
            System.out.println( "null" );
         }
         index++;
      }
      
   // return string that is the result of the array analysis
      return result; 
   }
   
/**
 * To Power
 * <p>
 * Local recursive method to calculate exponentiation with positive integers
 * 
 * @param base - base of exponentiation
 * @param exponent - exponent of exponentiation
 * @return result of calculation
 */
   private int toPower( int base, int exponent )
   {
      int result = 1;
      if( exponent > 0 )
      {
         result *= base * toPower( base, exponent - 1 );
      }
      
      return result;
   }
}
