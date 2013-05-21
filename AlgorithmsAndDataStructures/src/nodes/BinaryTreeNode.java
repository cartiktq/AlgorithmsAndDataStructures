package nodes;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNode<C extends Comparable<C>> 
			implements Comparable<BinaryTreeNode<C>>{
	
	private BinaryTreeNode<C> parent, leftChild, rightChild;
	private C content;
	private String name;
	/**This is a pointer to a list of nodes with identical content but different names**/
	private List<BinaryTreeNode<C>> equivalentNodesList;
	
	/* GETTERS AND SETTERS */
	public BinaryTreeNode<C> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<C> parent) {
		this.parent = parent;
	}

	public BinaryTreeNode<C> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode<C> leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode<C> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode<C> rightChild) {
		this.rightChild = rightChild;
	}


	public C getContent() {
		return content;
	}

	public void setContent(C content) {
		this.content = content;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public List<BinaryTreeNode<C>> getEquivalentNodesList(){
		return this.equivalentNodesList;
	}
	
	public void setEquivalentNodesList(List<BinaryTreeNode<C>> enl){
		this.equivalentNodesList = enl;
	}
	
	/*END OF GETTERS AND SETTERS*/
	
	public List<BinaryTreeNode<C>> getChildren(){
		List<BinaryTreeNode<C>> children = new ArrayList<BinaryTreeNode<C>>();
		children.add(getLeftChild());
		children.add(getRightChild());
		return children;
	}
	
	public boolean isTheRoot() {
		return (getParent() == null);
	}

	public boolean isALeaf() {
		return (getChildren() == null || getChildren().size() == 0);
	}

	@Override
	public int compareTo(BinaryTreeNode<C> btn) {
		return this.getContent().compareTo(btn.getContent());
	}

	@Override
	public boolean equals(Object other){
		if(other != null && other instanceof BinaryTreeNode){
			BinaryTreeNode<C> otherNode = (BinaryTreeNode<C>)other;
			C thisContent = this.getContent();
			C otherContent = otherNode.getContent();
			String thisName = this.getName();
			String otherName = otherNode.getName();
			if(thisContent != null && otherContent != null && 
					thisContent.equals(otherContent)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Node containining " + content;		
	}		
}
