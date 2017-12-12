package chapter2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiaozhu
 * 		题目：将搜索二叉树转换成双向链表
 * 		思路：
 * 			①对该二叉搜索树按中序遍历的顺序存储到队列中，中序读取的方式是：先左，再中，后右，以递归的方式来查找；
 * 			②接下来对链表中的数据进行连接操作，在链表中一个节点由3部分构成：
 * 				值vlaue，左孩子left，右孩子right;
 * 			    所以对于每个节点对象，都需要对其left和right的连接进行设置，但是，对于首尾节点来说，和其他节点有点不同，
 * 			首先，首届点的left=null，尾节点的right=null，这些都要提前进行设置，对于其他的节点，其left和right都要具体赋值。
 */
public class Case15 {

	public Node convert1(Node head) {
		Queue<Node> queue = new LinkedList<Node>();
		inOrderToQueue(head , queue);
		if(queue.isEmpty()) {
			return head;
		}
		/**
		 * 		最后要返回链表的头节点，queue出队的第一个节点就是头节点，即二叉树中最左端的节点
		 * 		链表中的left指向上个节点对象，right指向下个节点对象，所以需要使用变量存储上个节点，
		 * 	这里pre用于存储上个节点，cur为下一个节点对象，
		 * 		pre.right=cur,设置好右孩子，
		 * 		cur.left=pre,同时设置好左孩子，因为这2个节点是相互指向对方的。
		 */
		head = queue.poll();
		Node pre = head;
		pre.left = null;
		Node cur = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			pre.right = cur;
			cur.left = pre;
			pre = cur;
		}
		pre.right = null;
		return head;
	}
	
	/**
	 * 		将二叉树的节点压入到队列中，这里采用的是中序遍历压入队列的方式，即先将当节点的左孩子压入，再将本节点压入，最后将右孩子
	 * 	压入队列中。这种方式需要使用到递归的方式，递归终止的条件是：当前传入的节点对象为null。
	 * @param head	：当前传入的节点对象
	 * @param queue：用于将本结点放入到队列中
	 */
	public void inOrderToQueue(Node head , Queue<Node> queue) {
		if(head == null) {
			return;
		}
		inOrderToQueue(head.left , queue);
		queue.offer(head);
		inOrderToQueue(head.right , queue);
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
