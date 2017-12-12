package chapter2;

/**
 * @author xiaozhu
 * 		题目：将搜索二叉树转换成双向链表
 * 		思路：利用递归函数，不使用任何容器的方法。如果不适用任何的容器，那么链表之间节点的关系必然需要重新构造才行。方法是：
 * 			以根节点作为分解，分别对左子树和右子树进行递归操作，递归函数分为2部分内容：
 * 			①递归的终止条件：当传递过来的节点node为null时，递归终止，return;
 * 			②递归的操作：以根节点为分界，对左子树和右子树进行重新的连接，连接操作的所有情况在if,else if里面进行判断，首先最小的
 * 		连接情况是根节点下分别只有1个左右孩子节点，对该子树进行重连：
 * 			左孩子的left指向根节点head,右孩子的left指向根节点head,同时head的left和right也要做出相应的更改，最后
 * 		让该子链的最后一个节点
 *
 */
public class Case15Method2 {

	public Node convert2(Node head) {
		if(head == null) {
			return null;
		}
		Node last = process(head);
		head = last.right;
		last.right = null;
		return head;
	}
	
	public Node process(Node head) {
		if(head == null) {
			return null;
		}
		Node leftE = process(head.left);
		Node rightE = process(head.right);
		Node leftS = leftE != null ? leftE.right : null;
		Node rightS = rightE != null ? rightE.right : null;
		if(leftE != null && rightE != null) {
			leftE.right = head;
			head.left = leftE;
			rightS.left = head;
			head.right = rightS;
			rightE.right = leftS;
			return rightE;
		}else if(leftE != null) {
			leftE.right = head;
			head.left = leftE;
			head.right = leftS;
			return head;
		}else if(rightE != null) {
			head.right = rightS;
			rightS.left = head;
			rightE.right = head;
			return rightE;
		}else {
			head.right = head;
			return head;
		}
	}
	
	class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data) {
			this.value = data;
		}
	}
}
