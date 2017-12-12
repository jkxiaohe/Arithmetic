package chapter1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiaozhu
 * 		猫狗队列的实现
 * 		因为要按照对象的特点来分别取出，所以可以创建多个队列来分别存储不同的对象；
 * 		当需要按照进出的顺序取出时，需要使用一个标记flag来记录每个实例对象进入队列的顺序，使用一个标记符号即可，每个对象都有唯一
 * 的一个进入队列的标记符号，然后从所有的队列中取出队尾的那个元素，比较他们的标记值谁大谁小。
 *
 */
public class Case4 {

	//使用2个不同的队列来分别存储对象，以及一个全局的时间戳记录变量
	private Queue<PetEnterQueue> dogQ;
	private Queue<PetEnterQueue> catQ;
	private long count;
	
	public Case4() {
		this.dogQ = new LinkedList<PetEnterQueue>();
		this.catQ = new LinkedList<PetEnterQueue>();
		this.count = 0;
	}
	
	//在向队列中添加对象时，首先要判断该对象的所属类型，然后连同其时间戳一同新建一个模型对象放入相应的队列中
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
	 * 		当弹出时，分为多种方式：2个队列都不为null，有一个为null，
	 *     ①比较2个队列的队尾元素的时间戳值，时间戳愈小，说明其入队更早，应该最先弹出；
	 *     ②当只有1个队列时，直接弹出元素即可
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
	 *	队列中存储的对象模型，因为对每个入队的对象新加了一个时间戳，所以需要一个新的模型来对每个对象进行一个包装，
	 *至少要有2个参数：对象，时间戳；
	 * 然后提供2个获取这2个参数的方法，用于最后的取值；
	 * 还有一个函数用于判断该对象的所属类型。
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
