package com.wisdom.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerateHtml {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		File file = new File(
				"C:\\Users\\Administrator\\Desktop\\sheet_salary.html");
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			sb.append(lineTxt);
		}
		String html = sb.toString();

		
		file = new File(
				"C:\\Users\\Administrator\\Desktop\\sheet_salary_html.txt");
		read = new InputStreamReader(
				new FileInputStream(file));
		bufferedReader = new BufferedReader(read);
		lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if (lineTxt.isEmpty())
				continue;
			String[] strArray = lineTxt.split("\t");
			int posName = sb.indexOf(strArray[0]);
			int pos1 = sb.indexOf("<td", posName + 2);
			sb.insert(pos1 + 3, " id=\"" + strArray[1] + "\" ");
			int pos2 = sb.indexOf("<td", pos1 + 2);
			sb.insert(pos2 + 3, " id=\"" + strArray[2] + "\" ");
		}
		FileWriter fileWriter = new FileWriter("C:\\Users\\Administrator\\Desktop\\Result.html");
		fileWriter.write(sb.toString());
		fileWriter.flush();
		fileWriter.close();
	}
}
