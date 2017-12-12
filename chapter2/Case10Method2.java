package chapter2;

/**
 * @author xiaozhu
 * 		题目：题目：两个单链表生成相加链表
 * 		思路：不使用栈结构，
 * 			1.将2个链表逆序；
 * 			2.同步将2个链表的数字相加，同时关注每一步是否有进位；
 * 			3.将2个逆序的链表重新调整为原来的样子。
 *
 */
public class Case10Method2 {

	public  Node addList2(Node head1 , Node head2) {
		head1 = reverseList(head1);
		head2 = reverseList(head2);
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node c1 = head1;
		Node c2 = head2;
		Node pre = null;
		Node node = null;
		while(c1 != null || c2 != null) {
			n1 = c1 != null ? c1.value : 0;
			n2 = c2 != null ? c2.value : 0;
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n%10);
			node .next = pre;
			ca = n / 10;
			c1 = c1 != null ? c1.next : null;
			c2 = c2 != null ? c2.next : null;
		}
		if(ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		reverseList(head1);
		reverseList(head2);
		return node;
	}
	
	public Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while(head != null) {
			next = head.next;
			head.next = null;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
