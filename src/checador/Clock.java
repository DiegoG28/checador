package checador;

import java.util.ArrayList;

public class Clock extends Thread{
	int hours;
	int minutes;
	int seconds;
	Join join;
	ArrayList<String> employeeTime = new ArrayList<String>();
	
	public Clock (int h, int m, Join join) {
		this.hours = h;
		this.minutes = m;
		this.join = join;
	}
	
	public void run() {
		boolean life = true;
		while(life) {
			for (seconds = 0; seconds<60; seconds++) {
				try {
					if(join.numberTyped && !join.exit) {
						System.out.println("\u001B[32m" + "Se ha registrado su entrada");
						System.out.println(hours + ":" + minutes + ":" + seconds);
					    System.out.println("" + "\u001B[0m");
						join.numberTyped = false;
					}
					if(join.numberTyped && join.exit) {
						System.out.println("\u001B[33m" + "Se ha registrado su salida");
						System.out.println(hours + ":" + minutes + ":" + seconds);
					    System.out.println("" + "\u001B[0m");
						join.numberTyped = false;
						join.exit = false;
					}
					if(join.print) {
						System.out.println(hours + ":" + minutes + ":" + seconds);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
