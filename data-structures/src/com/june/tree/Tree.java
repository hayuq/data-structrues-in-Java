package com.june.tree;

public interface Tree<E> {
	
	int deepth();
	
	int size();
	
	E root();
	
	void add(E e);
	
	E get(int index);
	
	E set(int index, E e);
	
	void add(int index, E e);
	
	int search(Object o);
	
	E remove(int index);
	
	boolean remove(Object o);
	
	/**
	 * 前序遍历
	 */
	void preOrder();
	
	/**
	 * 中序遍历
	 */
	void inOrder();
	
	/**
	 * 后序遍历
	 */
	void postOrder();

}
