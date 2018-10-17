package com.june.tree;

public interface Tree<E> {
	
	int depth();
	
	int size();
	
	boolean isEmpty();
		
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

	/**
	 * 层序遍历
	 */
	void levelOrder();
	
}
