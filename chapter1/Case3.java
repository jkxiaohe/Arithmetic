package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		ʹ�õݹ麯����һ��ջ�е�������������
 */
public class Case3 {

	//����1
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();   //ÿ�εݹ鶼�ᵯ��ջ����Ԫ�أ�����ʹ��result�����棬���������Ĵ���
		if(stack.isEmpty()) {
			//��ʱջΪNull��ֱ�����Ϸ��أ���ʼ��ջ���������
			return result;
		}else {
			//��ǰջ��λnull,����ջ�������֣����������µݹ飻ֱ����ջΪnullʱ������һ����俪ʼһ�ν��������
			//������ѹ��ջ�У�����ʵ��ռ�����ֵ�����
			int last = getAndRemoveLastElement(stack);
			stack.push(last);
			return last;
		}
	}
	
	//����2
	public static void reverse(Stack<Integer> stack) {
		//�ݹ����ֹ��������ʱջΪnull������
		if(stack.isEmpty()) {
			return;
		}
		//���÷���ֱ�ӵ���ջ������
		int i = getAndRemoveLastElement(stack);
		//ÿ�ε���һ���������µݹ����
		reverse(stack);
		//�ӵݹ�����ʼ���ν�������ջ
		stack.push(i);
	}
	
}
