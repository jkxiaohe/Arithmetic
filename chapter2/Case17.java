package chapter2;

/**
 * @author xiaozhu
 * 		��Ŀ��ɾ�������е�ĳ���ڵ�
 * 		˼·��1.�����Ҫɾ���Ľڵ�Ϊnull����ôֱ�ӷ��أ�
 * 				2.���ɾ������β�ڵ㣬�����޷���֪��һ���ڵ㣬���޷������ϸ��ڵ��nextָ�򣬼�ʹ��node=null������null��ϵͳ����
 * 			һ���ض��������ϸ��ڵ��nextĬ��ָ����Ƿǿգ����������׳��쳣��
 * 				3.��������������£��ҵ���һ���ڵ���󣬲�����һ���ڵ��value��next����ֵ����ǰ�ڵ㣬����˳�����ɾ��������
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
