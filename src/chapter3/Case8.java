package chapter3;

import java.util.HashMap;

/**
 *      题目：在二叉树中找到累加和为指定值的最长路径长度
 *      思路：使用一个Map来存储所有可能的结果，其Key：从head节点开始的累加和；Value：这个累加和在路径中最早出现的层数，
 *              在开始的时候首先put(0，0)，表示累加和为0的结果不用包括任何节点就可以得到
 */
public class Case8 {

    public int getMaxLength(Node head , int sum){
        HashMap<Integer , Integer> sumMap = new HashMap<Integer , Integer>();
        sumMap.put(0 , 0);
        return preOrder(head , sum , 0 , 1 , 0 , sumMap);
    }

    /**
     *
     * @param head：当前遍历的节点
     * @param sum：指定的累加和
     * @param preSum：从head节点开始到cur（当前节点）的父节点的路径上的累加和
     * @param level：当前节点在二叉树中的层次深度
     * @param maxLen：最终结果，即累加和的最长路径长度
     * @param sumMap：在Map对象中存储了二叉树所形成的各种结果
     * @return
     */
    public int preOrder(Node head , int sum , int preSum , int level , int maxLen , HashMap<Integer , Integer> sumMap){
        //head为null，意味着到了叶子节点的位置，返回此时求得的最后结果maxLen
        if(head == null){
            return maxLen;
        }
        //curSum：从head到当前节点的路径上的累加和，head是当前节点，preSum是当前路径上的所有节点的累加和
        int curSum = preSum + head.value;
        /**
         *      在Map中记录（累加和，最早出现的层次数），
         *      注意：即使对于相同的Key值（即路径上的累加和），Map也只是记录第一次出现的那次，因为第一次出现的那次层次数最低，
         *              以后再有相同的值时，层次数一定更高，那时level-preLevel便不是最大路径长度了，下面有解释
         */
        if(!sumMap.containsKey(curSum)){
            sumMap.put(curSum , level);
        }
        /**
         *      接下来的if代码的作用：求解在必须以cur结尾的情况下，累加和为规定值得最长路径长度
         *      解释：首先在所有的过程中，maxLen的赋值只有在if条件下有过，下面的maxLen都是递归条件，而在递归中真正更改maxLen
         *              的语句还是在if中，所以终究只有if这一个条件起作用。那么现在来看看if中作了什么：
         *              Math.max()，求其中的最大值，里面有2个参数，maxLen代表的是之前遍历过程中所求得的最大值，然后与现在求得的
         *       最大值要进行一个对比：
         *              level是当前层次深度，map.get(curSum-sum)，结果是求sum所具有的最大深度，（curSum-sum）即使sum开始累加的
         *       位置，把这个开始位置的深度get出来，然后和sum结尾的位置level相减，即是当前路径上sum的最长路径，然后和原来的比较即可。
         */
        if(sumMap.containsKey(curSum - sum)){
            maxLen = Math.max(level - sumMap.get(curSum - sum) , maxLen);
        }
        /**
         *      当前路径上的累加和可能<sum，即不满足要求的条件，上面那个if代码便不会执行，接下来要继续二叉树的先序遍历，直到
         *    找到为止，当然要遍历完二叉树的所有分支，所以要向2个方向递归，一个是head.left，一个是head.right,
         *    curSum在下一次递归中变成了preSum，level层次数要加1，maxLen传下去要进行比较更新
         */
        maxLen = preOrder(head.left , sum , curSum , level + 1 , maxLen , sumMap);
        maxLen = preOrder(head.right , sum , curSum , level + 1 , maxLen , sumMap);
        //以cur为头节点的子树处理完，要返回到上一层的父节点，将当前level处的可能值从Map中移除，判断的条件是根据层次数level进行判断
        if(level == sumMap.get(curSum)){
            sumMap.remove(curSum);
        }
        return maxLen;
    }

    class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }
}
