package chapter2;

/**
 *      题目：按照左右半区的方式重新组合单链表
 *      思路：分别找到左右半区的开始节点，然后对这2个节点的进行遍历，通过更改节点之间的指向，省去占用新空间的问题。
 */
public class Case20 {

    public void relocate(Node head){
        //特殊情况：列表为null，或只有1个节点，那么直接返回
        if(head == null || head.next == null){
            return ;
        }
        Node mid = head;
        Node right = head.next;
        while(right.next != null && right.next.next != null){
            mid = mid.next;
            right = right.next.next;
        }
        mid.next = null;
        mergeLR(head , right);
    }

    public void mergeLR(Node left , Node right){
        Node next = null;
        while(left.next != null){
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }

    class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
}
