package com.cookwithkroger.rev2019.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.cookwithkroger.rev2019.model.Customer;
import com.cookwithkroger.rev2019.model.CustomerDao;

@Component
public class CustomerJDBCDao implements CustomerDao {
	private JdbcTemplate jdbcTemplate;
	
	public CustomerJDBCDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Customer getById(int customerId) {
		String getCustomerByIdSql = "SELECT customer_id, username, firstname, lastname, password, eamil_address FROM customer WHERE customer_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getCustomerByIdSql, customerId);
		
		Customer c = null;
		if (result.next()) {
			c = createCustomer(result.getInt("customer_id"),
						result.getString("username"),
						result.getString("firstname"),
						result.getString("lastname"),
						result.getString("password"),
						result.getString("email_address"));
		}
		
		return c;
	}
	
	private Customer createCustomer(int customerId, String userName, String firstName, String lastName, String password, String email_address) {
		Customer c = new Customer();
		c.setCustomerId(customerId);
		c.setUserName(userName);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setPassword(password);
		c.setEmail(email_address);
		
		return c;
	}
	
	@Override
	public List<Integer> getItemUPCFromPantry(int customerId) {
		
		String getPantryID = "SELECT pantry_ID FROM customer_pantry WHERE customer_id = ?";
		
		SqlRowSet resultPantryID = jdbcTemplate.queryForRowSet(getPantryID, customerId);
		
		int pantry_ID = -1;
		if (resultPantryID.next()) {
			pantry_ID = resultPantryID.getInt("pantry_ID");
		}
		
		if(pantry_ID == -1) {
			return null;
		}
		
		
		String getItemUPCsFromPantry = "SELECT upc FROM pantry_products WHERE pantry_ID = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getItemUPCsFromPantry, pantry_ID);
		
		List<Integer> resultsList = new ArrayList<Integer>();
		while (result.next()) {
			resultsList.add(result.getInt("upc"));
		}
		
		return resultsList;
	}
	
	@Override
	public void buyItems(List<Product> listProducts, int pantry_id) {
		for (Product product: listProducts) {
			String addItemsToPantry = "INSERT INTO pantry_products (pantry_id, upc) VALUES (?,?);";
			jdbcTemplate.update(addItemsToPantry, pantry_id, product.getProductUPC());
		}
	}

}
