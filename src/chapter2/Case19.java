package chapter2;

/**
 *      题目:合并2个有序的单链表
 *      思路：2个链表的值要分别比较，并将较小的那个放入新链表中，当一个为null时另一个仍要继续放入，此处的代码避免了
 *              这种判断。
 *              为了节约空间，考虑到2个链表都是有序的，那么将链表2的节点按序插入到链表1中，最后返回链1的头节点即可，同时
 *           也避免了新开辟一个空间来存放2个链表的数据。
 */
public class Case19 {

    public Node merge(Node head1 , Node head2){
        //2个链表中有null的情况下，直接返回那个非null的链表，或者可能是返回一个null
        if(head1 == null || head2 == null){
            return head1 != null ? head1 : head2;
        }
        //头节点是最小值，所以需要对2个链表的头节点进行比较，选取出一个最小值的节点
        Node head = head1.value < head2.value ? head1 : head2;
        //cur1指向头节点小的链表，cur2指向头节点大的链表
        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;
        //pre记录上一个节点，next代表当前，用于改变上个节点next的指向
        Node pre = null;
        Node next = null;
        while(cur1 != null && cur2 != null){
            if(cur1.value <= cur2.value){
                //pre代表cur1表中当前的位置，cur1移动到链1的下个位置
                pre = cur1;
                cur1 = cur1.next;
            }else{
                //next用于记录链2的下个节点，cur2从链2中分离出来加入到链1中
                next = cur2.next;
                //下面这2行代码将cur2成功加入到了pre和cur1的中间，即成功的插入到了链1
                pre.next = cur2;
                cur2.next = cur1;
                //插入完成后，pre移动1个单位，同时cur2继续用来记录并比较链2的下个节点值
                pre = cur2;
                cur2 = next;
            }
        }
        //上个循环遍历完成后，可能有链表为null的情况，此时将非null的那个链表直接加入到pre的尾部即可
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
}
