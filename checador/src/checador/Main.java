package checador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Join joinsito = new Join();
		Employee trabajadorsito = new Employee(joinsito);
		Clock relojsito = new Clock(9,0, joinsito, trabajadorsito);
		relojsito.start();
		trabajadorsito.start();
	}

}
