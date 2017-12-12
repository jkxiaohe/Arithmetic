package chapter2;

import chapter2.Case6.Node;

/**
 * @author xiaozhu
 * 		��Ŀ�����ε������Լɪ�����⣬ʹ����ѧ�������
 * 		˼·��ͨ���Ա������˺ͽڵ��ŵĹ۲죬��������ѧ����ʽ�ķ�ʽ�����������ڵĹ�ϵ��
 * 				B=(A-1)%i + 1
 * 				B:�ڵ㣬A����ţ�i���ڵ�����
 *
 */
public class Case6Method2 {

	public Node josephusKill2(Node head,int m) {
		if(head==null || head.next==head || m<1) {
			return head;
		}
		Node cur = head.next;
		int tmp = 1;
		while(cur != head) {
			tmp++;
			cur = cur.next;
		}
		tmp = getLive(tmp,m);
		while(--tmp != 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}
	
	public int getLive(int i,int m) {
		if(i == 1) {
			return 1;
		}
		return (getLive(i-1,m)+m-1)%i + 1;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
