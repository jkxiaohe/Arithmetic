package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		题目：在单链表中删除指定值得节点
 *
 */
public class Case14 {

	/**
	 * 		方法1：使用工具栈来存储，分为2个过程：
	 * 			①在存储之前，首先对节点进行过滤，即按照题目的要求将符合的元素存放进去；
	 * 			②在从工具中取出时，重新构建各个节点之间连接的顺序。
	 */
	public Node removeValue1(Node head , int num) {
		Stack<Node> stack = new Stack<Node>();
		while(head != null) {
			if(head.value != num) {
				stack.push(head);
			}
			head = head.next;
		}
		while(!stack.isEmpty()) {
			//上一个节点的next域指向当前节点
			stack.peek().next = head;
			head = stack.pop();
		}
		return head;
	}
	
	/**
	 * 		首先找到第一个!=num的头节点，然后从该节点开始逐个向后遍历，对遍历过程中的每个节点进行判断。
	 */
	public Node removeValue2(Node head , int num) {
		while(head != null) {
			if(head.value != num) {
				break;
			}
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
		while(cur != null) {
			if(cur.value == num) {
				pre.next = cur.next;
			}else {
				pre = cur;
			}
			cur = cur.next;
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
