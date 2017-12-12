package chapter2;

/**
 * @author xiaozhu
 * 		题目：删除链表的a/b处的节点
 * 		思路：a/b * b ，能够得到一个数字，对该数字向上取整，即是所应删除的区间的右端的位置。
 *
 */
public class Case3Method2 {

	public Node removeByRatio(Node head,int a,int b) {
		if(a<1 || a>b) {
			return head;
		}
		int n = 0;
		Node cur = head;
		//查找当前链表中共有多少个节点
		while(cur != null) {
			n++;
			cur = cur.next;
		}
		n = (int)Math.ceil((double)(a * n) / (double)b);
		if(n == 1) {
			head = head.next;
		}
		if(n > 1) {
			cur = head;
			while(--n != 1) {
				cur = cur.next;
			}
			head = cur;
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
