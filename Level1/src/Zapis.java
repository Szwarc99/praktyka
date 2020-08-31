import java.io.FileNotFoundException;
import java.io.PrintWriter;
 
public class Zapis {

	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("C:/Users/Piotrek/Desktop/asseco/Tutorials/write.txt");
		pw.println("ala wziela leki, kot zniknal");
		pw.close();       
    }
}