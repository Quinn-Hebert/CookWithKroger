package com.cookwithkroger.rev2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cookwithkroger.rev2019.model.Cart;
import com.cookwithkroger.rev2019.model.CartDao;
import com.cookwithkroger.rev2019.model.Customer;
import com.cookwithkroger.rev2019.model.CustomerDao;
import com.cookwithkroger.rev2019.model.Product;
import com.cookwithkroger.rev2019.model.ProductDao;
import com.cookwithkroger.rev2019.model.Recipe;
import com.cookwithkroger.rev2019.model.RecipeDao;

@Controller 
public class SiteController {

	@Autowired
	private RecipeDao recipeDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CartDao cartDao;
	
	private List<Recipe> currentRecipes;
	
	public SiteController() {
		currentRecipes = new ArrayList<>();
	}
	
	@RequestMapping("/")
	public String displayMainPage() {
		currentRecipes.clear();
		return "mainPage";
	}
	
	@RequestMapping("/recipeList")
	public String displayRecipeList(@RequestParam String budget, 
									@RequestParam String category, 
									@RequestParam String servings,
									@RequestParam String cooktime,
									ModelMap modelHolder,
									HttpServletRequest request) {
		currentRecipes = recipeDao.getRecipeThatMeetSearch(Double.parseDouble(budget), 1, category, Integer.parseInt(cooktime), Integer.parseInt(servings));
		modelHolder.put("servings", servings);
		modelHolder.put("recipes", currentRecipes);
		return "recipeList";
	}
	
	@RequestMapping("/recipeDetail")
	public String showRecipeDetail(HttpServletRequest request) {
		String recipeId = request.getParameter("recipeId");
		request.setAttribute("recipe", recipeDao.getById(Integer.parseInt(recipeId)));
		return "recipeDetail";
	}
	
	@RequestMapping("/aboutUs")
	public String displayAboutUs() {
		return "aboutUs";
	}
	
	@RequestMapping("/cart")
	public String displayCart(HttpServletRequest request) {
		String[] ingredients = request.getParameterValues("ingredient");
		List<Product> ingredientList = new ArrayList<>();
		for (String i : ingredients) {
			ingredientList.add(productDao.getByUPC(Integer.parseInt(i)));
		}
		cartDao.emptyCart(1);
		cartDao.addItemsToCart(ingredientList, 1);
		request.setAttribute("ingredients", cartDao.getAllProductsInCart(1));
		request.setAttribute("ingQty", cartDao.getAllProductsWithQuantitiesInCart(1));
		return "cart";
	}
	
	@RequestMapping("/thankyouPage")
	public String displayThankyouPage() {
		return "thankyouPage";
	}
}
