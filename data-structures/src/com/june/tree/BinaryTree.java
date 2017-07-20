package com.june.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class BinaryTree<E> implements Tree<E> {
	
	protected transient Node<E> root; //根节点
	
	protected transient int size; //树的节点数
	
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
	
	public int depth(Node<E> p) {
		if (p == null) 
			return 0;
		//递归分别计算左右子树的深度
		int leftDepth = depth(p.left);
		int rightDepth = depth(p.right);
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
		delete(root);
	}
	
	//TODO 清除是这样？？？
	public void delete(Node<E> p) {
		if (p != null) {
			delete(p.left);
			delete(p.right);
			p = null;
			size--;
		}
		
		/** 
         * 1. 若p没有左子树，直接用p的右孩子取代它；
         * 2. 若p有左子树，找到其左子树的最右边的叶子结点r，用该叶子结点r来替代p，把r的左孩子作为r的父亲的右孩子；
         */
//		if (p == null)
//			return;
//        if(p.left == null) {
//        	p = p.right;
//        } else {
//            Node<E> r = p.left;
//            Node<E> prev = p.right;
//            while(r.right != null) {
//            	prev = r;
//            	r = r.right;
//            }
//            p.item = r.item;
//            // 若r不是p的左子树,p的左子树不变，r的左子树作为r的父结点的右孩子结点
//            if(prev != r) {
//            	prev.right = r.left;
//            } else {
//            	// 若r是p的左子树，则p的左子树指向r的左子树
//            	p.left = r.left;
//            }
//        }
	}
	
	@Override
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * 先序遍历：根 -> 左 -> 右
	 * @param p
	 */
	public void preOrder(Node<E> p) {
		if (p != null) {
			visit(p);
			preOrder(p.left);
			preOrder(p.right);
		}
	}
	
	@Override
	public void inOrder() {
		inOrder(root);
	}

	/**
	 * 中序遍历：左 -> 根 -> 右
	 * @param p
	 */
	public void inOrder(Node<E> p) {
		if (p != null) {
			inOrder(p.left);
			visit(p);
			inOrder(p.right);
		}
	}
	
	@Override
	public void postOrder() {
		postOrder(root);
	}

	/**
	 * 后序遍历：左 -> 右 -> 根
	 * @param p
	 */
	public void postOrder(Node<E> p) {
		if (p != null) {
			postOrder(p.left);
			postOrder(p.right);
			visit(p);
		}
	}
	
	@Override
	public void levelOrder() {
		levelOrder(root);
	}

	/**
	 * 层序遍历，基于队列
	 * @param p
	 */
	public void levelOrder(Node<E> p) {
		if (p == null) 
			return;
		Queue<Node<E>> queue = new LinkedList<>(); // 层序遍历时保存结点的队列
		queue.offer(p); // 初始化
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
		System.out.printf("%3d", n.data);
	}
		
	static class Node<E> {
		E data;
		Node<E> left;
		Node<E> right;
		
		Node() {}
		
		Node(E item) {
			this(null, item, null);
		}
		
		Node(Node<E> left, E data, Node<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
		}
		
	}
	
}
