
public class Point {
	int x;
	int y;
	
	Point(int a, int b)
	{
		x = a;
		y = b;
	}
	
	
	public Point() {
		x = 0;
		y = 0;
	
	}


	public Point(Point point) {
		x = point.getX();
		y = point.getY();
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
