
public class SetPoint {
	public static void main(String[] args)
	{
		Point point = new Point();
		point.setX(10);
		point.setY(20);
		System.out.println("wsp�rz�dne punktu to "+point.getX()+", "+point.getY());
		Point point2 = new Point(point);
		System.out.println("wsp�rz�dne punktu2 to "+point2.getX()+", "+point2.getY());
	}
}
