package chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *      题目：二叉树的按层打印
 *      思路：按层打印的顺序是从左到右，这个很容易实现，只要使用Queue队列即可。但是要做到按层打印，还要确定每一行的最右节点。
 *              可以使用2个节点对象来确定。Node1用来定位当前层的最右节点，Node2来定位下一层的最右节点。
 *              首先，整体的遍历过程仍然是使用队列，刚开始的时候，Node1是很容易确定的，在从左到右输出Node1的时候，将其左右孩子节点
 *         顺序压入到队列中，同时，每次压入一个节点对象，都要更新Node2为当前的节点，这样，当遍历到Node1当前行结束时，Node1的
 *         最右的一个子孩子便是下一行的最后一个节点，Node2成功进行了更新。在此之后，更新Node1=Node2，继续更新Node2即可。
 */
public class Case12 {

    public void printByLevel(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        int level = 1;
        Node last = head;
        Node nLast = null;
        queue.offer(head);
        System.out.print("Level " + (level++) + " : ");
        while(!queue.isEmpty()){
            head = queue.poll();
            System.out.print(head.value + " ");
            if(head.left != null){
                queue.offer(head.left);
                nLast = head.left;
            }
            if(head.right != null){
                queue.offer(head.right);
                nLast = head.right;
            }
            /*
                    head==last，说明已经遍历到了当前层上的最后一个节点，上面已经有语句输出，所以在这里要进行一个新的换行。
                     同时，如果当前正在处理的是树的最后一层，那么此时Queue是null，不需要再进行输出了，里面的语句不需要执行，
                 !queue.isEmpty()正是为了避免这种情况。
             */
            if(head == last && !queue.isEmpty()){
                System.out.print("\nLevel " + (level++) + " : ");
                last = nLast;
            }
        }
        //最后一个值输出后，还缺一个换行符
        System.out.println();
    }

    class Node{
        public int value;
        public Node left;
        public Node right;
        Node(int data){
            this.value =  data;
        }
    }
}
