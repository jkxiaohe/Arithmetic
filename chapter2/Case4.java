package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ����ת��������
 * 		˼·��ʹ��3���ڵ������������״̬��
 * 				pre����һ����cur����ǰ��next����һ��
 * 				��cur.next = pre;����cur�ڵ��ָ��ָ������һ���ڵ㣬Ȼ��pre,cur,next�����Ų��һ����λ����������cur��ָ��
 * 			�������ı�־�ǣ�next�Ѿ�ΪNull��cur�����һ���ڵ㴦��������ָ��󣬽�next����cur,curΪnull��������
 *
 */
public class Case4 {
 
	public Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		//head==null���Ƿ�����������ı�־
		while(head != null) {
			next = head.next;    //next��ǰ�ƶ�
			head.next = pre;     //���ĵ�ǰ�ڵ�ָ���ָ��Ϊ��һ������һ���ڵ��ָ��Ӧ��Ϊnull ,���Կ�ʼ��ʱ����preΪnull
			//ָ�������ɺ󣬽�pre,head��cur��������ƶ�һ����λ
			pre = head;
			head = next;
		}
		//��ѭ��������ʱ��headΪNull��preΪ��������һ���ڵ�
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
