package fr.uha.ensisa.jadoma.model;

public class Scheduler {
	
	public boolean canStartDownloading() {
		return false;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.canStartDownloading());
	}
}