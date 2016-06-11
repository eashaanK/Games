package binarTrees;

public class BinaryExpressionTree {

	private class Node{
		public Node left, right;
		public Comparable data;
		public Node(Node l, Node r, Comparable d){
			this.left = l;
			this.right = r;
			this.data = d;
		}
	}
	
	private Node root;
	
	public BinaryExpressionTree(){
		
	}
	
	public void solve(String expression){
		
	}
	
	/**
	 * Temporary. Tests solve. 
	 */
	public void testHelperSolve(){
		Node n3 = new Node(null, null, 3);
		Node n4 = new Node(null, null, 4);
		Node nStar = new Node(null, null, "*");
		Node n5 = new Node(null, null, 5);
		Node nPlus = new Node(null, null, "+");

		nStar.left = nPlus;
		nStar.right = n5;
		
		nPlus.left = n3;
		nPlus.right = n4;
		solve(nStar, nStar);
	}
	
	/**
	 * Solves a given tree. Helper method. 
	 * @param previous
	 * @param current
	 */
	private void solve(Node previous, Node current){
		if(current.left != null)
		{
			previous = current;
			current = current.left;
			solve(previous, current);
		}
		else{
			// parse data stored in previous
			String operator = (String) previous.data;
			int leftNum = (Integer)current.data;
			int rightNum = (Integer)previous.right.data;
			Integer answer = null;
			if(operator.equals("+")) answer = leftNum + rightNum;
			else if(operator.equals("-")) answer = leftNum - rightNum;
			else if(operator.equals("*")) answer = leftNum * rightNum;
			else if(operator.equals("/")) answer = leftNum / rightNum;
			else if(operator.equals("%")) answer = leftNum % rightNum;
			else
				throw new IllegalArgumentException("Expecting an operator. Found \"" + operator + "\"");
			previous.data = answer;
			previous.left = null;
			previous.right = null;
			
			System.out.println(answer);
		}
	}
	
	public static void main(String[] args){
		BinaryExpressionTree tree = new BinaryExpressionTree();
		tree.testHelperSolve();
	}
}
