/* 
 * HashTableChained.java
 * Alicia Sheng
 * 6/14/2020
 */

package dict;

import list.*;

/**
 * HashTableChained implements a Dictionary as a hash table with chaining. All
 * objects used as keys must have a valid hashCode() method, which is used to
 * determine which bucket of the hash table an entry is stored in. Each object's
 * hashCode() is presumed to return an int between Integer.MIN_VALUE and
 * Integer.MAX_VALUE. The HashTableChained class implements only the compression
 * function, which maps the hash code to a bucket in the table's range.
 *
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

	/**
	 * Place any data fields here.
	 **/
	protected List[] defTable;
	protected int N; // number of buckets
	protected int entries;

	/**
	 * Construct a new empty hash table intended to hold roughly sizeEstimate
	 * entries. (The precise number of buckets is up to you, but we recommend you
	 * use a prime number, and shoot for a load factor between 0.5 and 1.)
	 **/

	public HashTableChained(int sizeEstimate) {
		for (int i = sizeEstimate; i < sizeEstimate * 2; i++) {
			if (isPrime(i)) {
				N = i;
				break;
			} else {
				N = 101;
			}
		}
		defTable = new List[N];
		entries = 0;
	}

	/**
	 * Construct a new empty hash table with a default size. Say, a prime in the
	 * neighborhood of 100.
	 **/

	public HashTableChained() {
		defTable = new List[101];
		N = 101;
		entries = 0;
	}

	/**
	 * Check if a number is prime.
	 */

	public boolean isPrime(int number) {
		boolean result = true;
		if (number == 1) {
			return false;
		}
		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE to a
	 * value in the range 0...(size of hash table) - 1.
	 *
	 * This function should have package protection (so we can test it), and should
	 * be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		int result = Math.abs(code) % N;
		return result;
	}

	/**
	 * Returns the number of entries stored in the dictionary. Entries with the same
	 * key (or even the same key and value) each still count as a separate entry.
	 * 
	 * @return number of entries in the dictionary.
	 **/

	public int size() {
		return entries;
	}

	/**
	 * Tests if the dictionary is empty.
	 *
	 * @return true if the dictionary has no entries; false otherwise.
	 **/

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Create a new Entry object referencing the input key and associated value, and
	 * insert the entry into the dictionary. Return a reference to the new entry.
	 * Multiple entries with the same key (or even the same key and value) can
	 * coexist in the dictionary.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key   the key by which the entry can be retrieved.
	 * @param value an arbitrary object.
	 * @return an entry containing the key and value.
	 **/

	public Entry insert(Object key, Object value) {
		Entry entry = new Entry();
		entry.key = key;
		entry.value = value;
		int hashCode = key.hashCode();
		int bucket = compFunction(hashCode);
		if (defTable[bucket] == null) {
			defTable[bucket] = new DList();
			defTable[bucket].insertBack(entry);
		} else {
			defTable[bucket].insertBack(entry);
		}
		entries++;
		return entry;
	}

	/**
	 * Search for an entry with the specified key. If such an entry is found, return
	 * it; otherwise return null. If several entries have the specified key, choose
	 * one arbitrarily and return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key the search key.
	 * @return an entry containing the key and an associated value, or null if no
	 *         entry contains the specified key.
	 **/

	public Entry find(Object key) {
		int hashCode = key.hashCode();
		int bucket = compFunction(hashCode);
		List list = defTable[bucket];
		Entry entry = null;
		if (list != null) {
			ListNode node = list.front();
			for (int i = 0; i < list.length(); i++) {
				try {
					if (((Entry) node.item()).key().equals(key)) {
						entry = new Entry();
						entry.key = key;
						entry.value = ((Entry) node.item()).value();
						break;
					}
					node = node.next();
				} catch (InvalidNodeException e) {
				}
			}
		}
		return entry;
	}

	/**
	 * Remove an entry with the specified key. If such an entry is found, remove it
	 * from the table and return it; otherwise return null. If several entries have
	 * the specified key, choose one arbitrarily, then remove and return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key the search key.
	 * @return an entry containing the key and an associated value, or null if no
	 *         entry contains the specified key.
	 */

	public Entry remove(Object key) {
		int hashCode = key.hashCode();
		int bucket = compFunction(hashCode);
		List list = defTable[bucket];
		Entry entry = null;
		if (list != null) {
			ListNode node = list.front();
			for (int i = 0; i < list.length(); i++) {
				try {
					if (((Entry) node.item()).key().equals(key)) {
						entry = new Entry();
						entry.key = key;
						entry.value = ((Entry) node.item()).value();
						node.remove();
						break;
					}
					node = node.next();
				} catch (InvalidNodeException e) {
				}
			}
			entries--;
		}
		return entry;
	}

	/**
	 * Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		defTable = new List[N];
		entries = 0;
	}

}
