package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ����ת˫������
 * 		˼·���뷴ת������������֮ͨ����ֻ��Ҫ��ÿ���ڵ��last��nextָ��Ľڵ㻥��һ��λ�ü��ɡ�
 *
 */
public class Case4Method2 {

	public DoubleNode reverseList(DoubleNode head) {
		DoubleNode pre  = null;
		DoubleNode next = null;
		while(head != null) {
			//���ȼ�¼��һ���ڵ��λ�ã��ٷ�ת�����pre��cur�ڵ�����ƶ�һ����λ
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	class DoubleNode{
		public int value;
		public DoubleNode last;
		public DoubleNode next;
		public DoubleNode(int data) {
			this.value = data;
		}
	}
	
}
