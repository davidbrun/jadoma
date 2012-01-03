package fr.uha.ensisa.jadoma.model;

public class CompletedDownloadPart extends DownloadPart {

	public CompletedDownloadPart(int beginByte, int endByte) {
		super(beginByte, endByte);
	}

	@Override
	public double getPercentCompleted() {
		return 100.0;
	}

	@Override
	public int getNbrOfCompletedBytes() {
		return super.getDownloadPartSize();
	}
}