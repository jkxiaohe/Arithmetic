package chapter2;

import java.util.HashSet;

/**
 * @author xiaozhu
 * 		��Ŀ��ɾ������������ֵ�ظ��Ľڵ�
 * 		˼·����������hash����ص㣬�������еĽڵ㰤������������ÿһ���������Ľڵ��ŵ�hash���У������ٱ�����ʱ�������ж�hash
 * 	�����Ƿ�����ͬ��ֵ������У�˵���ظ��������ýڵ㣻���û�У��������hash���У�������������һ����
 *
 */
public class Case13 {

	public void removeRep1(Node head) {
		if(head == null) {
			return;
		}
		//����set���ϵ��ص㣺���򣬲����ظ����жϽڵ�ֵ��Ψһ��
		HashSet<Integer> set = new HashSet<Integer>();
		//��ͷ�ڵ����һ���ڵ㿪ʼ��pre���ڴ洢��һ���ڵ㣬��Ϊcur��ǰ�ڵ���ܻ��ظ�����ʱpre.nextֱ��ָ����һ���ڵ�cur.next
		Node pre = head;
		Node cur = head.next;
		set.add(head.value);
		while(cur != null) {
			if(set.contains(cur.value)) {
				pre.next = cur.next;
			}else {
				set.add(cur.value);
				pre = cur;
			}
			cur = cur.next;
		}
	}
	
	public void removeRep2(Node head) {
		Node cur = head;
		Node pre = null;
		Node next = null;
		/**
		 * 		cur����ǰ���ڱȽϵĶ���pre������ָ�����ڱ����Ľڵ����һ����next���ڲ��ϵ����²��ҽ�㣬
		 * 	��Ҫ��֤pre��next���һ���ڵ���������ظ���ʱ�򷽱�ɾ��next����
		 * 		�ȽϵĹ���ʹ��next�����������еĽڵ㣬���뵱ǰ����cur.valueֵ���бȽϣ�Ȼ�󲻶ϵĽ�next������һ����
		 */
		while(cur != null) {
			pre = cur;
			next = cur.next;
			while(next != null) {
				if(cur.value == next.value) {
					pre.next = next.next;
				}else {
					pre = next;
				}
				next = next.next;
			}
			cur = cur.next;
		}
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
