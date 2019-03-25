package com.cookwithkroger.rev2019.model;

public class Cart {
	
	private int cartID;
	private int customerID;
	private int quantity;
	
	public Cart(){
		
	}
	
	public int getCartID() {
		return cartID;
	}
	
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
