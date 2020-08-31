
public class Vararg {
		
	static int multiply(int... numbs){
		int value = 1;
		for(int i = 0;i<numbs.length;i++) {
			value *= numbs[i];
		}
		return value;
	
	}
	public static void main(String[] args) {
		System.out.println(multiply(1,2,3,4,5,6,7,8));
	}
	
}
