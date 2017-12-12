package chapter2;

/**
 * @author xiaozhu
 * 		题目：反转单向链表
 * 		思路：使用3个节点来描述链表的状态：
 * 				pre：上一个，cur：当前，next：下一个
 * 				令cur.next = pre;即将cur节点的指向指向来上一个节点，然后将pre,cur,next都向后挪移一个单位，继续更改cur的指向，
 * 			最后结束的标志是：next已经为Null，cur在最后一个节点处，更改完指向后，将next给了cur,cur为null，结束。
 *
 */
public class Case4 {
 
	public Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		//head==null，是反向链表结束的标志
		while(head != null) {
			next = head.next;    //next向前移动
			head.next = pre;     //更改当前节点指针的指向为上一个，第一个节点的指向应该为null ,所以开始的时候令pre为null
			//指向更改完成后，将pre,head（cur）都向后移动一个单位
			pre = head;
			head = next;
		}
		//在循环结束的时候，head为Null，pre为链表的最后一个节点
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
