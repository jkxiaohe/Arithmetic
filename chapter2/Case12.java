package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		题目：将单链表的每k个节点之间逆序
 * 		思路：栈结构可以将一连串数字逆序，所以该题可以使用栈结构达到逆序链表的目的。
 * 				需要注意的是，需要使用一个node来存储整个链表的头节点，以及对于每k个反转的链表节点，需要记录一下头节点和尾节点，
 * 			因为在寻找完下一组k个逆序节点后，需要对2个逆序了的链表尾部和头部的节点进行链接。
 */
public class Case12 {

	public Node reverseKNodes1(Node head , int K) {
		//k<2时，不需要进行逆序，直接返回头节点
		if(K < 2) {
			return head;
		}
		//stack用来存储逆序的节点
		Stack<Node> stack = new Stack<Node>();
		Node newHead = head;   //反转后的链表的头节点
        //使用3个节点对象将链表逆序
		Node cur = head;
		Node pre = null;
		Node next = null;
		/**
		 * 		在逆序的过程中，要比较stack中存储的节点个数是否达到k值，如果有那么就进行逆序，逆序由resign1来实现。
		 * 		由于将k个节点反转之后要对节点进行一个链接的操作，该链接有2个点：
		 * 			①第一组k节点逆序后，为了维持链表的连接状态，需要将逆序的最后一个节点指向右边第一个正常的节点对象；
		 * 			②第二组k节点逆序后，有2件事情需要完成：
		 * 					（1）将左边已经逆序的后一个节点指向当前的第一个节点；
		 * 					（2）同样为了保持链表的连接状态，将最后一个节点指向下一个正常序列的节点。
		 * 			③对于后面的每组节点，都需要向步骤2一样的有2步更改节点的操作，所以在逆序函数resign1中需要更改2个节点的指向，
		 * 		但是第①步例外，只需要更改一下右边的指向，因为初始的时候左边已经逆序的链表为Null，为了保证步骤①和后面所有的
		 * 		步骤都相同，初始时设置pre为null，并令pre来保存已经逆序的k个节点的最后一个，pre!=null时进行更改操作。
		 */
		while(cur != null) {
			//next来存储右边正常序列的第1个节点
			next = cur.next;
			stack.push(cur); 
			if(stack.size() == K) {
				//pre存储逆序后的K组节点的最后一个，初始为Null
				pre = resign1(stack , pre , next);
				//新的头节点实际上只需要设置1次即可，后面的逆序操作不会更改头节点，
				//所以该语句只需要执行一次即可，使用条件newHead == head 在第一次时会执行1次，后面便不会再执行
				newHead = newHead == head ? cur : newHead;
			}
			cur = next;
		}
		return newHead;
	}
	
	/**
	 * 		负责实现链表元素的逆序
	 * @param stack	：内部存储了倒排好的节点
	 * @param left ：倒叙后左边链表的最后一个节点
	 * @param right	：正序右边链表的第一个节点
	 * @return	：返回将当前k个节点倒叙后的链表的最后一个节点
	 * 		
	 */
	public Node resign1(Stack<Node> stack , Node left , Node right) {
		Node cur = stack.pop();
		//如果左边的链表不为null，那么更新左边节点的指针，指向右边逆序后的第一个节点
		if(left != null) {
			left.next = cur;
		}
		//此处只需要2个节点就可以完成节点的逆序，因为头节点是从stack中取出来的
		Node next = null;
		while(!stack.isEmpty()) {
			next = stack.pop();
			cur.next = next;
			cur = next;
		}
		//最后还要更新逆序后的最后一个节点指向右边正常序列的第一个节点
		cur.next = right;
		//返回的是逆序后的尾节点
		return cur;
	}
	
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
