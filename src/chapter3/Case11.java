package chapter3;

import java.util.HashMap;
import java.util.Map;

/**
 *      题目：找到二叉树中符合搜索二叉树条件的最大拓扑结构
 *      思路：对每个节点建立一个拓扑贡献记录。
 *              拓扑贡献记录：即每个节点包含2个额外的信息：
 *                      第一个：左子树可为当前节点贡献的节点数目
 *                      第二个：右子树可为当前节点贡献的节点数目
 *              核心：如果分别得到了h左右两个孩子为头的拓扑贡献记录，可以快速的得到h为头的拓扑贡献记录。对左右2个节点本身进行一个
 *                      判断，如果满足要求，将其拓扑贡献图相加，并将孩子节点自身也加上。
 *
 */
public class Case11 {

    /*
        Record记录每个节点的额外信息，包括：l：左边拓扑记录数
     */
    public class Record{
        public int l;
        public int r;
        public Record(int left , int right){
            this.l = left;
            this.r = right;
        }
    }

    /**
     *      作用：每个节点都有一个额外的记录，使用<key,value>的方式来存储是最方便的，所以这里使用Map来存储。
     *              其key为每个Node对象，Record对应该Node的信息。
     *              整个过程大概是利用二叉树的后序遍历。
     * @param head
     * @return
     */
    public int bstTopoSize2(Node head){
        Map<Node , Record> map = new HashMap<Node , Record>();
        return posOrder(head , map);
    }

    /**
     *      作用：构造整个二叉树的拓扑贡献图，要从二叉树的底部开始，所以要先对左右子树进行递归查找，
     *              然后在每个节点遍历完后，要对拓扑图的信息进行更新，这个过程由modifyMap()函数来完成，
     *              并且放在整个函数的最后，这一步是最后迭代完成的。
     * @param h：当前节点
     * @param map：key-value信息图，从最下层开始构造，每次向上返回时都要更新。
     * @return
     */
    public int posOrder(Node h , Map<Node , Record> map){
        //此时遍历到了叶子节点的末尾，对应的拓扑图left和right都为0
        if(h == null){
            return 0;
        }
        //构造当前节点前，首先构造其左右孩子节点
        int ls = posOrder(h.left , map);
        int rs = posOrder(h.right , map);
        //左右2边都构造好后，对当前节点进行构造，当前节点的额外信息：left和right都要重新更新
        //注意在这里不接受modifyMap函数的返回值，modifyMap函数的返回值在modifyMap函数里自己更改
        modifyMap(h.left , h.value , map , true);
        modifyMap(h.right , h.value , map , false);
        /*
                从Map对象中取出节点对应的信息记录，由于父节点实际上就是其下面的子节点累加形成的，故上面的2个函数的返回值
          是不需要接收的
         */
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        /*
                父节点的Record由左右2个节点形成，每个子节点的left和right相加，再算上其本身，即是最后的答案。
                要注意的是，叶子节点的情况例外：因为叶子节点的Record为（0，0），在刚开始的时候没有任何语句进行，所以在这里要
            进行特殊的赋值，记录Record==null，即是叶子节点的情况，取0值。
         */
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        //上面计算完毕后，将当前节点Node及记录Record加入到Map中
        map.put(h , new Record(lbst , rbst));
        //从当前节点及其子节点中找出最大拓扑大小中最大的那个
        return Math.max(lbst + rbst + 1 , Math.max(ls , rs));
    }

    /**
     *      作用：修改Map中存储的信息，当一个节点不属于父节点的拓扑图时，同时以为着其子节点也不属于
     * @param n：查找到的节点对象（不一定是当前的节点）
     * @param v：父节点的值，用来与父节点下的某个节点值进行比较，判断属不属于父节点下的二叉搜索树
     * @param m：Map对象，从树下往树上回退的过程中，Map中存储的<key,value>会发生变化
     * @param s：标志符号，有2个值：true：当前查找的节点n是父节点的左子树；false：右子树
     * @return：返回当前查找的节点n拓扑贡献记录数目
     */
    public int modifyMap(Node n , int v , Map<Node , Record> m , boolean s){
        /*
                递归的终止条件：叶子节点尾部：null，
                关于第二个条件：当map中不包含n节点对象时，直接返回一个0，此种情况发生在叶子节点上面，对于叶子节点，其额外
           信息一定为（0，0），这个设置由上面的函数posOrder（）来完成。
         */
        if(n == null || (!m.containsKey(n))){
            return 0;
        }
        //如果在Map中有该节点的记录，那么取出，每次向上返回的时候可能会进行更新
        Record r = m.get(n);
        /*
        if的第一种情况：
                s==true，说明是父节点的左孩子；s==false，说明是父节点的右孩子；
                value(left) > value(parent) && value(right) < value(parent)，都是不满足要求的情况，处理方法为：
                从Map中将该节点的对象移除，并返回当前节点所拥有的节点数目（包括其本身），用于将该节点及其子树全部都排除在外；
        if的第二种情况：
                负责处理第一种情况：当节点不满足要求时，需要进行父节点中将包括该节点的数目删除。
                minus可以得到该节点不满足要求的节点的数目，仍然是通过modifyMap函数本身来实现的，在传值时，要将不符合条件的值
            传过去，所以根据了s的真假来判断；
                minus有值后，根据s的真假判断是左右哪边进行删除操作；
                当删除操作完成后，即可将最新的节点数目放入到map对象中
         */
        if((s && n.value > v) || ((!s) && n.value < v)){
            m.remove(n);
            return r.l + r.r + 1;
        }else{
            int minus = modifyMap(s ? n.right : n.left , v , m , s);
            if(s){
                r.r = r.r - minus;
            }else{
                r.l = r.l - minus;
            }
            m.put(n , r);
            return minus;
        }
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
