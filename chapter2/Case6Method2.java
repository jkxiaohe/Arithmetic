package chapter2;

import chapter2.Case6.Node;

/**
 * @author xiaozhu
 * 		题目：环形单链表的约瑟夫问题，使用数学方法解决
 * 		思路：通过对报数的人和节点编号的观察，可以用数学方程式的方式来发现其内在的关系。
 * 				B=(A-1)%i + 1
 * 				B:节点，A：编号，i：节点总数
 *
 */
public class Case6Method2 {

	public Node josephusKill2(Node head,int m) {
		if(head==null || head.next==head || m<1) {
			return head;
		}
		Node cur = head.next;
		int tmp = 1;
		while(cur != head) {
			tmp++;
			cur = cur.next;
		}
		tmp = getLive(tmp,m);
		while(--tmp != 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}
	
	public int getLive(int i,int m) {
		if(i == 1) {
			return 1;
		}
		return (getLive(i-1,m)+m-1)%i + 1;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
