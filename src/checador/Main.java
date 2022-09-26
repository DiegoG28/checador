package checador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Join joinsito = new Join();
		Clock relojsito = new Clock(23,58, joinsito);
		Employee trabajadorsito = new Employee(joinsito);
		relojsito.start();
		trabajadorsito.start();
	}

}
