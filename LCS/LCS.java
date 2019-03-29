package lcs;

import java.util.Arrays;
import java.util.HashSet;
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
    
    public static Set<String> collectSolution(String rStr, int r, String cStr, int c, int[][] memo){
		
    	// reached gutters
		if (r == 0 || c == 0)
		{
			Set<String> temp = new HashSet<String>();
			temp.add("");
			return temp;
		}

		// if the characters match
		if (rStr.charAt(r - 1) == cStr.charAt(c - 1))
		{
			// Add the matched letter to all substrings by recursing top and left
			Set<String> lcs = collectSolution(rStr, r - 1, cStr, c - 1, memo);
			Set<String> holding = new HashSet<>();
			
			String matchingChar = ("" + rStr.charAt(r-1));
			
			// append current character rStr[r - 1] or cStr[c - 1]
			// to all LCS of substring rStr[0..r-2] and cStr[0..c-2]
			for(String sub : lcs) {
				sub = sub + matchingChar;
				holding.add(sub);
			}
			return holding;
		}

		// if characters don't match
		
		Set <String> left = new HashSet<>();
		Set <String> up = new HashSet<>(); 

		// left vs top
		if (memo[r - 1][c] > memo[r][c - 1])
			left = collectSolution(rStr, r-1, cStr, c, memo);

		// top vs left
		if (memo[r][c - 1] > memo[r - 1][c])
			up = collectSolution(rStr, r, cStr, c-1, memo);
		
		//merge two sets
		left.addAll(up);
		

		return left;
	}
	

       
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
    	int[][] memo = new int [rStr.length()+1][cStr.length()+1];

    	bottomUpTableFill(rStr, cStr, rStr.length(), cStr.length(), memo);
    	memoCheck = memo;

    	return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memo);

    }

    static int bottomUpTableFill( String rStr, String cStr, int rInd, int cInd, int memo[][]) { 

    	for (int i=0; i<=rInd; i++) 
    	{ 
    		for (int j=0; j<=cInd; j++) 
    		{ 
    			if (i == 0 || j == 0) 
    				memo[i][j] = 0; 
    			else if (rStr.charAt(rInd - 1) == cStr.charAt(rInd - 1)) 
          memo[i][j] = 1 + memo[i-1][j-1]; 
    			else
          memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]); 
    		}
    	}	
    	return memo[rInd][cInd];
  }  
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
    
    	lcsRecursiveHelper(rStr, rStr.length(), cStr, cStr.length(), memo);
    	memoCheck = memo;
    	  	
    	return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memo);
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
        //if chars don't match, check left and up
        else { 
  
            memo[rInd][cInd] = Math.max(lcsRecursiveHelper(rStr, rInd, cStr, cInd - 1, memo), 
                    lcsRecursiveHelper(rStr, rInd - 1, cStr, cInd, memo)); 
  
            return memo[rInd][cInd]; 
        } 
    } 
}
