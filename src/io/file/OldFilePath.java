package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {

	public static void main(String[] args) throws IOException {
		File file = new File("temp/..");
		System.out.println("path = " + file.getPath());
		// 절대 경로
		System.out.println("Absolute path = " + file.getAbsolutePath()); // C:\inflearn\younghan\java\practice-java-adv2\temp\..

		// 정규 경로
		System.out.println("Canonical path = " + file.getCanonicalPath()); // C:\inflearn\younghan\java\practice-java-adv2

		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
		}
	}
}
