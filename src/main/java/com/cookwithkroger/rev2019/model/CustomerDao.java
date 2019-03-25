package com.cookwithkroger.rev2019.model;

import java.util.List;

import com.cookwithkroger.rev2019.model.Customer;

public interface CustomerDao {
	Customer getById(int CustomerId);
	List<Integer> getItemUPCFromPantry(int customerId);
	void buyItems(List<Product> listProducts, int pantry_id);
}
