package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		题目：两个单链表生成相加链表
 * 		思路：可以将遍历单链表，将单链表中的每个数字都压入到栈中，然后从栈中弹出，这样数字的顺序就变为了从低位到高位，
 * 				然后每次从栈中弹出一个数字，将数字相加，如果有进位，用一个专门的数字来表示。
 * 				另外，进位的数字只可能是1，不可能超过1，各位数上相加的最大值9+9=18,也只是进1位而已。
 *
 */
public class Case10 {

	public Node addList1(Node head1,Node head2) {
		//使用2个栈对象分别将2个链表中的所有值放入到栈中
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		while(head1 != null) {
			s1.push(head1.value);
			head1 = head1.next;
		}
		while(head2 != null) {
			s2.push(head2.value);
			head2 = head2.next;
		}
		/**
		 * 		定义几个变量来存放运算变量，
		 * 		ca:进位值，n1:同一位数上的第一个链表值，n2：第二个链表值，n:2个链表同位相加值
		 */
		int ca = 0;
		int n1 = 0;
		int n2 = 0;
		int n = 0;
		Node node = null;
		Node pre = null;
		/**
		 * 		只要2个栈都不为null，就继续运算，由于是从低位开始的相加，所以在后面需要对进位进行判断，
		 * 	同时2个栈的大小可能不一样，在相加的时候要判断当前栈是否为Null，如果为Null，用0来代替，可以使得相加不会出错。
		 * 		同时需要考虑节点生成的连接方式，结果应该是从高位链接到低位，但由于这里是从低位开始算的，所以需要将链接反向，
		 * 	方法是：
		 * 			使用2个节点对象来进行存储，一个用于指向当前节点，代表旧的节点对象，一个用于创建新的节点，代表左边新的节点，
		 * 		然后将新节点的next域指向旧节点对象pre，这样反向链接就形成了。
		 * 		
		 */
		while(!s1.isEmpty() || !s2.isEmpty()) {
			n1 = s1.isEmpty() ? 0 : s1.pop();
			n2 = s2.isEmpty() ? 0 : s2.pop();
			n = n1 + n2 + ca;
			pre = node;
			node = new Node(n%10);
			node.next = pre;
			ca = n / 10;
		}
		//上述循环结束后，没有对最后一次相加进位的情况进行判断，所以需要在这里独立判断，对于进位ca的可能值，
		//要么为0，要么为1，如果为1，那么需要再次新创建一个节点对象，并反向更改指针的指向。
		if(ca == 1) {
			pre = node;
			node = new Node(1);
			node.next = pre;
		}
		return node;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
