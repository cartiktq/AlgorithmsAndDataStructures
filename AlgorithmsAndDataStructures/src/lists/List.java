package lists;

import nodes.Node;

public class List<C extends Comparable<C>> implements IList<C> {

	int listLength;
	Node<C> first, last;

	public List(){
		listLength = 0;
		first = null;
		last = first;
	}
	
	@Override
	public int getLength() {
		return listLength;
	}

	@Override
	public Node<C> getNodeAtIndex(int index) throws IllegalAccessException {
		if (listLength == 0) throw new IllegalAccessException("Trying to access a node from an empty list");
		if(index >= listLength || index < 0) throw new IllegalArgumentException("Index cannot equal or exceed length of list or be less than zero");
		if(index == 0) return first;
		if (index == listLength - 1) return last;
		int i = 0;
		Node<C> currentNode = first;
		while(i < index){
			currentNode = currentNode.getNext();
			i++;
		}
		return currentNode;
	}

	@Override
	public Node<C> getFirstNode() {
		return first;
	}

	@Override
	public Node<C> getLastNode() {
		return last;
	}

	@Override
	public void removeFirstNode() throws IllegalAccessException {
		if(listLength == 0) throw new IllegalAccessException("List is empty");
		if(first != null){
			first = first.getNext();
			--listLength;
		}
	}

	@Override
	public void removeLastNode() throws IllegalAccessException {
		if(listLength == 0) throw new IllegalAccessException("List is empty");		
		if(last != null){
			last = last.getPrevious();
			--listLength;
		}
	}

	@Override
	public void removeNodeAtIndex(int index) throws IllegalAccessException {
		if(listLength == 0) throw new IllegalAccessException("List is empty");		
		Node<C> nodeToRemove = this.getNodeAtIndex(index);
		Node<C> previousNode = nodeToRemove.getPrevious();
		Node<C> nextNode = nodeToRemove.getNext();
		previousNode.setNext(nextNode);
		nextNode.setPrevious(previousNode);
		--listLength;
	}

	@Override
	public void addNodeToFront(C newFirstContent) {
		++listLength;
		Node<C> newFirst = new Node<C>();
		newFirst.setContent(newFirstContent);
		if(first == null){
			first = newFirst;
			last = first;
			return;
		}
		Node<C> oldFirst = first;
		first = newFirst;
		newFirst.setNext(oldFirst);
		oldFirst.setPrevious(newFirst);
	}

	@Override
	public void addNodeToEnd(C newLastContent) {
		++listLength;
		Node<C> newLast = new Node<C>();
		newLast.setContent(newLastContent);
		if(last == null){
			last = newLast;
			first = last;
			return;
		}
		Node<C> oldLast = last;
		last = newLast;
		newLast.setPrevious(oldLast);
		oldLast.setNext(newLast);
	}

	@Override
	public void addNodeAtIndex(C nodeContent, int index) {
		Node<C> nodeAtIndex = null;
		Node<C> newNode = new Node<C>();
		newNode.setContent(nodeContent);
		try{
			nodeAtIndex = this.getNodeAtIndex(index);
		} catch(IllegalAccessException iae){
			System.out.println("Problem accessing old node at index");
			iae.printStackTrace();
		}
		if(nodeAtIndex != null){
//			System.out.println("Found node with value: " + nodeAtIndex.getContent() + " at index: " + index);
			Node<C> previousNode = nodeAtIndex.getPrevious();
			newNode.setPrevious(previousNode);
			previousNode.setNext(newNode);
			newNode.setNext(nodeAtIndex);
			nodeAtIndex.setPrevious(newNode);
			++listLength;
		}
	}

	@Override
	public String toString(){
		String rep = "{";
		int i = 0;
		try{
			for(i = 0; i < listLength - 1; i++){
				rep += i + ": " + getNodeAtIndex(i).getContent() + ", " ;
			}
		} catch(IllegalAccessException iae){
			rep = null;
			System.out.println("Problem accessing list elements");
			iae.printStackTrace();
		} finally{
			if(rep != null){
				try{
					rep += i + ": " + getNodeAtIndex(i).getContent() + "}";
				}catch(IllegalAccessException iae){
					rep = null;
					System.out.println("Problem accessing last list element in finally block");
					iae.printStackTrace();
				}finally{
					if(rep == null){
						rep = "{}";
					}
				}
			}
			else{
				rep = "{}";
			}
		}
		return rep;
	}
}
