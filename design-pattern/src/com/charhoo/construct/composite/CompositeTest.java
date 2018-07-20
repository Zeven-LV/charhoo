package com.charhoo.construct.composite;

import java.util.Enumeration;

public class CompositeTest {

	public static void main(String[] args) {
		Tree tree = new Tree("A");
		TreeNode nodeB = new TreeNode("B");
		TreeNode nodeC1 = new TreeNode("C1");
		TreeNode nodeC2 = new TreeNode("C2");

		nodeB.add(nodeC1);
		nodeB.add(nodeC2);
		tree.root.add(nodeB);
		System.out.println("build the tree finished!");

		System.out.println(tree.root.getName());
		System.out.println(tree.root.getParent());
		Enumeration<TreeNode> e = tree.root.getChildren();
		StringBuilder sb = new StringBuilder();
		getNode(e,sb);
	}

	public static void getNode(Enumeration<TreeNode> e,StringBuilder sb) {
		while (e.hasMoreElements()) {
			TreeNode tn = e.nextElement();
			sb.append("-");
			String str = sb.toString();
			System.out.println(str+tn.getName());
			System.out.println(str+tn.getName() +"'s parent is "+ tn.getParent().getName());
				
			Enumeration<TreeNode> ne = tn.getChildren();
			if (ne != null) {
				getNode(ne,sb);
			}
		}
	}

}
