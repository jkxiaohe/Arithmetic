package chapter2;

/**
 * @author xiaozhu
 *		题目：删除链表的中间节点
 */
public class Case3 {

	public Node removeMidNode(Node head) {
		//异常情况：头节点为Null，或只有一个节点
		if(head==null || head.next==null) {
			return head;
		}
		if(head.next.next == null) {
			return head.next;
		}
		/**
		 * 		pre和cur差2个节点的距离，对应：链表长度每增加2，要删除的节点就后移一个节点。
		 */
		Node pre = head;
		Node cur = head.next.next;
		while(cur.next!=null && cur.next.next!=null) {
			cur = cur.next.next;    //链表长度移动2个单位
			pre = pre.next;          //要删除的节点后移1个单位
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
