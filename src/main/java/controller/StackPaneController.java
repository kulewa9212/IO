package controller;

import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextField;
import kursJFX.Singleton;

public class StackPaneController {
	
	@FXML
	TextField T1;
	Button B1;
	Singleton DB = Singleton.getInstance();
	//ComboBoxBase<T> COM1;
	
	public StackPaneController(){
		System.out.println("Powolano kontroler! ");
	
	}
		

	public void b1clicked(){
		String SQL = "SELECT * FROM klienci";
		try {
			Singleton.stmt = Singleton.con.createStatement(SQLServerResultSet.TYPE_SS_SERVER_CURSOR_FORWARD_ONLY,
					+SQLServerResultSet.CONCUR_READ_ONLY);
			Singleton.rs = Singleton.stmt.executeQuery(SQL);
			T1.setText("------------------Zawartoœæ bazy danych-----------------");
			try {

				int rowCount = 0;
				long startTime = 0;
				long stopTime = 0;
				long runTime = 0;

				Singleton.rs.setFetchSize(1);
				startTime = System.currentTimeMillis();
				while (				Singleton.rs.next()) {
					rowCount++;
					T1.appendText("\n"+
							Singleton.rs.getString("Imie") + " " +Singleton.rs.getString("Nazwisko") + ", PESEL:" +Singleton.rs.getString("PESEL")+" "+"\n");
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
			Singleton.rs.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
	}

}
