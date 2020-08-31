
public class Rekurencja {
			
	public int sum(int x) {
		if(x > 0) {
			return x + sum(x-1);
		}
		else {
			return 0;
		}				
	}
	
	public int fib(int x) {
		if(x>2)
			return fib(x-1)+fib(x-2);
		if(x==0)
			return 0;		
		else
			return 1;
	}
	
	public static void main(String[] args) {
		Rekurencja r = new Rekurencja();
		System.out.println(r.sum(3));
		System.out.println(r.fib(20));
	}
}
