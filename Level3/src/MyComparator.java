import java.util.Comparator;

public class MyComparator implements Comparator<Kolor>{
	public int compare(Kolor first, Kolor second)	{
		if(first.rare != second.rare) {
			if(first.rare) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			return first.toString().compareTo(second.toString());
		}
	}
}
