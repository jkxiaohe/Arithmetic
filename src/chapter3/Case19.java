package chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *      题目：判断一棵二叉树是否为搜索二叉树和完全二叉树
 */
public class Case19 {

    /*
            搜索二叉树的判断：通过中序遍历，如果正确，那么结果应该递增的序列。
            使用morris遍历，可以避免使用到额外的空间，直接在树本身上进行修改，即通过让下层的节点指向上层。
     */
    public boolean isBST(Node head){
        //异常情况
        if(head == null){
            return true;
        }
        /*
            res：结果标志
            pre：上一个节点对象，用来判断是否是递增的序列
            cur1,cur2：需要使用2个节点来改变树的连接指向
         */
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        //cur1是当前正在遍历的对象，故使用cur1来进行判断
        while(cur1 != null){
            //按照Morris的规则，cur2是cur1左边的最右节点，故每次操作时cur2是cur1的左节点
            cur2 = cur1.left;
            //cur1的左孩子可能为null，故要进行正确性的判断
            if(cur2 != null){
                /*
                        这里的目的是要让cur1左子树的最右节点指向cur1本身，这样整个二叉树的链接顺序便是递增的，
                        cur2.right是否为Null决定了是否要向右移动，
                        cur2.right == cur1 ，说明已经更改了cur1左子树最右节点的指向，无须再进行更改。
                 */
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                //经过上述的while循环后，cur2当前成为了cur1的左子树的最右节点
                if(cur2.right == null){
                    //cur2.right是null，是没有被使用的，根据Morris的规则，将其指向父节点cur1，这样便串接起来了
                    cur2.right = cur1;
                    //cur1向左移动，继续进行上述类似的更改，continue退出当前的循环
                    cur1 = cur1.left;
                    continue;
                }else{
                    //重新将其置为null，不影响原来的二叉树
                    cur2.right = null;
                }
            }
            /*
                    经过上面的处理，cur1已经移动到了最左边，至于原因：上面有个cur1=cur1.left，然后还有一个continue ！！！！！
                    pre是cur1的前一个节点，当pre==null时，cur1此时是中序遍历的第一个节点；
                    pre.value > cur1.value不符合递增的规则，此时res记录结果为false，但不能立即结束，原因在于Morris遍历分为
                调整二叉树结构和恢复二叉树结构2个阶段，如果此时直接结束，可能会造成二叉树的无法恢复。
             */
            if(pre != null && pre.value > cur1.value){
                res = false;
            }
            //上面都处理完后，pre记录此时cur1的值，然后cur1向右移动，右边的值是要更大的，并且右节点已经指向了上一层的父节点
            pre = cur1;
            cur1 = cur1.right;
        }
        //最后返回结果即可
        return res;
    }


    /**
     *      题目：完全二叉树的判断
     *      思路：首先完全二叉树的定义：叶子节点只能出现在最下层和次下层，且在该层的最左边。
     *          根据完全二叉树的定义，设定规则集去判断即可。
     *          1.按层遍历，从左到右；（所以Queue先将left节点放入，再将right放入）
     *          2.如果当前节点有右孩子，但没有左孩子，直接返回false；（对应代码中的   l==null && r != null）
     *          3.如果当前节点并不是左右孩子都有，那之后的节点必须都叶子节点，否则返回true；
     *              （这点的理解：完全二叉树最多可以有2个空层，如果父节点缺少某个节点，那么下一层就是第2层，不能再有叶子了，
     *              判断方法对应——（leaf && (l != null || r != null））
     *              leaf说明了当前节点应该是叶子节点，left和right只要有一个为true即可直接返回false。
     */
    public boolean isCBT(Node head){
        if(head == null){
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            //综合了不满足要求的条件
            if((leaf && (l != null || r != null)) || (l == null && r != null)){
                return false;
            }
            if(l != null){
                queue.offer(l);
            }
            if(r != null){
                queue.offer(r);
            }else{
                //leaf选择在这里赋值的原因：right==null，left不论是否为null，该层或下一层必定都为叶子节点
                leaf = true;
            }
        }
        return true;
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
