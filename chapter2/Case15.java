package chapter2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiaozhu
 * 		��Ŀ��������������ת����˫������
 * 		˼·��
 * 			�ٶԸö��������������������˳��洢�������У������ȡ�ķ�ʽ�ǣ��������У����ң��Եݹ�ķ�ʽ�����ң�
 * 			�ڽ������������е����ݽ������Ӳ�������������һ���ڵ���3���ֹ��ɣ�
 * 				ֵvlaue������left���Һ���right;
 * 			    ���Զ���ÿ���ڵ���󣬶���Ҫ����left��right�����ӽ������ã����ǣ�������β�ڵ���˵���������ڵ��е㲻ͬ��
 * 			���ȣ��׽���left=null��β�ڵ��right=null����Щ��Ҫ��ǰ�������ã����������Ľڵ㣬��left��right��Ҫ���帳ֵ��
 */
public class Case15 {

	public Node convert1(Node head) {
		Queue<Node> queue = new LinkedList<Node>();
		inOrderToQueue(head , queue);
		if(queue.isEmpty()) {
			return head;
		}
		/**
		 * 		���Ҫ���������ͷ�ڵ㣬queue���ӵĵ�һ���ڵ����ͷ�ڵ㣬��������������˵Ľڵ�
		 * 		�����е�leftָ���ϸ��ڵ����rightָ���¸��ڵ����������Ҫʹ�ñ����洢�ϸ��ڵ㣬
		 * 	����pre���ڴ洢�ϸ��ڵ㣬curΪ��һ���ڵ����
		 * 		pre.right=cur,���ú��Һ��ӣ�
		 * 		cur.left=pre,ͬʱ���ú����ӣ���Ϊ��2���ڵ����໥ָ��Է��ġ�
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
	 * 		���������Ľڵ�ѹ�뵽�����У�������õ����������ѹ����еķ�ʽ�����Ƚ����ڵ������ѹ�룬�ٽ����ڵ�ѹ�룬����Һ���
	 * 	ѹ������С����ַ�ʽ��Ҫʹ�õ��ݹ�ķ�ʽ���ݹ���ֹ�������ǣ���ǰ����Ľڵ����Ϊnull��
	 * @param head	����ǰ����Ľڵ����
	 * @param queue�����ڽ��������뵽������
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
