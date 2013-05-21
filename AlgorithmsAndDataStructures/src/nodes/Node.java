package nodes;

public class Node<C extends Comparable<C>> implements INode<C>, Comparable<Node<C>>{

	private C content;
	private Node<C> previous, next;
	
	
	public Node<C> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<C> previous) {
		this.previous = previous;
	}

	public Node<C> getNext() {
		return next;
	}

	public void setNext(Node<C> next) {
		this.next = next;
	}

	public C getContent() {
		return content;
	}

	public void setContent(C c){
		content = c;
	}
	@Override
	public String toString() {
		return "Node containining " + content;		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(obj instanceof Node){
			Node<?> other = (Node<?>) obj;
			if (content != other.content)
				return false;
		}
		return true;
	}

	@Override
	public int compareTo(Node<C> arg0) {
		return this.getContent().compareTo(arg0.getContent());
	}
	
	
}
