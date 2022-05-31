package p8_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BST_ClassMain
   {
    public static final char COMMA = ',';
    public static final char SEMICOLON = ';';
    public static final char SPACE = ' ';
    public static final char MINUS_SIGN = '-';

    public static final int MAX_INPUT_CHARS = 80;
    public static final int EOF_MARKER = -1;

    private static FileReader fileIn;
    private static final boolean SHOW_INPUT = false;
    
    
      public static void main(String[] args)
         {
          StateData_BST_Class testClass, copiedClass;

          testClass = uploadData( "inData.txt" );
          
          System.out.println( "\nInorder Display:" );
          
          testClass.displayTree( StateData_BST_Class.IN_TRAVERSE );
          
          System.out.println( "\nPreorder Display:" );
          
          testClass.displayTree( StateData_BST_Class.PRE_TRAVERSE );
          
          System.out.println( "\nPostorder Display:" );
          
          testClass.displayTree( StateData_BST_Class.POST_TRAVERSE );

          System.out.println( "\nRemoving North Carolina" );
          
          removeItem( testClass, "North Carolina" );
          
          System.out.println( "\nInorder Display:" );
          
          testClass.displayTree( StateData_BST_Class.IN_TRAVERSE );
          
          System.out.println( "\nRemoving Alabama" );

          removeItem( testClass, "Alabama" );
          
          System.out.println( "\nInorder Display:" );
          
          testClass.displayTree( StateData_BST_Class.IN_TRAVERSE );
          
          System.out.println( "\nRemoving Wyoming" );

          removeItem( testClass, "Wyoming" );
          
          System.out.println( "\nInorder Display:" );
          
          testClass.displayTree( StateData_BST_Class.IN_TRAVERSE );
          
          System.out.println( "\nRunning Copy Constructor" );

          copiedClass = new StateData_BST_Class( testClass );
          
          System.out.println( "\nCopy Constructor Test:" );
          
          copiedClass.displayTree( StateData_BST_Class.IN_TRAVERSE );          
         }

    public static StateDataClass removeItem( StateData_BST_Class bst, 
                                                              String stateName )
       {
        return bst.removeItem( new StateDataClass( stateName, "", 
                                                               0, 0.0, 0, 0 ) );
       }
    
    public static StateDataClass searchForItem( StateData_BST_Class bst, 
                                                              String stateName )
       {
        return bst.search( new StateDataClass( stateName, "", 0, 0.0, 0, 0 ) );
       }
   
      /**
       * uploads data from requested file
       * 
       * @param fileName name of file to access
       * 
       * @return Boolean result of data upload
       */
      private static StateData_BST_Class uploadData( String fileName )
         {
          String stateName, stateInit;
          int numInst, lowTemp, highTemp;
          double avgTemp;
          StateDataClass dataItem;
          StateData_BST_Class newData = new StateData_BST_Class();
          
          try
             {
              // Open FileReader 
              fileIn = new FileReader( fileName );
              
              // get first line - state name
              stateName = getALine( MAX_INPUT_CHARS, COMMA );
              
              while( compareStrings( stateName, "EndOfFile" ) != 0 )
                 {
                  // get state initials
                  stateInit = getALine( MAX_INPUT_CHARS, COMMA );
                  
                  // get number of institutions
                  numInst = getAnInt( MAX_INPUT_CHARS );
                  
                  // get average temperature
                  avgTemp = getADouble( MAX_INPUT_CHARS );
                  
                  // get lowest temperature
                  lowTemp = getAnInt( MAX_INPUT_CHARS );
                  
                  // get highest temperature
                  highTemp = getAnInt( MAX_INPUT_CHARS );
                  
                  // add data to StateDataClass, and then to tree
                  dataItem = new StateDataClass( stateName, stateInit, 
                                        numInst, avgTemp, lowTemp, highTemp );
                 
                  newData.insert( dataItem );
                  
                  if( SHOW_INPUT )
                     {
                      System.out.println( dataItem.toString() );
                     }
                  
                  stateName = getALine( MAX_INPUT_CHARS, COMMA );                  
                 }              
             }
         
          catch( FileNotFoundException fnfe )
             {
              System.out.println( "DATA ACCESS ERROR: Failure to open input file" );
             
              return null;
             }

          return newData;
         }
      
      /**
       * gets a string up to a maximum length or to specified delimiter
       * 
       * @param maxLength maximum length of input line
       * 
       * @param delimiterChar delimiter character to stop input
       * 
       * @return String captured from file
       */
      private static String getALine( int maxLength, char delimiterChar )
         {
          int inCharInt;
          int index = 0;
          String strBuffer = "";

          try
             {
              inCharInt = fileIn.read();

              // consume leading spaces
              while( index < maxLength && inCharInt <= (int)( SPACE )  )
                 {
                  if( inCharInt == EOF_MARKER )
                     {
                      return "EndOfFile";
                     }
                  
                  index++; 
                  
                  inCharInt = fileIn.read();
                 }
              
              while( index < maxLength && inCharInt != (int)( delimiterChar ) )
                 {   
                  if( inCharInt >= (int)( SPACE ) )
                     {
                      strBuffer += (char)( inCharInt );

                      index++;
                    }
                  
                  inCharInt = fileIn.read();             
                 }
              
              //inCharInt = fileIn.read();
             }
          
          catch( IOException ioe )
             {
              System.out.println( "INPUT ERROR: Failure to capture character" );
             
              strBuffer = "";
             }
             
          return strBuffer;
         }

      /**
       * gets an integer from the input string
       * 
       * @param maxLength maximum length of characters
       * input to capture the integer
       * 
       * @return integer captured from file
       */
      private static int getAnInt( int maxLength )
         {
          int inCharInt;
          int index = 0;
          String strBuffer = "";
          int intValue;
          boolean negativeFlag = false;

          try
             {
              inCharInt = fileIn.read();

              // clear space up to number
              while( index < maxLength && !charInString( (char)inCharInt, "0123456789+-" ) )
                 {
                  inCharInt = fileIn.read();
                  
                  index++;
                 }
              
              if( inCharInt == MINUS_SIGN )
                 {
                  negativeFlag = true;
                  
                  inCharInt = fileIn.read();
                 }

              while( charInString( (char)inCharInt, "0123456789" ) )
                 {   
                  strBuffer += (char)( inCharInt );

                  index++;
                  
                  inCharInt = fileIn.read();
                 }            
             }
          
          catch( IOException ioe )
             {
              System.out.println( "INPUT ERROR: Failure to capture character" );
             
              strBuffer = "";
             }
             
          intValue = Integer.parseInt( strBuffer );
          
          if( negativeFlag )
             {
              intValue *= -1;
             }
          
          return intValue;
         }

      /**
       * gets an integer from the input string
       * 
       * @param maxLength maximum length of characters
       * input to capture the integer
       * 
       * @return integer captured from file
       */
      private static double getADouble( int maxLength )
         {
          int inCharInt;
          int index = 0;
          String strBuffer = "";
          boolean negativeFlag = false;
          double doubleValue;

          try
             {
              inCharInt = fileIn.read();

              // clear space up to number
              while( index < maxLength && !charInString( (char)inCharInt, ".0123456789+-" ) )
                 {
                  inCharInt = fileIn.read();
                  
                  index++;
                 }

              if( inCharInt == MINUS_SIGN )
                 {
                  negativeFlag = true;
                  
                  inCharInt = fileIn.read();
                 }

              while( charInString( (char)inCharInt, ".0123456789" ) )
                 {   
                  strBuffer  += (char)( inCharInt );

                  index++;
                  
                  inCharInt = fileIn.read();
                 }            
             }
          
          catch( IOException ioe )
             {
              System.out.println( "INPUT ERROR: Failure to capture character" );
             
              strBuffer = "";
             }
             
          doubleValue = Double.parseDouble( strBuffer );
          
          if( negativeFlag )
             {
              doubleValue *= -1;
             }
          
          return doubleValue;
         }

      /**
       * compares two strings without consideration for case
       * 
       * @param oneString one of the strings to be tested
       * 
       * @param otherString other string to be tested
       * 
       * @return first greater than second: greater than zero;
       * first equal to second: equals zero
       * first less than second: less than zero
       */
      public static int compareStrings( String oneString, String otherString )
         {
         int difference, index = 0;

         while( index < oneString.length() && index < otherString.length() )
           {
            difference = toLower( oneString.charAt( index ) ) 
                         - toLower( otherString.charAt( index ) );

            if( difference != 0 )
               {
                return difference;
               }

            index++;
           }

          return oneString.length() - otherString.length();
         }        
      
      /**
       * tests and reports if a character is found in a given string
       * 
       * @param testChar character to be tested against the string
       * 
       * @param testString string within which the character is tested
       * 
       * @return Boolean result of test
       */
      private static boolean charInString( char testChar, String testString )
         {
          int index;
          
          for( index = 0; index < testString.length(); index++ )
             {
              if( testChar == testString.charAt( index ) )
                 {
                  return true;
                 }
             }
          
          return false;
         }
      
      /**
       * changes upper case letter to a lower case letter
       * 
       * @param inChar letter to be tested and potentially modified
       * 
       * @return lower case letter if upper case letter is input;
       * otherwise character is returned unchanged
       */
      private static char toLower( char inChar )
         {
          if( inChar >= 'A' && inChar <= 'Z' )
             {
              return (char)( inChar - 'A' + 'a' );
             }
          
          return inChar;
         }
      
   }
