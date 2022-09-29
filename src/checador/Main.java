package checador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Checker checker = new Checker();
		Clock clock = new Clock(9,0, checker);
	    clock.start();
		checker.start();
	}

}
