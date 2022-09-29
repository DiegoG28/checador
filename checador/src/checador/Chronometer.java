package checador;

public class Chronometer extends Thread{
	int hours;
	int minutes;
	int seconds;

	public Chronometer() {
		this.hours = 0;
		this.minutes = 0;
	}
	
	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}
	
	public void run() {
		boolean life = true;
		while(life) {
			for (seconds = 0; seconds<60; seconds++) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			}
		}
	}
}
