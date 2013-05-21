package nodes;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<C extends Comparable<C>> implements INode<C>, Comparable<TreeNode<C>>{

	private int id, level;
	private C content;

	private TreeNode<C> parent;
	private List<TreeNode<C>> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public C getContent() {
		return content;
	}

	public void setContent(C c) {
		content = c;
	}

	public TreeNode<C> getParent() {
		return parent;
	}

	public void setParent(TreeNode<C> parent) {
		this.parent = parent;
	}

	public List<TreeNode<C>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode<C>> children) {
		this.children = children;
	}
	
	public void addChild(TreeNode<C> child){
		List<TreeNode<C>> children = getChildren();
		if(children == null){
			children = new ArrayList<TreeNode<C>>();
		}
		children.add(child);
		setChildren(children);
	}
	
	public boolean isTheRoot(){
		return (getParent() == null);
	}
	
	public boolean isALeaf(){
		return (getChildren().size() == 0);
	}
	
	@Override
	public String toString() {
		return "Tree Node containining " + content;		
	}

	@Override
	public int hashCode() {
		final int prime = 32;
		int result = 11;
		result = prime * result + Integer.parseInt(content.toString());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode<C> other = (TreeNode<C>) obj;
		if (content == other.content && id == other.id)
			return true;
		return false;
	}

	@Override
	public int compareTo(TreeNode<C> arg0) {
		return this.getContent().compareTo(arg0.getContent());
	}

}
