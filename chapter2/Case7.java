package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		题目：判断一个链表是否是回文结构
 * 		题目：顺序遍历一遍链表，并将遍历的每个节点存放到一个栈结构中，从栈中依此弹出，即是该链表数字序列的反序。
 *
 */
public class Case7 {

	public boolean isPalindrome1(Node head) {
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while(cur.next != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while(head != null) {
			if(head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
