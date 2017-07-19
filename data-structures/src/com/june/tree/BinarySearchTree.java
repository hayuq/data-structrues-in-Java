package com.june.tree;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 二叉查找树（Binary Search Tree），又称二叉排序树（Binary Sort Tree），亦称二叉搜索树
 * 特点：
 * <li>若左子树不空，则左子树上所有结点的值均小于或等于它的根结点的值；</li>
 * <li>若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；</li>
 * <li>左、右子树也分别为二叉查找树；</li>
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements Tree<E>, Serializable {
	
	private static final long serialVersionUID = 2545329670444241029L;

	public BinarySearchTree() {
		root = null;
		comparator = null;
	}
	
	public BinarySearchTree(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}
	
	public Node<E> create(Node<E> node, E[] arr, int n) {
		for (int i = 0; i < n; i++) {
			node = insert(node, arr[i]);
		}
		return node;
	}
	
	@Override
	public boolean insert(E e) {
		return insert(root, e) != null;
	}

	public Node<E> insert(Node<E> root, E e) {
		if (root == null) {
			root = new Node<>(e);
			size++;
			return root;
		}
		int result = compare(e, root.item);
		if (result < 0) {
			root.left = insert(root.left, e);
		}
		if (result > 0) {
			root.right = insert(root.right, e);
		}
		size++;
		return root;
	}

	@Override
	public Node<E> search(Object o) {
		return search(root, o);
	}
	
	public Node<E> search(Node<E> root, Object o) {
		if (root == null)
			return null;
		int result = compare(o, root.item);
		return result > 0 ? search(root.right, o) 
				: (result == 0 ? root : search(root.left, o));
	}

	@Override
	public boolean remove(Object o) {
		return remove(root, o);
	}
	
	public boolean remove(Node<E> root, Object o) {
		if (root == null)
			return false;
		int result = compare(o, root.item);
		//待删除元素与当前节点值相等
		if (result == 0) { 
			//TODO 删除操作
			//判断当前节点是否有左右子树
			if (root.left == null && root.right == null) {
				//左右子树均为空(叶子节点)，直接将当前节点置为null
				root = null;				
			} else if (root.left != null) { //存在左子树
				root = root.left;
			} else if (root.right != null) { //存在右子树
				root = root.right;
			} else { //左右子树均不为空
				
			}
			size--;
			return true;
		} else if (result < 0) {
			return remove(root.left, o);
		} else {
			return remove(root.right, o);
		}
		
		//递归删除节点
//		Node<E> p = root;
//        /** 
//         * 1. 若p没有左子树，直接用p的右孩子取代它；
//         * 2. 若p有左子树，找到其左子树的最右边的叶子结点r，用该叶子结点r来替代p，把r的左孩子作为r的父亲的右孩子；
//         */
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
	
	@SuppressWarnings("unchecked")
	final int compare(Object e1, Object e2) {
		return comparator == null ? ((Comparable<? super E>)e1).compareTo((E)e2)
	            : comparator.compare((E)e1, (E)e2);
	}
	
}
