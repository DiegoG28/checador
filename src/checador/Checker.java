package checador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Checker extends Thread {
	ArrayList<String> employeeCode = new ArrayList<String>();
	boolean keyPressed = false;
	boolean numberTyped = false;
	boolean isExit = false;

	public void run() {
		String num;
		Scanner scanner = new Scanner(System.in);
		String readString = scanner.nextLine();
		boolean life = true;
		while (life) {
			if (readString.equals("")) {
				keyPressed = true;
				System.out.println("\u001B[34m" + "Ingresa tu número de empleado" + "\u001B[0m");
				num = scanner.nextLine();
				for (int i = 0; i < employeeCode.size(); i++) {
					if (employeeCode.get(i).compareTo(num) == 0) {
						isExit = true;
						employeeCode.remove(i);
					}
				}
				if (!isExit) {
					employeeCode.add(num);
				}
				numberTyped = true;
				keyPressed = false;
				synchronized(employeeCode) {
					employeeCode.notify();
				}
			}
			if (scanner.hasNextLine())
				readString = scanner.nextLine();
			else
				readString = null;
		}
	}
}
