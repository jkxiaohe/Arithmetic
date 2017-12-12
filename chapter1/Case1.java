package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu
 *���һ����getMin���ܵ�ջ
 *		��2��ջ�������洢���ݣ�һ������ѹջ��һ�������洢��ĿǰΪֹ����С���֣�ÿ�ν�����ѹջ��ʱ�򶼱Ƚ�һ�µ�ǰ���������
 *����Сջ�е��ĸ���С
 *			�ٵ�ǰ���ָ�С����ô����ǰ������ѹ�뵽��Сջ�У�
 *          ����Сջ�е����ָ�С����ô����ѹ�룬���߽���Сջ�е�ջ�������ظ�ѹ�롣
 */
public class Case1 {

	private Stack<Integer> stackData;   //�洢����
	private Stack<Integer> stackMin;    //�洢��Сֵ
	
	//��ʼ��
	public Case1() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}

	/**
	 * @param newNum  :  �¼������������
	 * 1.�����жϵ�ǰջ�Ƿ�ΪNull�����Ϊnull,ֱ��ѹ��ջ�м��ɣ�
	 * 2.���ջ�������֣���ô���ȵ���ջ�е���С���ֽ����жϣ�����µ����ָ�С����ôֱ����ջ���ɣ�
	 * 3.��󣺸�����һ��Ҫѹ��Dataջ�н��б��档
	 */
	public void push(int newNum) {
		if(this.stackMin.isEmpty()) {
			this.stackMin.push(newNum);
		}else if(newNum <= this.getmin()) {
			this.stackMin.push(newNum);
		}
		this.stackData.push(newNum);
	}
	
	/**
	 * @return	��ջ����
	 * �����ж�����ջ�Ƿ�Ϊnull��Ϊnullʱֱ�����쳣�������ʱû�д洢�κε����ݣ�
	 * Ȼ��Dataջ�е�����Ԫ����minջ���Ƚϣ������ȣ���ôMinջҲҪ������������ʱ���µ�ǰData�е���Сֵ��
	 */
	public int pop() {
		if(this.stackData.isEmpty()) {
			throw new RuntimeException("Your stack is empty");
		}
		int value = this.stackData.pop();
		if(value == this.getmin()) {
			this.stackMin.pop();
		}
		return value;
	}
	
	/**
	 * @return  ������Сֵ
	 * �����СջΪNull����ôֱ�����쳣������ǰû����СԪ�ؿ�ȡ��
	 * �����Сջ��Ϊnull,��ô˵������ֵ�ã�ֱ��ȡ��ջ�����Ǹ���СֵԪ�ؼ��ɣ�
	 * ʹ��peek()�ĺô���ȡ��ջ����Ԫ�أ��Ҳ������ջ���Ƴ���
	 */
	public int getmin() {
		if(this.stackMin.isEmpty()) {
			throw new RuntimeException("Your stack is empty");
		}
		return this.stackMin.peek();
	}
}
