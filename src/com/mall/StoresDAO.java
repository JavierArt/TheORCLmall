package com.mall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StoresDAO {

	private boolean containsID(final Collection<StoresDTO> stores, final int ID){
	    return stores.stream().anyMatch(o -> o.getID() == ID);
	}

	public void addstore()
	{
		Connection con=null;
		PreparedStatement pstm=null;
		try
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("enter name");
			String nom = scan.nextLine();		
			System.out.println("enter floor");
			int piso = scan.nextInt();	
			System.out.println("enter local number");
			int local = scan.nextInt();		
			scan.nextLine();

			con = UConecction.getConnection();
			String sql="INSERT INTO stores(ID,NAME,FLOOR,NUMLOCAL) VALUES(stores_seq.nextval,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nom);
			pstm.setInt(2, piso);
			pstm.setInt(3, local);
			int rtdo = pstm.executeUpdate();
			if(rtdo == 1)
			{
				System.out.println("Succesfully inserted");
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
	
	public Collection<StoresDTO> showStores()
	{
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try 
		{
			con = UConecction.getConnection();
			String sql="select * from stores";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			ArrayList<StoresDTO> ret = new ArrayList<>();
			StoresDTO dto=null;
			while(rs.next())
			{
				dto = new StoresDTO();
				dto.setID(rs.getInt("ID"));
				dto.setName(rs.getString("NAME"));
				dto.setFloor(rs.getInt("floor"));
				dto.setNumLocal(rs.getInt("NUMLOCAL"));
				ret.add(dto);
			}
			return ret;
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(rs!=null)rs.close();
				if(pstm!=null)pstm.close();
			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex); 
			}
		}
	}
	
	public void modifyStore(Collection<StoresDTO> tiendas)
	{
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try 
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("select the store id to modify");
			int key2 = scan.nextInt();
			scan.nextLine();
			if(containsID(tiendas,key2))
			{
				System.out.println("enter name");
				String nomb = scan.nextLine();
				System.out.println("enter floor");
				int piso2 = scan.nextInt();
				System.out.println("enter local number");
				int local2 = scan.nextInt();
				scan.nextLine();
				
				con = UConecction.getConnection();
				String sql = "UPDATE stores set NAME = ?, FLOOR = ?, NUMLOCAL=? WHERE ID = ?";
				
				pstm = con.prepareStatement(sql);
				pstm.setString(1, nomb);
				pstm.setInt(2, piso2);
				pstm.setInt(3, local2);
				pstm.setInt(4, key2);
				int rtdo = pstm.executeUpdate();
				if(rtdo>1)
				{
					String msg = "error more than one record was going to be updated in fact they were going to be" + rtdo; 
					throw new RuntimeException(msg); 
				}
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
				if(rs!=null)rs.close();
				if(pstm!=null)pstm.close();
			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex); 
			}
		}
	}
	
	public void cascadeDeleteStoreAndProducts()
	{
		Connection con=null;
		PreparedStatement pstm=null;
		try 
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("enter the store ID to delete");
			int where = scan.nextInt();
			con = UConecction.getConnection();
			String sql = "DELETE from stores where ID=?";			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, where);
			int rtdo =  pstm.executeUpdate();
			if(rtdo>1)
			{
				String msg = "error more than one record was going to be eliminated in fact they were going to be" + rtdo; 
				throw new RuntimeException(msg); 
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