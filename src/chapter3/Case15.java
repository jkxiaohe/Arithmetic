package chapter3;

/**
 *      题目：判断t1树是否包含t2树全部的拓扑结构
 *      思路：对t1进行循环判断，然后只要有t1的某个节点和t2的头节点匹配，那么就分别从这2个节点开始分别向左和向右进行比较，
 *              在比较的过程中，如果t1的子树提前为null了，或t1的子树上的节点值和t2上对应的节点值不相同，那么直接返回false，在
 *              比较到最后叶子节点为null的时候，返回true即可。
 *                  整体思路是采用了递归查找的方式。以t1的每个节点作为头节点和t2进行比较，然后对t1的每棵子树进行匹配。
 *                  所以，为了尽快的找出，将匹配的函数放在前面，而t1递归子树的函数放在后面执行。
 */
public class Case15 {

    public boolean contains(Node t1 , Node t2){
        //采用“或”的方式，如果前面已经查找到了为true，那么直接返回即可；如果为false，那么继续右边的递归查找。
        return check(t1 , t2) || contains(t1.left , t2) || contains(t1.right , t2);
    }

    /*
            该函数的目的是为了比较2棵子树上的节点是否完全相同。
            比较的原则：如果t2为null，返回true，是t2提前结束了，
            但如果h为null，或者发生值不等的情况，那么就是不包含，直接返回false.
            如果当前的节点值满足，那么2个子树一起向左和向右递归查找，并取其结果的&。
     */
    public boolean check(Node h , Node t2){
        if(t2 == null){
            return true;
        }
        if(h == null || h.value != t2.value){
            return false;
        }
        return check(h.left , t2.left) && check(h.right , t2.right);
    }

    class Node{
        public int value;
        public Node left;
        public Node right;
        Node(int data){
            this.value = data;
        }
    }
}
