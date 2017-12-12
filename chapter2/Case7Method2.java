package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		题目：判断一个链表是否是回文结构
 * 		思路：如果是回文结构，那么从中间分开，左右2半是能够重合的，即只需要把一半的数字存入栈结构中，然后和另一半的数字对比即可。
 * 		我的理解：理论很容易理解，但是代码的实现稍微有点数学运算。
 * 			首先，将链表的右半部分压入栈中，那么首先要确定的是右半部分是从链表的哪里开始的，这里使用right和cur的异步移动来确定。
 * 			初始值：right=head.next，如果链表只有一个节点，right为null，不影响结果，同时right的开始位置就是左边第一个节点的下一个，
 *     right = right.next，right以1个单位的方式移动；
 *     		cur=head，cur从头节点开始移动，cur的起始位置及其移动方式，要确保能够使得cur停止移动时right处于链表右半部分的起始位置，
 *     cur=cur.next.next，这是cur的移动方式，即cur以2个单位的方式移动。
 *     	现在开始证明该移动方式：
 *     		设链表的第一个节点的编号为1，则：
 *     		cur每次移动的编号：1，3，5，7，9，11，------------
 *     		right每次移动的编号：2，3，4，5，6，------------
 *     		并且cur和right是同时移动的，不存在谁先谁后的问题，再分析另一中情况：链表的长度为偶数或奇数，
 *     			如果链表长度为偶数，可均等的划分为2半；奇数长度，中间的那个数字跳过。
 *     		移动条件的判定：cur.next!=null && cur.next.next!=null，这样子才能够判断每次何时该移动。
 *     		cur每次移动的单位比right多1，那么就有：（cur的初始位置为1，right的初始位置为2）
 *     		length=2，cur不移动，right不移动，cur=1，right=2，相差1个单位；
 *     
 *     		length=3，cur移动2个单位，right移动1个单位，cur=3,right=3，相差0个单位；（空过中间那个奇数位）
 *     		length=4，不移动，维持上一个状态；
 *     		length=5，cur移动2个单位，right移动1个单位，cur=5,right=4，相差1个单位；（right仍然位于右半部分的起始位置）
 *     		length=6，不移动，维持上一个状态；
 *     		length=7，cur移动2个单位，right移动1个单位，cur=7,right=5，相差2个单位；（right仍然位于右半部分的起始位置）
 *     		length=8，不移动，维持上一个状态；
 *     		.............
 *     
 *     		通过对多组数据的观察，规律已经显现：
 *     			对于奇数，right需要移动；对于偶数，可以维持上一个状态；right和cur的异步移动方式，使得每奇偶一次的数字，cur都比right
 *     		多移动1个单位，解决了数组长度差距造成的间距问题。
 *
 */
public class Case7Method2 {

	public boolean isPalindrome2(Node head) {
		if(head==null || head.next==null) {
			return true;
		}
		Node right = head.next;
		Node cur = head;
		while(cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		while(right != null) {
			stack.push(right);
			right = right.next;
		}
		while(!stack.isEmpty()) {
			if(head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
