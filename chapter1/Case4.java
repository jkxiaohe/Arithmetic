package chapter1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiaozhu
 * 		è�����е�ʵ��
 * 		��ΪҪ���ն�����ص����ֱ�ȡ�������Կ��Դ�������������ֱ�洢��ͬ�Ķ���
 * 		����Ҫ���ս�����˳��ȡ��ʱ����Ҫʹ��һ�����flag����¼ÿ��ʵ�����������е�˳��ʹ��һ����Ƿ��ż��ɣ�ÿ��������Ψһ
 * ��һ��������еı�Ƿ��ţ�Ȼ������еĶ�����ȡ����β���Ǹ�Ԫ�أ��Ƚ����ǵı��ֵ˭��˭С��
 *
 */
public class Case4 {

	//ʹ��2����ͬ�Ķ������ֱ�洢�����Լ�һ��ȫ�ֵ�ʱ�����¼����
	private Queue<PetEnterQueue> dogQ;
	private Queue<PetEnterQueue> catQ;
	private long count;
	
	public Case4() {
		this.dogQ = new LinkedList<PetEnterQueue>();
		this.catQ = new LinkedList<PetEnterQueue>();
		this.count = 0;
	}
	
	//�����������Ӷ���ʱ������Ҫ�жϸö�����������ͣ�Ȼ����ͬ��ʱ���һͬ�½�һ��ģ�Ͷ��������Ӧ�Ķ�����
	public void add(Pet pet) {
		if(pet.getPetType().equals("dog")) {
			this.dogQ.add(new PetEnterQueue(pet,this.count++));
		}else if(pet.getPetType().equals("cat")) {
			this.catQ.add(new PetEnterQueue(pet,this.count++));
		}else {
			throw new RuntimeException("err , not found");
		}
	}
	
	/**
	 * 		������ʱ����Ϊ���ַ�ʽ��2�����ж���Ϊnull����һ��Ϊnull��
	 *     �ٱȽ�2�����еĶ�βԪ�ص�ʱ���ֵ��ʱ�����С��˵������Ӹ��磬Ӧ�����ȵ�����
	 *     �ڵ�ֻ��1������ʱ��ֱ�ӵ���Ԫ�ؼ���
	 */
	public Pet pollAll() {
		if(!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
			if(this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
				return this.dogQ.poll().getPet();
			}else {
				return this.catQ.poll().getPet();
			}
		}else if(!this.dogQ.isEmpty()) {
			return this.dogQ.poll().getPet();
		}else if(!this.catQ.isEmpty()) {
			return this.catQ.poll().getPet();
		}else {
			throw new RuntimeException("err , not found");
		}
	}
	
	public Dog pollDog() {
		if(!this.dogQ.isEmpty()) {
			return (Dog)this.dogQ.poll().getPet();
		}else {
			throw new RuntimeException("err , not found");
		}
	}
	
	public Cat pollCat() {
		if(!this.catQ.isEmpty()) {
			return (Cat)this.catQ.poll().getPet();
		}else {
			throw new RuntimeException("err , not found");
		}
	}
	
	public boolean isEmpty() {
		return this.dogQ.isEmpty() && this.catQ.isEmpty();
	}
	
	/**
	 *	�����д洢�Ķ���ģ�ͣ���Ϊ��ÿ����ӵĶ����¼���һ��ʱ�����������Ҫһ���µ�ģ������ÿ���������һ����װ��
	 *����Ҫ��2������������ʱ�����
	 * Ȼ���ṩ2����ȡ��2�������ķ�������������ȡֵ��
	 * ����һ�����������жϸö�����������͡�
	 */
	public class PetEnterQueue{
		private Pet pet;
		private long count;
		public PetEnterQueue(Pet pet,long count) {
			this.pet = pet;
			this.count = count;
		}
		public Pet getPet() {
			return this.pet;
		}
		public long getCount() {
			return this.count;
		}
		public String getEnterPetType() {
			return this.pet.getPetType();
		}
	}
	
	public class Pet{
		private String type;
		public Pet(String type) {
			this.type = type;
		}
		public String getPetType() {
			return this.type;
		}
	}
	
	public class Dog extends Pet{
		public Dog() {
			super("dog");
		}
	}
	
	public class Cat extends Pet{
		public Cat() {
			super("cat");
		}
	}
	
}
