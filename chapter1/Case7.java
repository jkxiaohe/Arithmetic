package chapter1;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author xiaozhu
 * 		���������MaxTree����ʱ��Ϳռ临�ӶȾ�ΪO(n)
 * 	˼·��
 * 		ʵ�ַ�����ȡÿһ��������߻��ұߵĵ�һ���ϴ��������Ϊ���ڵ㣬���������߶�û�У�˵���Ǹ��ڵ㡣
 * 		ͨ���÷���������ȷ������Ψһ�ԣ�����ÿһ���ڵ���ӽڵ����ֻ��2����֤�����£�
 *  ����a���ӽڵ���k1��k2������k1!=k2����ôk1��k2һ������һ��������Ϊ���ڵ㣬������ͬʱ��ĳ�������ӽڵ㣻
 *		��ʵ�ֹ����У���Ҫ��ÿһ�����ֲ�������ߺ��ұ߽ϴ�ĵ�һ�����֣������д洢��
 *	����ʹ��Map���󣬲��ֱ�ʹ��2��Map�ֱ�洢һ��������ߺ��ұߣ�keyΪ��ǰ�ڵ����valueΪ��߻��ұߵ�һ���ϴ���Ǹ����֣�
 *	����ͨ��map���ɲ��ҳ���ǰ�ڵ��������ߵĸ��ڵ㣻
 *		�ڹ������Ĺ����У�ȡ����ǰ�ڵ��������ߵĽڵ�󣬿��������¼��������
 *		��left==null && right==null��˵���ýڵ������ĸ��ڵ㣻
 * 		��left==null�����ڵ����ұߣ�Ȼ���ж�һ��right�����������Ƿ�Ϊnull��Ȼ����ӽ�ȥ��
 * 		��right==null����ڵ�����ߣ�ͬ��
 * 		��left!=null && right!=null��ȡ���ҽڵ��н�С���Ǹ���Ϊ���ڵ㡣
 */
public class Case7 {

	public Node getMaxTree(int[] arr) {
		Node[] nArr = new Node[arr.length];
		//��ÿ���ڵ��ʼ��
		for(int i=0;i != arr.length;i++) {
			nArr[i] = new Node(arr[i]);
		}
		Stack<Node> stack = new Stack<Node>();
		HashMap<Node,Node> lBigMap = new HashMap<Node,Node>();
		HashMap<Node,Node> rBigMap = new HashMap<Node,Node>();
		
		//��ÿ��������ߵ�һ���ϴ�����ִ���lBigMap��
		for(int i=0;i != nArr.length;i++) {
			Node curNode = nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, lBigMap);
			}
			stack.push(curNode);
		}
		//��Щ���ֵ���߾��Ǳ��ұߵĴ�˳��洢����stack�У������Щ���ְ���ȫ����������¼��lBigMap��
		while(!stack.isEmpty()) {
			popStackSetMap(stack, lBigMap);
		}
		
		//ͬ���ұ�
		for(int i=0; i!=nArr.length; i++) {
			Node curNode = nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, rBigMap);
			}
			stack.push(curNode);
		}
		while(!stack.isEmpty()) {
			popStackSetMap(stack, rBigMap);
		}
		
		//���һ���������������
		Node head = null;
		for(int i=0; i!=nArr.length; i++) {
			Node curNode = nArr[i];
			Node left = lBigMap.get(curNode);
			Node right = rBigMap.get(curNode);
			//ȡ���˵�ǰ�ڵ�����ҽϴ�ֵ��������������жϼ���
			if(left==null && right==null) {
				head = curNode;
			}else if(left == null) {
				if(right.left == null) {
					right.left = curNode;
				}else {
					right.right =curNode;
				}
			}else if(right == null) {
				if(left.left == null) {
					left.left = curNode;
				}else {
					left.right = curNode;
				}
			}else {
				//�������߶��нϴ�ֵ��ȡ��С���Ǹ���Ϊ���ڵ�
				Node parent = left.value < right.value ? left : right;
				if(parent.left == null) {
					parent.left = curNode;
				}else {
					parent.right = curNode;
				}
			}
		}
		return head;
	}
	
	/**
	 * @param stack    ��ջ�дӵ����ϰ��մӴ�С��˳��洢��ÿ���ڵ��Ӧ����ߣ��ұߣ��Ľϴ�ֵ�������Ϊ�������ϴ������ջ��
	 * 		Ȼ��ײ�����������ߵ�һ������������֣�����¼�������ִ���ջ������ջ�������ֵ����������ϴ�����ַ��룬�����ڱ����Ĺ���
	 * 		�У��ܹ���õ�ǰ���ֵ������û�У���һ��������������֣�����еĻ���ֱ�ӽ�������¼��Ϣ���뵽Map�м�¼����Ϊ���н�С����
	 * 		���������Բ����ܱ�֤���е����ֶ�����������Ӧ�ļ�¼��
	 * @param map	
	 */
	public void popStackSetMap(Stack<Node> stack,HashMap<Node,Node> map) {
		Node popNode = stack.pop();
		if(stack.isEmpty()) {
			map.put(popNode, null);
		}else {
			map.put(popNode,stack.peek());
		}
	}
	
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
