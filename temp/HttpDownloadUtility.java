package com.anandchakru.app.model.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
* A utility that downloads a file from a URL.
* 
*
*/
public class HttpDownloadUtility {
	private static final int BUFFER_SIZE = 4096;

	public static void downloadFileB(String fileURL, String saveFile, String token) throws IOException {
		FileOutputStream fos = null;
		try {
			fileURL = "https://github.com/anandchakru/fr/releases/download/1.0/fr.jar";
			URL url = new URL(fileURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "token " + token);
			conn.setRequestProperty("Accept", "application/octet-stream");
			ReadableByteChannel readableByteChannel = Channels.newChannel(conn.getInputStream());
			fos = new FileOutputStream(saveFile);
			fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public static void downloadFile(String fileURL, String saveFile, String token) throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpConn.setRequestProperty("Authorization", "token " + token);
		httpConn.setRequestProperty("Accept", "application/octet-stream");
		int responseCode = httpConn.getResponseCode();
		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();
			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			}
			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);
			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			//String saveFilePath = saveDir + File.separator + fileName;
			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFile);
			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			inputStream.close();
			System.out.println("File downloaded");
		} else {
			System.out.println("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}
}