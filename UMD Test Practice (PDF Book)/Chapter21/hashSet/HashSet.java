package hashSet;

import java.util.AbstractSet;
import java.util.Iterator;

public class HashSet extends AbstractSet{
	
	private Node[] bucketList;
	private int size;
	
	public HashSet(int size){
		bucketList = new Node[size];
		size = 0;
	}
	
	@Override
	public boolean add(Object o){
		int h = o.hashCode();
		if(h < 0) h *= -1;
		h %= bucketList.length;
		
		Node currentBucket = bucketList[h];
		//Prevents duplicates from being added.
		while(currentBucket != null){
			if(currentBucket.data.equals(o))
				return false;
			currentBucket = currentBucket.next;
		}
		Node newNode = new Node(o, bucketList[h]);
		bucketList[h] = newNode;
		size++;
		return true;
	}
	
	@Override
	public boolean contains(Object o){
		int h = o.hashCode();
		if(h < 0) h *= -1;
		h %= bucketList.length;
		
		Node current = bucketList[h];
		while(current != null){
			if(current.data.equals(o))
				return true;
			current = current.next;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object o){
		int h = o.hashCode();
		if(h < 0)h *= -1;
		h %= bucketList.length;
		
		Node current = bucketList[h];
		Node previous = null;
		while(current != null){
			if(current.data.equals(o)){
				if(previous != null)
					previous.next = current.next;
				else
					bucketList[h] = current.next;
				size--;
				return true;
			}
			previous = current;
			current = current.next;
		}
		
		return false;
	}

	public void printHashSet(){
		System.out.println("-----");
		for(int i = 0; i < bucketList.length; i++){
			Node bucket = bucketList[i];
			if(bucket == null)
				continue;
			while(bucket != null){
				System.out.print(bucket.data + ", ");
				bucket = bucket.next;
			}
			System.out.println();
		}
		System.out.println("-----");

	}
	
	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	private class Node{
		public Object data;
		public Node next;
		
		public Node(Object o, Node n){
			this.data = o;
			this.next = n;
		}
	}

	public static void main(String[] args){
		HashSet set = new HashSet(30);
		set.add("Shuwei is smart");
		set.add("Shuwei is a good badminton player");
		set.add("Apples");
		set.add("Orange");
		set.add("Red");
		set.add("Blue");
		set.printHashSet();
		
		//set.remove("Red");
		//set.printHashSet();
	}
}
