package trees;

import java.util.List;

import junit.framework.Assert;

import nodes.BinaryTreeNode;

import org.junit.Test;

import exceptions.EmptyTreeException;
import exceptions.NodeNotFoundException;

public class BinarySearchTreeTest {

	@Test
	public void testIsEmpty() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		Assert.assertTrue(bst.isEmpty());
	}

	@Test
	public void testPopulateNodeList() {
		Integer[] integerArray = {2,1,3,7,6,5,4};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.addGenericNodes(integerArray);
		
		System.out.println("Tree: " + bst.nodeList);
		
		Assert.assertTrue(bst.getRoot().getContent() == 4  && bst.getRoot().getLeftChild().getContent() == 2 
				&& bst.getRoot().getRightChild().getContent() == 6);
	}
	
	@Test
	public void testComputePreorderAndInorderTraversalList(){
		Integer[] integerArray = {2,1,3,4,5,7,6};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.addGenericNodes(integerArray);
		
		try {
			List<BinaryTreeNode<Integer>> poList = bst.computePreorderTraversalList();
			List<BinaryTreeNode<Integer>> ioList = bst.computeInorderTraversalList();
			System.out.println("PreOrder Traversal: " + poList);
			System.out.println("InOrder Traversal: " + ioList);
			Assert.assertTrue(true);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testDeleteNode(){
		Integer[] integerArray = {1,2,3,4,5,7,6,8};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.addGenericNodes(integerArray);
		
		BinaryTreeNode<Integer> node = new BinaryTreeNode<Integer>();
		node.setContent(7);
		
		try {
			System.out.println("Node list before removal: " + bst.nodeList);
			bst.deleteNode(node);
			System.out.println("Node list after removal: " + bst.nodeList);
			Assert.assertTrue(bst.findNodeInTree(node) == -1);
		} catch (NodeNotFoundException e) {
			e.printStackTrace();
			Assert.fail("Node to be deleted was not found in the tree");
		}
		
	}

}
