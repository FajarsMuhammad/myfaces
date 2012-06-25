package com.application.beans;

import java.io.Serializable;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.application.model.Document;

public class TreeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8570149161349805862L;

	private TreeNode root;
	private String includePage = "";

	@SuppressWarnings("unused")
	public TreeBean() {
		root = new DefaultTreeNode("root", null);

		TreeNode parent = new DefaultTreeNode(new Document("Master", "-",
				"Folder"), root);

		// Documents
		TreeNode customerList = new DefaultTreeNode("page", new Document(
				"Customer", "/pages/master/customerList.xhtml",
				"Word Document"), parent);
		
		
		TreeNode parent2 = new DefaultTreeNode(new Document("Transaksi", "-",
				"Folder"), root);
		
		TreeNode puchase= new DefaultTreeNode("page", new Document(
				"Purchase Order", "/pages/master/customerList.xhtml",
				"Word Document"), parent2);

	}

	public TreeNode getRoot() {
		return root;
	}

	public String getIncludePage() {
		return includePage;
	}

	public void setIncludePage(String includePage) {
		this.includePage = includePage;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		String type = event.getTreeNode().getType().toString();
		if (type.equalsIgnoreCase("default")) {
			includePage = "";
		} else if (type.equalsIgnoreCase("page")) {
			Document document = (Document) event.getTreeNode().getData();
			includePage = document.getUrl();
		}
	}

}
