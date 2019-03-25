package com.cookwithkroger.rev2019.model;

public class Product {
	
	private int productUPC;
	private String name;
	private String commodity;
	private String brand;
	private String storeNum;
	private double price;
	private int productSize;
	private String checked;
	
	public Product() {
	}


	public int getProductUPC() {
		return productUPC;
	}


	public void setProductUPC(int productUPC) {
		this.productUPC = productUPC;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCommodity() {
		return commodity;
	}


	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	
	public String getStoreNum() {
		return storeNum;
	}


	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}


	public int getProductSize() {
		return productSize;
	}


	public void setProductSize(int productSize) {
		this.productSize = productSize;
	}
	
	public String getChecked() {
		return checked;
	}
	
	public void setChecked(String checked) {
		this.checked = checked;
	}
}
