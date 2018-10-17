package com.june.tree;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 二叉查找树（Binary Search Tree），又称二叉排序树（Binary Sort Tree），亦称二叉搜索树
 * 特点：
 * <li>若左子树不空，则左子树上所有结点的值均小于它的根结点的值；</li>
 * <li>若右子树不空，则右子树上所有结点的值均大于它的根结点的值；</li>
 * <li>左、右子树也分别为二叉查找树；</li>
 * <li>不存在值相等的结点；</li>
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements Tree<E>, Serializable {
	
	private static final long serialVersionUID = 2545329670444241029L;

	protected Comparator<? super E> comparator;

	public BinarySearchTree() {}
	
	public BinarySearchTree(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}
	
	public BinarySearchTree<E> build(E[] arr) {
		root = new Node<>(arr[0]);
		Node<E> p = root;
		for (int i = 1, n = arr.length; i < n; i++) {
			p = insert(p, arr[i]);
		}
		return this;
	}
	
	@Override
	public Node<E> insert(E e) {
		return insert(root, e);
	}

	public Node<E> insert(Node<E> p, E e) {
		Node<E> t = p;
		if (t == null) {
			t = new Node<>(e);
			return t;
		}
		int result = compare(e, t.data);
		if (result < 0) {
			t.left = insert(t.left, e);
		} else if (result > 0) {
			t.right = insert(t.right, e);
		}
		return t;
	}

	@Override
	public Node<E> search(Object o) {
		return search(root, o);
	}
	
	public Node<E> search(Node<E> p, Object o) {
		if (p == null)
			return null;
		int result = compare(o, p.data);
		return result > 0 ? search(p.right, o) 
				: (result == 0 ? p : search(p.left, o));
	}

	@Override
	public boolean remove(Object o) {
		return remove(root, o);
	}
	
	/**
	 * 删除给定值所在结点，保证删除后的树仍然是一棵二叉搜索树
	 * @param p 从该结点开始查找并删除
	 * @param o 给定值
	 */
	public boolean remove(Node<E> p, Object o) {
		if (p == null || search(p, o) == null) {
			// 当前结点为空，或给定值不存在
			System.out.println("给定值不存在");
			return false;
		}
		System.out.println("要删除的值为" + o + "，当前节点：" + p);
		int result = compare(o, p.data);
		if (result == 0) {
			// 待删除元素与当前结点值相等，判断当前结点是否有左右子树
			if (p.left != null && p.right != null) {
				System.out.println("删除结点 " + p + " 的左右子树均存在");
				// 左右子树均存在，需要以下两个步骤
				// 1. 使用右子树的最小值（或左子树的最大值）替代当前结点的值
				p.data = min(p.right).data;
				// 2. 然后在其右子树中删除其中值最小的结点
				return remove(p.right, p.data);
			} else {
				System.out.println("删除结点 " + p + " 的左右子树至少有一个不存在");
				// 左右子树至少有一个不存在
				p = (p.left != null) ? p.left : p.right;
				System.out.println("删除后当前结点： " + p);
				return true;
			}
		} else if (result < 0) {
			// 小于当前结点值，到左子树中进行删除
			return remove(p.left, o);
		} else {
			// 大于当前结点值，到右子树中进行删除
		 	return remove(p.right, o);
		}
	}

	public Node<E> min() {
		return min(root);
	}

	private Node<E> min(Node<E> p) {
		if (p == null) {
			return null;
		}
		Node<E> temp = p;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}

	public Node<E> max() {
		return max(root);
	}

	private Node<E> max(Node<E> p) {
		if (p == null) {
			return null;
		}
		Node<E> temp = p;
		while (temp.right != null) {
			temp = temp.right;
		}
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	final int compare(Object e1, Object e2) {
		return comparator == null ? ((Comparable<? super E>)e1).compareTo((E)e2)
	            : comparator.compare((E)e1, (E)e2);
	}

}
