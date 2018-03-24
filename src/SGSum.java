import java.math.BigInteger;
import java.util.Scanner;
import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;


class SGSum {
	
	int CoinToss(int nodeintandId, int lengthOfSynopsis) {
		
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
			  	String text = Integer.toString(nodeintandId);
			 	md.update(text.getBytes("UTF-8"));
				byte[] digest = md.digest();
				
		        int leftmostBit = 0;
				if((digest[31] & 0x80) != 0)// when this condition is true, we know that leftmostBit is 1
					leftmostBit = 1;
				System.out.println(" leftmostbit= " + leftmostBit);
				return leftmostBit;
			}
			catch (Exception e) {
				System.out.println(e);
			}
		return 0;
	}
	
	int CT (int nodeintandId, int lenghtOfSynopsis) {
		int i = 1;
		while((i < lenghtOfSynopsis-1) && (CoinToss(nodeintandId, lenghtOfSynopsis) == 0)) {
			i = i+1;
			//System.out.println("i= " +i);
		}
		return i;
		
	}
	
	int [] syno (int node,int sensedValue, int synopsisLength) {
		int i = 1;
		int [] localSynopsis = new int[synopsisLength];
			while (i <= sensedValue ) {
				int nodeintandId = Integer.valueOf(String.valueOf(node) + String.valueOf(i)); 
				int j = CT(nodeintandId, synopsisLength);
				localSynopsis[j] = 1;
                System.out.println("node = " + node + " , nodeId = " + nodeintandId + " , j = " + j);
				i++;
			}
			for(int g =0; g < synopsisLength; g++) {
				System.out.print(" " +localSynopsis[g]);
			}	
		System.out.println();
		return localSynopsis;
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of nodes");
		int node = sc.nextInt();
		//System.out.println("Enter Sensed value");
		//int sensedValue = sc.nextInt();
		System.out.println("Enter Synopsis length");
		int synoposisLenght = sc.nextInt();
		
		SGSum synopsis = new SGSum();
		int [] finalSynopsis = new int [synoposisLenght];
		int i = 1;
		while (i <= node) {
			System.out.println("Enter Sensed value");
			int sensedValue = sc.nextInt();
			int [] temp = new int[synoposisLenght];
			temp = synopsis.syno(i, sensedValue, synoposisLenght);
			i++;
			for(int l = 0; l < finalSynopsis.length; l++) {
				if(temp[l] != 0) {
					finalSynopsis[l] = 1;
				}
			}
		}
		System.out.print("Base Synopsis value: ");
		for (int m =0; m < finalSynopsis.length; m++) {
			System.out.print(finalSynopsis[m]);	
		}
	}
}
