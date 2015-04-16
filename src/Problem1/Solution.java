package Problem1;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String lineToSort = br.readLine();
        
        // Break the input string up into individual elements
        String[] unsortedElements = lineToSort.split(" ");
        int numberOfElements = unsortedElements.length;
        
        // For recording which positions in the input had Integer values (the complement will be Strings)
        List<Integer> integerIndices = new LinkedList<Integer>();
        
        // Read the String and Integer values into their own Lists, which can be sorted independently
        List<String> stringValuesToSort = new LinkedList<String>();
        List<Integer> integerValuesToSort = new LinkedList<Integer>();
        
        String curRawElement;
        Integer integerElement;
        
        for (int i = 0 ; i < numberOfElements ; i++) {
            curRawElement = unsortedElements[i];
            
            // Determine if this is an Integer value or a String value
            try {
                integerElement = Integer.parseInt(curRawElement);
                
                integerIndices.add(i);
                
                integerValuesToSort.add(integerElement);
            }
            catch (NumberFormatException e) {
                stringValuesToSort.add(curRawElement);
            }    
        }
        
        // Sort the Integers and the Strings
        Collections.sort(integerValuesToSort);
        Collections.sort(stringValuesToSort);
        
        int curIntegerValueIndex = 0;
        int curStringValueIndex = 0;
        
        // Print out the sorted list in the appropriate format
        for (int i = 0 ; i < numberOfElements ; i++) {
            // Check if this position in the List should be taken from the Integers List
            if (!integerIndices.isEmpty() && (curIntegerValueIndex < integerIndices.size()) && (integerIndices.get(curIntegerValueIndex) == i)) {
                 
                // Print out the current position in the sorted Integers List
                System.out.print(integerValuesToSort.get(curIntegerValueIndex));
                
                // Go to the next Integer List position
                curIntegerValueIndex++;
            }
            // Otherwise, this position needs to be a String
            else {
                
                // Print out the current position in the sorted String List
                System.out.print(stringValuesToSort.get(curStringValueIndex));
                
                // Go to the next String List position
                curStringValueIndex++;
            }
            
            System.out.print(" ");
        }
    }
}