package com.june.tree;

/**
 * AVL树，又称平衡二叉树，它是一棵空树或者具有以下性质的二叉树：
 * 1、首先它是一棵二叉查找树；
 * 2、它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 */
public class AVLTree extends BinarySearchTree<E> {
	
	@Override
	public Node<E> insert(E e) {
		return insert(root, e);
	}

	@Override
	public Node<E> insert(Node<E> p, E e) {
		Node<E> n = super.insert(p, e);
		adjustBalance(n);
		return n;
	}
	
	@Override
	public boolean remove(Object o) {
		Node<E> t = search(o);
		if (t != null) {
			boolean ret = super.remove(o);
			if (ret) {
				adjustBalance(t);
			}
			return ret;
		}
		return false;
	}

	/**
	 * 调整平衡
	 */
	private void adjustBalance(Node<E> p) {
		int leftDepth = depth(p.left);
		int rightDepth = depth(p.right);
		if (leftDepth - rightDepth > 1) {
			// 左边高，右旋调整
			rotateRight(p);
			if (depth(p.right) - depth(p.left) > 1) {
				// 右边高，左旋调整
				rotateLeft(p);
			}
		} else if (rightDepth - leftDepth > 1) {
			// 右边高，左旋调整
			rotateLeft(p);
			if (depth(p.left) - depth(p.right) > 1) {
				// 左边高，右旋调整
				rotateRight(p);
			}
		} else {
			// 不需要调整
		}
	}

	/**
	 * 左旋：
	 * 1、将当前结点右子树的左孩子作为当前结点的右子树
	 * 2、将当前结点作为其右子树的左孩子
	 */
	private void rotateLeft(Node<E> root) {
		Node<E> p = root.right;
		root.right = p.left;
		p.left = root;
		root = p;
	}

	/**
	 * 右旋：
	 * 1、将当前结点左子树的右孩子作为当前结点的左子树
	 * 2、将当前结点作为其左子树的右孩子
	 */
	private void rotateRight(Node<E> root) {
		Node<E> p = root.left;
		root.left = p.right;
		p.right = root;
		root = p;
	}

}