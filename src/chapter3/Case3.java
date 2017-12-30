package chapter3;

/**
 *      题目：打印二叉树的边界节点
 *      标准2：1.头节点为边界节点；2.叶节点为边界节点；
 *                  3.树左边界延伸下去的路径为边界节点；
 *                  4.树右边界延伸下去的路径为边界节点。
 */
public class Case3 {

    public void printEdge2(Node head){
        if(head == null){
            return;
        }
        //头节点是一定符合要求的，直接打印
        System.out.println(head.value + " ");
        //接下来负责打印孩子节点，如果左右孩子都不为null，那么分别写出打印的过程
        //如果有一个为null，那么需要进行具体的判断
        if(head.left != null && head.right != null){
            printLeftEdge(head.left , true);
            printRightEdge(head.right , true);
        }else{
            //该种情况属于特例，说明二叉树是一个棒状的结构，直接递归，然后每次递归之前打印节点值即可。
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    /**
     *      该函数负责打印树左边界延伸下去的路径
     * @param h ：h节点表示上个节点从左或从右延伸下去的节点对象
     * @param print ：print代表树的延伸方向：
     *                              对于向左边延伸的，如果一直在向左边延伸，而没有向右边延伸，那么print为true,对应要求中的左边界延伸下去；
     *                              而对于向右边延伸的，又分为2种情况：
     *                                  ①左边没有子树延伸，那么向右延伸就是唯一的选择了，对应题目中的树边界向右延伸下去，此刻print仍为true，
     *                                  ②左右边界都有延伸的时候，当递归右边的延伸时，print为false，且print=false这个状态会在右边的子树下面
     *                                      一直继承下去，因为递归的条件使用? : 同时包含了这2个情况。
     */
    public void printLeftEdge(Node h , boolean print){
        if(h == null){
            return;
        }
        if(print || (h.left == null && h.right == null)){
            System.out.println(h.value + " ");
        }
        printLeftEdge(h.left , print);
        printLeftEdge(h.right , print && h.left == null ? true : false);
    }


    /**
     *      该方法对应向右边延伸下去的节点，和上面一个方法其实是相互反转了一下。
     * @param h ：当前的节点对象
     * @param print ：延该方向是否要进行打印
     *        与上一个方法不同的是，首先要进行递归然后再打印，原因是：输入的规则要求是顺序输出，右边是从下往上输出的，所以要先递归，
     *     递归到最后的时候会从最底部开始输出。
     */
    public void printRightEdge(Node h , boolean print){
        if(h == null){
            return;
        }
        printRightEdge(h.left , print && h.right == null ? true : false);
        printRightEdge(h.right , print);
        if(print || (h.left == null && h.right == null)){
            System.out.println(h.value + " ");
        }
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
