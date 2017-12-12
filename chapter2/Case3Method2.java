package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ��ɾ�������a/b���Ľڵ�
 * 		˼·��a/b * b ���ܹ��õ�һ�����֣��Ը���������ȡ����������Ӧɾ����������Ҷ˵�λ�á�
 *
 */
public class Case3Method2 {

	public Node removeByRatio(Node head,int a,int b) {
		if(a<1 || a>b) {
			return head;
		}
		int n = 0;
		Node cur = head;
		//���ҵ�ǰ�����й��ж��ٸ��ڵ�
		while(cur != null) {
			n++;
			cur = cur.next;
		}
		n = (int)Math.ceil((double)(a * n) / (double)b);
		if(n == 1) {
			head = head.next;
		}
		if(n > 1) {
			cur = head;
			while(--n != 1) {
				cur = cur.next;
			}
			head = cur;
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
