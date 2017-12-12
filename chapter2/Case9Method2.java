package chapter2;

import chapter2.Case9.Node;

/**
 * @author xiaozhu
 * 		��Ŀ�����ƺ������ָ��Ľڵ�����
 * 		˼·���������޵ļ����ڵ�ķ�ʽ��ʵ�֣���ÿһ�������ڵ���뵽��ǰ�ڵ�֮��
 *
 */
public class Case9Method2 {

	public Node copyListWithRand2(Node head) {
		if(head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		//���Ʋ�����ÿһ���ڵ�
		while(cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node curCopy = null;
		//���ø��ƽڵ��randָ��
		while(cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		//�������ڵ��ֳ���
		while(cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node rand;
		public Node(int data) {
			this.value = data;
		}
	}
}
