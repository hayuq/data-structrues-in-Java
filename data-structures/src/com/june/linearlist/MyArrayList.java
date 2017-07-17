package com.june.linearlist;

import java.util.Arrays;

/**
 * 实现List接口中的几个常用方法，包括元素的添加、获取、删除等
 */
public class MyArrayList<E> extends MyAbstractList<E> {
	
	private int size; //大小
 
	private Object[] data; //存放元素的数组
	
//	private static final int DEFAULT_CAPACITY = 10; //默认大小为10
	
	private static final Object[] EMPTY_DATA = {};

	public MyArrayList() {
		data = EMPTY_DATA;
	}
	
	public MyArrayList(int capacity) {
		if (capacity > 0) {
			data = new Object[capacity];
		} else if (capacity == 0) {
			data = EMPTY_DATA;
		} else {
			throw new IllegalArgumentException("Illegal capacity: " + capacity);
		}
	}
	
	/**
	 * 将指定元素添加到数组末尾
	 */
	@Override
	public boolean add(E e) {
		//TODO 扩容操作
		data[size++] = e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		remove(index);
		return true;
	}

	/**
	 * 清除所有元素
	 */
	@Override
	public void clear() {
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
		}
		size = 0;
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return data(index);
	}

	/**
	 * 将指定位置的元素替换成指定元素，并返回原来的元素
	 */
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E oldValue = data(index);
		data[index] = element;
		return oldValue;
	}

	/**
	 * 在指定位置插入指定元素
	 */
	@Override
	public void add(int index, E element) {
		rangeCheck(index);
		//TODO 扩容操作
		
		// 将index位置及其后面的元素整体向后移动一位
		System.arraycopy(data, index, data, index + 1, size - index);
		//将指定元素放到index位置
		data[index] = element;
		size++;
	}

	/**
	 * 删除指定位置的元素
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);
		E value = data(index);
		//计算需要移动的元素个数
		int needMoveNum = size - index - 1;
		if (needMoveNum > 0) {
			// 将index位置后面的元素整体向前移动一位
			System.arraycopy(data, index + 1, data, index, needMoveNum);
		}
		return value;
	}
	
	/**
	 * 根据下标返回数组元素
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private E data(int index) {
		return (E) data[index];
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	/**
	 * 从前往后，依次查找元素在MyArrayList中第一次出现的位置
	 * 若找到则返回该位置，否则返回-1
	 * @param o
	 * @return
	 */
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (data[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(data[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 从后往前，依次查找元素在MyArrayList中第一次出现的位置
	 * 若找到则返回该位置，否则返回-1
	 * @param o
	 * @return
	 */
	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i > 0; i--) {
				if (data[i]== null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i > 0; i--) {
				if (o.equals(data[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * 检查下标是否越界，若越界则抛出IndexOutOfBoundsException
	 * @param index
	 */
	private void rangeCheck(int index) {
		if (index >= size || index < 0 ) {
			throw new IndexOutOfBoundsException("Index:" + index + ", size: " + size);
		}
	}

}
