package chapter2;

/**
 * @author xiaozhu
 *		��Ŀ���ڵ������˫������ɾ��������K���ڵ�
 */
public class Case2 {

	public Node removeLastKthNode(Node head,int lastKth) {
		if(head==null || lastKth<1) {
			return head;
		}
		Node cur = head;
		while(cur != null) {
			lastKth--;
			cur = cur.next;
		}
		if(lastKth == 0) {
			head = head.next;
		}
		if(lastKth < 0) {
			cur = head;
			while(++lastKth != 0) {
				cur = cur.next;
			}
			head = cur.next;
		}
		return head;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
