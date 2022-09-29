package checador;

import java.io.IOException;
import java.util.ArrayList;

public class Clock extends Thread{
	int hours;
	int minutes;
	int seconds;
	String checkTitle;
	String checkTime;
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
						checkTitle = "\u001B[32m" + "Se ha registrado su entrada";
						checkTime = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
						checker.numberTyped = false;
					}
					if(checker.numberTyped && checker.isExit) {
						checkTitle = "\u001B[33m" + "Se ha registrado su salida";
						checkTime = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
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
				        try {
							new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        if(checkTitle != null) {
				        	System.out.println(checkTitle);
				            System.out.println(checkTime);
				        	System.out.println("" + "\u001B[0m");
				        }
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
