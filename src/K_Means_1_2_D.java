// Program for K-means clustering algorithm for 1-D / 2-D datasets non dynamic

import java.util.*;

class K_Means_1_2_D {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();

		System.out.print("\n Enter dimension of dataset (1-D / 2-D) : ");
		int choice = sc.nextInt();

		System.out.print("\n Enter no. of elements in dataset : ");
		int n = sc.nextInt();

		System.out.print("\n Enter the dataset (" + choice + "-D matrix) : ");

		if (choice == 1) {
			int dataset[] = new int[n];
			for (int i = 0; i < n; i++) {
				dataset[i] = sc.nextInt();
			}
			int x, y;
			do {
				x = dataset[ran.nextInt(n)];
				y = dataset[ran.nextInt(n)];
				System.out.println(" Initial random means: " + x + " " + y);

			} while (x == y);

			int x1 = -1, y1 = -1, index = 0;
			List<Integer> set1, set2;
			while ((x1 != x) || (y1 != y)) {
				set1 = new ArrayList<Integer>();
				set2 = new ArrayList<Integer>();
				int sum1 = 0, sum2 = 0;
				index++;
				for (int i = 0; i < n; i++) {
					if (Math.abs(dataset[i] - x) <= Math.abs(dataset[i] - y)) {
						set1.add(dataset[i]);
						sum1 += dataset[i];
					} else {
						set2.add(dataset[i]);
						sum2 += dataset[i];
					}
				}
				if ((x1 != x) || (y1 != y)) {
					x1 = x;
					y1 = y;
					x = sum1 / set1.size();
					y = sum2 / set2.size();
					System.out.print("\n Datasets at iteration " + (index) + " : \n Set 1: ");
					for (int j : set1)
						System.out.print(j + " ");
					System.out.print("\n Set 2: ");
					for (int j : set2)
						System.out.print(j + " ");
					System.out.println("\n Mean 1: " + x);
					System.out.println(" Mean 2: " + y);
				} else {
					System.out.print("\n K-Means Clustered Datasets : \n Set 1: ");
					for (int j : set1)
						System.out.print(j + " ");
					System.out.print("\n Set 2: ");
					for (int j : set2)
						System.out.print(j + " ");
				}
			}

		} else if (choice == 2) {
			System.out.print("\n Enter no. of attributes per element : ");
			int m = sc.nextInt();

			int dataset[][] = new int[n][m];
			for (int i = 0; i < n; i++) {
				System.out.print("\n Enter attribute values for element " + (i + 1) + " : ");
				for (int j = 0; j < m; j++) {
					dataset[i][j] = sc.nextInt();
				}
			}

			int x[] = new int[m], y[] = new int[m];
			do {

				int f = ran.nextInt(n);
				System.out.print("\n Initial random mean elements:- ");
				for (int j = 0; j < m; j++) {
					x[j] = dataset[f][j];
					System.out.print(x[j] + " ");
				}
				f = ran.nextInt(n);
				System.out.print("| ");
				for (int j = 0; j < m; j++) {
					y[j] = dataset[f][j];
					System.out.print(y[j] + " ");
				}
			} while (Arrays.equals(x, y));

			int x1[] = new int[m], y1[] = new int[m];
			Arrays.fill(x1, 0);
			Arrays.fill(y1, 0);
			int index = 0, flag = 0;
			List<int[]> set1, set2;

			while (!(Arrays.equals(x, x1)) || !(Arrays.equals(y, y1))) {
				set1 = new ArrayList<int[]>();
				set2 = new ArrayList<int[]>();

				int sum1[] = new int[m], sum2[] = new int[m];
				Arrays.fill(sum1, 0);
				Arrays.fill(sum2, 0);
				index++;
				for (int i = 0; i < n; i++) {
					int ecdist1 = 0, ecdist2 = 0;
					for (int j = 0; j < m; j++) {
						ecdist1 += Math.pow(dataset[i][j] - x[j], 2);
						ecdist2 += Math.pow(dataset[i][j] - y[j], 2);
					}

					if (Math.pow(ecdist1, 0.5) <= Math.pow(ecdist2, 0.5)) {
						set1.add(dataset[i]);
						for (int j = 0; j < m; j++) {
							sum1[j] += dataset[i][j];
						}

					} else {
						set2.add(dataset[i]);
						for (int j = 0; j < m; j++) {
							sum2[j] += dataset[i][j];
						}

					}

				}
				int s1 = set1.size(), s2 = set2.size();
				if (flag == 0) {

					int counter = 0;
					for (int j = 0; j < m; j++) {
						x1[j] = x[j];
						y1[j] = y[j];
						x[j] = sum1[j] / s1;
						y[j] = sum2[j] / s2;
						if((x[j] == x1[j])&&(y[j] == y1[j]))
							counter++;
					}

					// System.out.print("\n Final 1:");
					// for (int[] k : set1) {
					// for (int l : k)
					// System.out.print(l + " ");
					// System.out.print("| ");
					// }
					// System.out.print("\n Final 2:");
					// for (int[] k : set2) {
					// for (int l : k)
					// System.out.print(l + " ");
					// System.out.print("| ");
					// }
					
					if (counter == m) {
						flag = 1;

						System.out.print("\n\n K-Means Clustered Datasets : \n Set 1: ");
						for (int[] i : set1) {
							for (int j : i)
								System.out.print(j + " ");
							System.out.print("| ");
						}
						System.out.print("\n Set 2: ");
						for (int[] i : set2) {
							for (int j : i)
								System.out.print(j + " ");
							System.out.print("| ");
						}
					} else {
						flag = 0;

						System.out.print("\n\n Iteration " + (index)
								+ " : \n\n Set 1: ");
						for (int[] i : set1) {
							for (int j : i)
								System.out.print(j + " ");
							System.out.print("| ");
						}
						System.out.print("\n Set 2: ");
						for (int[] i : set2) {
							for (int j : i)
								System.out.print(j + " ");
							System.out.print("| ");
						}

						System.out.print("\n Mean element 1: ");
						for (int j = 0; j < m; j++) {
							System.out.print(" " + x[j]);
						}
						System.out.print("\n Mean element 2: ");
						for (int j = 0; j < m; j++) {
							System.out.print(" " + y[j]);

						}
					}
				}
			}

		}
		System.out.println("\n");
		sc.close();
	}
	// System.out.println(dataset);
}

