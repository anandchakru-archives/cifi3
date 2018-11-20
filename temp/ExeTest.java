package com.anandchakru.app;

import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExeTest {
	public static void main(String[] args) throws Exception {
		String[] script = { "powershell.exe", "C:\\Users\\anand\\ews\\test\\trx\\shut.ps1" };
		ProcessBuilder processB = new ProcessBuilder().command(script);
		//processB.directory(new File("C:\\Users\\anand\\ews\\test\\trx\\"));
		Process process = processB.start();
		System.out.println(IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8));
		System.out.println(IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8));
		process.waitFor();
	}
}