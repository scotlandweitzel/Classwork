package p9_package;

/**
 * Binary Search Tree class with self-balancing attributes specifically using 
 * the Adelson-Velsky and Landis strategy
 * @author Scotland Weitzel
 */
public class AVL_TreeClass
{
/**
 * Class Constants
 */
   private final char NULL_CHAR = '0';
   private static final char DASH = '-';
   private static final char SPACE = ' ';
      
/**
 * Member Data
 */
   private boolean rowStartFlag;
   Node treeRoot;
   
/**
 * Binary Search Tree node class for managing character data within an AVL tree
 * @author dagongent1
 */
   private class Node
   {
   /**
    * Node member data
    */
      private char data;
      private Node leftChildRef;
      private Node rightChildRef;
      
   /**
    * Node Initialization Constructor 
    * 
    * @param inData - char quantity
    */
      public Node( char inData )
      {
         data = inData;
         leftChildRef = null;
         rightChildRef = null;
      }
      
   /**
    * Node Initialization Constructor for Child References
    * 
    * @param inData - char quantity
    * @param leftRef - reference for left child
    * @param rightRef - reference for right child
    */
      public Node( char inData, Node leftRef, 
                   Node rightRef )
      {
         data = inData;
         leftChildRef = leftRef;
         rightChildRef = rightRef;
      }
      
