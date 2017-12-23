package chapter3;

/**
 *      题目：打印二叉树的边界节点
 *      标准1：1.头节点为边界节点；2.叶节点为边界节点；3.每一层的最左和最右的节点。
 *      思路：首先要把得到结果的步骤一步一步的列出来，然后用代码去按照每一步来实现。
 *                1.得到二叉树的每一层的最左边和最右边的节点；
 *                2.打印最左边的节点；
 *                3.打印叶子节点，同时叶子节点需要满足：既不是最左边的节点，也不是最右边的节点；
 *                4.从从底向上，打印最右边的节点，同时要满足：该最右的节点与同层最左的节点不重合。
 *                这样子对每一步都用一个方法来实现，最后将这些方法有条理性的综合起来。
 */
public class Case2 {

    public void printEdge1(Node head){
        //如果当前的头节点为Null，直接退出
        if(head == null){
            return ;
        }
        //height用来获取二叉树的深度，有重要的意义，可以用来判断打印最左节点有几个，最右节点有几个
        //注意这里刚开始是0，意味着根节点是作为第0层的
        int height = getHeight(head , 0);
        //接下来在map中记录每一层的最左和最右节点，height代表了有多少层，2是记录的个数
        Node[][] edgeMap = new Node[height][2];
        setEdgeMap(head , 0 , edgeMap);

        //打印左边界
        for(int i=0 ; i != edgeMap.length ; i++){
            System.out.println(edgeMap[i][0].value + " ");
        }

        //打印叶子节点
        printLeafNotInMap(head , 0 , edgeMap);

        //打印右边界的节点，里面的if条件用来判断该右节点和左节点是不同的（像根节点这种左右节点一样的就不会再打印了）
        for(int i=edgeMap.length-1 ; i != -1 ; i--){
            if(edgeMap[i][0] != edgeMap[i][1]){
                System.out.println(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();
    }

    /**
     *      该函数用于获取二叉树的深度
     * @param h ：当前的节点对象
     * @param l ：当前节点位于第几层
     * @return ：返回最后的结果
     *      该函数使用了递归遍历的方法，同时又照顾到了左右2边遍历深度不一样的问题，取其深度最大的那个作为返回值即可。
     *      1.h==null有2中情况下会满足该条件：
     *              ①如果刚开始的头节点h即为null，那么直接返回l层即可；
     *              ②在多次递归的过程中，终于递归到了叶子节点，然后叶子节点再向下递归的时候，遇到null，返回当前叶子节点的深度l,此时返回的
     *              l实际上被上个递归的Math.max()里的函数接收到了；
     *       2.getHeight()递归函数有2个，原因：
     *               对于一个节点，要同时对其左孩子和右孩子进行一个查找，同时由于向下查找了一层，l+1；
     *       3.Math.max()，上述2个递归的发生都是在Math.max()中进行的，那是因为可能左孩子更深，也有可能右孩子更深，综合起来要去
     *              最大的那一个，小的直接忽略。
     */
    public int getHeight(Node h , int l){
        if(h == null){
            return l;
        }
        return Math.max(getHeight(h.left , l+1) , getHeight(h.right , l+1));
    }

    /**
     *      设置好每一层的边界节点
     * @param h ：当前层的节点，后面是通过和最左边的节点的比较来更新最右节点的
     * @param l ：当前遍历的深度，要把每一个节点放到正确位置的深度L上
     * @param edgeMap   : 存放这些值
     *      思路：查找最左节点实际上并不难，只要从根节点开始一直取其left节点开始，或者说只要每次都递归的是left，那么该值一定是当前
     *               层的最左节点；难的是最右节点，最右节点甚至需要递归好多次才能够找到，所以该函数的重心也是在寻找最右节点。
     *            1. h==null，说明已经递归到底了，也是递归函数的终止条件；
     *            2. 在一层有多个节点的时候，而且是多次调用的代码，对于最左节点，由于可以一次性的找到，所以该行的代码应该只执行
     *                一次，故需要一个判断条件来避免该行代码的多次执行：
     *                  ①最左节点刚开始的时候是null值，此时直接将h值赋给最左节点，由后面一行的代码可以知道第一次时h时代表的left；
     *                  ②在①处赋完值后，后面改行代码都不需要再执行了，使用的方法是：只要不为null，那么就一直取它本身的值就好了，这样
     *                      就不会改变最左节点值了；
     *             3. 最左节点可以一次性的得到确认，但最右节点确实需要不断更新才能够最终敲定值的，由后面的代码可以看出，最后递归的都是
     *                  最右边的节点值，所以每次递归一次，都要更新当前层次的最右节点值，只要还有，那么递归肯定会判断到该层，只要一直
     *                  处于更新状态，那么map[l][1]迟早会成为最右节点的；
     *             4.最后就是递归了，对二叉树的每一个左右孩子节点做递归查找，同时层次l+1，要注意的是递归的顺序：
     *                  先递归左孩子，这样方便取到最左边的节点值；
     *                  后递归右孩子，这样既不会对最左值造成干扰，同时又能够更新最右节点值。
     */
    public void setEdgeMap(Node h , int l , Node[][] edgeMap){
        if(h == null){
            return;
        }
        edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
        edgeMap[l][1] = h;
        setEdgeMap(h.left , l+1 , edgeMap);
        setEdgeMap(h.right , l+1 , edgeMap);
    }

    /**
     *      打印叶子节点，对应上面的思路
     * @param h ：当前的节点值
     * @param l  ： 当前的层次
     * @param m ：m对应的是map
     *         打印叶子节点的时候，是从头节点开始查找的，所以需要进行判断，当然判断条件有点多：
     *              h.left==null和h.right==null，是标准叶子节点的特征；
     *              同时叶子节点要满足不与最左节点和最右节点重合，
     *          后面2行是递归查找叶子节点。
     */
    public void printLeafNotInMap(Node h , int l , Node[][] m){
        if(h == null){
            return;
        }
        if(h.left == null && h.right == null && h != m[l][0] && h != m[l][1]){
            System.out.println(h.value + " ");
        }
        printLeafNotInMap(h.left , l + 1 , m);
        printLeafNotInMap(h.right , l+1 , m);
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
