package Problem2;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read in the first line of the input, which indicates how many data centers there are
        String numDataCentersLine;
        numDataCentersLine = br.readLine();
        
        int numDataCenters = Integer.parseInt(numDataCentersLine);
        
        // Keep track of which data centers have which data sets
        Map<Integer, Set<Integer>> dataCenterToCurrentDataSets = new HashMap<Integer, Set<Integer>>();
        // Keep track of which data sets are at each data center
        Map<Integer, Set<Integer>> currentDataSetToCurrentDataCenters = new HashMap<Integer, Set<Integer>>();
        
        Set<Integer> allDataSetIds = new HashSet<Integer>();
        
        int curDataCenterId;
        
        String curDataSetLine;
        String[] curDataSetLineSplit;
        
        String curNumDataSetString;
        int curNumDataSets;
        
        String curDataSetIdString;
        int curDataSetId;
        
        Set<Integer> curDataSetsAtDataCenter;
        Set<Integer> curDataCentersWithDataSet;
        
        // Loop over the number of data centers
        for (int i = 0 ; i < numDataCenters ; i++) {
            // Associate this data center with its appropriate identifier
            curDataCenterId = i+1;
            
            curDataSetLine = br.readLine();
            curDataSetLineSplit = curDataSetLine.split(" ");
            
            curNumDataSetString = curDataSetLineSplit[0];
            curNumDataSets = Integer.parseInt(curNumDataSetString);
            
            // Log an empty data center
            if (curNumDataSets == 0) {
                dataCenterToCurrentDataSets.put(curDataCenterId, new HashSet<Integer>());
            }
                            
            // Loop over the number of data sets at this data center
            for (int j = 0 ; j < curNumDataSets ; j++) {
                curDataSetIdString = curDataSetLineSplit[j+1];
                curDataSetId = Integer.parseInt(curDataSetIdString);
                
                allDataSetIds.add(curDataSetId);
                
                // Associate this data center with this data set
                curDataSetsAtDataCenter = dataCenterToCurrentDataSets.get(curDataCenterId);
                
                if (curDataSetsAtDataCenter == null) {
                    curDataSetsAtDataCenter = new HashSet<Integer>();
                    
                    dataCenterToCurrentDataSets.put(curDataCenterId, curDataSetsAtDataCenter);
                }
                
                curDataSetsAtDataCenter.add(curDataSetId);
                
                // Associate this data set with this data center
                curDataCentersWithDataSet = currentDataSetToCurrentDataCenters.get(curDataSetId);
                
                if (curDataCentersWithDataSet == null) {
                    curDataCentersWithDataSet = new HashSet<Integer>();
                    
                    currentDataSetToCurrentDataCenters.put(curDataSetId, curDataCentersWithDataSet);
                }
                
                curDataCentersWithDataSet.add(curDataCenterId);
            }
        }
        
        // For each data center, figure out what data sets it is missing and then find a source for one of them
        Set<String> copyInstructions = new HashSet<String>();
        
        StringBuffer curCopyInstructionBuffer;
        int curDataSetIdToBeCopied;
        int curSourceDataCenterId;
        int curDestinationDataCenterId;
        String curCopyInstruction;
        
        Set<Integer> curMissingDataSetIds;
        
        Set<Integer> curDataCentersWithMissingDataSet;
        
        for (Integer curDataCenterIdForCopyInstructions : dataCenterToCurrentDataSets.keySet()) {
            curDataSetsAtDataCenter = dataCenterToCurrentDataSets.get(curDataCenterIdForCopyInstructions);
            
            curMissingDataSetIds = new HashSet<Integer>(allDataSetIds);
            curMissingDataSetIds.removeAll(curDataSetsAtDataCenter);
            
            curDestinationDataCenterId = curDataCenterIdForCopyInstructions;
            
            for (Integer curMissingDataSetId : curMissingDataSetIds) {
                curDataSetIdToBeCopied = curMissingDataSetId;
                
                // Find the data centers that have the missing data set
                curDataCentersWithMissingDataSet = currentDataSetToCurrentDataCenters.get(curMissingDataSetId);
                
                curSourceDataCenterId = curDataCentersWithMissingDataSet.iterator().next();
               
                curCopyInstructionBuffer = new StringBuffer();
                
                curCopyInstructionBuffer.append(curDataSetIdToBeCopied);
                curCopyInstructionBuffer.append(" ");
                curCopyInstructionBuffer.append(curSourceDataCenterId);
                curCopyInstructionBuffer.append(" ");
                curCopyInstructionBuffer.append(curDestinationDataCenterId);
                
                curCopyInstruction = curCopyInstructionBuffer.toString();
                
                copyInstructions.add(curCopyInstruction);
            }
        }
        
        // Print out the copy instructions
        for (String curCopyInstructionToPrint : copyInstructions) {
            System.out.println(curCopyInstructionToPrint);
        }
        
        System.out.print("done");
    }
}