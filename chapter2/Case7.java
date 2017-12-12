package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		��Ŀ���ж�һ�������Ƿ��ǻ��Ľṹ
 * 		��Ŀ��˳�����һ����������������ÿ���ڵ��ŵ�һ��ջ�ṹ�У���ջ�����˵��������Ǹ������������еķ���
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
