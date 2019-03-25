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
public class RecipeJDBCDao implements RecipeDao {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSrc;
	
	public RecipeJDBCDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dataSrc = dataSource;
	}
	
	@Override
	public Recipe getById(int recipeId) {
		String getRecipeByIdSql = "SELECT recipe_id, name, recipe_instructions, recipe_description, time_to_cook,"
								+ "recipe_price, recipe_video, recipe_image FROM recipe WHERE recipe_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getRecipeByIdSql, recipeId);
		
		Recipe r = null;
		if (result.next()) {
			r = createRecipe(result.getInt("recipe_id"), 
					result.getString("name"),
					result.getString("recipe_instructions"),
					result.getString("recipe_description"),
					result.getInt("time_to_cook"),
					result.getDouble("recipe_price"),
					result.getString("recipe_video"),
					result.getString("recipe_image")
					);
		}
		
		return r;
	}
	
	private Recipe createRecipe(int recipe_id, String name, String recipe_instructions, String recipe_description, int time_to_cook, double recipe_price, String recipe_video, String recipe_image) {
		Recipe r = new Recipe();
		r.setRecipeId(recipe_id);
		r.setName(name);
		r.setDescription(recipe_description);
		r.setPrice(recipe_price);
		r.setRecipeImage(recipe_image);
		r.setRecipeVideo(recipe_video);
		r.setCookTime(time_to_cook);

		List<String> instructions = new ArrayList<String>();
		for (String s : recipe_instructions.split("\\|")) {
			instructions.add(s);
		}
		r.setInstructions(instructions);
		
		Map<Product, Integer> ingredients = new HashMap<Product, Integer>();
		
		String getProductsForRecipe = "SELECT upc, quantity FROM recipe_product WHERE recipe_id = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getProductsForRecipe, recipe_id);
		
		while (result.next()) {
			ProductDao productJDBCDao = new ProductJDBCDao(dataSrc);
			ingredients.put(productJDBCDao.getByUPC(result.getInt("upc")), result.getInt("quantity"));
		}
		
		r.setIngredients(ingredients);
		
		return r;
	}
	
	private List<Recipe> getAllRecipes() {
		
		String getAllRecipes = "SELECT * FROM recipe ORDER BY recipe_price";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllRecipes);
		
		List<Recipe> recipeList = new ArrayList<Recipe>();
		while (result.next()) {
			recipeList.add(createRecipe(result.getInt("recipe_id"), 
					result.getString("name"),
					result.getString("recipe_instructions"),
					result.getString("recipe_description"),
					result.getInt("time_to_cook"),
					result.getDouble("recipe_price"),
					result.getString("recipe_video"),
					result.getString("recipe_image")
					));
		}
		return recipeList;
	}
	
	@Override
	public List<Recipe> getRecipeInPriceRange(double price) {
		List<Recipe> recipeInPriceList = new ArrayList<Recipe>();
		
		String getAllRecipes = "SELECT * FROM recipe WHERE recipe_price < ? ORDER BY recipe_price";
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllRecipes, price);
		while (result.next()) {
			recipeInPriceList.add(createRecipe(result.getInt("recipe_id"), 
					result.getString("name"),
					result.getString("recipe_instructions"),
					result.getString("recipe_description"),
					result.getInt("time_to_cook"),
					result.getDouble("recipe_price"),
					result.getString("recipe_video"),
					result.getString("recipe_image")
					));
		}
		
		return recipeInPriceList;
	}
	
	@Override
	public List<Recipe> getRecipeInPriceRange(double price, int customer_ID) {
		
		List<Recipe> recipeListInRange = new ArrayList<Recipe>();
		
		List<Recipe> recipeList = getAllRecipes();
		
		List<Integer> upcsList = getItemUPCFromPantry(customer_ID);
				
		for (Recipe currentRecipe: recipeList) {
			double thisPrice = currentRecipe.getPrice();
			Map<Product, Integer> currentProducts = currentRecipe.getIngredients();
			for (Product currentProduct: currentProducts.keySet()) {
				for (Integer upc: upcsList) {
					if (currentProduct.getProductUPC() == upc) {
						thisPrice -= getPriceOfProduct(upc) * currentProducts.get(currentProduct);
					}
				}
			}
			if (thisPrice < price) {
				recipeListInRange.add(currentRecipe);
			}
		}
		
		return recipeListInRange;
	}
	
	@Override
	public List<Recipe> getRecipeInPriceRangeNumServings(double price, int numOfServings) {
		List<Recipe> recipeInPriceList = new ArrayList<Recipe>();
		
		String getAllRecipes = "SELECT * FROM recipe WHERE recipe_price < ? ORDER BY recipe_price";
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllRecipes, price / numOfServings);
		while (result.next()) {
			recipeInPriceList.add(createRecipe(result.getInt("recipe_id"), 
					result.getString("name"),
					result.getString("recipe_instructions"),
					result.getString("recipe_description"),
					result.getInt("time_to_cook"),
					result.getDouble("recipe_price"),
					result.getString("recipe_video"),
					result.getString("recipe_image")
					));
		}
		
		return recipeInPriceList;
	}
	
	@Override
	public List<Recipe> getRecipeInPriceRangeNumServings(double price, int customer_ID, int numOfServings) {
		
		List<Recipe> recipeListInRange = new ArrayList<Recipe>();
		
		List<Recipe> recipeList = getAllRecipes();
		
		List<Integer> upcsList = getItemUPCFromPantry(customer_ID);
				
		for (Recipe currentRecipe: recipeList) {
			double thisPrice = currentRecipe.getPrice() * numOfServings;
			Map<Product, Integer> currentProducts = currentRecipe.getIngredients();
			for (Product currentProduct: currentProducts.keySet()) {
				for (Integer upc: upcsList) {
					if (currentProduct.getProductUPC() == upc) {
						thisPrice -= getPriceOfProduct(upc) * currentProducts.get(currentProduct) * numOfServings;
					}
				}
			}
			currentRecipe.setPrice(thisPrice);
			if (thisPrice < price) {
				recipeListInRange.add(currentRecipe);
			}
		}
		
		return recipeListInRange;
	}
	
	private double getPriceOfProduct(int upc) {
		String getPriceOfProd = "SELECT product_price FROM product_store WHERE upc = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getPriceOfProd, upc);
		
		if (result.next()) {
			return result.getDouble("product_price");
		}
		return 0;
	}
	
	private List<Integer> getItemUPCFromPantry(int customerId) {
		
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
	public List<Recipe> getRecipeInCategory(String categoryName) {
		
		if (categoryName.equals("*")) {
			return getAllRecipes();
		}
		
		List<Recipe> recipeInCategory = new ArrayList<Recipe>();
		
		String getCategoryID = "SELECT category_id FROM category"
				+ " WHERE name = ?";
		SqlRowSet resultCatID = jdbcTemplate.queryForRowSet(getCategoryID, categoryName);
		int catID = 0;
		if (resultCatID.next()) {
			catID = resultCatID.getInt("category_id");
		}
		
		String getAllRecipeIDInCat = "SELECT recipe_id FROM recipe_category"
									+ " WHERE category_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllRecipeIDInCat, catID);
		while (result.next()) {
			recipeInCategory.add(getById(result.getInt("recipe_id")));
		}
		
		return recipeInCategory;
	}
	
	@Override
	public List<Recipe> getRecipeForPrepTime(int timeToCook) {
		List<Recipe> recipePrepTime = new ArrayList<Recipe>();
		
		String getAllRecipeByPrepTime = "SELECT recipe_ID FROM recipe WHERE time_to_cook < ? ORDER BY recipe_price";
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllRecipeByPrepTime, timeToCook);
		while (result.next()) {
			recipePrepTime.add(getById(result.getInt("recipe_id")));
		}
		
		return recipePrepTime;
	}
	
	@Override
	public List<Recipe> getRecipeThatMeetSearch(double price, String categoryName, int timeToCook, int numberOfServings) {
		
		List<Recipe> recipesInCategory = getRecipeInCategory(categoryName);
		List<Recipe> recipesInTimeToCook = getRecipeForPrepTime(timeToCook);
		List<Recipe> recipesInPriceRange = getRecipeInPriceRangeNumServings(price, numberOfServings);
		
		List<Recipe> resultRecipeList = new ArrayList<Recipe>();
		
		for (Recipe r1: recipesInPriceRange) {
			for (Recipe r2: recipesInCategory) {
				if (r1.getRecipeId() == r2.getRecipeId()) {
					for (Recipe r3: recipesInTimeToCook) {
						if (r1.getRecipeId() == r3.getRecipeId()) {
							resultRecipeList.add(r1);
						}
					}
				}
			}
		}
		
		return resultRecipeList;
	}
	
	@Override
	public List<Recipe> getRecipeThatMeetSearch(double price, int customer_ID, String categoryName, int timeToCook, int numberOfServings) {
		List<Recipe> recipesInCategory = getRecipeInCategory(categoryName);
		List<Recipe> recipesInTimeToCook = getRecipeForPrepTime(timeToCook);
		List<Recipe> recipesInPriceRange = getRecipeInPriceRangeNumServings(price, customer_ID, numberOfServings);
		
		List<Recipe> resultRecipeList = new ArrayList<Recipe>();
		
		for (Recipe r1: recipesInPriceRange) {
			for (Recipe r2: recipesInCategory) {
				if (r1.getRecipeId() == r2.getRecipeId()) {
					for (Recipe r3: recipesInTimeToCook) {
						if (r1.getRecipeId() == r3.getRecipeId()) {
							resultRecipeList.add(r1);
						}
					}
				}
			}
		}
		
		return resultRecipeList;
	}
	
	@Override
	public List<Recipe> getRecipeThatMeetSearch(double price, int timeToCook, int numberOfServings) {
		
		List<Recipe> recipesInTimeToCook = getRecipeForPrepTime(timeToCook);
		List<Recipe> recipesInPriceRange = getRecipeInPriceRangeNumServings(price, numberOfServings);
		
		List<Recipe> resultRecipeList = new ArrayList<Recipe>();
		
		for (Recipe r1: recipesInPriceRange) {
			for (Recipe r2: recipesInTimeToCook) {
				if (r1.getRecipeId() == r2.getRecipeId()) {
					resultRecipeList.add(r1);
				}
			}
		}
		
		return resultRecipeList;
	}
	
	@Override
	public List<Recipe> getRecipeThatMeetSearch(double price, int customer_ID, int timeToCook, int numberOfServings) {
		
		List<Recipe> recipesInTimeToCook = getRecipeForPrepTime(timeToCook);
		List<Recipe> recipesInPriceRange = getRecipeInPriceRangeNumServings(price, customer_ID, numberOfServings);
		
		List<Recipe> resultRecipeList = new ArrayList<Recipe>();
		
		for (Recipe r1: recipesInPriceRange) {
			for (Recipe r2: recipesInTimeToCook) {
				if (r1.getRecipeId() == r2.getRecipeId()) {
					resultRecipeList.add(r1);
				}
			}
		}
		
		return resultRecipeList;
	}

	@Override
	public List<Recipe> getRecipeThatMeetSearch(double price, String categoryName, int numberOfServings) {
		
		List<Recipe> recipesInCategory = getRecipeInCategory(categoryName);
		List<Recipe> recipesInPriceRange = getRecipeInPriceRangeNumServings(price, numberOfServings);
		
		List<Recipe> resultRecipeList = new ArrayList<Recipe>();
		
		for (Recipe r1: recipesInPriceRange) {
			for (Recipe r2: recipesInCategory) {
				if (r1.getRecipeId() == r2.getRecipeId()) {
					resultRecipeList.add(r1);
				}
			}
		}
		
		return resultRecipeList;
	}

	@Override
	public List<Recipe> getRecipeThatMeetSearch(double price, int customer_ID, String categoryName, int numberOfServings) {
		
		List<Recipe> recipesInCategory = getRecipeInCategory(categoryName);
		List<Recipe> recipesInPriceRange = getRecipeInPriceRangeNumServings(price, customer_ID, numberOfServings);
		
		List<Recipe> resultRecipeList = new ArrayList<Recipe>();
		
		for (Recipe r1: recipesInPriceRange) {
			for (Recipe r2: recipesInCategory) {
				if (r1.getRecipeId() == r2.getRecipeId()) {
					resultRecipeList.add(r1);
				}
			}
		}
		
		return resultRecipeList;
	}

}
