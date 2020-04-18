package com.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class UConecction 
{
	//null conection
		private static Connection con=null;

		//obtain conection
		public static Connection getConnection()
		{
				try
				{
					if(con == null)
					{
						//determine when the program ends
						Runtime.getRuntime().addShutdownHook(new MiShutdownHook());
						
						ResourceBundle rb = ResourceBundle.getBundle("jdbc");
						String driver = rb.getString("driver");
						String url = rb.getString("url");
						String pwd = rb.getString("pwd");
						String usr = rb.getString("usr");
						
						Class.forName(driver);
						con = DriverManager.getConnection(url, usr, pwd);
					}
					return con;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}
			}
			static class MiShutdownHook extends Thread
			{
				public void run()
				{
					try
					{
						Connection con = UConecction.getConnection();
						con.close();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						throw new RuntimeException(ex);
					}
				}
			}
}