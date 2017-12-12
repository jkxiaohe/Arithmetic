package chapter2;

/**
 * @author xiaozhu
 * 		题目：判断一个链表是否是回文结构
 * 		方法三：将右半区域反转，都指向中间的节点，然后leftStart和rightStart不断的向中间靠拢，比较其值。
 * 		思路：在方法2的经验之上，可以很轻松的找到中间节点，然后使用3个节点将右半部分的节点指向全部逆向，并记录右半节点的起始位置，
 * 	最后比较，最后要恢复链表的状态，需要记录一下最后的节点，并再次使用3个节点状态来将其恢复。
 *
 */
public class Case7Method3 {

	public boolean isPalindrome3(Node head) {
		if(head==null || head.next==head) {
			return true;
		}
		Node n1 = head;
		Node n2 = head;
		while(n2.next!=null && n2.next.next!=null) {
			n1 = n1.next;
			n2 = n2.next.next;
		}
		n2 = n1.next;
		n1.next = null;
		Node n3 = null;
		while(n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}
		n3 = n1;
		n2 = head;
		boolean res = true;
		while(n1!=null && n2!=null) {
			if(n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		n1 = n3.next;
		n3.next = null;
		while(n1 != null) {
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
