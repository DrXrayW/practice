package xray.leetcode.basicStructure;

import java.util.*;
public class Heap<T extends Comparable<T>> {
	
	public static void main(String[] args) {
		Heap<Integer> x = new Heap<Integer>();
		x.add(3);
		x.add(1);
		x.add(1);
		x.add(3);
		x.add(2);
		
		while(!x.isEmpty()){
			System.out.println(""+x.pop());
		}
	}
	
	private List<T> arr = new ArrayList<T>();
	int capacity = Integer.MAX_VALUE;
	public int size(){
		return arr.size();
	}
	public Heap(){
	}
	
	public Heap(int capacity){
		this.capacity = capacity;
	}
	public boolean isEmpty(){
		return arr.isEmpty();
	}
	public boolean isFull(){
		return size() == capacity;
	}
	public boolean add(T element){
		if(isFull()){
			return false;
		}
		arr.add(element);
		siftUp(size()-1);
		return true;
	}
	private void siftUp(int index) {
		T element = arr.get(index);
		while(index!=0){
			int parent = getParent(index);
			if(arr.get(parent).compareTo(element)>0){
				swap(parent, index);
			}else{
				break;
			}
			index = parent;
		}
	}
	
	private void siftDown(int index){
		T element = arr.get(index);
		boolean swapped = true;
		while(swapped){
			swapped = false;
			Integer left = getLeft(index);
			Integer right = getRight(index);
			Boolean goLeft = shouldGoLeft(left, right, element);
			if(goLeft!=null){
				swapped = true;
				if(goLeft){
					swap(index, left);
				}else{
					swap(index, right);
				}
				index = goLeft ? left : right;
			}
		}
	}
	
	private Boolean shouldGoLeft(Integer left, Integer right, T element) {
		if(left==null&&right==null){
			return null;
		}
		if(right==null){
			return true;
		}
		if(left==null){
			return false;
		}
		T leftValue = arr.get(left);
		T rightValue = arr.get(right);
		
		boolean leftSmaller = leftValue.compareTo(rightValue) < 0;
		T minValue = leftSmaller? leftValue : rightValue;
		if(element.compareTo(minValue)>0){
			return leftSmaller; 
		}
		return null;
	}
	private void swap(int i1, int i2) {
		T tmp = arr.get(i1);
		arr.set(i1, arr.get(i2));
		arr.set(i2, tmp);
	}
	private Integer getParent(int index){
		if(index%2==0){
			index-=2;
		}else{
			index-=1;
		}
		index = index /2;
		if(index>=0){
			return index;
		}
		return null;
	}
	
	private Integer getLeft(int index){
		return getChild(index, true);
	}
	
	private Integer getRight(int index){
		return getChild(index, false);
	}
	
	private Integer getChild(int index, boolean left){
		if(left){
			index = index*2 + 1;
		}else{
			index = index*2 + 2;
		}
		if(index < size()){
			return index;
		}
		return null;
	}
	
	public T pop(){
		if(isEmpty()){
			return null;
		}
		T element = arr.get(0);
		int tail = size() - 1;
		if(tail!=0){
			arr.set(0, arr.get(tail));
		}
		arr.remove(tail);
		if(size()>0){
			siftDown(0);
		}
		return element;
	}
}
