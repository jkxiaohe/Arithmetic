package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		��Ŀ�����������������������
 * 		˼·�����Խ��������������������е�ÿ�����ֶ�ѹ�뵽ջ�У�Ȼ���ջ�е������������ֵ�˳��ͱ�Ϊ�˴ӵ�λ����λ��
 * 				Ȼ��ÿ�δ�ջ�е���һ�����֣���������ӣ�����н�λ����һ��ר�ŵ���������ʾ��
 * 				���⣬��λ������ֻ������1�������ܳ���1����λ������ӵ����ֵ9+9=18,Ҳֻ�ǽ�1λ���ѡ�
 *
 */
public class Case10 {

	public Node addList1(Node head1,Node head2) {
		//ʹ��2��ջ����ֱ�2�������е�����ֵ���뵽ջ��
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		while(head1 != null) {
			s1.push(head1.value);
			head1 = head1.next;
		}
		while(head2 != null) {
			s2.push(head2.value);
			head2 = head2.next;
		}
		/**
		 * 		���弸��������������������
		 * 		ca:��λֵ��n1:ͬһλ���ϵĵ�һ������ֵ��n2���ڶ�������ֵ��n:2������ͬλ���ֵ
		 */
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node node = null;
		Node pre = null;
		/**
		 * 		ֻҪ2��ջ����Ϊnull���ͼ������㣬�����Ǵӵ�λ��ʼ����ӣ������ں�����Ҫ�Խ�λ�����жϣ�
		 * 	ͬʱ2��ջ�Ĵ�С���ܲ�һ��������ӵ�ʱ��Ҫ�жϵ�ǰջ�Ƿ�ΪNull�����ΪNull����0�����棬����ʹ����Ӳ������
		 * 		ͬʱ��Ҫ���ǽڵ����ɵ����ӷ�ʽ�����Ӧ���ǴӸ�λ���ӵ���λ�������������Ǵӵ�λ��ʼ��ģ�������Ҫ�����ӷ���
		 * 	�����ǣ�
		 * 			ʹ��2���ڵ���������д洢��һ������ָ��ǰ�ڵ㣬����ɵĽڵ����һ�����ڴ����µĽڵ㣬��������µĽڵ㣬
		 * 		Ȼ���½ڵ��next��ָ��ɽڵ����pre�������������Ӿ��γ��ˡ�
		 * 		
		 */
		while(!s1.isEmpty() || !s2.isEmpty()) {
			n1 = s1.isEmpty() ? 0 : s1.pop();
			n2 = s2.isEmpty() ? 0 : s2.pop();
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n%10);
			node.next = pre;
			ca = n / 10;
		}
		//����ѭ��������û�ж����һ����ӽ�λ����������жϣ�������Ҫ����������жϣ����ڽ�λca�Ŀ���ֵ��
		//ҪôΪ0��ҪôΪ1�����Ϊ1����ô��Ҫ�ٴ��´���һ���ڵ���󣬲��������ָ���ָ��
		if(ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		return node;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
