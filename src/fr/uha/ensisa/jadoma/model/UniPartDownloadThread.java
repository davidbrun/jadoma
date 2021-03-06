package fr.uha.ensisa.jadoma.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.util.Date;

import fr.uha.ensisa.jadoma.util.HttpConnection;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class UniPartDownloadThread extends DownloadThread {
	
	private static final int MAX_BUFFER_SIZE = 1024;
	private HttpConnection httpConnection;
    private RandomAccessFile fileDestination;
	
	public UniPartDownloadThread(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		super(downloadPanel, download);
		this.httpConnection = new HttpConnection(download.getUrlFrom());
		
		// Create the parts of the download (in that case just one)
		if (this.download.getListDownloadParts().size() == 0)
			this.download.getListDownloadParts().add(new ActiveDownloadPart(0, this.download.getSize()));
		
		// Create the output file
		try {
			this.fileDestination = new RandomAccessFile(download.getFileDestination(), "rw");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		if (!this.isRunning)
		{
			try {
				this.isRunning = true;
				download.setCurrentState(DownloadState.DOWNLOADING);
				if (!(download.getStartDate().after(new Date(Long.MIN_VALUE))))
					download.setStartDate(new Date());
				downloadPanel.updateComponentValues(download);
				
				ActiveDownloadPart downloadPart = (ActiveDownloadPart) this.download.getListDownloadParts().get(0);
				this.httpConnection.setRange(downloadPart.getNbrOfCompletedBytes(), downloadPart.getEndByte());
				this.fileDestination.seek(downloadPart.getNbrOfCompletedBytes());
	            InputStream in = this.httpConnection.getInputStream();
	            
	            // Headers are delimited with "\r\n\r\n"
	            int count = 0;
	            byte buff[] = new byte[1];
	            boolean flag = true;
	            StringBuilder headers = new StringBuilder();
	            while (flag && in.read(buff) != -1) {
	                headers.append((char)buff[0]);
	                if ((buff[0] == 13 && (count == 0 || count == 2)) || (count == 1 && buff[0] == 10))
	                    count++;
	                else if (buff[0] == 10 && count == 3)
	                    flag = false;
	                else
	                    count = 0;
	            }
	            
	            // Check if we have a 20* return code
	            if (!headers.substring(9, 11).equalsIgnoreCase("20")) {
	                this.isRunning = false;
	                this.interrupt();
	            }
	            
	            float downloadSpeed = 0;
	            int i = 1;
	            long timeInit = System.nanoTime();
	            // Read the following and write it in the destination file
	            while (downloadPart.getNbrOfCompletedBytes() < downloadPart.getEndByte()) {
	                // Adapt the size of the buffer to avoid problems
	                byte buffer[];
	                if (downloadPart.getEndByte() - downloadPart.getNbrOfCompletedBytes() > MAX_BUFFER_SIZE)
	                    buffer = new byte[MAX_BUFFER_SIZE];
	                else
	                    buffer = new byte[downloadPart.getEndByte() - downloadPart.getNbrOfCompletedBytes()];
	                
	                // Read from the server into the buffer
	                int read = in.read(buffer);
	                
	                if (read != -1) {
	                	// Write the buffer into the file
	                    fileDestination.write(buffer, 0, read);
	                    downloadPart.setNbrOfCompletedBytes(downloadPart.getNbrOfCompletedBytes() + read);
	                    
	                    if (System.nanoTime() - timeInit > 400000000l)
	                    {
	                    	float tmp = 1000000000l * buffer.length * i;
	                        downloadSpeed = (float) (tmp / (1.0 + System.nanoTime() - timeInit));
	                        download.setLastKnownSpeed(downloadSpeed);
	                    	downloadPanel.setSpeedLabel(downloadSpeed);
	                        downloadPanel.setProgressValue((int) (download.getProgress() * 100));
	                        downloadPanel.updateProgressionLabel();
	                        downloadPanel.updateStateLabel();
	                        // Init
	                        timeInit = System.nanoTime();
		                    i = 0;
	                    }
	                    i++;
	                }
	                else
	                	break;
	            }
	            
	            // Last update of the view
	            download.setLastKnownSpeed(0);
            	downloadPanel.setSpeedLabel(0);
                downloadPanel.setProgressValue((int) (download.getProgress() * 100));
                downloadPanel.updateProgressionLabel();
                downloadPanel.updateStateLabel();
                // End the download
	            in.close();
	            this.fileDestination.close();
	            this.isRunning = false;
	            this.isDead = true;
	            download.setCurrentState(DownloadState.COMPLETED);
	        } catch (Exception ex) {
	            this.isRunning = false;
	            this.isDead = true;
	        }
		}
	}
	
	@Override
	public void interrupt() {
		if (this.isRunning)
		{
			try {
				super.interrupt();
				this.httpConnection.getInputStream().close();
				this.fileDestination.close();
				this.isRunning = false;
				this.isDead = true;
				download.setCurrentState(DownloadState.PAUSED);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}