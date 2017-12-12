package chapter2;

/**
 * @author xiaozhu
 * 		题目：两个单链表相交的一系列问题
 * 		思路：
 * 			1.首先将链表相交的情况进行分类：
 * 				①2个链表都无环，即2个链表都是单向的链表，后面有共同的部分；
 * 				②2个链表都有环，入环的节点不确定，但是有环的部分是相同的；
 *
 */
public class Case11 {

	public Node getIntersectNode(Node head1 , Node head2) {
		if(head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		//1个有环，1个无环，这种结构是不可能相交的，所以对这种情况不进行判断
		if(loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		if(loop1 != null && loop2 != null) {
			return bothLoop(head1 , loop1 , head2 , loop2);
		}
		return null;
	}
	
	/**
	 * 		判断2个有环链表是否相交，返回第一个相交的节点。
	 * @param head1
	 * @param loop1：第一个有环链表的入环节点
	 * @param head2
	 * @param loop2
	 * @return
	 * 		对于2个有环链表的相交，情况分为：
	 * 			①loop1==loop2,2个链表的环结构以及入环节点是相同的，其相交节点在head1->loop1和head2->loop2这一段内；
	 * 			②loop1 != loop2，2个链表的环结构相同但是入环节点是不同的，此时loop1或(loop2)沿环内走一圈，一定可以找到loop2(loop1);
	 */
	public Node bothLoop(Node head1 , Node loop1 , Node head2 , Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		//if条件用来判断2个环结构的入环节点是否相同
		if(loop1 == loop2) {
			//如果入环节点相同，只需要从2个链表相同长度的位置开始逐个向下遍历，找到首个重合的节点即可
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
			//2个链表的共用一个环结构，但是各自入环的节点是不同的，此时从一个链表的入环节点出发，走一圈，如果遇到Loop2，说明找到了
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
	 * 		查找无环链表相交的首个节点
	 * @param head1：链表1
	 * @param head2：链表2
	 * @return ：返回2个值：null：2个单链表无相交；node：相交的首节点
	 * 		1.2个单链表如果相交，那么从相交节点开始，链表的后半部分一定是相同的；
	 * 		2.从2个链表的相同长度开始走，那么一定可以同时走到相交的节点，重点在于求出2个链表长度的差值，并使得较长的链表先走差值的距离。
	 */
	public Node noLoop(Node head1 , Node head2) {
		//异常的判断
		if(head1 == null || head2 == null) {
			return null;
		}
		/**
		 * 		cur1,cur2分别用于遍历链表，n用来求出2个链表的长度
		 * 		2个链表共用了一个n,第一个循环n++记录了链表1的长度，第二个循环n--记录了链表2相对于链表1的差值，
		 * 	该差值可能为正，说明链表1更长；也可能为负，说明链表2更长
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
		//cur1和cur2最后都到达了各自链表的尾节点，那么他们一定相同
		if(cur1 != cur2) {
			return null;
		}
		//cur1指向较长的链表
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;
		//取n得绝对值，用来代表较长的链表要先走n步
		n = Math.abs(n);
		while(n != 0) {
			n--;
			cur1 = cur1.next;
		}
		//cur1和cur2同时开始走，直到找到相同的首节点为止
		while(cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}
	
	/**
	 * 		判断一个链表是否有环
	 * @param head ：链表的头节点
	 * @return ：返回值有2个结果：null：没有环，node：入环节点
	 * 		1.判断一个链表是否有环可以设置2个节点对象，一个为slow，移动1步，一个为fast，移动2步，如果链表没有环，那么fast一定会返回
	 * 	一个null；如果有环，那么fast和slow节点一定会在环的某处重合；
	 * 		2.当fast和slow重合时，继续判断出环的入环节点处，方法：fast移动到头节点的位置，移动速度改为1，fast和slow同时开始以速度1
	 * 	的单位遍历链表，当fast和slow再次相遇时，一定是在入环的节点处相遇，返回该入环的节点。
	 */
	public Node getLoopNode(Node head) {
		//异常情况的判断：链表为null，slow移动的第1个节点，fast移动的第2个节点。
		if(head == null || head.next == null || head.next.next == null) {
			return null;
		}
		//n1：slow指针；n2：fast指针
		Node n1 = head.next;
		Node n2 = head.next.next;
		//该循环判断是否有环
		while(n1 != n2) {
			if(n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		n2 = head;
		//该循环判断入环的节点
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
