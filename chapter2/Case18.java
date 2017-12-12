package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ��������Ļ��ε������в����½ڵ�
 *		˼·�����ȶԸ�������������з������ۣ�
 *			  ������������numӦ�ò����λ�ã�Num��λ��nodeӦ��Ҫ���㣺>=�ϸ��ڵ㣬<=����ڵ㣬�����������ȥ���бȽϣ�
 *			  ��󷵻���Сֵ�ڵ��ʱ��ӦΪ�²���Ľڵ��п��ܸı�����Сֵ��ͷ�ڵ��״��������Ҫ��ͷ�ڵ��ֵ����һ���жϡ�
 */
public class Case18 {

	public Node insertNum(Node head , int num) {
		Node node = new Node(num);
		//ԭ�������ڵ�����£���ǰ�ڵ��������γ�һ����
		if(head == null) {
			node.next = node;
			return node;
		}
		//num�����λ��һ���Ǽ���pre��cur�м�ģ����԰����������ȥ���бȽ�
		Node pre = head;
		Node cur = head.next;
		while(cur != head) {
			if(pre.value <= num && cur.value >= num) {
				break;
			}
			pre = cur;
			cur = cur.next;
		}
		pre.next = node;
		node.next = cur;
		//��󷵻ص�ʱ��Ҫ���¶���Сֵ�ڵ�����жϣ���Ϊ����Ľڵ��п�������β�ڵ��λ��
		return head.value < num ? head : node;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
