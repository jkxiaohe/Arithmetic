package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ��������������ת����˫������
 * 		˼·�����õݹ麯������ʹ���κ������ķ���������������κε���������ô����֮��ڵ�Ĺ�ϵ��Ȼ��Ҫ���¹�����С������ǣ�
 * 			�Ը��ڵ���Ϊ�ֽ⣬�ֱ�������������������еݹ�������ݹ麯����Ϊ2�������ݣ�
 * 			�ٵݹ����ֹ�����������ݹ����Ľڵ�nodeΪnullʱ���ݹ���ֹ��return;
 * 			�ڵݹ�Ĳ������Ը��ڵ�Ϊ�ֽ磬�����������������������µ����ӣ����Ӳ��������������if,else if��������жϣ�������С��
 * 		��������Ǹ��ڵ��·ֱ�ֻ��1�����Һ��ӽڵ㣬�Ը���������������
 * 			���ӵ�leftָ����ڵ�head,�Һ��ӵ�leftָ����ڵ�head,ͬʱhead��left��rightҲҪ������Ӧ�ĸ��ģ����
 * 		�ø����������һ���ڵ�
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
