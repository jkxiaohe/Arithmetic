package chapter3;

import java.util.Stack;

/**
 *      题目：分别用递归和非递归的方式实现二叉树的遍历
 */
public class Case1 {

    public void preOrderRecur(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public void inOrderRecur(Node head){
        if(head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    public void posOrderRecur(Node head){
        if(head == null){
            posOrderRecur(head.left);
            posOrderRecur(head.right);
            System.out.println(head.value + " ");
        }
    }

    //使用栈的方式实现

    /**
     *      前序遍历的方式：根，左，右
     * @param head
     *      方法：开始的时候先将当前节点压入到栈中，然后进入循环。
     *      循环的方式是：首先从栈中弹出元素，该元素必定为根，所以输出其值，然后分别将当前节点的右节点和左节点
     *    压入栈中，这样下次从栈中弹出来的时候一定是先弹出左孩子，然后再弹出右孩子，满足根左右的要求。
     */
    public void preOrderUnRecur(Node head){
        System.out.println("pre-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value + " ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     *      中序遍历：顺序是根，左，右，所以只要当前节点不为null，那么将当前节点压入到栈中，并顺序查找其left节点，只要left节点不为null，
     *    便将其left节点也压入到栈中，如果发现为null的节点，那么将当前栈中的数据弹出，并随即将head设置为right右节点，因为左孩子
     *    是没有右节点的，只有上一层的根节点有，所以可以确保顺序是左根右，而且stack中存储的节点都是根左，可以确保每次都向上返回
     *    一个正确的结果。
     */
    public void inOrderUnRecur(Node head){
        System.out.println("in-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.println(head.value + "  ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     *      使用2个栈来实现后序遍历：后序遍历的顺序是左右根，是先左，如果使用一个栈来存储的话，那么从上到下应该先是左，右，根，但
     *    使用一个栈去按照这种顺序将节点压入栈中的话，根节点要不和左孩子相连，要不和右孩子相连，左右孩子节点是不相连的，所以考虑
     *    使用2个栈去存储这些节点。
     *          使用2个栈来存储，使用栈s1来调整节点的顺序，使用s2来存放排好顺序的节点，s1需要一层一层的去遍历，首先将根节点存入（根节点
     *     在s2中是最后)，再将s1的左节点存入，s1的右节点存入，这样从s1中弹出节点时，在s2中的顺序就变为了s1的左节点在上部，s1的右节点
     *     在下部，保证了从s2中取出节点的时候是先左后右。
     */
    public void posOrderUnRecur1(Node head){
        System.out.println("pos-order: ");
        if(head != null){
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if(head.left != null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.println(s2.pop().value + "  ");
            }
        }
    }

    public void posOrderUnRecur2(Node h){
        System.out.println("pos-order: ");
        if(h != null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while(!stack.isEmpty()){
                c = stack.peek();
                if(c.left != null && h != c.left && h != c.right){
                    stack.push(c.left);
                }else if(c.right != null && h != c.right){
                    stack.push(c.right);
                }else{
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
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
