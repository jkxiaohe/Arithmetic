package chapter2;

import chapter2.Case4.Node;

/**
 * @author xiaozhu
 * 		题目：反转部分单向链表
 * 		思路：分为2步:1.对from和to范围内的节点反转；2.构建新链表节点的指向。
 * 				在第2步中，又分为了2种情况。
 *
 */
public class Case5 {

	public Node reversePart(Node head,int from,int to) {
		int len = 0;     //记录数组的长度
		//反转链表需要3个节点来描述，node1用来遍历链表，并作为当前节点对象。
		Node node1 = head;      
		//fPre和tPos初始值为Null，方便后续对情况作判断
		Node fPre = null;
		Node tPos = null;
		/**
		 * 		该循环是要找出fPre的前一个节点，和tPos的后一个节点，同时对反转的情况进行判断。
		 * 		node1用来遍历整个链表中的数字，而fPre和tPos是链表中某个范围内的节点，那么len在逐渐增大的过程中，如果from和to
		 * 	合法，那么一定在某个时候存在len == from（或to）。同时，为了求出当前处理过程属于哪一类情况，fPre为from的前一个节点，
		 * 	tPos为to的后一个节点，如果from是头节点，此时from-1 < 1,是不会存在len==from-1的情况的，那么fPre就仍未Null，说明了
		 * 	反转部分是包含头节点的；其他情况下fPre肯定能得到一个有效值，按照正常情况反转就好了。
		 */
		while(node1 != null) {
			len++;
			fPre = len==from-1 ? node1 : fPre;
			tPos = len==to+1 ? node1 : tPos;
			node1 = node1.next;
		}
		//from和to代表了反转的左端和右端，在这里判断异常情况，因为要用到链表长度len，所以放在了这里
		if(from > to || from < 1 || to > len) {
			return head;
		}
		/**
		 * 		开始对链表进行反转，根据反转的2种可能情况，使用三目运算符对其进行判断：
		 * 		①fPre==null，说明反转的部分包含头节点，令node1(当前节点)为头节点head;
		 * 		②fPre!=null ，说明反转部分不包含头节点，令node1为fPre的下一个节点，即所要反转节点的开始部分。
		 * 		node2，next：反转链表需要3个节点对象来描述，node2代表当前节点，next代表下一个，node1代表上一个
		 */
		node1 = fPre==null ? head : fPre.next;
		Node node2 = node1.next;
		
		//不论是2种情况中的哪一个，都需要将from节点指向to的后一个节点，该步在这里执行
		node1.next = tPos;
		
		Node next = null;
		while(node2 != tPos) {
			next = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = next;
		}
		//如果满足，说明在链表范围内的反转已经完成，更改from节点指向到反转的最后一个（此时node1已经移动到了）
		if(fPre != null) {
			fPre.next = node1;
			return head;
		}
		//fPre==null，说明头节点也反转了，此时Node1作为反转的最后一个节点，作为反转后的头节点
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
