package CS146F21WuProject1;

import static org.junit.Assert.*;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//This class test each operations of the singly linked list
public class SinglyLinkedListTest 
{
	private SinglyLinkedList<String> list1;

	@Before
	public void setUp() throws Exception 
	{
		list1 = new SinglyLinkedList<>();
	}

	//Test if the size change correctly after add/remove item from the set
	@Test
	public void testGetSize() 
	{
		assertEquals(0, list1.getSize());
		
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		
		assertEquals(2, list1.getSize());
		
		list1.removeItem("Cat");
		
		assertEquals(1, list1.getSize());
	}

	//check if the items in set, the head and the size change correct after adding items
	//nothing should change if the item is a duplicate 
	@Test
	public void testAddItemToSet() 
	{
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		list1.addItemToSet("Bird");
		list1.addItemToSet("Dog");
		assertEquals(3, list1.getSize());
		assertEquals("Bird", list1.getHead().getValue());
		assertEquals("Bird Cat Dog ", list1.toString());
		
		list1.addItemToSet("Fish");
		list1.addItemToSet("Bear");
		assertEquals(5, list1.getSize());
		assertEquals("Bear", list1.getHead().getValue());
		assertEquals("Bear Fish Bird Cat Dog ", list1.toString());	
	}

	//check if the items in set, the head and the size change correct after remove items
	//and check if the removeItem() will return the removed value
	@Test
	public void testRemoveItem()
	{
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		list1.addItemToSet("Bird");
		list1.addItemToSet("Fish");
		list1.addItemToSet("Bear");
		
		assertEquals("Bear", list1.removeItem("Bear"));
		assertEquals("Cat", list1.removeItem("Cat"));
		
		assertEquals(3, list1.getSize());
		assertEquals("Fish", list1.getHead().getValue());
		assertEquals("Fish Bird Dog ", list1.toString());	
	}

	//check if isMember() return true if an item is in set, false if it's not
	@Test
	public void testIsMember()
	{
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		list1.addItemToSet("Bird");
		list1.addItemToSet("Fish");
		list1.addItemToSet("Bear");
		
		assertEquals(true, list1.isMember("Bear"));
		assertEquals(true, list1.isMember("Bird"));
		assertEquals(false, list1.isMember("Tree"));
		assertEquals(false, list1.isMember("Apple"));
	}

	//check if findNode() return the correct node by given an value
	//or return null if the value is not in the set
	@Test
	public void testFindNode() 
	{
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		list1.addItemToSet("Bird");
		list1.addItemToSet("Fish");
		list1.addItemToSet("Bear");
		
		assertEquals("Bear", list1.findNode("Bear").getValue());
		assertEquals("Fish", list1.findNode("Fish").getValue());
		assertEquals(null, list1.findNode("Tree"));
	}

	//FindUnion() should return a set include the all distinct value of 
	//list1 and list2
	@Test
	public void testFindUnion()
	{
		SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
		SinglyLinkedList<String> unionSet;
		
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		list1.addItemToSet("Bird");
		list1.addItemToSet("Fish");
		list1.addItemToSet("Bear");
		
		list2.addItemToSet("Panda");
		list2.addItemToSet("Lion");
		list2.addItemToSet("Cat");
		list2.addItemToSet("Cow");
		list2.addItemToSet("Fish");
		list2.addItemToSet("Monkey");
		
		unionSet = list1.findUnion(list2);
		
		//Should be 9 distinct value in the union
		assertEquals(9, unionSet.getSize());
		assertEquals("Panda Lion Cow Monkey Dog Cat Bird Fish Bear ", unionSet.toString());
	}

	//FindIntersection() should return a set include the all common distinct value of 
	//list1 and list2
	@Test
	public void testFindIntersection() 
	{
		SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
		SinglyLinkedList<String> interSet;
		
		list1.addItemToSet("Dog");
		list1.addItemToSet("Cat");
		list1.addItemToSet("Bird");
		list1.addItemToSet("Fish");
		list1.addItemToSet("Bear");
		
		list2.addItemToSet("Panda");
		list2.addItemToSet("Lion");
		list2.addItemToSet("Cat");
		list2.addItemToSet("Cow");
		list2.addItemToSet("Fish");
		list2.addItemToSet("Monkey");
		
		interSet = list1.findIntersection(list2);
		
		//Should be 2 common distinct value in the intersection 
		assertEquals(2, interSet.getSize());
		assertEquals("Cat Fish ", interSet.toString());
	}
	
	@Test
	public void testGenerics()
	{
		SinglyLinkedList<Integer> intSet = new SinglyLinkedList<>();
		intSet.addItemToSet(2);
		intSet.addItemToSet(3);
		intSet.addItemToSet(1);
		intSet.addItemToSet(12);
		assertEquals(4, intSet.getSize());
		
		assertTrue(intSet.removeItem(1) == 1);
		
		assertEquals(3, intSet.getSize());
		
		assertEquals("12 3 2 ", intSet.toString());
	}
	
	//This method test execute time for each operations of the linked list
	@Test
	public void testExecuteTime()
	{
		Random random = new Random();
		SinglyLinkedList<Integer> intSet1 = new SinglyLinkedList<>();
		
		
		// addItemToSet()
		long start = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++ )
		{
			intSet1.addItemToSet(random.nextInt(1000));
		}
		long end = System.currentTimeMillis();
		System.out.println("addItemSet() excute time: " + (end-start));
		
		
		//removeItem()
		start = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++ )
		{
			intSet1.removeItem(random.nextInt(1000));
		}
		end = System.currentTimeMillis();
		System.out.println("removeItem() excute time: " + (end-start));	
		
		
		//isMember()
		start = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++ )
		{
			intSet1.isMember(random.nextInt(1000));
		}
		end = System.currentTimeMillis();
		System.out.println("isMember() excute time: " + (end-start));	
		
		
		//findNode()
		start = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++ )
		{
			intSet1.findNode(random.nextInt(1000));
		}
		end = System.currentTimeMillis();
		System.out.println("findNode() excute time: " + (end-start));	
		
		
		//findUnion() and findInterception()
		SinglyLinkedList<Integer> intSet2 = new SinglyLinkedList<>();
		SinglyLinkedList<Integer> intSet3 = new SinglyLinkedList<>();
		
		for(int i = 0; i < 1000; i++ )
		{
			intSet2.addItemToSet(random.nextInt(1000));
			intSet3.addItemToSet(random.nextInt(1000));
		}
		
		start = System.currentTimeMillis();
		
		intSet2.findUnion(intSet3);
		end = System.currentTimeMillis();
		System.out.println("findUnion() excute time: " + (end-start));	
		
		
		start = System.currentTimeMillis();
		intSet2.findIntersection(intSet3);
		end = System.currentTimeMillis();
		System.out.println("findIntersection() excute time: " + (end-start));	
	}
}
