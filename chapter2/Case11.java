package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ�������������ཻ��һϵ������
 * 		˼·��
 * 			1.���Ƚ������ཻ��������з��ࣺ
 * 				��2�������޻�����2�������ǵ�������������й�ͬ�Ĳ��֣�
 * 				��2�������л����뻷�Ľڵ㲻ȷ���������л��Ĳ�������ͬ�ģ�
 *
 */
public class Case11 {

	public Node getIntersectNode(Node head1 , Node head2) {
		if(head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		//1���л���1���޻������ֽṹ�ǲ������ཻ�ģ����Զ���������������ж�
		if(loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if(loop1 != null && loop2 != null) {
			return bothLoop(head1 , loop1 , head2 , loop2);
		}
		return null;
	}
	
	/**
	 * 		�ж�2���л������Ƿ��ཻ�����ص�һ���ཻ�Ľڵ㡣
	 * @param head1
	 * @param loop1����һ���л�������뻷�ڵ�
	 * @param head2
	 * @param loop2
	 * @return
	 * 		����2���л�������ཻ�������Ϊ��
	 * 			��loop1==loop2,2������Ļ��ṹ�Լ��뻷�ڵ�����ͬ�ģ����ཻ�ڵ���head1->loop1��head2->loop2��һ���ڣ�
	 * 			��loop1 != loop2��2������Ļ��ṹ��ͬ�����뻷�ڵ��ǲ�ͬ�ģ���ʱloop1��(loop2)�ػ�����һȦ��һ�������ҵ�loop2(loop1);
	 */
	public Node bothLoop(Node head1 , Node loop1 , Node head2 , Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		//if���������ж�2�����ṹ���뻷�ڵ��Ƿ���ͬ
		if(loop1 == loop2) {
			//����뻷�ڵ���ͬ��ֻ��Ҫ��2��������ͬ���ȵ�λ�ÿ�ʼ������±������ҵ��׸��غϵĽڵ㼴��
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while(cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while(cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while(n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while(cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}else {
			//2������Ĺ���һ�����ṹ�����Ǹ����뻷�Ľڵ��ǲ�ͬ�ģ���ʱ��һ��������뻷�ڵ��������һȦ���������Loop2��˵���ҵ���
			cur1 = loop1.next;
			while(cur1 != loop1) {
				if(cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}
	
	/**
	 * 		�����޻������ཻ���׸��ڵ�
	 * @param head1������1
	 * @param head2������2
	 * @return ������2��ֵ��null��2�����������ཻ��node���ཻ���׽ڵ�
	 * 		1.2������������ཻ����ô���ཻ�ڵ㿪ʼ������ĺ�벿��һ������ͬ�ģ�
	 * 		2.��2���������ͬ���ȿ�ʼ�ߣ���ôһ������ͬʱ�ߵ��ཻ�Ľڵ㣬�ص��������2�������ȵĲ�ֵ����ʹ�ýϳ����������߲�ֵ�ľ��롣
	 */
	public Node noLoop(Node head1 , Node head2) {
		//�쳣���ж�
		if(head1 == null || head2 == null) {
			return null;
		}
		/**
		 * 		cur1,cur2�ֱ����ڱ�������n�������2������ĳ���
		 * 		2����������һ��n,��һ��ѭ��n++��¼������1�ĳ��ȣ��ڶ���ѭ��n--��¼������2���������1�Ĳ�ֵ��
		 * 	�ò�ֵ����Ϊ����˵������1������Ҳ����Ϊ����˵������2����
		 */
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while(cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		while(cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		//cur1��cur2��󶼵����˸��������β�ڵ㣬��ô����һ����ͬ
		if(cur1 != cur2) {
			return null;
		}
		//cur1ָ��ϳ�������
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		//ȡn�þ���ֵ����������ϳ�������Ҫ����n��
		n = Math.abs(n);
		while(n != 0) {
			n--;
			cur1 = cur1.next;
		}
		//cur1��cur2ͬʱ��ʼ�ߣ�ֱ���ҵ���ͬ���׽ڵ�Ϊֹ
		while(cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}
	
	/**
	 * 		�ж�һ�������Ƿ��л�
	 * @param head �������ͷ�ڵ�
	 * @return ������ֵ��2�������null��û�л���node���뻷�ڵ�
	 * 		1.�ж�һ�������Ƿ��л���������2���ڵ����һ��Ϊslow���ƶ�1����һ��Ϊfast���ƶ�2�����������û�л�����ôfastһ���᷵��
	 * 	һ��null������л�����ôfast��slow�ڵ�һ�����ڻ���ĳ���غϣ�
	 * 		2.��fast��slow�غ�ʱ�������жϳ������뻷�ڵ㴦��������fast�ƶ���ͷ�ڵ��λ�ã��ƶ��ٶȸ�Ϊ1��fast��slowͬʱ��ʼ���ٶ�1
	 * 	�ĵ�λ����������fast��slow�ٴ�����ʱ��һ�������뻷�Ľڵ㴦���������ظ��뻷�Ľڵ㡣
	 */
	public Node getLoopNode(Node head) {
		//�쳣������жϣ�����Ϊnull��slow�ƶ��ĵ�1���ڵ㣬fast�ƶ��ĵ�2���ڵ㡣
		if(head == null || head.next == null || head.next.next == null) {
			return null;
		}
		//n1��slowָ�룻n2��fastָ��
		Node n1 = head.next;
		Node n2 = head.next.next;
		//��ѭ���ж��Ƿ��л�
		while(n1 != n2) {
			if(n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		n2 = head;
		//��ѭ���ж��뻷�Ľڵ�
		while(n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
