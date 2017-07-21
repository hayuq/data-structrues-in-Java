package com.june.test;

import com.june.tree.BinarySearchTree;

public class Test {
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		Integer[] arr = {1, 2, 5, 3, 4, 0, 2, 7};
		tree = tree.build(arr, arr.length);
		System.out.println("先序遍历：");
		tree.preOrder();
		System.out.println("\n中序遍历：");
		tree.inOrder();
		System.out.println("\n后序遍历：");
		tree.postOrder();
		System.out.println("\n层序遍历：");
		tree.levelOrder();
		
//		tree.clear();
		System.out.println("\n查找节点值为2的节点：" + tree.search(2));
		System.out.println("树的深度：" + tree.depth());
		System.out.println("树的节点数：" + tree.size());
	}

}
