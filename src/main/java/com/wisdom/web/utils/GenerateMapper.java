package com.wisdom.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerateMapper {

	public static void main(String[] args) throws IOException {
		
		File file = new File(
				"C:\\Users\\Administrator\\Desktop\\sheet_salary_tax.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(
				file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if(lineTxt.isEmpty()) continue;
			System.out.print("rs.getDouble(\""+lineTxt+"\"),");
		}
		
	}
	
}
