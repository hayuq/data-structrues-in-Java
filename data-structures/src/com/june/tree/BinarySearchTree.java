package com.june.tree;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 二叉查找树（Binary Search Tree），又称二叉排序树（Binary Sort Tree），亦称二叉搜索树
 * 特点：
 * <li>若左子树不空，则左子树上所有结点的值均小于它的根结点的值；</li>
 * <li>若右子树不空，则右子树上所有结点的值均大于它的根结点的值；</li>
 * <li>左、右子树也分别为二叉查找树；</li>
 */
public class BinarySearchTree<E> extends BinaryTree<E> implements Tree<E>, Serializable {
	
	private static final long serialVersionUID = 2545329670444241029L;
	
	/**
	 * 用于保证树中节点的有序性
	 */
	protected Comparator<? super E> comparator;

	public BinarySearchTree() {
		root = null;
		comparator = null;
	}
	
	public BinarySearchTree(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}
	
	public BinarySearchTree<E> build(E[] arr, int n) {
		root = new Node<>(arr[0]);
		Node<E> p = root;
		for (int i = 1; i < n; i++) {
			p = insert(p, arr[i]);
		}
		return this;
	}
	
	@Override
	public boolean insert(E e) {
		return insert(root, e) != null;
	}

	public Node<E> insert(Node<E> p, E e) {
		if (p == null) {
			p = new Node<>(e);
			size++;
			return p;
		}
		int result = compare(e, p.data);
		if (result < 0) {
			//插入的节点值比当前节点值小，则插入到左子树
			p.left = insert(p.left, e);
		}
		if (result > 0) {
			//插入的节点值比当前节点值大，则插入到右子树
			p.right = insert(p.right, e);
		}
		return p;
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
		if (search(root, o) == null)
			return false;
		return remove(root, o);
	}
	
	public boolean remove(Node<E> p, Object o) {
		if (p == null)
			return false;
		int result = compare(o, p.data);
		//待删除元素与当前节点值相等
		if (result == 0) { 
			//TODO 删除操作
			//判断当前节点是否有左右子树
			if (p.left == null && p.right == null) {
				//左右子树均为空(叶子节点)，直接将当前节点置为null
				p = null;				
			} else if (p.left != null) { //存在左子树
				p = p.left;
			} else if (p.right != null) { //存在右子树
				p = p.right;
			} else { //左右子树均不为空
				
			}
			size--;
			return true;
		} else if (result < 0) {
			return remove(p.left, o);
		} else {
			return remove(p.right, o);
		}
		
        /** 
         * 1. 若p没有左子树，直接用p的右孩子取代它；
         * 2. 若p有左子树，找到其左子树的最右边的叶子结点r，用该叶子结点r来替代p，把r的左孩子作为r的父亲的右孩子；
         */
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
	
	static <E> Node<E> predecessor(Node<E> p) {
		//TODO predecessor
		return p.left;
	}
	
	static <E> Node<E> successor(Node<E> p) {
		//TODO successor
		return p.right;
	}
	
	@SuppressWarnings("unchecked")
	final int compare(Object e1, Object e2) {
		return comparator == null ? ((Comparable<? super E>)e1).compareTo((E)e2)
	            : comparator.compare((E)e1, (E)e2);
	}

}
