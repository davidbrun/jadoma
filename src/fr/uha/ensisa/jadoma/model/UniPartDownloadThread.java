package fr.uha.ensisa.jadoma.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import fr.uha.ensisa.jadoma.util.HttpConnection;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class UniPartDownloadThread extends DownloadThread {
	
	private static final String DESTINATION_DIRECTORY = System.getProperty("user.home") + "/";
	private static final int MAX_BUFFER_SIZE = 1024;
	private boolean isRunning = false;
	private HttpConnection httpConnection;
    private RandomAccessFile fileDestination;
	
	public UniPartDownloadThread(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		super(downloadPanel, download);
		this.httpConnection = new HttpConnection(download.getUrlFrom());
		
		// Create the parts of the download (in that case just one)
		this.download.getListDownloadParts().add(new ActiveDownloadPart(0, this.download.getSize()));
		
		// Create the output file
		try {
			this.fileDestination = new RandomAccessFile(DESTINATION_DIRECTORY + this.download.getName(), "rw");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		try {
			this.isRunning = true;
			
			ActiveDownloadPart downloadPart = (ActiveDownloadPart) this.download.getListDownloadParts().get(0);
			this.httpConnection.setRange(downloadPart.getBeginByte(), downloadPart.getEndByte());
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
            
            // Read the following and write it in the destination file
            while (downloadPart.getNbrOfCompletedBytes() < downloadPart.getEndByte()) {
                // Adapt the size of the buffer to avoid problems
                long timeInit = System.nanoTime();
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
                    float tmp = 1000000000 * buffer.length;
                    downloadSpeed = (float) (tmp / (1.0 + System.nanoTime() - timeInit));
                    downloadPanel.setProgressValue((int) (download.getProgress() * 100));
                }
                else
                	break;
            }
            
            in.close();
            this.fileDestination.close();
            this.isRunning = false;
        } catch (Exception ex) {
            this.isRunning = false;
        }
	}
	
	@Override
	public void interrupt() {
		try {
			this.httpConnection.getInputStream().close();
			this.fileDestination.close();
			this.isRunning = false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isRunning() {
		return isRunning;
	}
}