   /**
    * Node Copy Constructor
    * 
    * @param copied - Node object to be copied
    */
      public Node( Node copied )
      {
         data = copied.data;
         leftChildRef = null;
         rightChildRef = null;
      }
   }
   
/**
 * AVL Tree Class Default Constructor
 */
   public AVL_TreeClass()
   {
      treeRoot = null;
      rowStartFlag = false;
      
   }
   
/**
 * AVL Tree Class Copy Constructor
 * 
 * @param copied - AVL_TreeClass object to be copied
 */
   public AVL_TreeClass( AVL_TreeClass copied )
   {
   // capture root node to start copying
      Node wkgRef = copied.treeRoot;
      rowStartFlag = false;
      
   // copy contents of tree if the tree is not empty
      treeRoot = copyConstructorHelper( wkgRef );
   }
   
/**
 * Clear Tree
 * <p>
 * clears the tree
 */
   public void clearTree()
   {
      treeRoot = null;
   }
   
/**
 * Copy Constructor Helper
 * <p>
 * Recursive copy constructor helper
 * <p>
 * Note: Uses preorder strategy to copy nodes
 * 
 * @param wkgCopiedRef - Node reference at which method starts at each level of 
 * recursion
 * @return Node reference to link current node info to methods calling this 
 * method
 */
   private Node copyConstructorHelper( Node wkgCopiedRef )
   {
      if( wkgCopiedRef == null )
      {
         return null;
      }
   
   // create new node with passed working reference
      Node copiedNode = new Node(wkgCopiedRef);
      
      copiedNode.leftChildRef = 
            copyConstructorHelper( wkgCopiedRef.leftChildRef );
      copiedNode.rightChildRef = 
            copyConstructorHelper( wkgCopiedRef.rightChildRef );
      
      return copiedNode;
   }
   
/**
 * Display Chars
 * <p>
 * Local recursive method to display a specified number of a specified character
 * 
 * @param numChars - number of chars to display
 * @param outChar - character to display
 */
   private void displayChars( int numChars, char outChar )
   {
      if( numChars > 0 )
      {
         System.out.print( outChar );
         displayChars( numChars - 1, outChar );
      }
   }
   
/**
 * Find Tree Height
 * <p>
 * Provides tree height to the user
 * 
 * @return integer height of tree
 */
   public int findTreeHeight()
   {
      return getTreeHeight( treeRoot );
   }
   
/**
 * Get Balance Factor
 * <p>
 * Gets balance factor indicating if tree is unbalanced from given root down
 * 
 * @param wkgLocalRef - Node from which balance factor is found
 * @return integer balance factor
 */
   private int getBalanceFactor( Node wkgLocalRef )
   {
      int leftTreeHeight = 0;
      int rightTreeHeight = 0;
      
      if( wkgLocalRef == null )
      {
         return 0;
      }
      
   // calculate the heights of each subtree (left and right)
      if( wkgLocalRef.leftChildRef != null )
      {
         leftTreeHeight = getTreeHeight( wkgLocalRef.leftChildRef );
      }
      if( wkgLocalRef.rightChildRef != null )
      {
         rightTreeHeight = getTreeHeight( wkgLocalRef.rightChildRef );
      }
   // return the balance factor
      return leftTreeHeight - rightTreeHeight;
   }
   
/**
 * Get Max
 * <p>
 * Finds maximum of two given numbers
 * 
 * @param one - one of two values to be tested
 * @param other - the other value to be tested
 * @return greater of the two integers
 */
   private int getMax( int one, int other )
   {
      if( one > other )
      {
         return one;
      }
      
      return other;
   }
   
/**
 * Get Tree Height
 * <p>
 * Tree height helper method
 * 
 * @param wkgLocalRef - Node from which height is found
 * @return integer height of AVL tree
 */
   private int getTreeHeight( Node wkgLocalRef )
   {
      if( wkgLocalRef == null )
      {
         return 1;
      }
      
      int leftTreeHeight = 0;
      int rightTreeHeight = 0;
      
      if( wkgLocalRef.leftChildRef != null )
      {
         leftTreeHeight += getTreeHeight( wkgLocalRef.leftChildRef ) + 1;
      }
      
      if( wkgLocalRef.rightChildRef != null )
      {
         rightTreeHeight += getTreeHeight( wkgLocalRef.rightChildRef ) + 1;
      }
         
      return getMax( leftTreeHeight, rightTreeHeight );
   }
   
/**
 * In-Order Display
 * <p>
 * In order display of the AVL tree
 */
   public void inOrderDisplay()
   {
      inOrderDisplayHelper( treeRoot );
   }
   
/**
 * In-Order Display Helper
 * <p>
 * Provides in-order traversal action
 * <p>
 * NOTE TO GRADER: I had to switch to post-order to correctly display the tree
 * in a list
 * 
 * @param wkgLocalRef - Node tree root reference at the current recursion level
 */
   private void inOrderDisplayHelper( Node wkgLocalRef )
   {     
   // check left side
      if( wkgLocalRef.leftChildRef != null )
      {
         inOrderDisplayHelper( wkgLocalRef.leftChildRef );
      }
      
   // print node
      if( wkgLocalRef != null )
      {
         System.out.print( wkgLocalRef.data );
         System.out.print( SPACE );
      }
      
   // check right side
      if( wkgLocalRef.rightChildRef != null )
         {
            inOrderDisplayHelper( wkgLocalRef.rightChildRef );
         }
   }
   
/**
 * Insert
 * <p>
 * Insert method for the AVL tree
 * 
 * @param inData - char data to be added to the AVL tree
 */
   public void insert( char inData )
   {
      treeRoot = insertHelper( treeRoot, inData );
   }
   
/**
 * Insert Helper
 * <p>
 * Insert helper method for the AVL tree insert action
 * <p>
 * Note: Does not allow duplicate keys
 * 
 * @param wkgLocalRef - Node tree root reference at the current recursion level
 * @param inData - char item to be added to the AVL tree
 * @return Node reference to current AVL tree root
 */
   private Node insertHelper( Node wkgLocalRef, char inData )
   {  
   // check if working reference is empty
      if( wkgLocalRef == null )
      {
         return new Node( inData );
      }
      
   // check if current node is equal to the key
      if( wkgLocalRef.data == inData )
      {
         return wkgLocalRef;
      }
   
   // insert operation:
   // 1. insert to the right if inData is greater than the current node
      if( getMax(wkgLocalRef.data, inData) == inData )
      {
         wkgLocalRef.rightChildRef = 
               insertHelper( wkgLocalRef.rightChildRef, inData );
      }
      
   // 2. insert to the left if inData is less than the current node
      else if( getMax(wkgLocalRef.data, inData) == wkgLocalRef.data )
      {
         wkgLocalRef.leftChildRef = 
               insertHelper( wkgLocalRef.leftChildRef, inData );
      }
      
   // recalculate the balance factor after insertion
      int balanceFactor = getBalanceFactor( wkgLocalRef );
      
   // rotation possibilities
   // left rotation
      if( balanceFactor > 1 && inData < wkgLocalRef.leftChildRef.data )
      {
         return rotateRight( wkgLocalRef );
      }
      
   // right rotation
      else if( balanceFactor < -1 && inData > wkgLocalRef.rightChildRef.data )
      {
         return rotateLeft( wkgLocalRef );
      }
      
   // left right rotation
      else if( balanceFactor > 1 && inData > wkgLocalRef.leftChildRef.data )
      {
         wkgLocalRef.leftChildRef = rotateLeft( wkgLocalRef.leftChildRef );
         return rotateRight( wkgLocalRef );
      }
      
   // right left
      else if( balanceFactor < -1 && inData < wkgLocalRef.rightChildRef.data)
      {
         wkgLocalRef.rightChildRef = rotateRight( wkgLocalRef.rightChildRef );
         return rotateLeft( wkgLocalRef );
      }
      
      return wkgLocalRef;
   }
   
/**
 * Is Empty
 * <p>
 * Tests for an empty tree
 * @return Boolean result of test
 */
   public boolean isEmpty()
   {
      return treeRoot == null;
   }
   
/**
 * Rotate Left
 * <p>
 * Rotates local tree left or CCW
 * 
 * @param wkgLocalRef - reference of current item
 * @return Node resulting current root
 */
   private Node rotateLeft( Node wkgLocalRef )
   {
   // save the children
      Node newRoot =  wkgLocalRef.rightChildRef;
      Node tempNode = newRoot.leftChildRef;
      
   // rotate nodes to the left
      wkgLocalRef.leftChildRef = newRoot;
      newRoot.rightChildRef = tempNode;
      
   // return the root
      return newRoot;
   }
   
/**
 * Rotate Right
 * <p>
 * Rotates local tree right or CW
 * 
 * @param wkgLocalRef - reference of current item
 * @return Node resulting current root
 */
   private Node rotateRight( Node wkgLocalRef )
   {
   // save the children
      Node newRoot = wkgLocalRef.leftChildRef;
      Node tempNode = newRoot.rightChildRef;
      
   // rotate nodes to the right
      newRoot.rightChildRef = wkgLocalRef;
      wkgLocalRef.leftChildRef = tempNode;
      
   // return the root
      return newRoot;
   }
   
/**
 * Search
 * <p>
 * Searches for data in the AVL tree given char with necessary key
 * 
 * @param searchData - char item containing key
 * @return char reference to found data
 */
   public char search( char searchData )
   {
      return searchHelper( treeRoot, searchData );
   }
   
/**
 * Search Helper
 * <p>
 * Helper method for AVL tree search action
 * 
 * @param wkgLocalRef - Node tree root reference tat the current recursion level
 * @param searchData - char item containing key
 * @return char result of search
 */
   private char searchHelper( Node wkgLocalRef, char searchData )
   {
   // compare searchData key to current node
      
      if( wkgLocalRef.data == searchData )
      {
         return wkgLocalRef.data;
      }
   // check left node
      
      if( getMax( wkgLocalRef.data, searchData ) == searchData )
      {
         searchHelper( wkgLocalRef.rightChildRef, searchData );
      }
      
   // check right node
      else
      {
         searchHelper( wkgLocalRef.leftChildRef, searchData );
      }
      
   // if searching the tree finds no match, return NULL_CHAR
      return NULL_CHAR;
   }
   
/**
 * To Power
 * <p>
 * Local recursive method to calculate exponentiation with integers
 * 
 * @param base - base of exponentiation
 * @param exponent - exponent of exponentiation
 * @return result of exponentiation calculation
 */
   private int toPower( int base, int exponent )
   {
   // create local variable to store exponentiation calculation
      int result = 1;
      
   // check if passed exponent is zero, and exponentiate if its not zero
      if( exponent != 0 )
      {
         result *= base * toPower( base, exponent - 1 );
      }
      
   // end of exponentiation, return result
      return result;
      
   }
   
/**
 * [NOT ASSIGNED] Displays text-graphical representation of one level of tree
 * 
 * @param workingNode - node reference at current recursive level
 * @param nodeHeight - height of tree plus two for current height of nodes, 
 * including lowermost null children
 * @param displayLevel - level of tree at which the current line of display is 
 * to be presented
 * @param workingLevel - current level during recursive actions
 */
   private void displayAtTreeLevel( Node workingNode, int nodeHeight, 
                                    int displayLevel, int workingLevel )
   {
      char charOut = workingNode.data;
      
      if( workingLevel == displayLevel )
      {
         displayValue( charOut, nodeHeight, workingLevel );
         return;
      }
      
      if( workingNode.leftChildRef != null )
      {
         displayAtTreeLevel( workingNode.leftChildRef, nodeHeight, 
                             displayLevel, workingLevel + 1 );
      }
      else
      {
         displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
      }
      
      if( workingNode.rightChildRef != null )
      {
         displayAtTreeLevel( workingNode.rightChildRef, nodeHeight, 
                             displayLevel, workingLevel + 1 );
      }
      else
      {
         displayEmptyNodeSpaces( nodeHeight, displayLevel, workingLevel + 1 );
      }
   }
   
/**
 * [NOT ASSIGNED] Method that displays null or blank nodes for a tree at 
 * null locations
 * <p>
 * Note: used by displayAtTreeLevel
 * 
 * @param nodeHeight - height of tree plus two for current height of nodes, 
 * including lowermost null children
 * @param displayLevel - level of the tree at which the display will be applied
 * @param workingLevel - level of tree just below non-null node at which method 
 * is currently working
 */
   private void displayEmptyNodeSpaces( int nodeHeight, int displayLevel, 
                                        int workingLevel )
   {
      int nodesToDisplay = toPower( 2, displayLevel - workingLevel );
      char charOut = SPACE;
      
      if( displayLevel == workingLevel )
      {
         charOut = DASH;
      }
      
      while( nodesToDisplay > 0 )
      {
         displayValue( charOut, nodeHeight, displayLevel );
         nodesToDisplay--;
      }
   }
   
/**
 * [NOT ASSIGNED] Displays text-graphical representation of the AVL tree
 */
   public void displayTreeStructure()
   {
      int displayLevel, nodeHeight = getTreeHeight( treeRoot ) + 2;
      int workingLevel = 1;
      
      if( treeRoot != null )
      {
         for( displayLevel = 1; displayLevel <= nodeHeight; displayLevel++ )
         {
            rowStartFlag = true;
            displayAtTreeLevel( treeRoot, nodeHeight, displayLevel, 
                                workingLevel );
            System.out.println();
         }
      }
      else
      {
         System.out.println( "\nEmpty Tree - No Display" );
      }
   }
   
/**
 * [NOT ASSIGNED] Method used to display a character or color letter along with 
 * calculated leading spaces
 * <p>
 * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
 * 
 * @param data - data value to display, either letter or color data
 * @param nodeHeight - height of tree plus two for current height of nodes, 
 * including lowermost null children
 * @param workingLevel - current level during recursion
 */
   private void displayValue( char data, int nodeHeight, int workingLevel )
   {
      int leadingSpaces;
      
      if( rowStartFlag )
      {
         leadingSpaces = toPower( 2, nodeHeight - workingLevel );
         rowStartFlag = false;
      }
      else
      {
         leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
      }
      
      displayChars( leadingSpaces, SPACE );
      System.out.print( data );
   }
   
}
