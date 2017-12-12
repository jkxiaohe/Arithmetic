package chapter2;

/**
 * @author xiaozhu
 * 		题目：反转双向链表
 * 		思路：与反转单向链表有相通之处，只需要将每个节点的last和next指向的节点互换一下位置即可。
 *
 */
public class Case4Method2 {

	public DoubleNode reverseList(DoubleNode head) {
		DoubleNode pre  = null;
		DoubleNode next = null;
		while(head != null) {
			//首先记录下一个节点的位置，再反转，最后将pre和cur节点向后移动一个单位
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	class DoubleNode{
		public int value;
		public DoubleNode last;
		public DoubleNode next;
		public DoubleNode(int data) {
			this.value = data;
		}
	}
	
}
