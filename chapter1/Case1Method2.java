package chapter1;

import java.util.Stack;

public class Case1Method2 {

	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public Case1Method2() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}
	
	public void push(int newNum) {
		//������һ��Minջ���Ԫ�صķ�ʽ�������ǰ�����Ԫ�ش���ջ����Сֵ����ô��ջ����Сֵ�ظ���ջ������Minջ��Dataջ���Ա���
		//���ݵ�ͬ����
		if(this.stackMin.isEmpty()) {
			stackMin.push(newNum);
		}else if(newNum < getmin()) {
			this.stackMin.push(newNum);
		}else {
			int newMin = this.stackMin.peek();
			this.stackMin.push(newMin);
		}
		this.stackData.push(newNum);
	}
	
	public int pop() {
		if(this.stackMin.isEmpty()) {
			throw new RuntimeException("Your stack is empty");
		}
		//����Ԫ�ص�ʱ��Minջ��Dataջ��Ҫͬʱ��pop��һ��Ԫ��
		this.stackMin.pop();
		return this.stackData.pop();
	}
	
	public int getmin() {
		if(this.stackMin.isEmpty()) {
			throw new RuntimeException("Your stack is empty");
		}
		return this.stackMin.peek();
	}
	
}
