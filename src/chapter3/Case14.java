package chapter3;

import java.util.Stack;

/**
 *      题目：搜索二叉树中2个错误的节点
 *      思路：如果是正确的搜索二叉树，那么按照中序遍历的方式，其结果值应该是从小到大顺序排列。
 *              第一种情况：2个错误的节点在中序遍历的过程中是分开的，那么在遍历的时候会出现2次降序，找到那2个降序的节点即可；
 *              第二种情况：在遍历的过程中只出现了1次降序，那么说明：这2个错误的节点是连在一块的，把发生降序的这2个节点取出即可。
 */
public class Case14 {

    public Node[] getTwoErrNodes(Node head){
        Node[] errs = new Node[2];
        if(head == null){
            return errs;
        }
        /*
                使用stack栈来进行中序遍历，首先将头节点压入，如果当前节点有左孩子，那么优先将左孩子压入栈中，
                出栈的时候，将下一个节点移动到右节点的位置上。
         */
        Stack<Node> stack = new Stack<Node>();
        //pre用来记录上一个节点，用来处理第2中特殊的情况
        Node pre = null;
        //head==null的情况可能发生在head向右孩子移动的过程中
        while(!stack.isEmpty() || head != null){
            if(head != null){
                //只要head!=null，就将当前节点压入，并移动到其左孩子，
                stack.push(head);
                head = head.left;
            }else{
                /*
                        通过pop()操作可以保证按照中序遍历的方式弹出，
                        如果是左边的叶子节点，其head.left为null，会在这里被弹出，然后head到右孩子的位置，如果右孩子为null，那么
                   便会弹出根节点，然后head继续向右。
                 */
                head = stack.pop();
                /*
                        降序的判断，pre用来记录遍历过程中的上个节点对象，要判断是否有降序。
                        errs[0]的赋值使用了3目运算符，是因为想要直接一次性的解决2种情况的问题。
                        errs[0]如果已经有值，说明这2个错误的节点是分开的，直接使用它已有的值即可。
                        如果errs[0]为null，那么直接给予pre，即符合第一种情况，也符合第二种情况。
                 */
                if(pre != null && pre.value > head.value){
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
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
