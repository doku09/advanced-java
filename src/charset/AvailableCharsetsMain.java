package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {

	public static void main(String[] args) {
		SortedMap<String, Charset> charsets = Charset.availableCharsets();

		for (String charsetName : charsets.keySet()) {
			System.out.println("charsetName = " + charsetName);
		}

		System.out.println("=====");
		Charset charset1 = Charset.forName("MS949");
		System.out.println("charset1 = " + charset1);

		// 별칭 조회
		Set<String> aliases = charset1.aliases();
		for (String alias : aliases) {
			System.out.println("alias = " + alias);
		}

		Charset charset2 = Charset.forName("MS949");
		System.out.println("charset2 = " + charset2);

		Charset charset3 = StandardCharsets.UTF_8;
		System.out.println("charset3 = " + charset3);

		//시스템의 기본 Charset 조회
		Charset defaultCharset = Charset.defaultCharset();
		System.out.println("defaultCharset = " + defaultCharset);
	}
}
