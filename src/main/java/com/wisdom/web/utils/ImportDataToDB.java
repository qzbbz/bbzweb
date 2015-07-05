package com.wisdom.web.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ImportDataToDB {

	public static void main(String[] args) {
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名scutcs
		String url = "jdbc:mysql://121.40.63.208/bbz";

		// MySQL配置时的用户名
		String user = "root";

		// MySQL配置时的密码
		String password = "Cckzcbm110";

		try {
			// 加载驱动程序
			Class.forName(driver);

			// 连续数据库
			Connection conn = (Connection) DriverManager.getConnection(url,
					user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// statement用来执行SQL语句
			Statement statement = (Statement) conn.createStatement();
			File file = new File(
					"C:\\Users\\Administrator\\Desktop\\sheet_salary_tax.txt");
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				if(lineTxt.isEmpty()) continue;
				String sql = "alter table sheet_salary_tax add "+lineTxt+" double(100,3);";
				statement.executeUpdate(sql);
			}
			read.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}