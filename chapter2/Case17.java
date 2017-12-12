package chapter2;

/**
 * @author xiaozhu
 * 		题目：删除链表中的某个节点
 * 		思路：1.如果所要删除的节点为null，那么直接返回；
 * 				2.如果删除的是尾节点，由于无法得知上一个节点，就无法更改上个节点的next指向，即使将node=null，由于null在系统中是
 * 			一个特定的区域，上个节点的next默认指向的是非空，这样做会抛出异常；
 * 				3.其余正常的情况下，找到下一个节点对象，并将下一个节点的value和next都赋值给当前节点，即可顺利完成删除操作。
 *
 */
public class Case17 {

	public void removeNodeWired(Node node) {
		if(node == null) {
			return ;
		}
		Node next = node.next;
		if(next == null) {
			throw new RuntimeException("cat not remove");
		}
		node.value = next.value;
		node.next = next.next;
	}
	
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
}
