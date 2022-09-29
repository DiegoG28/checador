package checador;

import java.util.ArrayList;

public class Clock extends Thread{
	int hours;
	int minutes;
	int seconds;
	ArrayList<String> employeeTime = new ArrayList<String>();
	Checker checker;
	
	public Clock (int h, int m, Checker checker) {
		this.hours = h;
		this.minutes = m;
		this.checker = checker;
		
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		while(true) {
			for (seconds = 0; seconds<60; seconds++) {
				try {
					sleep(1000);
					if(checker.numberTyped && !checker.isExit) {
						System.out.println("\u001B[32m" + "Se ha registrado su entrada");
						System.out.println(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					    System.out.println("" + "\u001B[0m");
						checker.numberTyped = false;
					}
					if(checker.numberTyped && checker.isExit) {
						System.out.println("\u001B[33m" + "Se ha registrado su salida");
						System.out.println(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					    System.out.println("" + "\u001B[0m");
						checker.numberTyped = false;
						checker.isExit = false;
					}
					if(checker.keyPressed) {
						synchronized(checker.employeeCode) {
							Chronometer chron = new Chronometer();
							chron.start();
							checker.employeeCode.wait();
							hours = hours + chron.getHours();
							minutes = minutes + chron.getMinutes();
							seconds = seconds + chron.getSeconds();
							chron.stop();
						}
					}else {
						System.out.println(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					}
					if (seconds>=59) {
						seconds = seconds % 60;
						minutes++;
						if (minutes==60) {
							minutes = 0;
							hours++;
						}
						if (hours == 24) {
							hours = 0;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
