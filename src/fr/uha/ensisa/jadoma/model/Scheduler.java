package fr.uha.ensisa.jadoma.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Date;
import javax.swing.Timer;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;

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
	public void activate() {
		if (this.timer != null)
		{
			this.timer.stop();
			this.timer = null;
		}
		
		int timeBeforeTick = 0;
		Date d = new Date();
		final boolean goingToStop;
		
		int tmp0, tmp1, tmp2;
		
		if (canStartDownloading()) {
			tmp0 = (startDate.getHours() - d.getHours()) % 24;
			tmp1 = (startDate.getMinutes() - d.getMinutes()) % 60;
			tmp2 = (startDate.getSeconds() - d.getSeconds()) % 60;
			goingToStop = true;
		} else {
			tmp0 = (endDate.getHours() - d.getHours()) % 24;
			tmp1 = (endDate.getMinutes() - d.getMinutes()) % 60;
			tmp2 = (endDate.getSeconds() - d.getSeconds()) % 60;
			goingToStop = false;
		}
		timeBeforeTick += ((tmp0 >= 0 ? tmp0 : tmp0 + 24)) * 60 * 60 * 1000;
		timeBeforeTick += ((tmp1 >= 0 ? tmp1 : tmp1 + 60)) * 60 * 1000;
		timeBeforeTick += ((tmp2 >= 0 ? tmp2 : tmp2 + 60)) * 1000;
		
		this.timer = new Timer(Integer.MAX_VALUE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (goingToStop)
					doStopDownloading();
				else
					doStartDownloading();
			}
		});
		this.timer.setDelay(timeBeforeTick);
		this.timer.start();
	}
	
	private void doStopDownloading() {
		ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().stopDownloading();
		
		this.timer.stop();
		this.timer = null;
		this.activate();
	}
	
	private void doStartDownloading() {
		try {
			ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().startDownloading();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		this.timer.stop();
		this.timer = null;
		this.activate();
	}

	@SuppressWarnings("deprecation")
	public boolean canStartDownloading() {
		Date d = new Date();
		return !(d.getHours() >= startDate.getHours() && d.getHours() <= endDate.getHours() &&
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