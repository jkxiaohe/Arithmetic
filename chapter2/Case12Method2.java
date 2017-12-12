package chapter2;

import chapter2.Case12.Node;

/**
 * @author xiaozhu
 * 		˼·���뷽��1��˼·��ͬ��ֻ��������Ҫʹ�õ�ջ�ṹ����Ӧ�������˼����������洢��
 *
 */
public class Case12Method2 {

	public Node reverseKNodes2(Node head , int K) {
		if(K < 2) {
			return head;
		}
		Node cur = head;
		Node start = null;
		Node pre = null;
		Node next = null;
		int count = 1;
		while(cur != null) {
			next = cur.next;
			if(count == K) {
				//start���ڴ洢�������еĵ�һ����֮���Ը���Pre����ȡֵ������Ϊpreָ�����¸��ڵ㣬
				//�տ�ʱpre��null����ʱӦ��ѡ��head��Ϊ��ʼ��֮���pre.next�л�ȡ�¸�������Ľڵ�
				start = pre == null ? head : pre.next;
				//head��Ҫ��Ϊ������ת֮���������׽ڵ㷵�صģ�������Ӧ��ֻ����һ��ֵ���Ҳ��ڱ仯
				head = pre == null ? cur : head;
				resign2(pre , start , cur , next);
				//�ϸ�������start�иĶ���pre��start��ͬ����preָ�����ϸ�������������1���ڵ㣬����nextΪ����һ���ڵ㡣
				pre = start;
				count = 0;
			}
			count++;
			cur = next;
		}
		return head;
	}
	
	/**
	 * 		�÷����������������ͬʱҲ��������������еĸ�����������
	 * @param left	��������������һ���ڵ㣬�������еĵ�һ��
	 * @param start����Ҫ������������еĵ�һ���ڵ�
	 * @param end����Ҫ������������е����һ���ڵ�
	 * @param right���ұ�������ĵ�һ���ڵ�
	 * 		ע�⣺��java�У�ֻ��8�л������͵ı�����ֵ���ݣ����ࣨ�������ж������ͣ����ǵ�ַ���ݣ����ԣ��ڸ÷����жԶ���������
	 * 	���и��ģ�����Ӱ�쵽ԭʼ�Ķ��󣬼����������������еĶ�������ݡ�
	 */
	public void resign2(Node left , Node start ,Node end , Node right) {
		/**
		 * 		pre,cur,next����׼�ķ�ת�����3���󣬰���������Ǹ�whileѭ��
		 */
		Node pre = start;
		Node cur = start.next;
		Node next = null;
		//cur���յ������ұ߽磬ͬʱcur�ӵڶ����ڵ㿪ʼ��������Ϊ��һ���ڵ��ָ��������ΪNull�������ר�Ŵ���
		while(cur != right) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		//left�������ϸ����������к�����һ���ڵ㣬����ָ���ұߵĵ�һ��
		if(left != null) {
			left.next = end;
		}
		//ע�����������start���¸��ڵ��ָ��start���¸��ڵ�ֱ��ָ���ұߴ������еĵ�һ��
		start.next = right;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
