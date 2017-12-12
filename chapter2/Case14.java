package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		��Ŀ���ڵ�������ɾ��ָ��ֵ�ýڵ�
 *
 */
public class Case14 {

	/**
	 * 		����1��ʹ�ù���ջ���洢����Ϊ2�����̣�
	 * 			���ڴ洢֮ǰ�����ȶԽڵ���й��ˣ���������Ŀ��Ҫ�󽫷��ϵ�Ԫ�ش�Ž�ȥ��
	 * 			���ڴӹ�����ȡ��ʱ�����¹��������ڵ�֮�����ӵ�˳��
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
			//��һ���ڵ��next��ָ��ǰ�ڵ�
			stack.peek().next = head;
			head = stack.pop();
		}
		return head;
	}
	
	/**
	 * 		�����ҵ���һ��!=num��ͷ�ڵ㣬Ȼ��Ӹýڵ㿪ʼ������������Ա��������е�ÿ���ڵ�����жϡ�
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
