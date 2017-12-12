package chapter1;

import java.util.LinkedList;

/**
 * @author xiaozhu
 *		题目：最大值-最小值<=num 的子数组的数量
 *		思路：在当前数组中搜寻子数组的方法是使用滑动窗口机制，而对于每个子数组，需要根据每次滑动的动态数组来维护其最大值和
 *	最小值，由于数组的滑动很有特点，这里使用队列来存储。
 *		根据子数组的扩张特点，子数组的左端点设为i，右端点设为j，只可能j++，即右端点不断的向右移动，所以在使用队列存放当前
 *	端点范围内的最大值和最小值时，要根据i的值来将队列中已经过时的值弹出，而不能够根据j来弹出，因为j从头到尾都是在不断扩大的，
 *	而i会根据情况会逐渐的向右移，此时i的移动就可能会将某些值舍去，此时才需要根据i的变化动态的调整队列中的值。
 */
public class Case9 {

	public int getNum(int[] arr,int num) {
		if(arr==null || arr.length==0) {
			return 0;
		}
		LinkedList<Integer> qmin = new LinkedList<Integer>();     
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int i = 0;   //子数组的左端
		int j = 0;   //子数组的右端
		int res = 0;     //记录最后结果
		/**
		 *        此处的2个while代表来窗口的移动规则，首先将i固定，然后j不断的向右移动，不需要考虑j向左回移可能出现的子数组情况，根据
		 *  得出的结论：当父数组满足时，其子数组一定都满足，j-i ：即是以i为固定左端点，j不断向左移时候的子数组的数量，直接相加即可。
		 */
		while(i < arr.length) {
			while(j < arr.length) {
				/**
				 * 		队列中的数字，就算弹出，也是首先弹出数组中左边入队的那些数，根据入队的规则，先到先出，左边先入队的那些数字在
				 *  队列的右端，如果新的数字比队尾的数字更小（或更大），那么直接将其弹出即可，反正右边的数字放入队列后会长期存在于
				 *  队列中，直到i的移动发生变化；
				 *  	当然，在将队列中的数字弹出的时候，有2种情况：
				 *  	①qmin最小值队列，qmin左端的值>=当前值，将队尾值弹出，这样对头始终是当前窗口中的最小值；
				 *      ②qmax最大值队列，qmax左端的值<=当前值，说明当前值更大，弹出，并压入到对头，使得对头保持当前窗口中的最大值。
				 */
				while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
					qmin.pollLast();
				}
				qmin.addLast(j);
				while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
					qmax.pollLast();
				}
				qmax.addLast(j);
				
				//j向右移动过程中的异常：当前窗口已经有max-min > num 不满足条件，那么j继续向右端移动，仍然是不满足条件
				if(arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
					break;
				}
				j++;
			}
			if(qmin.peekFirst() == i) {
				qmin.pollFirst();
			}
			if(qmax.peekFirst() == i) {
				qmax.pollFirst();
			}
			res += j - i;
			i++;
		}
		return res;
	}
	
}
