package com.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mall.UConecction;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class Validate {

	public static boolean authorizeadmin(String user,String password)
	{		
		//id,user,code,isadmin
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;		
		try
		{
			con = UConecction.getConnection();
			String sql="SELECT USERNAME,CODE,ISADMIN FROM USERS where USERNAME =?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			String uncode;			
			while(rs.next())
			{
				String usern = rs.getString(1);
				String code = rs.getString(2);
				uncode = Encription.desencriptar(code);
				int admin = rs.getInt(3);
				boolean us=usern.equals((user.trim()));
		        boolean ps=uncode.equals(password);
		        boolean isadmin = (admin==1)?true:false;
		        if(us && ps && isadmin)
		        {
		        	return true;
		        }
		        else
		        {		        	
		        	return false;
		        }
			}
			return false;
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();

			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex); 
			}
		}		
	}
	
	public static void registerUser()
	{
		Connection con=null;
		PreparedStatement pstm=null;
		try
		{
			con = UConecction.getConnection();				
			Scanner scan = new Scanner(System.in);
			System.out.println("enter username");
			String username = scan.nextLine();
			System.out.println("enter password");
			String code = scan.nextLine();
			String Ecode = Encription.encriptar(code);
			System.out.println("Is this an admin Yes/No");
			String isadmin = scan.nextLine();
			int Iadmin = (isadmin.equals("Yes") || isadmin.equals("Y") || isadmin.equals("y") || isadmin.equals("yes")) ? 1:0;
			
			String sql = "insert into users(username,code,isadmin) values(?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, Ecode);
			pstm.setInt(3, Iadmin);
			int rtdo = pstm.executeUpdate();		
			if(rtdo == 1)
			{
				System.out.println("User succesfully inserted");
			}
			else
			{
				throw new RuntimeException("could not insert");
			}
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(pstm!=null)pstm.close();
			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}
}
