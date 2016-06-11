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
		/**
		 * INORDER TRAVERSAL
		 * Prints nodes in sorting order
		 */
		public void printNodes(){
			if(left != null)
				left.printNodes();
			System.out.println(data);
			if(right != null)
				right.printNodes();
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
		//Find Node to remove
		Node toBeRemoved = root;
		Node parent = null;
		boolean found = false;
		
		while(!found && toBeRemoved != null){
			int d = toBeRemoved.data.compareTo(obj);
			if(d == 0) found = true;
			else
			{
				parent = toBeRemoved;
				if(d > 0) toBeRemoved = toBeRemoved.left;
				else toBeRemoved = toBeRemoved.right;
			}
		}
		
		if(!found) return; 
		
		//At this point, the node should be at the current node to be removed. 
		//If one of the children is empty, use the other. 
		if(toBeRemoved.left == null || toBeRemoved.right == null){
			Node newChild;
			if(toBeRemoved.left == null)
				newChild = toBeRemoved.right;
			else
				newChild = toBeRemoved.left;
			
			if(parent == null) //Found in root
				root = newChild;
			else if(parent.left == toBeRemoved)
				parent.left = newChild;
			else
				parent.right = newChild;
			return;
		}
		
		//Now, its time to reorganize the tree
		Node smallestParent = toBeRemoved;
		Node smallest = toBeRemoved.right;
		while(smallest.left != null){
			smallestParent = smallest;
			smallest = smallest.left;
		}
		//We just found the smallest element in the right subtree of the removed element
		toBeRemoved.data = smallest.data;
		smallestParent.left = smallest.right;
	}
	
	public void print(){
		if(root != null)
			root.printNodes();
	}
	
	public static void main(String[] args){
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < 10; i++){
			tree.add(i);
		}
		tree.print();
		System.out.println();
		
		for(int i = 0; i < 10; i++){
			tree.remove(i);
			tree.print();
			System.out.println("------------");
		}
		
	}
}
