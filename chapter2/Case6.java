package chapter2;

/**
 * @author xiaozhu
 * 		题目：环形单链表的约瑟夫问题
 *
 */
public class Case6 {

	public Node josephusKilll(Node head,int m) {
		if(head==null || head.next==head || m<1) {
			return head;
		}
		//使用last节点来记录head的前一个，即链表的最后一个头节点
		Node last = head;
		while(last.next != head) {
			last = last.next;
		}
		int count = 0;
		/**
		 * 		令last和head节点始终相差一个节点的位置，然后如果count!=m,将head和last同时向前移动，
		 * 	如果此时count==m,那么当前该节点（head处的节点）应该删除，在last.next中暂存head的下一个节点，然后将Head和last
		 * 	都向前移动一个。
		 * 		在last已经存储了head的下一个，再将Last中的next给了head即可，不能够用head=head.next,因为此时的Head可能为那个要删除的节点。
		 */
		while(head != last) {
			if(++count == m) {
				last.next = head.next;
				count = 0;
			}else {
				last = last.next;
			}
			head = last.next;
		}
		return head;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
