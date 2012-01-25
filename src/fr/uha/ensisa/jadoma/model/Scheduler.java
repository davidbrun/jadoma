package fr.uha.ensisa.jadoma.model;

import java.io.Serializable;
import java.util.Date;
import javax.swing.Timer;

public class Scheduler implements Serializable {
	
	// Constants
	private static final long serialVersionUID = 9119231587032116122L;
	
	// Fields
	private Date startDate;
	private Date endDate;
	private Timer timer;
	
	@SuppressWarnings("deprecation")
	public Scheduler() {
		this.startDate = new Date();
		this.startDate.setHours(0);
		this.startDate.setMinutes(0);
		this.startDate.setSeconds(0);
		this.endDate = this.startDate;
	}

	@SuppressWarnings("deprecation")
	public boolean canStartDownloading() {
		Date d = new Date();
		return (d.getHours() >= startDate.getHours() && d.getHours() <= endDate.getHours() &&
				d.getMinutes() >= startDate.getMinutes() && d.getMinutes() <= endDate.getMinutes() &&
				d.getSeconds() > startDate.getSeconds() && d.getSeconds() < endDate.getSeconds());
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return String.valueOf(this.canStartDownloading());
	}
}