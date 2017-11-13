package kursJFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

public class Singleton implements Connectable {

	private static Singleton instance = null;
	public static Connection con = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	private Singleton() {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Hotel;integratedSecurity=true;";

		try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			System.out.println("---------Nast¹pi³o po³¹czenie z baz¹ danych !!!---------");
			System.out.println("-------------------------------------------------------- \n");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	@Override
	public void selectAll() {
		String SQL = "SELECT * FROM klienci";
		try {
			stmt = con.createStatement(SQLServerResultSet.TYPE_SS_SERVER_CURSOR_FORWARD_ONLY,
					+SQLServerResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQL);
			System.out.println("------------------Zawartoœæ bazy danych-----------------");
			timerTest();
			rs.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	@Override
	public void deleteSurname(String Surname) {
		String SQL = "DELETE FROM klienci WHERE Nazwisko= '" + Surname + "';";

		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeQuery(SQL);
		} catch (SQLException e) {

		}

	}

	public static void timerTest() {
		try {

			int rowCount = 0;
			long startTime = 0;
			long stopTime = 0;
			long runTime = 0;

			rs.setFetchSize(1);
			startTime = System.currentTimeMillis();
			while (rs.next()) {
				rowCount++;
				System.out.println(
						rs.getString("Imie") + " " + rs.getString("Nazwisko") + ", PESEL:" + rs.getString("PESEL"));
			}
			stopTime = System.currentTimeMillis();
			runTime = stopTime - startTime;

			System.out.println("-------------------------------------------------------- ");
			System.out.println("Przetworzono rekordów: " + rowCount);
			System.out.println("Czas [ms]: " + runTime);
			System.out.println("-------------------------------------------------------- \n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
				System.out.println("-------------------------------------------------------- ");
				System.out.println("-----------------Zamkniêto po³¹czenie-------------------");
				System.out.println("-------------------------------------------------------- \n \n ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void reopenConnection() {
		try {
			String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Hotel;integratedSecurity=true;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			System.out.println("---------Nast¹pi³o po³¹czenie z baz¹ danych !!!---------");
			System.out.println("-------------------------------------------------------- \n");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
