package fr.uha.ensisa.jadoma.model;

public class EmptyDownloadPart extends DownloadPart {

	public EmptyDownloadPart(int beginByte, int endByte) {
		super(beginByte, endByte);
	}

	@Override
	public double getPercentCompleted() {
		return 0.0;
	}

	@Override
	public int getNbrOfCompletedBytes() {
		return 0;
	}
}