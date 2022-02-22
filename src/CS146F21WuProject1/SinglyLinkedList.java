package CS146F21WuProject1;

//This class implements a set using singly linked list that can add 
//item, remove item, find size of the set, find a node, check if an
//item is in the set, find union and intersection of two set, and 
//print all items in the set

public class SinglyLinkedList<T>
{
	private int size;
	private Node<T> head;
	
	public SinglyLinkedList()
	{}
	
	//return the head of the list
	public Node<T> getHead() 
	{
		return this.head;
	}
	
	//this method return the size(number of items) of the list
	public int getSize()
	{
		return this.size;
	}
	
	//This method add an item to set from the head, if the item
	//is already in the set then the method will do nothing
	public void addItemToSet(T val)
	{
		//check if the item is already in the set or not
		if(!isMember(val))
		{
			addItem(val);
		}
	}
	
	
	//This method add an item to list from the head,
	public void addItem(T val)
	{
		//long start = System.currentTimeMillis();
		
		Node<T> newNode = new Node<>(val);
			
		//if the list is empty, the new node is the head
		if(head == null)
		{
			head = newNode;
			size++;
		}
		//add item from the head, and head will be the next node of 
		//the new node
		else
		{
			newNode.setNextNode(head);
			head = newNode;
			size++;
		}
		
		//long end = System.currentTimeMillis();
		//System.out.println("addItemSet() excute time: " + (end-start));	
	}
	
	
	//This method remove an item from the set and return that value
	public T removeItem(T val)
	{
		T removedVal = null;
		
		//if the head is null, nothing will happen
		if(head != null)
		{
			//if the value we want remove is in the head, then
			//set the head as it's next node
			if(head.getValue() == val)
			{
				removedVal = head.getValue();
				head = head.getNextNode();
				size--;
			}
			else
			{
				Node<T> prevNode = null; 
				Node<T> tempNode = head;  
				
				//start from head, check each node to find the value we 
				//want to remove. In the end, tempNode will be the node 
				//that we want to remove or null, and prevNode will be 
				//the node in previous position of tempNode
				while( tempNode != null && tempNode.getValue() != val)
				{
					prevNode = tempNode;
					tempNode = tempNode.getNextNode();	
				}
				
				//if tempNode is null after the loop, it means the item
				//is not in the set.return null
				if(tempNode == null)
				{
					return null;
				}
				//set the previous node's next node to tempNode's next
				//node, and the tempNode will be remove 
				else
				{
					removedVal = tempNode.getValue();  //save the removed value
					prevNode.setNextNode(tempNode.getNextNode());
					size--;
				}
			}
		}
		return removedVal;
	}
	
	
	//This method check if an item is in the set or not
	//It will return true if it is or false if it is not
	public boolean isMember(T val)
	{
		if(head == null)
		{
			return false;
		}
		else
		{
			Node<T> tempNode = head;
			
			//start from the head, each the value of each node, if a node
			//has the value we want then return true, else move to the next node
			for(int i = 0; i < size; i++)
			{
				if(tempNode.getValue() == val)
				{
					return true;
				}
				else
				{
					tempNode = tempNode.getNextNode();
				}
			}
			
			//return false in the end if the value is not in the set
			return false;
		}
	}
	
	//This method find a node based on the given value
	//It will return the node if the node is found
	public Node<T> findNode(T val)
	{
		
		Node<T> tempNode = head;
		
		//start from the head, each the value of each node, if a node
		//has given value, then return the that node, else, move to next node
		for(int i = 0; i < size; i++)
		{
			if(tempNode.getValue() == val)
			{
				return tempNode;
			}
			else
			{
				tempNode = tempNode.getNextNode();
			}
		}
		return null;	
	}
	
	
	//This method finds the union of this.list and the given list,
	//and return a list as the union set
	public SinglyLinkedList<T> findUnion(SinglyLinkedList<T> list2)
	{
		SinglyLinkedList<T> union = new SinglyLinkedList<>();
		
		Node<T> tempNode = this.head;
		
		//Using two for loops to add items of both set to the union set
		//additemToSet() will check if there are duplicates, so no need to 
		//check here
		for(int i = 0; i < size; i++)
		{
			union.addItem(tempNode.getValue());
			
			tempNode = tempNode.getNextNode();
		}
		
		Node<T> tempNode2 = list2.head;
		
		//using addItemToSet() for adding items of second set
		//to check if there are same items as first set
		for(int j = 0; j < list2.getSize(); j++)
		{
			
			union.addItemToSet(tempNode2.getValue());
			
			tempNode2 = tempNode2.getNextNode();
		}
		
		return union;
	}
	
	//This method finds the union of this.list and the given list,
	//and return a list as the intersection set
	public SinglyLinkedList<T> findIntersection(SinglyLinkedList<T> list2)
	{
		SinglyLinkedList<T> intersection = new SinglyLinkedList<T>();
		
		Node<T> tempNode = head;
		
		//using nested loop to compare all items in the two set
		//if there is same item in two set, add that item to the intersection
		for(int i = 0; i < size; i++)
		{
			Node<T> tempNode2 = list2.head;
			for(int j = 0; j < list2.size; j++)
			{
				if(tempNode.getValue() == tempNode2.getValue())
				{
					intersection.addItem(tempNode.getValue());
				}
				tempNode2 = tempNode2.getNextNode();
			}
			tempNode = tempNode.getNextNode();
		}
		
		return intersection;
	}
	
	//This method prints all the items in the set
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		if(size == 0)
		{
			builder.append("");
		}
		else
		{
			Node<T> tempNode = head;
			for(int i = 0; i < size; i++)
			{
				builder.append(tempNode.getValue());
				builder.append(" ");
				tempNode = tempNode.getNextNode();
			}
		}
		return builder.toString();
	}
}