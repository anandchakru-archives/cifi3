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
				"https://api.github.com/repos/anandchakru/private_repo/releases/assets/asset_id?access_token=access_token_from_github_com_settings_tokens");
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
