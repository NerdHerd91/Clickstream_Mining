import java.util.*;

public class DTreeNode {
	private String attributeName;
	private int attributeIndex;
	private int label;
	private Map<Integer, DTreeNode> branches;

	public DTreeNode(String attributeName, int attributeIndex, int label, Map<Integer, DTreeNode> branches) {
		this.attributeName = attributeName;
		this.attributeIndex = attributeIndex;
		this.label = label;
		this.branches = branches;
	}

	public String getName() {
		return attributeName;
	}

	public int getIndex() {
		return attributeIndex;
	}

	public int getLabel() {
		return label;
	}

	public Map<Integer, DTreeNode> getBranches {
		return branches;
	}
}
