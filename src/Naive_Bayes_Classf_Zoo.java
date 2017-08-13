
// Naive Bayes based Zoo Animal Classifier

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Naive_Bayes_Classf_Zoo {

	public static void main(String[] args) {

		// Define data set file and classes
		String csvFile = "zoo.data.txt";
		String[] animal_classes = { "Mammal", "Bird", "Reptile", "Fish", "Amphibian", "Insect", "Arthropod" };
		String line = "";
		BufferedReader br;

		List<double[]> attr_prob_dist = new ArrayList<double[]>(); // Probability Distribution for every attribute class-wise
		double[][] foot_dist = new double[9][8]; // No. of feet vs class prob dist
		int[] class_count = new int[8]; // Total freq per class
		int[] input_data = new int[17];
		double[] final_classf_prob = new double[8]; // Final classification probability

		Arrays.fill(class_count, 0);
		Arrays.fill(final_classf_prob, 100);
		for (double[] i : foot_dist) {
			Arrays.fill(i, 0);
		}
		int index = 0;

		try {
			// Input test data
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\nNaive Bayes based Zoo Animal Classifier :- ");
			System.out.println(
					"\nEnter values for the following properties that describe the animal.(Boolean Array)");
			System.out.println(
					"[hair, feathers, eggs, milk, airborne, aquatic, predator, toothed, backbone, "
							+ "breathes, venomous, fins, No.of Legs(NUM), tail, domestic, catsize]");
			String[] inp_array = br.readLine().split(",");
			for (int i = 1; i < input_data.length; i++) {
				input_data[i] = Integer.parseInt(inp_array[i - 1]);
			}

			// Read and process training data set from zoo.csv file 
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				String[] animal_attr = line.split(",");
				int anm_class = Integer.parseInt(animal_attr[17]);
				class_count[anm_class]++;

				// for (String string : animal_attr) {
				//	System.out.print(string + " ");
				// }
				// System.out.println();

				// Calculate summation distribution for all attributes
				for (int i = 0; i < animal_attr.length - 1; i++) {

					double[] buffer = new double[8];

					if (index == 0) {
						Arrays.fill(buffer, 0);
						attr_prob_dist.add(buffer);
					}
					if (i != 13 && animal_attr[i].equals("1")) {
						buffer = attr_prob_dist.get(i);
						buffer[anm_class]++;
						attr_prob_dist.set(i, buffer);
						buffer[0]++;
					} else if (i == 13) {
						foot_dist[Integer.parseInt(animal_attr[13])][Integer
								.parseInt(animal_attr[17])] += 1;
					}

				}
				index++;
			}

			// Calculate probability distribution for all boolean attributes
			// System.out.println("\nAttribute Probability Distribution :- \n");
			for (int i = 1; i < attr_prob_dist.size(); i++) {
				double[] dist = attr_prob_dist.get(i);
				for (int j = 1; j < dist.length; j++) {
					// System.out.print(dist[j] + " ");
					dist[j] /= class_count[j];
					// System.out.print(dist[j] + " ");
				}
				// System.out.println();
			}
			
			// for (int i : class_count) {
			// System.out.print(i + " ");
			//}
			
			// Calculate probability distribution for no. of feet attribute
			// System.out.println("\n\nFeet Count Class Distribution :- \n");
			for (int i = 1; i < foot_dist.length; i++) {
				double[] dist = foot_dist[i];
				for (int j = 1; j < dist.length; j++) {
					// System.out.print(dist[j] + " ");
					dist[j] /= class_count[j];
					// System.out.print(dist[j] + " ");
				}
				// System.out.println();
			}
			System.out.println();

			// Calculate probability for input data using Bayes' algorithm
			for (int i = 1; i < final_classf_prob.length; i++) {
				for (int j = 1; j < input_data.length; j++) {
					int x = input_data[j];
					// System.out.print(final_classf_prob[i]);
					if (j == 13) {
						final_classf_prob[i] *= foot_dist[x][i];
					} else {
						// System.out.println(" * " + attr_prob_dist.get(j)[i] + " * " + x);
						if (x == 1) {
							final_classf_prob[i] *= attr_prob_dist.get(j)[i];
						} else {
							final_classf_prob[i] *= (double) (1 - attr_prob_dist.get(j)[i]);
						}
					}
				}
				// System.out.println();
				final_classf_prob[i] *= ((double) class_count[i] / (double) index);
			}

			// Find best fit for given data using classification probability
			System.out.print("Classification Probablitity Distribution : ");
			int max_index = 0;
			double max = 0;
			for (int i = 1; i < final_classf_prob.length; i++) {
				double x = final_classf_prob[i];
				System.out.print(x + " ");
				if (x > max) {
					max = x;
					max_index = i;
				}
			}
			System.out.println("\n\nAnimal Class : " + max_index + " > " + animal_classes[max_index - 1]);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
