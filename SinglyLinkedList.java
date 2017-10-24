package stack_maze;

public class SinglyLinkedList<E> {
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;

	public SinglyLinkedList() {
	}

	public int size(){
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if(isEmpty()) 
			return null;

		return this.head.getElement();
	}

	public E last() {
		if(isEmpty()) 
			return null;

		return this.tail.getElement();
	}

	public void addFirst(E e ) {
		if(size == 0) {
			head = new Node<E>(e, null);
			tail = head;

		} 
		else {  
			Node<E> newNode = new Node<>(e, head);
			head = newNode;
		}

		size++;
	}

	public void addLast(E e) {
		Node<E> newNode = new Node<>(e, null);

		if (isEmpty()) {
			head = newNode;
		}
		else 
			tail.setNext(newNode);

		tail = newNode;
		size++;
	}

	public E removeFirst() {
		if(isEmpty())
			return null;
		
		E answer = head.getElement();
		size--;
		
		if (head.getNext() != null)
			head = head.getNext();
		else
			tail = head = null;
		
		return answer;
	}

	@SuppressWarnings("hiding")
	class Node<E> {
		private E element;
		private Node<E> next;

		public Node(E E, Node<E> n) {
			this.element = E;
			this.next = n;
		}

		public E getElement() {
			return this.element;
		}

		public Node<E> getNext() {
			return this.next;
		}

		public void setNext(Node<E> n) {
			this.next = n;
		}
	}

}