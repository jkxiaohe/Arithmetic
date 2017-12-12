package chapter2;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		��Ŀ�����������ÿk���ڵ�֮������
 * 		˼·��ջ�ṹ���Խ�һ���������������Ը������ʹ��ջ�ṹ�ﵽ���������Ŀ�ġ�
 * 				��Ҫע����ǣ���Ҫʹ��һ��node���洢���������ͷ�ڵ㣬�Լ�����ÿk����ת������ڵ㣬��Ҫ��¼һ��ͷ�ڵ��β�ڵ㣬
 * 			��Ϊ��Ѱ������һ��k������ڵ����Ҫ��2�������˵�����β����ͷ���Ľڵ�������ӡ�
 */
public class Case12 {

	public Node reverseKNodes1(Node head , int K) {
		//k<2ʱ������Ҫ��������ֱ�ӷ���ͷ�ڵ�
		if(K < 2) {
			return head;
		}
		//stack�����洢����Ľڵ�
		Stack<Node> stack = new Stack<Node>();
		Node newHead = head;   //��ת��������ͷ�ڵ�
        //ʹ��3���ڵ������������
		Node cur = head;
		Node pre = null;
		Node next = null;
		/**
		 * 		������Ĺ����У�Ҫ�Ƚ�stack�д洢�Ľڵ�����Ƿ�ﵽkֵ���������ô�ͽ�������������resign1��ʵ�֡�
		 * 		���ڽ�k���ڵ㷴ת֮��Ҫ�Խڵ����һ�����ӵĲ�������������2���㣺
		 * 			�ٵ�һ��k�ڵ������Ϊ��ά�����������״̬����Ҫ����������һ���ڵ�ָ���ұߵ�һ�������Ľڵ����
		 * 			�ڵڶ���k�ڵ��������2��������Ҫ��ɣ�
		 * 					��1��������Ѿ�����ĺ�һ���ڵ�ָ��ǰ�ĵ�һ���ڵ㣻
		 * 					��2��ͬ��Ϊ�˱������������״̬�������һ���ڵ�ָ����һ���������еĽڵ㡣
		 * 			�۶��ں����ÿ��ڵ㣬����Ҫ����2һ������2�����Ľڵ�Ĳ�����������������resign1����Ҫ����2���ڵ��ָ��
		 * 		���ǵڢٲ����⣬ֻ��Ҫ����һ���ұߵ�ָ����Ϊ��ʼ��ʱ������Ѿ����������ΪNull��Ϊ�˱�֤����ٺͺ������е�
		 * 		���趼��ͬ����ʼʱ����preΪnull������pre�������Ѿ������k���ڵ�����һ����pre!=nullʱ���и��Ĳ�����
		 */
		while(cur != null) {
			//next���洢�ұ��������еĵ�1���ڵ�
			next = cur.next;
			stack.push(cur); 
			if(stack.size() == K) {
				//pre�洢������K��ڵ�����һ������ʼΪNull
				pre = resign1(stack , pre , next);
				//�µ�ͷ�ڵ�ʵ����ֻ��Ҫ����1�μ��ɣ��������������������ͷ�ڵ㣬
				//���Ը����ֻ��Ҫִ��һ�μ��ɣ�ʹ������newHead == head �ڵ�һ��ʱ��ִ��1�Σ�����㲻����ִ��
				newHead = newHead == head ? cur : newHead;
			}
			cur = next;
		}
		return newHead;
	}
	
	/**
	 * 		����ʵ������Ԫ�ص�����
	 * @param stack	���ڲ��洢�˵��źõĽڵ�
	 * @param left ������������������һ���ڵ�
	 * @param right	�������ұ�����ĵ�һ���ڵ�
	 * @return	�����ؽ���ǰk���ڵ㵹������������һ���ڵ�
	 * 		
	 */
	public Node resign1(Stack<Node> stack , Node left , Node right) {
		Node cur = stack.pop();
		//�����ߵ�����Ϊnull����ô������߽ڵ��ָ�룬ָ���ұ������ĵ�һ���ڵ�
		if(left != null) {
			left.next = cur;
		}
		//�˴�ֻ��Ҫ2���ڵ�Ϳ�����ɽڵ��������Ϊͷ�ڵ��Ǵ�stack��ȡ������
		Node next = null;
		while(!stack.isEmpty()) {
			next = stack.pop();
			cur.next = next;
			cur = next;
		}
		//���Ҫ�������������һ���ڵ�ָ���ұ��������еĵ�һ���ڵ�
		cur.next = right;
		//���ص���������β�ڵ�
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
