package chapter3;

/**
 *      题目：使用Morris方法遍历二叉树，其时间复杂度为O(n)，额外空间复杂度为O(1)
 *      思路：Morris遍历没有使用到栈结构，而是利用了二叉树中本来就闲置的变量。一般的二叉树的遍历方法是从上到下，因为父节点
 *              拥有指向子节点的指针，而在Morris遍历中，是让下层到上层有指针。在二叉树中，最下层的节点，即叶子节点，其指向是
 *              null，利用这些节点的空闲指针，可以实现下层到上层的指向。
 *              更改指向的规则为：假设当前子树的头节点为h，让h的左子树中的最右节点的right指针指向h（即当前节点），然后再进行到h的
*          左孩子，继续同样的更改，直到遍历到的某个节点没有左子树为止。
 *              通过上面的这种处理方式，空闲节点的指向就被有效的利用起来了，接下来介绍如何将这些节点有序的打印出来，即：
 *            （1）首先在设置完关系后，节点落在了最左边的节点位置上，对该节点进行处理，（该节点是特殊的）
 *                    首先打印当前的这个节点，然后通过该节点的right指向向上移动到所指到的节点；
 *           （2）接下来的节点具有相似的特点：
 *                   如果当前节点cur的左子树的最右节点指向cur，那么另最右节点的right指针指向为null，再打印cur，然后通过cur的
 *              right指针移向下一个节点，接着对下一个节点进行相同的处理；
 *                   如果不是，那么继续步骤1的执行。
 *       分析：通过左子树的最右节点，可以将关系串联起来，然后通过每次输出后，移动到right节点，可以实现所有的迭代。
 *
 */
public class Case7 {

    /**
     *      中序遍历的方式
     * @param head
     */
    public void morrisIn(Node head){
        if(head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;
                }
            }
            System.out.println(cur1.value + " ");
            cur1 = cur1.right;
        }
        System.out.println();
    }

    /**
     *      先序遍历
     * @param head
     */
    public void morrisPre(Node head){
        if(head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    System.out.println(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;
                }
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }

    /**
     *      后序遍历的实现
     * @param head
     */
    public void morrisPos(Node head){
        if(head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2  != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    public void printEdge(Node head){
        Node tail = reverseEdge(head);
        Node cur = tail;
        while(cur != null){
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while(from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    class Node{
        public int value;
        Node left;
        Node right;
        public Node(int data){
            this.value = data;
        }
    }
}
