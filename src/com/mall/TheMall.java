package com.mall;

import java.util.Scanner;
import java.util.TreeMap;

import com.security.Encription;
import com.security.Validate;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;


public class TheMall {

	private static void printstores(Collection<StoresDTO> stores)
	{		
		for(StoresDTO st: stores)
		{
			System.out.println(st);
		}

	}
	
	private static void menuAdmin()
	{
		Collection<StoresDTO> stores;
		Facade fac = new Facade();		
		Scanner scan = new Scanner(System.in);
		int x=0;
		do
		{	
			System.out.println("WELCOME TO THE SYSTEM OF THE MALL FORUM TLAQUEPAQUE");
			System.out.println("1.Add a store");///
			System.out.println("2.Add a product");///
			System.out.println("3.Show stores");///
			System.out.println("4.Search products");///
			System.out.println("5.Modify store data");///
			System.out.println("6.Modify a store product");///
			System.out.println("7.Delete a store prodduct");///
			System.out.println("8.Delete the whole store and products");///
			System.out.println("9.Add a user");///
			System.out.println("0.Exit");
			System.out.println("Choose an option:");
			x = scan.nextInt();			
			switch(x)
			{
				case 1:fac.creatingtoresf();					
					break;
				case 2:
					stores = fac.obtainingStoresf();
					printstores(stores);
					fac.addingproductsf(stores);
					break;
				case 3:
					stores = fac.obtainingStoresf();
					printstores(stores);
					break;
				case 4:fac.searchingproducts();
					break;
				case 5:
					stores = fac.obtainingStoresf();
					printstores(stores);
					fac.modifystoredataf(stores);
					break;
				case 6:
					stores = fac.obtainingStoresf();
					printstores(stores);
					fac.modifyingproducts(stores);
					break;
				case 7:
					stores = fac.obtainingStoresf();
					printstores(stores);
					fac.deletingproducts(stores);
					break;
				case 8:
					stores = fac.obtainingStoresf();
					printstores(stores);
					fac.cascadeDeletef();
					break;
				case 9:
					Validate.registerUser();
				case 0: System.out.println("Exiting...");
					break;
				default: System.out.println("invalid option");
					break;
			}
		}while(x!=0);
	}

	private static void menuUser()
	{
		Collection<StoresDTO> stores;
		Facade fac = new Facade();	
		Scanner scan = new Scanner(System.in);
		int x=0;
		do
		{
			System.out.println("WELCOME TO THE SYSTEM OF THE MALL FORUM TLAQUEPAQUE");
			System.out.println("1.Show stores");
			System.out.println("2.Search products");
			System.out.println("0.Exit");
			System.out.println("Choose an option:");
			x = scan.nextInt();
			scan.nextLine();
			switch(x)
			{
				case 1:
					stores = fac.obtainingStoresf();
					printstores(stores);
					break;
				case 2:fac.searchingproducts();
					break;
				case 0: System.out.println("Exiting...");
					break;
				default: System.out.println("Invalid option");
				break;
			}
		}while(x!=0);
	}
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		int x=0;
		do			
		{
			System.out.println("WELCOME TO THE SYSTEM OF THE MALL FORUM TLAQUEPAQUE");
			System.out.println("Select a user type");
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("0.Exit the system");
			System.out.println("Choose an option");
			x = scan.nextInt();
			scan.nextLine();		
			switch(x)
			{
			case 1:
				System.out.println("enter username");
				String user = scan.nextLine();
				System.out.println("enter password");
				String pass = scan.nextLine();
				if(Validate.authorizeadmin(user, pass))
				{
					menuAdmin();
				}
				else
				{
					menuUser();
				}
				break;
			case 2:
				menuUser();
				break;
			case 0: System.out.println("Exiting the system...");
				break;
			default:System.out.println("invalid option");
				break;
			}
		}while(x!=0);
	}


}
