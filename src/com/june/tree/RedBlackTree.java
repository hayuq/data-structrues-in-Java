package com.june.tree;

/**
 * 红黑树：每个节点都带有颜色属性的二叉查找树，颜色或红色或黑色。
 * 性质1. 节点是红色或黑色；
 * 性质2. 根节点是黑色；
 * 性质3. 每个叶节点（NIL节点，空节点）是黑色的；
 * 性质4. 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)；
 * 性质5. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 */
public class RedBlackTree<E> extends BinarySearchTree<E> {

	private static final long serialVersionUID = -416789421042750707L;
	
	private static final boolean RED   = false;
    private static final boolean BLACK = true;
	
	@Override
	public Node<E> insert(E e) {
		return insert(root, e);
	}

	public RBNode<E> insert(RBNode<E> p, E e) {
		return super.insert(p, e);
	}
	
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return super.remove(o);
	}
	
	static class RBNode<E> extends Node<E> {
		/**
		 * 节点颜色
		 */
		boolean color = BLACK;
	}

}