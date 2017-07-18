package com.june.tree;

public class BinaryTree<E> implements Tree<E> {
	
	private TreeNode<E> root; //根节点
	
	private Object[] data; //存放树节点的数组
	
	private int size; //树的大小
	
	private int depth; //树的深度
	
	public BinaryTree() {
		root = new TreeNode<>();
		data = new Object[size];
	}
	
	@Override
	public int depth() {
		return depth;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public E root() {
		return root.item;
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int search(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void preOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postOrder() {
		// TODO Auto-generated method stub
		
	}
		
	@SuppressWarnings("unused")
	private static class TreeNode<E> {
		E item;
		TreeNode<E> left;
		TreeNode<E> right;
		
		TreeNode() {}
		
		TreeNode(TreeNode<E> left, E item, TreeNode<E> right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}

	}

}
