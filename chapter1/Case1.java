package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu
 *设计一个有getMin功能的栈
 *		用2个栈对象来存储数据，一个用来压栈，一个用来存储到目前为止的最小数字，每次将数字压栈的时候都比较一下当前加入的数字
 *和最小栈中的哪个更小
 *			①当前数字更小，那么将当前的数字压入到最小栈中；
 *          ②最小栈中的数字更小，那么将不压入，或者将最小栈中的栈顶数字重复压入。
 */
public class Case1 {

	private Stack<Integer> stackData;   //存储数据
	private Stack<Integer> stackMin;    //存储最小值
	
	//初始化
	public Case1() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}

	/**
	 * @param newNum  :  新加入进来的数字
	 * 1.首先判断当前栈是否为Null，如果为null,直接压入栈中即可；
	 * 2.如果栈中有数字，那么首先弹出栈中的最小数字进行判断，如果新的数字更小，那么直接入栈即可；
	 * 3.最后：该数字一定要压入Data栈中进行保存。
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
	 * @return	出栈操作
	 * 首先判断数据栈是否为null，为null时直接抛异常，代表此时没有存储任何的数据；
	 * 然后将Data栈中弹出的元素与min栈顶比较，如果相等，那么Min栈也要弹出，用来及时更新当前Data中的最小值。
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
	 * @return  查找最小值
	 * 如果最小栈为Null，那么直接抛异常，代表当前没有最小元素可取；
	 * 如果最小栈不为null,那么说明是有值得，直接取出栈顶的那个最小值元素即可；
	 * 使用peek()的好处：取出栈顶的元素，且不将其从栈中移除。
	 */
	public int getmin() {
		if(this.stackMin.isEmpty()) {
			throw new RuntimeException("Your stack is empty");
		}
		return this.stackMin.peek();
	}
}
