package chapter2;

import chapter2.Case4.Node;

/**
 * @author xiaozhu
 * 		��Ŀ����ת���ֵ�������
 * 		˼·����Ϊ2��:1.��from��to��Χ�ڵĽڵ㷴ת��2.����������ڵ��ָ��
 * 				�ڵ�2���У��ַ�Ϊ��2�������
 *
 */
public class Case5 {

	public Node reversePart(Node head,int from,int to) {
		int len = 0;     //��¼����ĳ���
		//��ת������Ҫ3���ڵ���������node1����������������Ϊ��ǰ�ڵ����
		Node node1 = head;      
		//fPre��tPos��ʼֵΪNull�����������������ж�
		Node fPre = null;
		Node tPos = null;
		/**
		 * 		��ѭ����Ҫ�ҳ�fPre��ǰһ���ڵ㣬��tPos�ĺ�һ���ڵ㣬ͬʱ�Է�ת����������жϡ�
		 * 		node1�����������������е����֣���fPre��tPos��������ĳ����Χ�ڵĽڵ㣬��ôlen��������Ĺ����У����from��to
		 * 	�Ϸ�����ôһ����ĳ��ʱ�����len == from����to����ͬʱ��Ϊ�������ǰ�������������һ�������fPreΪfrom��ǰһ���ڵ㣬
		 * 	tPosΪto�ĺ�һ���ڵ㣬���from��ͷ�ڵ㣬��ʱfrom-1 < 1,�ǲ������len==from-1������ģ���ôfPre����δNull��˵����
		 * 	��ת�����ǰ���ͷ�ڵ�ģ����������fPre�϶��ܵõ�һ����Чֵ���������������ת�ͺ��ˡ�
		 */
		while(node1 != null) {
			len++;
			fPre = len==from-1 ? node1 : fPre;
			tPos = len==to+1 ? node1 : tPos;
			node1 = node1.next;
		}
		//from��to�����˷�ת����˺��Ҷˣ��������ж��쳣�������ΪҪ�õ�������len�����Է���������
		if(from > to || from < 1 || to > len) {
			return head;
		}
		/**
		 * 		��ʼ��������з�ת�����ݷ�ת��2�ֿ��������ʹ����Ŀ�������������жϣ�
		 * 		��fPre==null��˵����ת�Ĳ��ְ���ͷ�ڵ㣬��node1(��ǰ�ڵ�)Ϊͷ�ڵ�head;
		 * 		��fPre!=null ��˵����ת���ֲ�����ͷ�ڵ㣬��node1ΪfPre����һ���ڵ㣬����Ҫ��ת�ڵ�Ŀ�ʼ���֡�
		 * 		node2��next����ת������Ҫ3���ڵ������������node2����ǰ�ڵ㣬next������һ����node1������һ��
		 */
		node1 = fPre==null ? head : fPre.next;
		Node node2 = node1.next;
		
		//������2������е���һ��������Ҫ��from�ڵ�ָ��to�ĺ�һ���ڵ㣬�ò�������ִ��
		node1.next = tPos;
		
		Node next = null;
		while(node2 != tPos) {
			next = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = next;
		}
		//������㣬˵��������Χ�ڵķ�ת�Ѿ���ɣ�����from�ڵ�ָ�򵽷�ת�����һ������ʱnode1�Ѿ��ƶ����ˣ�
		if(fPre != null) {
			fPre.next = node1;
			return head;
		}
		//fPre==null��˵��ͷ�ڵ�Ҳ��ת�ˣ���ʱNode1��Ϊ��ת�����һ���ڵ㣬��Ϊ��ת���ͷ�ڵ�
		return node1;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
