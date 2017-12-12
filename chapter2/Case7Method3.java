package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ���ж�һ�������Ƿ��ǻ��Ľṹ
 * 		�����������Ұ�����ת����ָ���м�Ľڵ㣬Ȼ��leftStart��rightStart���ϵ����м俿£���Ƚ���ֵ��
 * 		˼·���ڷ���2�ľ���֮�ϣ����Ժ����ɵ��ҵ��м�ڵ㣬Ȼ��ʹ��3���ڵ㽫�Ұ벿�ֵĽڵ�ָ��ȫ�����򣬲���¼�Ұ�ڵ����ʼλ�ã�
 * 	���Ƚϣ����Ҫ�ָ������״̬����Ҫ��¼һ�����Ľڵ㣬���ٴ�ʹ��3���ڵ�״̬������ָ���
 *
 */
public class Case7Method3 {

	public boolean isPalindrome3(Node head) {
		if(head==null || head.next==head) {
			return true;
		}
		Node n1 = head;
		Node n2 = head;
		while(n2.next!=null && n2.next.next!=null) {
			n1 = n1.next;
			n2 = n2.next.next;
		}
		n2 = n1.next;
		n1.next = null;
		Node n3 = null;
		while(n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}
		n3 = n1;
		n2 = head;
		boolean res = true;
		while(n1!=null && n2!=null) {
			if(n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		n1 = n3.next;
		n3.next = null;
		while(n1 != null) {
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
		}
		return res;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
