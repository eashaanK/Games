package binarTrees;

public class BinarySearchTree {

	private class Node{
		public Comparable data;
		public Node left;
		public Node right;
		public Node(Comparable data, Node Left, Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
		/**
		 * If new object is less that this, go to left. Else, go to right
		 * @param newNode
		 */
		public void addNode(Node newNode){
			int comparison = newNode.data.compareTo(this.data);
			if(comparison < 0){
				if(left == null) left = newNode;
				else left.addNode(newNode);
			}
			else if(comparison > 0){
				if(right == null) right = newNode;
				else right.addNode(newNode);
			}
			
		}
	}
	
	private Node root;
	
	public BinarySearchTree(){
		
	}
	
	/**
	 * If the root is null, assign the node. Otherwise, tell the root node to add the new node. 
	 * @param newData New data to add. 
	 */
	public void add(Comparable newData){
		Node newNode = new Node(newData, null, null);
		if(root == null)root = newNode;
		else root.addNode(newNode);
	}
	
	public void remove(Comparable obj){
		//Node nodeToRemo
	}
}
