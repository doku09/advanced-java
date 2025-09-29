package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp/hello.dat"); // 출력
		fos.write(65);
		fos.write(66);
		fos.write(67);
		fos.close();

		FileInputStream fis = new FileInputStream("temp/hello.dat"); // 입력
		System.out.println(fis.read());
		System.out.println(fis.read());
		System.out.println(fis.read());
		System.out.println(fis.read());
		fis.close();
	}
}
