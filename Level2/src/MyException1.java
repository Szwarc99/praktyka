
public class MyException1 extends Exception{
	
	public MyException1() {
		System.out.println("my error");
	}
	
	public MyException1(String text){
		System.out.println(text);
	}

}
