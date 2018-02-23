

import java.math.BigInteger;
import java.util.Scanner;
import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;


class SGSum {
	
	int MessageDigest(int nodeId, int lengthOfSynopsis) {
		
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
			  String text = Integer.toString(nodeId);
			 md.update(text.getBytes("UTF-8"));
			//byte text = (byte)nodeId;
			// md.update(text);
			// md.digest();
			byte[] digest = md.digest();
		    String temp = digest.toString();
		    //int tempnode = Integer.parseInt(temp);
			//Integer tempnodeid = Integer.valueOf(temp);  
			// -- String temp = digest.toString();
			 Integer test = Integer.decode(temp);
			//String binaryFormat = new BigInteger(temp.getBytes()).toString(2);
		    
			String binaryFormat = String.format("%8s", Integer.toBinaryString(test & 0xFF)).replace(' ', '0');
		    
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
	
	int CT (int nodeId, int lenghtOfSynopsis) {
		int i = 1;
		while((i < lenghtOfSynopsis - 1) && (MessageDigest(nodeId, lenghtOfSynopsis) == 0)) {
			i = i+1;
		}
		return i;
	}
	
	int [] syno (int node,int sensedValue, int synopsisLength) {
		int i = 1;
		int [] localSynopsis = new int[synopsisLength];
			while (i <= sensedValue ) {
				int nodeId = Integer.valueOf(String.valueOf(node) + String.valueOf(i)); 
				int j = CT(nodeId, synopsisLength);
				localSynopsis[j] = 1;
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