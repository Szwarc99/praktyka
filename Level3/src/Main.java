import java.util.Arrays;
import java.util.Scanner;
public class Main {
	
	void eyeColor()
	{
		Scanner s = new Scanner(System.in);
		String k;
		System.out.println("Jaki masz kolor oczu?");
		k = s.nextLine();
		if(k.equals(Kolor.BRAZOWY.name())){
			System.out.println("Masz popularny kolor oczu");			
		}
		else{
			System.out.println("Masz rzadki kolor oczu");
		}
	}
	
	
	public static void main(String[] args) {			
		Main m = new Main();
		MyComparator mc = new MyComparator();
		m.eyeColor();
		for(Kolor k:Kolor.values()){
			System.out.println(k.toString());
		}
		
		Kolor[] colors = Kolor.values();
		System.out.println("Przed sortowaniem:");
		for(Kolor c:colors) {
			System.out.println(c.toString());
		}
		
		Arrays.sort(colors, mc);
		
		System.out.println("Po sortowaniu:");
		for(Kolor c:colors) {
			System.out.println(c.toString());
		}		
	}

}
