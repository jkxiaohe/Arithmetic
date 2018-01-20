package chapter3;

/**
 *      题目：根据后序数组重建搜索二叉树
 *      思路1：后序遍历的特点是左右根，根节点在数组的最后一位，以根节点作为区分条件，左子树的节点都小于根，如果有，那么应该在
 *          数组的前面；右子树的节点都大于根，如果有，那么应该在数组的后面。
 *                  根据这个条件进行区分，可以将数组进行划分，左子树的最后一个节点下标应该和右子树-1的下标是相同的；然后分别对左右子树
*            进行递归判断。
 *                  注意遍历到最后的特殊情况：即只剩下一个节点。此时start和end指向同一个位置，返回为true。
 */
public class Case18 {

    //判断是否是后序遍历的结果
    public boolean isPostArray(int[] arr){
        //首先对数组异常的情况进行一个判断
        if(arr == null || arr.length == 0){
            return false;
        }
        return isPost(arr , 0 , arr.length - 1);
    }

    /**
     * @param arr：查找的数组对象
     * @param start：开始下标
     * @param end：结束下标
     * @return
     */
    public boolean isPost(int[] arr , int start , int end){
        //结束条件：只剩一个节点的时候，返回true
        if(start == end){
            return true;
        }
        //less：代表比根节点小的节点的下标位置，-1为默认值
        int less = -1;
        //more：代表比根节点大的节点的下标位置，end为默认值
        int more = end;
        /*
                从当前该数组的起始位置start到end位置进行判断，arr[end]最后一个节点即为根节点，
                前面的所有节点值，要么>根节点，要么<根节点，
                对于左子树，每次找到后都要进行更新，这样可以确保less指向的是左子树的最后一个节点的位置；
                对于右子树，只更新一次，后面找到后不再进行更新，为了避免更新，这里采用了三目运算符的方式。
         */
        for(int i = start ; i < end ; i++){
            if(arr[end] > arr[i]){
                less = i;
            }else{
                more = more == end ? i : more;
            }
        }
        //临近最后的特殊情况：一个节点值之前是2个节点，只会更新less和more中的一个，其中必定有一个是没有被更新的，所以这里采用||。
        if(less == -1 || more == end){
            return isPost(arr , start , end -1);
        }
        //二叉搜索树的判断条件，more-1应该和less相等
        if(less != more -1){
            return false;
        }
        //继续判断子树是否满足条件
        return isPost(arr , start , less) && isPost(arr , more , end-1);
    }

    //根据数组重新生成一个树
    public Node posArrayToBST(int[] posArr){
        if(posArr == null){
            return null;
        }
        return posToBST(posArr , 0 , posArr.length - 1);
    }

    public Node posToBST(int[] posArr , int start , int end){
        if(start > end){
            return null;
        }
        Node head = new Node(posArr[end]);
        int less = -1;
        int more = end;
        for(int i = start ; i < end ; i++){
            if(posArr[end] > posArr[i]){
                less = i;
            }else{
                more = more == end ? i : more;
            }
        }
        head.left = posToBST(posArr , start , less);
        head.right = posToBST(posArr , more , end -1);
        return head;
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
