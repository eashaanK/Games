package introToDataStructures;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList {
	
	private Node first;
	
	public LinkedList(){
		first = null;
	}
	
	public Object getFirst(){
		if(first == null)
			throw new NoSuchElementException("No elements to get");
		return first.data;
	}
	
	public void addFirst(Object o){
		Node newFirst = new Node(o, first);
		first = newFirst;
	}
	
	public Object removeFirst(){
		if(first == null)
			throw new NoSuchElementException("No elements to remove");
		Object toBeDeleted = first.data;
		first = first.next;
		return toBeDeleted;
	}
	
	public ListIterator listIterator(){
		return new LinkedListIterator();
	}

	//Private Classes
	private class Node{
		public Object data;
		public Node next;
		
		public Node(Object o, Node next){
			this.data = o;
			this.next = next;
		}
	}
	
	private class LinkedListIterator implements ListIterator{
		
		private Node position, previous;
		
		public LinkedListIterator(){
			position = null;
			previous = null;
		}

		@Override
		public boolean hasNext() {
			if (position == null ) return first != null ;
			else
			return position.next != null ;
		}

		@Override
		public Object next() {
			if(!hasNext())
				throw new NoSuchElementException("No elements in array");
			previous = position;
			if(position == null)
				position = first;
			else
				position = position.next;
			return position.data;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			if(previous == position)
				throw new IllegalStateException("Cannot call this method if you havent called next");
			if(position == first){
				removeFirst();
			}
			else{
				previous.next = position.next;
			}
			position = previous;
		}

		@Override
		public void set(Object e) {
			if(position == null)
				throw new NoSuchElementException("The array points to nothing...");
			position.data = e;
		}

		@Override
		public void add(Object e) {
			if(position == null){
				addFirst(e);
				position = first;
			}
			else{
				Node newNode = new Node(e, position.next);
				position.next = newNode;
				position = newNode;
			}
			previous = position;
		}
		
	}
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.addFirst(1);
		list.addFirst(2);
		list.addFirst(3);
		
		ListIterator iterator = list.listIterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
