package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		��һ��ջʵ����һ��ջ������
 * 		������ջ����ջ�ף������ǴӴ�С���С���󣬶�����ʵ�֡�
 * 		�Ӵ�С����ջ�е�����Ԫ�ط��뵽��ʱջ�У����Ų���ȡ��Ԫ�أ����������ʱջ�е�Ԫ�أ�����ʱջ�е�Ԫ�ص�����ԭʼջ�У�
 * 	ֱ����Ԫ��С����ʱջ�е�Ԫ�أ���������Ԫ��ѹ����ʱջ�ĵײ����ٴε���ʱ��Ϊԭʼջ�Ķ���Ԫ�ء�
 */
public class Case5 {

	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<Integer>();
		while(!stack.isEmpty()) {
			//����ȡ��stack�е�����Ԫ��
			int cur = stack.pop();
			//ֻҪhelp��Ϊnull�����ҵ�ǰԪ��Ҫ����ջ����Ԫ�أ��ͽ��䵯������ѹ���ԭʼջ��
			while(!help.isEmpty() && help.peek() < cur) {
				help.push(cur);
			}
			//����������ʱ��Ҫôcur<=ջ����Ԫ�أ�Ҫôhelpջ�е�Ԫ��ȫ������������������������Ԫ��ѹ��helpջ��
			help.push(cur);
		}
		//���ջ�е�Ԫ�ص���������
		while(!help.isEmpty()) {
			stack.push(help.pop());
		}
	}
	
}
