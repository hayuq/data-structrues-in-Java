package com.june.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class BinaryTree<E> implements Tree<E> {
	
	protected Node<E> root; //根节点
	
	protected int size; //树的节点数
	
	protected Comparator<? super E> comparator;
	
	/**
	 * 返回树的深度
	 * @param root
	 * @return
	 */
	@Override
	public int depth() {
		return depth(root);
	}
	
	protected Node<E> root() {
		return root;
	}
	
	public int depth(Node<E> root) {
		if (root == null) 
			return 0;
		//递归分别计算左右子树的深度
		int leftDepth = depth(root.left);
		int rightDepth = depth(root.right);
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 添加元素
	 * @param e
	 */
	public abstract boolean insert(E e);

	/**
	 * 查找元素
	 * @param o
	 * @return
	 */
	public abstract Node<E> search(Object o);
	
	/**
	 * 删除某个元素
	 * @param o
	 * @return
	 */
	public abstract boolean remove(Object o);
	
	@Override
	public void clear() {
		clear(root);
	}
	
	public void clear(Node<E> root) {
		if (root != null) {
			clear(root.left);
			clear(root.right);
			root = null;
			size = 0;
		}
	}
	
	@Override
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * 先序遍历：根 -> 左 -> 右
	 * @param root
	 */
	public void preOrder(Node<E> root) {
		if (root != null) {
			visit(root);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	@Override
	public void inOrder() {
		inOrder(root);
	}

	/**
	 * 中序遍历：左 -> 根 -> 右
	 * @param root
	 */
	public void inOrder(Node<E> root) {
		if (root != null) {
			inOrder(root.left);
			visit(root);
			inOrder(root.right);
		}
	}
	
	@Override
	public void postOrder() {
		postOrder(root);
	}

	/**
	 * 后序遍历：左 -> 右 -> 根
	 * @param root
	 */
	public void postOrder(Node<E> root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			visit(root);
		}
	}
	
	@Override
	public void levelOrder() {
		levelOrder(root);
	}

	/**
	 * 层序遍历，基于队列
	 * @param root
	 */
	public void levelOrder(Node<E> root) {
		if (root == null) 
			return;
		Queue<Node<E>> queue = new LinkedList<>(); // 层序遍历时保存结点的队列
		queue.offer(root); // 初始化
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			visit(node); // 访问节点
			if (node.left != null)
				queue.offer(node.left);
			if (node.right != null)
				queue.offer(node.right);
		}
	}

	private void visit(Node<E> n) {
		System.out.println(n.item + "");
	}
		
	static class Node<E> {
		E item;
		Node<E> left;
		Node<E> right;
		
		Node() {
		}
		
		Node(E item) {
			this(null, item, null);
		}
		
		Node(Node<E> left, E item, Node<E> right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}

	}
	
}
