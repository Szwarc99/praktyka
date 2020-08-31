
public class Pracownik {
	String name;
	String surname;
	int pay;
	
	public Pracownik() {
		name = "";
		surname = "";
		pay = 0;
	}
	
	public Pracownik(String n, String s, int p)	{
		name = n;
		surname = s;
		pay = p;
	}
	
	public void display()	{
		System.out.println(name +' '+surname+": "+pay);
	}
	
}
