package com.june.test;

import com.june.tree.BinarySearchTree;

public class Test {
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		Integer[] arr = {1, 12, 5, 3, 24, 6, 21, 7};
		tree = tree.build(arr);
		System.out.println("先序遍历：");
		tree.preOrder();
		System.out.println("\n中序遍历：");
		tree.inOrder();
		System.out.println("\n后序遍历：");
		tree.postOrder();
		System.out.println("\n层序遍历：");
		tree.levelOrder();
		Integer data = 5;
		System.out.println("\n节点值为5的结点：\n" + tree.search(data));
		tree.remove(data);
		System.out.println("将其删除后中序遍历：");
		tree.inOrder();
		System.out.println("\n树中最小值所在的结点：" + tree.min());
		System.out.println("树中最大值所在的结点：" + tree.max());
		System.out.println("树的深度：" + tree.depth());
		System.out.println("树的总结点数：" + tree.size());
		System.out.println("树的叶子结点数：" + tree.leafCount());
	}

}
