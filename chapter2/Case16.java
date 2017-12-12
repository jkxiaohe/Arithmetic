package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ���������ѡ������
 * 		˼·����һ�������ϵ��������ݽ���һ�����µ����򣬿���ʹ�õķ����ǣ��½�һ��������ԭ�����е�����˳���ҵ���Сֵ��Ȼ��
 * 				���μ��뵽���������У���ԭ������Ѱ����Сֵ�Ĺ����У�Ҫȷ���ҵ��󲢽���Ԫ��ɾ��������֤ԭ������������ԣ�Ȼ������������
 * 				���µĽ�Сֵ��ŵ���β����󷵻��������ͷ�ڵ㼴�ɡ�
 *
 */
public class Case16 {

	public static Node selectionSort(Node head) {
		Node tail = null;     //���򲿷ֵ�β��
		Node cur = head;    //δ���򲿷ֵ�ͷ��
		//smallPre��small������ԭ�����н���ɾ���ڵ�Ĳ���
		Node smallPre = null;    
		Node small = null;
		while(cur != null) {
			//��ʼ��ʱ����small=cur����Ϊ��С�ڵ�ֵ�п�����ͷ�ڵ㣬��ʱsmallPre=null����Ҫ��������ж�
			small = cur;
			smallPre = getSmallestPreNode(cur);
			//�������ͷ�ڵ㣬���������
			if(smallPre != null) {
				small = smallPre.next;      //��¼��ǰ��С�ڵ�
				smallPre.next = small.next;    //�ò����ڽ���С�ڵ�ɾ���Ĳ���
			}
			//cur��ԭ�����е�ͷ�ڵ㣬���ڲ���ԭ�������Сֵ�����small��cur����ôcur�˴��Ľڵ�Ͳ��ٴ����ˣ�������һ���ڵ㿪ʼ
			cur = cur == small ? cur.next : cur;
			//�������β����head���ڴ洢�ź����ͷ�ڵ㣬��tailΪnullʱ��˵��ͷ�ڵ㻹�����ڣ���ʱ��head=small
			//������ھͽ��µĽڵ���뵽β��ȥ������tail���Ų��һ��λ�õ�small��������������һ���ڵ㡣
			if(tail == null) {
				head = small;
			}else {
				tail.next = small;
			}
			tail = small;
		}
		return head;
	}
	
	//�����ǰ�������Сֵ��ǰһ���ڵ�
	public static Node getSmallestPreNode(Node head) {
		//��ѭ���Ľ����У�smallPre��pre�ֱ����ڼ�¼small��cur��ǰһ���ڵ㣬��¼ǰһ���ڵ��������
		//��ԭ�����н���ɾ����ʱ�򣬿���ֱ��������ǰ�ýڵ�
		Node smallPre = null;
		Node small = head;
		Node pre = head;
		Node cur = head.next;
		while(cur != null) {
			if(cur.value < small.value) {
				smallPre = pre;
				small = cur;
			}
			pre = cur;
			cur = cur.next;
		}
		return smallPre;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
