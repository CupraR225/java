import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class FileRanking {
	
	public String[][] arraySort(String[][] stringArr) throws FileNotFoundException {

		
		int arr[] = new int[stringArr.length];
		
		for(int k=0; k<stringArr.length; k++) {
			if(stringArr[k][0]==null) {
				//ignore
			}
			else {
				arr[k] = Integer.parseInt(stringArr[k][0]);
			}
		}

		int n = arr.length; 
        	for (int i = 0; i < n-1; i++) {
            		for (int j = 0; j < n-i-1; j++) {
                		if (arr[j] < arr[j+1]) { 
                    			int temp = arr[j]; 
                    			arr[j] = arr[j+1]; 
                    			arr[j+1] = temp;
                    			String temp2 = stringArr[j][1];
                    			stringArr[j][1] = stringArr[j+1][1];
                    			stringArr[j+1][1] = temp2;
                		}
            		}
        	}
		
		for(int j=0; j<arr.length; j++) {
			stringArr[j][0] = Integer.toString(arr[j]);
		}

		
		return stringArr;
	}
	
	public String[][] createArray() throws FileNotFoundException {
		File file = new File("ranking.txt");
		Scanner reader = new Scanner(file);

		String array[][] = new String[10][2];
		
		int i=0;
		String line;
		int index=0;
		while(reader.hasNextLine()) {
			line = reader.nextLine();
			if(line.contains(";")) index = line.indexOf(";");
			else System.exit(0);
			array[i][0] =  line.substring(0, index);
			array[i][1] = line.substring(index+1);
			i++;
		}
		return arraySort(array);
	}
	
	public void freshArray(int newscore, String name) throws FileNotFoundException {
		File file = new File("ranking.txt");
		
		String[][] a = new String[11][2];
		
		String[][] b = createArray();
		
		for(int i=0; i<10; i++) {
			a[i][0] = b[i][0];
			a[i][1] = b[i][1];
		}
		a[10][0] = Integer.toString(newscore);
		a[10][1] = name;
		
		String c[][] = new String[11][2];
		c = arraySort(a);
		
		PrintWriter newline = new PrintWriter("ranking.txt");
		for(int i=0; i<10; i++) {
			newline.println(c[i][0]+";"+c[i][1]);
		}
		newline.close();

	}
	
}