package chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *      题目：题目：二叉树的序列化和反序列化
 *      思路：使用层序遍历的方式来实现
 *
 */
public class Case6 {

    /**
     *      使用队列的方式，将二叉树每一层的节点值放入到res中
     * @param head
     * @return
     */
    public String serialByLevel(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        //对于每个节点，对其左右孩子节点都要加入到结果中，null的话也有特殊的表示
        while(!queue.isEmpty()){
            head = queue.poll();
            if(head.left != null){
                res += head.left.value + "!";
                queue.offer(head.left);
            }else{
                res += "#!";
            }
            if(head.right != null){
                res += head.right.value + "!";
                queue.offer(head.right);
            }else{
                res += "#!";
            }
        }
        return res;
    }

    /**
     *      按照层序遍历还原二叉树
     * @param levelStr ：层序遍历后的字符串
     * @return
     */
    public Node reconByLevelString(String levelStr){
        //将层序遍历的所有节点值放入到一个数组中，然后再通过index下标依次获取每个节点值
        String[] values = levelStr.split("!");
        int index = 0;
        //头节点的构造特殊，后面节点的处理方法都相同
        Node head = generateNodeByString(values[index++]);
        //使用queue队列来重新构造每个父子节点的关系
        Queue<Node> queue = new LinkedList<Node>();
        if(head != null){
            queue.offer(head);
        }
        Node node = null;
        //从队列中构造左右节点即可
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public Node generateNodeByString(String val){
        if(val.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(val));
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
