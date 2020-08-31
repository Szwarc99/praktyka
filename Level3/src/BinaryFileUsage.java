import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class BinaryFileUsage {
	
		
	public static void main(String[] args) {
		try {
			FileInputStream fileIn = new FileInputStream("input");
			FileOutputStream fileOut = new FileOutputStream("output");
			byte[] buffer = null;
			DataInputStream in = new DataInputStream(fileIn);
			DataOutputStream out = new DataOutputStream(fileOut);
			buffer = in.readAllBytes();
			out.write(buffer);
			in.close();
			out.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (IOException e) {
			System.out.println("IO Stream error");
			e.printStackTrace();
		}
		
	}
	
	
}
