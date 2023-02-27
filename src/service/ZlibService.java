package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Deflater;

public class ZlibService {

	public static long compress(File file) {
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] bytes = fis.readAllBytes();
			Deflater compressor = new Deflater();
			return compressor.deflate(bytes);
		} catch (IOException e) {
			throw new IllegalArgumentException("[ERROR] 파일을 읽는 중 오류가 발생했습니다.");
		}
	}
}
