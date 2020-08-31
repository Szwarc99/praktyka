
public class Car implements Pojazd{

	int speed = 0;
	boolean isMoving = false;
	
	@Override
	public void speed(int s) {
		this.speed = s;
		System.out.println("current speed: " + speed);		
	}

	@Override
	public void start() {
		this.isMoving = true;
		this.speed = 10;
		System.out.println(isMoving);
		System.out.println("current speed: " + speed);		
	}

	@Override
	public void stop() {
		this.isMoving = false;
		this.speed = 0;
		System.out.println(isMoving);
	}

}
