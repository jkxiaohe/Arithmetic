package chapter2;

import java.util.HashMap;

/**
 * @author xiaozhu
 * 		��Ŀ�����ƺ������ָ��Ľڵ�����
 *
 */
public class Case9 {

	public Node copyListWithRand1(Node head) {
		//ʹ��Map������ÿ���ڵ�ĸ����ڵ�
		HashMap<Node,Node> map = new HashMap<Node,Node>();
		Node cur = head;
		while(cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while(cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		//��󷵻ص�������ɵ������ͷ�ڵ�
		return map.get(head);
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
