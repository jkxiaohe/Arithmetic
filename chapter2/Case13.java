package chapter2;

import java.util.HashSet;

/**
 * @author xiaozhu
 * 		题目：删除无序单链表中值重复的节点
 * 		思路：可以利用hash表的特点，对链表中的节点挨个遍历，并将每一个遍历过的节点存放到hash表中，后续再遍历的时候首先判断hash
 * 	表中是否有相同的值，如果有，说明重复，跳过该节点；如果没有，则将其加入hash表中，并继续遍历下一个。
 *
 */
public class Case13 {

	public void removeRep1(Node head) {
		if(head == null) {
			return;
		}
		//根据set集合的特点：无序，不可重复来判断节点值的唯一性
		HashSet<Integer> set = new HashSet<Integer>();
		//从头节点的下一个节点开始，pre用于存储上一个节点，因为cur当前节点可能会重复，此时pre.next直接指向下一个节点cur.next
		Node pre = head;
		Node cur = head.next;
		set.add(head.value);
		while(cur != null) {
			if(set.contains(cur.value)) {
				pre.next = cur.next;
			}else {
				set.add(cur.value);
				pre = cur;
			}
			cur = cur.next;
		}
	}
	
	public void removeRep2(Node head) {
		Node cur = head;
		Node pre = null;
		Node next = null;
		/**
		 * 		cur代表当前正在比较的对象，pre是用于指向正在遍历的节点的上一个，next用于不断的向下查找结点，
		 * 	需要保证pre和next相差一个节点对象，这样重复的时候方便删除next对象；
		 * 		比较的规则：使用next遍历后面所有的节点，并与当前对象cur.value值进行比较，然后不断的将next移往下一个。
		 */
		while(cur != null) {
			pre = cur;
			next = cur.next;
			while(next != null) {
				if(cur.value == next.value) {
					pre.next = next.next;
				}else {
					pre = next;
				}
				next = next.next;
			}
			cur = cur.next;
		}
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
