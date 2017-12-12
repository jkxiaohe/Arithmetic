package chapter2;

import chapter2.Case9.Node;

/**
 * @author xiaozhu
 * 		题目：复制含有随机指针的节点链表
 * 		思路：利用有限的几个节点的方式来实现，将每一个副本节点插入到当前节点之后
 *
 */
public class Case9Method2 {

	public Node copyListWithRand2(Node head) {
		if(head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		//复制并链接每一个节点
		while(cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node curCopy = null;
		//设置复制节点的rand指针
		while(cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		//将副本节点拆分出来
		while(cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
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
