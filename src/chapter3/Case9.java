package chapter3;

/**
 *      题目：找到二叉树中的最大搜索二叉子树
 *      思路：1.使用一种通用的二叉树的遍历方法；
 *                2.判断当前cur节点是否满足二叉搜索树的特征：左子树上的最大值<cur.value，右子树上的最小值>cur.value；
 *                  当前cur不满足时，要从左右子树种选出最大的那个，对左右子树遍历时，要收集相关的信息进行比较：
 *                  信息包括：头节点，节点数，最小值，最大值
 *                3.根据步骤2得出来的结果，判断比较。
 */
public class Case9 {

    public Node biggestSubBST(Node head){
        //使用一个全局的数据来记录相关的数据，按下标顺序分别是：节点数，最小值，最大值
        int[] record = new int[3];
        return posOrder(head , record);
    }

    /**
     *
     * @param head：当前节点对象
     * @param record：record数组用来记录上面指定的一系列数据
     * @return
     */
    public Node posOrder(Node head , int[] record){
        /*
        特殊情况放在一开始便判断，即当前为叶子节点，record数组中的节点数一定为0，对于后2个空格的值，如果不进行赋值，可能会出现
        NullPointerException的异常，所以需要对其进行处理，如果是随便的一个值，那么可能会与后面的判断相矛盾，所以在这里给其一个
        肯定为null的值
         */
        if(head == null){
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        /*
                接下来的这几行代码是在寻求左子树中的最大二叉子树，使用的是递归的方法，后面还有一个是右子树的查找，原理和左子树一样，
         然后是判断，首先这样做是可行的：对于每个节点都会对其左右子树进行查找，查找完成后进行一个最大二叉搜索树的比较判断，
         后面再返回。
                在递归的过程中，传入了record数组，record数组可以用来负责记录满足要求的二叉搜索树的多个信息，且在每次递归的过程中，
          record数组中的记录值都会进行更新（record数组是全局通用的），在每次返回到上一层的时候，record代表的是当前节点cur的左子树
          （或右子树）的最大二叉搜索树，进行比较即可。
         */
        Node lBST = posOrder(left , record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right , record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        //更新当前record数组的最值记录，此时已经包含了当前节点，返回上一层的时候就携带了当前子树的最值记录
        record[1] = Math.min(lMin , value);
        record[2] = Math.max(rMax , value);
        /*
                当前节点cur可能就满足条件，if中便是再判断以cur为头节点的子树是否满足条件，包括：
                left == LBST：left是cur的左孩子，LBST代表了左子树中的二叉搜索树的头节点，此条件不满足，该cur子树肯定有不满足的节点存在；
                right == RBST：与左子树同理；
                lMax<vlaue：左边的最大值
                rMIN：右边的最小值
         */
        if(left == lBST && right == rBST && lMax < value && value < rMin){
            record[0] = lSize + rSize + 1;
            return head;
        }
        //如果上述条件不满足，那么选取左右子树中节点数目最多的那个返回
        record[0] = Math.max(lSize , rSize);
        return lSize > rSize ? lBST : rBST;
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
