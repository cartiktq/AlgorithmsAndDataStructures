package trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nodes.TreeNode;

public class Tree<C extends Comparable<C>> implements ITree<C> {

	public enum TreeOrder{
		MAX, MIN;
	} 

	/** Parameter decides if tree is binary, n-ary etc. Order cannot be < 2 **/
	private int branchingOrder; 
	private TreeNode<C> root;
	private List<TreeNode<C>> leaves;
	/** To track all the nodes in the tree**/
	private List<TreeNode<C>> allNodes; 
	private TreeNode<C> lastLeaf;
	/** To track nodes at each level **/
	private Map<Integer, List<TreeNode<C>>> levelToNodesMap; 
	
	public Tree(){
		branchingOrder = 2; /** BINARY TREE BY DEFAULT **/
		levelToNodesMap = new HashMap<Integer, List<TreeNode<C>>>();
		allNodes = new ArrayList<TreeNode<C>>();
	}

	public Tree (int bo){
		this();
		if(bo > 1){
			this.branchingOrder = bo;
		} else{
			System.err.println("WARNING: You gotta be kidding me! Only binary and higher branching orders are allowed. " +
					"Setting branching order to default value of 2");
		}
	}

	/* GETTERS AND SETTERS*/
	
	public List<TreeNode<C>> getAllNodes() {
		return allNodes;
	}
	
	public int getBranchingOrder(){
		return branchingOrder;
	}
	
	public TreeNode<C> getLastLeaf() {
		return lastLeaf;
	}

	public void setLastLeaf(TreeNode<C> lastLeaf) {
		this.lastLeaf = lastLeaf;
	}

	public void setRoot(TreeNode<C> root) {
		this.root = root;
	}

	@Override
	public TreeNode<C> getRoot() {
		return root;
	}

	@Override
	public List<TreeNode<C>> getLeaves() {
		return leaves;
	}

	/* END GETTERS AND SETTERS*/
	
