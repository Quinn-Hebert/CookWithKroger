package com.cookwithkroger.rev2019.model;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class ProductJDBCDao implements ProductDao {
	private JdbcTemplate jdbcTemplate;
	
	public ProductJDBCDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Product getByUPC(int productUPC) {
		String getProductByUPCSql = "SELECT upc, product_description, commodity, brand, product_size FROM product WHERE UPC = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getProductByUPCSql, productUPC);
		
		Product p = null;
		if (result.next()) {
			p = createProduct(result.getInt("upc"), result.getString("product_description"), result.getString("commodity"), result.getString("brand"), result.getInt("product_size"));
		}
		
		return p;
	}
	
	private Product createProduct(int upc, String name, String commodity, String brand, int productSize) {
		Product p = new Product();
		p.setName(name);
		p.setProductUPC(upc);
		p.setCommodity(commodity);
		p.setBrand(brand);
		p.setProductSize(productSize);
		
		String getPriceByUPCSql = "SELECT product_price FROM product_store WHERE upc = ?";
		
		SqlRowSet resultPrice = jdbcTemplate.queryForRowSet(getPriceByUPCSql, upc);
		
		if(resultPrice.next()) {
			p.setPrice(resultPrice.getDouble("product_price"));
		}
		
		String getProductByUPCSql = "SELECT upc FROM pantry_products WHERE upc = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getProductByUPCSql, upc);
		
		if(result.next()) {
			p.setChecked("");
		}
		else {
			p.setChecked("checked");
		}
		
		return p;
	}
	
}
