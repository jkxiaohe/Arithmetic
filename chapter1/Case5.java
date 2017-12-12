package chapter1;

import java.util.Stack;

/**
 * @author xiaozhu
 * 		用一个栈实现另一个栈的排序
 * 		方法：栈顶到栈底，不论是从大到小或从小到大，都可以实现。
 * 		从大到小：从栈中弹出的元素放入到临时栈中，接着不断取出元素，如果大于临时栈中的元素，将临时栈中的元素弹出到原始栈中，
 * 	直到该元素小于临时栈中的元素，这样最大的元素压入临时栈的底部，再次弹出时即为原始栈的顶部元素。
 */
public class Case5 {

	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<Integer>();
		while(!stack.isEmpty()) {
			//挨个取出stack中的所有元素
			int cur = stack.pop();
			//只要help不为null，并且当前元素要大于栈顶的元素，就将其弹出，并压入回原始栈中
			while(!help.isEmpty() && help.peek() < cur) {
				help.push(cur);
			}
			//当满足条件时：要么cur<=栈顶的元素，要么help栈中的元素全部都弹出；符合条件，将该元素压入help栈中
			help.push(cur);
		}
		//最后将栈中的元素倒过来即可
		while(!help.isEmpty()) {
			stack.push(help.pop());
		}
	}
	
}