	@Override
	public Collection<TreeNode<C>> getDescendants(TreeNode<C> tNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TreeNode<C>> getAncestors(TreeNode<C> tNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode<C> getNearestCommonAncestor(TreeNode<C> tNode1, TreeNode<C> tNode2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNodeToTree(TreeNode<C> tNode) {
		if(getRoot() == null){
			createRoot(tNode);
			return;
		}
		allNodes.add(tNode);
		addNewNodeAsLastLeaf(tNode);
		reorderTree();
	}
	
	public void addNodeToTree(TreeNode<C> tNode, TreeOrder orderBy) {
		if(getRoot() == null){
			createRoot(tNode);
			return;
		}
		addNewNodeAsLastLeaf(tNode);
		reorderTree();
	}
	
	private void createRoot(TreeNode<C> tNode) {
		root = tNode;
		root.setLevel(1);
		updateLevelToNodesMap(tNode, 1);
		root.setChildren(new ArrayList<TreeNode<C>>());
		leaves = new ArrayList<TreeNode<C>>();
		leaves.add(root);
		setLastLeaf(root);
	}

	private void addNewNodeAsLastLeaf(TreeNode<C> tNode) {
		int leafCount = getLeaves().size();
		int level = getLastLeaf().getLevel();
		int maxLeafCount = (int)Math.pow(branchingOrder, level - 1);
		
		if(leafCount < maxLeafCount){
			addNewLeafAtCurrentLevel(tNode);
		} else{
			addNewLeafAtNewLevel(tNode);
		}
	}

	private void addNewLeafAtCurrentLevel(TreeNode<C> tNode) {
		TreeNode<C> lastLeaf = getLastLeaf();
		int currentLevel = lastLeaf.getLevel();
		TreeNode<C> parentNode = lastLeaf.getParent();
		if(parentNode.getChildren().size() < branchingOrder){
			parentNode.addChild(tNode);
			tNode.setParent(parentNode);
		}else{
			TreeNode<C> nextParentLevelNode = findNodeNextToParentNode(parentNode);
			nextParentLevelNode.addChild(tNode);
			tNode.setParent(nextParentLevelNode);
		}
		tNode.setLevel(currentLevel);
		updateLevelToNodesMap(tNode, currentLevel);
		tNode.setChildren(new ArrayList<TreeNode<C>>());
		leaves.add(tNode);
		setLastLeaf(tNode);
	}

	private void updateLevelToNodesMap(TreeNode<C> tNode, int currentLevel) {
		List<TreeNode<C>> nodesAtCurrentLevel = levelToNodesMap.get(currentLevel);
		if(nodesAtCurrentLevel == null){
			nodesAtCurrentLevel = new ArrayList<TreeNode<C>>();
		}
		nodesAtCurrentLevel.add(tNode);
		levelToNodesMap.put(currentLevel, nodesAtCurrentLevel);
	}

	private TreeNode<C> findNodeNextToParentNode(TreeNode<C> parentNode) {
		List<TreeNode<C>> nodesAtParentLevel = parentNode.getParent().getChildren();
		int parentNodeIndex = nodesAtParentLevel.indexOf(parentNode);
		TreeNode<C> nextParentLevelNode = nodesAtParentLevel.get(parentNodeIndex + 1);
		return nextParentLevelNode;
	}
	
	private void addNewLeafAtNewLevel(TreeNode<C> tNode) {
		TreeNode<C> parentNode = getLeaves().get(0);
		int newLevel = getLastLeaf().getLevel() + 1;
		parentNode.addChild(tNode);
		tNode.setParent(parentNode);
		leaves = new ArrayList<TreeNode<C>>();
		tNode.setLevel(newLevel);
		updateLevelToNodesMap(tNode, newLevel);
		tNode.setChildren(new ArrayList<TreeNode<C>>());
		leaves.add(tNode);
		setLastLeaf(tNode);
	}

	private void reorderTree(){
		reorderTree(TreeOrder.MIN);
	}
	
	private void reorderTree(TreeOrder orderBy) {
		for(TreeNode<C> leaf : getLeaves()){
			TreeNode<C> parent = leaf.getParent();
			TreeNode<C> child = leaf;
			
			if(orderBy.equals(TreeOrder.MAX)){
				while(parent != null && child.compareTo(parent) < 0){
					swapNodeContent(parent, child);
					child = child.getParent();
					parent = child.getParent();
				}
			} else if (orderBy.equals(TreeOrder.MIN)){
				while(parent != null && child.compareTo(parent) > 0){
					swapNodeContent(parent, child);
					child = child.getParent();
					parent = child.getParent();
				}	
			}
		}
		
		
	}

	private void swapNodeContent(TreeNode<C> parent, TreeNode<C> child) {
		C parentContent = parent.getContent();
		C childContent = child.getContent();
		parent.setContent(childContent);
		child.setContent(parentContent);
	}

	@Override
	public void removeNodeFromTree(TreeNode<C> tNode, TreeOrder treeOrder) {
		// TODO Auto-generated method stub
	}

	@Override
	public String printInOrderTraversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printPostOrderTrasversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printPreOrderTraversal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		String rep = "";
		List<Integer> treeLevelsInAscendingOrder = sortTreeLevelsInAscendingOrder();
		for(int treeLevel : treeLevelsInAscendingOrder){
			rep += "Level " + treeLevel + ": " + levelToNodesMap.get(treeLevel) + "\n";
		}
		return rep;
	}

	private List<Integer> sortTreeLevelsInAscendingOrder() {
		List<Integer> listOfTreeLevels = new ArrayList<Integer>();
		Set<Integer> setOfTreeLevels = levelToNodesMap.keySet();
		Iterator<Integer> iterator = setOfTreeLevels.iterator();
		int treeLevel;
		while(iterator.hasNext()){
			treeLevel = iterator.next();
			listOfTreeLevels.add(treeLevel);
		}
		Collections.sort(listOfTreeLevels);
		return listOfTreeLevels;
	}

}
