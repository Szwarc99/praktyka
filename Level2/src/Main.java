import java.util.Scanner;

public class Main {

	boolean giveEvenNum () throws MyException1, MyException2 {
		Scanner scan = new Scanner(System.in);
		System.out.println("podaj parzyst¹ liczbê");
		int x = scan.nextInt();
		
		if(x==2)
			throw new MyException1("wow amazing, you're really testing my abilities in dividing by 2");
		if(x%2==0) {
			System.out.println("good job");
			return true;
		}
		else {
			throw new MyException2("come on it's basic maths");
		}
			
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		boolean correct = false;
		while(true) {
		try {
			correct = m.giveEvenNum();
		}
		catch(MyException1 e1){correct = true;}
		catch(MyException2 e2){correct = false;}
		if(correct == true)
		{
			System.out.println("done");
			break;
		}
		}
		
	}

}
