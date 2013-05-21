package trees;

import static org.junit.Assert.fail;

import java.util.List;

import junit.framework.Assert;
import nodes.TreeNode;

import org.junit.Ignore;
import org.junit.Test;

public class TreeTest {

	@Ignore
	@Test
	public void testTreeInt() {
		Tree t1 = new Tree(1);
		Tree t2 = new Tree(4);
		Tree t3 = new Tree();
		Assert.assertTrue(t1.getBranchingOrder() == 2 && t2.getBranchingOrder() == 4 && t3.getBranchingOrder() == 2);
	}

	@Ignore
	@Test
	public void testGetDescendants() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetAncestors() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetNearestCommonAncestor() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNodeToTree() {
		TreeNode tn1 = new TreeNode();
		tn1.setId(1);
		tn1.setContent(75);
		
		TreeNode tn2 = new TreeNode();
		tn2.setId(2);
		tn2.setContent(135);
		
		TreeNode tn3 = new TreeNode();
		tn3.setId(3);
		tn3.setContent(45);
		
		TreeNode tn4 = new TreeNode();
		tn4.setId(4);
		tn4.setContent(15);
		
		TreeNode tn5 = new TreeNode();
		tn5.setId(5);
		tn5.setContent(105);
		
		TreeNode tn6 = new TreeNode();
		tn6.setId(6);
		tn6.setContent(165);
		
		TreeNode tn7 = new TreeNode();
		tn7.setId(7);
		tn7.setContent(195);
		
		TreeNode tn8 = new TreeNode();
		tn8.setId(8);
		tn8.setContent(35);

		TreeNode tn9 = new TreeNode();
		tn9.setId(9);
		tn9.setContent(5);
		
		Tree t = new Tree(3);
		t.addNodeToTree(tn1);
		t.addNodeToTree(tn2);
		t.addNodeToTree(tn3);
		
		t.addNodeToTree(tn4);
		t.addNodeToTree(tn5);
		t.addNodeToTree(tn6);
		t.addNodeToTree(tn7);
		
		t.addNodeToTree(tn8);
		t.addNodeToTree(tn9);
		
		System.out.println("PRINTING TREE.....");
		System.out.println(t);
		
		Assert.assertTrue(checkTreeForMinOrdering(t.getAllNodes()));
	}

	
	

	private boolean checkTreeForMinOrdering(List<TreeNode> nodes) {
		for(TreeNode node : nodes){
			if(nodeEqualsOrLesserThanChildren(node) && nodeGreaterThanParent(node)){
				continue;
			} else{
				return false;
			}
		}
		return true;
	}

	private boolean nodeEqualsOrLesserThanChildren(TreeNode node) {
		if(node.isALeaf()){
			return true;
		}
		List<TreeNode> children = node.getChildren();
		for(TreeNode child : children){
			if(node.compareTo(child) >= 0){
				continue;
			}
			else{
				return false;
			}
		}
		return true;
	}

	private boolean nodeGreaterThanParent(TreeNode node) {
		if(node.isTheRoot()){
			return true;
		}
		if(node.compareTo(node.getParent()) < 0){
			return true;
		}
		return false;
	}

	@Ignore
	@Test
	public void testRemoveNodeFromTree() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testPrintInOrderTraversal() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testPrintPostOrderTrasversal() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testPrintPreOrderTraversal() {
		fail("Not yet implemented");
	}

}
