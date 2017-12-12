package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu  
 *		��2��ջ��ɵĶ���
 *		˼·�����е��ص����Ƚ��ȳ�����ջ���ص����Ƚ������ʹ��2��ջ��һ������push����һ������pop,pushջ��Ԫ��Pop����һ��ջ��
 *����˳������Ƚ��ȳ��ˣ�ֱ��ȥ�����ɣ���ʱpopջ�д洢��Pushջ��ԭ������Щ���ֵ���ȷ˳�������������Ȼ��pop������ֱ��
 *��popջ�е���Ԫ�ؼ��ɣ�popջ�е�˳������ȷ�ĳ�ջ˳��
 *		��ʱ�����Ԫ��Ҫ���뵽ջ�У���2�������
 *			��popջΪnull,ֱ�ӽ�pushջ�е����ݷ��뵽popջ �У�Ȼ���popջ��ȡ�����ɣ�
 *			��popջ��ΪNull���������Ƚ�popջ�е�����ȥ������ٽ�����ջ��������ʱ�µ�����ѹ��pushջ�б��漴�ɡ�
 */
public class Case2 {

	public Stack<Integer> stackPush;
	public Stack<Integer> stackPop;
	
	public Case2() {
		stackPush = new Stack<Integer>();
		stackPop = new Stack<Integer>();
	}
	
	//����popջ�Ƿ�ΪNull�����ݶ�Ҫ����ѹ��pushջ��
	public void add(int pushInt) {
		stackPush.push(pushInt);
	}
	
	/**
	 * ��ջ�Ĳ�����1.���2��ջ�ж�û�����ݣ��׳��쳣��
	 * 2.�����ʱpopջΪNull����ô��pushջ�е����ݰ������뵽popջ�У���ԭԭ������ջ˳��
	 * 3.�����ʱpopջ��Ϊnull ,��ô���ܽ�pushջ�е����ݷ��룬ֱ�Ӵ�popջ�е������ɡ�
	 */
	public int pop() {
		if(stackPop.isEmpty() && stackPush.isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}else if(stackPop.isEmpty()) {
			while(!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
		return stackPop.pop();
	}
	
	public int peek() {
		if(stackPop.isEmpty() && stackPush.isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}else if(stackPop.isEmpty()) {
			while(!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
		return stackPop.peek();
	}
}
