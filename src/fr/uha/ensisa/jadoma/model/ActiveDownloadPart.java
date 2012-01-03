package fr.uha.ensisa.jadoma.model;

public class ActiveDownloadPart extends DownloadPart {
	
	private int nbrOfCompletedBytes;
	
	public ActiveDownloadPart(int beginByte, int endByte) {
		super(beginByte, endByte);
		this.nbrOfCompletedBytes = 0;
	}

	@Override
	public double getPercentCompleted() {
		return 100.0 * super.getDownloadPartSize() / this.nbrOfCompletedBytes;
	}

	@Override
	public int getNbrOfCompletedBytes() {
		return this.nbrOfCompletedBytes;
	}
}