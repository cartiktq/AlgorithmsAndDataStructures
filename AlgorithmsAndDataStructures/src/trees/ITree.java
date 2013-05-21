package trees;

import java.util.Collection;

import nodes.TreeNode;

public interface ITree<C extends Comparable<C>> {

	public void addNodeToTree(TreeNode<C> node);
	public void removeNodeFromTree(TreeNode<C> node, Tree.TreeOrder treeOrder);
	
	public TreeNode<C> getRoot();
	public Collection<TreeNode<C>> getLeaves();
	
	public Collection<TreeNode<C>> getDescendants(TreeNode<C> tNode);
	public Collection<TreeNode<C>> getAncestors(TreeNode<C> tNode);
	
	public TreeNode<C> getNearestCommonAncestor(TreeNode<C> tNode1, TreeNode<C> tNode2);
	
	public String printInOrderTraversal();
	public String printPostOrderTrasversal();
	public String printPreOrderTraversal();

}
