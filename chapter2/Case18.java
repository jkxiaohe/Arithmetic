package chapter2;

/**
 * @author xiaozhu
 * 		题目：向有序的环形但链表中插入新节点
 *		思路：首先对该链表存在与否进行分类讨论；
 *			  遍历链表，查找num应该插入的位置，Num的位置node应该要满足：>=上个节点，<=后个节点，依据这个条件去进行比较；
 *			  最后返回最小值节点的时候，应为新插入的节点有可能改变了最小值是头节点的状况，所以要对头节点的值进行一下判断。
 */
public class Case18 {

	public Node insertNum(Node head , int num) {
		Node node = new Node(num);
		//原链表不存在的情况下，当前节点自连接形成一个环
		if(head == null) {
			node.next = node;
			return node;
		}
		//num插入的位置一定是夹在pre和cur中间的，所以按照这个条件去进行比较
		Node pre = head;
		Node cur = head.next;
		while(cur != head) {
			if(pre.value <= num && cur.value >= num) {
				break;
			}
			pre = cur;
			cur = cur.next;
		}
		pre.next = node;
		node.next = cur;
		//最后返回的时候要重新对最小值节点进行判断，因为插入的节点有可能正是尾节点的位置
		return head.value < num ? head : node;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
