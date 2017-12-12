package chapter2;

/**
 * @author xiaozhu
 * 		题目：将单向链表按某值划分为小中大
 *		思路：根据所给数值，划分为3个不同的链表，遍历原链表，分别将其存放入不同的链表中，分为3个步骤：
 *				（1）对于这3个链表，其公共部分是必须要有一个头节点和尾节点，头节点用来存储一个链表的开头，用于后续的遍历，而尾节点用于
 *		添加节点到链表中，负责链表整体的移动，这样基本就确定了方法中所要定义的变量都有哪些。
 *				（2）再将节点添加到链表中时，仍然需要一个注意的地方，即：
 *					①该链表有可能不存在，所以在定义变量初始化的时候就要将其置为null，方便后面的判断；
 *					②该链表只有一个节点，头尾都指向一个，此时需要将head和tail都指向同一个节点，即head==null的时候添加进去；
 *					③该链表有多个节点，此时需要使用尾节点tail将新的节点元素不断的添加到链表中，头节点需要维持原对象不变，因为需要使用
 *			头节点来遍历所有的节点对象；
 *				（3）将元素都按照顺序存放到对应的链表中后，需要将其连接起来，此处介绍连接的规则：
 *						①连接的方式是头尾相连，在连接的过程中，上一个链表的尾节点可能为Null，此处直接pass，开始后面链表的连接；
 *						②如果上个链表不为Null，那么其尾节点tail必定不为null，此时可以进行连接，将上个链表的tail.next指向当前链表的头节点；
 *						③后面一个链表也有可能为Null，仅仅进行了连接操作，仍然需要判断当前的链表是否为Null，否则进行下个连接的时候是
 *				会报空指针的异常，既然已经判断了上个链表不为null，在当前链表为null的情况下，将当前链表的尾节点指向上个链表的尾节点，
 *				这样就避免了NullPointerException，在代码中直接使用三目运算符进行了判断和赋值。
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
		//将链表中所有的节点重新分离出去
		//使用3个链表来描述3个部分的状态，最后将他们串起来
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
