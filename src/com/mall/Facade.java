package com.mall;

import java.util.Collection;

public class Facade {
	
	public void creatingtoresf()
	{
		StoresDAO storesDAO = new StoresDAO();
		storesDAO.addstore();
	}
	
	public Collection<StoresDTO> obtainingStoresf()
	{
		StoresDAO storesDAO = new StoresDAO();
		return storesDAO.showStores();
	}

	//does not work yet
	public void cascadeDeletef()
	{
		StoresDAO storesDAO = new StoresDAO();
		storesDAO.cascadeDeleteStoreAndProducts();
	}
	
	public void modifystoredataf(Collection<StoresDTO> tiendas)
	{
		StoresDAO storesDAO = new StoresDAO();
		storesDAO.modifyStore(tiendas);
	}
	
	public void addingproductsf(Collection<StoresDTO> tiendas)
	{
		ProductsDAO productsDAO = new ProductsDAO();
		productsDAO.addproducts(tiendas);
	}
	
	public void searchingproducts()
	{
		ProductsDAO productsDAO = new ProductsDAO();
		productsDAO.searchproducts();
	}
	
	public void modifyingproducts(Collection<StoresDTO> tiendas)
	{
		ProductsDAO productsDAO = new ProductsDAO();
		productsDAO.modifyproducts(tiendas);
	}

	public void deletingproducts(Collection<StoresDTO> tiendas)
	{
		ProductsDAO productsDAO = new ProductsDAO();
		productsDAO.deleteproucts(tiendas);
	}
}
