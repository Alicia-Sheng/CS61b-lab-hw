/* 
 * LockDList.java
 * Alicia Sheng
 * 6/7/2020
 */

package list;

public class LockDList extends DList{
	
	/**
	 * lockNode() permanently locks the node.
	 * 
	 * @param node the node to be locked.
	 */
	public void lockNode(DListNode node) {
		((LockDListNode) node).lock = true;
	}
	
	/**
	 * newNode() calls the LockDListNode constructor.
	 * 
	 * @param item the item to store in the node.
	 * @param prev the node previous to this node.
	 * @param next the node following this node.
	 */	
	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}
	
	/**
	 * LockDList() constructor for an empty LockDList.
	 */
	public LockDList() {
		super();
	}

	/**
	 * remove() removes "node" from this LockDList. If "node" is null, do nothing.
	 * Performance: runs in O(1) time.
	 */
	public void remove(DListNode node) {
		if (node != null && !((LockDListNode) node).lock) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
		} else if (((LockDListNode) node).lock) {
			System.out.println("Error! This node is locked.");
		}
	}
	
	public static void main(String[] args) {
		DList l = new LockDList();
		l.insertBack(1);
		System.out.println("### TESTING LockDList ###\nList with 1 is " + l);
		System.out.println("\nSet 1 to be locked.");
		((LockDList) l).lockNode(l.head.next);
		System.out.println("Try to remove 1 from the list.");
		l.remove(l.head.next);
	}

}
