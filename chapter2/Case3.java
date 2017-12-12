package chapter2;

/**
 * @author xiaozhu
 *		��Ŀ��ɾ��������м�ڵ�
 */
public class Case3 {

	public Node removeMidNode(Node head) {
		//�쳣�����ͷ�ڵ�ΪNull����ֻ��һ���ڵ�
		if(head==null || head.next==null) {
			return head;
		}
		if(head.next.next == null) {
			return head.next;
		}
		/**
		 * 		pre��cur��2���ڵ�ľ��룬��Ӧ��������ÿ����2��Ҫɾ���Ľڵ�ͺ���һ���ڵ㡣
		 */
		Node pre = head;
		Node cur = head.next.next;
		while(cur.next!=null && cur.next.next!=null) {
			cur = cur.next.next;    //�������ƶ�2����λ
			pre = pre.next;          //Ҫɾ���Ľڵ����1����λ
		}
		return pre;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
