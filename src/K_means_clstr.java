
// Program for K-means clustering algorithm for 1-D / 2-D datasets

import java.util.*;

class K_means_clstr {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();

		System.out.print("\n Enter dimension of dataset (1-D / 2-D) : ");
		int choice = sc.nextInt();

		System.out.print("\n Enter no. of elements in dataset : ");
		int n = sc.nextInt();

		System.out.print("\n Enter no. of clusters required : ");
		int c = sc.nextInt();

		System.out.print("\n Enter the dataset (" + choice + "-D matrix) : ");

		if (choice == 1) {

			List<Integer> dataset = new ArrayList<Integer>();
			List<List<Integer>> clusters = new ArrayList<List<Integer>>();

			List<Integer> means = new ArrayList<Integer>();
			List<Integer> means_ng = new ArrayList<Integer>();

			for (int i = 0; i < n; i++) {
				dataset.add(sc.nextInt());
			}

			System.out.print(" Initial random means: ");
			for (int i = 0; i < c; i++) {
				int x;
				do {
					x = dataset.get(ran.nextInt(n));
				} while (means.contains(x));
				means.add(x);
				means_ng.add(0);
				System.out.print(x + " ");
			}

			int index = 0;

			while (!means_ng.equals(means)) {
				clusters.clear();
				for (int i = 0; i < c; i++) {
					clusters.add(new ArrayList<Integer>());
				}
				int sums[] = new int[c];
				Arrays.fill(sums, 0);
				index++;
				for (int i = 0; i < n; i++) {
					int clstr_no = 0, x = dataset.get(i);
					int min = Math.abs(x - means.get(0));

					List<Integer> cluster_buffer = new ArrayList<Integer>();
					for (int j = 0; j < c; j++) {
						int y = means.get(j);
						if (Math.abs(x - y) <= min) {
							min = Math.abs(x - y);
							clstr_no = j;
						}
					}
					cluster_buffer = clusters.get(clstr_no);
					cluster_buffer.add(x);
					clusters.set(clstr_no, cluster_buffer);
					sums[clstr_no] += x;
				}
				for (int i = 0; i < c; i++) {
					means_ng.set(i, means.get(i));
					means.set(i, sums[i] / clusters.get(i).size());
				}
				System.out.println("\n Iteration " + (index) + " : ");
				System.out.print("\n Means : ");
				for (int i = 0; i < c; i++) {
					System.out.print(means.get(i) + " | ");
				}

				if (!means_ng.equals(means)) {

					System.out.print("\n Clusters : ");
					for (int i = 0; i < c; i++) {
						for (int j : clusters.get(i))
							System.out.print(j + " ");
						System.out.print("| ");

					}

				} else {
					System.out.print("\n K means clustered datasets : ");
					for (int i = 0; i < c; i++) {
						for (int j : clusters.get(i))
							System.out.print(j + " ");
						System.out.print("| ");
					}
				}
			}
		}

		else if (choice == 2) {
			System.out.print("\n Enter no. of attributes per element : ");
			int m = sc.nextInt();

			List<List<Integer>> dataset = new ArrayList<List<Integer>>();
			List<List<List<Integer>>> clusters = new ArrayList<List<List<Integer>>>();

			List<List<Integer>> means = new ArrayList<List<Integer>>();
			List<List<Integer>> means_ng = new ArrayList<List<Integer>>();

			for (int i = 0; i < n; i++) {
				System.out.print("\n Enter attribute values for element " + (i + 1) + " : ");
				dataset.add(new ArrayList<Integer>());
				for (int j = 0; j < m; j++) {
					dataset.get(i).add(sc.nextInt());
				}
			}

			System.out.print("\n Initial random means elements :- ");
			for (int i = 0; i < c; i++) {
				List<Integer> x, x1 = new ArrayList<>(), y = new ArrayList<>();
				do {
					x = dataset.get(ran.nextInt(n));
					x1.clear();
					x1.addAll(x);
				} while (means.contains(x));
				means.add(x1);
				for (int j = 0; j < m; j++) {
					System.out.print(x.get(j) + " ");
					y.add(0);
				}
				means_ng.add(y);
				System.out.print("| ");
			}

			int index = 0;

			while (!means.containsAll(means_ng)) {
				clusters.clear();
				
				for (int i = 0; i < c; i++) {
					clusters.add(new ArrayList<List<Integer>>());
				}

				int sums[][] = new int[c][m];
				for (int j[] : sums)
					Arrays.fill(j, 0);
				index++;
				for (int i = 0; i < n; i++) {
					double min = 0;
					int clustr_no = -1;
					List<Integer> x = dataset.get(i);
					for (int j = 0; j < c; j++) {
						List<Integer> y = means.get(j);
						int edbuff = 0;
						for (int k = 0; k < m; k++) {
							edbuff += Math.pow(x.get(k) - y.get(k), 2);
						}
						double y1 = Math.pow(edbuff, 0.5);
						if (j == 0 || min > y1) {
							min = y1;
							clustr_no = j;
						}
					}
					List<List<Integer>> clstr_buff = clusters.get(clustr_no);
					clstr_buff.add(x);
					clusters.set(clustr_no, clstr_buff);
					for (int j = 0; j < m; j++) {
						sums[clustr_no][j] += x.get(j);
					}

				}
				System.out.print("\n\n Iteration " + (index) + " :- ");
							
				for (int i = 0; i < c; i++) {
					List<Integer> mean_buff = means.get(i);
					List<Integer> means_buff_ng = means_ng.get(i);

					for (int j = 0; j < m; j++) {
						means_buff_ng.set(j, mean_buff.get(j));
						mean_buff.set(j, sums[i][j] / clusters.get(i).size());
					}
					means.set(i, mean_buff);
				}

				if (!means_ng.equals(means)) {

					System.out.print("\n Clusters : ");
					for (int i = 0; i < c; i++) {
						List<List<Integer>> clstr_buff = clusters.get(i);
						for (List<Integer> j : clstr_buff) {
							for (int k : j) {
								System.out.print(k + " ");
							}
							System.out.print(", ");
						}
						System.out.print("|| ");
					}

				}

				System.out.print("\n Calculated Means : ");
				for (int i = 0; i < c; i++) {
					List<Integer> mean_buff = means.get(i);
					for (int j : mean_buff) {
						System.out.print(j + " ");
					}
					System.out.print("| ");
				}

			}

			System.out.print("\n\n\n K - Means clustered datasets : ");
			for (int i = 0; i < c; i++) {
				List<List<Integer>> clstr_buff = clusters.get(i);
				for (List<Integer> j : clstr_buff) {
					for (int k : j) {
						System.out.print(k + " ");
					}
					System.out.print(", ");
				}
				System.out.print("|| ");
			}
		}

		System.out.println("\n");
		sc.close();
	}
}
