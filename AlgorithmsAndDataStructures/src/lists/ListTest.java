package lists;

import static org.junit.Assert.fail;
import nodes.Node;

import org.junit.Assert;
import org.junit.Test;

public class ListTest<C extends Comparable<C>> {
	
	List<Integer> listToTest;
	
	public void createListToTest(){
		listToTest = new List<Integer>();
		for(int i = 0; i < 10; i++){
			listToTest.addNodeToEnd(i);
		}
		System.out.println("Created list: " + listToTest.toString());
	}
	@Test
	public void testAddNodeToFront() {
		createListToTest();
		listToTest.addNodeToFront(1);
		System.out.println("Added node to front");
		System.out.println("New list is: " + listToTest.toString());
		Assert.assertTrue(1 == listToTest.getFirstNode().getContent());
	}

	@Test
	public void testAddNodeAtIndex() {
		createListToTest();
		int i = 7;
		listToTest.addNodeAtIndex(200, i);
		try {
			Assert.assertTrue(200 == listToTest.getNodeAtIndex(i).getContent());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("Exception thrown when accessing node at index: " + i);
		}
		finally{
			System.out.println("Added node to index: " + i);
			System.out.println("New list is: " + listToTest.toString());
		}
	}
	
	@Test
	public void testAddNodeToEnd() {
		createListToTest();
		listToTest.addNodeToEnd(10);
		System.out.println("Added node to end");
		System.out.println("New list is: " + listToTest.toString());
		Assert.assertTrue(10 == listToTest.getLastNode().getContent());
	}

	
	@Test
	public void testGetNodeAtIndex() {
		createListToTest();
		int i = 6;
		Node<Integer> node = null;
		try{
			node = listToTest.getNodeAtIndex(i);
		} catch(IllegalAccessException iae){
			fail("Exception thrown when trying to get node at index: " + i);
		}
		if (node != null){
			Assert.assertTrue(node.getPrevious().getContent().equals(i -1) && 
					node.getNext().getContent().equals(i + 1));
		}
	}

	@Test
	public void testRemoveFirstNode() {
		createListToTest();
		try {
			listToTest.removeFirstNode();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("Exception thrown when trying to remove first node");
		}
		System.out.println("Removed first node");
		System.out.println("New list is: " + listToTest.toString());
		Node<Integer> newFirstNode = listToTest.getFirstNode();
		Assert.assertTrue((listToTest.getLength() == 9) && (newFirstNode.getContent().equals(1)));
	}

	@Test
	public void testRemoveLastNode() {
		createListToTest();
		try {
			listToTest.removeLastNode();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("Exception thrown when trying to remove last node");
		}
		Node<Integer> newLastNode = listToTest.getLastNode();
		System.out.println("Removed last node");
		System.out.println("New list is: " + listToTest.toString());

		Assert.assertTrue((listToTest.getLength() == 9) && (newLastNode.getContent().equals(8)));
	}

	@Test
	public void testRemoveNodeAtIndex() {
		createListToTest();
		int i = 5;
		try {
			listToTest.removeNodeAtIndex(i);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("Exception thrown when trying to remove node at index " + i);
		}
		Node<Integer> newNode = null;
		try {
			newNode = listToTest.getNodeAtIndex(i);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally{
			System.out.println("Removed node at index: " + i);
			System.out.println("New list is: " + listToTest.toString());
			Assert.assertTrue((newNode != null) && (listToTest.getLength() == 9) && (newNode.getContent().equals(i + 1)));
		}
	}

	@Test
	public void testToString() {
		createListToTest();
		System.out.println(listToTest.toString());
		Assert.assertTrue(true);
	}
}
