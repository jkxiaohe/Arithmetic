package chapter1;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author xiaozhu
 * 		构造数组的MaxTree，且时间和空间复杂度均为O(n)
 * 	思路：
 * 		实现方案：取每一个数的左边或右边的第一个较大的数字作为父节点，若左右两边都没有，说明是父节点。
 * 		通过该方法，可以确保树的唯一性，并且每一个节点的子节点最多只有2个，证明如下：
 *  假设a的子节点有k1和k2，由于k1!=k2，那么k1和k2一定有另一个可以作为父节点，而不是同时是某个树的子节点；
 *		在实现过程中，需要对每一个数字查找其左边和右边较大的第一个数字，并进行存储，
 *	可以使用Map对象，并分别使用2个Map分别存储一个数的左边和右边，key为当前节点对象，value为左边或右边第一个较大的那个数字，
 *	这样通过map即可查找出当前节点左右两边的父节点；
 *		在构造树的过程中，取出当前节点左右两边的节点后，可能有以下几种情况：
 *		①left==null && right==null，说明该节点是树的根节点；
 * 		②left==null，父节点在右边，然后判断一下right的左右子树是否为null，然后添加进去；
 * 		③right==null，如节点在左边，同理；
 * 		④left!=null && right!=null，取左右节点中较小的那个作为父节点。
 */
public class Case7 {

	public Node getMaxTree(int[] arr) {
		Node[] nArr = new Node[arr.length];
		//将每个节点初始化
		for(int i=0;i != arr.length;i++) {
			nArr[i] = new Node(arr[i]);
		}
		Stack<Node> stack = new Stack<Node>();
		HashMap<Node,Node> lBigMap = new HashMap<Node,Node>();
		HashMap<Node,Node> rBigMap = new HashMap<Node,Node>();
		
		//将每个数字左边第一个较大的数字存入lBigMap中
		for(int i=0;i != nArr.length;i++) {
			Node curNode = nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, lBigMap);
			}
			stack.push(curNode);
		}
		//有些数字的左边就是比右边的大，顺序存储在了stack中，最后将这些数字挨个全部弹出并记录在lBigMap中
		while(!stack.isEmpty()) {
			popStackSetMap(stack, lBigMap);
		}
		
		//同理右边
		for(int i=0; i!=nArr.length; i++) {
			Node curNode = nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, rBigMap);
			}
			stack.push(curNode);
		}
		while(!stack.isEmpty()) {
			popStackSetMap(stack, rBigMap);
		}
		
		//最后一步：构造出二叉树
		Node head = null;
		for(int i=0; i!=nArr.length; i++) {
			Node curNode = nArr[i];
			Node left = lBigMap.get(curNode);
			Node right = rBigMap.get(curNode);
			//取得了当前节点的左右较大值，接下来分情况判断即可
			if(left==null && right==null) {
				head = curNode;
			}else if(left == null) {
				if(right.left == null) {
					right.left = curNode;
				}else {
					right.right =curNode;
				}
			}else if(right == null) {
				if(left.left == null) {
					left.left = curNode;
				}else {
					left.right = curNode;
				}
			}else {
				//左右两边都有较大值，取较小的那个作为父节点
				Node parent = left.value < right.value ? left : right;
				if(parent.left == null) {
					parent.left = curNode;
				}else {
					parent.right = curNode;
				}
			}
		}
		return head;
	}
	
	/**
	 * @param stack    该栈中从底向上按照从大到小的顺序存储了每个节点对应的左边（右边）的较大值，以左边为例，将较大的数入栈，
	 * 		然后底部的数即是左边第一个比它大的数字，如果新加入的数字大于栈顶，则将栈顶的数字弹出，并将较大的数字放入，这样在遍历的过程
	 * 		中，能够获得当前数字的左边有没有（第一个）比它大的数字，如果有的话，直接将该条记录信息放入到Map中记录，因为会有较小数字
	 * 		弹出，所以并不能保证所有的数字都在里面有相应的记录。
	 * @param map	
	 */
	public void popStackSetMap(Stack<Node> stack,HashMap<Node,Node> map) {
		Node popNode = stack.pop();
		if(stack.isEmpty()) {
			map.put(popNode, null);
		}else {
			map.put(popNode,stack.peek());
		}
	}
	
	public class Node{
		public int value;
		public Node left;
		public Node right;
		public Node(int data) {
			this.value = data;
		}
	}
	
}
