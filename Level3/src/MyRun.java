
public class MyRun implements Runnable {
	private int id;
	
	public MyRun(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println("w¹tek: "+id);
			try {
				Thread.sleep(100);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Runnable[] runners = new Runnable[10];
		Thread[] threads = new Thread[10];
	
		for(int i = 0; i<10;i++) {
			runners[i] = new MyRun(i);
		}
		for(int i = 0; i<10;i++) {
			threads[i] = new Thread(runners[i]);
		}
		
		for(int i = 0; i<10;i++) {
			threads[i].start();
		}
}
}
