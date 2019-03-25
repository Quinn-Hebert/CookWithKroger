package com.cookwithkroger.rev2019.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class CartJDBCDao implements CartDao{

	
	private JdbcTemplate jdbcTemplate;
	
	public CartJDBCDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Cart getById(int cartId) {
		String getCartByIdSql = "SELECT cart_id, customer_id, quantity FROM customer_cart WHERE cart_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getCartByIdSql, cartId);
		
		Cart cart = null;
		if (result.next()) {
			cart = createCart(result.getInt("cart_id"),
						result.getInt("customer_id"),
						result.getInt("quantity"));
		}
		
		return cart;
	}
	
	private Cart createCart(int cartId, int customerId, int quantity) {
		Cart cart = new Cart();
		cart.setCartID(cartId);
		cart.setCustomerID(customerId);
		cart.setQuantity(quantity);
		return cart;
	}
	
	@Override
	public void addItemsToCart(List<Product> listProducts, int cart_ID) {
		Map<Product,Integer> map = new HashMap<Product,Integer>();
		for (Product product: listProducts) {
			if (map.get(product) == null) {
				map.put(product, 1);
				String getQuantityOfProduct = "SELECT quantity FROM recipe_product WHERE upc = ?";
				int quantity = 1;
				SqlRowSet result = jdbcTemplate.queryForRowSet(getQuantityOfProduct, product.getProductUPC());
				if (result.next()) {
					quantity = result.getInt("quantity");
				}
				String addItemsToPantry = "INSERT INTO cart_products (cart_ID, upc, quantity) VALUES (?,?,?);";
				jdbcTemplate.update(addItemsToPantry, cart_ID, product.getProductUPC(),quantity);
			}
		}
	}
	
	@Override
	public List<Product> getAllProductsInCart(int cart_ID) {
		List<Product> productList = new ArrayList<Product>();
		List<Integer> upcsList = getAllProductUPCsInCart(cart_ID);
		
		for (Integer currentUPC: upcsList) {
			productList.add(getByUPC(currentUPC));
		}
		
		return productList;
	}
	
	@Override
	public Map<Integer,Integer> getAllProductsWithQuantitiesInCart(int cart_ID) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<Product> productList = getAllProductsInCart(cart_ID);
		for (Product product: productList) {
			String getQuantityOfProduct = "SELECT quantity FROM recipe_product WHERE upc = ?";
			int quantity = 1;
			SqlRowSet result = jdbcTemplate.queryForRowSet(getQuantityOfProduct, product.getProductUPC());
			if (result.next()) {
				quantity = result.getInt("quantity");
			}
			map.put(product.getProductUPC(), quantity);
		}
		return map;
	}
	
	private Product getByUPC(int productUPC) {
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
	
	@Override
	public List<Integer> getAllProductUPCsInCart(int cart_ID) {
		
		String getAllRecipes = "SELECT upc FROM cart_products WHERE cart_ID = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllRecipes, cart_ID);
		
		List<Integer> upcList = new ArrayList<Integer>();
		
		while (result.next()) {
			upcList.add(result.getInt("upc"));
		}
		
		return upcList;
	}
	
	@Override
	public void emptyCart(int cart_ID) {
		String emptyCart = "DELETE FROM cart_products WHERE cart_ID = ?;";
		jdbcTemplate.update(emptyCart, cart_ID);
	}
}
