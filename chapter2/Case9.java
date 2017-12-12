package chapter2;

import java.util.HashMap;

/**
 * @author xiaozhu
 * 		题目：复制含有随机指针的节点链表
 *
 */
public class Case9 {

	public Node copyListWithRand1(Node head) {
		//使用Map对象复制每个节点的副本节点
		HashMap<Node,Node> map = new HashMap<Node,Node>();
		Node cur = head;
		while(cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while(cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		//最后返回的是所组成的链表的头节点
		return map.get(head);
	}
	
	class Node{
		public int value;
		public Node next;
		public Node rand;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
