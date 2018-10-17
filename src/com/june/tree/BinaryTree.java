package com.june.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class BinaryTree<E> implements Tree<E> {
	
	protected transient Node<E> root; //根节点
	
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
		if (p == null) { 
			return 0;
		}
		//递归分别计算左右子树的深度
		int leftDepth = depth(p.left);
		int rightDepth = depth(p.right);
		return Math.max(leftDepth, rightDepth) + 1;
	}

	public int leafCount() {
		return leafCount(root);
	}

	public int leafCount(Node<E> p) {
		if (p == null) {
			return 0;
		}
		if (p.left == null && p.right == null) {
			return 1;
		}
		return leafCount(p.left) + leafCount(p.right);
	}
	
	@Override
	public int size() {
		return size(root);
	}

	private int size(Node<E> p) {
		if (p == null) {
			return 0;
		}
		return size(p.left) + size(p.right) + 1;
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * 添加元素
	 * @param e
	 * @return
	 */
	public abstract Node<E> insert(E e);

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
	
	public boolean contains(Object o) {
		return search(o) != null;
	}
		
	@Override
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * 先序遍历：根 -> 左 -> 右，递归方式
	 * @param p
	 */
	public void recursivePreOrder(Node<E> p) {
		if (p != null) {
			visit(p);
			recursivePreOrder(p.left);
			recursivePreOrder(p.right);
		}
	}

	/**
	 * 先序遍历：根 -> 左 -> 右，基于栈实现
	 * @param p
	 */
	public void preOrder(Node<E> p) {
		if (p == null)	return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> temp = p;
		while (temp != null || !stack.isEmpty()) {
			// 当前结点不为空或栈中有数据
			while (temp != null) {
				// 遍历当前结点
				visit(temp);
				stack.push(temp);
				// 然后将左子树的结点全都添加到栈中
				temp = temp.left;
			}
			// 当前结点和左子树遍历完成，准备遍历右子树
			temp = stack.pop().right;
		}
	}
	
	@Override
	public void inOrder() {
		inOrder(root);
	}

	/**
	 * 中序遍历：左 -> 根 -> 右，递归方式
	 * @param p
	 */
	public void recursiveInOrder(Node<E> p) {
		if (p != null) {
			recursiveInOrder(p.left);
			visit(p);
			recursiveInOrder(p.right);
		}
	}

	/**
	 * 中序遍历：左 -> 根 -> 右，基于栈实现
	 * @param p
	 */
	public void inOrder(Node<E> p) {
		if (p == null)	return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> temp = p;
		while (temp != null || !stack.isEmpty()) {
			while (temp != null) {
				// 首先当前结点入栈
				stack.push(temp);
				// 然后将其左子树的结点全都添加到栈中
				temp = temp.left;
			}
			temp = stack.pop();
			visit(temp);
			// 当前结点和其左子树遍历完成，准备遍历右子树
			temp = temp.right;
		}
	}
	
	@Override
	public void postOrder() {
		postOrder(root);
	}

	/**
	 * 后序遍历：左 -> 右 -> 根，递归方式
	 * @param p
	 */
	public void recursivePostOrder(Node<E> p) {
		if (p != null) {
			recursivePostOrder(p.left);
			recursivePostOrder(p.right);
			visit(p);
		}
	}

	/**
	 * 后序遍历：左 -> 右 -> 根，基于栈实现
	 * @param p
	 */
	public void postOrder(Node<E> p) {
		if (p == null)	return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> curr = p; // 当前结点
		Node<E> prev = null; // 上一次遍历的结点
		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
				// 首先将当前结点入栈
				stack.push(curr);
				// 然后将其左子树结点全都添加到栈中
				curr = curr.left;
			}
			Node<E> peek = stack.peek();
			if (peek.right == null || peek.right == prev) {
				// 右子树不存在或已经遍历，依次输出栈中的结点
				visit(peek);
				stack.pop();
				prev = peek;
			} else {
				// 需要遍历右子树
				curr = peek.right;
			}
		}
	}
	
	@Override
	public void levelOrder() {
		levelOrder(root);
	}

	/**
	 * 层序遍历
	 * @param p
	 */
	public void levelOrder(Node<E> p) {
		if (p == null)	return;
		// 层序遍历时保存结点的队列
		Queue<Node<E>> queue = new LinkedList<>(); 
		// 当前结点入队列
		queue.offer(p);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			// 访问结点
			visit(node);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}

	private void visit(Node<E> n) {
		System.out.printf("%3d", n.data);
	}
		
	protected static class Node<E> {
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
