package chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *      题目：二叉树的序列化和反序列化
 *      思路：方法１：通过先序遍历来实现
 *              在输出每一个节点的时候，需要注意：
 *                  １．每个节点存储的数值的长度可能都不同，在输出多个节点的值时可能会造成混乱，所以需要使用一个标记来分隔不同节点的
 *                      值，在这里使用的是！；
 *                  ２．需要使用一个特殊的符号标记来表示ｎｕｌｌ值，在这里使用＃；
 */
public class Case5 {

    //将二叉树序列化的过程，采用的方法是先序遍历+递归，方法的开头首先给出终止的条件，即nulll节点的判断，然后
    //再对正常的节点进行处理，即在值的末尾加标记!，最后分别向左递归和向右递归。
    public String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //重建二叉树的过程，首先将先序遍历的每个节点值放入到队列Queue中，然后由另一个方法对Queue中的值取出做操作
    public Node reconByPreString(String preStr){
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for(int i=0 ; i != values.length ; i++){
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    /**
     *      根据队列的特点：先进先出，那么先出的顺序便是：根，左，右；与进入的顺序相对应，直接构造即可。
     * @param queue
     * @return
     */
    public Node reconPreOrder(Queue<String> queue){
        //在队列弹出后，要对null值特殊判断，对于null节点是不构造任何关系的，其他的节点需要对其构造左右子孩子
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
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
