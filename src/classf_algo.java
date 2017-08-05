import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class classf_algo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String csvFile = "./zoo.data.txt";
		String line = "";
		BufferedReader br;
		
		List<int[]> attr_prob_dist = new ArrayList<int[]>();
		
		try {
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				
				String[] animal_attr = line.split(",");
				for (int i = 0; i < animal_attr.length ; i++) {
					if( i == 13 || i == 17 )
						attr_prob_dist.add(new int[9]);
					else
						attr_prob_dist.add(new int[7]);
					Arrays.fill(attr_prob_dist.get(i), 0);
				}
				
				System.out.println("Name: " + animal_attr[0] + " , Class:" + animal_attr[17]);
				

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
 