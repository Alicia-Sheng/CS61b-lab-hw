/* 
 * LockDListNode.java
 * Alicia Sheng
 * 6/7/2020
 */

package list;

/**
 *  A LockDListNode is a node in a LockDList.
 */
public class LockDListNode extends DListNode {
	
	protected boolean lock;

	/**
	 * LockDListNode() constructor.
	 * 
	 * @param i the item to store in the node.
	 * @param p the node previous to this node.
	 * @param n the node following this node.
	 */
	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i, p, n);
		lock = false;
	}

}
