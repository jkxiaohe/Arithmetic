package chapter3;

/**
 *      题目：判断二叉树是否为平衡二叉树
 *      思路：使用后序遍历的方式，分别求解左右子树的最大深度，对其进行判断。
 */
public class Case17 {

    public boolean isBalance(Node head){
        /*
                长度为1，只是用来记录当前查找的子树是否为平衡二叉树，之所以使用数组，是数组在递归的过程中是地址传递的，
            相当于一个全局的boolean变量。
         */
        boolean[] res = new boolean[1];
        //默认为true，即是一个平衡二叉树
        res[0] = true;
        //递归查找，head：当前节点，level：当前节点的深度，res：数组负责一个全局变量的作用
        getHeight(head ,  1 , res);
        return res[0];
    }

    public int getHeight(Node head , int level , boolean[] res){
        //如果查找到了叶子节点的末尾，返回null即可
        if(head == null){
            return level;
        }
        //对当前子树的左孩子进行深度查找，每次向下递归的时候，level深度要+1
        int lH = getHeight(head.left , level + 1 , res);
        /*
                当左边判断完成后，res数组中记录了判断的结果，if条件判断如果为false，那么直接返回，返回的结果已经不重要了，
           因为res数组中已经将整个二叉树标记为false，返回什么结果也不会再改变该结果，仅仅是为了能够快速的结束递归过程。
                 顺便说一句，level在递归的过程中是逐层相加的，也就是说每次返回来的level都是当前节点下的最大深度。
         */
        if(!res[0]){
            return level;
        }
        //右子树同理
        int rH = getHeight(head.right , level + 1 , res);
        if(!res[0]){
            return level;
        }
        //对左右子树 的深度差进行判断，整个递归中唯一一个改变res数组值得代码
        if(Math.abs(lH - rH) > 1){
            res[0] = false;
        }
        //返回左右子树最大的深度给父节点
        return Math.max(lH , rH);
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
