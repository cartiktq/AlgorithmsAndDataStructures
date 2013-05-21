package trees;

import nodes.BinaryTreeNode;

public class BTree implements IBTree {

	BinaryTreeNode root;
	
	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}

	public void addNewNodeToTree(BinaryTreeNode btNode){
		BinaryTreeNode parent = getRoot();
		BinaryTreeNode leftChild = parent.getLeftChild();
		BinaryTreeNode rightChild = parent.getRightChild();
		
		if(leftChild == null){
			
		}
		
		if (parent.compareTo(btNode) > 0){
			
		}
	}
	
	@Override
	public int binarySearch(BinaryTreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containsNode(BinaryTreeNode node) {
		// TODO Auto-generated method stub
		return false;
	}

}
