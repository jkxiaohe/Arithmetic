package chapter1;

import java.util.LinkedList;

/**
 * @author xiaozhu
 *		���ɴ������ֵ������
 *		˼·�����ȴ��ڵĴ�Сֵ�ǹ̶��ģ�����ʹ��һ���������洢�����е�ֵ��
 *		�����鳤��Ϊn�����ڴ�СΪw����
 *	���������˳������������±�洢�������У�ʹ���±�ĺô����±��ǰ��������ģ�����ʹ���±����ֱ��ȡ��ֵ�����бȽϣ�
 *		��������ÿ�����ֵ��±�i���뵽�����е�ԭ��
 *			��ÿ�η�����±궼���ӵ���β��Ŀ�ģ�����������ڶ�β����õ������ڶ�ͷ������ʱ���Ӷ�ͷ�������ʱ���Ӷ�β�룻
 *			��Ϊ��֤�������±�ĸ���<=���������ɵ����ִ�Сw��ÿ�ζ�����Щ�������ڵ������±����ɾ����ʹ�÷���Ϊ��i-w��i������
 *		��ǰ������������������±�λ�ã�(i-w) ���ڵ����־�Ϊ��ǰ���ڵ�w�����ֵ��±꣬<=(i-w) ���±��Ѿ������������ԣ�ֱ�ӴӶ���
 *		��ɾ�����ɣ����ڴ�����˳���������ұ��ƶ������Կ��԰����ж�ɾ����ʹ�÷���Ϊ����ͷ�±� == (i-w) ,����˳���ƶ������ԣ�����
 *		ʹ��==�ţ���ȻҲ����ʹ��<=;
 *			��ÿ�ζ���������������ֵ��±���ڶ�ͷ��λ�ã�ʵ�ַ���Ϊ�������������У�������Ϊ�˻�ȡ�������е����ֵ����Щ����С��
 *		���ֿ���ֱ�Ӻ��Ե����������������±�ʱ�����������жϵ�ǰ��β�����ֺ������ֵĴ�С��ϵ���У�
 *				��1����β����<=�����֣�����β�����ֳ��ӣ�ֱ����������>������Ϊֹ�����߶���Ϊnull�����Ӳ���Ӱ��������ȷ�ԣ���Ϊ
 *			�¼�����±�ֵ�϶����ڶ����е������±꣬�϶��ڴ��ڵ�ǰ���ɷ�Χ���ǰ��Ԫ�أ������ǵ�ǰ��������ֵ�е����ֵ����Щ
 *			С�ĳ��Ӳ�Ӱ�����ֵ�Ľ����
 *				��2����β����>�����֣�ֱ�ӽ����±�ӵ���β��ȥ������ֻҪ��֤��ͷ�����ֵ���±꼴�ɡ�
 */
public class Case6 {

	public int[] getMaxWindow(int[] arr,int w) {
		//�쳣������жϣ�������Ϊnull���ڴ��ڴ�С<1��������ĳ���<���ڵĴ�С
		if(arr==null || w<1 || arr.length<w) {
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length-w+1];
		int index = 0;
		for(int i=0;i<arr.length;i++) {
			//���жϵ�ʱ��Ҫ��֤��������ֵ����
			while(!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[i]) {
				qmax.pollLast();    //�������±����
			}
			qmax.addLast(i);
			if(qmax.peekFirst() == i-w) {
				qmax.pollFirst();
			}
			//����ﵽ�˴��ڵĴ�С����ô��ʼ���������
			if(i>=w-1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}
	
}