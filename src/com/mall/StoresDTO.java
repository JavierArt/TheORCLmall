package com.mall;

public class StoresDTO {

	private int ID;
	private String name;
	private int floor;
	private int numLocal;
	
	
	@Override
	public String toString() {
		return "Stores [ID=" + ID + ", name=" + name + ", floor=" + floor + ", numLocal=" + numLocal + "]";
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
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getNumLocal() {
		return numLocal;
	}
	public void setNumLocal(int numLocal) {
		this.numLocal = numLocal;
	}

}
