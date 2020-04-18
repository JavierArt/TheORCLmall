package com.mall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import javax.management.RuntimeErrorException;

public class ProductsDAO {
//products_seq
//stores_seq	
	//search
	
	private boolean containsID(final Collection<StoresDTO> stores, final int ID){
	    return stores.stream().anyMatch(o -> o.getID() == ID);
	}
	
	public void addproducts(Collection<StoresDTO> stores)
	{
		Connection con=null;
		PreparedStatement pstm=null;		
		try
		{
			con = UConecction.getConnection();				
			Scanner scan = new Scanner(System.in);
			System.out.println("select the store id where to add the product");
			int key3 = scan.nextInt();
			scan.nextLine();
			if(containsID(stores,key3))
			{				
				System.out.println("enter name");
				String nombr = scan.nextLine();						
				System.out.println("enter ammount");
				int cant = scan.nextInt();						
				System.out.println("enter price");
				double pre = scan.nextDouble();
			
				String sql="INSERT INTO products(ID,NAME,AMMOUNT,PRICE,STOREID) VALUES(products_seq.nextval,?,?,?,?)";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, nombr);
				pstm.setInt(2, cant);
				pstm.setDouble(3, pre);
				pstm.setInt(4, key3);
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
	
	public void searchproducts()
	{
		Connection con = null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try
		{
			con = UConecction.getConnection();
			Scanner scan = new Scanner(System.in);
			System.out.println("Which product are you looking for");
			String product = scan.nextLine();
			String sql = "select products.name, products.price, products.ammount, stores.name as storename,stores.floor "
					+ "from products "
					+ "left join stores on stores.id = products.storeid "
					+ "where products.name LIKE '%"+product+"%'";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();			
			ProductsDTO dtop=null;
			StoresDTO dtos=null;
			while(rs.next())
			{
				dtop = new ProductsDTO();
				dtos = new StoresDTO();
				dtop.setName(rs.getString("Name"));
				dtop.setPrice(rs.getInt("price"));
				dtop.setAmmount(rs.getInt("ammount"));
				dtos.setName(rs.getString("storename"));
				dtos.setFloor(rs.getInt("floor"));
				System.out.println(dtos);
				System.out.println(dtop);
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
				if(rs!=null)rs.close();
			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}
	public void modifyproducts(Collection<StoresDTO> stores)
	{
		Connection con = null;
		PreparedStatement pstm=null;
		PreparedStatement pstm2=null;
		ResultSet rs = null;
		try
		{
			con = UConecction.getConnection();				
			Scanner scan = new Scanner(System.in);
			System.out.println("select the store id where to delete the product");
			int key3 = scan.nextInt();
			scan.nextLine();
			if(containsID(stores,key3))
			{
				//here you show products and enters them into an arraylist
				String sql= "select * from products where storeid = ?";
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, key3);
				rs = pstm.executeQuery();
				ArrayList<ProductsDTO> ret = new ArrayList<>();
				ProductsDTO dto=null;
				while(rs.next())
				{
					dto = new ProductsDTO();
					dto.setID(rs.getInt("ID"));
					dto.setName(rs.getString("NAME"));
					dto.setAmmount(rs.getInt("ammount"));
					dto.setPrice(rs.getInt("PRICE"));
					ret.add(dto);
				}
				//print products from the selected store list
				for(ProductsDTO p:ret)
				{
					System.out.println(p);
				}
				if(!ret.isEmpty())
				{
					System.out.println("enter id from product to modify");
					int idpr = scan.nextInt();
					scan.nextLine();
					System.out.println("enter name");
					String name = scan.nextLine();
					System.out.println("enter ammount");
					int ammount = scan.nextInt();
					System.out.println("enter price");
					int price = scan.nextInt();
					String sql2= "UPDATE products SET NAME=?, AMMOUNT=?, PRICE=? WHERE ID =?";
					pstm2 = con.prepareStatement(sql2);
					pstm2.setString(1, name);
					pstm2.setInt(2, ammount);
					pstm2.setInt(3, price);
					pstm2.setInt(4, idpr);
					int rtdo = pstm2.executeUpdate();
					if(rtdo>1)
					{
						String msg = "error more than one record was going to be updated in fact they were going to be" + rtdo; 
						throw new RuntimeException(msg); 
					}
				}
				else
				{
					System.out.println("This stores doesn't have any products");
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
				if(pstm!=null)pstm.close();
				if(pstm2!=null)pstm2.close();
			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}
	
	public void deleteproucts(Collection<StoresDTO> stores)
	{
		Connection con = null;
		PreparedStatement pstm=null;
		PreparedStatement pstm2=null;
		ResultSet rs = null;
		try
		{
			con = UConecction.getConnection();				
			Scanner scan = new Scanner(System.in);
			System.out.println("select the store id where to delete the product");
			int key3 = scan.nextInt();
			scan.nextLine();
			if(containsID(stores,key3))
			{
				//here you show products and enters them into an arraylist
				String sql= "select * from products where storeid = ?";
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, key3);
				rs = pstm.executeQuery();
				ArrayList<ProductsDTO> ret = new ArrayList<>();
				ProductsDTO dto=null;
				while(rs.next())
				{
					dto = new ProductsDTO();
					dto.setID(rs.getInt("ID"));
					dto.setName(rs.getString("NAME"));
					dto.setAmmount(rs.getInt("ammount"));
					dto.setPrice(rs.getInt("PRICE"));
					ret.add(dto);
				}
				//print products from the selected store list
				for(ProductsDTO p:ret)
				{
					System.out.println(p);
				}
				if(!ret.isEmpty())
				{
					System.out.println("enter id from product to delete");
					int idpr = scan.nextInt();
					String sql2= "Delete from products where id = ?";
					pstm2 = con.prepareStatement(sql2);
					pstm2.setInt(1, idpr);
					pstm2.executeUpdate();
				}
				else
				{
					System.out.println("This stores doesn't have any products");
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
				if(pstm!=null)pstm.close();
				if(pstm2!=null)pstm2.close();
			}
			catch(Exception ex)
			{
				throw new RuntimeException(ex);
			}
		}
	}
}