package chapter2;

/**
 * @author xiaozhu
 * 		题目：单链表的选择排序
 * 		思路：将一个链表上的所有数据进行一个重新的排序，可以使用的方法是：新建一个链表，将原链表中的数据顺次找到最小值，然后
 * 				依次加入到该新链表中，再原链表中寻找最小值的过程中，要确保找到后并将该元素删除，并保证原链表具有连续性，然后新链表依次
 * 				将新的较小值存放到链尾，最后返回新链表的头节点即可。
 *
 */
public class Case16 {

	public static Node selectionSort(Node head) {
		Node tail = null;     //排序部分的尾部
		Node cur = head;    //未排序部分的头部
		//smallPre和small用于在原链表中进行删除节点的操作
		Node smallPre = null;    
		Node small = null;
		while(cur != null) {
			//初始的时候令small=cur，因为最小节点值有可能是头节点，此时smallPre=null，需要特殊进行判断
			small = cur;
			smallPre = getSmallestPreNode(cur);
			//如果不是头节点，正常情况下
			if(smallPre != null) {
				small = smallPre.next;      //记录当前最小节点
				smallPre.next = small.next;    //该步用于将最小节点删除的操作
			}
			//cur是原链表中的头节点，用于查找原链表的最小值，如果small是cur，那么cur此处的节点就不再存在了，从其下一个节点开始
			cur = cur == small ? cur.next : cur;
			//新链表的尾部，head用于存储排好序的头节点，当tail为null时，说明头节点还不存在，此时令head=small
			//如果存在就将新的节点加入到尾部去，并将tail向后挪移一个位置到small处，用于连接下一个节点。
			if(tail == null) {
				head = small;
			}else {
				tail.next = small;
			}
			tail = small;
		}
		return head;
	}
	
	//求出当前链表的最小值的前一个节点
	public static Node getSmallestPreNode(Node head) {
		//在循环的进行中，smallPre和pre分别用于记录small和cur的前一个节点，记录前一个节点的作用是
		//在原链表中将其删除的时候，可以直接跳过当前该节点
		Node smallPre = null;
		Node small = head;
		Node pre = head;
		Node cur = head.next;
		while(cur != null) {
			if(cur.value < small.value) {
				smallPre = pre;
				small = cur;
			}
			pre = cur;
			cur = cur.next;
		}
		return smallPre;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
