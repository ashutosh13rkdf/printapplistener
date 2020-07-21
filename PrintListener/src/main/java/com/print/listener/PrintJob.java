package com.print.listener;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class PrintJob {
	@JmsListener(destination = "printJob")
	public void receiveQueue(String sourceURL) {
		System.out.println("Message Received: " + sourceURL);
			try {
				printJob(sourceURL);
			} catch (IOException e1) {
				System.out.println("Exception in print job : "+e1.getMessage());
			} 
			System.out.println("Print Completed : " + sourceURL);
		  try { 
			  	Thread.sleep(5000); 
			  } catch (InterruptedException e){ 
				  e.printStackTrace();   
			  }
		 
	}
/*
	private static String download(String sourceURL, String targetDirectory) throws IOException {
		URL url = new URL(sourceURL);
		String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
		Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
		Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		return fileName;
	}
*/
	private static void printJob(String filePath) throws IOException {
		
		      URL url = new URL(filePath);
		      PDDocument pdf = PDDocument.load(url.openStream());
		      PrinterJob job = PrinterJob.getPrinterJob();
		      job.setPageable(new PDFPageable(pdf));
		      try {
				job.print();
			} catch (PrinterException e) {
				System.out.println("Exception in job.print() : "+e.getMessage());
			}
		     
	}

	/*public void printPdf(String url) throws IOException {
		System.out.println("start pdf printing using default printer in java");
		URL url1 = new URL(url);

		byte[] ba1 = new byte[1024];
		int baLength;
		FileOutputStream fos1 = new FileOutputStream("download.pdf");

		try {

			System.out.print("Connecting to " + url1.toString() + " ... ");
			URLConnection urlConn = url1.openConnection();

			// Checking whether the URL contains a PDF
			if (!urlConn.getContentType().equalsIgnoreCase("application/pdf")) {
				System.out.println("FAILED.\n[Sorry. This is not a PDF.]");
			} else {
				try {

					// Read the PDF from the URL and save to a local file
					InputStream is1 = url1.openStream();
					while ((baLength = is1.read(ba1)) != -1) {
						fos1.write(ba1, 0, baLength);
					}
					fos1.flush();
					fos1.close();
					is1.close();

					// Load the PDF document and display its page count
					System.out.print("DONE.\nProcessing the PDF ... ");

					URL http = new URL(url);
					URLConnection conn = http.openConnection();
					conn.setUseCaches(false);
					conn.connect();

					PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
					// FileInputStream fis = new FileInputStream(conn.getInputStream());
					DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
					// Doc pdfDoc = new SimpleDoc(conn.getInputStream(), flavor, null);
					Doc pdfDoc = new SimpleDoc(http.openStream(), flavor, null);

					DocPrintJob printJob = printService.createPrintJob();
					System.out.println("before print");
					printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
					System.out.println("after print");
					// fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ConnectException ce) {
			System.out.println("FAILED.\n[" + ce.getMessage() + "]\n");
		}
	}*/
}
