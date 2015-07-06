package com.wisdom.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerateRetMap {

	public static void main(String[] args) throws IOException {
		File file = new File(
				"C:\\Users\\Administrator\\Desktop\\sheet_salary_tax.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(
				file));
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		while ((lineTxt = bufferedReader.readLine()) != null) {
			if(lineTxt.isEmpty()) continue;
			System.out.println("$('#"+lineTxt+"').html(data."+lineTxt+");");
			/*String[] ar = lineTxt.split("_");
			String tmp = "";
			for(int i=0; i<ar.length; i++) {
				String ari = ar[i];
				tmp = tmp + ari.substring(0,1).toUpperCase() + ari.substring(1);
			}*/
			//System.out.println(tmp);
			//System.out.println("retMap.put(\""+lineTxt+"\", format.format(sheetSalaryTax.get"+tmp+"() == null ? 0 : sheetSalaryTax.get"+tmp+"()));");
			
		}
	}
	
}
