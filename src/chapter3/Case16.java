package chapter3;

/**
 *      题目：判断t1树中是否有与t2树拓扑结构完全相同的子树
 *      思路：首先是把t1树和t2树按照先序遍历的方式序列化，接下来只要验证t2的序列化子串是否包含在t1中即可。
 *              KMP算法可以在线性时间内解决这类问题。
 */
public class Case16 {

    public boolean isSubtree(Node t1 , Node t2){
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str , t2Str) != -1;
    }

    public String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //KMP算法：????????????
    public int getIndexOf(String s , String m){
        if(s == null || m == null || m.length() < 1 || s.length() < m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while(si < ss.length && mi < ms.length){
            if(ss[si] == ms[mi]){
                si++;
                mi++;
            }else if(next[mi] == -1){
                si++;
            }else{
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while(pos < next.length){
            if(ms[pos-1] == ms[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[pos++] = 0;
            }
        }
        return next;
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
