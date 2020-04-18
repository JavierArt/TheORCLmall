package com.mall;

public class ProductsDTO {

	private int ID;
	private String name;
	private int ammount;
	private double price;

	
	@Override
	public String toString() {
		return "Products [ ID="+ID+" name of the product=" + name + ", ammount=" + ammount + ", price=" + price + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsDTO other = (ProductsDTO) obj;
		if (ID != other.ID)
			return false;
		return true;
	}



	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAmmount() {
		return ammount;
	}


	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
