import java.math.BigInteger;
import java.util.Scanner;
import java.security.MessageDigest;

class SGSum {
	
	int MessageDigest(int nodeId, int lengthOfSynopsis) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			//String text = Integer.toString(nodeId);
			//md.update(text.getBytes("UTF-8"));
			
			String binaryFormat = String.format("%8s", Integer.toBinaryString(nodeId & 0xFF)).replace(' ', '0');
			
			int stringLength = binaryFormat.length();
			char lastBit = binaryFormat.charAt(stringLength-1);	
			int finalBit = Character.getNumericValue(lastBit);
			return finalBit;
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	private Object toBinaryString(String text) {
		return null;
	}

	int CT (int nodeId, int lengthOfSynopsis) {
		int i = 1;
		while((i < lengthOfSynopsis - 1) && (MessageDigest(nodeId, lengthOfSynopsis) == 0)) {
			i = i+1;
		}
		return i;
	}
	
	int [] syno (int node,int sensedValue, int lengthOfSynopsis) {
		int i = 1;
		int [] localSynopsis = new int[lengthOfSynopsis];
			while (i <= sensedValue ) {
				int nodeId = Integer.valueOf(String.valueOf(node) + String.valueOf(i)); 
				int j = CT(nodeId, lengthOfSynopsis);
				localSynopsis[j] = 1;
				i++;
			}
			for(int g =0; g < lengthOfSynopsis; g++) {
				System.out.print(" " +localSynopsis[g]);
			}	
		System.out.println();
		return localSynopsis;
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of nodes");
		int node = sc.nextInt();
		//System.out.println("Enter Sensed value");
		//int sensedValue = sc.nextInt();
		System.out.println("Enter Synopsis length");
		int lengthOfSynopsis = sc.nextInt();
		
		SGSum synopsis = new SGSum();
		int [] finalSynopsis = new int [lengthOfSynopsis];
		int i = 1;
		while (i <= node) {
			System.out.println("Enter Sensed value");
			int sensedValue = sc.nextInt();
			int [] nodeId = new int[lengthOfSynopsis];
			nodeId = synopsis.syno(i, sensedValue, lengthOfSynopsis);
			i++;
			for(int l = 0; l < finalSynopsis.length; l++) {
				if(nodeId[l] != 0) {
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