package fr.uha.ensisa.jadoma.model;

public class ActiveDownloadPart extends DownloadPart {
	
	private int nbrOfCompletedBytes;
	
	public ActiveDownloadPart(int beginByte, int endByte) {
		super(beginByte, endByte);
		this.nbrOfCompletedBytes = 0;
	}

	@Override
	public double getPercentCompleted() {
		return (double) this.nbrOfCompletedBytes / super.getDownloadPartSize();
	}

	@Override
	public int getNbrOfCompletedBytes() {
		return this.nbrOfCompletedBytes;
	}

	@Override
	public void setNbrOfCompletedBytes(int nbrOfCompletedBytes) {
		this.nbrOfCompletedBytes = nbrOfCompletedBytes;
	}
}