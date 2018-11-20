package com.anandchakru.app;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlTest {
	public static void main(String[] args) throws Exception {
		URL url = new URL(
				"https://api.github.com/repos/anandchakru/jrvite/releases/assets/9453603?access_token=19b300f8638e1addb11c92417796feb61a26c4ba");
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Accept", "application/octet-stream");
		ReadableByteChannel uChannel = Channels.newChannel(connection.getInputStream());
		FileOutputStream foStream = new FileOutputStream("C:/Users/anand/jrvite.jar");
		FileChannel fChannel = foStream.getChannel();
		fChannel.transferFrom(uChannel, 0, Long.MAX_VALUE);
		uChannel.close();
		foStream.close();
		fChannel.close();
		System.out.println(connection.getResponseCode());
	}
}