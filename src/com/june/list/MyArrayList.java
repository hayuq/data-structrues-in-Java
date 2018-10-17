package com.june.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/**
 * 实现List接口中的几个常用方法，包括元素的添加、获取、删除等
 */
public class MyArrayList<E> extends MyAbstractList<E> implements RandomAccess, Cloneable, Serializable {
	
	private static final long serialVersionUID = -8097819797774446523L;

	transient int size;
	
	transient Object[] data;
	
	private static final int DEFAULT_CAPACITY = 10; //默认可以存放10个元素
	
	private static final Object[] EMPTY_DATA = {};
	
	private static final Object[] DEFAULT_CAPACITY_EMPTY_DATA = {};

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	public MyArrayList() {
		data = DEFAULT_CAPACITY_EMPTY_DATA;
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
	
	public MyArrayList(Collection<? extends E> c) {
        data = c.toArray();
        if ((size = data.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (data.getClass() != Object[].class)
                data = Arrays.copyOf(data, size, Object[].class);
        } else {
            // replace with empty array.
            this.data = EMPTY_DATA;
        }
    }
	
	/**
	 * 将指定元素添加到数组末尾
	 */
	@Override
	public boolean add(E e) {
		ensureCapacity(size + 1);
		data[size++] = e;
		return true;
	}

	/**
	 * 确保新添加的元素可以正常添加到数组中，因此需要判断是否需要对数组进行扩容操作
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity) {
		if (data == DEFAULT_CAPACITY_EMPTY_DATA) 
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		if (minCapacity > data.length)  //超出数组长度，需要扩容
			grow(minCapacity);
	}

	/**
	 * 扩容操作，扩容为原来的1.5倍
	 * @param minCapacity
	 */
	 private void grow(int minCapacity) {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity)
        	newCapacity = minCapacity;
        if (newCapacity > MAX_ARRAY_SIZE)
        	newCapacity = hugeCapacity(minCapacity);
        data = Arrays.copyOf(data, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }
    
    public void trimToSize() {
    	
    }

	@Override
	public boolean remove(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (data[i] == null) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(data[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
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
		ensureCapacity(size + 1);
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
		data[size--] = null; //将最后一个元素置为null，同时size减一
		return value;
	}
	
	/**
	 * 仅完成移除元素操作，不检查下标范围，也不返回原来元素的值
	 * @param index
	 */
	private void fastRemove(int index) {
		//计算需要移动的元素个数
		int needMoveNum = size - index - 1;
		if (needMoveNum > 0) {
			// 将index位置后面的元素整体向前移动一位
			System.arraycopy(data, index + 1, data, index, needMoveNum);
			// 等价于以下写法
			/*for (int i = index; i < size - index; i++) {
				data[i] = data[i + 1];
			}*/
		}
		data[size--] = null; //将最后一个元素置为null，同时size减一
	}
	
	/**
	 * 根据下标返回数组元素
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	E data(int index) {
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
			for (int i = size - 1; i >= 0; i--) {
				if (data[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
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
			throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyArrayList<E> o = (MyArrayList<E>)super.clone();
		o.data = Arrays.copyOf(data, size);
		return o;
	}
	
	private void writeObject(ObjectOutputStream os) throws IOException {
		os.defaultWriteObject();
		os.writeInt(size);
		//将数组输出到对象流中
		for (int i = 0; i < data.length; i++) {
			os.writeObject(data[i]);
		}
	}

	private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
		data = EMPTY_DATA;
		is.defaultReadObject();
		is.readInt(); //读取size属性值
		if (size > 0) {			
			ensureCapacity(size);
			Object[] arr = data;
			for (int i = 0; i < size; i++) {
				arr[i] = is.readObject();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length > size )
			return (T[]) Arrays.copyOf(data, a.length);
		System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object e : c)
            if (!contains(e))
                return false;
        return true;
	}

}
