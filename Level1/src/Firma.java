
public class Firma {
	public static void main(String[] args) {		
		Pracownik prac = new Pracownik("Piotr","Szwarc",2000);
		prac.display();
		
		Szef szef = new Szef();
		szef.display();		
	}
}
