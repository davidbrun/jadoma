package fr.uha.ensisa.jadoma.model;

import java.io.Serializable;

public abstract class DownloadPart implements Serializable {
	
	// Constants
	private static final long serialVersionUID = -4567321286992582627L;
	
	private int beginByte;
	private int endByte;
	
	public DownloadPart(int beginByte, int endByte) {
		this.beginByte = beginByte;
		this.endByte = endByte;
	}

	public int getBeginByte() {
		return beginByte;
	}

	public void setBeginByte(int beginByte) {
		this.beginByte = beginByte;
	}

	public int getEndByte() {
		return endByte;
	}

	public void setEndByte(int endByte) {
		this.endByte = endByte;
	}
	
	public abstract double getPercentCompleted();
	
	public abstract int getNbrOfCompletedBytes();
	
	public abstract void setNbrOfCompletedBytes(int nbrOfCompletedBytes);
	
	public int getDownloadPartSize() {
		return this.endByte - this.beginByte;
	}
}