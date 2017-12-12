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
		//更改了一下Min栈添加元素的方式：如果当前加入的元素大于栈顶最小值，那么将栈顶最小值重复入栈，这样Min栈和Data栈可以保持
		//数据的同步性
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
		//弹出元素的时候，Min栈和Data栈需要同时都pop出一个元素
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
