import java.util.*;
import java.io.File;

public class Clickstream {
	
	public static final int FEATURES = 274;

	public static void main(String[] args) {
		// Set of String Feature Names
		Set<String> featNames = new LinkedHashSet<String>();

		// Maps from Class value (0 | 1) to a Set containing Map objects (from Feature Name to attribute value (0 | 1))
		// Seperate maps for training data and test data
		Map<Integer, Set<PageView>> trainFeat = new HashMap<Integer, Set<PageView>>();
		Map<Integer, Set<PageView>> testFeat = new HashMap<Integer, Set<PageView>>();

		try {
			Scanner sc = new Scanner(new File("DataSet/featnames.csv"));
			while(sc.hasNextLine()) {
				featNames.add(sc.nextLine().trim());
			}
			sc.close();

			buildDataMaps(trainFeat, "./DataSet/trainfeat.csv", "./DataSet/trainlabs.csv");
//			buildDataMaps(testFeat, "./DataSet/testfeat.csv", "./DataSet/testlabs.csv");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void buildDataMaps(Map<Integer,Set<PageView>> dataMap, String featurePath, String labPath) {
		try {
			Scanner data = new Scanner(new File(featurePath));
			Scanner labs = new Scanner(new File(labPath));

			while(labs.hasNextInt() && data.hasNextLine()) {
				System.out.println("Reading a user line");
				int label = labs.nextInt();
				String dataLine = data.nextLine();
				Scanner sc = new Scanner(dataLine);
				
				int[] features = new int[FEATURES];
				int index = 0;

				while(sc.hasNextInt()) {
					features[index] = sc.nextInt();
					index++;
				}
				
				if(!dataMap.containsKey(label)) {
					dataMap.put(label, new HashSet<PageView>());
				}
				PageView pv = new PageView(label, features);
				dataMap.get(label).add(pv);
				sc.close();
				System.out.println("Finished a user example");
			}
			data.close();
			labs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
