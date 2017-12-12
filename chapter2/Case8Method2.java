package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ������������ĳֵ����ΪС�д�
 *		˼·������������ֵ������Ϊ3����ͬ����������ԭ�����ֱ������벻ͬ�������У���Ϊ3�����裺
 *				��1��������3�������乫�������Ǳ���Ҫ��һ��ͷ�ڵ��β�ڵ㣬ͷ�ڵ������洢һ������Ŀ�ͷ�����ں����ı�������β�ڵ�����
 *		��ӽڵ㵽�����У���������������ƶ�������������ȷ���˷�������Ҫ����ı���������Щ��
 *				��2���ٽ��ڵ���ӵ�������ʱ����Ȼ��Ҫһ��ע��ĵط�������
 *					�ٸ������п��ܲ����ڣ������ڶ��������ʼ����ʱ���Ҫ������Ϊnull�����������жϣ�
 *					�ڸ�����ֻ��һ���ڵ㣬ͷβ��ָ��һ������ʱ��Ҫ��head��tail��ָ��ͬһ���ڵ㣬��head==null��ʱ����ӽ�ȥ��
 *					�۸������ж���ڵ㣬��ʱ��Ҫʹ��β�ڵ�tail���µĽڵ�Ԫ�ز��ϵ���ӵ������У�ͷ�ڵ���Ҫά��ԭ���󲻱䣬��Ϊ��Ҫʹ��
 *			ͷ�ڵ����������еĽڵ����
 *				��3����Ԫ�ض�����˳���ŵ���Ӧ�������к���Ҫ���������������˴��������ӵĹ���
 *						�����ӵķ�ʽ��ͷβ�����������ӵĹ����У���һ�������β�ڵ����ΪNull���˴�ֱ��pass����ʼ������������ӣ�
 *						������ϸ�����ΪNull����ô��β�ڵ�tail�ض���Ϊnull����ʱ���Խ������ӣ����ϸ������tail.nextָ��ǰ�����ͷ�ڵ㣻
 *						�ۺ���һ������Ҳ�п���ΪNull���������������Ӳ�������Ȼ��Ҫ�жϵ�ǰ�������Ƿ�ΪNull����������¸����ӵ�ʱ����
 *				�ᱨ��ָ����쳣����Ȼ�Ѿ��ж����ϸ�����Ϊnull���ڵ�ǰ����Ϊnull������£�����ǰ�����β�ڵ�ָ���ϸ������β�ڵ㣬
 *				�����ͱ�����NullPointerException���ڴ�����ֱ��ʹ����Ŀ������������жϺ͸�ֵ��
 *						
 */
public class Case8Method2 {

	public static Node listPartition2(Node head , int pivot) {
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node bH = null;
		Node bT = null;
		Node next = null;
		//�����������еĽڵ����·����ȥ
		//ʹ��3������������3�����ֵ�״̬��������Ǵ�����
		while(head != null) {
			next = head.next;
			head.next = null;
			if(head.value < pivot) {
				if(sH == null) {
					sH = head;
					sT = head;
				}else {
					sT.next = head;
					sT = head;
				}
			}else if(head.value == pivot) {
				if(eH == null) {
					eH = head;
					eT = head;
				}else {
					eT.next = head;
					eT = head;
				}
			}else {
				if(bH == null) {
					bH = head;
					bT = head;
				}else {
					bT.next = head;
					bT = head;
				}
			}
			head = next;
		}
		if(sT != null) {
			sT.next = eH;
			eT = eT == null ? sT : eT;
		}
		if(eT != null) {
			eT.next = bH;
		}
		return sH != null ? sH : eH != null ? eH : bH;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
