import java.util.*;
import java.io.File;

public class Clickstream {
	
	public static final int FEATURES = 274;

	public static void main(String[] args) {
		// Set of String Feature Names.
		Set<String> featNames = new LinkedHashSet<String>();
		
		// Set of PageView objects for each feature set.
		// Contains class value and array of feature values.
		Set<PageView> trainFeat = new HashSet<PageView>();
		Set<PageView> testFeat = new HashSet<PageView>();

		// Parse the data for each dataset.
		try {
			Scanner sc = new Scanner(new File("DataSet/featnames.csv"));
			while(sc.hasNextLine()) {
				featNames.add(sc.nextLine().trim());
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
	* @param featNames Set of feature names correspoding to attributes
	* @return A DTreeNode containing the attribute split on and branches to any children
	*/
	public static DTreeNode learnTree(Set<PageView> pageViews, Set<String> featNames) {
		// TODO implement recursive tree building
	}

	/**
	* Predicts the class values for a dataset of PageView objects,
	* Also writes to file the predicted value to be compared to actual output for accuracy.
	*
	* @param pageViews Set of PageView Data to train from
	* @param featNames Set of feature names correspoding to attributes
	* @param root DTreeNode root for the decision tree built using the training data
	*/
	public static void predictTree(Set<PageView> pageViews, Set<String> featNames, DTreeNode root) {
		// TODO implement prediction on test data from tree
		// Save results to file
	}
}
