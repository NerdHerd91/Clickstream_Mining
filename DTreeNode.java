import java.util.*;

public class DTreeNode {
	private String attribute_name;
	private int attribute_index;
	private Set<DTreeNode> branches;

	public DTreeNode(attribute_name, attribute_index, branches) {
		this.attribute_name = attribute_name;
		this.attribute_index = attribute_index;
		this.branches = branches;
	}

	public String getName() {
		return attribute_name;
	}

	public int getIndex() {
		return attribute_index;
	}

	public Set<DTreeNode> getBranches {
		return branches;
	}
}
