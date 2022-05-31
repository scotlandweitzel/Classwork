package p1_package;

/**
 * Class for managing sets of integers,
 * has capacity to generate various sets
 * 
 * @author Michael Leverington
 *
 */
public class SetClass 
   {
    /**
     * constant with default array capacity
     */
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
    
    /**
     * integer array for data
     */
    int[] setArray;
    
    /**
     * capacity of array
     */
    int arrayCapacity;
    
    /**
     * number of values in array
     */
    int arraySize;
    
    /**
     * constants for specifying set data
     */
    public static final int INCREMENTED = 101;
    public static final int ODD = 102;
    public static final int EVEN = 103;
    public static final int PRIME = 104;
    
    /**
     * Default constructor
     * <p>
     * Initializes set array but sets power set array to null
     */
    public SetClass()
       {
       // 1. Initialize set object
          arraySize = 0;
          arrayCapacity = DEFAULT_ARRAY_CAPACITY;
          setArray = new int[arrayCapacity];
          
       }

    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array but sets power set array to null
     * 
     * @param initialCapacity integer that specifies array capacity
     */
    public SetClass( int initialCapacity )
       {
       // 1. Initialize set object to specified capacity
          arraySize = 0;
          arrayCapacity = initialCapacity;
          setArray = new int[arrayCapacity];
          
       }

    /**
     * Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update 
     * of power set array
     * 
     * @param copied SetClass object to be copied
     */
    public SetClass( SetClass copied)
       {
       // 1. Initialize index variable to loop through set to be copied
          int index = 0;
          
       // 2. Initialize size and capacity for the new set
          arraySize = copied.arraySize;
          arrayCapacity = copied.arrayCapacity;
          setArray = new int[arrayCapacity];
          
       // 3. Copy each element of the copy array into the new array
          while( index < copied.arraySize )
          {
             setArray[index] = copied.setArray[index];
             index++;
          }
          
       }

    /**
     * Adds integer to set
     * <p>
     * increases capacity using checkForResize if array is full
     * <p>
     * does not allow duplicate values in set
     * 
     * @param item integer value to be added to set
     */
    public void addItem( int item )
       {
       // 1. Check to see if set capacity needs to be resized
          checkForResize();
          
       // 2. Initialize variables
          int index = 0;
          boolean inSet = false;
          
       // 3. Check if item to be added is already in the set
          while( !inSet & index < arraySize)
          {
             if( setArray[index] == item )
             {
                inSet = true;
             }
             index++;
          }
       
       // 4. Add item to set if item is not already in the set
          if( !inSet )
          {
             setArray[arraySize] = item;
             arraySize++;
          }
          
       }

    /**
     * Local function tests for resize of the set array
     * <p>
     * If array needs to be resized, array capacity is doubled;
     * otherwise, no change
     * 
     * @return boolean report that resize was conducted
     */
    private boolean checkForResize()
       {
       // 1. Instantly return false if array size is less than the capacity
          if( arraySize < arrayCapacity )
          {
             return false;
          }
          
       // 2. Initialize index to loop through old array
          int index = 0;
          
       // 3. Create new array with double the capacity of the old one
          arrayCapacity = arrayCapacity * 2;
          int[] newArray = new int[arrayCapacity];
          
       // 4. Copy each element of the old array into the new one
          while( index < arraySize )
          {
             newArray[index] = setArray[index];
             index++;
          }
          
       // 5. Set the old array to the new array
          setArray = newArray;
          return true;

       }
    
    /**
     * Returns the intersection of THIS set and the given other set
     * 
     * @param other SetClass data with which intersection is found
     * 
     * @return SetClass object with intersection of two sets
     */
    public SetClass findIntersection( SetClass other )
       {
       // 1. Create intersection set
          SetClass intersectionSet = new SetClass();
          
       // 2. Initialize variables
          int index = 0;
             
       // 3. Loop through both sets to create intersection set
          while(index < arraySize )
          {
             if( other.hasElement( setArray[index] ) )
             {
                intersectionSet.addItem( setArray[index] );
             }
             index++;
          }
             
       // 4. Return intersection set
          return intersectionSet;
          
       }
    
    /**
     * Returns the union of THIS set and the given other set
     * 
     * @param other SetClass data with which union is found
     * 
     * @return SetClass object with union of two sets
     */
    public SetClass findUnion( SetClass other )
       {
       // 1. Create union set
          SetClass unionSet = new SetClass();
          
       // 2. Initialize variables
          int index = 0;
          
       // 3. Loop through both sets to create union set
          // loop through first set
          while( index < arraySize )
          {
             unionSet.addItem( setArray[index] );
             unionSet.addItem( other.setArray[index] );
             index++;
          }
     
       // 4. Return union set
          return unionSet;

       }
    
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting 
     * with THIS set
     * 
     * @param other SetClass object from which THIS SetClass items 
     * will be removed
     * 
     * @return SetClass object with data as specified
     */
    public SetClass findRelativeComplementOfThisSetIn( SetClass other )
       {
       // 1. Create an intersection set and the set for the relative complement
          SetClass intersectionSet = this.findIntersection( other );
          SetClass relCompSet = new SetClass( other );
          
       // 2. Initialize variables
          int index = 0;
          
       // 3. Remove elements of the intersection from the copied set
          while( index < intersectionSet.arraySize )
          {
             relCompSet.removeAtIndex( index );
             index++;
          }
          
       // 3. Return the relative complement set
          return relCompSet;
        
       }
    
    /**
     * Seeks and finds prime starting at given value
     * 
     * @param value integer value to start search for prime
     * 
     * @return next prime number
     */
    private int getNextPrime( int value )
       {
       // 1. Initialize variables
          int testVal = value;
          
       // 2. Increment test value until prime number is found
          while( !isPrime(testVal) )
          {
             testVal++;
          }
          
       // 3. Return the nearest prime number
          return testVal;

       }
    
    /**
     * Tests to indicate if integer value is one
     * of the set elements
     * 
     * @param testElement integer element to be found in set
     * 
     * @return boolean result of test
     */
    public boolean hasElement( int testElement )
       {
       // 1. Initialize variables
          int index = 0;
          
       // 2. Loop through set to compare the test element
          while( index < arraySize )
          {
             if( setArray[index] == testElement )
             {
                return true;
             }
             index++;
          }
          
       // 3. Return result
          return false;
          
       }
    
    /**
     * Tests for even, reports
     * 
     * @param value integer value to be tested
     * 
     * @return boolean result as specified
     */
    private boolean isEven( int value )
       {
          return ( value % 2 == 0 );
       }
    
    /**
     * Tests to indicate if set is subclass of another given set
     * 
     * @param other SetClass object to be tested
     * if THIS set is a subset of it
     * 
     * @return boolean result of test
     */
    public boolean isSubsetOf( SetClass other )
       {
       // 1. Initialize variables
          int index = 0;
          int matches = 0;
          
       // 2. Loop through both sets and compare each element of possible subset
          while( index < arraySize )
          {
             if( other.hasElement( setArray[index] ) )
             {
                matches++;
             }
             index++;
          }
          
       // 3. Return true if all elements match larger set
          return ( matches == arraySize );
        
       }
    
    /**
     * Removes value at given index;
     * moves all succeeding data down in array
     * 
     * @param indexToRemove integer index of element value to remove
     */
    private void removeAtIndex( int indexToRemove )
       {
       // 1. Initialize variables
          int index = indexToRemove;
          
       // 2. Remove item at index and update array
          while( index < arraySize - 1 )
          {
             setArray[index] = setArray[index + 1];
             index++;
          }
          arraySize--;
       }    
    
    /**
     * Removes value if it is found in set
     * 
     * @param valToRemove integer value to be removed
     * 
     * @return boolean result of operation success
     */
    public boolean removeValue( int valToRemove )
       {
       // 1. Initialize variables
          int index = 0;
       
       // 2. Loop through set to remove value, return true after operation
          while( index < arraySize )
          {
             if( setArray[index] == valToRemove )
             {
                removeAtIndex( index );
                return true;
             }
             index++;
          }
          
       // 3. Return false if value could not be found in the set
        return false;
        
       }
    
////////////////////////////////////////////////////////////////////////////////
// DO NOT MODIFY CODE BELOW THIS LINE (but you may use the methods)
////////////////////////////////////////////////////////////////////////////////
    /**
     * Tests to indicate if given integer value is prime
     * 
     * @param testVal integer value given
     * 
     * @return boolean result of test
     */
    private boolean isPrime( int testVal )
       {
        int modVal = 2;
        int testDivide = (int)( Math.ceil( Math.sqrt( (double)testVal ) ) );
        
        while( modVal <= testDivide ) 
           {
            if( testVal % modVal == 0 )
               {
                return false;
               }
            
            modVal++;
           }
        
        return true;
       }
    
    /**
     * Loads a number of specified integers to set
     * <p>
     * Characteristics may be odd, even, incremented, or prime
     * <p> 
     * Parameter four is only used with INCREMENTED
     * 
     * @param startValue integer value indicates starting value
     * 
     * @param numItems integer number of items to load
     * 
     * @param valueCharacteristic integer characteristic code 
     * (ODD, EVEN, INCREMENTED, PRIME )
     * 
     * @param incrementBy integer value used to specify increment
     * if INCREMENTED characteristic is set
     */
    public void loadItems( int startValue, int numItems, 
                                   int valueCharacteristic, int incrementBy )
       {
        int index, runningValue = startValue;
        
        if( valueCharacteristic == ODD || valueCharacteristic == EVEN )
           {
            if( valueCharacteristic == ODD && isEven( runningValue ) 
                || valueCharacteristic == EVEN && !isEven( runningValue ) )
               {
                runningValue++;
               }
            
            for( index = 0; index < numItems; index++ )
               {
                addItem( runningValue );
                
                runningValue += 2;
               }
           }
        
        else if( valueCharacteristic == PRIME )
           {
            // decrement by one for the first iteration
            runningValue--;
            
            for( index = 0; index < numItems; index++ )
               {
                runningValue = getNextPrime( runningValue + 1 );
                
                addItem( runningValue );
               }
           }
        
        else  // assume incremented
           {
           for( index = 0; index < numItems; index++ )
              {
               addItem( runningValue );
               
               runningValue += incrementBy;
              }
           }
       }

    /**
     * Provides list of set array elements
     * as comma-delimited string
     */
    @Override
    public String toString()
       {
        int index;
        String outString = "";
        
        for( index = 0; index < arraySize; index++ )
           {
            if( index > 0 )
               {
                outString += ", ";
               }
            
            outString += setArray[ index ];
           }
        
        return outString;        
       }
    
   }

    
