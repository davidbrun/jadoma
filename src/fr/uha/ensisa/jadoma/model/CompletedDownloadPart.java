package fr.uha.ensisa.jadoma.model;

public class CompletedDownloadPart extends DownloadPart {

	// Constants
	private static final long serialVersionUID = -3452658901547583558L;

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

	@Override
	public void setNbrOfCompletedBytes(int nbrOfCompletedBytes) { }
}