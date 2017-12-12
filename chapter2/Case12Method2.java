package chapter2;

import chapter2.Case12.Node;

/**
 * @author xiaozhu
 * 		思路：与方法1的思路相同，只不过不需要使用到栈结构，相应的增加了几个变量来存储。
 *
 */
public class Case12Method2 {

	public Node reverseKNodes2(Node head , int K) {
		if(K < 2) {
			return head;
		}
		Node cur = head;
		Node start = null;
		Node pre = null;
		Node next = null;
		int count = 1;
		while(cur != null) {
			next = cur.next;
			if(count == K) {
				//start用于存储待排序列的第一个，之所以根据Pre来获取值，是因为pre指向了下个节点，
				//刚开时pre是null，此时应该选用head作为开始；之后从pre.next中获取下个待排序的节点
				start = pre == null ? head : pre.next;
				//head是要作为整个反转之后的链表的首节点返回的，所以其应该只被赋一次值，且不在变化
				head = pre == null ? cur : head;
				resign2(pre , start , cur , next);
				//上个函数对start有改动，pre和start相同，即pre指向了上个逆序链表的最后1个节点，且其next为后面一个节点。
				pre = start;
				count = 0;
			}
			count++;
			cur = next;
		}
		return head;
	}
	
	/**
	 * 		该方法负责将有序表逆序，同时也负责处理逆序过程中的各种连接问题
	 * @param left	：左边逆序后的最后一个节点，即正序中的第一个
	 * @param start：所要逆序的正序链中的第一个节点
	 * @param end：所要逆序的正序链中的最后一个节点
	 * @param right：右边正常序的第一个节点
	 * 		注意：在java中，只有8中基本类型的变量是值传递，其余（包括所有对象类型）都是地址传递，所以，在该方法中对对象所做的
	 * 	所有更改，都会影响到原始的对象，即调用它的主函数中的对象的内容。
	 */
	public void resign2(Node left , Node start ,Node end , Node right) {
		/**
		 * 		pre,cur,next：标准的反转链表的3对象，包括下面的那个while循环
		 */
		Node pre = start;
		Node cur = start.next;
		Node next = null;
		//cur的终点是其右边界，同时cur从第二个节点开始调整，因为第一个节点的指向不是设置为Null，后面会专门处理
		while(cur != right) {
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		//left代表了上个调整的序列后的最后一个节点，将其指向右边的第一个
		if(left != null) {
			left.next = end;
		}
		//注意这里更改了start的下个节点的指向，start的下个节点直接指向右边待排序列的第一个
		start.next = right;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
