package chapter3;

/**
 *      题目：找到二叉树中符合搜索二叉树条件的最大拓扑结构
 *      思路：对每个节点进行查找，分别对左右节点进行考察，将其加入到考察队列，然后对考察队列中的每一个节点对象，根据二叉树的
 *              比较原则，与头节点进行比较，如果不满足要求，则其本身以及子树不会加入到拓扑中。
 *              关于考察队列，如果当前子节点满足要求，那么将其
 */
public class Case10 {

    /**
     *      作用：遍历所有的二叉树结点，并在以每个节点为头的子树中都求一遍其中的最大拓扑结构，最后要找到那个最大的
     * @param head：当前查找的节点对象
     * @return
     */
    public int bstTopoSize1(Node head){
        //递归的终止条件：查找完了叶子节点
        if(head == null){
            return 0;
        }
        //以当前节点head开始，maxToPo函数是用来查找head开始的最大拓扑结构的函数
        int max = maxTopo(head , head);
        //每个节点都有左右2个孩子（不论是否为null），为了遍历到所有的情况，应该对其进行查找，同时，要与当前节点head查找到的
        //结果挑选出一个最大值
        max = Math.max(bstTopoSize1(head.left) , max);
        max = Math.max(bstTopoSize1(head.right) , max);
        return max;
    }

    /**
     *      作用：查找最大的拓扑结构，此函数最后要返回一个拓扑图中节点的数目，必然要对拓扑图中的节点进行一个考察，if中的条件
     *              即是对每个节点的考察，而具体的判断方式是由isBSTNode()函数来完成的，在考察的过程中，需要2个参数：一个是h，
     *              代表的是当前拓扑图中的头节点；一个是n，代表的是当前正在查找的节点；
     *              对于每个查找的节点，其下的所有子孩子也都要进行判断，因此对其都要进行递归查找，返回其下满足要求的节点数。
     * @param h：拓扑图中的头节点
     * @param n：拓扑图中此时正在查询的对象
     * @return
     */
    public int maxTopo(Node h , Node n){
        if(h != null && n != null && isBSTNode(h , n , n.value)){
            return maxTopo(h , n.left) + maxTopo(h , n.right) + 1;
        }
        return 0;
    }

    /**
     *      作用：
     * @param h
     * @param n
     * @param value
     * @return
     */
    public boolean isBSTNode(Node h , Node n , int value){
        if(h == null){
            return false;
        }
        if(h == n){
           return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right , n , value);
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
