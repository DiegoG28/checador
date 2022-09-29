package checador;

import java.util.ArrayList;

public class Clock extends Thread{
	int hours;
	int minutes;
	int seconds;
	Join join;
	ArrayList<String> employeeTime = new ArrayList<String>();
	Employee employee;
	
	public Clock (int h, int m, Join join, Employee employee) {
		this.hours = h;
		this.minutes = m;
		this.join = join;
		this.employee = employee;
		
	}
	
	public void run() {
		boolean life = true;
		while(life) {
			for (seconds = 0; seconds<60; seconds++) {
				try {
					if(join.numberTyped && !join.isExit) {
						System.out.println("\u001B[32m" + "Se ha registrado su entrada");
						System.out.println(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					    System.out.println("" + "\u001B[0m");
						join.numberTyped = false;
					}
					if(join.numberTyped && join.isExit) {
						System.out.println("\u001B[33m" + "Se ha registrado su salida");
						System.out.println(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					    System.out.println("" + "\u001B[0m");
						join.numberTyped = false;
						join.isExit = false;
					}
					if(join.keyPressed) {
						System.out.println(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					}
					if(!join.keyPressed) {
						synchronized(employee.employeeCode) {
							Chronometer chron = new Chronometer();
							chron.start();
							employee.employeeCode.wait();
							hours = hours + chron.getHours();
							minutes = minutes + chron.getMinutes();
							seconds = seconds + chron.getSeconds();
							chron.stop();
						}
					}
					
					sleep(1000);
					if (seconds==59) {
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
