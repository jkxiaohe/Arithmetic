package chapter2;

/**
 * @author xiaozhu
 * 		题目：打印2个有序链表的公共部分
 *
 */
public class Case1 {

	public void printCommonPart(Node head1,Node head2) {
		System.out.print("Common Part : ");
	    //首先2个链表都不为Null时要进行3中情况的判断，如果只有一个为真，那么输出null
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
