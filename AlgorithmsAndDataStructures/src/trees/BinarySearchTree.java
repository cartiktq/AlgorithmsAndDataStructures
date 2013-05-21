package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import nodes.BinaryTreeNode;
import exceptions.EmptyTreeException;
import exceptions.NodeNotFoundException;

public class BinarySearchTree<C extends Comparable<C>> {

	protected BinaryTreeNode<C> root;
	protected List<BinaryTreeNode<C>> nodeList, orderedNodeList;
	
	public BinarySearchTree(){
		nodeList = new ArrayList<BinaryTreeNode<C>>();
		orderedNodeList = new ArrayList<BinaryTreeNode<C>>();
	}

	/* GETTERS AND SETTERS*/
	public BinaryTreeNode<C> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<C> root) {
		this.root = root;
	}

	public List<BinaryTreeNode<C>> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<BinaryTreeNode<C>> nodeList) {
		this.nodeList = nodeList;
	}
	/*END GETTERS AND SETTERS*/
	
	public boolean isEmpty(){
		return (root == null);
	}	
	
	public boolean isTheRoot(BinaryTreeNode<C> node){
		return getRoot().equals(node);
	}
	
	public boolean isALeaf(BinaryTreeNode<C> node){
		return (node.getChildren().size() == 0);
	}
	
	public void addGenericNodes(C[] genericArray){
		for(C generic : genericArray){
			BinaryTreeNode<C> genericNode = new BinaryTreeNode<C>();
			genericNode.setContent(generic);
			orderedNodeList.add(genericNode);
		}
		populateNodeList();
	}
	
	public void addNewNode(BinaryTreeNode<C> newNode){
		orderedNodeList.add(newNode);
		populateNodeList();
	}
	
	private void populateNodeList(){
		Collections.sort(orderedNodeList);
		int outputArraySize = this.calculateSizeOfOutputArray(orderedNodeList);
		BinaryTreeNode<C>[] outputArray = new BinaryTreeNode[outputArraySize];
		int medianPosition = findMedianPosition(0, orderedNodeList.size() - 1);
		BinaryTreeNode<C> median = orderedNodeList.get(medianPosition);
		outputArray[0] = median;
		setRoot(median);
		outputArray = addOtherNodes(orderedNodeList, outputArray, median, 0, 0, medianPosition - 1);
		outputArray = addOtherNodes(orderedNodeList, outputArray, median, 0, medianPosition + 1, orderedNodeList.size() - 1);
		nodeList = Arrays.asList(outputArray);
	}
	
	private BinaryTreeNode<C>[] addOtherNodes(List<BinaryTreeNode<C>> inputList, BinaryTreeNode<C>[] outputArray, 
			BinaryTreeNode<C> lastMedian, int position, int start, int end){
		if(end - start + 1 > 0){
			int medianPosition = findMedianPosition(start, end);
			int indexOnArray = 0;
			BinaryTreeNode<C> newMedian = inputList.get(medianPosition);
			if(newMedian.compareTo(lastMedian) < 0){
				indexOnArray = 2*position + 1;
				lastMedian.setLeftChild(newMedian);
			} else if (newMedian.compareTo(lastMedian) > 0){
				indexOnArray = 2*position + 2;
				lastMedian.setRightChild(newMedian);
			} else {
				addNewMedianToEquivalentNodesListOfOldMedianIfNecessary(newMedian, lastMedian);
			}
			outputArray[indexOnArray] = newMedian;
			newMedian.setParent(lastMedian);
			outputArray = addOtherNodes(inputList, outputArray, newMedian, indexOnArray, start, medianPosition - 1);
			outputArray = addOtherNodes(inputList, outputArray, newMedian, indexOnArray, medianPosition + 1, end); 
		}
		return outputArray;
	}
	
	private void addNewMedianToEquivalentNodesListOfOldMedianIfNecessary(
			BinaryTreeNode<C> newMedian, BinaryTreeNode<C> lastMedian) {
		String newMedianName = newMedian.getName();
		String lastMedianName = lastMedian.getName();
		if(newMedianName != null && lastMedianName != null && !newMedianName.equals(lastMedianName)){
			List<BinaryTreeNode<C>> equivalentNodeList = lastMedian.getEquivalentNodesList();
			equivalentNodeList.add(newMedian);
			lastMedian.setEquivalentNodesList(equivalentNodeList);
		}
	}

	private int calculateSizeOfOutputArray(List<BinaryTreeNode<C>> inputList) {
		double inSize = inputList.size();
		double level = Math.floor(Math.log(inSize)/Math.log(2.0)) + 1;
		return (int)Math.pow(2.0, level) - 1;
	}
	
	private int findMedianPosition(int start, int end){
		if(start < end){
			int listSize = end - start + 1;
			return start + (listSize - 1)/2;
		}
		return start;
	}	
	
	public void deleteNode(BinaryTreeNode<C> node) throws NodeNotFoundException{
		if(orderedNodeList.contains(node)){
			orderedNodeList.remove(node);
			this.populateNodeList();
		} else {
			throw new NodeNotFoundException("Given node is not in the tree");
		}
	}
	
	public int findNodeInTree(BinaryTreeNode<C> node){
		if(nodeList.contains(node)){
			return nodeList.indexOf(node);
		}
		return -1;
	}
	
	public List<BinaryTreeNode<C>> computePreorderTraversalList() throws EmptyTreeException{
		if(this.isEmpty()){
			throw new EmptyTreeException("No nodes in tree");
		}
		List<BinaryTreeNode<C>> traversalList = new ArrayList<BinaryTreeNode<C>>();
		traversalList.add(root);
		addTraversedNodesPreorder(traversalList, root);
		return traversalList;
	}

	private void addTraversedNodesPreorder(List<BinaryTreeNode<C>> traversalList, BinaryTreeNode<C> root) {
		BinaryTreeNode<C> leftChild = root.getLeftChild();
		BinaryTreeNode<C> rightChild = root.getRightChild();
		if(leftChild != null){
			traversalList.add(leftChild);
			addTraversedNodesPreorder(traversalList, leftChild);
		} 
		if(rightChild != null){
			traversalList.add(rightChild);
			addTraversedNodesPreorder(traversalList, rightChild);
		}
	}
	
	public List<BinaryTreeNode<C>> computeInorderTraversalList() throws EmptyTreeException{
		if(this.isEmpty()){
			throw new EmptyTreeException("No nodes in tree");
		}
		List<BinaryTreeNode<C>> inorderList = new ArrayList<BinaryTreeNode<C>>();
		addTraversedNodesInorder(inorderList, root);
		return inorderList;
	}

	private void addTraversedNodesInorder(List<BinaryTreeNode<C>> inorderList, BinaryTreeNode<C> root) {
		BinaryTreeNode<C> leftChild = root.getLeftChild();
		BinaryTreeNode<C> rightChild = root.getRightChild();
		if(leftChild != null){
			addTraversedNodesInorder(inorderList, leftChild);
		}
		inorderList.add(root);
		if(rightChild != null){
			addTraversedNodesInorder(inorderList, rightChild);
		}
	}
	
}
