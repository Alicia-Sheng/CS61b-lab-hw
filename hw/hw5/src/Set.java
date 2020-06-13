/* 
 * Set.java
 * Alicia Sheng
 * 6/13/2020
 */

import list.*;

/**
 * A Set is a collection of Comparable elements stored in sorted order.
 * Duplicate elements are not permitted in a Set.
 **/
public class Set {

	protected List list;
	
	/**
	 * Set ADT invariants: 1) The Set's elements must be precisely the elements of
	 * the List. 2) The List must always contain Comparable elements, and those
	 * elements must always be sorted in ascending order. 3) No two elements in the
	 * List may be equals().
	 **/

	/**
	 * Constructs an empty Set.
	 *
	 * Performance: runs in O(1) time.
	 **/
	public Set() {
		list = new DList();
	}

	/**
	 * cardinality() returns the number of elements in this Set.
	 *
	 * Performance: runs in O(1) time.
	 **/
	public int cardinality() {
		return list.length();
	}

	/**
	 * insert() inserts a Comparable element into this Set.
	 *
	 * Sets are maintained in sorted order. The ordering is specified by the
	 * compareTo() method of the java.lang.Comparable interface.
	 *
	 * Performance: runs in O(this.cardinality()) time.
	 **/
	public void insert(Comparable c){
		if (cardinality() == 0) {
			list.insertFront(c);
		} else {
			ListNode node = list.front();
			for (int i = 0; i < cardinality(); i++) {
				try {
					if (((Comparable) node.item()).compareTo(c) < 0 && i == cardinality() - 1) {
						node.insertAfter(c);
						return;
					} else if (((Comparable) node.item()).compareTo(c) < 0) {
						node = node.next();
					} else if (((Comparable) node.item()).compareTo(c) > 0) {
						node.insertBefore(c);
						return;
					} else {
						return;
					}
				} catch (InvalidNodeException e) {
				}
			}
		}
	}

	/**
	 * union() modifies this Set so that it contains all the elements it started
	 * with, plus all the elements of s. The Set s is NOT modified. Make sure that
	 * duplicate elements are not created.
	 *
	 * Performance: Must run in O(this.cardinality() + s.cardinality()) time.
	 *
	 * Your implementation should NOT copy elements of s or "this", though it will
	 * copy _references_ to the elements of s. Your implementation will create new
	 * _nodes_ for the elements of s that are added to "this", but you should reuse
	 * the nodes that are already part of "this".
	 *
	 * DO NOT MODIFY THE SET s. DO NOT ATTEMPT TO COPY ELEMENTS; just copy
	 * _references_ to them.
	 **/
	public void union(Set s) {
		try {
			ListNode node1 = this.list.front();
			ListNode node2 = s.list.front();
			int size1 = this.cardinality();
			int size2 = s.cardinality();
			if (size2 == 0) {
			} else if (size1 == 0) {
				this.list = s.list;
			} else {
				int i = 0;
				int j = 0;
				while (i < size1 && j < size2) {
					if (((Comparable) node1.item()).compareTo((Comparable) node2.item()) < 0) {
						node1 = node1.next();
						i++;
					} else if (((Comparable) node1.item()).compareTo((Comparable) node2.item()) > 0) {
						node1.insertBefore(node2.item());
						node2 = node2.next();
						j++;
					} else {
						node2 = node2.next();
						j++;
					}
				} if (i == size1 && j < size2) {
					for (int a = j; a < size2; a++) {
						this.list.insertBack(node2.item());
						node2 = node2.next();
					}
				}
			}
		} catch (InvalidNodeException e) {
		}
	}

	/**
	 * intersect() modifies this Set so that it contains the intersection of its own
	 * elements and the elements of s. The Set s is NOT modified.
	 *
	 * Performance: Must run in O(this.cardinality() + s.cardinality()) time.
	 *
	 * Do not construct any new ListNodes during the execution of intersect. Reuse
	 * the nodes of "this" that will be in the intersection.
	 *
	 * DO NOT MODIFY THE SET s. DO NOT CONSTRUCT ANY NEW NODES. DO NOT ATTEMPT TO
	 * COPY ELEMENTS.
	 **/
	public void intersect(Set s) {
		try {
			ListNode node1 = this.list.front();
			ListNode node2 = s.list.front();
			ListNode remove;
			int size1 = this.cardinality();
			int size2 = s.cardinality();
			if (size1 == 0) {
			} else if (size2 == 0) {
				this.list = new DList();
			} else {
				int i = 0;
				int j = 0;
				while (i < size1 && j < size2) {
					if (((Comparable) node1.item()).compareTo((Comparable) node2.item()) < 0) {
						remove = node1;
						node1 = node1.next();
						remove.remove();
						i++;
					} else if (((Comparable) node1.item()).compareTo((Comparable) node2.item()) > 0) {
						node2 = node2.next();
						j++;
					} else {
						node1 = node1.next();
						node2 = node2.next();
						i++;
						j++;
					}
				} if (i < size1 && j == size2) {
					for (int a = i; a < size1; a++) {
						remove = node1;
						node1 = node1.next();
						remove.remove();
					}
				}
			}
		} catch (InvalidNodeException e) {
		}
	}

	/**
	 * toString() returns a String representation of this Set. The String must have
	 * the following format: { } for an empty Set. No spaces before "{" or after
	 * "}"; two spaces between them. { 1 2 3 } for a Set of three Integer elements.
	 * No spaces before "{" or after "}"; two spaces before and after each element.
	 * Elements are printed with their own toString method, whatever that may be.
	 * The elements must appear in sorted order, from lowest to highest according to
	 * the compareTo() method.
	 *
	 * WARNING: THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS FORMAT,
	 * RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS. ANY DEVIATIONS WILL LOSE POINTS.
	 **/
	public String toString() {
		if (cardinality() == 0) {
			return "{ }";
		} else {
			String result = "{ ";
			ListNode node = list.front();
			for (int i = 0; i < cardinality(); i++) {
				try {
					result += node.item() + " ";
					node = node.next();
				} catch (InvalidNodeException e) {
				}
			}
			result += "}";
			return result;
		}
		
	}

	public static void main(String[] argv) throws InvalidNodeException {
		Set s = new Set();
		s.insert(new Integer(3));
		s.insert(new Integer(4));
		s.insert(new Integer(3));
		System.out.println("Set s = " + s);

		Set s2 = new Set();
		s2.insert(new Integer(4));
		s2.insert(new Integer(5));
		s2.insert(new Integer(5));
		System.out.println("Set s2 = " + s2);

		Set s3 = new Set();
		s3.insert(new Integer(5));
		s3.insert(new Integer(3));
		s3.insert(new Integer(8));
		System.out.println("Set s3 = " + s3);

		s.union(s2);
		System.out.println("After s.union(s2), s = " + s);

		s.intersect(s3);
		System.out.println("After s.intersect(s3), s = " + s);

		System.out.println("s.cardinality() = " + s.cardinality());
	}
}
