package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ����ӡ2����������Ĺ�������
 *
 */
public class Case1 {

	public void printCommonPart(Node head1,Node head2) {
		System.out.print("Common Part : ");
	    //����2��������ΪNullʱҪ����3��������жϣ����ֻ��һ��Ϊ�棬��ô���null
		while(head1!=null && head2!=null) {
			if(head1.value < head2.value) {
				head1 = head1.next;
			}else if(head1.value > head2.value) {
				head2 = head2.next;
			}else {
				System.out.print(head1.value + " ");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		System.out.println();
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
