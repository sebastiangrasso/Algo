package pathfinder.uninformed;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Maze Pathfinding algorithm that implements a basic, uninformed, breadth-first tree search.
 */
public class Pathfinder {
    
    /**
     * Given a MazeProblem, which specifies the actions and transitions available in the
     * search, returns a solution to the problem as a sequence of actions that leads from
     * the initial to a goal state.
     * 
     * @param problem A MazeProblem that specifies the maze, actions, transitions.
     * @return An ArrayList of Strings representing actions that lead from the initial to
     * the goal state, of the format: ["R", "R", "L", ...]
     */
    public static ArrayList<String> solve (MazeProblem problem) {
        // TODO: Initialize frontier -- what data structure should you use here for
        // breadth-first search? Recall: The frontier holds SearchTreeNodes!
        Queue<SearchTreeNode> frontier = new LinkedBlockingQueue <SearchTreeNode>();
        
        // TODO: Add new SearchTreeNode representing the problem's initial state to the
        // frontier. Since this is the initial state, the node's action and parent will
        // be null
        SearchTreeNode root = new SearchTreeNode(problem.INITIAL_STATE, null, null);
        frontier.add(root);
        
        // TODO: Loop: as long as the frontier is not empty...
        while (frontier.isEmpty() == false) {
           
        	// TODO: Get the next node to expand by the ordering of breadth-first search
            SearchTreeNode curr = frontier.remove();            
        	
            // TODO: If that node's state is the goal (see problem's isGoal method),
            // you're done! Return the solution
            // [Hint] Use a helper method to collect the solution from the current node!
            if (problem.isGoal(curr.state)) {         	
              	ArrayList <String> sol = retrace(curr);
            	int [] test = problem.testSolution(sol);
            	if (test[0] == 1) {
            		return sol;
            	}
            }
            
            // TODO: Otherwise, must generate children to keep searching. So, use the
            // problem's getTransitions method from the currently expanded node's state...
            Map<String, MazeState> moves = problem.getTransitions(curr.state);
            
            // TODO: ...and *for each* of those transition states...
            // [Hint] Look up how to iterate through <key, value> pairs in a Map -- an
            // example of this is already done in the MazeProblem's getTransitions method
            for (Map.Entry<String, MazeState> options : moves.entrySet()) {          	
                // TODO: ...add a new SearchTreeNode to the frontier with the appropriate
                // action, state, and parent
            	SearchTreeNode temp = new SearchTreeNode(options.getValue(), options.getKey(), curr);
            	frontier.add(temp);
            }
        }
        // Should never get here, but just return null to make the compiler happy
        return null;
}
    
    
// Helper method designed to obtain path to solution
// Method begins at GOAL_STATE and works through parent nodes to obtain actions    
public static ArrayList<String> retrace(SearchTreeNode end) {
	
	ArrayList <String> steps = new ArrayList <String>();
	SearchTreeNode curr = end;
	
	while (curr.parent!= null) {
		steps.add(0, curr.action);
		curr = curr.parent;	
	}
	
	return steps;

}
    
}    

/**
 * SearchTreeNode that is used in the Search algorithm to construct the Search
 * tree.
 */
class SearchTreeNode {
    
    MazeState state;
    String action;
    SearchTreeNode parent;
    
    /**
     * Constructs a new SearchTreeNode to be used in the Search Tree.
     * 
     * @param state The MazeState (col, row) that this node represents.
     * @param action The action that *led to* this state / node.
     * @param parent Reference to parent SearchTreeNode in the Search Tree.
     */
    SearchTreeNode (MazeState state, String action, SearchTreeNode parent) {
        this.state = state;
        this.action = action;
        this.parent = parent;
    }
    
}
