
public class MyException2 extends Exception{

	public MyException2() {
		System.out.println("the other error");
	}
	
	public MyException2(String text){
		System.out.println(text+"!!!");
	}
}
