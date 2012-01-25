package fr.uha.ensisa.jadoma.model;

import java.io.Serializable;

public enum DownloadState implements Serializable {
	DOWNLOADING, PAUSED, CANCELED, COMPLETED
}