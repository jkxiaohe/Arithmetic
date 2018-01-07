package chapter3;

import java.util.Deque;
import java.util.LinkedList;

/**
 *      题目：二叉树的ZigZag打印
 *      思路：ZigZag：即Z字形的打印方式，每遍历新的一行的时候都调转方向。
 *               方法一：使用2个ArrayList，遍历list1时，将list1的子节点放入到list2中，且list1和list2输出的顺序相反。
 *                          缺点：ArrayList结构为动态数组，当元素达到一定规模时会发生扩容操作。
*                方法二：使用双端队列结构来完成。双端队列这里使用的是Deque。
 *                  首先，打印的方向是：先是从左到右，然后是从右到左，依次循环打印每一层的节点。
 *                  根据打印的方向可以选择是从队列的头部弹出节点还是从队列的尾部弹出节点。
 *                  接下来只需要介绍入队和出队的规则：
 *                  首先头节点是要直接放入到队列中的，然后第一层是从左向右打印，那么依次从头部弹出节点：
 *           如果当前弹出的节点有孩子节点，那么入队的方向是从尾部入队，孩子节点入队的方向是先左孩子后右孩子；
 *                  接下来改为从尾部弹出节点：
 *                  如果当前弹出的节点有孩子节点，那么入队的方向是从队列的头部入队，孩子节点入队的方向是先右孩子后左孩子。
 *
 */
public class Case13 {

    public void printByZigZag(Node head){
        //首先对异常情况进行判断，节点为null直接返回
        if(head == null){
            return;
        }
        //使用双端队列Dque，实现方式是链表
        Deque<Node> dq = new LinkedList<Node>();
        /*
             level：当前是第几层。
             lr：2个值：true：代表从左到右输出；false：代表从右到左输出。
             last：记录当前层的最后一个节点
             nLast：记录下一层的最后一个节点
         */
        int level = 1;
        boolean lr = true;
        Node last = head;
        Node nLast = null;
        //刚开始的时候，要把头节点放入队列中
        dq.offerFirst(head);
        pringLevelAndOrientation(level++ , lr);
        while(!dq.isEmpty()){
            if(lr){
                //lr=true，代表从左到右打印，根据规则，应从头部的节点弹出
                head = dq.pollFirst();
                /*
                        接下来的判断弹出的节点是否有孩子节点，如果有孩子节点，那么应先将左孩子放入到队列的尾端，然后再判断右孩子。
                        根据观察可以发现，nLast是每次入队的第一个孩子节点，在下一次弹出操作的时候，由于是反方向进行的，所以每次nLast
                   都代表的是最后一个。
                 */
                if(head.left != null){
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerLast(head.left);
                }
                if(head.right != null){
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerLast(head.right);
                }
            }else{
                //情况相同，是从右到左输出，反过来即可
                head = dq.pollLast();
                if(head.right != null){
                    nLast = nLast == null ? head.right : nLast;
                    dq.offerFirst(head.right);
                }
                if(head.left != null){
                    nLast = nLast == null ? head.left : nLast;
                    dq.offerFirst(head.left);
                }
            }
            //上面处理完毕后，就可以输出节点的值了
            System.out.print(head.value + " ");
            /*
                    下面就是做输出后的各种处理，包括nLast节点的更新，如果上面输出的是当前层的最后一个节点，是要进行换行操作的，
                还有输出方向的更改。
                   首先是if的判断条件，head==last说明已经到了当前层的最后一个节点，!dq.isEmpty()说明队列中还有节点需要打印，在这
               2种条件都满足的情况下，进行相应的处理。
                    lr：更改方向
                    last：将last更新到下一层的最后一个节点
                    换行
                    输出层号的信息
             */
            if(head == last && !dq.isEmpty()){
                lr = !lr;
                last = nLast;
                nLast = null;
                System.out.println();
                pringLevelAndOrientation(level++ , lr);
            }
        }
        System.out.println();
    }

    /**
     *
     * @param level：用于输出当前打印的是第几层
     * @param lr：根据lr的true和false来判断是从哪个方向进行打印
     */
    public void pringLevelAndOrientation(int level , boolean lr){
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? " left to right: " : " right to left: ");
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
