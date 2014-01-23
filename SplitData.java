public class SplitData {

	private String attributeName;
	private int attributeIndex;

	public SplitData(String attributeName, int attributeIndex) {
		this.attributeName = attributeName;
		this.attributeIndex = attributeIndex;
	}

	public String getName() {
		return this.attributeName;
	}

	public int getIndex() {
		return this.attributeIndex;
	}
}
