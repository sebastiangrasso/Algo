package lcs;

import java.util.Arrays;
import java.util.Set;

public class LCS {
    
    /**
     * memoCheck is used to verify the state of your tabulation after
     * performing bottom-up and top-down DP. Make sure to set it after
     * calling either one of topDownLCS or bottomUpLCS to pass the tests!
     */
    public static int[][] memoCheck;
    
    // -----------------------------------------------
    // Shared Helper Methods
    // -----------------------------------------------
    
    public <Set> collectSolution(String rStr, int r, String cStr, int c, int[][] memo){
    
    //base case
    if (r = 0 || c = 0) {
    	return { "" };
    }
    
    
    }
    
    // [!] TODO: Add your shared helper methods here!
    

    // -----------------------------------------------
    // Bottom-Up LCS
    // -----------------------------------------------
    
    /**
     * Bottom-up dynamic programming approach to the LCS problem, which
     * solves larger and larger subproblems iterative using a tabular
     * memoization structure.
     * @param rStr The String found along the table's rows
     * @param cStr The String found along the table's cols
     * @return The longest common subsequence between rStr and cStr +
     *         [Side Effect] sets memoCheck to refer to table
     */
    public static Set<String> bottomUpLCS (String rStr, String cStr) {
        throw new UnsupportedOperationException();
    }
    
    // [!] TODO: Add any bottom-up specific helpers here!
    
    
    // -----------------------------------------------
    // Top-Down LCS
    // -----------------------------------------------
    
    /**
     * Top-down dynamic programming approach to the LCS problem, which
     * solves smaller and smaller subproblems recursively using a tabular
     * memoization structure.
     * @param rStr The String found along the table's rows
     * @param cStr The String found along the table's cols
     * @return The longest common subsequence between rStr and cStr +
     *         [Side Effect] sets memoCheck to refer to table  
     */
    public static Set<String> topDownLCS (String rStr, String cStr) {
    	  	 
        int[][] memo = new int [rStr.length()+1][cStr.length()+1];
    
    	int longestSubstring = lcsRecursiveHelper(rStr, rStr.length(), cStr, cStr.length(), memo);
    	memoCheck = memo;
    	
    	for (int[] x : memo)
    	{
    	   for (int y : x)
    	   {
    	        System.out.print(y + " ");
    	   }
    	   System.out.println();
    	}  	
        throw new UnsupportedOperationException();
    }
    
    /**
     * Completes the memoization table using top-down dynamic programming
     * @param rStr The String along the memoization table's rows
     * @param rInd The current letter's index in rStr
     * @param cStr The String along the memoization table's cols
     * @param cInd The current letter's index in cStr
     * @param memo The memoization table
     */
    static int lcsRecursiveHelper (String rStr, int rInd, String cStr, int cInd, int[][] memo) {
        
    	// base case  
        if (rInd == 0 || cInd == 0) { 
            return 0; 
        } 
  
        // check if its been computed  
        if (memo[rInd][cInd] != 0) { 
            return memo[rInd][cInd]; 
        } 
  
        // if chars match, store the char and recurse on diagonal  
        if (rStr.charAt(rInd - 1) == cStr.charAt(cInd - 1)) { 
            memo[rInd][cInd] = 1 + lcsRecursiveHelper(rStr, rInd - 1, cStr, cInd - 1, memo); 
  
            return memo[rInd][cInd]; 
        } 
        //if chars don't match, save solution for avoiding future work
        else { 
  
            memo[rInd][cInd] = Math.max(lcsRecursiveHelper(rStr, rInd, cStr, cInd - 1, memo), 
                    lcsRecursiveHelper(rStr, rInd - 1, cStr, cInd, memo)); 
  
            return memo[rInd][cInd]; 
        } 
    } 
}
