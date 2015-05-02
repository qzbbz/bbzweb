package com.wisdom.web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64Converter {
	
	public static String imageToBase64(InputStream is) {
		byte[] data = null;
		try {
			data = new byte[is.available()];
			is.read(data);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Base64.Encoder encoder = Base64.getEncoder();
		return new String(encoder.encode(data));
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String imgFile = "C:\\Users\\Administrator\\Desktop\\test.jpg";
        InputStream in = new FileInputStream(imgFile);
        System.out.println(Base64Converter.imageToBase64(in));
	}
}
