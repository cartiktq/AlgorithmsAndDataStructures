package lists;

import nodes.Node;

public interface IList<C> {
	public int getLength();
	
	public Node<?> getNodeAtIndex(int index) throws IllegalAccessException;
	public Node<?> getFirstNode();
	public Node<?> getLastNode();
	
	public void removeFirstNode() throws IllegalAccessException;
	public void removeLastNode() throws IllegalAccessException;
	public void removeNodeAtIndex(int index) throws IllegalAccessException;
	
	public void addNodeToFront(C nodeContent);
	public void addNodeToEnd(C nodeContent);
	public void addNodeAtIndex(C nodeContent, int index);
}
