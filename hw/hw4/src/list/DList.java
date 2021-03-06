/* 
 * DList.java
 * Alicia Sheng
 * 6/7/2020
 */

package list;

/**
 * A DList is a mutable doubly-linked list ADT. Its implementation is
 * circularly-linked and employs a sentinel (dummy) node at the head of the
 * list.
 *
 * DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

	/**
	 * head references the sentinel node. size is the number of items in the list.
	 * (The sentinel node does not store an item.)
	 *
	 * DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
	 */

	protected DListNode head;
	protected int size;

	/*
	 * DList invariants: 1) head != null. 2) For any DListNode x in a DList, x.next
	 * != null. 3) For any DListNode x in a DList, x.prev != null. 4) For any
	 * DListNode x in a DList, if x.next == y, then y.prev == x. 5) For any
	 * DListNode x in a DList, if x.prev == y, then y.next == x. 6) size is the
	 * number of DListNodes, NOT COUNTING the sentinel, that can be accessed from
	 * the sentinel (head) by a sequence of "next" references.
	 */

	/**
	 * newNode() calls the DListNode constructor. Use this class to allocate new
	 * DListNodes rather than calling the DListNode constructor directly. That way,
	 * only this method needs to be overridden if a subclass of DList wants to use a
	 * different kind of node.
	 * 
	 * @param item the item to store in the node.
	 * @param prev the node previous to this node.
	 * @param next the node following this node.
	 */
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new DListNode(item, prev, next);
	}

	/**
	 * DList() constructor for an empty DList.
	 */
	public DList() {
		head = newNode(null, null, null);
		head.prev = head;
		head.next = head;
		size = 0;
	}

	/**
	 * isEmpty() returns true if this DList is empty, false otherwise.
	 * 
	 * @return true if this DList is empty, false otherwise. Performance: runs in
	 *         O(1) time.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * length() returns the length of this DList.
	 * 
	 * @return the length of this DList. Performance: runs in O(1) time.
	 */
	public int length() {
		return size;
	}

	/**
	 * insertFront() inserts an item at the front of this DList.
	 * 
	 * @param item is the item to be inserted. Performance: runs in O(1) time.
	 */
	public void insertFront(Object item) {
		head.next = newNode(item, head, head.next);
		head.next.next.prev = head.next;
		size++;
	}

	/**
	 * insertBack() inserts an item at the back of this DList.
	 * 
	 * @param item is the item to be inserted. Performance: runs in O(1) time.
	 */
	public void insertBack(Object item) {
		head.prev = newNode(item, head.prev, head);
		head.prev.prev.next = head.prev;
		size++;
	}

	/**
	 * front() returns the node at the front of this DList. If the DList is empty,
	 * return null.
	 *
	 * Do NOT return the sentinel under any circumstances!
	 *
	 * @return the node at the front of this DList. Performance: runs in O(1) time.
	 */
	public DListNode front() {
		if (size == 0) {
			return null;
		} else {
			return head.next;
		}
	}

	/**
	 * back() returns the node at the back of this DList. If the DList is empty,
	 * return null.
	 *
	 * Do NOT return the sentinel under any circumstances!
	 *
	 * @return the node at the back of this DList. Performance: runs in O(1) time.
	 */
	public DListNode back() {
		if (size == 0) {
			return null;
		} else {
			return head.prev;
		}
	}

	/**
	 * next() returns the node following "node" in this DList. If "node" is null, or
	 * "node" is the last node in this DList, return null.
	 *
	 * Do NOT return the sentinel under any circumstances!
	 *
	 * @param node the node whose successor is sought.
	 * @return the node following "node". Performance: runs in O(1) time.
	 */
	public DListNode next(DListNode node) {
		if (node == null || node.next.equals(head)) {
			return null;
		} else {
			return node.next;
		}
	}

	/**
	 * prev() returns the node prior to "node" in this DList. If "node" is null, or
	 * "node" is the first node in this DList, return null.
	 *
	 * Do NOT return the sentinel under any circumstances!
	 *
	 * @param node the node whose predecessor is sought.
	 * @return the node prior to "node". Performance: runs in O(1) time.
	 */
	public DListNode prev(DListNode node) {
		if (node == null || node.prev.equals(head)) {
			return null;
		} else {
			return node.prev;
		}
	}

	/**
	 * insertAfter() inserts an item in this DList immediately following "node". If
	 * "node" is null, do nothing.
	 * 
	 * @param item the item to be inserted.
	 * @param node the node to insert the item after. Performance: runs in O(1)
	 *             time.
	 */
	public void insertAfter(Object item, DListNode node) {
		if (node != null) {
			node.next = newNode(item, node, node.next);
			node.next.next.prev = node.next;
			size++;
		}
	}

	/**
	 * insertBefore() inserts an item in this DList immediately before "node". If
	 * "node" is null, do nothing.
	 * 
	 * @param item the item to be inserted.
	 * @param node the node to insert the item before. Performance: runs in O(1)
	 *             time.
	 */
	public void insertBefore(Object item, DListNode node) {
		if (node != null) {
			node.prev = newNode(item, node.prev, node);
			node.prev.prev.next = node.prev;
			size++;
		}
	}

	/**
	 * remove() removes "node" from this DList. If "node" is null, do nothing.
	 * Performance: runs in O(1) time.
	 */
	public void remove(DListNode node) {
		if (node != null) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
		}
	}

	/**
	 * toString() returns a String representation of this DList.
	 *
	 * DO NOT CHANGE THIS METHOD.
	 *
	 * @return a String representation of this DList. Performance: runs in O(n)
	 *         time, where n is the length of the list.
	 */
	public String toString() {
		String result = "[  ";
		DListNode current = head.next;
		while (current != head) {
			result = result + current.item + "  ";
			current = current.next;
		}
		return result + "]";
	}
	
	public static void main(String[] args) {
		DList l = new DList();
		System.out.println("### TESTING insertFront ###\nEmpty list is " + l);

		l.insertFront(9);
		System.out.println("\nInserting 9 at front.\nList with 9 is " + l);
		if (!l.head.next.item.equals(9)) {
			System.out.println("head.next.item is wrong.");
		}
		if (!l.head.next.prev.equals(l.head)) {
			System.out.println("head.next.prev is wrong.");
		}
		if (!l.head.prev.item.equals(9)) {
			System.out.println("head.prev.item is wrong.");
		}
		if (!l.head.prev.next.equals(l.head)) {
			System.out.println("head.prev.next is wrong.");
		}
		if (l.size != 1) {
			System.out.println("size is wrong.");
		}
		
		System.out.println("\n### TESTING insertBack ###\nList with 9 is " + l);

		l.insertBack(10);
		System.out.println("\nInserting 10 at back.\nList with 9 and 10 is " + l);
		if (!l.head.prev.item.equals(10)) {
			System.out.println("head.prev.item is wrong.");
		}
		if (!l.head.prev.next.equals(l.head)) {
			System.out.println("head.prev.next is wrong.");
		}
		if (!l.head.next.item.equals(9)) {
			System.out.println("head.next.item is wrong.");
		}
		if (!l.head.next.prev.equals(l.head)) {
			System.out.println("head.next.prev is wrong.");
		}
		if (!l.head.next.next.equals(l.head.prev)) {
			System.out.println("l.head.next.next != l.head.prev.");
		}
		if (!l.head.prev.prev.equals(l.head.next)) {
			System.out.println("l.head.prev.prev != l.head.next.");
		}
		if (l.size != 2) {
			System.out.println("size is wrong.");
		}
		
		System.out.println("\n### TESTING front and back ###\nList with 9 and 10 is " + l);
		System.out.println("\nFront of " + l + " is " + l.front().item);
		if (!l.front().item.equals(9)) {
			System.out.println("front is wrong.");
		}
		System.out.println("Back of " + l + " is " + l.back().item);
		if (!l.back().item.equals(10)) {
			System.out.println("back is wrong.");
		}
		
		l = new DList();		
		System.out.println("\nEmpty list is " + l);
		System.out.println("\nFront of " + l + " is " + l.front());
		if (l.front() != null) {
			System.out.println("front is wrong.");
		}
		System.out.println("Back of " + l + " is " + l.back());
		if (l.back() != null) {
			System.out.println("back is wrong.");
		}
		
		l.insertFront(10);
		l.insertFront(9);
		System.out.println("\n### TESTING next and prev ###\nList with 9 and 10 is " + l);
		System.out.println("\nNext of 9 is " + l.next(l.head.next).item);
		if (!l.next(l.head.next).item.equals(10)) {
			System.out.println("next is wrong.");
		}
		System.out.println("Next of 10 is " + l.next(l.head.next.next));
		if (l.next(l.head.next.next) != null) {
			System.out.println("next is wrong.");
		}
		System.out.println("Prev of 9 is " + l.prev(l.head.next));
		if (l.prev(l.head.next) != null) {
			System.out.println("prev is wrong.");
		}
		System.out.println("Prev of 10 is " + l.prev(l.head.next.next).item);
		if (!l.prev(l.head.next.next).item.equals(9)) {
			System.out.println("prev is wrong.");
		}
		
		System.out.println("\n### TESTING insertBefore ###\nList with 9 and 10 is " + l);
		
		l.insertBefore(8, l.head.next);
		System.out.println("\nInserting 8 before 9.\nList with 8, 9 and 10 is " + l);
		l.insertAfter(11, l.head.prev);
		System.out.println("\nInserting 11 after 10.\nList with 8, 9, 10 and 11 is " + l);
		
		System.out.println("\n### TESTING remove ###\nList with 8, 9, 10 and 11 is " + l);
		
		l.remove(l.head.next.next.next);
		System.out.println("\nRemoving 10 from the list.\nList with 8, 9 and 11 is " + l);

	}
}
