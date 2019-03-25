package com.cookwithkroger.rev2019.model;

import java.util.List;

public interface RecipeDao {
	Recipe getById(int recipeId);
	List<Recipe> getRecipeInPriceRange(double price);
	List<Recipe> getRecipeInPriceRange(double price, int customer_ID);
	List<Recipe> getRecipeInCategory(String categoryName);
	List<Recipe> getRecipeForPrepTime(int timeToCook);
	List<Recipe> getRecipeInPriceRangeNumServings(double price, int numOfServings);
	List<Recipe> getRecipeInPriceRangeNumServings(double price, int customer_ID, int numOfServings);
	List<Recipe> getRecipeThatMeetSearch(double price, String categoryName, int timeToCook, int numberOfServings);
	List<Recipe> getRecipeThatMeetSearch(double price, int customer_ID, String categoryName,int timeToCook, int numberOfServings);
	List<Recipe> getRecipeThatMeetSearch(double price, int timeToCook, int numberOfServings);
	List<Recipe> getRecipeThatMeetSearch(double price, int customer_ID, int timeToCook, int numberOfServings);
	List<Recipe> getRecipeThatMeetSearch(double price, String categoryName, int numberOfServings);
	List<Recipe> getRecipeThatMeetSearch(double price, int customer_ID, String categoryName, int numberOfServings);
}
