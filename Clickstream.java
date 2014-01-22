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
	}

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
}
