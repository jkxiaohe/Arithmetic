package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ�����ε������Լɪ������
 *
 */
public class Case6 {

	public Node josephusKilll(Node head,int m) {
		if(head==null || head.next==head || m<1) {
			return head;
		}
		//ʹ��last�ڵ�����¼head��ǰһ��������������һ��ͷ�ڵ�
		Node last = head;
		while(last.next != head) {
			last = last.next;
		}
		int count = 0;
		/**
		 * 		��last��head�ڵ�ʼ�����һ���ڵ��λ�ã�Ȼ�����count!=m,��head��lastͬʱ��ǰ�ƶ���
		 * 	�����ʱcount==m,��ô��ǰ�ýڵ㣨head���Ľڵ㣩Ӧ��ɾ������last.next���ݴ�head����һ���ڵ㣬Ȼ��Head��last
		 * 	����ǰ�ƶ�һ����
		 * 		��last�Ѿ��洢��head����һ�����ٽ�Last�е�next����head���ɣ����ܹ���head=head.next,��Ϊ��ʱ��Head����Ϊ�Ǹ�Ҫɾ���Ľڵ㡣
		 */
		while(head != last) {
			if(++count == m) {
				last.next = head.next;
				count = 0;
			}else {
				last = last.next;
			}
			head = last.next;
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
