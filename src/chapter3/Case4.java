package chapter3;

/**
 *      题目：如何较为直观的打印二叉树
 *      思路：这里使用的方法是将二叉树逆时针旋转90度，然后用不同的方法来表示，分为以下几点：
 *              1.输出的方式是逆时针转90度后的结果，即将没一列的节点按层打印出来，最后一列（即二叉树最右边的节点）在第一层输出，
 *                  然后再下一行继续输出前一列的结果；
 *              2.为了更形象的表示出每一个节点与其父节点的关系，使用不同的符号来表示该关系：
 *                  （1）如果是父节点，在节点值两端加HH;
 *                  （2）如果是子节点，分为2种情况，当父节点在子节点的上方时，用^^表示；
 *                                                                      当父节点在子节点的下方时，用vv表示；
 *             3.在打印每个节点时，由于每个节点的值不同，会造成格式显示的不完整，所以要对所有节点的值固定一个显示的长度，不够该长度
 *               时使用空格来对齐，java中最长的整型：Integer.MIN_VALUE，长度为11位；
 *             4.确定了打印的样式后，接下来解决查找的问题：
 *                    可以确定的是，要先遍历右子树，再根节点，最后左子树；
 *                    因为右子树实在最上一层打印的，然后在下一行再打印根节点，打印完一个就要换行；
 *                    在每次打印的过程中，要记录当前节点在树的哪一层，对应的，在横向打印的过程中，要留下对应深度的间距。
 */
public class Case4 {

    public void printTree(Node head){
        System.out.println("Binary Tree : ");
        printInOrder(head , 0 , "H" , 17);
        System.out.println();
    }

    /**
     *
     * @param head ：当前所查找到的节点
     * @param height ：当前节点所在二叉树的深度，控制间距的大小
     * @param to ：标识符，表示该节点的关系，有H：根节点，v：父节点在下方，^：父节点在上方
     * @param len ：控制每一个节点所占据的长度总长
     */
    public void printInOrder(Node head , int height , String to , int len){
        //如果当前节点为Null，直接返回
        if(head == null){
            return;
        }
        //打印节点应该是从最右边的节点开始，所以在经过上面null的判断后，立马进入递归，并且传入的是右边的孩子节点值，
        //深度height+1，v：既然是右节点，那么父节点一定在其下方，用v表示
        printInOrder(head.right , height + 1 , "v" , len);
        //上面的递归，在有右节点的情况下会一直递归下去，当上面那行代码执行完时，此时要么是右节点，或者就是根节点，进行输出操作
        //在左右两端加上标志符号
        String val = to + head.value + to;
        //下面的lenM，lenL，lenR都是用来记录长度的，lenM：当前节点的长度，lenL：前面需要的空格数目，lenR：后面需要空格的数目
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        //将上面的节点都组合好后，进行输出操作
        System.out.println(getSpace(height * len) + val);
        //至此，右节点和根节点都已经输出完毕了，最后将左节点输出
        printInOrder(head.left , height + 1 , "^" , len);
    }

    //根据指定的Num值创建指定长度空格的字符串
    public String getSpace(int num){
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for(int i=0 ; i < num ; i++){
            buf.append(space);
        }
        return buf.toString();
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
