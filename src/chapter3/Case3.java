package chapter3;

/**
 *      题目：打印二叉树的边界节点
 *      标准2：1.头节点为边界节点；2.叶节点为边界节点；
 *                  3.树左边界延伸下去的路径为边界节点；
 *                  4.树右边界延伸下去的路径为边界节点。
 */
public class Case3 {

    public void printEdge2(Node head){

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
