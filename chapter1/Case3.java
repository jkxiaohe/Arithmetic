package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		使用递归函数将一个栈中的数字逆序排列
 */
public class Case3 {

	//方法1
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();   //每次递归都会弹出栈顶的元素，并且使用result来保存，用来后续的存入
		if(stack.isEmpty()) {
			//此时栈为Null，直接向上返回，开始向栈中添加数据
			return result;
		}else {
			//当前栈部位null,弹出栈顶的数字，并继续向下递归；直到当栈为null时，从下一条语句开始一次将最近弹出
			//的数字压入栈中，即可实现占中数字的逆序
			int last = getAndRemoveLastElement(stack);
			stack.push(last);
			return last;
		}
	}
	
	//方法2
	public static void reverse(Stack<Integer> stack) {
		//递归的终止条件：此时栈为null，返回
		if(stack.isEmpty()) {
			return;
		}
		//调用方法直接弹出栈顶数字
		int i = getAndRemoveLastElement(stack);
		//每次弹出一个继续向下递归查找
		reverse(stack);
		//从递归的最后开始依次将数字入栈
		stack.push(i);
	}
	
}
