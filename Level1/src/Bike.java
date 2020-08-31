
public class Bike implements Pojazd {
	
	int speed = 0;
	boolean isMoving = false;

	@Override
	public void speed(int s) {
		this.speed = s;
		
	}

	@Override
	public void start() {
		this.isMoving = true;
		this.speed = 3;
		
	}

	@Override
	public void stop() {
		this.isMoving = false;
		this.speed = 0;			
	}
}
