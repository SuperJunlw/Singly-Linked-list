package CS146F21WuProject1;

//This class implements node for the linked list
public class Node<T>
{
	//A node contain a value and it's next node
	private T value;
	private Node<T> nextNode;
	
	public Node()
	{}
	
	//create a new node using the given value
	public Node(T val)
	{
		this.value = val;
		this.nextNode = null;
	}

	//setters and getters for the instance variables
	
	public T getValue() 
	{
		return value;
	}
	public void setValue(T value)
	{
		this.value = value;
	}
	public Node<T> getNextNode() 
	{
		return nextNode;
	}
	public void setNextNode(Node<T> nextNode)
	{
		this.nextNode = nextNode;
	}
}