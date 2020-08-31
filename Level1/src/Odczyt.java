import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
  
 public class Odczyt {
    public static void main(String[] args) throws FileNotFoundException {
         File file = new File("C:/Users/Piotrek/Desktop/asseco/Tutorials/write.txt");
         Scanner in = new Scanner(file);
         while(in.hasNextLine()) {
         String zdanie = in.nextLine();
         System.out.println(zdanie);
         }
         in.close();
     }
 }
