package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileMain {

	public static void main(String[] args) throws IOException {
		File file = new File("temp/example.txt");
		File directory = new File("temp/exampleDir");

		System.out.println("file.exists() = " + file.exists()); // 파일이 존재하는지

		boolean created = file.createNewFile(); //실제 파일을 만들어준다
		System.out.println("File created = " + created);

		boolean dirCreated = directory.mkdir();
		System.out.println("dirCreated = " + dirCreated);

//		boolean deleted = file.delete();
//		System.out.println("deleted = " + deleted);

		System.out.println("Is file: " + file.isFile());
		System.out.println("Is directory: " + directory.isDirectory());
		System.out.println("File Name: " + file.getName());
		System.out.println("File size: " + file.length() + " bytes");

		File newFile = new File("temp/newExample.txt");
		boolean renamed = file.renameTo(newFile);
		System.out.println("renamed = " + renamed);

		long lastModified = newFile.lastModified();
		System.out.println("lastModified = " + new Date(lastModified));

	}
}
