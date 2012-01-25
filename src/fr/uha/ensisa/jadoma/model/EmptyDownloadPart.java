package fr.uha.ensisa.jadoma.model;

public class EmptyDownloadPart extends DownloadPart {

	// Constants
	private static final long serialVersionUID = -5812399969817829560L;

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

	@Override
	public void setNbrOfCompletedBytes(int nbrOfCompletedBytes) { }
}