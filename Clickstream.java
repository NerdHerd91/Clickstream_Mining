import java.util.*;
import java.io.File;

public class Clickstream {
	
	public static final int FEATURES = 274;

	public static void main(String[] args) {
		// Array of String Feature Names.
		//Set<String> featNames = new LinkedHashSet<String>();
		String[] featNames = new String[FEATURES];

		// Set of PageView objects for each feature set.
		// Contains class value and array of feature values.
		Set<PageView> trainFeat = new HashSet<PageView>();
		Set<PageView> testFeat = new HashSet<PageView>();

		// Parse the data for each dataset.
		try {
			Scanner sc = new Scanner(new File("./DataSet/featnames.csv"));
			int index = 0;
			while(sc.hasNextLine()) {
				//featNames.add(sc.nextLine().trim());
				featNames[index] = sc.nextLine().trim();
			}
			sc.close();

			buildDataMaps(trainFeat, "./DataSet/trainfeat.csv", "./DataSet/trainlabs.csv");
			buildDataMaps(testFeat, "./DataSet/testfeat.csv", "./DataSet/testlabs.csv");
		} catch (Exception e) {
			System.out.println(e);
		}

		// Build Decision Tree using training data
		DTreeNode root = learnTree(trainFeat, featNames);

		// Predict class for test data
		predictTree(testFeat, featNames, root);
	}

	/**
	* Parses a file containing features and a file containing the class output data,
	* For each example, creates a PageView object and adds it to the set.
	*
	* @param pageViews Reference to the set to place PageView we create into.
	* @param featurePath File path to the file containing features.
	* @param labPath File path to the file containing output classes.
	*/
	public static void buildDataMaps(Set<PageView> pageViews, String featurePath, String labPath) {
		try {
			Scanner data = new Scanner(new File(featurePath));
			Scanner labs = new Scanner(new File(labPath));

			while(labs.hasNextInt() && data.hasNextLine()) {
				int label = labs.nextInt();
				String dataLine = data.nextLine();
				Scanner sc = new Scanner(dataLine);
				
				int[] features = new int[FEATURES];
				int index = 0;

				while(sc.hasNextInt()) {
					features[index] = sc.nextInt();
					index++;
				}
				
				pageViews.add(new PageView(label, features));
				sc.close();
			}
			data.close();
			labs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Returns a DTreeNode that holds the attribute name, index and a set of branches to children.
	* 
	* @param pageViews Set of PageView Data to train from
	* @param featNames Array of feature names correspoding to attributes
	* @return A DTreeNode containing the attribute split on and branches to any children
	*/
	public static DTreeNode learnTree(Set<PageView> pageViews, String[] featNames, Set<Integer> testAttr) {
		int positive = 0;
		for (PageView pageView : pageViews) {
			if (pageView.getLabel == 1) { positive++; }
		}

		if (positive == pageViews.size()) {
			return new DTreeNode(sd.attributeName, sd.attributeIndex, null);
		} else if (positive == 0) {
			return new DTreeNode(sd.attributeName, sd.attributeIndex, null);
		} else {
			//DTreeNode node = new DTreeNode(sd.attributeName, sd.attirbuteIndex, new Map<Integer, DTreeNode>());
			//for(Integer value : sd.range) {
			//	node.getBranches().put(value, learnTree(pageViews, featNames);
			//}
			int attrIndex = -1;
			int maxGain = 0;
			for (int i = 0; i < FEATURES; i++) {
				if (!testAttr.contains(i)) {
					double gain = informationGain(pageViews, i);
					if (maxGain < gain) {
						maxGain = gain;
						attrIndex = i;
					}
				}
			}

			return node;
		}
	}

	/**
	* Returns a boolean indicating we should no longer continue splitting.
	*
	* @param sd Object containing pertinent data for splitting on.
	* @return Returns a boolean indicating Chi-Square result.
	*/
	public static boolean chiSquare(SplitData sd) {
		return false;
	}

	/**
	* Computes the information gain for a particular attribute to split on.
	*
	* @param pageViews Accepts a set of pageview objects.
	* @param attributeIndex Represents the index of the attribute we wish to split on.
	* @return Returns the information gain for this particular attribute split.
	*/
	public static double informationGain(Set<PageView> pageViews, int attributeIndex) {
		double entropyS = entropy(pageViews);
		double gain = 0;
		Map<Integer, Set<PageView>> values = new Map<Integer, Set<PageView>>();
		
		// Create a map from all possible values to a Set of PageViews containing them.
		for (PageView pageView : pageViews) {
			value = pageView.getFeatures()[attributeIndex];
			if (!values.containsKey(value) {
				values.add(new Set<PageView>);
			}
			values.get(value).add(pageView);
		}

		// Sum the individual entropies * the weighted fraction for that particular subset.
		for (Integer value : values.keySet()) {
			gain += values.get(value).size() / ((double) pageViews.size()) * entropy(values.get(value));
		}
		return gain;		
	}

	/**
	* Calculates the entropy of a given collection
	*
	* @param pageViews Collection to calculate entropy of.
	* @return Returns a double reprenting the entropy value.
	*/
	public static double entropy(Set<PageView> pageViews) {
		int tot = pageViews.size();
		int pos = 0;
		for(PageView p : pageViews) {
			if(p.getLabel > 0) { pos++; }
		}
		double pProp = (-1.0 * pos / tot) * Math.log(1.0 * pos / tot) / Math.log(2);
		double nProp = (1.0 * (tot - pos) / tot) * Math.log(1.0 * (tot - pos) / tot) / Math.log(2);
		return pProp - nProp;
	}

	/**
	* Predicts the class values for a dataset of PageView objects,
	* Also writes to file the predicted value to be compared to actual output for accuracy.
	*
	* @param pageViews Set of PageView Data to train from
	* @param featNames Array of feature names correspoding to attributes
	* @param root DTreeNode root for the decision tree built using the training data
	*/
	public static void predictTree(Set<PageView> pageViews, String[] featNames, DTreeNode root) {
		// TODO implement prediction on test data from tree
		// Save results to file
	}
}
