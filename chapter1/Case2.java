package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu  
 *		由2个栈组成的队列
 *		思路：队列的特点是先进先出，而栈的特点是先进后出，使用2个栈，一个用来push，另一个用来pop,push栈的元素Pop到另一个栈中
 *后其顺序就是先进先出了，直接去除即可，此时pop栈中存储了Push栈中原来的那些数字的正确顺序，如果接下来仍然有pop操作，直接
 *从pop栈中弹出元素即可，pop栈中的顺序是正确的出栈顺序；
 *		此时如果有元素要加入到栈中，分2中情况：
 *			①pop栈为null,直接将push栈中的数据放入到pop栈 中，然后从pop栈中取出即可；
 *			②pop栈不为Null，必须首先将pop栈中的数字去玩才能再进行入栈操作，此时新的数据压入push栈中保存即可。
 */
public class Case2 {

	public Stack<Integer> stackPush;
	public Stack<Integer> stackPop;
	
	public Case2() {
		stackPush = new Stack<Integer>();
		stackPop = new Stack<Integer>();
	}
	
	//不论pop栈是否为Null，数据都要首先压入push栈中
	public void add(int pushInt) {
		stackPush.push(pushInt);
	}
	
	/**
	 * 出栈的操作：1.如果2个栈中都没有数据，抛出异常；
	 * 2.如果此时pop栈为Null，那么将push栈中的数据挨个放入到pop栈中，还原原来的入栈顺序；
	 * 3.如果此时pop栈不为null ,那么不能将push栈中的数据放入，直接从pop栈中弹出即可。
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
