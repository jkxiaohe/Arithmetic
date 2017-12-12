package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		��Ŀ���ж�һ�������Ƿ��ǻ��Ľṹ
 * 		˼·������ǻ��Ľṹ����ô���м�ֿ�������2�����ܹ��غϵģ���ֻ��Ҫ��һ������ִ���ջ�ṹ�У�Ȼ�����һ������ֶԱȼ��ɡ�
 * 		�ҵ���⣺���ۺ�������⣬���Ǵ����ʵ����΢�е���ѧ���㡣
 * 			���ȣ���������Ұ벿��ѹ��ջ�У���ô����Ҫȷ�������Ұ벿���Ǵ���������￪ʼ�ģ�����ʹ��right��cur���첽�ƶ���ȷ����
 * 			��ʼֵ��right=head.next���������ֻ��һ���ڵ㣬rightΪnull����Ӱ������ͬʱright�Ŀ�ʼλ�þ�����ߵ�һ���ڵ����һ����
 *     right = right.next��right��1����λ�ķ�ʽ�ƶ���
 *     		cur=head��cur��ͷ�ڵ㿪ʼ�ƶ���cur����ʼλ�ü����ƶ���ʽ��Ҫȷ���ܹ�ʹ��curֹͣ�ƶ�ʱright���������Ұ벿�ֵ���ʼλ�ã�
 *     cur=cur.next.next������cur���ƶ���ʽ����cur��2����λ�ķ�ʽ�ƶ���
 *     	���ڿ�ʼ֤�����ƶ���ʽ��
 *     		������ĵ�һ���ڵ�ı��Ϊ1����
 *     		curÿ���ƶ��ı�ţ�1��3��5��7��9��11��------------
 *     		rightÿ���ƶ��ı�ţ�2��3��4��5��6��------------
 *     		����cur��right��ͬʱ�ƶ��ģ�������˭��˭������⣬�ٷ�����һ�����������ĳ���Ϊż����������
 *     			���������Ϊż�����ɾ��ȵĻ���Ϊ2�룻�������ȣ��м���Ǹ�����������
 *     		�ƶ��������ж���cur.next!=null && cur.next.next!=null�������Ӳ��ܹ��ж�ÿ�κ�ʱ���ƶ���
 *     		curÿ���ƶ��ĵ�λ��right��1����ô���У���cur�ĳ�ʼλ��Ϊ1��right�ĳ�ʼλ��Ϊ2��
 *     		length=2��cur���ƶ���right���ƶ���cur=1��right=2�����1����λ��
 *     
 *     		length=3��cur�ƶ�2����λ��right�ƶ�1����λ��cur=3,right=3�����0����λ�����չ��м��Ǹ�����λ��
 *     		length=4�����ƶ���ά����һ��״̬��
 *     		length=5��cur�ƶ�2����λ��right�ƶ�1����λ��cur=5,right=4�����1����λ����right��Ȼλ���Ұ벿�ֵ���ʼλ�ã�
 *     		length=6�����ƶ���ά����һ��״̬��
 *     		length=7��cur�ƶ�2����λ��right�ƶ�1����λ��cur=7,right=5�����2����λ����right��Ȼλ���Ұ벿�ֵ���ʼλ�ã�
 *     		length=8�����ƶ���ά����һ��״̬��
 *     		.............
 *     
 *     		ͨ���Զ������ݵĹ۲죬�����Ѿ����֣�
 *     			����������right��Ҫ�ƶ�������ż��������ά����һ��״̬��right��cur���첽�ƶ���ʽ��ʹ��ÿ��żһ�ε����֣�cur����right
 *     		���ƶ�1����λ����������鳤�Ȳ����ɵļ�����⡣
 *
 */
public class Case7Method2 {

	public boolean isPalindrome2(Node head) {
		if(head==null || head.next==null) {
			return true;
		}
		Node right = head.next;
		Node cur = head;
		while(cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		while(right != null) {
			stack.push(right);
			right = right.next;
		}
		while(!stack.isEmpty()) {
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
