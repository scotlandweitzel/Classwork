package p8_package;

/**
 * Binary Search Tree (BST) class for managing StateDataClass data
 * 
 * @author Scotland Weitzel
 *
 */

public class StateData_BST_Class
{
/**
 * Member Data
 */
   private StateDataClass rootNode;
   private int displayCounter;
   
/**
 * Class Constants
 */
   public static final int PRE_TRAVERSE = 101;
   public static final int IN_TRAVERSE = 102;
   public static final int POST_TRAVERSE = 103;

/**
 * Default Constructor
 */
   public StateData_BST_Class()
   {
      rootNode = null;
      displayCounter = 0;
   }
   
/**
 * Copy Constructor
 */
   public StateData_BST_Class( StateData_BST_Class copied )
   {
   // capture copied root node and store in working reference
      StateDataClass wkgRef = copied.rootNode;
      displayCounter = 0;
      
   // start copying
      rootNode = copyConstructorHelper( wkgRef );
   }
   

/**
 * Clear Tree
 * <p>
 * Clears the tree
 */
   public void clearTree()
   {
      rootNode = null;
      displayCounter = 0;
   }
   
/**
 * Compare Strings
 * <p>
 * If the first string parameter is greater than the second, this method 
 * returns a positive value; if the first string parameter is less than the 
 * second, it returns a negative value; if they are equal, it returns zero
 * 
 * @param one String for comparison
 * @param other String to compare to the first string
 * @return result of comparison as described above
 */
   public int compareStrings( String one, String other )
   {
      return one.compareTo( other );
   }
   
/**
 * Copy Constructor Helper
 * <p>
 * Recursively copies nodes using pre-order traversal
 * 
 * @param wkgRef - current working StateDataClass reference in recursion
 * @return reference to link to StateDataClass node above
 */
   private StateDataClass copyConstructorHelper( StateDataClass wkgRef )
   {
   // check if the working reference is null
      if( wkgRef != null )
      {
      // create the new node object
         StateDataClass newNode = new StateDataClass( wkgRef );
      // copy the left child node
         newNode.leftChildRef = copyConstructorHelper( wkgRef.leftChildRef );
      // copy the right child node
         newNode.rightChildRef = copyConstructorHelper( wkgRef.rightChildRef );
      // return the node
         return newNode;
      }
   
   // an empty working reference returns null
      else
      {
         return null;
      }
   }
   
/**
 * Display In Order
 * <p>
 * Provides an inOrder traversal action using recursion
 * 
 * @param wkgRef - current working StateDataClass reference in recursion
 */
   private void displayInOrder( StateDataClass wkgRef )
   {
   // check left side
      if( wkgRef.leftChildRef != null )
      {
         displayInOrder( wkgRef.leftChildRef );
      }
      
   // print node at this point
      displayCounter++;
   // print the counter
      System.out.format( "%2d: ", displayCounter);
   // print the state and end line
      System.out.println( wkgRef.toString() );
      
   // check right side
      if( wkgRef.rightChildRef != null )
      {
         displayInOrder( wkgRef.rightChildRef );
      }
   }
   
/**
 * Display Post Order
 * <p>
 * Provides a postOrder traversal action using recursion
 * 
 * @param wkgRef - current working StateDataClass reference in recursion
 */
   private void displayPostOrder( StateDataClass wkgRef )
   {
   // check left side
      if( wkgRef.leftChildRef != null )
      {
         displayPostOrder( wkgRef.leftChildRef );
      }
      
   // check right side
      if( wkgRef.rightChildRef != null )
      {
         displayPostOrder( wkgRef.rightChildRef );
      }
      
   // if no nodes exist on either side, found state data to print
      displayCounter++;
   // print the counter
      System.out.format( "%2d: ", displayCounter);
   // print state and end line
      System.out.println( wkgRef.toString() );
   }
   
/**
 * Display Pre Order
 * <p>
 * Provides a preOrder traversal action using recursion
 * 
 * @param wkgRef - current working StateDataClass reference in recursion
 */
   private void displayPreOrder( StateDataClass wkgRef )
   {
   // printing node at this point
      displayCounter++;
   // print the counter
      System.out.format( "%2d: ", displayCounter);
   // print state and end line
      System.out.println( wkgRef.toString() );
      
   // check left side
      if( wkgRef.leftChildRef != null )
      {
         displayPreOrder( wkgRef.leftChildRef );
      }
      
   // check right side
      if( wkgRef.rightChildRef != null )
      {
         displayPreOrder( wkgRef.rightChildRef );
      }
   }
   
/**
 * Display Tree
 * <p>
 * Provides user with three ways to display the Binary Search Tree data
 * 
 * @param traverseCode - integer code for selecting BST traversal method, 
 * accepts PRE_TRAVERSE, IN_TRAVERSE, and POST_TRAVERSE
 */
   public void displayTree( int traverseCode )
   {
   // reset counter to zero
      displayCounter = 0;
      
   // create working reference
      StateDataClass wkgRef = rootNode;
      
   // print tree if it is not empty
      if( !isEmpty() )
      {
      // displays tree with pre order traversal
         if( traverseCode == PRE_TRAVERSE )
         {
            displayPreOrder( wkgRef );
         }
         
      // displays tree with post order traversal
         else if( traverseCode == POST_TRAVERSE )
         {
            displayPostOrder( wkgRef );
         }
         
      // displays tree with in order traversal
         else
         {
            displayInOrder( wkgRef );
         }
      }
      
   }
   
/**
 * Insert
 * <p>
 * Insert method for the Binary Search Tree
 * <p>
 * Note: calls the insert helper to implement all the data insertions
 * 
 * @param inData - StateDataClass data to be added to the Binary Search Tree
 */
   public void insert( StateDataClass inData )
   {
   // create working reference
      StateDataClass wkgRef = rootNode;
     
   // insert operation if list is not empty
      if( !isEmpty() )
      {
         insertHelper( wkgRef, inData );
      }
      else
      {
         rootNode = inData;
      }
      
   // 
   }
   
/**
 * Insert Helper
 * <p>
 * Recursive insert helper method for Binary Search Tree insert action
 * <p>
 * Adds new StateDataClass node to left or right of the current tree; if node 
 * is already in tree, node is updated with the incoming data
 * 
 * @param wkgRef - current working reference at the current recursion level
 * @param inData - StateDataClass item to be added to the Binary Search Tree
 * @return StateDataClass reference to current node insertion operation
 */
   private StateDataClass insertHelper( StateDataClass wkgRef, 
                                        StateDataClass inData )
   {
   // inData is greater than the current node
      if( compareStrings( inData.toString(), wkgRef.toString() ) > 0 )
      {
      // add data to right side of current node if there is no right child
         if( wkgRef.rightChildRef == null )
         {
            wkgRef.rightChildRef = inData;
         }
      // otherwise attempt operation on the right child
         else
         {
            insertHelper( wkgRef.rightChildRef, inData );
         }
      }
      
   // inData is less than the current node
      else if( compareStrings( inData.toString(), wkgRef.toString() ) < 0 )
      {
      // add data to the left side of current node if there is no left child
         if( wkgRef.leftChildRef == null )
         {
            wkgRef.leftChildRef = inData;
         }
      // otherwise attempt operation on left child
         else
         {
            insertHelper( wkgRef.leftChildRef, inData );
         }
      }
      
   // otherwise, inData is equal to the current node and so the node is 
   // updated
      else
      {
         wkgRef.setData( inData );
      }
      
   // return reference to current node
      return wkgRef;
   }
   
/**
 * Is Empty
 * <p>
 * Tests for an empty tree
 * 
 * @return boolean result of test
 */
   public boolean isEmpty()
   {
      return rootNode == null;
   }
   
/**
 * Remove From Min
 * <p>
 * Searches tree from given working reference to minimum value found below it 
 * in farthest left node, stores data value found, unlinks the found node, 
 * links the parent node's left node to the child's right node, and returns 
 * the child/found node to the calling method
 * 
 * @param parentRef - reference to currrent StateDataClass node
 * @param childRef - reference to child StateDataClass node to be tested and/or 
 * removed
 * @return StateDataClass reference containing removed StateDataClass node
 */
   private StateDataClass removeFromMin( StateDataClass parentRef, 
                                         StateDataClass childRef )
   {
   // recurse until least value is reached
      if( childRef.leftChildRef != null )
      {
         return removeFromMin( childRef, 
                               childRef.leftChildRef );
      }

   // min val found, unlink found node and return its reference
      parentRef.leftChildRef = childRef.rightChildRef;
      return childRef;
   
   }
   
/**
 * Remove Item
 * <p>
 * Removes data StateDataClass node from tree using given key
 * <p>
 * Note: uses remove helper method
 * <p>
 * Note: uses search initially to get value, if it is in tree; if the value is 
 * found, remove helper method is called, otherwise this returns null
 * 
 * @param outData - StateDataClass node that includes the necessary key
 * @return result of removal
 */
   public StateDataClass removeItem( StateDataClass outData )
   {
   // create working reference to traverse BST
      StateDataClass wkgRef = rootNode;
   // create reference for found node to remove
      StateDataClass result = search( outData );
      
   // if the outData is found, continue with helper method to remove node
      if( result != null )
      {
      // save result as a new node
         result = new StateDataClass( result );
      // run helper method on root node
         rootNode = removeItemHelper( wkgRef, outData );
      }
      
   // return the result
      return result;
   }
   
/**
 * Remove Item Helper
 * <p>
 * Remove helper for Binary Search Tree remove action
 * <p>
 * Note: Recursive method returns updated local root to main tree linakge
 * <p>
 * Note: uses removeFromMin method
 * 
 * @param wkgRef - StateDataClass node reference at the current recursion level
 * @param outData - StateDataClass item that includes the necessary key
 * @return StateDataClass reference result of remove helper action
 */
   private StateDataClass removeItemHelper( StateDataClass wkgRef, 
                                            StateDataClass outData )
   {
   // if outData is greater than the current node, go right
      if( compareStrings( outData.toString(), wkgRef.toString() ) > 0 )   
      {
         wkgRef.rightChildRef = removeItemHelper( wkgRef.rightChildRef, 
                                                  outData );
      }
      
   // if outData is less than current node, go left
      else if ( compareStrings( outData.toString(), wkgRef.toString() ) < 0 )
      {
         wkgRef.leftChildRef = removeItemHelper( wkgRef.leftChildRef, outData );
      }
      
   // otherwise, outData equals the current node, we are at the node to remove
      else
      {
      // no child exists
         if( wkgRef.rightChildRef == null && wkgRef.leftChildRef == null )
         {
            return null;
         }
         
       // one child exists
         else if( wkgRef.leftChildRef == null )
         {
         // left node is empty, return right child reference
            return wkgRef.rightChildRef;
         }
         else if( wkgRef.rightChildRef == null )
         {
         // right node is empty, return left child reference
            return wkgRef.leftChildRef;
         }
         
      // two children exist
         else
         {
         // overwrite current node with right node if right node has an empty
         // left node
            if( wkgRef.rightChildRef.leftChildRef == null )
            {
               wkgRef.setData( wkgRef.rightChildRef );
               wkgRef.rightChildRef = wkgRef.rightChildRef.rightChildRef;
            }
            
         // otherwise, determine the minimum value in right side of tree
            else
            {
            // find minimum node and save it in temp
               StateDataClass tempStorage = 
                     removeFromMin( wkgRef, wkgRef.rightChildRef );
            // save minimum into current node
               wkgRef.setData(tempStorage);
               
            // delete min value node
               wkgRef.rightChildRef = removeItemHelper( wkgRef.rightChildRef, 
                                                        tempStorage );
            }
         
         }
     
      }
      return wkgRef;
   }
   
/**
 * Search
 * <p>
 * Searches for data in Binary Search Tree given StateDataClass reference with 
 * necessary key
 * 
 * @param searchData - StateDataClass reference containing key
 * @return reference to found data, or null if not found
 */
   public StateDataClass search( StateDataClass searchData )
   {
   // create working reference
      StateDataClass wkgRef = rootNode;
      
   // operate search if the list is not empty
      if( !isEmpty() )
      {
         return searchHelper( wkgRef, searchData );
      }
      
   // the method returns null if the list is empty (nothing to search)
      else
      {
         return null;
      }
   }
   
/**
 * Search Helper
 * <p>
 * Helper method for recursive Binary Search Tree search action
 * 
 * @param wkgRef - StateDataClass tree node reference at the current recursion 
 * level
 * @param searchData - StateDataClass reference containing key
 * @return StateDataClass item found, or null if not found
 */
   private StateDataClass searchHelper( StateDataClass wkgRef, 
                                        StateDataClass searchData )
   {
   // return the working reference if it matches the search data
      if( compareStrings( searchData.toString(), wkgRef.toString() ) == 0 )
      {
         return wkgRef;
      }
      
   // otherwise traverse the list
      else
      {
      // check if there is a left child
         if( wkgRef.leftChildRef != null )
         {
            return searchHelper( wkgRef.leftChildRef, searchData );
         }
      // check if there is a right child
         else if( wkgRef.rightChildRef != null )
         {
            return searchHelper( wkgRef.rightChildRef, searchData );
         }
      }
   
   // null is returned as at this point, the search was unsuccessful
      return null;
   }
}
